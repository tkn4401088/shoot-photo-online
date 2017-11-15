/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.exception;


import com.finger.shoot.common.contants.ExceptionConstant;

/**
 * 参数校验异常类
 *
 * @author zb
 * @version 1.0
 * @since 1.0
 */
public class ParamsCheckFailException extends CommonException {
    public ParamsCheckFailException() {
        super(ExceptionConstant.PARAMS_CHECK_FAIL_CODE, ExceptionConstant.PARAMS_CHECK_FAIL_MSG);
    }

    public ParamsCheckFailException(String msg) {
        super(ExceptionConstant.PARAMS_CHECK_FAIL_CODE, msg);
    }
}
