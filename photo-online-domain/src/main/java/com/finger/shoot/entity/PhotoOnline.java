/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.entity;


import com.finger.portal.base.model.PageModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 直播团设置
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Data
public class PhotoOnline extends PageModel {

	//columns START
    /**
     * id       db_column: id 
     */
    @NotNull(message = "直播间id必填", groups = {ValidateJudgeIsOnlineTour.class})
	private Long id;
    /**
     * 团订单ID       db_column: order_id
     */
    private Long orderId;

    @NotNull(message = "订单号必填", groups = {ValidateJudgeIsOnlineTour.class})
    private String orderNo;  //订单号

    /**
     * 直播类型ID       db_column: live_type_id 
     */
	private String liveTypeId;
    /**
     * 直播类型名称       db_column: live_type_name 
     */
	private String liveTypeName;
    /**
     * 直播主题
     */
    private String liveName;
    /**
     * 直播封面       db_column: cover_img 
     */
	private String coverImg;
    /**
     * 直播详情banner图       db_column: banner_img 
     */
	private String bannerImg;
    /**
     * 开放时间：1永久开发，2自定义时段       db_column: open_time 
     */
	private Integer openTime;
    /**
     * 开始时间，时间段开放时有效       db_column: start_time 
     */
	private java.util.Date startTime;
    /**
     * 结束时间，时间段开放时有效       db_column: end_time 
     */
	private java.util.Date endTime;
    /**
     * 开放权限：1完全开放 2密码访问       db_column: open_auth 
     */
	private Integer openAuth;
    /**
     * 访问密码，密码访问时有效       db_column: access_pwd 
     */
	private String accessPwd;
    /**
     * 活动介绍（不超过255个字）       db_column: introduce 
     */
	private String introduce;

    /**
     * 照片数       db_column: photo_num
     */
    private Integer photoNum;

    /**
     * 访问数       db_column: access_num
     */
    private Integer accessNum;

    /**
     * 转发数       db_column: forward_num
     */
    private Integer forwardNum;

    private String address; //地点
    /**
     * 创建时间       db_column: created_time 
     */
	private java.util.Date createdTime;
    /**
     * 修改时间       db_column: updated_time 
     */
	private java.util.Date updatedTime;
	//columns END
    /**
     * 创建时间       db_column: created_time
     */
    private String url; //地点
    /**
     * 创建时间       db_column: created_time
     */
    private String QRCode; //地点
    /**
     * 是否需要审核：1是 0否       db_column: is_approval
     */
    private Integer isApproval;
    /**
     * 是否需要审核：1是 0否       db_column: is_photo_water
     */
    private Integer isPhotoWater;
    /**
     * 景点    db_column: idid
     */
    private Integer tripid;
    /**
     * 景点名称       db_column: journey_name
     */
    private String journeyName;
    /**
     * 行程日期       db_column: journey_day
     */
    private Integer journeyDay;
    /**
     * 时间段     db_column: period_type
     */
    private Integer periodType;




    /* ********************** 参数校验类 *******************************/
    public interface ValidateJudgeIsOnlineTour{

    }
}

