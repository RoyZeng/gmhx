package com.gome.gmhx.service.common.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.gome.common.util.DateUtils;
import com.gome.gmhx.dao.basicdata.HxBarCodeDao;
import com.gome.gmhx.dao.basicdata.HxBarCodeRulesDao;
import com.gome.gmhx.dao.basicdata.HxCodeBarDao;
import com.gome.gmhx.dao.basicdata.HxProductDetailDao;
import com.gome.gmhx.dao.hxhistory.HxHistoryData;
import com.gome.gmhx.dao.servicemanage.HxServiceCustomerDao;
import com.gome.gmhx.dao.servicemanage.HxServicePartsInfoDao;
import com.gome.gmhx.dao.servicemanage.HxServiceProductDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTroubleInfoDao;
import com.gome.gmhx.dao.storeinfo.HxCurrentStockDao;
import com.gome.gmhx.dao.wsdl.EccDao;
import com.gome.gmhx.entity.BarCode;
import com.gome.gmhx.entity.HxBarCodeRules;
import com.gome.gmhx.entity.HxCodeBar;
import com.gome.gmhx.entity.HxCurrentStock;
import com.gome.gmhx.entity.HxProductDetail;
import com.gome.gmhx.entity.HxServiceCustomer;
import com.gome.gmhx.entity.HxServicePartsInfo;
import com.gome.gmhx.entity.HxServiceProduct;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.JlSale;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.ValidateResult;
import com.gome.gmhx.jbpm.CurrentSysUser;
import com.gome.gmhx.service.common.MachineReviewService;
import com.gome.gmhx.service.servicemanage.HxServiceTicketQueryService;
import com.gome.gmhx.service.wsdl.service.JlSaleService;
@Service("machineReviewService")
public class MachineReviewServiceImpl implements MachineReviewService {
	
	private static Map<String,String> config;
	
	static{
		config = new HashMap<String,String>();
		config.put("#N-5","\\d{5}");
		config.put("#YYYYMM","\\d{6}");
	}
	
	@Resource
	private EccDao eccDao;
	@Resource
	private HxProductDetailDao hxProductDetailDao;
	@Resource
	private HxBarCodeRulesDao hxBarCodeRulesDao;
	@Resource
	private HxCodeBarDao hxCodeBarDao;
	@Resource
	private HxBarCodeDao hxBarCodeDao;
	@Resource
	private HxServiceProductDao hxServiceProductDao;
	@Resource
	private JlSaleService jlSaleService;
	@Resource
	private HxServiceTicketQueryService hxServiceTicketQueryService;
	@Resource
	private HxServiceCustomerDao hxServiceCustomerDao;
	@Resource
	HxServiceTroubleInfoDao hxServiceTroubleInfoDao;
	@Resource
	HxServicePartsInfoDao hxServicePartsInfoDao;
	@Resource
	HxServiceTicketDao hxServiceTicketDao;
	@Resource
	HxCurrentStockDao hxCurrentStockDao;
	@Resource(name="hxHistoryDataImpl")
	HxHistoryData hxHistoryData;
	
