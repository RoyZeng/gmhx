package com.gome.gmhx.webservice.wsdl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.gome.gmhx.dao.wsdl.EccDao;
import com.gome.gmhx.entity.CrmWebsite;
import com.gome.gmhx.entity.CustomerserviceSaleMapping;
import com.gome.gmhx.entity.ValidateBarCode;
import com.gome.gmhx.entity.ValidateResult;
import com.gome.gmhx.service.common.MachineReviewService;
import com.gome.gmhx.service.wsdl.service.CrmWebsiteService;
import com.gome.gmhx.webservice.wsdl.CrmWebsiteWService;

@WebService  
@SOAPBinding(style = Style.DOCUMENT)
@Component("crmWebsiteWServiceBean")
public class CrmWebsiteWServiceImpl implements CrmWebsiteWService {
	@Resource
	private CrmWebsiteService crmWebsiteService;
	@Resource
	private MachineReviewService machineReviewService;
	@Resource
	private EccDao eccDao;
	
	@Override
	public void handleCrmwebsite(List<CrmWebsite> list){
		this.crmWebsiteService.handleCrmwebsite(list);
	}

	@Override
	public void handleCustomerserviceSaleMapping(List<CustomerserviceSaleMapping> list) {
		this.crmWebsiteService.handleCustomerserviceSaleMapping(list);
	}

	@Override
	public String handleResetPassword(String xmlData) {
		return crmWebsiteService.insertResetPassword(xmlData);
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
