package com.tzb.backend.web.exception;

public class NotExistException extends RuntimeException{

    private String msg;
    public NotExistException(String msg) {
        super("文物不存在");
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
