package com.lhh.file.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;


public class UtilsDate {
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static final String yyyy_MM_dd_HH_mm_ss_zhry = "yyyy年MM月dd日 HH:mm";
    public static final String yyyy = "yyyy";
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyyMM = "yyyyMM";

	

	public static void main(String[] args) {
		System.out.println(UtilsDate.getSystemDateToString("yyyy-MM-dd 23:59:59"));
		System.out.println(UtilsDate.getSystemDateToDate("yyyy-MM-dd 23:59:59"));
		System.out.println(UtilsDate.getStringToDate(yyyy_MM_dd_HH_mm_ss, UtilsDate.getSystemDateToString("yyyy-MM-dd 23:59:59")));
		System.out.println(UtilsDate.getDateToString(UtilsDate.getSystemDateToDate("yyyy-MM-dd 23:59:59"),yyyy_MM_dd_HH_mm_ss ));
	}
	
	

	/**
	 * 将字符串日期格式转化成另一种日期格式
	 * 
	 * @param strDate
	 *            :字符日期
	 * @param dateFormat
	 *            ：字符日期的格式
	 * @param pformat
	 *            ：需要格式化后的日期格式
	 * @return
	 */
	public static String getStringToString(String strDate, String dateFormat,
			String pformat) {
		return getDateToString(getStringToDate(dateFormat, strDate), pformat);

	}

	/**
	 * 系统日期转化成字符串
	 * 
	 * @param format
	 * @return
	 */
	public static String getSystemDateToString(String format) {
		return getDateToString(new Date(), format);
	}

	/**
	 * 日期转化成str字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateToString(Date date, String format) {
		if (date == null)
			return null;
		SimpleDateFormat siFormat = new SimpleDateFormat(format);
		try {
			return siFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 日期转化成str字符串(如果出错。或者为空则返回第三个参数的默认对象)
	 * 
	 * @param date
	 * @param format
	 * @param str
	 * @return
	 */
	public static String getDateToString(Date date, String format,String str) {
		if (date == null)
			return str;
		SimpleDateFormat siFormat = new SimpleDateFormat(format);
		try {
			return siFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	

	/**
	 * 系统日期的前n日
	 * 
	 * @param dateTime
	 *            ：待处理的日期
	 * @param n
	 *            ：加减天数
	 * @return
	 */
	public static String getSystemDateToYesterday(int n, String format) {
		try {
			return getDateToString(new Date(getSystemDateToDate(format)
					.getTime() - getTime(n)), format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取系统日期的年月份
	 * 
	 * @param format
	 * @param n
	 *            :正数为后几月，负数为前几月
	 * @return
	 */
	public static String getSysBeforMoth(String format, int n) {
		return new SimpleDateFormat(format).format(getSysBeforMoth(n));
	}
	
	/**
	 * 读取某一年的今天
	 * @param date
	 * @param num：正数代表未来+几年，负数代表过去-几年
	 * @return
	 */
	public static Date getYearToday(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// calendar.add(Calendar.WEEK_OF_YEAR, -1);
		calendar.add(Calendar.YEAR, -1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 系统日期int格式
	 * 
	 * @param format
	 * @return
	 */
	public static int getSystemDateInteger(String format) {
		return getDateInteger(format, new Date());

	}

	/**
	 * 读取日期中的年份
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static int getDateInteger(String format, Date date) {
		try {
			return Integer.parseInt(new SimpleDateFormat(format).format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 月份的最后一天
	 * @param date
	 * @return
	 */
	public static int getMothLastDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 距离n天的毫秒数
	 * 
	 * @param n
	 * @return
	 */
	public static long getTime(int n) {
		return n * 24 * 60 * 60 * 1000;
	}

	/**
	 * 系统日期的前n日、或者后n日
	 * 
	 * @param dateTime
	 *            ：待处理的日期
	 * @param n
	 *            ：加减天数(正=后，负=前)
	 * @return
	 */
	public static Date getBeforDay(int n, Date date) {
		try {
			return new Date(date.getTime() + getTime(n));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * N个月的最后一天
	 * @return
	 */
	public static Date getBeforMathLastDay(int n){
		 Calendar c = Calendar.getInstance();
		 c.add(Calendar.MONTH, n);
		 //得到一个月最后一天日期(31/30/29/28)
		 int MaxDay=c.getActualMaximum(Calendar.DAY_OF_MONTH);
		 //按你的要求设置时间
		 c.set( c.get(Calendar.YEAR), c.get(Calendar.MONTH), MaxDay, 23, 59, 59);
		 return c.getTime();
	}

	/**
	 * 读取日期月份
	 * 
	 * @param format
	 * @param n
	 *            :正数为后几月，负数为前几月
	 * @return
	 */
	public static Date getSysBeforMoth(int n) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, n);
		return c.getTime();
	}
	

	/**
	 * 读取系统日期
	 * 
	 * @param format
	 * @return
	 */
	public static Date getSystemDateToDate(String format) {
		return getStringToDate(format,
				new SimpleDateFormat(format).format(new Date()));
	}

	/**
	 * 字符串日期转化成日期对象Date
	 * 
	 * @param format
	 * @param date
	 * @param locale
	 * @return
	 */
	public static Date getStringToDate(String format, String date, Locale locale) {
		try {
			if( StringUtils.isNotBlank(date))return new SimpleDateFormat(format, locale).parse(date);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串日期转化成日期对象Date（本地日期对象）
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static Date getStringToDate(String format, String date) {
		return getStringToDate(format, date, Locale.CHINA);
	}

	/**
	 * 比较两个日期是否相等
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEquals(Date date1, Date date2) {
		return isEqualsYYYYMMDD(getDateToString(date1, yyyy_MM_dd_HH_mm_ss),
				getDateToString(date2, yyyy_MM_dd_HH_mm_ss));
	}

	/**
	 * 当前日期是否与系统日期相等
	 * 
	 * @param date
	 * @param systemformat
	 * @return
	 */
	public static boolean isEqualsYYYYMMDDToday(String date, String systemformat) {
		return isEqualsYYYYMMDD(date, getSystemDateToString(systemformat));
	}

	/**
	 * 当前日期是否与系统日期相等
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isEqualsYYYYMMDDToday(Date date) {
		return isEqualsYYYYMMDD(date, new Date());
	}

	/**
	 * 比较两个日期是否相等
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqualsYYYYMMDD(Date date1, Date date2) {
		return isEqualsYYYYMMDD(getDateToString(date1, yyyy_MM_dd),
				getDateToString(date2, yyyy_MM_dd));
	}

	/**
	 * 比较两个日期字符串是否相等，需要确定两个日期的字符格式
	 * 
	 * @param str1
	 * @param str1Format
	 * @param str2
	 * @param str2Format
	 * @return
	 */
	public static boolean isEqualsYYYYMMDD(String str1, String str1Format,
			String str2, String str2Format) {
		return isEqualsYYYYMMDD(
				getStringToString(str1, str1Format, yyyy_MM_dd),
				getStringToString(str2, str2Format, yyyy_MM_dd));
	}

	/**
	 * 比较两个日期字符串是否相等，需要确定两个日期的字符格式
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEqualsYYYYMMDD(String str1, String str2) {
		if (str1 == null) {
			if (str2 == null)
				return true;
			return false;
		}
		return str1.equals(str2);
	}

	/**
	 * 比较日期大小
	 * 
	 * @param date1
	 * @param date2
	 * @return true：date1大于/等于data2；false：date1小于data2
	 */
	public static boolean isMoreSize(Date date1, Date date2) {
		if (date1 == null)
			return false;
		if (!date1.before(date2))
			return true;
		return false;
	}



    
}
