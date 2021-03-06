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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@Api("游客管理")
@RequestMapping("/visitors")
public class VisitorsController {

    @Autowired
    private VisitorsService visitorsService;


    @ApiOperation(value="查询游客列表", notes="根据条件查询列表")
    @ApiImplicitParam(name = "visitors", value = "游客实体对象", required = false, dataType = "Visitors")
    @RequestMapping(value = "/selectVisitors", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
