package com.gome.common.util;

public class StatusToValUtil {

	public static String getValByStatus(String status){
		String val=null;
		if ("流程结束".equals(status)) {
			val="S0";
		} else if ("暂存".equals(status)) {
			val="S1";
		} else if ("提交".equals(status)) {
			val="S2";
		} else if ("分部审核".equals(status)) {
			val="S3";
		} else if ("总部审核".equals(status)) {
			val="S4";
		} else if ("填写出库数量".equals(status)) {
			val="S5";
		} else if ("出库".equals(status)) {
			val="S6";
		} else if ("邮包发货".equals(status)) {
			val="S7";
		} else if ("邮包收货".equals(status)) {
			val="S8";
		} else if ("网店确认".equals(status)) {
			val="S9";
		}else if("退回修改".equals(status)){
			val="S10";
		}
		return val;
	}
}
