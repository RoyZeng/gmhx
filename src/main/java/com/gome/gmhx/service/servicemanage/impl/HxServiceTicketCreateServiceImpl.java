package com.gome.gmhx.service.servicemanage.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.dao.servicemanage.HxServiceCustomerDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;
import com.gome.gmhx.entity.HxServiceCustomer;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;
import com.gome.gmhx.service.servicemanage.HxServiceTicketCreateService;

@Service("hxServiceTicketCreateService")
public class HxServiceTicketCreateServiceImpl implements HxServiceTicketCreateService {

	@Resource
	HxServiceCustomerDao serviceCustomerDao;
	
	@Resource
	HxServiceTicketDao hxServiceTicketDao;
	
	@Override
	public List<Map<String, Object>> getServiceTicketPageList(Page page) {
		return hxServiceTicketDao.getServiceTicketPageList(page);
	}
	
	@Override
	public List<Map<String, Object>> getGMZBPageList(Page page) {
		return hxServiceTicketDao.getGMZBPageList(page);
	}

	
	@Override
	public String saveServiceTicket(HxServiceTicketVO serviceTicketVO,SysUser sysUser) {
		HxServiceCustomer serviceCustomer = serviceTicketVO.getServiceCustomer();
		HxServiceTicket serviceTicket = serviceTicketVO.getServiceTicket();
		// 生成服单号
		String serviceID;
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String currentDateString = format.format(new Date());
		Integer sequence = hxServiceTicketDao.getMaxSequence();
		String str = "";
		if(sequence!=null){
			if(sequence<10000){
				str = String.format("%04d", ++sequence); 
			}else{
				str = String.valueOf(++sequence);
			}
		}else{
			sequence = 1 ;
			str = String.format("%04d", sequence); 
		}
		if("01".equals(serviceTicket.getServiceType())){
			serviceID = "RP-"+currentDateString + str;
		// 安装
		}else if("02".equals(serviceTicket.getServiceType())){
			serviceID = "IN-"+currentDateString + str;
		}else{
			return null;
		}
		
		serviceCustomer.setCreateManC(sysUser.getUserAccount());
		serviceCustomer.setCreateOrganizationC(sysUser.getCompanyId());
		serviceCustomer.setCreateTimeC(new Date());
		serviceTicket.setServiceId(serviceID);
		serviceTicket.setSequence(sequence);
		serviceTicket.setCreateManS(sysUser.getUserAccount());
		serviceTicket.setCreateOrganizationS(sysUser.getCompanyId());
		serviceTicket.setCreateTimeS(new Date());
		
		if(null!=serviceCustomer.getCustomerId()&&!"".equals(serviceCustomer.getCustomerId())){
			serviceCustomerDao.updateServiceCustomer(serviceCustomer);
			serviceTicket.setCustomerId(serviceCustomer.getCustomerId());
		}else{
			String customerID = UUIDGenerator.getUUID();
			serviceCustomer.setCustomerId(customerID);
			serviceTicket.setCustomerId(customerID);
			serviceCustomerDao.insertServiceCustomer(serviceCustomer);
		}
		// 服务单状态为已录入
		serviceTicket.setServiceStatus("01");
		hxServiceTicketDao.insertServiceTicket(serviceTicket);
		return serviceID;
	}

	@Override
	public Map<String, Object> getServiceTicketById(String serviceId) {
		return hxServiceTicketDao.selectServiceTicketByPrimaryKey(serviceId);
	}

	@Override
	public void updateServiceTicket(HxServiceTicket serviceTicket) {
		hxServiceTicketDao.updateServiceTicketByPrimaryKeySelective(serviceTicket);
	}

	@Override
	public void deleteServiceTicketById(String serviceId) {
		hxServiceTicketDao.deleteServiceTicketByPrimaryKey(serviceId);
	}

	@Override
	public void updateServiceTicketToFinish(String serviceId) {
		hxServiceTicketDao.updateServiceTicketToFinish(serviceId);
	}

}



