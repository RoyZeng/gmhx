package com.gome.gmhx.service.wsdl.service;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.EccBrand;
import com.gome.gmhx.entity.EccGoods;
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.EccRegional;
import com.gome.gmhx.entity.EccRegionalSection;
import com.gome.gmhx.entity.EccSalesOrg;
import com.gome.gmhx.entity.EccSectionMapping;
import com.gome.gmhx.entity.EccStore;
import com.gome.gmhx.entity.ValidateBarCode;

public interface EccService {
	void handleEccBrand(List<EccBrand> list);
	
	void handleEccGoods(List<EccGoods> list);
	
	void handleEccGoodsCategory(List<EccGoodsCategory> list);
	
	void handleEccRegional(List<EccRegional> list);
	
	void handleEccRegionalSection(List<EccRegionalSection> list);
	
	void handleEccSalesOrg(List<EccSalesOrg> list);
	
	void handleEccSectionMapping(List<EccSectionMapping> list);
	
	void handleEccStore(List<EccStore> list);
	
	public List<Map> getEccGoodsCategory(String classification,String upperCode);
	
	String validateHxBarCode(ValidateBarCode vebc) ;

	List<Map<String, Object>> getGoodClassPageList(Page page);

	List getFaultByCategory(String category);
}
