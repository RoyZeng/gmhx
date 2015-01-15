package com.gome.gmhx.webservice.wsdl;

import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.gome.gmhx.entity.JlInstall;
import com.gome.gmhx.entity.JlAccount;
import com.gome.gmhx.entity.JlRepair;
import com.gome.gmhx.entity.JlRepairMeasures;
import com.gome.gmhx.entity.JlRepairParts;
import com.gome.gmhx.entity.ValidateBarCode;
import com.gome.gmhx.entity.ValidateResult;

@WebService  
@SOAPBinding(style = Style.DOCUMENT) 
public interface JlWService {
	public String saveInstall(List<JlInstall> installs);
	
	public String saveAccount(List<JlAccount> accounts);
	
	public String saveRepair(List<JlRepair> repairs);
	
	public String saveRepairParts(List<JlRepairParts> repairParts);
	
	public String saveRepairMeasures(List<JlRepairMeasures> repairMeasures);
	
	public ValidateResult barCodeValidate(ValidateBarCode validateBarCode);

}
