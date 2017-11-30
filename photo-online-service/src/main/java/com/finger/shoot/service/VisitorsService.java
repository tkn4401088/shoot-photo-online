/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */

package com.finger.shoot.service;

import  com.finger.shoot.entity.Visitors;
import java.util.List;


/**
 * 游客表接口
 * 
 * @author pmd
 * @version 1.0
 * @since 1.0
 */
public interface VisitorsService {

	/**
	 * 新增
	 * @param visitors
	 * @return
	 */
	int insert(Visitors visitors);

	/**
	 * 批量新增
	 * @param visitorss
	 * @return
	 */
	int batchInsert(List<Visitors> visitorss);

	/**
	 * 根据ID来删除数据
	 * @param id
	 * @return
	 */
	int deleteById(Long id);

	/**
	 * 根据ID来更新数据
	 * @param visitors
	 * @return
	 */
	int updateById(Visitors visitors);

	/**
	 * 根据ID来查询数据
	 * @param id
	 * @return
	 */
	Visitors selectById(Long id);

	/**
	 * 根据条件来查询List
	 * @param visitors
	 * @return
	 */
	List<Visitors> selectVisitorss(Visitors visitors);

	/**
	 * 分页查询
	 * @param visitors
	 * @return
	 */
	List<Visitors> selectPageVisitorss(Visitors visitors);
}

