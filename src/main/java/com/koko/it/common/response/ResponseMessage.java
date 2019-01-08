package com.koko.it.common.response;

import com.google.common.collect.Maps;
import com.koko.it.common.constants.Constants;

public class ResponseMessage {
    private int code;
    private String msg;
    private long count;
    private Object data;


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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseMessage() {}
    public ResponseMessage(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseMessage(int code, String msg, long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static ResponseMessage ok(Object data) {
        return new ResponseMessage(Constants.Response.SUCCESS, Constants.Response.SUCCESS_MSG, data);
    }

    public static ResponseMessage ok(long count,Object data) {
        return new ResponseMessage(Constants.Response.SUCCESS, Constants.Response.SUCCESS_MSG, count, data);
    }

    public static ResponseMessage ok() {
        return new ResponseMessage(Constants.Response.SUCCESS, Constants.Response.SUCCESS_MSG, Maps.newHashMap());
    }

    public static ResponseMessage fail(String msg) {
        return new ResponseMessage(Constants.Response.FAIL, msg, Maps.newHashMap());
    }


}
