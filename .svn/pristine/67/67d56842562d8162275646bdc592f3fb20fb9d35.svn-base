package com.gome.gmhx.webservice.wsdl.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.stereotype.Component;

import com.gome.gmhx.entity.EccBrand;
import com.gome.gmhx.entity.EccGoods;
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.EccRegional;
import com.gome.gmhx.entity.EccRegionalSection;
import com.gome.gmhx.entity.EccSalesOrg;
import com.gome.gmhx.entity.EccSectionMapping;
import com.gome.gmhx.entity.EccStore;
import com.gome.gmhx.entity.ValidateBarCode;
import com.gome.gmhx.service.wsdl.service.EccService;
import com.gome.gmhx.webservice.wsdl.EccWService;

@WebService  
@SOAPBinding(style = Style.DOCUMENT) 
@Component("eccWServiceBean")
public class EccWServiceImpl implements EccWService{
	@Resource
	private EccService eccService;

	@Override
	public void handleEccBrand(List<EccBrand> list) {
		eccService.handleEccBrand(list);
	}

	@Override
	public void handleEccGoods(List<EccGoods> list) {
		eccService.handleEccGoods(list);
	}

	@Override
	public void handleEccGoodsCategory(List<EccGoodsCategory> list) {
		eccService.handleEccGoodsCategory(list);
	}

	@Override
	public void handleEccRegional(List<EccRegional> list) {
		eccService.handleEccRegional(list);
	}

	@Override
	public void handleEccRegionalSection(List<EccRegionalSection> list) {
		eccService.handleEccRegionalSection(list);
	}

	@Override
	public void handleEccSalesOrg(List<EccSalesOrg> list) {
		eccService.handleEccSalesOrg(list);
	}

	@Override
	public void handleEccSectionMapping(List<EccSectionMapping> list) {
		eccService.handleEccSectionMapping(list);
	}

	@Override
	public void handleEccStore(List<EccStore> list) {
		eccService.handleEccStore(list);
	}
	
}
