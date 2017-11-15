/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.entity;


import com.finger.portal.base.model.PageModel;
import lombok.Data;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 照片直播转发记录表
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Data
public class PhotoOnlineForward extends PageModel {

	//columns START
    /**
     * id       db_column: id 
     */
	private Long id;
    /**
     * 游客ID- 首发人       db_column: v_id 
     */
	private Long vid;
    /**
     * 游客ID- 上级转发人       db_column: pre_id 
     */
	private Long preId;
    /**
     * 游客ID- 转发人       db_column: forward_id 
     */
	private Long forwardId;
    /**
     * 游客ID- 转发人       db_column: reader_id 
     */
	private Long readerId;
    /**
     * 转发时间       db_column: forward_time 
     */
	private java.util.Date forwardTime;
    /**
     * 阅读时间       db_column: read_time 
     */
	private java.util.Date readTime;
    /**
     * 阅读时间       db_column: parent_id 
     */
	private Long parentId;
    /**
     * 层级       db_column: level 
     */
	private Integer level;
    /**
     * 访问对象,1直播列表，2直播团，3单张照片，4生成物       db_column: forward_obj 
     */
	private Integer forwardObj;
    /**
     * 根据访问对象取不同的值，obj为1时，此处为空，obj为2时此处为t_order表的ID，obj为3时此处为t_group_photo表中的ID, obj为4时此处为t_photo_custom表中的ID       db_column: forward_obj_val 
     */
	private Long forwardObjVal;
	//columns END

}

