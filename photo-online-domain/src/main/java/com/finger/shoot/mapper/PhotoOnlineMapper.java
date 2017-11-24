/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.mapper;


import com.finger.shoot.entity.PhotoOnline;

import java.util.List;

/**
 * 直播团设置Mapper类
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface PhotoOnlineMapper {

    int deleteById(Long id);

    int insert(PhotoOnline photoOnline);

    PhotoOnline selectByOrderId(Long orderId);

    /**
     * 查询所有直播团
     * @param photoOnline
     * @return
     */
    List<PhotoOnline> selectPagePhotoOnline(PhotoOnline photoOnline);


    int updatePhoneOnlineYnByOrderId(Long orderId);
    int updateOrderByOrderId(Long orderId);
    /**
     * 变更t_order和t_photo_online直播团状态
     * @param orderId
     * @return
     */
    int updateStatusByOrderId(Long orderId);
    PhotoOnline selectById(Long id);


    int updateAccessNumByOrderId(Long orderId);
}