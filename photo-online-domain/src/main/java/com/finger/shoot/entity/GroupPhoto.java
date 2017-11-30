/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.entity;


import com.finger.portal.base.model.PageModel;
import lombok.Data;

import java.util.List;

/**
 * 团照片信息表
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Data
public class GroupPhoto extends PageModel{

	//columns START
    /**
     * id       db_column: id 
     */
	private Long id;
    /**
     * 团ID       db_column: group_id 
     */
	private Long groupId;
    /**
     * 相机图片url       db_column: camera_base_url 
     */
	private String cameraBaseUrl;
    /**
     * 原始url       db_column: base_url 
     */
	private String baseUrl;
    /**
     * 修改后url       db_column: update_url 
     */
	private String updateUrl;
    /**
     * 缩略图url       db_column: thumbnail_url 
     */
	private String thumbnailUrl;
    /**
     * 文件名       db_column: filename 
     */
	private String filename;
    /**
     * 状态：1 未处理；2：已处理 3：已打印       db_column: STATUS 
     */
	private Integer status;
    /**
     * 创建人       db_column: creator 
     */
	private String creator;
    /**
     * 创建时间       db_column: create_time 
     */
	private java.util.Date createTime;
    /**
     * 照片原图文件大小       db_column: filesize 
     */
	private Integer filesize;
    /**
     * 相片类型(jpg,png等等)       db_column: type 
     */
	private String type;
    /**
     * 是否删除标志 0已删除 1未删除       db_column: yn 
     */
	private Integer yn;
    /**
     * 是否删除到回收站 0已删除到回收站 1未删除到回收站       db_column: is_delete 
     */
	private Integer isDelete;
    /**
     * 是否已经下载 0未下载 1已下载       db_column: is_download 
     */
	private Integer isDownload;
    /**
     * faceStatus       db_column: face_status 
     */
	private Integer faceStatus;
    /**
     * 原图来源(1PC 2android 3ios)       db_column: camera_url_source 
     */
	private Integer cameraUrlSource;
    /**
     * 压缩图来源(1PC 2android 3ios)       db_column: base_url_source 
     */
	private Integer baseUrlSource;
    /**
     * 评分       db_column: score 
     */
	private Double score;
    /**
     * 照片相机raw原图地址       db_column: camera_raw_url 
     */
	private String cameraRawUrl;
    /**
     * 是否删除到精选照片回收站       db_column: is_delete_choice 
     */
	private Integer isDeleteChoice;
    /**
     * 是否精选照片       db_column: is_choice 
     */
	private Integer isChoice;
    /**
     * 是否移动端删除(0否，1是)       db_column: is_mobile 
     */
	private Integer isMobile;
    /**
     * 入选精品照片时间       db_column: choice_time 
     */
	private java.util.Date choiceTime;
    /**
     * 压缩图文件大小       db_column: base_filesize 
     */
	private Integer baseFilesize;
    /**
     * 美化后图片文件大小       db_column: update_filesize 
     */
	private Integer updateFilesize;
	//columns END

    private List<String> ids;

    private String photographer;

    private Long tripId;  //行程ID

    private String journeyName;  //行程景点名称

}

