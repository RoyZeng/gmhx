package com.gome.common.util;

public class StringUtils {
	/*
	 * 截取后十位
	 */
	public static String subLastTenChar(String positionCode){
		return positionCode.substring(0, positionCode.length() - 10);
	}
}
