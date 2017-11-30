package com.finger.shoot.service.impl;

import com.finger.shoot.entity.StaticData;
import com.finger.shoot.mapper.StaticDataMapper;
import com.finger.shoot.service.StaticDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengmd on 2017/11/18.
 */
@Service
public class StaticDataServiceImpl implements StaticDataService {

    @Autowired
    StaticDataMapper staticDataMapper;

    @Override
    public List<StaticData> selectStaticDataByType(String type) {
        return staticDataMapper.selectStaticDataByType(type);
    }
}
