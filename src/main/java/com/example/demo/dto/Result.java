package com.example.demo.dto;

public class Result {

    private int code=0;//响应状态码
    private String msg="ok";//响应消息
    private Object data;//响应数据

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Object data) {
        this.data = data;
    }

    public static Result ok(){
        return new Result();
    }

    public static Result error(String msg){
        return new Result(-1,msg);
    }

    public static Result ok(Object data){
        return new Result(data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
