package com.jeeplus.modules.xhreception.pay;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * 
 * @author 
 * 
 */
public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	public static final String HOUR = "hour";
	public static final String DAY = "day";
	public static final String MINUTE = "minute";

	public static Date convertDate(String adateStrteStr, String format) {
		java.util.Date date = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			date = simpleDateFormat.parse(adateStrteStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return date;
	}
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static Date getDate(String date){
		Date dd = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dd = notEmpty(date) ? sdf.parse(date) : null;
			return dd;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dd;
	}
	
	private static boolean notEmpty(String date) {
		return date!=null && date.length()!=0;
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "null";
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new java.text.SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 */
	public static Date format(String date) {
		return format(date, null);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 */
	public static Date format(String date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return new Date();
		}
		Date d = null;
		try {
			d = new java.text.SimpleDateFormat(pattern).parse(date);
		} catch (ParseException pe) {
		}
		return d;
	}

	public static String stringDate(){
		DateFormat dateFormat;  
		dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String s = dateFormat.format(date);
		return s;
	}
	
	/**
	 * 得到某天的从00点开始的日期
	 * @param date
	 * @return
	 */
	public static Date getDayZero(Date date){
		String ds = format(date, "yyyy-MM-dd");
		return getDate(ds);
	}

}
