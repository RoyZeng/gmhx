package com.gome.gmhx.service.servicemanage.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxBarCodeRulesDao;
import com.gome.gmhx.dao.basicdata.HxCodeBarDao;
import com.gome.gmhx.dao.basicdata.HxProductDetailDao;
import com.gome.gmhx.dao.servicemanage.HxServiceRetreatReplacementDao;
import com.gome.gmhx.entity.BarCode;
import com.gome.gmhx.entity.HxBarCodeRules;
import com.gome.gmhx.entity.HxCodeBar;
import com.gome.gmhx.entity.HxProductDetail;
import com.gome.gmhx.entity.HxServiceRetreatReplacement;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.jbpm.JbpmProcessDefinations;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.service.common.MachineReviewService;
import com.gome.gmhx.service.servicemanage.HxRetreatReplacementCreateService;

@Service("hxRetreatReplacementCreateService")
public class HxRetreatReplacementCreateServiceImpl implements HxRetreatReplacementCreateService {
	@Resource
	private HxServiceRetreatReplacementDao hxRetreatReplacementCreateDao;
	
	@Resource
	private JbpmService jbpmService;
	
	@Resource
	private HxCodeBarDao hxCodeBarDao;
	
	@Resource
	private HxProductDetailDao hxProductDetailDao;
	
	@Resource
	private HxBarCodeRulesDao hxBarCodeRulesDao;
	
	@Resource
	private MachineReviewService machineReviewService;
	
	private static Map<String,String> config;
	
	static{
		config = new HashMap<String,String>();
		config.put("N-5","\\d{5}");
		config.put("YYYYMM","\\d{6}");
	}

	@Override
	public List<Map<String, Object>> getRetreatReplacementCreatePageList(Page page) {
		return hxRetreatReplacementCreateDao.getRetreatReplacementCreatePageList(page);
	}

