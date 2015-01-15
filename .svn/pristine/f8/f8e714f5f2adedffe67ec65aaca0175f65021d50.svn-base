package com.gome.gmhx.dao.servicemanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxServiceCustomer;

@Repository("hxServiceCustomerDao")
public interface HxServiceCustomerDao {

	public List<Map<String, Object>> getCustomerList(Page page);

	public void insertServiceCustomer(HxServiceCustomer serviceCustomer);

	public void updateServiceCustomer(HxServiceCustomer serviceCustomer);

	public HxServiceCustomer getCustomerById(String customerId);
	
	public void insertServiceCustomerList(List<HxServiceCustomer> customers);

}
