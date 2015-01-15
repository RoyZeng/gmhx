package com.gome.gmhx.jbpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
* @ClassName: JBPMProcessDefinations
* @Description: jbpm中流程定义名称（服务以"SP"开头，配件以"MP"开头）
* @author liuchao43
* @date 2014年8月27日 下午4:57:57
*
*/
public class JbpmProcessDefinations {
	
	public static Properties mapping = null;
	
	public static String getProcessDefinations(String billType){
		
		try{
			if(mapping==null){
				mapping = new Properties();
				mapping.load(JbpmProcessDefinations.class.getResourceAsStream("billTypeAndProcessMapping.properties"));
			}
			return mapping.getProperty(billType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public static final String  headDeptNewMaterialAllocation = "MP01";//总部物料新料调拨流程
	public static final String  subDeptNewMaterialApply = "MP02";//分部物料新料申请
	public static final String webSiteNewMaterialApply = "MP03";//网点物料新料申请
	public static final String webSiteNewMaterialReturn = "MP04";//网点物料新料退回
	public static final String webSiteOldMaterialReturn = "MP05";//网点物料旧料退回
	public static final String webSiteMaterialBad = "MP06";//网点物料来料不良
	public static final String webSiteMaterialLost = "MP07";//网点物料来料报失
	public static final String webSiteOutGoodSale = "MP08";//网点物料保外销售
	public static final String subDeptOldMaterialScrap = "MP09";//分部配件旧料报废
	public static final String subDeptStockTransfer = "MP10";//分部库存转移
	public static final String serviceBillAuditing = "SP01";//服务单审核
	public static final String installationReceipt = "SP02";//安装回执
	public static final String remoteInstallationFeeApplication = "SP03";//远程安装费申请
	public static final String engineeringMachineServiceApply = "SP04";//工程机服务单申请
	public static final String returnMachineApply = "SP05";//退换机申请
	public static final String repairReceipt  = "SP06";//维修回执
	//物料 服务
	public static final String MATERIAL="MP";
	
}
