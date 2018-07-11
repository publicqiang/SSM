package com.mySSM.seckill.dto;

import com.mySSM.seckill.entity.SuccessSeckill;
import com.mySSM.seckill.enums.SeckillStateEnum;

/**
 * 封装秒杀执行后结果
 */
public class SeckillExecution {

    private long seckillId;

    //秒杀执行结果状态
    private int state;

    //状态表示
    private String stateInfo;

    //秒杀成功对象
    private SuccessSeckill successSeckill;

    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SuccessSeckill successSeckill) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.successSeckill = successSeckill;
    }

    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessSeckill getSuccessSeckill() {
        return successSeckill;
    }

    public void setSuccessSeckill(SuccessSeckill successSeckill) {
        this.successSeckill = successSeckill;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successSeckill=" + successSeckill +
                '}';
    }
}
