/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service;

import  com.finger.shoot.entity.PhotoOnlineForward;
import java.util.List;


/**
 * 照片直播转发记录表接口
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface PhotoOnlineForwardService {

	/**
	 * 新增
	 * @param photoOnlineForward
	 * @return
	 */
	int insert(PhotoOnlineForward photoOnlineForward);

	/**
	 * 批量新增
	 * @param photoOnlineForwards
	 * @return
	 */
	int batchInsert(List<PhotoOnlineForward> photoOnlineForwards);

	/**
	 * 根据ID来删除数据
	 * @param id
	 * @return
	 */
	int deleteById(Long id);

	/**
	 * 根据ID来更新数据
	 * @param photoOnlineForward
	 * @return
	 */
	int updateById(PhotoOnlineForward photoOnlineForward);

	/**
	 * 根据ID来查询数据
	 * @param id
	 * @return
	 */
	PhotoOnlineForward selectById(Long id);

	/**
	 * 根据条件来查询List
	 * @param photoOnlineForward
	 * @return
	 */
	List<PhotoOnlineForward> selectPhotoOnlineForwards(PhotoOnlineForward photoOnlineForward);

	/**
	 * 分页查询
	 * @param photoOnlineForward
	 * @return
	 */
	List<PhotoOnlineForward> selectPagePhotoOnlineForwards(PhotoOnlineForward photoOnlineForward);
}

