package com.mySSM.seckill.dto;

/**
 * 秒杀返回结果
 * 所有ajax返回的类型，封装json结果
 *
 * @param <T>
 */
public class SeckillResult<T> {

    private Boolean success;

    private T data;

    private String error;

    public SeckillResult(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(Boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
