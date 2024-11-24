//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.alipaydemo.common;

import java.io.Serializable;

public class Res implements Serializable {
    private Object data;
    private Integer code;
    private String msg;
    private boolean success;

    public Res() {
    }

    public Res(Integer code) {
        this(code, (String)null, (Object)null);
    }

    public Res(Integer code, String message) {
        this(code, message, (Object)null);
    }

    public Res(Integer code, String message, Object data) {
        this.code = code;
        this.msg = message;
        this.data = data;
        this.success = ResultCode.SUCCESS.getCode().equals(code);
    }

    public static Res status(boolean flag) {
        return flag ? success() : failed(ResultCode.BAD_REQUEST.getMsg());
    }

    public static Res success() {
        return new Res(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), (Object)null);
    }

    public static Res success(Object data) {
        return new Res(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static Res success(String message, Object data) {
        return new Res(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static Res failed(String message, Object data) {
        return new Res(ResultCode.BAD_REQUEST.getCode(), message, data);
    }

    public static Res failed(String message) {
        return new Res(ResultCode.BAD_REQUEST.getCode(), message);
    }

    public static Res failed(Integer code, String message) {
        return new Res(code, message);
    }

    public static Res failed(ResultCode result) {
        return new Res(result.getCode(), result.getMsg());
    }

    public static Res failed(ResultCode result, String message) {
        return new Res(result.getCode(), message);
    }

    public static Res failed(Integer code, String message, Object data) {
        return new Res(code, message, data);
    }

    public Object getData() {
        return this.data;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Res)) {
            return false;
        } else {
            Res other = (Res)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isSuccess() != other.isSuccess()) {
                return false;
            } else {
                label49: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label49;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label49;
                    }

                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Res;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + (this.isSuccess() ? 79 : 97);
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        return result;
    }

    public String toString() {
        return "Res(data=" + this.getData() + ", code=" + this.getCode() + ", msg=" + this.getMsg() + ", success=" + this.isSuccess() + ")";
    }
}
