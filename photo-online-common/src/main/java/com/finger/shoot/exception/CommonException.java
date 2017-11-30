/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.exception;

/**
 * 公共异常类
 *
 * @author zb
 * @version 1.0
 * @since 1.0
 */
public class CommonException extends RuntimeException {
    private String code; //异常对应的返回码
    private String msg; //异常对应的描述信息

    public CommonException() {
        super();
    }

    public CommonException(String message) {
        super(message);
        msg = message;
    }

    public CommonException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