	@Override
	public String machineRecevie(String serviceId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String regularConvert(String expr){
		if(config.get(expr)!=null){
			return config.get(expr);
		}
		return expr;
	}

	/**
	 * 如果是整机就返回内外机机型，如果是内机或外机就返回内机或外机。
	 */
	@Override
	public Map<String, String> splitMachineCode(String category,String MachineCode,String brand) {
		Map<String, String> map = new HashMap<String, String>();
		String insideCode = "";
		String outsideCode = "";
		if(category.startsWith("R03")){
			if("00011".equals(brand)){
				// 整机
				if(MachineCode.indexOf('-')==-1){
					insideCode = MachineCode + "-G";
					outsideCode = MachineCode + "-W";
					map.put("insideCode", insideCode);
					map.put("outsideCode", outsideCode);
				// 内机、外机	
				}else if(MachineCode.indexOf("-G")!=-1){
					map.put("insideCode", MachineCode);
				}else if(MachineCode.indexOf("-W")!=-1){
					map.put("outsideCode", MachineCode);
				}else{
					map.put("insideCode", MachineCode);
				}
			}else if("00018".equals(brand)){
				if(MachineCode.indexOf("GW/")!=-1){
					insideCode = MachineCode.replaceFirst("GW/", "G/");
					outsideCode = MachineCode.replaceAll("GW/", "W/");
					map.put("insideCode", insideCode);
					map.put("outsideCode", outsideCode);
				}else if(MachineCode.indexOf("G/")!=-1){
					map.put("insideCode", MachineCode);
				}else if(MachineCode.indexOf("W/")!=-1){
					map.put("outsideCode", MachineCode);
				}
			}
		}else{
			map.put("insideCode", MachineCode);
		}
		return map;
	}
	
	@Override
	public String mergeMachineCode(String category,String brand,String machineCode){
		if(category.startsWith("R03")){
			if("00011".equals(brand)){
				// 整机
				if(machineCode.indexOf('-')==-1){
					return machineCode;
				// 内机、外机	
				}else if(machineCode.indexOf("-G")!=-1){
					return machineCode.replace("-G", "");
				}else if(machineCode.indexOf("-W")!=-1){
					return machineCode.replace("-W", "");
				}else{
					return machineCode;
				}
			}else if("00018".equals(brand)){
				if(machineCode.indexOf("GW/")!=-1){
					return machineCode;
				}else if(machineCode.indexOf("G/")!=-1){
					return machineCode.replace("G/", "GW/");
				}else if(machineCode.indexOf("W/")!=-1){
					return machineCode.replace("W/", "GW/");
				}
			}
		}else{
			return machineCode;
		}
		return machineCode;
	}
	

	//恒信填单机器审核
	@Override
	public String hxServiceBillMachineReview(String machineModeCode,String barCode,String hxNum,HxServiceProduct serviceProduct) {
		StringBuffer message = new StringBuffer();
		//校验销售数据
		if(serviceProduct!=null){
			String message2 = this.velidateSalesData(serviceProduct);
			if(!StringUtils.isEmpty(message2)){
				message.append(message2);
			} 
		}
		//校验该条码金力/crm是否锁定
		String message3 = this.velidateJlAndCrmTempBarCodeStore(barCode);
		if(!StringUtils.isEmpty(message3)) {
			message.append("该条码已锁定！");
		}
		//校验条码库，并保存条码到临时库中
		String message4 = this.velidateBarCodeExist(machineModeCode, barCode);
		if(!StringUtils.isEmpty(message4)){
			Map param = new HashMap();
			param.put("hxNum", hxNum);
			param.put("createTime", new Date());
			param.put("updateTime", new Date());
			param.put("jlNum", "");
			param.put("barCode", barCode);
			param.put("isSubmit", "1");
			this.hxBarCodeDao.addTempBarCode(param);
		}
		return message.toString();
	}


	//金力条码校验接口使用
	@Override
	public String JlOrCrmServiceBillOnceMachineReview(String machineModeCode,
			String barCode, String jlNum) {
		//校验条码是否已使用
		String message1 = this.velidateBarCodeIsUsed(barCode);
		if(!StringUtils.isEmpty(message1)) return message1;
		
		//校验条码规则
		String message2 = this.velidateBarCodeRule(machineModeCode, barCode);
		if(!StringUtils.isEmpty(message2)) return message2;
		
		//校验该条码是否使用过 
		String message3 = this.velidateJlAndCrmTempBarCodeStore(barCode);
		if(!StringUtils.isEmpty(message3)&&!jlNum.equals(message3)) return "条码已锁定！";
		
		//条码未被绑定
		if(StringUtils.isEmpty(message3)){
			Map param = new HashMap();
			param.put("barCode", barCode);
			param.put("jlNum", jlNum);
			param.put("hxNum", "");
			param.put("isSubmit", "0");
			param.put("createTime", new Date());
			param.put("updateTime", new Date());
			this.hxBarCodeDao.addTempBarCode(param);
		}
		return null;
	}
	
	/**
	 * @Title: JlOrCrmServiceBillTwiceMachineReview
	 * @Description: 校验该条码是否调用过校验接口
	 * @param @param machineModeCode
	 * @param @param barCode
	 * @param @param serviceProduct
	 * @param @param jlNum
	 * @param @param hxNum
	 * @param @return 设定文件
 	 * @return String 返回类型
	 * @throws
	 */
	@Override
	public String JlOrCrmServiceBillTwiceMachineReview(String machineModeCode,String barCode,HxServiceProduct serviceProduct,String jlNum,String hxNum) {
		StringBuffer message = new StringBuffer();
		String message3 = this.velidateJlAndCrmTempBarCodeStore(barCode);
		if(!StringUtils.isEmpty(message3)&&message3.equals(jlNum)){
			Map param = new HashMap();
			param.put("barCode",barCode);
			param.put("hxNum", hxNum);
			param.put("updateTime", new Date());
			this.hxBarCodeDao.setIsSubmit(param);
		}else{
			message.append("该数据未调用条码校验接口！");
		}
		if(serviceProduct!=null){
			String message4 = this.velidateSalesData(serviceProduct);
			if(!StringUtils.isEmpty(message4)){
				message.append(message4);
			}
		}
		return message.toString();
	}
	
	/**
	* @Title: velidateBarCodeRule
	* @Description: 校验机型条码对照表和条码规则配置是否符合
	* @param @param machineModeCode
	* @param @param barCode
	* @param @return 设定文件
	* @return String 返回类型
	* @throws
	*/
	public String velidateBarCodeRule(String machineModeCode,String barCode){
		/*
		 * 1.根据机型代码查询国美代码
		 * 2.根据机型代码在条形码对照表中获取该机型的条形码校验规则并进行校验。
		 * 3.根据国美代码在条形码规则配置表中查询条形码校验规则并进行校验
		 * 
		 * 1.机型代码中含有W/的为三洋空调外机，匹配外机条码
		 * 2.机型代码中含有G/的为三洋空调内机，匹配内机条码
		 * 3.机型代码中含有-W的为伊莱克斯空调外机，匹配外机条码
		 * 4.机型代码中含有-G的为伊莱克斯空调内机，匹配内机条码
		 * 5.其他为伊莱克斯热水器 匹配内机条码
		 * */
		
		StringBuffer message = new StringBuffer();
		try{
			String barCodeType = null;
			if(machineModeCode.indexOf("W/")>-1||machineModeCode.indexOf("-W")>-1){
				barCodeType="W";
			}else if(machineModeCode.indexOf("G/")>-1||machineModeCode.indexOf("-G")>-1){
				barCodeType="G";
			}else{
				barCodeType="G";
			}
			//条形码对照校验  
			List<HxCodeBar> hxCodeBars  = this.hxCodeBarDao.getHxCodeBarByMachineCode(machineModeCode.replaceAll("[\\u4E00-\\u9FA5]+",""));
			if(hxCodeBars.isEmpty()){
				message.append("该机型条形码对照配置信息不存在！");
				return message.toString();
			}
			
			for(int i=0;i<hxCodeBars.size();i++){
				HxCodeBar hxCodeBar = hxCodeBars.get(i);
				String systemBarCode = null;
				if(StringUtils.isNotEmpty(hxCodeBar.getInnerCode1().trim())&&barCodeType.equals("G")){
					systemBarCode = hxCodeBar.getInnerCode1().trim();
				}else if(StringUtils.isNotEmpty(hxCodeBar.getInnerCode2().trim())&&barCodeType.equals("G")){
					systemBarCode = hxCodeBar.getInnerCode2().trim();
				}else if(StringUtils.isNotEmpty(hxCodeBar.getOuterCode().trim())&&barCodeType.equals("W")){
					systemBarCode = hxCodeBar.getOuterCode();
				}
				
				int begin = 0;
				//17-1/18-2情况
				if(hxCodeBar.getCodeBegin().indexOf("/")>-1){
					String[] codeBegins = hxCodeBar.getCodeBegin().split("/");
					for(String codeBegin:codeBegins){
						if(barCode.length()==Integer.parseInt(codeBegin.split("-")[0])){
							begin = Integer.parseInt(codeBegin.split("-")[1])-1;
							break;
						}
					}
					
				}else{
					begin = Integer.parseInt(hxCodeBar.getCodeBegin())-1;
				}
				
				if(barCode.substring(begin).matches("^("+systemBarCode.replace(";","|")+")\\w+")){
						break;
				}else{
					if(i==hxCodeBars.size()-1){
						message.append("条形码对照表匹配错误！");
						return message.toString();
					}
				}
			}

			//校验条形码规则配置
			List<HxProductDetail>  hxProductDetails = this.hxProductDetailDao.getHxProductDetailByMode(machineModeCode);
			if(hxProductDetails.isEmpty()){
				message.append("该机型产品资料配置信息不存在！");
				return message.toString();
			}
			HxProductDetail hxProductDetail = hxProductDetails.get(0);
			String gomeCode = hxProductDetail.getGomeCode();
			List<HxBarCodeRules> hxBarCodeRules = this.hxBarCodeRulesDao.getHxBarCodeRulesByGomeCode(gomeCode);
			
			if(hxProductDetails.isEmpty()){
				message.append("该机型条形码规则配置信息不存在！");
				return message.toString();
			}
			
			for(int i=0;i<hxBarCodeRules.size();i++){
				HxBarCodeRules hxBarCodeRule = hxBarCodeRules.get(i);
				boolean flag = false;
				if(barCodeType.equals("G")){
					//校验内机位
					if(hxBarCodeRule.getInsideMachine()!=null&&StringUtils.isNotEmpty(hxBarCodeRule.getInsideMachineContent())){
						if(barCode.substring(hxBarCodeRule.getInsideMachine()-1).matches("^("+regularConvert(hxBarCodeRule.getInsideMachineContent()).replace(";","|")+")\\w+")){
							flag =true;
						}
					}
					if(hxBarCodeRule.getInsideMachineOne()!=null&&StringUtils.isNotEmpty(hxBarCodeRule.getInsideMachineContentOne())){
						//校验内机1
						if(barCode.substring(hxBarCodeRule.getInsideMachineOne()-1).matches("^("+regularConvert(hxBarCodeRule.getInsideMachineContentOne()).replace(";","|")+")\\w+")){
							flag =true;
						}
					}
					if(hxBarCodeRule.getInsideMachineTwo()!=null&&StringUtils.isNotEmpty(hxBarCodeRule.getInsideMachineContentTwo())){
						//校验内机2
						if(barCode.substring(hxBarCodeRule.getInsideMachineTwo()-1).matches("^("+regularConvert(hxBarCodeRule.getInsideMachineContentTwo()).replace(";","|")+")\\w+")){
							flag =true;
						}
					}
				}else if(barCodeType.equals("W")){
					//校验外机位
					if(hxBarCodeRule.getOutsideMachine()!=null&&StringUtils.isNotEmpty(hxBarCodeRule.getOutsideMachineContent())){
						if(barCode.substring(hxBarCodeRule.getOutsideMachine()-1).matches("^("+regularConvert(hxBarCodeRule.getOutsideMachineContent()).replace(";","|")+")\\w+")){
							flag =true;
						}
					}
					if(hxBarCodeRule.getOutsideMachineOne()!=null&&hxBarCodeRule.getOutsideMachineContentOne()!=null&&!"".equals(hxBarCodeRule.getOutsideMachineContentOne())){
						//校验内机1
						if(barCode.substring(hxBarCodeRule.getOutsideMachineOne()-1).matches("^("+regularConvert(hxBarCodeRule.getOutsideMachineContentOne()).replace(";","|")+")\\w+")){
							flag =true;
						}
					}
					if(hxBarCodeRule.getOutsideMachineTwo()!=null&&hxBarCodeRule.getOutsideMachineContentTwo()!=null&&!"".equals(hxBarCodeRule.getOutsideMachineContentTwo())){
						//校验内机2
						if(barCode.substring(hxBarCodeRule.getOutsideMachineTwo()-1).matches("^("+regularConvert(hxBarCodeRule.getOutsideMachineContentTwo()).replace(";","|")+")\\w+")){
							flag =true;
						}
					}
				}
				
				if(barCode.length()!=hxBarCodeRule.getBarCodeNumber()){
					flag = false;
				}
				
				if(!flag&&i==hxBarCodeRules.size()-1){
					message.append("机型条形码规则配置中未通过!");
				}else if(flag){
					break;
				}
				
			}
		}catch(Exception e){
			message.append("条形码校验异常");
			e.printStackTrace();
		}
		return message.toString();
		
	}
	
	/**
	* @Title: velidateBarCodeIsUsed
	* @Description: 校验条码是否在安装回执、维修回执、退换机中已使用
	* @param @param barCode
	* @param @return 设定文件
	* @return String 返回类型
	* @throws
	*/
	public String velidateBarCodeIsUsed(String barCode){
		StringBuffer message = new StringBuffer();
		List serviceProducts = this.hxServiceProductDao.getHxServiceProductByBarCode(barCode);
		if(serviceProducts.size()!=0){
			message.append("该条码已经使用！");
			return message.toString();
		}
		/*List historyTicketId = hxHistoryData.getHistoryTicketByBarCode(barCode);
		if(historyTicketId.size()!=0){
			message.append("该条码已经在旧系统中使用！");
			return message.toString();
		}*/
		return message.toString();
	}
	
	/**
	* @Title: velidateBarCodeIsUsed
	* @Description: 校验条码是否在安装回执、维修回执、退换机中已使用
	* @param @param barCode
	* @param @return 设定文件
	* @return String 返回类型
	* @throws
	*/
	public String velidateBarCodeIsUsed(String barCode,String customerId){
		StringBuffer message = new StringBuffer();
		List<Map> serviceProducts = this.hxServiceProductDao.getHxServiceProductByBarCode(barCode);
		if(serviceProducts.size()!=0){
			for(Map map : serviceProducts){
				if(!StringUtils.equals(map.get("id").toString(), customerId)){
					message.append("该条码已经使用！");
					return message.toString();
				}
			}
		}
		return message.toString();
	}
	
	/**
	* @Title: velidateBarCodeExist
	* @Description: 校验条码库是否存在该条码
	* @param @param machineModeCode
	* @param @param barCode
	* @param @return 设定文件
	* @return String 返回类型
	* @throws
	*/
	private String  velidateBarCodeExist(String machineModeCode,String barCode){
		StringBuffer message = new StringBuffer();
		BarCode barcode = new BarCode();
		barcode.setBarCode(barCode);
		barcode.setMachineType(machineModeCode);
		List barcodes = this.hxBarCodeDao.getBarCodeByMachineTypeAndBarCode(barcode);
		if(barcodes==null||barcodes.size()==0){
			message.append("该条码在条码库不存在！");
			return message.toString();
		}
		return message.toString();
	}
	
	/**
	* @Title: velidateJlAndCrmTempBarCodeStaore
	* @Description: 校验金力和crm的临时条码库，如果条码使用过就返回服务单的金力单号
	* @param @param machineModeCode
	* @param @param barCode
	* @param @return 设定文件
	* @return String 返回类型
	* @throws
	*/
	private String  velidateJlAndCrmTempBarCodeStore(String barCode){
		StringBuffer message = new StringBuffer();
		List<Map> data = this.hxBarCodeDao.getTempBarCode(barCode);
		if(!data.isEmpty()){
			return (String)data.get(0).get("jlNum");
		}
		return message.toString();
	}
	
	/**
	* @Title: velidateSalesData
	* @Description: 销售信息是否存在，且时间是否正确
	* @param @param machineModeCode
	* @param @param barCode
	* @param @return 设定文件
	* @return String 返回类型
	* @throws
	*/
	private String  velidateSalesData(HxServiceProduct serviceProduct){
		// 1、销售数据存在
		// 2、销售内容与安装内容相符(时间)
		StringBuffer message = new StringBuffer();
		JlSale saleProduct = jlSaleService.selectSaleByPrimaryKey(serviceProduct.getSaleCenter(),serviceProduct.getDeliveryOrderNum().toString());
		if(saleProduct==null) return message.append("没有相关销售记录！").toString();
		if(!DateUtils.formatDateTime(saleProduct.getJzrq(), "yyyy-MM-dd").equals(DateUtils.formatDateTime(serviceProduct.getBuyDate(), "yyyy-MM-dd")))
			return message.append("商品的购买日期与销售信息不相符！").toString();
		return null;
	}

	/** 
	* @Title: hxRepairBillMachineReview 
	* @Description: 恒信维修回执机审
	* @param 
	* @return 
	* @throws 
	*/
	@Override
	public String hxRepairBillMachineReview(String key) {
		/**
		 *  没录安装卡的显示：维修单对应的安装单不存在或维修信息与安装单不一致，维修日期早于安装日期
		 *  重复维修这几种情况都是机审不通过的
		 *  更换配件与机型不符：更换配件与维修单机型不符,
		 *  库存件不足、(网点的库存必须充足)
		 *  保外维修、
		 */
		StringBuffer message = new StringBuffer();
		String serviceId = key;
		HxServiceTicket serviceTicket = hxServiceTicketQueryService.getServiceTicketByServiceId(serviceId);
		HxServiceProduct serviceProduct = hxServiceProductDao.getServiceProductByPrimaryKey(serviceTicket.getProductId());
		HxServiceCustomer serviceCustomer = hxServiceCustomerDao.getCustomerById(serviceTicket.getCustomerId());
		
		List<Map<String, Object>> troubles = hxServiceTroubleInfoDao.selectTroublesByPrimaryKey(serviceId);
		List<HxServicePartsInfo> parts = hxServicePartsInfoDao.selectPartsById(serviceId);
		
		SysUser user = CurrentSysUser.getCurrentSysUser();
		
		String category = serviceProduct.getCategory();
		String machineType = serviceProduct.getMachineType();
		String internalCode1 = serviceProduct.getInternalMachineCode1();
		String internalCode2 = serviceProduct.getInternalMachineCode2();
		String externalCode = serviceProduct.getExternalMachineCode();
		String machineCode = serviceProduct.getMachineCode();
		
		// 验证条码规则
		/*String message0 = this.validateCodeRule(serviceProduct);
		if(!StringUtils.isEmpty(message0)) return message0;*/
		
		// 验证维修回执是否与安装回执匹配
		String message1 = this.validateInstallInfo(key,serviceProduct);
		if(!StringUtils.isEmpty(message1)){
			message.append(message1);
		}
		
		// 验证是否重复录单（一个月内重复维修两次以上的，属于重复维修）
		String message2 = this.validateRePairDuplicate(key,serviceProduct);
		if(StringUtils.isEmpty(message2)) message.append(message2);
		
		// 验证机型与维修单配件是否相符
		String message3 = this.validatePartsCorrectness(machineType,parts);
		if(StringUtils.isEmpty(message3)) message.append(message3);
		
		// 验证但钱库存数量是否充足,以及配件的更换是否在保修期内
		String message4 = this.validateIsStockEnough(user,parts,serviceProduct);
		if(StringUtils.isEmpty(message4))message.append(message4);
		
		return message.toString();
	}
	
	private String validateCodeRule(HxServiceProduct serviceProduct){
		String machineType = serviceProduct.getMachineType();
		
		String category = serviceProduct.getCategory();
		String mode = serviceProduct.getMode();
		String message=null;
		if(category.startsWith("R03")||category.startsWith("r03")){
			if(!serviceProduct.getInternalMachineCode1().equals("")&&mode!="03"){
				String messageInter1 = this.velidateBarCodeRule(machineType,serviceProduct.getInternalMachineCode1());
				if(messageInter1!=null&&!messageInter1.equals(""))
					message = "内机条码1不符合条码规则，请修改后重试！";
			}else{
				message = "内机条码1不能为空！"; 
			}
			
			if(!serviceProduct.getInternalMachineCode2().equals("")){
				String messageInter2 = this.velidateBarCodeRule(machineType,serviceProduct.getInternalMachineCode2());
				if(messageInter2!=null&&!messageInter2.equals(""))
					message = "内机条码2不符合条码规则，请修改后重试！";
			}
			
			if(!serviceProduct.getExternalMachineCode().equals("")&&mode!="02"){
				String messageExter = this.velidateBarCodeRule(machineType,serviceProduct.getExternalMachineCode());
				if(messageExter!=null&&!messageExter.equals(""))
					message = "外机条码不符合条码规则，请修改后重试！";
			}else{
				message = "外机条码不能为空！"; 
			}
		
		}else{
			String messageMachine = this.velidateBarCodeRule(machineType,serviceProduct.getMachineCode());
			if(messageMachine!=null&&messageMachine!="")
				message = "机器条码不符合条码规则，请修改后重试！";
		}
		return message;
	}
	
	/**
	 * 验证维修回执是否与安装回执匹配
	 * @param key
	 * @param serviceProduct
	 * @return
	 */
	private String validateInstallInfo(String key,HxServiceProduct serviceProduct){
		// 查询安装回执信息
		HxServiceProduct installProduct = null;
		if(serviceProduct.getCategory().startsWith("R03")||serviceProduct.getCategory().startsWith("r03")){
			installProduct = hxServiceProductDao.getProductsByBarCodes(serviceProduct.getInternalMachineCode1(),serviceProduct.getInternalMachineCode2(),serviceProduct.getMachineCode());
		}else{
			installProduct = hxServiceProductDao.getProductsByBarCode(serviceProduct.getMachineCode());
		}
		
		if(installProduct==null) return "维修单对应的安装单不存在或维修信息与安装单不一致";
		
		Date installDate = installProduct.getInstallDate();
		Date repairDate = serviceProduct.getCreateTimeP();
		if(installDate!=null&&repairDate!=null){
			if(installDate.getTime()>=repairDate.getTime())
				return "维修日期早于安装日期";
			
		}else{
			return "维修日期或安装日期不能为空";
		}
		return null;
	}

	/**
	 * 验证是否重复录单（一个月内重复维修两次以上的，属于重复维修）
	 * @param key
	 * @param serviceProduct
	 * @return
	 */
	private String validateRePairDuplicate(String key,HxServiceProduct serviceProduct){
		// 获取当前月内相同产品提交的维修单数量
		Integer count = new Integer(0);
		if(serviceProduct.getCategory().startsWith("R03")||serviceProduct.getCategory().startsWith("r03")){
			String internalMachineCode1 = "";
			String internalMachineCode2 = "";
			if(StringUtils.isNotEmpty(serviceProduct.getInternalMachineCode1()))
				internalMachineCode1 = serviceProduct.getInternalMachineCode1();
			if(StringUtils.isNotEmpty(serviceProduct.getInternalMachineCode2()))
				internalMachineCode2 = serviceProduct.getInternalMachineCode2();
			if(StringUtils.isNotEmpty(internalMachineCode1)||StringUtils.isNotEmpty(internalMachineCode2)){
				count = hxServiceTicketDao.getServiceTicketCounts(internalMachineCode1,internalMachineCode2,serviceProduct.getMachineCode());
			}
		}else{
			count = hxServiceTicketDao.getServiceTicketCount(serviceProduct.getMachineCode());
		}
		if(count>2) return "重复维修";
		return null;
	}
	
	/**
	 * 验证机型与维修单配件是否相符
	 * @param machineType
	 * @param parts
	 * @return
	 */
	private String validatePartsCorrectness(String machineType,List<HxServicePartsInfo> parts){
		if(parts!=null){
			for(HxServicePartsInfo part : parts){
				List<String> fittingCodes = hxServicePartsInfoDao.selectFittingByMachineType(machineType);
				if(!fittingCodes.contains(part.getPartsCode())) return "配件"+part.getPartsCode()+"与机型不符";
			}
		}
		return null;
	}
	
	/**
	 * 验证但钱库存数量是否充足,配件是否在保修期内
	 * @param user
	 * @param parts
	 * @return
	 */
	private String validateIsStockEnough(SysUser user,List<HxServicePartsInfo> parts,HxServiceProduct serviceProduct){
		if(parts!=null){
			Calendar nowCalendar = Calendar.getInstance();
			Calendar terminateCalendar = Calendar.getInstance();
			nowCalendar.setTimeInMillis(new Date().getTime());
			terminateCalendar.setTime(serviceProduct.getInstallDate());
			
			HxCurrentStock stock = new HxCurrentStock();
			stock.setIsNew(1);
			stock.setOrgId(CurrentSysUser.getCurrentSysUser().getOrgId());
			for(HxServicePartsInfo part : parts){
				// 验证库存
				stock.setFittingCode(part.getPartsCode());
				stock = hxCurrentStockDao.getCurrentStock(stock);
				if(stock.getStock().intValue()<part.getAmount().intValue()) return "配件"+part.getPartsCode()+"不足";
				
				// 验证配件保修期限
				terminateCalendar.set(Calendar.YEAR, part.getTerm());
				int result = terminateCalendar.compareTo(nowCalendar);
				
				// 超过保修期
				if(result<0) return "配件"+part.getPartsCode()+"已经超过保修期限";
			}
		}
		return null;
	}
}