package com.mySSM.seckill.dao;

import com.mySSM.seckill.entity.SuccessSeckill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessSeckillDAO {

    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入的数量
     */
    int insertSuccessSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);


    /**
     * 根据id查询successSeckill并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessSeckill queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone") long userPhone);
}
