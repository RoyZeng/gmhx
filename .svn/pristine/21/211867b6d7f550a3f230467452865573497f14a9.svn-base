/** 
 * @Project:     sy_oa  
 * @Title:       DateUtils.java 
 * @Package:     com.sy.common.util 
 * @Description: 日期时间工具类 
 * @author:      QIJJ  
 * @date:        2013-9-1 下午2:39:22 
 * @version:     v1.0
 */
package com.gome.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;


public class DateUtils {

	// 格式：年－月－日 小时：分钟：秒
	public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

	// 格式：年－月－日 小时：分钟
	public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

	// 格式：年月日 小时分钟秒
	public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";
	
	// 格式：年月日小时分钟秒
	public static final String FORMAT_FOUR = "yyyyMMddHHmmss";
	
	// 格式：月日小时分钟
	public static final String FORMAT_FIVE = "MM-dd HH:mm";

	// 格式：年－月－日
	public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";

	// 格式：年月日
	public static final String EIGHT_STYLE_DATE_FORMAT = "yyyyMMdd";

	// 格式：月－日
	public static final String SHORT_DATE_FORMAT = "MM-dd";

	// 格式：小时：分钟：秒
	public static final String LONG_TIME_FORMAT = "HH:mm:ss";

	// 格式：年-月
	public static final String MONTG_DATE_FORMAT = "yyyy-MM";
	
	// 格式: 年/月
	public static final String MONTH_DATE_FORMAT_2 = "yyyy/MM";

	// 年的加减
	public static final int SUB_YEAR = Calendar.YEAR;

	// 月加减
	public static final int SUB_MONTH = Calendar.MONTH;

	// 天的加减
	public static final int SUB_DAY = Calendar.DATE;

	// 小时的加减
	public static final int SUB_HOUR = Calendar.HOUR;

	// 分钟的加减
	public static final int SUB_MINUTE = Calendar.MINUTE;

	// 秒的加减
	public static final int SUB_SECOND = Calendar.SECOND;
	
	// 格式：年月日小时分钟秒
	public static final String FORMAT_Five_New = "yyyyMMddHHmmssSS";

	static final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四","星期五", "星期六" };

	@SuppressWarnings("unused")
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(FORMAT_ONE);

	public DateUtils() {}

