/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service;

import  com.finger.shoot.entity.OrderPhotoOnlineConf;
import java.util.List;


/**
 * 直播团设置接口
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface OrderPhotoOnlineConfService {

	/**
	 * 新增
	 * @param orderPhotoOnlineConf
	 * @return
	 */
	int insert(OrderPhotoOnlineConf orderPhotoOnlineConf);

	/**
	 * 批量新增
	 * @param orderPhotoOnlineConfs
	 * @return
	 */
	int batchInsert(List<OrderPhotoOnlineConf> orderPhotoOnlineConfs);

	/**
	 * 根据ID来删除数据
	 * @param id
	 * @return
	 */
	int deleteById(Long id);

	/**
	 * 根据ID来更新数据
	 * @param orderPhotoOnlineConf
	 * @return
	 */
	int updateById(OrderPhotoOnlineConf orderPhotoOnlineConf);

	/**
	 * 根据ID来查询数据
	 * @param id
	 * @return
	 */
	OrderPhotoOnlineConf selectById(Long id);

	/**
	 * 根据条件来查询List
	 * @param orderPhotoOnlineConf
	 * @return
	 */
	List<OrderPhotoOnlineConf> selectOrderPhotoOnlineConfs(OrderPhotoOnlineConf orderPhotoOnlineConf);

	/**
	 * 分页查询
	 * @param orderPhotoOnlineConf
	 * @return
	 */
	List<OrderPhotoOnlineConf> selectPageOrderPhotoOnlineConfs(OrderPhotoOnlineConf orderPhotoOnlineConf);
}