	@Override
	public String saveRetreatReplacement(HxServiceRetreatReplacement retreatReplacement, SysUser sysUser) {
		// 验证条码
		
		String result = validateBarCode(retreatReplacement);
		if(result!=null){
			return result;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String currentDateString = format.format(new Date());
		Integer sequence = hxRetreatReplacementCreateDao.getMaxSequence();
		String str = "";
		if(sequence!=null){
			str = String.format("%04d", ++sequence); 
		}else{
			sequence = 1 ;
			str = String.format("%04d", sequence); 
		}
		String applyId = "TR-"+currentDateString + str;
		retreatReplacement.setApplyId(applyId);
		retreatReplacement.setSequence(sequence);
		retreatReplacement.setApplyUnit(sysUser.getCompanyId());
		retreatReplacement.setStatus("S1");
		retreatReplacement.setIsPassCheck(false);
		retreatReplacement.setCreateMan(sysUser.getUserAccount());
		retreatReplacement.setCreateOrganization(sysUser.getCompanyId());
		retreatReplacement.setCreateTime(new Date());
		hxRetreatReplacementCreateDao.insertRetreatReplacement(retreatReplacement);
		return "{\"flag\" : \"success\",\"message\" : \""+applyId+"\"}";
	}

	@Override
	public HxServiceRetreatReplacement getRetreatReplacementById(String applyId) {
		return hxRetreatReplacementCreateDao.selectRetreatReplacementById(applyId);
	}

	@Override
	public String updateRetreatReplacement(HxServiceRetreatReplacement retreatReplacement, SysUser sysUser) {
		String result = validateBarCode(retreatReplacement);
		if(result!=null){
			return result;
		}
		retreatReplacement.setAlterMan(sysUser.getUserAccount());
		retreatReplacement.setAlterOrganization(sysUser.getCompanyId());
		retreatReplacement.setAlterTime(new Date());
		hxRetreatReplacementCreateDao.updateRetreatReplacementById(retreatReplacement);
		return "{\"flag\" : \"success\",\"message\" : \""+retreatReplacement.getApplyId()+"\"}";
	}

	@Override
	public void deleteRetreatReplacement(String serviceId) {
		hxRetreatReplacementCreateDao.deleteServiceTicketByPrimaryKey(serviceId);
	}

	@Override
	public void sendServiceTicket(String serviceId) {
		HxServiceRetreatReplacement retreatReplacement = hxRetreatReplacementCreateDao.selectRetreatReplacementById(serviceId);
		this.jbpmService.startProcessInstanceByKey(JbpmProcessDefinations.returnMachineApply, retreatReplacement);
		hxRetreatReplacementCreateDao.updateRetreatReplacementById(retreatReplacement);
	}
	
	public String validate(String machineModeCode, String barCode){
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
			List<HxCodeBar> hxCodeBars  = this.hxCodeBarDao.getHxCodeBarByMachineCode(machineModeCode);
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
	
	private String regularConvert(String expr){
		if(config.get(expr)!=null){
			return config.get(expr);
		}
		return expr;
	}
	
	private String validateBarCode(HxServiceRetreatReplacement retreatReplacement){
		return null;
	}
/*		String oldMachineType = retreatReplacement.getOldMachineType();
		if(StringUtils.isNotEmpty(oldMachineType)){
			if(retreatReplacement.getOldMachineCode()!=null&&retreatReplacement.getOldMachineCode()!=""){
				String oldMachineCode = validate(oldMachineType,retreatReplacement.getOldMachineCode());
				if(oldMachineCode!=null)
					return "{\"flag\" : \"failure\",\"message\" : \""+oldMachineCode+"\"}";
			}
			if(retreatReplacement.getOldInnerCode1()!=null&&retreatReplacement.getOldInnerCode1()!=""){
				String oldInnerCode1 = validate(oldMachineType,retreatReplacement.getOldInnerCode1());
				if(oldInnerCode1!=null)
					return "{\"flag\" : \"failure\",\"message\" : \""+oldInnerCode1+"\"}";
			}
			if(retreatReplacement.getOldInnerCode2()!=null&&retreatReplacement.getOldInnerCode2()!=""){
				String oldInnerCode2= validate(oldMachineType,retreatReplacement.getOldInnerCode2());
				if(oldInnerCode2!=null)
					return "{\"flag\" : \"failure\",\"message\" : \""+oldInnerCode2+"\"}";
			}
			if(retreatReplacement.getOldOuterCode()!=null&&retreatReplacement.getOldOuterCode()!=""){
				String oldOuterCode = validate(oldMachineType,retreatReplacement.getOldOuterCode());
				if(oldOuterCode!=null)
					return "{\"flag\" : \"failure\",\"message\" : \""+oldOuterCode+"\"}";
			}
		}else{
			return "原机型不能为空！";
		}
		
		String machineType = retreatReplacement.getOldMachineType();
		if(StringUtils.isNotEmpty(machineType)){
			if(retreatReplacement.getMachineCode()!=null&&retreatReplacement.getMachineCode()!=""){
				String  machineCode= validate(machineType,retreatReplacement.getMachineCode());
				if(machineCode!=null)
					return "{\"flag\" : \"failure\",\"message\" : \""+machineCode+"\"}";
			}
			if(retreatReplacement.getInnerCode1()!=null&&retreatReplacement.getInnerCode1()!=""){
				String innerCode1 = validate(machineType,retreatReplacement.getInnerCode1());
				if(innerCode1!=null)
					return "{\"flag\" : \"failure\",\"message\" : \""+innerCode1+"\"}";
			}
			if(retreatReplacement.getInnerCode2()!=null&&retreatReplacement.getInnerCode2()!=""){
				String  innerCode2= validate(machineType,retreatReplacement.getInnerCode2());
				if(innerCode2!=null)
					return "{\"flag\" : \"failure\",\"message\" : \""+innerCode2+"\"}";
			}
			if(retreatReplacement.getOuterCode()!=null&&retreatReplacement.getOuterCode()!=""){
				String  outerCode= validate(machineType,retreatReplacement.getOuterCode());
				if(outerCode!=null)
					return "{\"flag\" : \"failure\",\"message\" : \""+outerCode+"\"}";
			}
		}else{
			return "现机型不能为空！";
		}
		return null;
	}*/
}
