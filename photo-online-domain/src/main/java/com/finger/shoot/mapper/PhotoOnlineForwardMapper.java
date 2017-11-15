/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.mapper;


import com.finger.shoot.entity.PhotoOnlineForward;

import java.util.List;

/**
 * 照片直播转发记录表Mapper类
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface PhotoOnlineForwardMapper {

    int insert(PhotoOnlineForward photoOnlineForward);

    int batchInsert(List<PhotoOnlineForward> photoOnlineForwards);

    int deleteById(Long id);

    int updateById(PhotoOnlineForward photoOnlineForward);

    PhotoOnlineForward selectById(Long id);

    List<PhotoOnlineForward> selectPhotoOnlineForwards(PhotoOnlineForward photoOnlineForward);

    List<PhotoOnlineForward> selectPagePhotoOnlineForwards(PhotoOnlineForward photoOnlineForward);
}