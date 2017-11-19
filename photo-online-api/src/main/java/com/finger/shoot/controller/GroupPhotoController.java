/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.controller;

import com.finger.portal.base.util.ResponseModel;

import com.finger.shoot.entity.GroupPhoto;
import com.finger.shoot.exception.ParamsCheckFailException;
import com.finger.shoot.service.GroupPhotoService;
import com.finger.shoot.utils.ValidatedUtil;
import com.finger.shoot.utils.ExceptionPrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * 团照片信息表控制类
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/groupPhoto")
public class GroupPhotoController {

    @Autowired
    private GroupPhotoService groupPhotoService;

    /**
     * 查询团下面的照片
     * @param groupPhoto
     * @param result
     * @return
     */
    @RequestMapping(value = "/selectGroupPhotos", method = RequestMethod.POST)
    public Object selectGroupPhotos(@RequestBody GroupPhoto groupPhoto, BindingResult result){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();
        try {
            //校验参数
            ValidatedUtil.validatedParams(result);

            List<GroupPhoto> groupPhotos = groupPhotoService.selectGroupPhotos(groupPhoto);
            susResp.setData(groupPhotos);
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
