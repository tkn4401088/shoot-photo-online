/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.mapper;


import com.finger.shoot.entity.Visitors;

import java.util.List;

/**
 * 游客表Mapper类
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface VisitorsMapper {

    int insert(Visitors visitors);

    int batchInsert(List<Visitors> visitorss);

    int deleteById(Long id);

    int updateById(Visitors visitors);

    Visitors selectById(Long id);

    List<Visitors> selectVisitorss(Visitors visitors);

    List<Visitors> selectPageVisitorss(Visitors visitors);
}