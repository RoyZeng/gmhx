package com.gome.gmhx.webservice.wsdl;

import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.gome.gmhx.entity.CrmWebsite;
import com.gome.gmhx.entity.CustomerserviceSaleMapping;
import com.gome.gmhx.entity.ValidateBarCode;
import com.gome.gmhx.entity.ValidateResult;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface CrmWebsiteWService {
	public void handleCrmwebsite(List<CrmWebsite> list);
	
	public void handleCustomerserviceSaleMapping(List<CustomerserviceSaleMapping> list);
	
	public String handleResetPassword(String xmlData);
	
	public ValidateResult barCodeValidate(ValidateBarCode validateBarCode);
}
