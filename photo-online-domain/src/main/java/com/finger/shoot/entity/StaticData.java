package com.finger.shoot.entity;

import com.finger.portal.base.model.PageModel;
import lombok.Data;

/**
 * 静态数据类
 * Created by pengmd on 2017/11/18.
 */
@Data
public class StaticData extends PageModel {

    private String dataType;
    private String dataValue;
    private String dataName;
}
