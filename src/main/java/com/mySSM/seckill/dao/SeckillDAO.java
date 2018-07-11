package com.mySSM.seckill.dao;

import com.mySSM.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface SeckillDAO {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 如果影响行数 > 1,表示更新的记录数
     */
    int reduceNumber(@Param("seckillId")long seckillId, @Param("killTime")Date killTime);

    /**
     *根据主键查询商品
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit")int limit);
}
