package com.mySSM.seckill.service.impl;

import com.mySSM.seckill.dao.SeckillDAO;
import com.mySSM.seckill.dao.SuccessSeckillDAO;
import com.mySSM.seckill.dto.Exposer;
import com.mySSM.seckill.dto.SeckillExecution;
import com.mySSM.seckill.entity.Seckill;
import com.mySSM.seckill.entity.SuccessSeckill;
import com.mySSM.seckill.enums.SeckillStateEnum;
import com.mySSM.seckill.exception.RepeatKillException;
import com.mySSM.seckill.exception.SeckillCloseException;
import com.mySSM.seckill.exception.SeckillException;
import com.mySSM.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillDAO seckillDAO;
    @Autowired
    private SuccessSeckillDAO successSeckillDAO;

    //md5盐值字符串，用于混淆md5
    private final String slat = "fu124@#yweu12hio#$%^&*123wefg456#$%^JKoi1k";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDAO.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDAO.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDAO.queryById(seckillId);
        if (seckill == null)
            return new Exposer(false, seckillId);
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //系统当前时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //转化特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }


    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑：减库存 + 记录购买行为
        Date nowTime = new Date();

        try {
            //减库存
            int updateCount = seckillDAO.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新记录，秒杀结束
                throw new SeckillCloseException("seckill is closes");
            } else {
                //记录购买行为
                int insertCount = successSeckillDAO.insertSuccessSeckill(seckillId, userPhone);
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //秒杀成功
                    SuccessSeckill successSeckill = successSeckillDAO.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successSeckill);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所以编译器异常转化为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }
}
