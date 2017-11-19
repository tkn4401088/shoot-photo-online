/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.controller;

import com.finger.portal.base.util.ResponseModel;

import com.finger.shoot.common.Constants;
import com.finger.shoot.entity.PhotoOnlineCustom;
import com.finger.shoot.exception.ParamsCheckFailException;
import com.finger.shoot.service.PhotoOnlineCustomService;
import com.finger.shoot.utils.BeanUtil;
import com.finger.shoot.utils.ValidatedUtil;
import com.finger.shoot.utils.ExceptionPrintUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * 照片直播客户制作物表控制类
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/photoOnlineCustom")
public class PhotoOnlineCustomController {

    @Autowired
    private PhotoOnlineCustomService photoOnlineCustomService;


    /**
     * 根据ID查询拼图|小视频
     * @param photoOnlineCustom
     * @param result
     * @return
     */
    @RequestMapping(value = "/selectPhotoOnlineCustomById", method = RequestMethod.POST)
    public Object selectPhotoOnlineCustomById(@RequestBody PhotoOnlineCustom photoOnlineCustom, BindingResult result){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();
        try {
            //校验参数
            ValidatedUtil.validatedParams(result);

            photoOnlineCustom = photoOnlineCustomService.selectById(photoOnlineCustom.getId());
            if(null != photoOnlineCustom) {
                susResp.setData(BeanUtil.getProperties(photoOnlineCustom,
                        new String[]{"id", "worksType", "url", "filesize", "accessNums","orderId"},
                        false));
            }
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



    /**
     * 保存拼图|小视频
     * @param photoOnlineCustom
     * @param result
     * @return
     */
    @RequestMapping(value = "/savePhotoOnlineCustom", method = RequestMethod.POST)
    public Object savePhotoOnlineCustom(@RequestBody PhotoOnlineCustom photoOnlineCustom, BindingResult result){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();
        try {
            //校验参数
            ValidatedUtil.validatedParams(result);

            int flag = photoOnlineCustomService.insert(photoOnlineCustom);
            if(flag <=0) {
                susResp = new ResponseModel(Constants.ERR_CODE_500, Constants.ERR_MSG_MAP.get(Constants.ERR_CODE_500));
            }
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
