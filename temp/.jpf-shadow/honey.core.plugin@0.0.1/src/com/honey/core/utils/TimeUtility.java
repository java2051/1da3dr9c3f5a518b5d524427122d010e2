package com.honey.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式化通用类
 * @author Administrator
 *
 */
public class TimeUtility {
	static public final String FORMAT_DATE_ONLY_CH = "yyyy年MM月dd日";
	static public final String FORMAT_MONTH_ONLY = "yyyy-MM";
	static public final String FORMAT_DATE_ONLY = "yyyy-MM-dd";
	static public final String FORMAT_DATE_HOUR = "yyyy-MM-dd HH:mm";
	static public final String FORMAT_TIME_ONLY = "HH:mm:ss";
	static public final String FORMAT_COMPACT = "yyMMddHHmmssSSS";
	static public final String FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
	static public final String FORMAT_DETAIL = "yyyy-MM-dd HH:mm:ss.SSS";
	
	static public final String FORMAT_YEAR = "yyyy";
	
	static public final String FORMAT_TIME_HOUR = "HH";
	static public final String FORMAT_TIME_MINUTE ="mm";
	
	static public final long DATE_SECOND=86400;//一天有86400秒 
	static public final long DATE_MINUTE=1440;//一天有1440分
	static public final long MINUTE_SECOND=60;//一天有60分
	
	
	public static Date parse(String str, String format) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String format( String format) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.format(new Date());
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static String format(Date date, String format) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isExpire(String strTime, String strExpiredTime) {
		Date time = parse(strTime, FORMAT_NORMAL);
		Date expiredTime = parse(strExpiredTime, FORMAT_NORMAL);

		return (time.compareTo(expiredTime) >= 0);
	}

	/**
	 * 生成制定日期的Date对象，从0点开始
	 * 
	 * @param year
	 * @param month
	 * @param days
	 * @return
	 */
	public static Date createDate(int year, int month, int days) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, days, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

}
