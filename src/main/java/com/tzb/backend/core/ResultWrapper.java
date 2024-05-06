package com.tzb.backend.core;

import lombok.Getter;

/**
 * 用于表示API接口的返回结果。
 *
 * @author 29002
 */
@Getter
public class ResultWrapper {

    /** 结果状态码。 */
    private int code;

    /** 结果消息。 */
    private String msg;

    /** 结果数据。 */
    private Object data;

    /** 返回结果的时间戳。 */
    private final Long timestamp;

    /**
     * 默认构造函数，初始化时间戳为当前时间。
     */
    public ResultWrapper() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 创建一个成功的结果对象。
     *
     * @param data 结果数据
     */
    public void success(Object data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

    /**
     * 创建一个失败的结果对象。
     *
     * @param code 状态码
     * @param msg  消息
     */
    public void fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
}
