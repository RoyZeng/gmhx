package com.gome.gmhx.service.basicdata.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.BrandDao;
import com.gome.gmhx.entity.Brand;
import com.gome.gmhx.service.basicdata.BrandService;

@Service("BrandService")
public class BrandServiceImpl implements BrandService{

	@Resource
	 private BrandDao brandDao;
	
	@Override
	public List<Map<String, Object>> getBrandPageList(Page page) {
		
		return brandDao.getBrandPageList(page);
	}

	@Override
	public void addBrand(Brand brand) {
		brandDao.addBrand(brand);
		
	}

	@Override
	public Brand getBrandById(String aa) {
		
		return brandDao.getBrandById(aa);
	}
	
	@Override
	public Brand getShowById(String id) {
		
		return brandDao.getShowById(id);
	}
	@Override
	public void updateBrand(Brand brand) {
		
		brandDao.updateBrand(brand);
		
	}

	@Override
	public List<Map<String, Object>> getBrandExport(Brand brand) {
		
		return brandDao.getBrandExport(brand);
	}

	@Override
	public List<Map> getAllBrand() {
		return this.brandDao.getAllBrand();
	}

	@Override
	public void setIsUse(Brand brand) {
		this.brandDao.setIsUse(brand);
		
	}


}
