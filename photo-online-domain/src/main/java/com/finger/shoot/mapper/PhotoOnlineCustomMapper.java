/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.mapper;


import com.finger.shoot.entity.PhotoOnlineCustom;

import java.util.List;

/**
 * 照片直播客户制作物表Mapper类
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface PhotoOnlineCustomMapper {

    int insert(PhotoOnlineCustom photoOnlineCustom);

    int batchInsert(List<PhotoOnlineCustom> photoOnlineCustoms);

    int deleteById(Long id);

    int updateById(PhotoOnlineCustom photoOnlineCustom);

    PhotoOnlineCustom selectById(Long id);

    List<PhotoOnlineCustom> selectPhotoOnlineCustoms(PhotoOnlineCustom photoOnlineCustom);

    List<PhotoOnlineCustom> selectPagePhotoOnlineCustoms(PhotoOnlineCustom photoOnlineCustom);
}