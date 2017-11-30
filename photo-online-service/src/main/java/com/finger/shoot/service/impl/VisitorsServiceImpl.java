/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service.impl;

import com.finger.shoot.service.VisitorsService;
import com.finger.shoot.mapper.VisitorsMapper;
import com.finger.shoot.entity.Visitors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 游客表service实现
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class VisitorsServiceImpl implements VisitorsService {

    @Autowired
    private VisitorsMapper visitorsMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int insert(Visitors visitors){
        return visitorsMapper.insert(visitors);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int batchInsert(List<Visitors> visitorss){
        return visitorsMapper.batchInsert(visitorss);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int deleteById(Long id){
        return visitorsMapper.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int updateById(Visitors visitors){
        return visitorsMapper.updateById(visitors);
    }

    public List<Visitors> selectVisitorss(Visitors visitors){
        return visitorsMapper.selectVisitorss(visitors);
    }

    public Visitors selectById(Long id){
        return visitorsMapper.selectById(id);
    }

    public List<Visitors> selectPageVisitorss(Visitors visitors){
        return visitorsMapper.selectPageVisitorss(visitors);
    }
}
