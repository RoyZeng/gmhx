package com.gome.gmhx.webservice.wsdl.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.gome.gmhx.dao.wsdl.EccDao;
import com.gome.gmhx.entity.JlAccount;
import com.gome.gmhx.entity.JlInstall;
import com.gome.gmhx.entity.JlRepair;
import com.gome.gmhx.entity.JlRepairMeasures;
import com.gome.gmhx.entity.JlRepairParts;
import com.gome.gmhx.entity.ValidateBarCode;
import com.gome.gmhx.entity.ValidateResult;
import com.gome.gmhx.service.common.MachineReviewService;
import com.gome.gmhx.service.wsdl.service.JlAccountService;
import com.gome.gmhx.service.wsdl.service.JlInstallService;
import com.gome.gmhx.service.wsdl.service.JlRepaireService;
import com.gome.gmhx.webservice.wsdl.JlWService;

@WebService  
@SOAPBinding(style = Style.DOCUMENT) 
@Component("jlWServiceBean")
public class JlWServiceImpl implements JlWService {
	
	@Resource
	private JlInstallService installService;
	
	@Resource
	private JlAccountService accountService;
	
	@Resource
	private JlRepaireService repaireService;
	
	@Resource
	private MachineReviewService machineReviewService;
	
	@Resource
	private EccDao eccDao;
	
	@Override
	public String saveInstall(List<JlInstall> installs) {
		return installService.insertInstall(installs);
	}

	@Override
	public String saveAccount(List<JlAccount> accounts) {
		for(JlAccount account:accounts){
			accountService.insertAccount(account);
		}
		return "ok";
	}

	@Override
	public String saveRepair(List<JlRepair> repairs) {
		for(JlRepair repair:repairs){
			repaireService.insertRepair(repair);
		}
		return "ok";
	}

	@Override
	public String saveRepairParts(List<JlRepairParts> repairParts) {
		for(JlRepairParts repairPart:repairParts){
			repaireService.insertRepairPart(repairPart);
		}
		return "ok";
	}

	@Override
	public String saveRepairMeasures(List<JlRepairMeasures> repairMeasures) {
		for(JlRepairMeasures repairMeasure:repairMeasures){
			repaireService.insertRepairMeasure(repairMeasure);
		}
		return "ok";
	}

	@Override
	public ValidateResult barCodeValidate(ValidateBarCode validateBarCode) {
		
		if(!StringUtils.isNotEmpty(validateBarCode.getNJTM())){
			return new ValidateResult("N");
		}
		Map data = this.eccDao.getEccGoodsByGoodsCode(validateBarCode.getSPBM());
		if(data==null){
			return new ValidateResult("N");
		}
		String machineCode =((String) data.get("GROES")).replaceAll("[\\u4E00-\\u9FA5]+",""); ;
		
		//空调R03  伊莱克斯00011  三洋00018
		if(validateBarCode.getSPFL0().startsWith("R03")){
				if(!StringUtils.isNotEmpty(validateBarCode.getYJTM())){//空调外机不允许为空
					return new ValidateResult("N");
				}
				if("00011".equals(validateBarCode.getPPB01())){
					if(StringUtils.isNotEmpty(validateBarCode.getNJTM())){
						String msg = this.machineReviewService.JlOrCrmServiceBillOnceMachineReview(machineCode+"-G", validateBarCode.getNJTM(),validateBarCode.getJLNUM());
						if(StringUtils.isNotEmpty(msg)){
							return new ValidateResult("N");
						}
					}
					if(StringUtils.isNotEmpty(validateBarCode.getYJTM())){
						String msg = this.machineReviewService.JlOrCrmServiceBillOnceMachineReview(machineCode+"-W", validateBarCode.getYJTM(),validateBarCode.getJLNUM());
						if(StringUtils.isNotEmpty(msg)){
							return new ValidateResult("N");
						}
					}
					return new ValidateResult("Y");
				}else if("00018".equals(validateBarCode.getPPB01())){
					if(StringUtils.isNotEmpty(validateBarCode.getNJTM())){
						String msg = this.machineReviewService.JlOrCrmServiceBillOnceMachineReview(machineCode.replace("/GW", "/G"), validateBarCode.getNJTM(),validateBarCode.getJLNUM());
						if(StringUtils.isNotEmpty(msg)){
							return new ValidateResult("N");
						}
					}
					if(StringUtils.isNotEmpty(validateBarCode.getYJTM())){
						String msg = this.machineReviewService.JlOrCrmServiceBillOnceMachineReview(machineCode.replace("/GW", "/W"), validateBarCode.getYJTM(),validateBarCode.getJLNUM());
						if(StringUtils.isNotEmpty(msg)){
							return new ValidateResult("N");
						}
					}
					return new ValidateResult("Y");
				}
			}else{
				if(StringUtils.isNotEmpty(validateBarCode.getNJTM())){
					String msg = this.machineReviewService.JlOrCrmServiceBillOnceMachineReview(machineCode, validateBarCode.getNJTM(),validateBarCode.getJLNUM());
					if(StringUtils.isNotEmpty(msg)){
						return new ValidateResult("N");
					}
					return  new ValidateResult("Y");
				}
			}
			return  new ValidateResult("N");
	}

}
