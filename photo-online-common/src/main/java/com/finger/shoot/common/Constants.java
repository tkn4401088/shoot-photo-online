package com.finger.shoot.common;

/**
 * Created by pengmd on 2016/7/28.
 */
public class Constants {
    public final static String SUCCESS = "0";  //成功
    public final static  String nonExistentCode ="404";
    public final static String nonExistentMsg = "";
    public final static String ERR_CODE_REPEAT_REQUEST="504";
    public final static String ERR_MSG_REPEAT_REQUEST="订单已存在,不允许重复请求！";
    public final static String ERR_CODE_NOT_ENOUGH="505";
    public final static String ERR_MSG_NOT_ENOUGH="余额不足.请求失败！";
    public final static String ERR_CODE_NOT_ORDER="506";
    public final static String ERR_MSG_NOT_ORDER="订单不存在！";

    public final static String CREDIT_LIMIT_BALANCE ="credit_limit_balance";
    public final static Long BALANCE_LOCK=10L;
    public final static int BALANCE_EDIT_ADD_TYPE=1;
    public final static int BALANCE_EDIT_SUBTRACT_TYPE=2;
}
