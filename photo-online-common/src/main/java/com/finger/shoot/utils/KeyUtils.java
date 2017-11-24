package com.finger.shoot.utils;

import java.util.Random;
import java.util.UUID;

public  class KeyUtils {

	/***
	 * 随机产生32位16进制字符串
	 * @return
	 */
	public static String getRandom32PK(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	} 
	
	/***
	 * 随机产生32位16进制字符串，以时间开头
	 * @return
	 */
	public static String getRandom32BeginTimePK(){
		String timeStr = DateTime.currentDateTime("yyyyMMddHHmmssSSS");
		String random32 = getRandom32PK();
		return timeStr+random32.substring(17,random32.length());
	}
	
	/***
	 * 随机产生32位16进制字符串，以时间结尾
	 * @return
	 */
	public static String getRandom32EndTimePK(){
		String timeStr = DateTime.currentDateTime("yyyyMMddHHmmssSSS");
		String random32 = getRandom32PK();
		return random32.substring(0,random32.length()-17)+timeStr;
	}
	
	/**




	 * @return
	 */
	public static String getRandomValiteCode(int size){
		if(size <= 0) size = 6;//默认6位 
		String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生的字符串
		Random random = new Random();//随机种子
		String rst = "";//返回值
		for (int i = 0; i < size; i++) {
			rst += randString.charAt(random.nextInt(36));
		}
		return rst;
	}
	
	/**
	 * 获取随机的验证码
	 * @return
	 */
	public static String getRandom6ValiteCode(int size){
		if(size <= 0) size = 6;//默认6位 
		String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";//随机产生的字符串
		Random random = new Random();//随机种子
		String rst = "";//返回值
		for (int i = 0; i < size; i++) {
			rst += randString.charAt(random.nextInt(36));
		}
		return rst;
	}
	
	/**
	 * 获取随机数
	 * @return
	 */
	public static String getRandom6Number(int size){
		if(size <= 0) size = 6;//默认6位 
		String randString = "0123456789";//随机产生的字符串
		Random random = new Random();//随机种子
		String rst = "";//返回值
		for (int i = 0; i < size; i++) {
			rst += randString.charAt(random.nextInt(6));
		}
		return rst;
	}
	
	/**
	 * 获取8位随机字符串 
	 * @return
	 */
	public static String get8RandomValiteCode(int size){
		if(size <= 0) size = 8;//默认8位 
		String randString = "0123456789";//随机产生的字符串
		Random random = new Random();//随机种子
		String rst = "";//返回值
		for (int i = 0; i < size; i++) {
			rst += randString.charAt(random.nextInt(10));
		}
		return rst;
	}


	/** 自定义进制(0,1没有加入,容易与o,l混淆) */
	private static final char[] r = new char[] { 'Q', 'w', 'E', '8', 'a', 'S', '2', 'd', 'Z', 'x', '9', 'c', '7', 'p', 'O', '5', 'i', 'K', '3', 'm', 'j', 'U', 'f', 'r', '4', 'V', 'y', 'L', 't', 'N', '6', 'b', 'g', 'H' };
	/** 自动补全组(不能与自定义进制有重复) */
	private static final char[] b = new char[] { 'q', 'W', 'e', 'A', 's', 'D', 'z', 'X', 'C', 'P', 'o', 'I', 'k', 'M', 'J', 'u', 'F', 'R', 'v', 'Y', 'T', 'n', 'B', 'G', 'h' };
	/** 进制长度 */
	private static final int l = r.length;

	/**
	 *
	 * @param size
	 * @return
     */
	public static String toSerialNumber(int size) {
		char[] buf = new char[32];
		int charPos = 32;
		long num = 6;
		while ((num / l) > 0) {
			buf[--charPos] = r[(int) (num % l)];
			num /= l;
		}
		buf[--charPos] = r[(int) (num % l)];
		String str = new String(buf, charPos, (32 - charPos));
		// 不够长度的自动随机补全
		if (str.length() < size) {
			StringBuffer sb = new StringBuffer();
			Random rnd = new Random();
			for (int i = 0; i < size - str.length(); i++) {
				sb.append(b[rnd.nextInt(24)]);
			}
			str += sb.toString();
		}
		return str;
	}
}

