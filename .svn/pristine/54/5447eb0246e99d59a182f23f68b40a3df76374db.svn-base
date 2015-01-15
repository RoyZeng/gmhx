package com.gome.gmhx.service.wsdl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.BrandDao;
import com.gome.gmhx.dao.basicdata.HxBarCodeRulesDao;
import com.gome.gmhx.dao.basicdata.HxCodeBarDao;
import com.gome.gmhx.dao.basicdata.HxProductClassifyDao;
import com.gome.gmhx.dao.basicdata.HxProductDetailDao;
import com.gome.gmhx.dao.wsdl.EccDao;
import com.gome.gmhx.entity.Brand;
import com.gome.gmhx.entity.EccBrand;
import com.gome.gmhx.entity.EccGoods;
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.EccRegional;
import com.gome.gmhx.entity.EccRegionalSection;
import com.gome.gmhx.entity.EccSalesOrg;
import com.gome.gmhx.entity.EccSectionMapping;
import com.gome.gmhx.entity.EccStore;
import com.gome.gmhx.entity.HxProductClassify;
import com.gome.gmhx.entity.ValidateBarCode;
import com.gome.gmhx.service.wsdl.service.EccService;

@Service("eccService")
public class EccServiceImpl implements EccService {
	@Resource
	private EccDao eccDao;
	@Resource
	private HxProductDetailDao hxProductDetailDao;
	@Resource
	private HxBarCodeRulesDao hxBarCodeRulesDao;
	@Resource
	private BrandDao brandDao;
	@Resource
	private HxProductClassifyDao hxProductClassifyDao;
	
	private HxCodeBarDao hxCodeBarDao;
	
	

	@Override
	public void handleEccBrand(List<EccBrand> list) {
		//eccDao.clearEccBrand();
		//eccDao.insertEccBrand(list);
		for(EccBrand eccBrand:list){
			Brand brand = this.brandDao.getBrandById(eccBrand.getProdh());
			if(brand==null){
				brand = new Brand();
				brand.setCreater("admin");
				brand.setModifier("admin");
				brand.setMod_date(new Date());
				brand.setId(eccBrand.getProdh());
				brand.setBrand(eccBrand.getVtext());
				brand.setMod_date(new Date());
				brand.setIsUse("0");
				this.brandDao.addBrand(brand);
			}else{
				brand.setMod_date(new Date());
				brand.setIsUse("0");
				brand.setBrand(eccBrand.getVtext());
				this.brandDao.updateBrand(brand);
			}
		}
	}

	@Override
	public void handleEccGoods(List<EccGoods> list) {
		eccDao.replaceEccGoods(list);
	}

	@Override
	public void handleEccGoodsCategory(List<EccGoodsCategory> list) {
//		eccDao.clearEccGoodsCategory();
//		eccDao.insertEccGoodsCategory(list);
		for(EccGoodsCategory egc:list){
			HxProductClassify hpc = 	this.hxProductClassifyDao.getHxProductClassifyByCassifyCode(egc.getClass_());
			if(hpc==null){
				hpc = new HxProductClassify();
				hpc.setClassifyCode(egc.getClass_());
				hpc.setClassifyName(egc.getKschl());
				hpc.setParentCode(egc.getZsjfldm());
				hpc.setLevel(egc.getZfljb());
				this.hxProductClassifyDao.createHxProductClassify(hpc);
			}else{
				Map m = new HashMap();
				m.put("classifyCode", egc.getClass_());
				m.put("classifyName", egc.getKschl());
				m.put("parentCode", egc.getZsjfldm());
				hpc.setParentCode(egc.getZsjfldm());
				hpc.setLevel(egc.getZfljb());
				this.hxProductClassifyDao.updateHxProductClassify(m);
			}
		
		}
	}

	@Override
	public void handleEccRegional(List<EccRegional> list) {
		String ids="";
		for(EccRegional regional:list)
			ids+="'"+regional.getBzirk()+"',";
		if(ids.length()>0){
			ids="("+ids.substring(0,ids.length()-1)+")";
			eccDao.deleteEccRegionalByBzirks(ids);
			eccDao.insertEccRegional(list);
		}
	}

	@Override
	public void handleEccRegionalSection(List<EccRegionalSection> list) {
		String ids="";
		for(EccRegionalSection regionalSection:list)
			ids+="'"+regionalSection.getVkbur()+"',";
		if(ids.length()>0){
			ids="("+ids.substring(0,ids.length()-1)+")";
			eccDao.deleteEccRegionalSectionByVkburs(ids);
			eccDao.insertEccRegionalSection(list);
		}
	}

	@Override
	public void handleEccSalesOrg(List<EccSalesOrg> list) {
		if(list!=null && list.size()>0){
			eccDao.deleteEccSalesOrgByPrimary(list);
			eccDao.insertEccSalesOrg(list);
		}
	}

	@Override
	public void handleEccSectionMapping(List<EccSectionMapping> list) {
		String ids="";
		for(EccSectionMapping sectionMapping:list)
			ids+="'"+sectionMapping.getVkgrp()+"',";
		if(ids.length()>0){
			ids="("+ids.substring(0,ids.length()-1)+")";
			eccDao.deleteEccSectionMappingByVkgrps(ids);
			eccDao.insertEccSectionMapping(list);
		}
	}

	@Override
	public void handleEccStore(List<EccStore> list) {
		String ids="";
		for(EccStore store:list)
			ids+="'"+store.getWerks()+"',";
		if(ids.length()>0){
			ids="("+ids.substring(0,ids.length()-1)+")";
			eccDao.deleteEccStoreByWerkss(ids);
			eccDao.insertEccStore(list);
		}
	}

	@Override
	public String validateHxBarCode(ValidateBarCode vebc) {
		return null;
	}

	@Override
	public List<Map> getEccGoodsCategory(String classification, String upperCode) {
		
		Map param = new HashMap();
		param.put("upperCode", upperCode);
		if(!StringUtils.isEmpty(classification)){
			param.put("classification", classification);
		}
		return this.eccDao.getEccGoodsCategory(param);
	}

	@Override
	public List<Map<String, Object>> getGoodClassPageList(Page page) {
		return eccDao.getGoodClassPageList(page);
	}

	@Override
	public List getFaultByCategory(String category) {
		return eccDao.getFaultByCategory(category);
	}

}
