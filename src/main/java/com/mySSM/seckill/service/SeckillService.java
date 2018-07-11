package com.mySSM.seckill.service;

import com.mySSM.seckill.dto.Exposer;
import com.mySSM.seckill.dto.SeckillExecution;
import com.mySSM.seckill.entity.Seckill;
import com.mySSM.seckill.exception.RepeatKillException;
import com.mySSM.seckill.exception.SeckillCloseException;
import com.mySSM.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在使用者角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 获得单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启是否输出秒杀接口地址
     * 是否输出系统时间和秒杀时间
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException,SeckillCloseException,RepeatKillException;
}
