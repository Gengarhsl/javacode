package com.example.alipaydemo.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AjaxJson {
    private int status; // 状态码，0 表示成功，非 0 表示失败
    private String msg; // 消息，成功或失败的说明信息
    private Object data; // 返回的数据对象

    public AjaxJson() {
        this.status = 0; // 默认状态为成功
    }

    public AjaxJson(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public AjaxJson(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    // Getters and setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    // Method to return exception information
    public static AjaxJson returnExceptionInfo(String errorMsg) {
        return new AjaxJson(500, errorMsg); // 500 表示服务器内部错误
    }
}
