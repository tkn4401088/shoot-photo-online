/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.mapper;


import com.finger.shoot.entity.OrderPhotoOnlineConf;

import java.util.List;

/**
 * 直播团设置Mapper类
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface OrderPhotoOnlineConfMapper {

    int insert(OrderPhotoOnlineConf orderPhotoOnlineConf);

    int batchInsert(List<OrderPhotoOnlineConf> orderPhotoOnlineConfs);

    int deleteById(Long id);

    int updateById(OrderPhotoOnlineConf orderPhotoOnlineConf);

    OrderPhotoOnlineConf selectById(Long id);

    List<OrderPhotoOnlineConf> selectOrderPhotoOnlineConfs(OrderPhotoOnlineConf orderPhotoOnlineConf);

    List<OrderPhotoOnlineConf> selectPageOrderPhotoOnlineConfs(OrderPhotoOnlineConf orderPhotoOnlineConf);
}