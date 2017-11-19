/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.entity;


import com.finger.portal.base.model.PageModel;
import lombok.Data;

/**
 * 照片直播客户制作物表
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Data
public class PhotoOnlineCustom extends PageModel {

	//columns START
    /**
     * id       db_column: id 
     */
	private Long id;
    /**
     * 游客ID       db_column: v_id 
     */
	private Long vid;

    private Long orderId;
    /**
     * 制作物类型：1拼图、2小视频、3杂志封面       db_column: works_type 
     */
	private Integer worksType;
    /**
     * 制作物URL       db_column: url 
     */
	private String url;
    /**
     * 文件大小       db_column: filesize 
     */
	private Long filesize;
    /**
     * 访问次数       db_column: access_nums 
     */
	private Integer accessNums;
    /**
     * 制作物对象JSON字符串       db_column: objects 
     */
	private String objects;
    /**
     * createdTime       db_column: created_time 
     */
	private java.util.Date createdTime;
	//columns END

}

