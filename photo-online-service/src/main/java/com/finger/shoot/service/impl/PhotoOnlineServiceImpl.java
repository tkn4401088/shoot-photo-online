/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service.impl;

import com.finger.shoot.common.Constants;
import com.finger.shoot.service.PhotoOnlineService;
import com.finger.shoot.mapper.PhotoOnlineMapper;
import com.finger.shoot.entity.PhotoOnline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 直播团设置service实现
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class PhotoOnlineServiceImpl implements PhotoOnlineService {

    @Autowired
    private PhotoOnlineMapper photoOnlineMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public String savePhotoOnline(PhotoOnline photoOnline) {
        PhotoOnline result = photoOnlineMapper.selectByOrderId(photoOnline.getOrderId());
        if (result != null && result.getId() > 0) {
            photoOnlineMapper.deleteById(result.getId());
        }
        int rst = photoOnlineMapper.insert(photoOnline);
        if (rst > 0) {
            photoOnlineMapper.updateOrderByOrderId(result.getOrderId());
            return Constants.SUCCESS;
        }
        return Constants.ERR_CODE_500;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public String changeStatusByOrderId(PhotoOnline photoOnline) {
        int result = photoOnlineMapper.updateStatusByOrderId(photoOnline.getOrderId());
        if (result > 0) {

            return Constants.SUCCESS;
        }
        return Constants.ERR_CODE_500;
    }

    @Override
    public PhotoOnline selectByOrderId(Long orderId) {
        return photoOnlineMapper.selectByOrderId(orderId);
    }

    @Override
    public PhotoOnline selectById(Long id) {
        return photoOnlineMapper.selectById(id);
    }

    @Override
    public List<PhotoOnline> selectPagePhotoOnline(PhotoOnline photoOnline) {
        return photoOnlineMapper.selectPagePhotoOnline(photoOnline);
    }

    @Override
    public int updatePhoneOnlineYnByOrderId(Long orderId) {
        return photoOnlineMapper.updatePhoneOnlineYnByOrderId(orderId);
    }

    @Override
    public int updateOrderByOrderId(Long orderId) {
        return photoOnlineMapper.updateOrderByOrderId(orderId);
    }

    @Override
    public int updateAccessNumByOrderId(Long orderId) {
        return photoOnlineMapper.updateAccessNumByOrderId(orderId);
    }
}
