package com.lemon.common;

import lombok.Data;

/**
 * @ClassName Result
 * @Description 前端数据统一封装
 * @Author lemon
 * @Date 15:21 2022/11/9
 * @Version 2.1
 **/
@Data
public class Result {
    private int code;   //编码 200成功 400失败
    private String msg; //成功 失败
    private long Total; //总记录数
    private Object data; //数据

    public static Result fail(){
        return result(400,"失败",0L,null);
    }

    public static Result success(Object data){
        return result(200,"成功",0L,data);
    }

    public static Result success(){
        return result(200,"成功",0L,null);
    }

    public static Result success(Object data ,long total){
        return result(200,"成功",total,data);
    }

    private static Result result(int code,String msg,long total,Object data){
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        res.setData(data);
        return res;
    }


}
