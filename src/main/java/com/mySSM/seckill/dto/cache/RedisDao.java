package com.mySSM.seckill.dto.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.mySSM.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        this.jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public Seckill getSeckill(long seckillId) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String key = "seckill :" + seckillId;
            //并没有实现内部序列化操作
            // get->byte[] -> 反序列化 -->Object
            //采用自定义序列化方式
            byte[] bytes = jedis.get(key.getBytes());
            //缓存中获取到
            if (bytes != null) {
                //空对象
                Seckill seckill = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                //seckill 被反序列化
                return seckill;
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        Jedis jedis = null;
        //set Object -->序列化 -->byte[]
        try {
            jedis = jedisPool.getResource();
            String key = "seckill :" + seckill.getSeckillId();
            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            //超市缓存
            int timeout = 60 * 60;//一小时
            String result = jedis.setex(key.getBytes(), timeout, bytes);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
}
