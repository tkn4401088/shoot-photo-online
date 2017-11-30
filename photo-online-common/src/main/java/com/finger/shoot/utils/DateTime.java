package com.finger.shoot.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 公共日期时间操作工具类 */
public class DateTime {
	/** DateTimeFormat1 = "yyyy-MM-dd HH:mm:ss" */
	public static final String DateTimeFormat1 = "yyyy-MM-dd HH:mm:ss";

	/** DateTimeFormat2 = "yyyy-MM-dd HH:mm:ss:SSS" */
	public static final String DateTimeFormat2 = "yyyy-MM-dd HH:mm:ss:SSS";
	
	/** DateTimeForma3 = "yyyy-MM-dd HH:mm" */
	public static final String DateTimeFormat3 = "yyyy-MM-dd HH:mm";

	/** DateFormat1 = "yyyy-MM-dd" */
	public static final String DateFormat1 = "yyyy-MM-dd";

	/** DateFormat2 = "yyyy/MM/dd" */
	public static final String DateFormat2 = "yyyy/MM/dd";

	/** DateFormat3 = "yyyy.MM.dd" */
	public static final String DateFormat3 = "yyyy.MM.dd";

	/** DateFormat4 = "yyyyMM" */
	public static final String DateFormat4 = "yyyy-MM";

	/** DateFormat4 = "yyyyMM" */
	public static final String DateFormat5 = "yyyyMMdd";

	/** TimeFormat1 = "HH:mm:ss" */
	public static final String TimeFormat1 = "HH:mm:ss";

	/** TimeFormat2 = "HH:mm:ss:SSS" */
	public static final String TimeFormat2 = "HH:mm:ss:SSS";

	/** _HH : 一小时的毫秒数 60 * 60 * 1000 */
	public static final int _HH = 60 * 60 * 1000;

	/** _MM : 一分钟的毫秒数 60 * 1000 */
	public static final int _MM = 60 * 1000;

	/** _SS : 一秒的毫秒数 1000 */
	public static final int _SS = 1000;

	/**时间开始日期 */
	public static final String START="2014-01";
	/**中午十二点*/
	public static final String NOONTIME="12:00:00";

	/**
	 * @return 返回文本信息的日期对应的格林威治标准时间（1970年1月1日00:00:00.000）的偏移量,单位是毫秒。 1秒=1000毫秒。
	 * @param format
	 *            "yyyy-MM-dd HH:mm:ss:SSS"或"yyyy-MM-dd HH:mm:ss"。
	 */
	public static long dateTimeParse(String datetime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(datetime);
			return date.getTime();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return 0;
	}

