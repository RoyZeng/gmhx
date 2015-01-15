package com.gome.gmhx.webservice.wsdl;

import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.gome.gmhx.entity.EccBrand;
import com.gome.gmhx.entity.EccGoods;
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.EccRegional;
import com.gome.gmhx.entity.EccRegionalSection;
import com.gome.gmhx.entity.EccSalesOrg;
import com.gome.gmhx.entity.EccSectionMapping;
import com.gome.gmhx.entity.EccStore;
import com.gome.gmhx.entity.ValidateBarCode;

@WebService  
@SOAPBinding(style = Style.DOCUMENT) 
public interface EccWService {
	void handleEccBrand(List<EccBrand> list);
	
	void handleEccGoods(List<EccGoods> list);
	
	void handleEccGoodsCategory(List<EccGoodsCategory> list);
	
	void handleEccRegional(List<EccRegional> list);
	
	void handleEccRegionalSection(List<EccRegionalSection> list);
	
	void handleEccSalesOrg(List<EccSalesOrg> list);
	
	void handleEccSectionMapping(List<EccSectionMapping> list);
	
	void handleEccStore(List<EccStore> list);
	
}
