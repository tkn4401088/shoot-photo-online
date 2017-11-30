package com.finger.shoot.service;

import com.finger.shoot.entity.StaticData;

import java.util.List;

/**
 * Created by pengmd on 2017/11/18.
 */
public interface StaticDataService {
    /**
     * 根据dataName查找静态数据
     * @param name
     * @return
     */
    List<StaticData> selectStaticDataByType(String type);
}
