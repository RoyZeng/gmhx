package com.gome.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class SysUtils {
	
	public static boolean isEmptyString(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	public static String nullToString(String source){
		if(source==null){
			return "";
		}else{
			return source;
		}
	}
	
	public static String getCurrentDate() {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date today = new Date();
		String tString = df.format(today);
		return tString;
	}
	
	public static String getCurrentDateTime() {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date today = new Date();
		String tString = df.format(today);
		return tString;
	}
	
	/**
	 * 得到当前系统时间 
	 * @return 当前时间的格式字符串，时间格式为"HH:mm:ss"
	 */
	public static String getCurrentTime() {
		String pattern = "HH:mm:ss";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date today = new Date();
		String tString = df.format(today);
		return tString;
	}
	
	
	public static String LCh(String sourString, String cChar, int cLen) {
		int tLen = sourString.length();
		int i, iMax;
		String tReturn = "";
		if (tLen >= cLen) {
			return sourString;
		}
		iMax = cLen - tLen;
		for (i = 0; i < iMax; i++) {
			tReturn += cChar;
		}
		tReturn = tReturn.trim() + sourString.trim();
		return tReturn;
	}
	

	public static String firstToUpper(String str){
		char c = str.charAt(0);
		return Character.toUpperCase(c) + str.substring(1);
	}
	public static String subString(String localizedMessage, int len) {
		if(isEmptyString(localizedMessage)){
			return "";
		}
		if(localizedMessage.length()>len){
			return localizedMessage.substring(0, len);
		}else{
			return localizedMessage;
		}
	}	
	
    public static String unicodeToGBK(String strOriginal)
    {
        if (strOriginal != null)
        {
            try
            {
                if (!isGBKString(strOriginal))
                {
                    return new String(strOriginal.getBytes("ISO8859_1"), "GBK");
                }
                else
                {
                    return strOriginal;
                }

            }
            catch (Exception exception)
            {
                System.out.println(exception.getMessage());
                return strOriginal;
            }
        }
        else
        {
            return "";
        }
    }	
    
    /**
     * 判断是否是GBK编码
     * @param tStr String
     * @return boolean
     */
    public static boolean isGBKString(String tStr)
    {
        int tlength = tStr.length();
        Integer t = new Integer(0);
        int t1 = 0;
        for (int i = 0; i < tlength; i++)
        {
            t1 = Integer.parseInt(Integer.toOctalString(tStr.charAt(i)));
            if (t1 > 511)
            {
                return true;
            }
        }
        return false;
    }	
    
    /**
	 * 生成32为随机号码作为message_id
	 * 
	 * @return
	 */
	public static String getRandomCode() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
	
	public static boolean isMapContainValue(List<Map<String, Object>> list, String parcelCode) {
		for(Map<String, Object> m: list){
			for(Map.Entry<String, Object> entry: m.entrySet()){
				if("parcel_code".equals(entry.getKey()) && parcelCode.equals(entry.getValue())){
					return true;
				}
			}
		}
		return false;
	}
}
