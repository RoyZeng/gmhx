package com.gome.gmhx.service.servicemanage.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.servicemanage.HxServiceProductDao;
import com.gome.gmhx.entity.HxServiceProduct;
import com.gome.gmhx.service.servicemanage.HxProductService;

@Service("hxServiceProductService")
public class HxServiceProductServiceImpl implements HxProductService{
	@Resource
	HxServiceProductDao serviceProductDao;


	@Override
	public List<Map<String, Object>> getProductSerivcePageList(Page page) {
		return serviceProductDao.getProductSerivcePageList(page);
	}

	@Override
	public void insertServiceProduct(HxServiceProduct entity) {
		serviceProductDao.insertServiceProduct(entity);
	}

	@Override
	public void updateServiceProduct(HxServiceProduct entity) {
		serviceProductDao.updateServiceProductByPrimaryKey(entity);
	}
	
}
