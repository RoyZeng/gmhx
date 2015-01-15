package com.gome.gmhx.jbpm;

/**
* @ClassName: JBPMTransactions
* @Description: jbpm中线的名称
* @author A18ccms a18ccms_gmail_com
* @date 2014年8月27日 下午4:57:11
*
*/
public interface JbpmTransactions {
	
	public static String transactionSubmit = "T1";//提交
	public static String transactionPass  =  "T2";//通过
	public static String transactionRefuse = "T3";//不通过
	public static String transactionOperationComplete  =  "T4";//操作完成
	public static String transactionCancel = "T5";//取消
	public static String transactionTermination = "T6";//终止
	public static String transactionReturnModify = "T7";//退回修改

}
