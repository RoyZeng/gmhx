package com.gome.common.util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class BeanTool extends BeanUtils {

	private static Logger _fldif = Logger.getLogger(BeanTool.class);
	public static String DATA_BASE_TYPE = "int;java.lang.Integer;short;java.lang.Short;float;java.lang.Float;double;java.lang.Double;char;java.lang.Char;long;java.lang.Long;boolean;java.lang.Boolean;Sjava.lang.String;byte;java.lang.Byte;";
	public static String ATTRIBUTE_VALUE = "value";
	private class booleanTranslator {

		public boolean getValueByString(String s) {
			s = s.trim().toLowerCase();
			return s.equals("true") || s.equals("1");
		}

		final BeanTool this$0;

		private booleanTranslator() {
			super();
			this$0 = BeanTool.this;
		}

		booleanTranslator(booleanTranslator booleantranslator) {
			this();
		}
	}

	private class byteTranslator {

		public byte getValueByString(String s) {
			return s.getBytes()[0];
		}

		final BeanTool this$0;

		private byteTranslator() {
			super();
			this$0 = BeanTool.this;
		}

		byteTranslator(byteTranslator bytetranslator) {
			this();
		}
	}

	private class charTranslator {

		public char getValueByString(String s) {
			return s.charAt(0);
		}

		final BeanTool this$0;

		private charTranslator() {
			this$0 = BeanTool.this;
		}

		charTranslator(charTranslator chartranslator) {
			this();
		}
	}

	private class doubleTranslator {

		public double getValueByString(String s) {
			return (new Double(s)).doubleValue();
		}

		final BeanTool this$0;

		private doubleTranslator() {
			this$0 = BeanTool.this;
		}

		doubleTranslator(doubleTranslator doubletranslator) {
			this();
		}
	}

	private class floatTranslator {

		public float getValueByString(String s) {
			return (new Float(s)).floatValue();
		}

		final BeanTool this$0;

		private floatTranslator() {
			this$0 = BeanTool.this;
		}

		floatTranslator(floatTranslator floattranslator) {
			this();
		}
	}

	private class intTranslator {

		public int getValueByString(String s) {
			return (new Integer(s)).intValue();
		}

		final BeanTool this$0;

		private intTranslator() {
			this$0 = BeanTool.this;
		}

		intTranslator(intTranslator inttranslator) {
			this();
		}
	}

	private class longTranslator {

		public long getValueByString(String s) {
			return (new Long(s)).longValue();
		}

		final BeanTool this$0;

		private longTranslator() {
			this$0 = BeanTool.this;
		}

		longTranslator(longTranslator longtranslator) {
			this();
		}
	}

	private class shortTranslator {

		public short getValueByString(String s) {
			return (new Short(s)).shortValue();
		}

		final BeanTool this$0;

		private shortTranslator() {
			this$0 = BeanTool.this;
		}

		shortTranslator(shortTranslator shorttranslator) {
			this();
		}
	}

	static class DateTool {
		
		public DateTool() {
			
		}

		public static Date strToDate(String s) throws Exception {
			String s1 = getDefautFormat(s);
			return strToDate(s, s1);
		}

		public static Date strToDate(String s, String s1) throws Exception {
			try {
				if (s1 == null || s1.equals(""))
					s1 = "yyyy-MM-dd";
				else
					s1.equals(DATA_TIME_CONSTANT);
				SimpleDateFormat simpledateformat = new SimpleDateFormat(s1
						.trim());
				return simpledateformat.parse(s.trim());
			} catch (Exception exception) {
				throw new Exception((new StringBuilder("根据")).append(
						s1).append("模式转换字符")
						.append(s).append(
								"异常，请检查！")
						.toString());
			}
		}

		public static String dateToStr(Date date) {
			if (date == null) {
				return "";
			} else {
				SimpleDateFormat simpledateformat = new SimpleDateFormat(
						DATA_TIME_CONSTANT);
				a.debug(date.getClass().getName());
				return simpledateformat.format(date);
			}
		}

		public static String dateToStr(Date date, String s) {
			if (date == null) {
				return "";
			} else {
				SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
				return simpledateformat.format(date);
			}
		}

		public static String getSystemDateStr(String s) throws Exception {
			try {
				Date date = new Date();
				SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
				return simpledateformat.format(date);
			} catch (Exception exception) {
				throw new Exception(
						(new StringBuilder("根据")).append(s).append("模式得到系统时间异常，请检查").append(s).append("是否正确！")
								.toString());
			}
		}

		public static Date getSystemDate() {
			Date date = new Date(System.currentTimeMillis());
			return date;
		}

		public static Date dateAdd(int i, Date date, int j) {
			GregorianCalendar gregoriancalendar = new GregorianCalendar();
			gregoriancalendar.setTime(date);
			gregoriancalendar.add(i, j);
			return gregoriancalendar.getTime();
		}

		public static String getDefautFormat(String s) throws Exception {
			if(s.length()==17){//适应国美接口时间格式
				return "yyyyMMddHHmmssSSS";
			}else if(s.length()==14){
				return "yyyyMMddHHmmss";
			}else if(s.length()==8){
				return "yyyyMMdd";
			}
			s = s.trim();
			String s1 = "";
			String as[] = { "yyyy", "-MM", "-dd" };
			String as1[] = { " HH", ":mm", ":ss" };
			int i = StringUtils.split(s, "-").length;
			s = StringUtils.replace(s, ":", "#");
			int j = StringUtils.split(s, "#").length;
			int k = StringUtils.split(s, " ").length;
			for (int l = 0; l < i; l++)
				s1 = (new StringBuilder(String.valueOf(s1))).append(as[l])
						.toString();

			for (int i1 = 0; i1 < j && j > 1; i1++)
				s1 = (new StringBuilder(String.valueOf(s1))).append(as1[i1])
						.toString();

			if (k > 1 && j < 1)
				s1 = (new StringBuilder(String.valueOf(s1))).append(as1[0])
						.toString();
			if (StringUtils.split(s, ".").length > 1)
				s1 = (new StringBuilder(String.valueOf(s1))).append(".SSS")
						.toString();
			if (s1.equals("")){
				throw new Exception(
						(new StringBuilder("解析字符时间的格式错误： 时间值=")).append(s).toString());
			}
			else
				return s1;
		}

		public static int DATE_TYPE_YEAR = 1;
		public static int DATE_TYPE_MONTH = 2;
		public static int DATE_TYPE_DAY = 5;
		public static int DATE_TYPE_HOUR = 10;
		public static int DATE_TYPE_MINUTE = 12;
		private static Logger a = Logger.getLogger(DateTool.class);
		public static String DATA_TIME_CONSTANT = "yyyy-MM-dd HH:mm:ss";
		public static String DATE_CONSTANT = "yyyy-MM-dd";

	}

	static class StringTool extends StringUtils {

		public StringTool() {
		}

		public static boolean isNotEmpty(String s) {
			return s != null && !s.equals("");
		}

		public static boolean isEmpty(String s) {
			return !isNotEmpty(s);
		}

		public static String linkObjectWithoutRepeat(Collection collection,
				String s, String s1) {
			Hashtable hashtable = new Hashtable();
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				if (obj != null && isNotEmpty(obj.toString()))
					hashtable.put(obj, obj);
			}

			Iterator iterator1 = hashtable.keySet().iterator();
			String s2;
			for (s2 = ""; iterator1.hasNext(); s2 = (new StringBuilder(String
					.valueOf(s2))).append(s1).append(
					iterator1.next().toString().trim()).append(s1).toString())
				if (!s2.equals(""))
					s2 = (new StringBuilder(String.valueOf(s2))).append(s)
							.append(" ").toString();

			return s2;
		}

		public static String str2ISO(String s) throws Exception {
			if (s == null || s.trim().equals(""))
				return s;
			else
				return new String(s.getBytes("GBK"), "iso8859-1");
		}

		public static String iso2GBK(String s) throws Exception {
			if (s == null || s.trim().equals(""))
				return s;
			else
				return new String(s.getBytes("iso8859-1"), "GBK");
		}

		public static String[] split(String s, String s1) {
			ArrayList arraylist = new ArrayList();
			boolean flag = true;
			boolean flag1 = false;
			int j = 0;
			int k = s1.length();
			if (s == null || s.trim().equals("") || s1 == null
					|| s1.trim().equals(""))
				return null;
			do {
				int i = s.indexOf(s1, j);
				if (i > -1) {
					arraylist.add(s.substring(j, i));
					j = i + k;
				} else {
					arraylist.add(s.substring(j, s.length()));
					flag = false;
				}
			} while (flag);
			String as[] = new String[arraylist.size()];
			return (String[]) arraylist.toArray(as);
		}

		public static String parseStr(String s) {
			if (s == null)
				s = "";
			return s;
		}

		public static String parseStr(String s, String s1) {
			if (s == null)
				return s1;
			else
				return s;
		}

		public static byte[] convertPass(char ac[]) {
			byte abyte0[] = (byte[]) null;
			try {
				if (ac == null) {
					abyte0 = new byte[0];
				} else {
					ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
					OutputStreamWriter outputstreamwriter = new OutputStreamWriter(
							bytearrayoutputstream, "UTF-8");
					outputstreamwriter.write(ac);
					outputstreamwriter.flush();
					abyte0 = bytearrayoutputstream.toByteArray();
					outputstreamwriter.close();
				}
			} catch (UnsupportedEncodingException unsupportedencodingexception) {
			} catch (IOException ioexception) {
				throw new IllegalArgumentException((new StringBuilder(
						"Error reading password ")).append(
						ioexception.toString()).toString());
			}
			return abyte0;
		}

		public static byte[] sha1_digest(byte abyte0[]) {
			try {
				MessageDigest messagedigest = null;
				messagedigest = MessageDigest.getInstance("SHA");
				return messagedigest.digest(abyte0);
			} catch (NoSuchAlgorithmException nosuchalgorithmexception) {
				nosuchalgorithmexception.printStackTrace();
			}
			return null;
		}

		public static byte[] md5_digest(byte abyte0[]) {
			try {
				MessageDigest messagedigest = null;
				messagedigest = MessageDigest.getInstance("MD5");
				return messagedigest.digest(abyte0);
			} catch (NoSuchAlgorithmException nosuchalgorithmexception) {
				nosuchalgorithmexception.printStackTrace();
			}
			return null;
		}

		public static String replace(String s, String s1, String s2) {
			StringBuffer stringbuffer = new StringBuffer();
			int i = s1.length();
			for (int j = 0; (j = s.indexOf(s1)) != -1;) {
				stringbuffer.append((new StringBuilder(String.valueOf(s
						.substring(0, j)))).append(s2).toString());
				s = s.substring(j + i);
			}

			stringbuffer.append(s);
			return stringbuffer.toString();
		}

		public static String replaceTag(String s, String s1, String s2) {
			StringBuffer stringbuffer = new StringBuffer();
			int i = 1;
			int j = s1.length();
			if (s == null || s1 == null || s2 == null)
				return s;
			for (int k = 0; (k = s.indexOf(s1)) != -1;) {
				if (i % 2 == 0)
					stringbuffer.append(s2);
				else
					stringbuffer.append(s.substring(0, k));
				s = s.substring(k + j);
				i++;
			}

			stringbuffer.append(s);
			return stringbuffer.toString();
		}

		public static final String getTagContent(String s, String s1) {
			String s2 = "";
			if (s != null && !s.equals("")) {
				int i = s.indexOf(s1);
				if (i != -1) {
					s = s.substring(i + s1.length(), s.length());
					i = s.indexOf(s1);
					if (i != -1)
						s2 = s.substring(0, i);
					else
						s2 = s;
				}
			}
			return s2;
		}

		public static final String formatTag(String s) {
			String s1 = s;
			if (s1 != null)
				s1 = (new StringBuilder("<!--%")).append(s1).append("%-->")
						.toString();
			return s1;
		}

		public static final String htmlEncode(String s) {
			StringBuffer stringbuffer = new StringBuffer();
			if (s == null)
				return "";
			for (int i = 0; i < s.length(); i++)
				stringbuffer.append(a(s.charAt(i)));

			return stringbuffer.toString();
		}

		private static String a(char c) {
			String s = null;
			if (c == '&')
				s = "&";
			else if (c == '"')
				s = "";
			else if (c == '<')
				s = "<";
			else if (c == '>')
				s = ">";
			else
				s = String.valueOf(c);
			return s;
		}

		public static Number toNumber(String s, Number number) {
			if (s == null || s.trim().equals(""))
				return number;
			try {
				NumberFormat.getInstance().parse(s);
			} catch (ParseException parseexception) {
				return number;
			}
			return number;
		}

		public static Date toDate(String s, String s1) {
			try {
				SimpleDateFormat simpledateformat;
				if (s1 != null && !s1.trim().equals(""))
					simpledateformat = new SimpleDateFormat(s1);
				else
					simpledateformat = new SimpleDateFormat();
				return simpledateformat.parse(s);
			} catch (ParseException parseexception) {
				parseexception.printStackTrace();
			}
			return null;
		}

		public static boolean isInt(String s) {
			if (s == null) {
				return false;
			} else {
				Pattern pattern = Pattern.compile("[-+]{0,1}[0-9]+");
				return pattern.matcher(s).matches();
			}
		}

		public static int strToInt(String s) {
			if (!isInt(s))
				return 0;
			else
				return Integer.parseInt(s);
		}

		public static Integer strToInteger(String s) {
			Integer integer = Integer.valueOf(0);
			if (s == null || "".equals(s))
				return Integer.valueOf(0);
			try {
				integer = new Integer(s);
			} catch (Exception exception) {
			}
			return integer;
		}

	}

	public BeanTool() {

	}

	public static void invokeMethod(Object obj, String s, Object aobj[])
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method amethod[] = obj.getClass().getMethods();
		for (int i = 0; i < amethod.length; i++)
			if (amethod[i].getName().equals(s)) {
				java.lang.reflect.Type atype[] = amethod[i]
						.getGenericParameterTypes();
				amethod[i].invoke(obj, aobj);
			}

	}

	public static String getExprFirstAttrName(String s) {
		String as[] = StringUtils.split(s, ".");
		return as[0];
	}

	public static String getExprEndAttr(String s) {
		String as[] = StringUtils.split(s, ".");
		if (as.length > 1)
			return as[as.length - 1];
		else
			return s;
	}

	public static boolean isBeanExpr(String s) {
		return StringUtils.split(s, ".").length > 1;
	}

	public static void setBeanAttrBySampleExpr(Object obj, String s,
			Object obj1, String s1) throws Exception {
		String s2 = (new StringBuilder("变量对象："))
				.append(obj).append(" 变量表达式： ")
				.append(s).append(" 计算对象：").append(
						obj1).append("  计算表达式：")
				.append(s1).toString();
		if (s1 != null && !s1.equals(""))
			obj1 = getSmapleExpressVlaue(obj1, s1);
		if (s == null || s.equals(""))
			if (obj.getClass().isInstance(obj1))
				obj = obj1;
			else
				throw new Exception(
						(new StringBuilder(
								"参数表达式赋值错误,类型不匹配"))
								.append(s2).toString());
		String as[] = StringUtils.split(s, ".");
		String s3 = as[as.length - 1];
		String s4 = "";
		for (int i = 0; i < as.length - 1; i++) {
			if (i != 0 && i != as.length - 1)
				s4 = (new StringBuilder(String.valueOf(s4))).append(".")
						.toString();
			s4 = (new StringBuilder(String.valueOf(s4))).append(as[i])
					.toString();
		}

		Object obj2 = obj;
		if (!s4.equals(""))
			obj2 = getSmapleExpressVlaue(obj, s4);
		Class class1 = getAttributeType(obj2, s3);
		if (class1.isInstance(obj1))
			setAttributeValue(obj2, s3, obj1);
		else if (obj1 instanceof String)
			setAttributeByString(obj2, s3, (String) obj1);
		else
			throw new Exception(
					(new StringBuilder(
							"参数表达式赋值错误,类型不匹配"))
							.append(s2).toString());
	}

	public static String[] getAtrributeNames(Class class1, Class class2)
			throws Exception {
		ArrayList arraylist = new ArrayList();
		Class class3 = class1;
		String as[] = (String[]) null;
		for (; isSubClass(class3, class2) || class3 == class2; class3 = class3
				.getSuperclass()) {
			as = getAtrributeNames(class3);
			String as1[];
			int j = (as1 = as).length;
			for (int i = 0; i < j; i++) {
				String s = as1[i];
				arraylist.add(s);
			}

		}

		as = new String[arraylist.size()];
		arraylist.toArray(as);
		return as;
	}

	public static String[] getAtrributeNames(Object obj) throws Exception {
		return getAtrributeNames(obj.getClass());
	}

	public static Object getSmapleExpressVlaue(Object obj, String s)
			throws Exception {
		String as[] = StringUtils.split(s, ".");
		for (int i = 0; i < as.length; i++) {
			if (obj == null)
				return null;
			if (obj instanceof Map)
				obj = ((Map) obj).get(as[i]);
			obj = getAttributeValue(obj, as[i]);
		}

		return obj;
	}

	public static boolean canAccess(Object obj, String s) {
		if (obj == null || !StringTool.isNotEmpty(s))
			return false;
		String s1 = "";
		try {
			Field field = obj.getClass().getField(s);
			String s2 = field.get(obj).toString();
		} catch (Exception exception) {
			Method method = getGetterMethod(obj, s);
			if (method == null) {
				Method method1 = getIsMethod(obj, s);
				return method1 != null;
			} else {
				return true;
			}
		}
		return true;
	}

	public static Object getAttributeValue(Object obj, String s)
			throws Exception {
		if (!StringTool.isNotEmpty(s))
			return null;
		Object obj1 = "";
		try {
			Field field = obj.getClass().getField(s);
			obj1 = field.get(obj).toString();
		} catch (Exception exception) {
			Method method = null;
			if (getAttributeType(obj.getClass(), s) == Boolean.TYPE)
				method = getIsMethod(obj, s);
			if (method == null)
				method = getGetterMethod(obj, s);
			if (method == null) {
				String s1 = (new StringBuilder(
						"反射获取Bean的属性值失败！　"))
						.append(obj.getClass().getName())
						.append(" 的属性“")
						.append(s)
						.append(
								"” 不是 Public类型，也没有getter方法：")
						.append(a(s)).toString();
				throw new Exception(s1);
			}
			obj1 = method.invoke(obj, null);
		}
		return obj1;
	}

	public static boolean isSubClass(Class class1, Class class2) {
		return class2.isAssignableFrom(class1);
	}

	public static String getAttributeValueAsString(Object obj, String s)
			throws Exception {
		if (!StringTool.isNotEmpty(s))
			return null;
		Object obj1 = getAttributeValue(obj, s);
		if (obj1 instanceof Number) {
			NumberFormat numberformat = NumberFormat.getInstance();
			numberformat.setGroupingUsed(false);
			numberformat.setMaximumIntegerDigits(30);
			numberformat.setMaximumFractionDigits(8);
			return numberformat.format(obj1);
		} else {
			return object2Str(obj1, null);
		}
	}

	public static String object2Str(Object obj) {
		return object2Str(obj, null);
	}

	public static String object2Str(Object obj, String s) {
		if (obj == null)
			return "";
		if (obj instanceof Date)
			return DateTool.dateToStr((Date) obj);
		if (!(obj instanceof Number))
			return obj.toString();
		NumberFormat numberformat;
		try {
			DecimalFormat decimalformat = (DecimalFormat) NumberFormat
					.getPercentInstance();
			if (s != null && !"".equals(s)) {
				int i = StringTool.strToInt(s);
				String s1 = "";
				if (s != null && !s.equals("")) {
					for (int j = 0; j < i; j++)
						s1 = (new StringBuilder(String.valueOf(s1)))
								.append("0").toString();

				}
				String s2 = "";
				if (s1.equals(""))
					s2 = "0";
				else
					s2 = (new StringBuilder("0.")).append(s1).toString();
				BigDecimal bigdecimal = (new BigDecimal(String.valueOf(obj)))
						.setScale(i, 4);
				decimalformat.applyPattern(s2);
				return decimalformat.format(bigdecimal);
			}
		} catch (Exception exception) {
			return String.valueOf(obj);
		}
		numberformat = NumberFormat.getInstance();
		numberformat.setGroupingUsed(false);
		numberformat.setMaximumIntegerDigits(30);
		numberformat.setMaximumFractionDigits(8);
		return numberformat.format(obj);
	}

	public static String number2StrByPre(Object obj, String s) {
		if (obj == null)
			return "";
		if (!(obj instanceof Number))
			return null;
		NumberFormat numberformat;
		try {
			if (s != null && !"".equals(s)) {
				DecimalFormat decimalformat = (DecimalFormat) NumberFormat
						.getPercentInstance();
				String s1 = "";
				String s2 = "";
				int i = StringTool.strToInt(s);
				for (int j = 0; j < i; j++)
					s1 = (new StringBuilder(String.valueOf(s1))).append("0")
							.toString();

				if (s1.equals(""))
					s2 = "0";
				else
					s2 = (new StringBuilder("0.")).append(s1).toString();
				BigDecimal bigdecimal = (new BigDecimal(String.valueOf(obj)))
						.setScale(i, 4);
				decimalformat.applyPattern(s2);
				return decimalformat.format(bigdecimal);
			}
		} catch (Exception exception) {
			return String.valueOf(obj);
		}
		numberformat = NumberFormat.getInstance();
		numberformat.setGroupingUsed(false);
		numberformat.setMaximumIntegerDigits(30);
		numberformat.setMaximumFractionDigits(8);
		return numberformat.format(obj);
	}

	public static Object String2BaseTypeData(String s, Class class1)
			throws Exception {
		throw new Exception("没有实现的方法");
	}

	public static boolean hasCons(Class class1, Class aclass[]) {
		try {
			class1.getConstructor(aclass);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	public static void setAttributeValue(Object obj, String s, Object obj1)
			throws Exception {
		if (obj1 == null || obj == null)
			return;
		if (StringTool.isEmpty(s))
			return;
		if (!canAccess(obj, s))
			return;
		Class class1 = getAttributeType(obj, s);
		Object obj2 = null;
		if ((obj1 instanceof Number) && class1 != obj1.getClass()) {
			obj2 = ((Number) obj1).toString();
			setAttributeByString(obj, s, (String) obj2);
			return;
		}
		if (class1 != obj1.getClass()) {
			if (isBeseType(class1) && isBeseType(obj1.getClass())) {
				obj2 = obj1.toString();
				setAttributeByString(obj, s, (String) obj2);
				return;
			}
			obj2 = obj1;
		} else {
			obj2 = obj1;
		}
		a(obj, s, obj2);
	}

	private static void a(Object obj, String s, Object obj1) throws Exception {
		Object obj2 = obj1;
		try {
			Class class1 = getAttributeType(obj.getClass(), s);
			if (class1 == String.class && obj2 != null)
				obj2 = obj1.toString();
			Field field = obj.getClass().getField(s);
			field.set(obj, obj2);
			obj2 = field.get(obj).toString();
		} catch (Exception exception) {
			Method method = getSetterMethod(obj, s);
			if (method == null) {
				String s1 = (new StringBuilder(
						"反射获取Bean的属性值失败！　"))
						.append(obj.getClass().getName())
						.append(" 的属性“")
						.append(s)
						.append(
								"” 不是 Public类型，也没有setter方法：")
						.append(a(s)).toString();
				throw new Exception(s1);
			}
			try {
				method.invoke(obj, new Object[] { obj2 });
			} catch (Exception exception1) {
				String s2 = (new StringBuilder(
						"反射获取Bean的属性值失败！　"))
						.append(obj.getClass().getName()).append(
								" 属性名: ").append(s).append(
								" 值： ").append(obj2).toString();
				throw new Exception(s2, exception1);
			}
		}
	}

	public static Object createBeseTypeInstance(Class class1, String s)
			throws Exception {
		BeanTool beantool = new BeanTool();
		if (class1 == String.class)
			return s;
		Object obj = beantool.a(class1);
		Class aclass[] = new Class[1];
		aclass[0] = String.class;
		Method method = obj.getClass().getMethod("getValueByString", aclass);
		Object aobj[] = new Object[1];
		aobj[0] = s;
		Object obj1 = null;
		try {
			obj1 = method.invoke(obj, aobj);
		} catch (Exception exception) {
			throw new Exception(
					(new StringBuilder(
							"字符串生成基本数据类型对象实例失败  , 数据类型="))
							.append(class1.getName()).append(" 值=")
							.append(s.toString()).toString(), exception);
		}
		return obj1;
	}

	public static void setAttributeByString(Object obj, String s, String s1)
			throws Exception {
		if (obj == null)
			throw new Exception(
					"Bean属性赋值失败 Bean 为空");
		if (StringUtils.isEmpty(s))
			throw new Exception("属性名称为空！");
		Class class1 = getAttributeType(obj, s);
		if (class1 == null)
			throw new Exception((new StringBuilder("Bean ")).append(
					obj.getClass().getName()).append(
					" 不存在属性 ").append(s).toString());
		String s2 = "int;short;float;double;char;long;boolean;byte;Long;";
		if (s2.indexOf(class1.getName()) >= 0) {
			if ("".equals(s1))
				s1 = "0";
			BeanTool beantool = new BeanTool();
			Object obj2 = beantool.a(class1);
			Class aclass1[] = new Class[1];
			aclass1[0] = String.class;
			Method method = obj2.getClass().getMethod("getValueByString",aclass1);
			Object aobj1[] = new Object[1];
			aobj1[0] = s1;
			try {
				Object obj3 = method.invoke(obj2, aobj1);
				a(obj, s, obj3);
			} catch (Exception exception3) {
				throw new Exception((new StringBuilder(
						"Bean属性赋值失败 Bean ="))
						.append(obj.toString()).append(
								" 属性名称=").append(s).append(
								", 数据类型=").append(
								class1.getName()).append(" 值=").append(
								s1.toString()).toString(), exception3);
			}
		} else {
			Object obj1 = null;
			if (class1 == Date.class || class1 == Time.class
					|| class1 == Timestamp.class) {
				try {
					obj1 = DateTool.strToDate(s1.toString());
				} catch (Exception exception) {
					obj1 = null;
				}
			} else {
				Class aclass[] = new Class[1];
				aclass[0] = String.class;
				Constructor constructor = class1.getConstructor(aclass);
				Object aobj[] = new Object[1];
				aobj[0] = s1;
				try {
					obj1 = constructor.newInstance(aobj);
				} catch (Exception exception2) {
					obj1 = null;
				}
			}
			try {
				a(obj, s, obj1);
			} catch (Exception exception1) {
				throw new Exception((new StringBuilder(
						"Bean属性赋值失败 Bean ="))
						.append(obj.toString()).append(" Attribute=").append(s)
						.append(" value=").append(s1.toString()).toString(),
						exception1);
			}
		}
	}

	private static String _mthdo(String s) {
		s = s.trim();
		String as[] = StringUtils.split(s, " ");
		if (as.length == 1)
			return DateTool.DATE_CONSTANT;
		else
			return DateTool.DATA_TIME_CONSTANT;
	}

	public static Method getGetterMethod(Object obj, String s) {
		Method amethod[] = obj.getClass().getMethods();
		String s1 = a(s);
		for (int i = 0; i < amethod.length; i++)
			if (amethod[i].getName().equals(s1))
				return amethod[i];

		return null;
	}

	public static Method getIsMethod(Object obj, String s) {
		Method amethod[] = obj.getClass().getMethods();
		String s1 = _mthif(s);
		for (int i = 0; i < amethod.length; i++)
			if (amethod[i].getName().equals(s1))
				return amethod[i];

		return null;
	}

	public static Method getSetterMethod(Object obj, String s) {
		Method amethod[] = obj.getClass().getMethods();
		String s1 = _mthfor(s);
		for (int i = 0; i < amethod.length; i++)
			if (amethod[i].getName().equals(s1))
				return amethod[i];

		return null;
	}

	private static String a(String s) {
		String s1 = (new StringBuilder()).append(s.charAt(0)).toString()
				.toUpperCase();
		String s2 = (new StringBuilder("get")).append(s1).append(
				s.substring(1, s.length())).toString();
		return s2;
	}

	private static String _mthif(String s) {
		String s1 = (new StringBuilder()).append(s.charAt(0)).toString()
				.toUpperCase();
		String s2 = (new StringBuilder("is")).append(s1).append(
				s.substring(1, s.length())).toString();
		return s2;
	}

	private static String _mthfor(String s) {
		String s1 = (new StringBuilder()).append(s.charAt(0)).toString()
				.toUpperCase();
		String s2 = (new StringBuilder("set")).append(s1).append(
				s.substring(1, s.length())).toString();
		return s2;
	}

	public static void setCollectionBeanAttr(Collection collection, String s,
			Object obj) throws Exception {
		if (collection == null || StringUtils.isEmpty(s))
			return;
		for (Iterator iterator = collection.iterator(); iterator.hasNext(); setAttributeValue(
				iterator.next(), s, obj))
			;
	}

	public static boolean isBeseType(Object obj) {
		return isBeseType(obj.getClass());
	}

	public static boolean isBeseType(Class class1) {
		if (isSubClass(class1, Number.class))
			return true;
		String s = (new StringBuilder(String.valueOf(class1.getName())))
				.append(";").toString();
		return DATA_BASE_TYPE.indexOf(s) > -1;
	}

	public static boolean isValueAttibute(String s) {
		return s.equals(ATTRIBUTE_VALUE);
	}

	public static Field getFieldByName(Class class1, String s) {
		Field afield[] = class1.getDeclaredFields();
		for (int i = 0; i < afield.length; i++)
			if (afield[i].getName().equals(s.trim()))
				return afield[i];

		return null;
	}

	public static List getMethodByName(Class class1, String s) {
		Method amethod[] = class1.getMethods();
		ArrayList arraylist = new ArrayList();
		for (Iterator iterator = arraylist.iterator(); iterator.hasNext();) {
			Method method = (Method) iterator.next();
			if (method.getName().equalsIgnoreCase(s.trim()))
				arraylist.add(method);
		}

		return arraylist;
	}

	public static Class getAttributeType(Class class1, Class class2, String s)
			throws SecurityException, NoSuchFieldException {
		class2 = ((Class) (class2 != null ? class2 : Object.class));
		ArrayList arraylist = new ArrayList();
		Class class3 = class1;
		String as[] = (String[]) null;
		for (; isSubClass(class3, class2) || class3 == class2; class3 = class3
				.getSuperclass()) {
			Field afield[] = class3.getDeclaredFields();
			for (int i = 0; i < afield.length; i++)
				if (afield[i].getName().equals(s.trim()))
					return afield[i].getType();

		}

		return null;
	}

	public static String getClassNameWithoutPackage(Object obj) {
		String s = obj.getClass().getName();
		String as[] = StringUtils.split(s, ".");
		return as[as.length - 1];
	}

	public static Object createInstanceByName(String s) throws Exception {
		return Class.forName(s).newInstance();
	}

	private Object a(Class class1) {
		String s = class1.getName();
		Object obj = null;
		if (s.equals("int"))
			obj = new intTranslator(null);
		else if (s.equals("short"))
			obj = new shortTranslator(null);
		else if (s.equals("float"))
			obj = new floatTranslator(null);
		else if (s.equals("double"))
			obj = new doubleTranslator(null);
		else if (s.equals("boolean"))
			obj = new booleanTranslator(null);
		else if (s.equals("char"))
			obj = new charTranslator(null);
		else if (s.equals("byte"))
			obj = new byteTranslator(null);
		else if (s.equals("long"))
			obj = new longTranslator(null);
		return obj;
	}

	public static Class getBeanGernaricParmeterType(Class class1, int i) {
		if (class1.getGenericSuperclass() instanceof ParameterizedType) {
			ParameterizedType parameterizedtype = (ParameterizedType) class1
					.getGenericSuperclass();
			Class class2 = (Class) parameterizedtype.getRawType();
			_fldif.debug((new StringBuilder("===================")).append(
					parameterizedtype.getRawType().getClass().getName())
					.toString());
			java.lang.reflect.Type atype[] = parameterizedtype
					.getActualTypeArguments();
			if (atype.length > i) {
				java.lang.reflect.GenericDeclaration genericdeclaration = ((TypeVariable) atype[i])
						.getGenericDeclaration();
				if (genericdeclaration instanceof Class)
					return (Class) genericdeclaration;
			}
		}
		return null;
	}

	public static Class getAttributeParmeterType(Class class1, String s, int i) {
		Field field = getFieldByName(class1, s);
		if (field == null)
			return null;
		if (field.getGenericType() instanceof ParameterizedType) {
			ParameterizedType parameterizedtype = (ParameterizedType) field
					.getGenericType();
			java.lang.reflect.Type atype[] = parameterizedtype
					.getActualTypeArguments();
			if (atype.length > i) {
				java.lang.reflect.Type type = atype[i];
				if (atype[i] instanceof Class)
					return (Class) atype[i];
			}
		}
		return null;
	}

	public static final boolean isOverdue(String s) throws Exception {
		Date date = DateTool.strToDate("2007-06-01");
		Date date1 = new Date(System.currentTimeMillis());
		if (!date.after(date1)) {
			if (StringTool.isEmpty(s))
				s = "该";
			throw new Exception((new StringBuilder(String.valueOf(s))).append(
					"功能试用过期  ;  ").append(
					date1.toGMTString()).toString());
		} else {
			return false;
		}
	}

	public static void bean2Map(Object obj, Map map) throws Exception {
		String as[] = getAtrributeNames(obj.getClass());
		for (int i = 0; i < as.length; i++)
			if (canAccess(obj, as[i]))
				map.put(as[i], getAttributeValueAsString(obj, as[i]));

	}

	public static void map2Bean(Map map, Object obj) throws Exception {
		String as[] = getAtrributeNames(obj.getClass());
		for (int i = 0; i < as.length; i++)
			if (canAccess(obj, as[i])) {
				Object obj1 = map.get(as[i]);
				if (obj1 instanceof Number) {
					NumberFormat numberformat = NumberFormat.getInstance();
					numberformat.setGroupingUsed(false);
					numberformat.setMaximumIntegerDigits(30);
					numberformat.setMaximumFractionDigits(8);
					setAttributeByString(obj, as[i], numberformat.format(obj1));
				} else if (obj1 != null && !"".equals(obj1)) {
					String s = object2Str(obj1, null);
					setAttributeByString(obj, as[i], s);
				}
			}

	}

	public static Object getValueByAttr(Object obj, String s) throws Exception {
		if (obj instanceof Map)
			return ((Map) obj).get(s);
		else
			return getAttributeValue(obj, s);
	}

	public static String changeDataToJSon(List list) throws Exception {
		StringBuffer stringbuffer = new StringBuffer("[");
		ArrayList arraylist = new ArrayList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof Map) {
				java.util.Set set = ((Map) obj).keySet();
				arraylist = new ArrayList(set);
			} else {
				String as[] = getAtrributeNames(obj.getClass());
				String as1[];
				int k = (as1 = as).length;
				for (int j = 0; j < k; j++) {
					String s = as1[j];
					arraylist.add(s);
				}

			}
			if (arraylist.size() > 0)
				stringbuffer.append("{");
			for (int i = 0; i < arraylist.size(); i++) {
				String s1 = (String) arraylist.get(i);
				Object obj1 = getValueByAttr(obj, s1);
				String s2 = obj1 != null ? obj1.toString() : "";
				stringbuffer.append((new StringBuilder(String.valueOf(s1)))
						.append(":'").append(s2).append("'").toString());
				if (i == 0 && i != list.size() - 1 && list.size() > 1)
					stringbuffer.append(",");
			}

			if (arraylist.size() > 0)
				stringbuffer.append("},");
		}

		stringbuffer.deleteCharAt(stringbuffer.length() - 1);
		stringbuffer.append("]");
		return stringbuffer.toString();
	}

	public static String[] getAtrributeNames(Class class1) throws Exception {
		String as[] = (String[]) null;
		ArrayList arraylist = new ArrayList();
		recursiveClassAtrrib(class1, arraylist);
		as = new String[arraylist.size()];
		arraylist.toArray(as);
		Arrays.sort(as);
		return as;
	}

	public static List recursiveClassAtrrib(Class class1, List list)
			throws Exception {
		for (int i = 0; i < class1.getDeclaredFields().length; i++)
			list.add(class1.getDeclaredFields()[i].getName());
		if(class1.getSuperclass()!=null){
			recursiveClassAtrrib(class1.getSuperclass(), list);
		}
		return list;
	}

	public static Class getAttributeType(Object obj, String s)
			throws SecurityException, NoSuchFieldException {
		return getAttributeType(obj.getClass(), s);
	}

	public static Class getAttributeType(Class class1, String s)
			throws SecurityException, NoSuchFieldException {
		Field afield[] = class1.getDeclaredFields();
		for (int i = 0; i < afield.length; i++)
			if (afield[i].getName().equals(s.trim()))
				return afield[i].getType();

		return recursiveAtrribType(class1.getSuperclass(), s);
	}

	public static Class recursiveAtrribType(Class class1, String s)
			throws SecurityException, NoSuchFieldException {
		Field afield[] = class1.getDeclaredFields();
		for (int i = 0; i < afield.length; i++)
			if (afield[i].getName().equals(s.trim()))
				return afield[i].getType();

		String s1 = class1.getSuperclass().getName();
		if (s1.startsWith("com.isoftstone."))
			return recursiveAtrribType(class1.getSuperclass(), s);
		else
			return null;
	}

	public static Map compareVO(Object obj, Object obj1) throws Exception {
		HashMap hashmap = new HashMap();
		Class class1 = obj.getClass();
		Class class2 = obj1.getClass();
		Class class3 = null;
		if (class1 != class2 && !class1.isAssignableFrom(class2)
				&& !class2.isAssignableFrom(class1))
			throw new RuntimeException(
					"两个对象不是同一类别，也没有继承关系，没法比较是否相同，请使用相同类对象。");
		String as[] = (String[]) null;
		if (class1.isAssignableFrom(class2)) {
			as = getAtrributeNames(obj);
			class3 = class1;
		} else if (class2.isAssignableFrom(class1)) {
			as = getAtrributeNames(obj1);
			class3 = class2;
		}
		for (int i = 0; i < as.length; i++) {
			Object obj2 = getAttributeValue(obj, as[i]);
			Object obj3 = getAttributeValue(obj1, as[i]);
			if (obj2 != null && obj3 != null) {
				Object obj4 = null;
				Object obj5 = null;
				Class class4 = getAttributeType(obj, as[i]);
				if (isBeseType(class4)) {
					String s = getAttributeValueAsString(obj, as[i]);
					String s1 = getAttributeValueAsString(obj1, as[i]);
					if (s1 != null && !s1.equals(s))
						hashmap.put((new StringBuilder()).append(class3)
								.append(".").append(as[i]).toString(),
								(new StringBuilder("oldValue:")).append(s)
										.append(",").append("newValue:")
										.append(s1).toString());
					else if (s != null && !s.equals(s1))
						hashmap.put((new StringBuilder()).append(class3)
								.append(".").append(as[i]).toString(),
								(new StringBuilder("oldValue:")).append(s)
										.append(",").append("newValue:")
										.append(s1).toString());
				} else {
					compareVO(obj2, obj3, ((Map) (hashmap)));
				}
			} else if (obj2 != obj3)
				hashmap.put((new StringBuilder()).append(class3).append(".")
						.append(as[i]).toString(), (new StringBuilder(
						"oldValue:")).append(obj2).append(",").append(
						"newValue:").append(obj3).toString());
		}

		return hashmap;
	}

	public static void compareVO(Object obj, Object obj1, Map map)
			throws Exception {
		if (map == null)
			map = new HashMap();
		Class class1 = obj.getClass();
		Class class2 = obj1.getClass();
		Class class3 = null;
		if (class1 != class2 && !class1.isAssignableFrom(class2)
				&& !class2.isAssignableFrom(class1))
			throw new RuntimeException(
					"两个对象不是同一类别，也没有继承关系，没法比较是否相同，请使用相同类对象。");
		String as[] = (String[]) null;
		if (class1.isAssignableFrom(class2)) {
			as = getAtrributeNames(obj);
			class3 = class1;
		} else if (class2.isAssignableFrom(class1)) {
			as = getAtrributeNames(obj1);
			class3 = class2;
		}
		for (int i = 0; i < as.length; i++) {
			Object obj2 = getAttributeValue(obj, as[i]);
			Object obj3 = getAttributeValue(obj1, as[i]);
			Object obj4 = null;
			Object obj5 = null;
			Class class4 = getAttributeType(obj, as[i]);
			if (isBeseType(class4)) {
				String s = getAttributeValueAsString(obj, as[i]);
				String s1 = getAttributeValueAsString(obj1, as[i]);
				if (s1 != null && !s1.equals(s))
					map.put((new StringBuilder()).append(class3).append("-->")
							.append(as[i]).toString(), (new StringBuilder(
							"oldValue:")).append(s).append(",").append(
							"newValue:").append(s1).toString());
				else if (s != null && !s.equals(s1))
					map.put((new StringBuilder()).append(class3).append("-->")
							.append(as[i]).toString(), (new StringBuilder(
							"oldValue:")).append(s).append(",").append(
							"newValue:").append(s1).toString());
			} else {
				compareVO(obj2, obj3, map);
			}
		}

	}

}