	/**
	 * @Title:       toDate 
	 * @Description: 将符合日期格式的字符串转换为日期类型
	 * @param dateStr
	 * @return    
	 * @return:      java.util.Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:40:54
	 */
	public static java.util.Date toDate(String dateStr) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(FORMAT_ONE);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr);
		} catch (Exception e) {
			d = null;
		}
		
		return d;
	}

	/**
	 * @Title:       toDate 
	 * @Description: 将符合日期格式的字符串转换为日期类型
	 * @param dateStr
	 * @param format
	 * @return    
	 * @return:      java.util.Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:41:52
	 */
	public static java.util.Date toDate(String dateStr, String format) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr);
		} catch (Exception e) {
			d = null;
		}
		
		return d;
	}

	/**
	 * @Title:       formatDateTime 
	 * @Description: 日期转换为字符串
	 * @param date
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:45:47
	 */
	public static String formatDateTime(java.util.Date date) {
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(FORMAT_ONE);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * @Title:       formatDateTime 
	 * @Description: 日期转换为字符串
	 * @param date
	 * @param format
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:48:25
	 */
	public static String formatDateTime(java.util.Date date, String format) {
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * @Title:       getCurrDate 
	 * @Description: 获取当前时间的指定格式
	 * @param format
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:48:47
	 */
	public static String getCurrDate(String format) {
		return formatDateTime(new Date(), format);
	}

	/**
	 * @Title:       dateSub 
	 * @Description: 日期月份相加减处理
	 * @param dateKind
	 * @param dateStr
	 * @param amount
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:49:23
	 */
	public static String dateSub(int dateKind, String dateStr, int amount) {
		Date date = toDate(dateStr, FORMAT_ONE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(dateKind, amount);
		
		return formatDateTime(calendar.getTime(), FORMAT_ONE);
	}

	/**
	 * @Title:       timeSub 
	 * @Description: 两个日期相减
	 * @param firstTime
	 * @param secTime
	 * @return    
	 * @return:      long
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:49:33
	 */
	public static long timeSub(String firstTime, String secTime) {
		long first = toDate(firstTime, FORMAT_ONE).getTime();
		long second = toDate(secTime, FORMAT_ONE).getTime();
		
		return (second - first) / 1000;
	}
	
	/**
	 * @Title:       timeSub 
	 * @Description: 两个日期相减
	 * @param firstTime
	 * @param secTime
	 * @return    
	 * @return:      long
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:49:55
	 */
	public static long timeSub(Date firstTime, Date secTime) {
		long first = firstTime.getTime();
		long second = secTime.getTime();
		
		return (second - first) / 1000;
	}

	/**
	 * @Title:       getDaysOfMonth 
	 * @Description: 获得某月的天数
	 * @param year
	 * @param month
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:50:04
	 */
	public static int getDaysOfMonth(String year, String month) {
		int days = 0;
		if (month.equals("1") || month.equals("3") || month.equals("5")
				|| month.equals("7") || month.equals("8") || month.equals("10")
				|| month.equals("12")) {
			days = 31;
		} else if (month.equals("4") || month.equals("6") || month.equals("9")
				|| month.equals("11")) {
			days = 30;
		} else {
			if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
					|| Integer.parseInt(year) % 400 == 0) {
				days = 29;
			} else {
				days = 28;
			}
		}

		return days;
	}

	/**
	 * @Title:       getDaysOfMonth 
	 * @Description: 获取某年某月的天数
	 * @param year
	 * @param month
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:50:27
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @Title:       getToday 
	 * @Description: 获得当前日期
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:50:51
	 */
	public static int getToday() {
		Calendar calendar = Calendar.getInstance();
		
		return calendar.get(Calendar.DATE);
	}

	/**
	 * @Title:       getToMonth 
	 * @Description:  获得当前月份
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:51:02
	 */
	public static int getToMonth() {
		Calendar calendar = Calendar.getInstance();
		
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * @Title:       getToYear 
	 * @Description: 获得当前年份
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:51:24
	 */
	public static int getToYear() {
		Calendar calendar = Calendar.getInstance();
		
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * @Title:       getDay 
	 * @Description:  返回日期的天
	 * @param date
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:51:37
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.DATE);
	}

	/**
	 * @Title:       getYear 
	 * @Description: 返回日期的年
	 * @param date
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:51:50
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * @Title:       getMonth 
	 * @Description: 返回日期的月份，1-12
	 * @param date
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:52:01
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * @Title:       dayDiff 
	 * @Description: 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
	 * @param date1
	 * @param date2
	 * @return    
	 * @return:      long
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:52:12
	 */
	public static long dayDiff(Date date1, Date date2) {
		
		return (date2.getTime() - date1.getTime()) / 86400000;
	}

	/**
	 * @Title:       yearDiff 
	 * @Description: 比较两个日期的年差
	 * @param before
	 * @param after
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:52:27
	 */
	public static int yearDiff(String before, String after) {
		Date beforeDay = toDate(before, LONG_DATE_FORMAT);
		Date afterDay = toDate(after, LONG_DATE_FORMAT);
		
		return getYear(afterDay) - getYear(beforeDay);
	}

	/**
	 * @Title:       yearDiffCurr 
	 * @Description: 比较指定日期与当前日期的差
	 * @param after
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:52:36
	 */
	public static int yearDiffCurr(String after) {
		Date beforeDay = new Date();
		Date afterDay = toDate(after, LONG_DATE_FORMAT);
		
		return getYear(beforeDay) - getYear(afterDay);
	}

	/**
	 * @Title:       dayDiffCurr 
	 * @Description: 比较指定日期与当前日期的差
	 * @param before
	 * @return    
	 * @return:      long
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:52:46
	 */
	public static long dayDiffCurr(String before) {
		Date currDate = DateUtils.toDate(currDay(), LONG_DATE_FORMAT);
		Date beforeDate = toDate(before, LONG_DATE_FORMAT);
		
		return (currDate.getTime() - beforeDate.getTime()) / 86400000;
	}

	/**
	 * @Title:       getFirstWeekdayOfMonth 
	 * @Description: 获取每月的第一周
	 * @param year
	 * @param month
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:52:59
	 */
	public static int getFirstWeekdayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
		c.set(year, month - 1, 1);
		
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * @Title:       getLastWeekdayOfMonth 
	 * @Description: 获取每月的最后一周
	 * @param year
	 * @param month
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:53:14
	 */
	public static int getLastWeekdayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
		c.set(year, month - 1, getDaysOfMonth(year, month));
		
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * @Title:       getNow 
	 * @Description: 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:59:13
	 */
	public static String getNow() {
		Calendar today = Calendar.getInstance();
		
		return formatDateTime(today.getTime(), FORMAT_ONE);
	}

	/**
	 * @Title:       getAstro 
	 * @Description: 根据生日获取星座
	 * @param birth
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:59:27
	 */
	public static String getAstro(String birth) {
		if (!isDate(birth)) {
			birth = "2000" + birth;
		}
		if (!isDate(birth)) {
			return "";
		}
		
		int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1,birth.lastIndexOf("-")));
		int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
		String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
		int[] arr = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
		int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
		
		return s.substring(start, start + 2) + "座";
	}

	/**
	 * @Title:       isDate 
	 * @Description: 判断日期是否有效,包括闰年的情况
	 * @param date
	 * @return    
	 * @return:      boolean
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午2:59:43
	 */
	public static boolean isDate(String date) {
		StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
		reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
		reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
		reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
		reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
		reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
		reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
		reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
		
		Pattern p = Pattern.compile(reg.toString());
		
		return p.matcher(date).matches();
	}

	/**
	 * @Title:       nextMonth 
	 * @Description: 取得指定日期过 months 月后的日期 (当 months 为负数表示指定月之前)
	 * @param date  日期 为null时表示当天
	 * @param months  相加(相减)的月数
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:02:43
	 */
	public static Date nextMonth(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.MONTH, months);
		
		return cal.getTime();
	}

	/**
	 * @Title:       nextDay 
	 * @Description: 取得指定日期过 day 天后的日期 (当 day 为负数表示指日期之前)
	 * @param date 
	 * @param day
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:03:21
	 */
	public static Date nextDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.DAY_OF_YEAR, day);
		
		return cal.getTime();
	}

	/**
	 * @Title:       nextDay 
	 * @Description: 取得距离今天 day 日的日期
	 * @param day
	 * @param format
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:04:01
	 */
	public static String nextDay(int day, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, day);
		
		return formatDateTime(cal.getTime(), format);
	}

	/**
	 * @Title:       nextWeek 
	 * @Description:  取得指定日期过 day 周后的日期 (当 day 为负数表示指定月之前)
	 * @param date
	 * @param week
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:05:38
	 */
	public static Date nextWeek(Date date, int week) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.WEEK_OF_MONTH, week);
		
		return cal.getTime();
	}

	/**
	 * @Title:       currDay 
	 * @Description: 获取当前的日期(yyyy-MM-dd) 
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:05:11
	 */
	public static String currDay() {
		
		return DateUtils.formatDateTime(new Date(), DateUtils.LONG_DATE_FORMAT);
	}
	
	/**
	 * @Title:       currDays 
	 * @Description: 获取当前的日期(yyyy-MM-dd HH:mm:ss) 
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:05:53
	 */
	public static String currDays() {
		
		return DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_ONE);
	}
	
	/**
	 * @Title:       befoDay 
	 * @Description:  获取昨天的日期
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:06:17
	 */
	public static String befoDay() {
		
		return befoDay(DateUtils.LONG_DATE_FORMAT);
	}

	/**
	 * @Title:       befoDay 
	 * @Description: 根据时间类型获取昨天的日期
	 * @param format
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:06:29
	 */
	public static String befoDay(String format) {
		
		return DateUtils.formatDateTime(DateUtils.nextDay(new Date(), -1), format);
	}

	/**
	 * @Title:       befoMonth 
	 * @Description: 根据时间类型获取上月的月份
	 * @param format
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:06:41
	 */
	public static String befoMonth(String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -1);
		
		return DateUtils.formatDateTime(cal.getTime(), format);
	}

	/**
	 * @Title:       afterDay 
	 * @Description: 获取明天的日期
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:06:58
	 */
	public static String afterDay() {
		
		return DateUtils.formatDateTime(DateUtils.nextDay(new Date(), 1),DateUtils.LONG_DATE_FORMAT);
	}

	/**
	 * @Title:       getDayNum 
	 * @Description: 取得当前时间距离1900/1/1的天数
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:07:20
	 */
	public static int getDayNum() {
		int daynum = 0;
		GregorianCalendar gd = new GregorianCalendar();
		Date dt = gd.getTime();
		
		GregorianCalendar gd1 = new GregorianCalendar(1900, 1, 1);
		Date dt1 = gd1.getTime();
		daynum = (int) ((dt.getTime() - dt1.getTime()) / (24 * 60 * 60 * 1000));
	
		return daynum;
	}

	/**
	 * @Title:       getDateByNum 
	 * @Description: getDayNum的逆方法(用于处理Excel取出的日期格式数据等)
	 * @param day
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:07:42
	 */
	public static Date getDateByNum(int day) {
		GregorianCalendar gd = new GregorianCalendar(1900, 1, 1);
		Date date = gd.getTime();
		date = nextDay(date, day);
		
		return date;
	}

	/**
	 * @Title:       getYmdDateCN 
	 * @Description: 针对yyyy-MM-dd HH:mm:ss格式,显示yyyymmdd 
	 * @param datestr
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:07:55
	 */
	public static String getYmdDateCN(String datestr) {
		if (datestr == null){
			return "";
		}
		if (datestr.length() < 10){
			return "";
		}
			
		StringBuffer buf = new StringBuffer();
		buf.append(datestr.substring(0, 4)).append(datestr.substring(5, 7)).append(datestr.substring(8, 10));
	
		return buf.toString();
	}

	/**
	 * @Title:       getFirstDayOfMonth 
	 * @Description: 获取本月第一天
	 * @param format
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:08:39
	 */
	public static String getFirstDayOfMonth(String format) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		
		return formatDateTime(cal.getTime(), format);
	}
	
	/**
	 * @Title:       getFirstDayOfMonth 
	 * @Description: 获取某月第一天
	 * @param date
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:08:51
	 */
	public static Date getFirstDayOfMonth(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		
		return cal.getTime();
	}

	/**
	 * @Title:       getLastDayOfMonth 
	 * @Description: 获取本月最后一天
	 * @param format
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:09:15
	 */
	public static String getLastDayOfMonth(String format) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		
		return formatDateTime(cal.getTime(), format);
	}
	
	/**
	 * @Title:       getLastDayOfMonth 
	 * @Description: 获取某月最后一天
	 * @param date
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:09:40
	 */
	public static Date getLastDayOfMonth(Date date) {
		if (date == null){
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		
		return cal.getTime();
	}

	/**
	 * @Title:       getStartOfDay 
	 * @Description: Returns a Date set to the first possible millisecond of the day, just
	 *				 after midnight. If a null day is passed in, a new Date is created.
	 *				 midnight (00m 00h 00s)
	 * @param day
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:10:25
	 */
	public static Date getStartOfDay(Date day) {
		
		return getStartOfDay(day, Calendar.getInstance());
	}

	/**
	 * @Title:       getTodayStart 
	 * @Description: 获取自定义开始时间
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:11:11
	 */
	public static Date getTodayStart(){
		
		return getStartOfDay(new Date());
	}
	
	/**
	 * @Title:       getBeforeDate 
	 * @Description: 获取前天时间
	 * @param mirror
	 * @param dayNum
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:11:16
	 */
	public static Date getBeforeDate(Date mirror, int dayNum) {
		Calendar current = Calendar.getInstance();
		current.setTime(mirror);
		current.add(Calendar.HOUR, -24 * dayNum);
	
		return current.getTime();
	}
	
	/**
	 * @Title:       getStartOfDay 
	 * @Description: Returns a Date set to the first possible millisecond of the day, just
	 *				 after midnight. If a null day is passed in, a new Date is created.
	 *				 midnight (00m 00h 00s)
	 * @param day
	 * @param cal
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:11:38
	 */
	public static Date getStartOfDay(Date day, Calendar cal) {
		if (day == null){
			day = new Date();
		}
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		
		return cal.getTime();
	}

	/**
	 * @Title:       getEndOfDay 
	 * @Description: Returns a Date set to the last possible millisecond of the day, just
	 * 				 before midnight. If a null day is passed in, a new Date is created.
	 *				 midnight (00m 00h 00s)
	 * @param day
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:11:57
	 */
	public static Date getEndOfDay(Date day) {
		
		return getEndOfDay(day, Calendar.getInstance());
	}

	/**
	 * @Title:       getEndOfDay 
	 * @Description: 获取自定义最后一天
	 * @param day
	 * @param cal
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:12:12
	 */
	public static Date getEndOfDay(Date day, Calendar cal) {
		if (day == null){
			day = new Date();
		}
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
		
		return cal.getTime();
	}

	/**
	 * @Title:       timeDiffForDay 
	 * @Description: 判断2个时间相差多少天
	 * @param pBeginTime
	 * @param pEndTime
	 * @return
	 * @throws ParseException    
	 * @return:      Long
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:13:09
	 */
	public static Long timeDiffForDay(Date pBeginTime, Date pEndTime)throws ParseException {
		Long beginL = pBeginTime.getTime();
		Long endL = pEndTime.getTime();
		Long day = (endL - beginL) / 86400000;
		
		return day;
	}

	/**
	 * @Title:       timeDiffForHour 
	 * @Description: 判断2个时间相差多少小时
	 * @param pBeginTime
	 * @param pEndTime
	 * @return
	 * @throws ParseException    
	 * @return:      Long
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:13:32
	 */
	public static Long timeDiffForHour(Date pBeginTime, Date pEndTime)throws ParseException {
		Long beginL = pBeginTime.getTime();
		Long endL = pEndTime.getTime();
		Long hour = ((endL - beginL) % 86400000) / 3600000;
		
		return hour;
	}

	/**
	 * @Title:       timeDiffForMin 
	 * @Description:  判断2个时间相差多少分
	 * @param pBeginTime
	 * @param pEndTime
	 * @return
	 * @throws ParseException    
	 * @return:      Long
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:13:46
	 */
	public static Long timeDiffForMin(Date pBeginTime, Date pEndTime)throws ParseException {
		Long beginL = pBeginTime.getTime();
		Long endL = pEndTime.getTime();
		Long min = ((endL - beginL) % 86400000 % 3600000) / 60000;
		
		return min;
	}
	
	/**
	 * @Title:       getTime 
	 * @Description: 返回指定时间与当前时间差多少天(小时, 分钟, 刚才)
	 * @param time   要比较的时间
	 * @return    
	 * @return:      String 多少天(小时, 分钟, 刚才)
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:14:19
	 */
	public static String getTime(Date time) {
		String result = null;
		Long temp = null;
		final Date currentDate = new Date();
		
		do {
			try {
				// 看差多少天
				temp = timeDiffForDay(time, currentDate);
				if (temp > 0l) {
					result = temp + "天前";
					break;
				}
				
				// 看差多少时
				temp = timeDiffForHour(time, currentDate);
				if (temp > 0l) {
					result = temp + "小时前";
					break;
				}
				
				// 看差多少分
				temp = timeDiffForMin(time, currentDate);
				if (temp > 0l) {
					result = temp + "分种前";
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} while (false);
		
		return result == null ? "刚才" : result;
	}
	
	/**
	 * @Title:       getMonthDelay 
	 * @Description: 获取延迟月份时间
	 * @param currentDay
	 * @param month
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:15:26
	 */
	public static Date getMonthDelay(Date currentDay, int month) {
		Calendar current = Calendar.getInstance();
		current.setTime(currentDay);

		current.add(Calendar.MONTH, month);
		current.add(Calendar.HOUR, -24);
		
		return current.getTime();
	}
	
	/**
	 * @Title:       getStartOfLastMonth 
	 * @Description: 获取上月开始时间
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:15:41
	 */
	public static Date getStartOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, -1);
		
		return cal.getTime();
	}
	
	/**
	 * @Title:       getStartOfToday 
	 * @Description: 获取当天开始时间
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:16:06
	 */
	public static Date getStartOfToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		
		return cal.getTime();
	}
	
	/**
	 * @Title:       getEndOfLastMonth 
	 * @Description: 获取上月最后一天
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:16:14
	 */
	public static Date getEndOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		
		return cal.getTime();
	}
	
	/**
	 * @Title:       getMinEndOfLastMonth 
	 * @Description: 获取上月中旬时间
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:16:20
	 */
	public static Date getMinEndOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		
		return cal.getTime();
	}
	
	/**
	 * @Title:       getDaysOfLastMonth 
	 * @Description: 获取上月天数
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:16:28
	 */
	public static int getDaysOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
	
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @Title:       getWorkTime 
	 * @Description: 获取两个日期中间的工作日
	 * @param starttime
	 * @param endtime
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:17:05
	 */
	public static int getWorkTime(String starttime, String endtime) {
		// 设置时间格式
		SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT);
		Date dateFrom = null;
		Date dateTo = null;
		try {
			dateFrom = dateFormat.parse(starttime);
			dateTo = dateFormat.parse(endtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int workdays = 0;
		Calendar cal = null;
		while (dateFrom.before(dateTo) || dateFrom.equals(dateTo)) {
			cal = Calendar.getInstance();
			// 设置日期
			cal.setTime(dateFrom);
			if ((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
					&& (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
				// 进行比较，如果日期不等于周六也不等于周日，工作日+1
				workdays++;
			}
			// 日期加1
			cal.add(Calendar.DAY_OF_MONTH, 1);
			dateFrom = cal.getTime();
		}
		
		return workdays;
	}

	/**
	 * @Title:       getYearDays 
	 * @Description: 获取两个日期中间的工作日
	 * @param cal
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:18:04
	 */
	public static int getYearDays(Calendar cal) {
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.roll(Calendar.DAY_OF_YEAR, -1);
		int yearDays = cal.get(Calendar.DAY_OF_YEAR);
		
		return yearDays;
	}
	
	/**
	 * @Title:       getFirstDayOfWeek 
	 * @Description: 获取本周第一天
	 * @param date
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:18:25
	 */
	public static Date getFirstDayOfWeek(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());// Monday
		
		return cal.getTime();
	}
	
	/**
	 * @Title:       getLastDayOfWeek 
	 * @Description: 获取本周最后一天
	 * @param date
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:18:37
	 */
	public static Date getLastDayOfWeek(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6);// Sunday
		
		return cal.getTime();
	}
	
	/**
	 * @Title:       getNextMonth0fDay 
	 * @Description: 获取某月次月的最后一天
	 * @param date
	 * @param format
	 * @return    
	 * @return:      String
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:18:47
	 */
	public static String  getNextMonth0fDay(Date date,String format){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH,2);
		cal.add(Calendar.DATE, -1);
		
		return formatDateTime(cal.getTime(),format);
	}
	
	/**
	 * @Title:       getRecentTimeVal 
	 * @Description: 当前时间减去38年的 毫秒数.
	 * @return    
	 * @return:      long
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:19:00
	 */
	public static long getRecentTimeVal() {
		// 38 年的 毫秒数
		long millisecondsIn38 = 86400 * 365 * 38;
		
		return System.currentTimeMillis() / 1000 - millisecondsIn38;
	}
	
	/**
	 * @Title:       getDayOfWeek 
	 * @Description: 获得某一天是星期几, 默认是当前日期.
	 * @param date
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:19:16
	 */
	public static int getDayOfWeek(Date date) {
		final Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * @Title:       getNextMonday 
	 * @Description: 获得某一天的下周一的日期, 默认是当天.
	 * @param date
	 * @return    
	 * @return:      Date
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:19:29
	 */
	public static Date getNextMonday(Date date) {
		final Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		
		final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
   	    cal.add(Calendar.DATE, (7 - dayOfWeek + 2) % 7);
   	    
   	    return cal.getTime();
	}
	
	/**
	 * @Title:       getHourOfDay 
	 * @Description: 拿某一时间的 24小时计时法中的小时数, 默认当前时间
	 * @param date
	 * @return    
	 * @return:      int
	 * @throws:      
	 * @author:      QIJJ
	 * @date:        2013-9-1 下午3:19:51
	 */
	public static int getHourOfDay(Date date) {
    	final Calendar cal = Calendar.getInstance();  
		if (date != null) {
			cal.setTime(date);
		}
		
    	return cal.get(Calendar.HOUR_OF_DAY);
	}

	
	public static void main(String[] args) throws ParseException {
		String ds = "2011-11-18";
		Date d = new SimpleDateFormat("yyyy-MM-dd").parse(ds);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int i1 = cal.get(Calendar.MONTH);
		while (cal.get(Calendar.MONTH) != (i1 == 11 ? 0 : i1 + 1)) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		cal.add(Calendar.DAY_OF_MONTH, -1);
		System.out.println("这个月是" + cal.get(Calendar.DAY_OF_MONTH) + "天");
		
		String ds1 = ds.substring(0, ds.lastIndexOf("-") + 1) + 1;
		String ds2 = ds.substring(0, ds.lastIndexOf("-") + 1) + cal.get(Calendar.DAY_OF_MONTH);
		System.out.println(ds1);
		System.out.println(ds2);
		System.out.println(getWorkTime(ds1, ds2));
		
		
		System.out.println("--------------------");
		System.out.println(DateUtils.getBeforeDate( new Date(), 1));
		
		
		System.out.println("-----------本周第一天-------------");
		System.out.println(formatDateTime(getFirstDayOfWeek(new Date())));
	}
		
}
