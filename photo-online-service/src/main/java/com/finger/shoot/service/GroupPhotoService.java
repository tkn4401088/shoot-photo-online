/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service;

import  com.finger.shoot.entity.GroupPhoto;
import java.util.List;


/**
 * 团照片信息表接口
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface GroupPhotoService {

	/**
	 * 新增
	 * @param groupPhoto
	 * @return
	 */
	int insert(GroupPhoto groupPhoto);

	/**
	 * 根据ID来查询数据
	 * @param id
	 * @return
	 */
	GroupPhoto selectById(Long id);

	/**
	 * 根据条件来查询List
	 * @param groupPhoto
	 * @return
	 */
	List<GroupPhoto> selectGroupPhotos(GroupPhoto groupPhoto);

	/**
	 * 分页查询
	 * @param groupPhoto
	 * @return
	 */
	List<GroupPhoto> selectPageGroupPhotos(GroupPhoto groupPhoto);
}

