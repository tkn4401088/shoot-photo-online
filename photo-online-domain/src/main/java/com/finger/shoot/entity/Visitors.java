/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.entity;


import com.finger.portal.base.model.PageModel;
import lombok.Data;

/**
 * 游客表
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Data
public class Visitors extends PageModel {

	//columns START
    /**
     * id       db_column: id 
     */
	private Long id;
    /**
     * 昵称       db_column: nick_name 
     */
	private String nickName;
    /**
     * 头像       db_column: head_img 
     */
	private String headImg;
    /**
     * 游客来源：1微信，2QQ，3XXX       db_column: visitor_src 
     */
	private Integer visitorSrc;
    /**
     * 来源ID       db_column: open_id 
     */
	private String openId;
    /**
     * 最后一次访问时间       db_column: last_access_time 
     */
	private java.util.Date lastAccessTime;
    /**
     * createdTime       db_column: created_time 
     */
	private java.util.Date createdTime;
	//columns END

}

