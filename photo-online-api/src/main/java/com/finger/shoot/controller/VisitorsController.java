/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.controller;

import com.finger.portal.base.util.ResponseModel;

import com.finger.shoot.entity.Visitors;
import com.finger.shoot.exception.ParamsCheckFailException;
import com.finger.shoot.service.VisitorsService;
import com.finger.shoot.utils.ValidatedUtil;
import com.finger.shoot.utils.ExceptionPrintUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * 游客表控制类
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/visitors")
public class VisitorsController {

    @Autowired
    private VisitorsService visitorsService;


    //@ApiOperation(value="根据条件查询列表", notes="根据条件查询列表", response = ResponseModel.class)
    //@ApiImplicitParam(name = "visitors", value = "查询条件-visitors对象", required = false, dataType = "Visitors")
    @RequestMapping(value = "/selectVisitorss", method = RequestMethod.POST)
    public Object selectVisitorss(@RequestBody Visitors visitors, BindingResult result){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();
        try {
            //校验参数
            ValidatedUtil.validatedParams(result);

            List<Visitors> visitorss = visitorsService.selectVisitorss(visitors);
            susResp.setData(visitorss);
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
