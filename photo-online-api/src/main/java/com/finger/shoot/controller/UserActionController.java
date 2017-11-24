package com.finger.shoot.controller;

import com.finger.portal.base.util.ResponseModel;
import com.finger.shoot.entity.GroupPhoto;
import com.finger.shoot.exception.ParamsCheckFailException;
import com.finger.shoot.utils.ExceptionPrintUtil;
import com.finger.shoot.utils.ValidatedUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pengmd on 2017/11/22.
 */
@Slf4j
@RestController
@RequestMapping("/userAction")
public class UserActionController {

    /**
     * 保存用户动作（访问）
     * 本次只计数，以后再记明细
     * @param groupPhoto
     * @param result
     * @return
     */
    @RequestMapping(value = "/saveUserAccessAction", method = RequestMethod.POST)
    public Object saveUserAction(@RequestBody GroupPhoto groupPhoto, BindingResult result){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();
        try {

        }catch (ParamsCheckFailException e){
            log.error(ExceptionPrintUtil.getMessage(e));
            e.printStackTrace();
            susResp = new ResponseModel(e.getCode(), e.getMsg());
        }catch (Exception e){
            susResp = ResponseModel.getFailedResponseModel().setData(e.getMessage());
            log.error(ExceptionPrintUtil.getMessage(e));
            e.printStackTrace();
        }
        return susResp;
    }
}