	public static Date dateTimeParse2(String datetime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(datetime);
			return date;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	/**
	 *
	 */
	public static String currentDateDistance(String endDate) {
		//系数,默认为天数
		Long quotient = 86400000l;
		Long hours = 3600000l;
		Long mins = 60000l;
		Long cha =  Calendar.getInstance().getTimeInMillis()- DateTime.dateTimeParse(endDate, "yyyy-MM-dd hh:mm");
		if(cha>=quotient){
			return Math.abs(cha) / quotient+"天前";
		}else if(cha>=hours){
			return Math.abs(cha) / hours+"小时前";
		}else if(cha>=mins){
			return Math.abs(cha) / mins+"分钟前";
		}else{
			return "刚刚";
		}
	};

	/**
	 * @return 返回测试时间是否在beginTime和endTime之间，如果在返回true，不在返回false。
	 * @param beginTime
	 *            开始时间。格式为："yyyy-MM-dd HH:mm:ss:SSS"或者"yyyy-MM-dd HH:mm:ss"。
	 * @param endTime
	 *            结束时间。格式同上。
	 * @param testTime
	 *            被测试的时间。格式同上。
	 * @param timeFormat
	 *            共同的格式为："yyyy-MM-dd HH:mm:ss:SSS"或"yyyy-MM-dd
	 *            HH:mm:ss"，请使用本类中提供的类型 。
	 */
	public static boolean isInTwoTime(String beginTime, String endTime,
									  String testTime, String timeFormat) {
		long begin = DateTime.dateTimeParse(beginTime, timeFormat);
		long end = DateTime.dateTimeParse(endTime, timeFormat);
		long test = DateTime.dateTimeParse(testTime, timeFormat);
		if (test > begin && test < end) {
			return true;
		}
		return false;
	}


	/**
	 * @return 返回测试时间是否在beginTime和endTime之间或者==，如果在返回true，不在返回false。
	 * @param beginTime
	 *            开始时间。格式为："yyyy-MM-dd HH:mm:ss:SSS"或者"yyyy-MM-dd HH:mm:ss"。
	 * @param endTime
	 *            结束时间。格式同上。
	 * @param testTime
	 *            被测试的时间。格式同上。
	 * @param timeFormat
	 *            共同的格式为："yyyy-MM-dd HH:mm:ss:SSS"或"yyyy-MM-dd
	 *            HH:mm:ss"，请使用本类中提供的类型 。
	 */
	public static boolean isBetweenTwoTime(String beginTime, String endTime,
										   String testTime, String timeFormat) {
		long begin = DateTime.dateTimeParse(beginTime, timeFormat);
		long end = DateTime.dateTimeParse(endTime, timeFormat);
		long test = DateTime.dateTimeParse(testTime, timeFormat);
		if (test >= begin && test <= end) {
			return true;
		}
		return false;
	}
	/**
	 * 比较两个时间，如果endtime晚于begintime，则返回true，否则返回false
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean compareTime(String beginTime, String endTime) {
		long begin = DateTime.dateTimeParse(beginTime, DateTimeFormat1);
		long end = DateTime.dateTimeParse(endTime, DateTimeFormat1);
		if (end > begin) {
			return true;
		}
		return false;
	}

	/**
	 * 比较两个时间，如果endtime晚于begintime，则返回true，否则返回false
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean compareTime(String beginTime, String endTime,String dateformat) {
		long begin = DateTime.dateTimeParse(beginTime, dateformat);
		long end = DateTime.dateTimeParse(endTime, dateformat);
		if (end > begin) {
			return true;
		}
		return false;
	}

	/**
	 * 比较两个时间，如果endtime晚于指定时间，则返回true，否则返回false
	 * @param endTime
	 * @return
	 */
	public static boolean compareTime(String endTime) {
		long begin = DateTime.dateTimeParse(NOONTIME, TimeFormat1);
		long end = DateTime.dateTimeParse(endTime, TimeFormat1);
		if (end > begin) {
			return true;
		}
		return false;
	}

	/**
	 * 比较两个日期，如果当前日期比beginTime早三天，则返回true，否则返回false
	 * @param beginTime 比较的时间
	 * @param str 相差的毫秒数
	 * @param dateFormat 日期时间格式
	 * @return
	 */
	public static boolean comDate(String beginTime,String str,String dateFormat) {
		Calendar cal = Calendar.getInstance();
		long amount = Long.parseLong(str);
		long begin = DateTime.dateTimeParse(beginTime, dateFormat);
		String endTime = new SimpleDateFormat(dateFormat).format(cal.getTime());
		long end = DateTime.dateTimeParse(endTime, dateFormat);
		boolean flag = end-begin >=amount?true:false;
		if(flag){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * [daysBetween 获得两个日期字符串之间的天数差]
	 * @param  {[String]} startDate [传入的开始日期]
	 * @param  {[String]} endDate [传入的结束日期]
	 * @param  {[boolean]} requiredAbs [是否需要取绝对值,false 用于判断时间先后]
	 * @param  {[float]} ratio [时间差系数,默认是8640000,表示一天]
	 * @author liulin 2013/01/29
	 * @return {[Integer]}     [差值]
	 */
	public static Long daysBetween(String startDate, String endDate, boolean requiredAbs, Long ratio) {
		//系数,默认为天数
		Long quotient = 86400000l;
		if(null != ratio && Float.parseFloat(String.valueOf(ratio)) > 0){
			quotient = ratio;
		}
		Long cha = DateTime.dateTimeParse(startDate, "yyyy-MM-dd hh:mm") - DateTime.dateTimeParse(endDate, "yyyy-MM-dd hh:mm");
		if(requiredAbs){
			return Math.abs(cha) / quotient;
		}else{
			return cha / quotient;
		}
	};

	/**
	 * @param dateFormat
	 *            请使用本类提供的类型。格式："yyyy-MM-dd"或"yyyy/MM/dd"或"yyyy.MM.dd" 。
	 * @return 返回指定格式的当前日期
	 */
	public static String currentDate(String dateFormat) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(d);
	}

	/**
	 * 短日期
	 * @return
	 */
	public static String currShortDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String dateStr = sdf.format(cal.getTime());
		return dateStr;
	}

	/**
	 * 获取系统现在的时间
	 * @return 时间
	 */
	public static Date currentDate(){
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * @param timeFormat
	 *            请使用本类提供的类型。格式："HH:mm:ss"或"HH:mm:ss:SSS" 。
	 * @return 返回指定格式的当前时间
	 */
	public static String currentTime(String timeFormat) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		return sdf.format(d);
	}

	/**
	 * 昨天的时间
	 * @return
	 */
	public static String preDayTime(String timeFormat){
		//Date转换到calendar
		Calendar cal = Calendar.getInstance();
		//在calendar基础上新增一小时
		cal.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		return sdf.format(cal.getTime());
	}
	/**
	 * 指定天数的时间
	 * @return
	 */
	public static String preDayTime(String timeFormat, int day){
		//Date转换到calendar
		Calendar cal = Calendar.getInstance();
		//在calendar基础上新增一小时
		cal.add(Calendar.DAY_OF_MONTH, day);
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		return sdf.format(cal.getTime());
	}

	/**
	 * @param format
	 *            请使用本类提供的类型。格式："yyyy-MM-dd HH:mm:ss:SSS"或"yyyy-MM-dd HH:mm:ss"。
	 * @return 返回指定格式的当前日期时间
	 */
	public static String currentDateTime(String format) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}


