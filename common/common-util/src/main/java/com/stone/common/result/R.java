package com.stone.common.result;

import lombok.Data;

/**
 * @author stonestart
 * @create 2022/10/27 - 20:43
 *全局统一返回结果类
 */
@Data
public class R<T> {
    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private T data;

    public R(){}

    // 返回数据
    protected static <T> R<T> build(T data) {
        R<T> R = new R<T>();
        if (data != null)
            R.setData(data);
        return R;
    }

    public static <T> R<T> build(T body, Integer code, String message) {
        R<T> R = build(body);
        R.setCode(code);
        R.setMessage(message);
        return R;
    }

    public static <T> R<T> build(T body, ResultCodeEnum resultCodeEnum) {
        R<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static<T> R<T> ok(){
        return R.ok(null);
    }

    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> R<T> ok(T data){
        R<T> R = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> R<T> fail(){
        return R.fail(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> R<T> fail(T data){
        R<T> R = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public R<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public R<T> code(Integer code){
        this.setCode(code);
        return this;
    }

}
