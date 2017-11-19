package com.finger.shoot.common;

import java.util.Map;

/**
 * Created by pengmd on 2016/7/28.
 */
public class Constants {
    public final static String SUCCESS = "0";  //成功
    public final static String ERR_CODE_404= "404";
    public final static String ERR_CODE_500= "500";
    public final static String ERR_CODE_1001= "1001";
    public final static String ERR_CODE_1002= "1002";

    public static Map<String,String> ERR_MSG_MAP;

    static{
        ERR_MSG_MAP.put(ERR_CODE_1001,"该订单没有直播配置！");
        ERR_MSG_MAP.put(ERR_CODE_1002,"保存失败！");
        ERR_MSG_MAP.put(ERR_CODE_404,"无效请求！");
        ERR_MSG_MAP.put(ERR_CODE_500,"内部错误！");
        ERR_MSG_MAP.put(SUCCESS,"成功");
    }


    public final static String PHOTO_ONLINE_STATIC_DATA_TYPE = "PHOTO_ONLINE_TYPE";
}
