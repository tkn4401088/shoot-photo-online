/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.controller;

import com.finger.portal.base.util.ResponseModel;

import com.finger.shoot.entity.PhotoOnlineCustom;
import com.finger.shoot.exception.ParamsCheckFailException;
import com.finger.shoot.service.PhotoOnlineCustomService;
import com.finger.shoot.utils.ValidatedUtil;
import com.finger.shoot.utils.ExceptionPrintUtil;
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


   // @ApiOperation(value="根据条件查询列表", notes="根据条件查询列表", response = ResponseModel.class)
    //@ApiImplicitParam(name = "photoOnlineCustom", value = "查询条件-photoOnlineCustom对象", required = false, dataType = "PhotoOnlineCustom")
    @RequestMapping(value = "/selectPhotoOnlineCustoms", method = RequestMethod.POST)
    public Object selectPhotoOnlineCustoms(@RequestBody PhotoOnlineCustom photoOnlineCustom, BindingResult result){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();
        try {
            //校验参数
            ValidatedUtil.validatedParams(result);

            List<PhotoOnlineCustom> photoOnlineCustoms = photoOnlineCustomService.selectPhotoOnlineCustoms(photoOnlineCustom);
            susResp.setData(photoOnlineCustoms);
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
