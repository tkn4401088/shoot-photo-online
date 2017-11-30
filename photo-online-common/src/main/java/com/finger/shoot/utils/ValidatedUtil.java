/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.utils;


import com.finger.shoot.exception.ParamsCheckFailException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;


/**
 * 参数校验
 *
 * @author zb
 * @version 1.0
 * @since 1.0
 */
public class ValidatedUtil {

    /**
     * 错误信息处理
     * @param result  校验错误结果对象集合
     * @throws ParamsCheckFailException
     */
    public static void validatedParams(BindingResult result)throws ParamsCheckFailException {
        if(result!=null && result.getAllErrors()!=null && result.getAllErrors().size()>0){
            throw new ParamsCheckFailException(result.getAllErrors().get(0).getDefaultMessage());
        }
    }

    /**
     * 指定对象属性错误信息处理
     * @param result 校验错误结果对象集合
     * @param fileds 校验对象属性集合
     * @throws ParamsCheckFailException
     */
    public static void validatedParams(BindingResult result, List<String> fileds)throws ParamsCheckFailException{
        if(result!=null && result.getAllErrors()!=null && result.getAllErrors().size()>0 && fileds!=null && fileds.size()>0){
            for(String field : fileds){
                for(ObjectError error : result.getAllErrors()){
                    FieldError fieldError = (FieldError)error;
                    if(field.equals(fieldError.getField())){
                        throw new ParamsCheckFailException(error.getDefaultMessage());
                    }
                }
            }
        }
    }

}