	/** 获得当前时间,格式为："yyyy-MM-dd HH:mm:ss.SSS" */
	public static String currentDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar
				.getInstance().getTime());
	}

	/**
	 * @return 返回当前系统时间的格林威治毫秒时。
	 * @param format
	 *            指定返回的值精确到秒还是毫秒。请使用本类提供的类型。格式："yyyy-MM-dd HH:mm:ss:SSS"精确到毫秒 或
	 *            "yyyy-MM-dd HH:mm:ss"精确到秒。
	 */
	public static long currentDateTimeGreenwich(String format) {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sysDatetime = sdf.format(rightNow.getTime());
		return DateTime.dateTimeParse(sysDatetime, format);
	}

	/**
	 * 返回以毫秒为单位的当前时间。注意，当返回值的时间单位是毫秒时，值的粒度取决于底层操作系统，并且粒度可能更大。
	 * 例如，许多操作系统以几十毫秒为单位测量时间。 请参阅 Date 类的描述，了解可能发生在“计算机时间”和协调世界时
	 * （UTC）之间的细微差异的讨论。 返回： 当前时间与协调世界时 1970 年 1 月 1 日午夜之间的时间差（以毫秒为单 位测量）。
	 *
	 * @see Date
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 返回最准确的可用系统计时器的当前值，以毫微秒为单位。此方法只能用于测量已过的时间，与系统或钟表时间的其他任何时间概念无关。
	 * 返回值表示从某一固定但任意的时间算起的毫微秒数（或许从以后算起，所以该值可能为负）。此方法提供毫微秒的精度，但不是必要的毫
	 * 微秒的准确度。它对于值的更改频率没有作出保证。在取值范围大于约 292 年（263 毫微秒）的连续调用的不同点在于：由于数字溢出，
	 * 将无法准确计算已过的时间。
	 */
	// 例如，测试某些代码执行的时间长度：
	// long startTime = System.nanoTime();
	// //... the code being measured ...
	// long estimatedTime = System.nanoTime() - startTime;
	//
	public static long nanoTime() {
		return System.nanoTime();
	}

	/**
	 * @return 把毫秒时间转换成小时分钟秒格式 HH:mm:ss
	 */
	public static String toHHmmss(final long millisecond) {
		long h = millisecond / _HH;
		// int m = (int) ((millisecond - h * _HH) / _MM);
		// int s = (int) ((millisecond - h * _HH - m * _MM) / _SS);
		SimpleDateFormat sdf = new SimpleDateFormat(":mm:ss");
		return h + sdf.format(millisecond);
	}

	//得到指定天数的日期
	public static String getBeforeDate(String dateformat,int day,String flag){
		Calendar cal = Calendar.getInstance();
		if("after".equals(flag)){
			cal.set(Calendar.DATE, cal.get(Calendar.DATE)+day);
		}else if("before".equals(flag)){
			cal.set(Calendar.DATE, cal.get(Calendar.DATE)-day);
		}
		DateFormat df = new SimpleDateFormat(dateformat);
		String str = df.format(cal.getTime());
		return str;
	}

	/**
	 * @return 把毫秒时间转换成小时分钟秒毫秒格式 HH:mm:ss:SSS
	 */
	public static String toHHmmssSSS(final long millisecond) {
		long h = millisecond / _HH;
		// int m = (int) ((millisecond - h * _HH) / _MM);
		// int s = (int) ((millisecond - h * _HH - m * _MM) / _SS);
		// int ms = (int) (millisecond - h * _HH - m * _MM - s * _SS);
		SimpleDateFormat sdf = new SimpleDateFormat(":mm:ss:SSS");
		return h + sdf.format(millisecond);
	}

	/**
	 * @return 把毫秒时间转换成日期类型date
	 */
	public static Date long2Date(final long millisecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millisecond);
		return calendar.getTime();
	}

	/**
	 * @return 把毫秒时间转换成日期类型date
	 */
	public static String long2DateTime(final long millisecond) {
		long h = millisecond / _HH;
		// int m = (int) ((millisecond - h * _HH) / _MM);
		// int s = (int) ((millisecond - h * _HH - m * _MM) / _SS);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		return h + sdf.format(millisecond);
	}

	/**
	 * ghc 2015-11-22
	 * @return 把毫秒时间转换成日期类型 yyyy-MM-dd
	 */
	public static String longToDate(long millisecond) {
		//long h = millisecond / _HH;
		// int m = (int) ((millisecond - h * _HH) / _MM);
		// int s = (int) ((millisecond - h * _HH - m * _MM) / _SS);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(millisecond);
	}

	/**
	 * 在系统时间基础上增加时间函数
	 * @return
	 */
	public static Date addTime(Date dateTime, int h) throws Exception{
		if(h >= 0) h = 1;//默认为 1 小时
		//Date转换到calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTime);
		//在calendar基础上新增一小时
		cal.add(Calendar.HOUR, h);
		return cal.getTime();
	}

	/**
	 * 在系统时间基础上增加时间函数
	 * @return
	 */
	public static Date addTimeByDays(Date dateTime, int days) throws Exception{
		//Date转换到calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTime);
		//在calendar基础上新增一小时
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	/**
	 * date 类型数据 转换成 特定格式
	 * @param dateTime
	 * @param format
	 * @return
	 */
	public static String date2Str(Date dateTime, String format){
		if(format == null || "".equals(format)) format = DateFormat1;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dateTime);
	}

	/**
	 * date 类型数据 转换成 特定格式
	 * @param format
	 * @return
	 */
	public static String time2Str(Timestamp Time, String format){
		if(format == null || "".equals(format)) format = DateFormat3;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(Time);
	}


	/**
	 * 字符串类型 转换成 date
	 * @return
	 */
	public static Date str2Date(String date){
		Date d = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateFormat1);
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
		return d;
	}

	/**
	 * 字符串类型 转换成 date
	 * @return
	 */
	public static Date str2DateTime(String date){
		Date d = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFormat1);
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
		return d;
	}



	/**
	 * 获取当前周和当前月的开始日期和结束日期
	 * @param dateFormat
	 * @return
	 */
	public static String getWeekDay(String type,String dateFormat){
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String start_date = "";
		String end_date = "";
		//获取当前月第一天：
		Calendar cal = Calendar.getInstance();
		if("month".equals(type)){
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
			start_date = format.format(cal.getTime());
			//获取当前月最后一天
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			end_date = format.format(ca.getTime());
		}else if("week".equals(type)){
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //获取本周一的日期
			start_date= format.format(cal.getTime());
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			cal.add(Calendar.WEEK_OF_YEAR, 1);
			end_date = format.format(cal.getTime());
		}else if("year".equals(type)){
			int year = cal.get(Calendar.YEAR);
			start_date = year+"01-01";
			end_date = year+"12-31";
		}
		return start_date+";"+end_date;
	}

	/**
	 * 获取当前月天数
	 * @return
	 */
	public static int getCurrMonthDays(){
		Calendar cal = Calendar.getInstance();
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}

	/**
	 * 判断当前日期属于星期几
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getWeekOfDate(String date) throws ParseException {
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sim = new SimpleDateFormat(DateFormat1);
		Date dt = sim.parse(date);
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 获取当前时间字符串
	 * @return
	 */
	public static String getCurrDateByType(String type)  {
		Calendar cal = Calendar.getInstance();
		int d = 0;
		if("day".equals(type)){
			d = cal.get(Calendar.DATE);
		}
		if("month".equals(type)){
			d = cal.get(Calendar.MONTH) + 1;
		}
		if("year".equals(type)){
			d = cal.get(Calendar.YEAR);
		}
		if("week".equals(type)){
			d = cal.get(Calendar.WEEK_OF_YEAR);
		}
		if("hour".equals(type)){
			d = cal.get(Calendar.HOUR_OF_DAY);
		}
		if("minute".equals(type)){
			d = cal.get(Calendar.MINUTE);
		}
		return String.valueOf(d);
	}




	/**
	 * 季度的开始时间和结束时间
	 * @return
	 */
	public static String qua2date(String quarter){
		String year = quarter.split("-")[0];
		String qua = quarter.split("-")[1];
		String str="";
		if("Q1".equals(qua)){
			str = year+"-01"+","+year+"-03";
		}else if("Q2".equals(qua)){
			str = year+"-04"+","+year+"-06";
		}else if("Q3".equals(qua)){
			str = year+"-07"+","+year+"-09";
		}else if("Q4".equals(qua)){
			str = year+"-10"+","+year+"-12";
		}
		return str;
	}

	/**
	 * @author Shaq
	 *
	 * @param timeInterval
	 * 输入为间隔的分钟数(不考虑跨小时的情况)。比如输入15，表明每隔15分钟集中发送一次
	 * @return
	 * -1 程序出错
	 * 返回是当前时间距离间隔时间的毫秒数
	 */
	public static long getTimeIntervalbyMin (long cTime, int timeInterval) {

		if ( 0 > cTime  ||  0 >= timeInterval )

			return -1L;


		Calendar  Cal = Calendar.getInstance();

		Cal.setTimeInMillis(cTime);


		int cSec = Cal.get(Calendar.SECOND);


		int cMin = Cal.get(Calendar.MINUTE);

		int minInterval;

		if ( 60 < ( cMin / timeInterval + 1 )  * timeInterval ) {
			return ( (60 - cMin)*60 - cSec) * 1000 ;
		}

		else
			minInterval = cMin  % timeInterval;


		//System.out.println ("timeInterval：" + timeInterval + " minInterval: " + minInterval + " cMin:" + cMin);

		if (0 == minInterval && 0 == cSec )
			return 0L;

		return ((timeInterval - minInterval) * 60  - cSec ) * 1000;

	}

	/**
	 * @author Shaq
	 * 根据防打扰时间段的设置返回等待的毫秒数
	 * @param cTime
	 * 需要判断的时间
	 * @param startDistTime
	 * 防打扰开始时间
	 * @param EndDistTime
	 * 防打扰结束时间
	 * @return
	 * -1 ：不打扰时间内
	 * -2：程序出错
	 * >=0: 在打扰时间内，并返回需要等待的毫秒数
	 */
	public static long getTimeIntervalbyDist (long cTime, Calendar startDistTime, Calendar EndDistTime) {



		if ( 0 > cTime  || null == startDistTime || null == EndDistTime)
			return -2;

		Calendar  Cal = Calendar.getInstance();

		Cal.setTimeInMillis(cTime);

		startDistTime.set(Cal.get(Calendar.YEAR), Cal.get(Calendar.MONTH), Cal.get(Calendar.DATE));
		EndDistTime.set(Cal.get(Calendar.YEAR), Cal.get(Calendar.MONTH), Cal.get(Calendar.DATE));


		if (startDistTime.get(Calendar.HOUR_OF_DAY) > EndDistTime.get(Calendar.HOUR_OF_DAY)) {
			if ( Cal.get(Calendar.HOUR_OF_DAY) >= startDistTime.get(Calendar.HOUR_OF_DAY) )  {//开始时间与待比较的时间在同一天
				EndDistTime.add(Calendar.DAY_OF_MONTH, 1);
			} else { // 待比较的时间比开始时间大一天
				startDistTime.add(Calendar.DAY_OF_MONTH, -1);

			}
		}
			/*
			SimpleDateFormat sdf = new SimpleDateFormat( DateTimeFormat1);
			System.out.println( "===startDistTime==" + sdf.format(startDistTime.getTime()));
			System.out.println( "===EndDistTime==" + sdf.format(EndDistTime.getTime()));
			*/
		if ( cTime >= EndDistTime.getTimeInMillis())
			return -1;

		if ( cTime <= startDistTime.getTimeInMillis())
			return -1;


		//EndDistTime.set(Cal.get(Calendar.YEAR), Cal.get(Calendar.MONTH), Cal.get(Calendar.DATE));

		return EndDistTime.getTimeInMillis() - cTime;

	}


	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}


	/**
	 * 将number类型的日期转换成特定格式
	 * @param str
	 * @param type
	 * @return
	 */
	public static String formatNumDate(String str,String type){
		String month = "01";
		String day = "01";
		if("date".equals(type)){
			month = str.substring(4,6);
			day = str.substring(6);
		}else if("date_cut".equals(type)){
			month = str.substring(4,6);
			day = str.substring(6);
			return month+"-"+day;
		}
		return month+"月"+day+"日";
	}

}
