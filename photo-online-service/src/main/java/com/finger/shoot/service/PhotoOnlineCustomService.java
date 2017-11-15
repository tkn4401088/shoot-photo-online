/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service;

import  com.finger.shoot.entity.PhotoOnlineCustom;
import java.util.List;


/**
 * 照片直播客户制作物表接口
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface PhotoOnlineCustomService {

	/**
	 * 新增
	 * @param photoOnlineCustom
	 * @return
	 */
	int insert(PhotoOnlineCustom photoOnlineCustom);

	/**
	 * 批量新增
	 * @param photoOnlineCustoms
	 * @return
	 */
	int batchInsert(List<PhotoOnlineCustom> photoOnlineCustoms);

	/**
	 * 根据ID来删除数据
	 * @param id
	 * @return
	 */
	int deleteById(Long id);

	/**
	 * 根据ID来更新数据
	 * @param photoOnlineCustom
	 * @return
	 */
	int updateById(PhotoOnlineCustom photoOnlineCustom);

	/**
	 * 根据ID来查询数据
	 * @param id
	 * @return
	 */
	PhotoOnlineCustom selectById(Long id);

	/**
	 * 根据条件来查询List
	 * @param photoOnlineCustom
	 * @return
	 */
	List<PhotoOnlineCustom> selectPhotoOnlineCustoms(PhotoOnlineCustom photoOnlineCustom);

	/**
	 * 分页查询
	 * @param photoOnlineCustom
	 * @return
	 */
	List<PhotoOnlineCustom> selectPagePhotoOnlineCustoms(PhotoOnlineCustom photoOnlineCustom);
}

