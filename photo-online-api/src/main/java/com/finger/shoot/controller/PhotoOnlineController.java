/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.controller;

import com.finger.portal.base.util.ResponseModel;

import com.finger.shoot.common.Constants;
import com.finger.shoot.entity.PhotoOnline;
import com.finger.shoot.entity.StaticData;
import com.finger.shoot.exception.ParamsCheckFailException;
import com.finger.shoot.service.PhotoOnlineService;
import com.finger.shoot.service.StaticDataService;
import com.finger.shoot.utils.BeanUtil;
import com.finger.shoot.utils.ValidatedUtil;
import com.finger.shoot.utils.ExceptionPrintUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 直播团设置控制类
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@Api("直播团设置")
@RequestMapping("/photoOnline")
public class PhotoOnlineController {

    @Autowired
    private PhotoOnlineService photoOnlineService;

    @Autowired
    private StaticDataService staticDataService;


    @ApiOperation(value="保存及修改直播团设置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "orderId", value = "订单ID", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "liveName", value = "直播名称", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "liveType", value = "直播类型", required = true)
    })
    @RequestMapping(value = "/savePhotoOnline", method = RequestMethod.POST)
    public Object savePhotoOnline(@RequestBody PhotoOnline photoOnlineConf, BindingResult result){
        ResponseModel susResp = null;
        try {
            //校验参数
            ValidatedUtil.validatedParams(result);
            String code = photoOnlineService.savePhotoOnline(photoOnlineConf);
            susResp = new ResponseModel(code, Constants.ERR_MSG_MAP.get(code));
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
     * 取消直播团设置信息
     * @return
     */
    @ApiOperation(value="根据订单ID变更直播团状态")
    @RequestMapping(value = "/changeStatusByOrderId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object changeStatusByOrderId(@RequestBody PhotoOnline photoOnline, BindingResult result){
        ResponseModel susResp = null;
        try {
            //校验参数
            ValidatedUtil.validatedParams(result);
            String code = photoOnlineService.changeStatusByOrderId(photoOnline);
            susResp = new ResponseModel(code, Constants.ERR_MSG_MAP.get(code));
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

    @ApiOperation(value="根据订单ID查询直播设置")
    @ApiResponses({
         @ApiResponse(code = 500, message = "哟吼")
    })
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "Long", name = "orderId", value = "订单ID", required = true) })
    @ApiImplicitParam(name = "photoOnline", value = "直播团配置对象", required = false, dataType = "PhotoOnline")
    @RequestMapping(value = "/selectPhotoOnlineByOrderId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object selectPhotoOnlineByOrderId(@RequestBody PhotoOnline photoOnline, BindingResult result){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();
        try {
            //校验参数
            ValidatedUtil.validatedParams(result);
            PhotoOnline retPhotoOnline = photoOnlineService.selectByOrderId(photoOnline.getOrderId());
            if(null != retPhotoOnline) {
                susResp.setData(BeanUtil.getProperties(retPhotoOnline,
                        new String[]{"openTime","endTime","openAuth","liveTypeId","liveTypeName","liveName", "coverImg","bannerImg", "startTime","photoNum","accessNum","forwardNum", "introduce"},
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
     * 根据ID查询直播团
     * @param photoOnline
     * @param result
     * @return
     */
    @RequestMapping(value = "/selectPhotoOnlineById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object selectPhotoOnlineById(@RequestBody PhotoOnline photoOnline, BindingResult result){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();
        try {
            //校验参数
            ValidatedUtil.validatedParams(result);
            PhotoOnline retPhotoOnline = photoOnlineService.selectById(photoOnline.getId());
            if(null != retPhotoOnline) {
                susResp.setData(BeanUtil.getProperties(retPhotoOnline,
                        new String[]{"orderNo","liveName", "coverImg","bannerImg", "startTime","photoNum","accessNum","forwardNum", "introduce"},
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
     * 查询所有直播团
     * @param photoOnline
     * @param result
     * @return
     */
    @RequestMapping(value = "/selectPhotoOnline", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object selectPhotoOnline(@RequestBody PhotoOnline photoOnline, BindingResult result){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();
        try {
            //校验参数
            ValidatedUtil.validatedParams(result);
            List<PhotoOnline> retPhotoOnline = photoOnlineService.selectPagePhotoOnline(photoOnline);
            if(null != retPhotoOnline) {
                susResp.setData(BeanUtil.getListProperties(retPhotoOnline,
                        new String[]{"id","orderId","curPage","pageCount","rowsCount","liveName", "coverImg","photoNum","accessNum","forwardNum",
                                "bannerImg","startTime", "openAuth","introduce"},
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
     * 查询所有直播团类型
     * @return
     */
    @RequestMapping(value = "/selectPhotoOnlineTypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object selectPhotoOnlineTypes(){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();
        try {
            List<StaticData> staticDataList = staticDataService.selectStaticDataByType(Constants.PHOTO_ONLINE_STATIC_DATA_TYPE);
            if(null != staticDataList) {
                susResp.setData(BeanUtil.getListProperties(staticDataList,
                        new String[]{"dataName", "dataValue"},
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
}
