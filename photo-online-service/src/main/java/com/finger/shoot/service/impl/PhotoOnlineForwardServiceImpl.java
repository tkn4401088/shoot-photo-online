/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service.impl;

import com.finger.shoot.service.PhotoOnlineForwardService;
import com.finger.shoot.mapper.PhotoOnlineForwardMapper;
import com.finger.shoot.entity.PhotoOnlineForward;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 照片直播转发记录表service实现
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class PhotoOnlineForwardServiceImpl implements PhotoOnlineForwardService {

    @Autowired
    private PhotoOnlineForwardMapper photoOnlineForwardMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int insert(PhotoOnlineForward photoOnlineForward){
        return photoOnlineForwardMapper.insert(photoOnlineForward);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int batchInsert(List<PhotoOnlineForward> photoOnlineForwards){
        return photoOnlineForwardMapper.batchInsert(photoOnlineForwards);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int deleteById(Long id){
        return photoOnlineForwardMapper.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int updateById(PhotoOnlineForward photoOnlineForward){
        return photoOnlineForwardMapper.updateById(photoOnlineForward);
    }

    public List<PhotoOnlineForward> selectPhotoOnlineForwards(PhotoOnlineForward photoOnlineForward){
        return photoOnlineForwardMapper.selectPhotoOnlineForwards(photoOnlineForward);
    }

    public PhotoOnlineForward selectById(Long id){
        return photoOnlineForwardMapper.selectById(id);
    }

    public List<PhotoOnlineForward> selectPagePhotoOnlineForwards(PhotoOnlineForward photoOnlineForward){
        return photoOnlineForwardMapper.selectPagePhotoOnlineForwards(photoOnlineForward);
    }
}
