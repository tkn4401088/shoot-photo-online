/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service.impl;

import com.finger.shoot.service.PhotoOnlineCustomService;
import com.finger.shoot.mapper.PhotoOnlineCustomMapper;
import com.finger.shoot.entity.PhotoOnlineCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 照片直播客户制作物表service实现
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class PhotoOnlineCustomServiceImpl implements PhotoOnlineCustomService {

    @Autowired
    private PhotoOnlineCustomMapper photoOnlineCustomMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int insert(PhotoOnlineCustom photoOnlineCustom){
        return photoOnlineCustomMapper.insert(photoOnlineCustom);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int batchInsert(List<PhotoOnlineCustom> photoOnlineCustoms){
        return photoOnlineCustomMapper.batchInsert(photoOnlineCustoms);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int deleteById(Long id){
        return photoOnlineCustomMapper.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int updateById(PhotoOnlineCustom photoOnlineCustom){
        return photoOnlineCustomMapper.updateById(photoOnlineCustom);
    }

    public List<PhotoOnlineCustom> selectPhotoOnlineCustoms(PhotoOnlineCustom photoOnlineCustom){
        return photoOnlineCustomMapper.selectPhotoOnlineCustoms(photoOnlineCustom);
    }

    public PhotoOnlineCustom selectById(Long id){
        return photoOnlineCustomMapper.selectById(id);
    }

    public List<PhotoOnlineCustom> selectPagePhotoOnlineCustoms(PhotoOnlineCustom photoOnlineCustom){
        return photoOnlineCustomMapper.selectPagePhotoOnlineCustoms(photoOnlineCustom);
    }
}
