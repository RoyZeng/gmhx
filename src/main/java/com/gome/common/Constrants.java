package com.gome.common;

import java.util.HashMap;
import java.util.Map;

public class Constrants {
	//码表map
	public static final Map<String, Map<String, String>> CODEMAP = new HashMap<String, Map<String, String>>();
	//物料标题map
	public static final Map<String, String> FITTING_TITLE_MAP = new HashMap<String, String>();
	
	//
	public static final Map<String, String> JBPMWORKENTITYSTATUS_MAP = new HashMap<String, String>();

	static{
		/*
		FITTING_TITLE_MAP.put("fb-blp-fh","分部配件来料不良");
		FITTING_TITLE_MAP.put("fb-ll-bs","分部配件来料报失");
		FITTING_TITLE_MAP.put("fb-n-cg","分部配件新料采购");
		FITTING_TITLE_MAP.put("fb-n-ck-xs","分部配件新料销售出库");
		FITTING_TITLE_MAP.put("fb-n-sq","分部配件新料申请");
		FITTING_TITLE_MAP.put("fb-n-th","分部配件新料退回");
		FITTING_TITLE_MAP.put("fb-o-bf","分部配件旧料报废");
		FITTING_TITLE_MAP.put("fb-o-ck-xs","分部配件旧料销售出库");
		FITTING_TITLE_MAP.put("fb-o-fh","分部配件旧料返回");
		FITTING_TITLE_MAP.put("wd-blp-fh","网点配件来料不良");
		FITTING_TITLE_MAP.put("wd-ll-bs","网点配件来料报失");
		FITTING_TITLE_MAP.put("wd-n-cg","网点配件新料采购");
		FITTING_TITLE_MAP.put("wd-n-ck-xs","网点配件新料销售出库");
		FITTING_TITLE_MAP.put("wd-n-sq","网点配件新料申请");
		FITTING_TITLE_MAP.put("wd-n-th","网点配件新料退回");
		FITTING_TITLE_MAP.put("wd-o-ck-xs","网点配件旧料销售出库");
		FITTING_TITLE_MAP.put("wd-o-fh","网点配件旧料返回");
		FITTING_TITLE_MAP.put("wd-yjjh-sq","网点配件应急计划申请");
		FITTING_TITLE_MAP.put("zb-n-ck-pk","总部配件新料盘亏出库");
		FITTING_TITLE_MAP.put("zb-n-ck-qt","总部配件新料其他出库");
		FITTING_TITLE_MAP.put("zb-n-ck-thscck","总部配件新料退回生产仓库");
		FITTING_TITLE_MAP.put("zb-n-ck-xs","总部配件新料销售出库");
		FITTING_TITLE_MAP.put("zb-n-db","分部配件新料调拨");
		FITTING_TITLE_MAP.put("zb-n-rk-cg","总部配件新料采购入库");
		FITTING_TITLE_MAP.put("zb-n-rk-csh","总部配件新料初始化入库");
		FITTING_TITLE_MAP.put("zb-n-rk-ll","总部配件新料领料入库");
		FITTING_TITLE_MAP.put("zb-n-rk-py","总部配件新料盘盈入库");
		FITTING_TITLE_MAP.put("zb-n-rk-qt","总部配件新料其他入库");
		FITTING_TITLE_MAP.put("zb-n-rk-xf","总部配件新料修复入库");
		FITTING_TITLE_MAP.put("zb-o-rk-csh","总部配件旧料初始化入库");
		FITTING_TITLE_MAP.put("zb-o-rk-py","总部配件旧料盘盈入库");
		FITTING_TITLE_MAP.put("zb-o-rk-qt","总部配件旧料其他入库");
		*/
		FITTING_TITLE_MAP.put("zb-n-cg","总部配件新料采购");
		FITTING_TITLE_MAP.put("fb-n-db","分部配件新料调拨");
		FITTING_TITLE_MAP.put("fb-n-cg","分部配件新料采购");
		FITTING_TITLE_MAP.put("fb-n-sq","分部配件新料申请");
		FITTING_TITLE_MAP.put("fb-o-bf","分部配件旧料报废");
		FITTING_TITLE_MAP.put("fb-kc-zy","分部库存转移");
		FITTING_TITLE_MAP.put("wd-n-sq","网点配件新料申请");
		FITTING_TITLE_MAP.put("wd-n-th","网点配件新料退回");
		FITTING_TITLE_MAP.put("wd-o-fh","网点配件旧料返回");
		FITTING_TITLE_MAP.put("wd-ll-bl","网点配件来料不良");
		FITTING_TITLE_MAP.put("wd-ll-bs","网点配件来料报失");
		FITTING_TITLE_MAP.put("wd-bw-xs","网点配件保外销售");
		
		JBPMWORKENTITYSTATUS_MAP.put("S0", "流程结束");
		JBPMWORKENTITYSTATUS_MAP.put("S1", "暂存");
		JBPMWORKENTITYSTATUS_MAP.put("S2", "提交");
		JBPMWORKENTITYSTATUS_MAP.put("S3", "分部审核");
		JBPMWORKENTITYSTATUS_MAP.put("S4", "总部审核");
		JBPMWORKENTITYSTATUS_MAP.put("S5", "填写出库数量");
		JBPMWORKENTITYSTATUS_MAP.put("S6", "出库");
		JBPMWORKENTITYSTATUS_MAP.put("S7", "邮包发货");
		JBPMWORKENTITYSTATUS_MAP.put("S8", "邮包收货");
		JBPMWORKENTITYSTATUS_MAP.put("S9", "网店确认");

		JBPMWORKENTITYSTATUS_MAP.put("S11", "发货");
		JBPMWORKENTITYSTATUS_MAP.put("S12", "收货");
		JBPMWORKENTITYSTATUS_MAP.put("S13", "检测");
		JBPMWORKENTITYSTATUS_MAP.put("S14", "入库");
		JBPMWORKENTITYSTATUS_MAP.put("S15", "移库");
	}
	//用户session
	public static final String USER_INFO = "user";
	//用户登录方式
	public static final String GOME_USER = "1";
	public static final String THIRD_NETWORK_USER = "2";
	public static final String SYS_DEFINE_USER = "3";
	
	// 工作流菜单
	public static final String WORK_LIST_TYPE_VIEW="-1";
	public static final String WORK_LIST_TYPE="0";
	public static final String WORK_ING_TYPE="1";
	public static final String WORK_DONE_TYPE="2";
	public static final String WORK_MONITORING_TYPE="3";
	
	//=============不要修改以下配置=======================
	//总部，分部，网点
	public static final String ZB = "1";
	public static final String FB = "2";
	public static final String WD = "3";
	
	public static final String FITTING_HXZB = "Z00006";
	public static final String ORGANIZATION_HXZB = "GMZB";
	
	public static final Integer NEW = 1;
	public static final Integer OLD = 0;
	public static final String DEFAULT_MOVE_DIRECTION = "9";
	
	//=============打印类型=======================
	public static final String PRINT_TYPE_RETURNMACHINE="1";  // 退换机
	public static final String PRINT_TYPE_PARCELTRANSCEIVER="2"; // 邮包收发货
	public static final String PRINT_TYPE_MATERIALMANAGE="3"; // 配件申请
	public static final String PRINT_TYPE_INOUTSTOCK="4"; // 出入库申请

}
