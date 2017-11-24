/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service;

import com.finger.shoot.entity.PhotoOnline;
import java.util.List;


/**
 * 直播团设置接口
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface PhotoOnlineService {

	/**
	 * 保存及修改配置
	 * @param photoOnline
	 * @return
     */
	String savePhotoOnline(PhotoOnline photoOnline);

	/**
	 * 根据订单ID来查询数据
	 * @param orderId
	 * @return
	 */
	PhotoOnline selectByOrderId(Long orderId);

	/**
	 * 根据ID来查询数据
	 * @param id
	 * @return
	 */
	PhotoOnline selectById(Long id);


	/**
	 * 查询所有直播团
	 * @param photoOnline
	 * @return
     */
	List<PhotoOnline> selectPagePhotoOnline(PhotoOnline photoOnline);


	/**
	 * 更新状态
	 * @param orderId
	 * @return
	 */
	int updatePhoneOnlineYnByOrderId(Long orderId);
	/**
	 * 更新直播团状态
	 * @param orderId
	 * @return
	 */
	int updateOrderByOrderId(Long orderId);
}

