/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service.impl;

import com.finger.shoot.service.GroupPhotoService;
import com.finger.shoot.mapper.GroupPhotoMapper;
import com.finger.shoot.entity.GroupPhoto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 团照片信息表service实现
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class GroupPhotoServiceImpl implements GroupPhotoService {

    @Autowired
    private GroupPhotoMapper groupPhotoMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int insert(GroupPhoto groupPhoto){
        return groupPhotoMapper.insert(groupPhoto);
    }

    public List<GroupPhoto> selectGroupPhotos(GroupPhoto groupPhoto){
        return groupPhotoMapper.selectGroupPhotos(groupPhoto);
    }

    public GroupPhoto selectById(Long id){
        return groupPhotoMapper.selectById(id);
    }

    public List<GroupPhoto> selectPageGroupPhotos(GroupPhoto groupPhoto){
        return groupPhotoMapper.selectPageGroupPhotos(groupPhoto);
    }
}
