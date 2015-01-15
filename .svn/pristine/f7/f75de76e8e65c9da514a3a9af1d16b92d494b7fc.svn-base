package com.gome.gmhx.service.servicemanage.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.servicemanage.HxServiceCustomerDao;
import com.gome.gmhx.entity.HxServiceCustomer;
import com.gome.gmhx.service.servicemanage.HxServiceCustomerService;

@Service("hxServiceCustomerService")
public class HxServiceCustomerServiceImpl implements HxServiceCustomerService {
	@Resource
	HxServiceCustomerDao hxServiceCustomerDao;

	@Override
	public void insertServiceCustomer(HxServiceCustomer serviceCustomer){
		hxServiceCustomerDao.insertServiceCustomer(serviceCustomer);
	}
	
	@Override
	public void updateServiceCustomer(HxServiceCustomer serviceCustomer){
		hxServiceCustomerDao.updateServiceCustomer(serviceCustomer);
	}

	@Override
	public HxServiceCustomer getCustomerById(String customerId) {
		return hxServiceCustomerDao.getCustomerById(customerId);
	}

	@Override
	public List<Map<String, Object>> getCustomerList(Page page) {
		return hxServiceCustomerDao.getCustomerList(page);
	}
}
