/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service.impl;

import com.finger.shoot.service.OrderPhotoOnlineConfService;
import com.finger.shoot.mapper.OrderPhotoOnlineConfMapper;
import com.finger.shoot.entity.OrderPhotoOnlineConf;
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
public class OrderPhotoOnlineConfServiceImpl implements OrderPhotoOnlineConfService {

    @Autowired
    private OrderPhotoOnlineConfMapper orderPhotoOnlineConfMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int insert(OrderPhotoOnlineConf orderPhotoOnlineConf){
        return orderPhotoOnlineConfMapper.insert(orderPhotoOnlineConf);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int batchInsert(List<OrderPhotoOnlineConf> orderPhotoOnlineConfs){
        return orderPhotoOnlineConfMapper.batchInsert(orderPhotoOnlineConfs);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int deleteById(Long id){
        return orderPhotoOnlineConfMapper.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int updateById(OrderPhotoOnlineConf orderPhotoOnlineConf){
        return orderPhotoOnlineConfMapper.updateById(orderPhotoOnlineConf);
    }

    public List<OrderPhotoOnlineConf> selectOrderPhotoOnlineConfs(OrderPhotoOnlineConf orderPhotoOnlineConf){
        return orderPhotoOnlineConfMapper.selectOrderPhotoOnlineConfs(orderPhotoOnlineConf);
    }

    public OrderPhotoOnlineConf selectById(Long id){
        return orderPhotoOnlineConfMapper.selectById(id);
    }

    public List<OrderPhotoOnlineConf> selectPageOrderPhotoOnlineConfs(OrderPhotoOnlineConf orderPhotoOnlineConf){
        return orderPhotoOnlineConfMapper.selectPageOrderPhotoOnlineConfs(orderPhotoOnlineConf);
    }
}
