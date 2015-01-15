package com.gome.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VeDate {
	 /**
	  * 获取现在时间
	  *
	  * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	  */
	 public static Date getNowDate() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(currentTime);
	  ParsePosition pos = new ParsePosition(8);
	  Date currentTime_2 = formatter.parse(dateString, pos);
	  return currentTime_2;
	 }

	 /**
	  * 获取现在时间
	  *
	  * @return返回短时间格式 yyyy-MM-dd
	  */
	 public static Date getNowDateShort() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String dateString = formatter.format(currentTime);
	  ParsePosition pos = new ParsePosition(8);
	  Date currentTime_2 = formatter.parse(dateString, pos);
	  return currentTime_2;
	 }

	 /**
	  * 获取现在时间
	  *
	  * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	  */
	 public static String getStringDate() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }

	 /**
	  * 获取现在时间
	  *
	  * @return 返回短时间字符串格式yyyy-MM-dd
	  */
	 public static String getStringDateShort() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }
}
