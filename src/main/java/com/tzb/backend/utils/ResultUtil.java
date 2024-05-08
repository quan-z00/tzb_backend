package com.tzb.backend.utils;

import com.tzb.backend.core.ResultWrapper;

/**
 * 用于生成API接口返回结果的封装工具类。
 * 该工具类提供了两个静态方法，用于生成成功或失败的返回结果对象。
 *
 * 示例使用：
 * <pre>
 *     {@code
 *     ResultWrapper successResult = ResultUtil.success(data);
 *     ResultWrapper failResult = ResultUtil.fail(code, msg);
 *     }
 * </pre>
 *
 * @see com.tzb.backend.core.ResultWrapper 返回结果封装注解，用于指示在返回结果中进行封装。
 * @author 29002
 */
public class ResultUtil {

    /**
     * 生成一个成功的返回结果对象。
     * @param data 结果数据
     * @return 成功的返回结果对象，封装了传入的数据
     */
    public static ResultWrapper success(Object data) {
        ResultWrapper resultWrapper = new ResultWrapper();
        resultWrapper.success(data);
        return resultWrapper;
    }

    /**
     * 生成一个失败的返回结果对象。
     * @param code 状态码
     * @param msg 消息
     * @return 失败的返回结果对象，封装了传入的状态码和消息
     */
    public static ResultWrapper fail(int code, String msg) {
        ResultWrapper resultWrapper = new ResultWrapper();
        resultWrapper.fail(code, msg);
        return resultWrapper;
    }
}
