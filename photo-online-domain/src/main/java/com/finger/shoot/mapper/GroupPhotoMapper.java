/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.mapper;


import com.finger.shoot.entity.GroupPhoto;

import java.util.List;

/**
 * 团照片信息表Mapper类
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface GroupPhotoMapper {

    int insert(GroupPhoto groupPhoto);

    GroupPhoto selectById(Long id);

    List<GroupPhoto> selectGroupPhotos(GroupPhoto groupPhoto);

    List<GroupPhoto> selectPageGroupPhotos(GroupPhoto groupPhoto);
}