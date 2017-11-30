package com.finger.shoot.mapper;

import com.finger.shoot.entity.StaticData;

import java.util.List;

/**
 * Created by pengmd on 2017/11/18.
 */
public interface StaticDataMapper {
    List<StaticData> selectStaticDataByType(String type);
}
