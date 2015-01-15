package com.gome.gmhx.service.servicemanage.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.dao.servicemanage.HxServiceCustomerDao;
import com.gome.gmhx.dao.servicemanage.HxServicePartsInfoDao;
import com.gome.gmhx.dao.servicemanage.HxServiceProductDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTroubleInfoDao;
import com.gome.gmhx.entity.HxServiceCustomer;
import com.gome.gmhx.entity.HxServicePartsInfo;
import com.gome.gmhx.entity.HxServiceProduct;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.HxServiceTroubleInfo;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;
import com.gome.gmhx.jbpm.JbpmProcessDefinations;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.service.common.MachineReviewService;
import com.gome.gmhx.service.servicemanage.HxRepairReceiptService;

@Service("hxRepairReceiptService")
public class HxRepairReceiptServiceImpl implements HxRepairReceiptService {

	@Resource
	HxServiceCustomerDao hxServiceCustomerDao;
	
	@Resource
	HxServiceTicketDao hxServiceTicketDao;
	
	@Resource
	HxServiceProductDao hxServiceProductDao;
	
	@Resource
	HxServiceTroubleInfoDao hxServiceTroubleInfoDao;
	
	@Resource
	HxServicePartsInfoDao hxServicePartsInfoDao;
	
	@Resource
	JbpmService jbpmService;
	
	@Resource
	MachineReviewService machineReviewService;
	
	@Override
	public String saveRepairReceipt(HxServiceTicketVO serviceTicketVO,SysUser sysUser) {
		HxServiceCustomer serviceCustomer = serviceTicketVO.getServiceCustomer();
		HxServiceProduct serviceProduct = serviceTicketVO.getServiceProduct();
		HxServiceTicket serviceTicket = serviceTicketVO.getServiceTicket();
		
		// 验证条码规则
		String machineType = serviceProduct.getMachineType();
		
		String category = serviceProduct.getCategory();
		String mode = serviceProduct.getMode();
		String message="";
		
		if(category.startsWith("R03")||category.startsWith("r03")){
			if(!serviceProduct.getInternalMachineCode1().equals("")&&!"03".equals(mode)){
				String messageInter1 = machineReviewService.velidateBarCodeRule(machineType,serviceProduct.getInternalMachineCode1());
				if(messageInter1!=null&&!messageInter1.equals(""))
					message = "内机条码1不符合条码规则，请修改后重试！";
			}else if(serviceProduct.getInternalMachineCode1().equals("")&&!"03".equals(mode)){
				message = "内机条码1不能为空！"; 
			}
			
			if(!serviceProduct.getInternalMachineCode2().equals("")){
				String messageInter2 = machineReviewService.velidateBarCodeRule(machineType,serviceProduct.getInternalMachineCode2());
				if(messageInter2!=null&&!messageInter2.equals(""))
					message = "内机条码2不符合条码规则，请修改后重试！";
			}
			
			if(!serviceProduct.getExternalMachineCode().equals("")&&!"02".equals(mode)){
				String messageExter = machineReviewService.velidateBarCodeRule(machineType,serviceProduct.getExternalMachineCode());
				if(messageExter!=null&&!messageExter.equals(""))
					message = "外机条码不符合条码规则，请修改后重试！";
			}else if(serviceProduct.getExternalMachineCode().equals("")&&!"02".equals(mode)){
				message = "外机条码不能为空！"; 
			}
		
		}else{
			String messageMachine = machineReviewService.velidateBarCodeRule(machineType,serviceProduct.getMachineCode());
			if(messageMachine!=null&&messageMachine!="")
				message = "机器条码不符合条码规则，请修改后重试！";
		}
		if(message!=""){
			return "{\"flag\" : \"falure\",\"message\" : \""+message+"\"}";
		}
		
		
		// 生成服务单号
		String productID = UUIDGenerator.getUUID();
		String serviceID = "";
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String currentDateString = format.format(new Date());
		Integer sequence = hxServiceTicketDao.getMaxSequence();
		String str = "";
		if(sequence!=null){
			if(sequence<10000){
				str = String.format("%04d", ++sequence); 
			}else{
				str =  String.valueOf(++sequence);
			}
		}else{
			sequence = 1 ;
			str = String.format("%04d", sequence); 
		}
		serviceID = "RP-"+currentDateString + str;
		// 用户
		serviceCustomer.setCreateManC(sysUser.getUserAccount());
		serviceCustomer.setCreateTimeC(new Date());
		serviceCustomer.setCreateOrganizationC(sysUser.getCompanyId());
		
		// 产品
		serviceProduct.setProductId(productID);
		serviceProduct.setCreateManP(sysUser.getUserAccount());
		serviceProduct.setCreateTimeP(new Date());
		serviceProduct.setCreateOrganizationP(sysUser.getCompanyId());
		
		// 服务单信息
		serviceTicket.setServiceId(serviceID);
		serviceTicket.setProductId(productID);
		serviceTicket.setSequence(sequence);
		serviceTicket.setServiceType("01");
		serviceTicket.setServiceStatus("S1");
		serviceTicket.setCreateManS(sysUser.getUserAccount());
		serviceTicket.setServiceUnit(serviceProduct.getInstallUnit());
		serviceTicket.setCreateTimeS(new Date());
		serviceTicket.setCreateOrganizationS(sysUser.getCompanyId());
		
		if(null!=serviceCustomer.getCustomerId()&&!"".equals(serviceCustomer.getCustomerId())){
			hxServiceCustomerDao.updateServiceCustomer(serviceCustomer);
			serviceTicket.setCustomerId(serviceCustomer.getCustomerId());
			serviceProduct.setCustomerId(serviceCustomer.getCustomerId());
		}else{
			String customerID = UUIDGenerator.getUUID();
			serviceCustomer.setCustomerId(customerID);
			serviceTicket.setCustomerId(customerID);
			serviceProduct.setCustomerId(customerID);
			hxServiceCustomerDao.insertServiceCustomer(serviceCustomer);
		}
		hxServiceProductDao.insertServiceProduct(serviceProduct);
		hxServiceTicketDao.insertServiceTicket(serviceTicket);
		// 故障配件
		List<HxServiceTroubleInfo> troubles = serviceTicketVO.getServiceTroubleInfos();
		List<HxServicePartsInfo> parts = serviceTicketVO.getServicePartsInfos();
		if(troubles != null && !troubles.isEmpty()){
			Map<String, Object> troubleMap = new HashMap<String, Object>();
			troubleMap.put("serviceId", serviceID);
			troubleMap.put("list", troubles);
			hxServiceTroubleInfoDao.insertTroubles(troubleMap);
		}
		if(parts != null && !parts.isEmpty()){
			Map<String, Object> partsMap = new HashMap<String, Object>();
			partsMap.put("serviceId", serviceID);
			partsMap.put("list", parts);
			partsMap.put("recordMan", sysUser.getUserAccount());
			partsMap.put("recordTime", new Date());
			hxServicePartsInfoDao.insertParts(partsMap);
		}
		return "{\"flag\" : \"success\",\"serviceId\" : \""+serviceID+"\"}";
	}

	@Override
	public List<Map<String, Object>> getRepairReceiptPageList(Page page) {
		return hxServiceTicketDao.getServiceTicketPageList(page);
	}

	@Override
	public Map<String, Object> getRepairReceiptById(String serviceId) {
		return hxServiceTicketDao.selectServiceTicketByPrimaryKey(serviceId);
	}

	@Override
	public List<Map<String, Object>> getTroublesByServiceId(String serviceId) {
		return hxServiceTroubleInfoDao.selectTroublesByPrimaryKey(serviceId);
	}

	@Override
	public List<Map<String, Object>> getPartsByServiceId(String serviceId) {
		return hxServicePartsInfoDao.selectPartsByPrimaryKey(serviceId);
	}

	@Override
	public String updateRepairReceipt(HxServiceTicketVO serviceTicketVO, SysUser sysUser) {
		HxServiceCustomer serviceCustomer = serviceTicketVO.getServiceCustomer();
		HxServiceProduct serviceProduct = serviceTicketVO.getServiceProduct();
		HxServiceTicket serviceTicket = serviceTicketVO.getServiceTicket();
		
		// 验证条码规则
		String machineType = serviceProduct.getMachineType();
		
		String category = serviceProduct.getCategory();
		String mode = serviceProduct.getMode();
		String message="";
		if(category.startsWith("R03")||category.startsWith("r03")){
			if(!serviceProduct.getInternalMachineCode1().equals("")&&!"03".equals(mode)){
				String messageInter1 = machineReviewService.velidateBarCodeRule(machineType,serviceProduct.getInternalMachineCode1());
				if(messageInter1!=null&&!messageInter1.equals(""))
					message = "内机条码1不符合条码规则，请修改后重试！";
			}else if(serviceProduct.getInternalMachineCode1().equals("")&&!"03".equals(mode)){
				message = "内机条码1不能为空！"; 
			}
			
			if(!serviceProduct.getInternalMachineCode2().equals("")){
				String messageInter2 = machineReviewService.velidateBarCodeRule(machineType,serviceProduct.getInternalMachineCode2());
				if(messageInter2!=null&&!messageInter2.equals(""))
					message = "内机条码2不符合条码规则，请修改后重试！";
			}
			
			if(!serviceProduct.getExternalMachineCode().equals("")&&!"02".equals(mode)){
				String messageExter = machineReviewService.velidateBarCodeRule(machineType,serviceProduct.getExternalMachineCode());
				if(messageExter!=null&&!messageExter.equals(""))
					message = "外机条码不符合条码规则，请修改后重试！";
			}else if(serviceProduct.getExternalMachineCode().equals("")&&!"02".equals(mode)){
				message = "外机条码不能为空！"; 
			}
		
		}else{
			String messageMachine = machineReviewService.velidateBarCodeRule(machineType,serviceProduct.getMachineCode());
			if(messageMachine!=null&&messageMachine!="")
				message = "机器条码不符合条码规则，请修改后重试！";
		}
		if(message!=""){
			return "{\"flag\" : \"falure\",\"message\" : \""+message+"\"}";
		}
		
		// 更新人信息
		serviceCustomer.setAlterTimeC(new Date());
		serviceCustomer.setAlterManC(sysUser.getUserAccount());
		serviceCustomer.setAlterOrganizationC(sysUser.getCompanyId());
		
		serviceProduct.setAlterTimeP(new Date());
		serviceProduct.setAlterManP(sysUser.getUserAccount());
		serviceProduct.setAlterOrganizationP(sysUser.getCompanyId());
		
		serviceTicket.setAlterTimeS(new Date());
		serviceTicket.setAlterManS(sysUser.getUserAccount());
		serviceTicket.setAlterOrganizationS(sysUser.getCompanyId());
		
		hxServiceCustomerDao.updateServiceCustomer(serviceCustomer);
		hxServiceProductDao.updateServiceProduct(serviceProduct);
		hxServiceTicketDao.updateServiceTicketByPrimaryKeySelective(serviceTicket);
		// 故障配件
		List<HxServiceTroubleInfo> troubles = serviceTicketVO.getServiceTroubleInfos();
		List<HxServicePartsInfo> parts = serviceTicketVO.getServicePartsInfos();
		if(troubles != null && !troubles.isEmpty()){
			Map<String, Object> troubleMap = new HashMap<String, Object>();
			troubleMap.put("serviceId", serviceTicket.getServiceId());
			troubleMap.put("list", troubles);
			hxServiceTroubleInfoDao.deleteTroublesById(serviceTicket.getServiceId());
			hxServiceTroubleInfoDao.insertTroubles(troubleMap);
		}else{
			hxServiceTroubleInfoDao.deleteTroublesById(serviceTicket.getServiceId());
		}
		
		if(parts != null && !parts.isEmpty()){
			Map<String, Object> partsMap = new HashMap<String, Object>();
			partsMap.put("serviceId", serviceTicket.getServiceId());
			partsMap.put("list", parts);
			partsMap.put("recordMan", sysUser.getUserAccount());
			partsMap.put("recordTime", new Date());
			hxServicePartsInfoDao.deletePartsById(serviceTicket.getServiceId());
			hxServicePartsInfoDao.insertParts(partsMap);
		}else{
			hxServicePartsInfoDao.deletePartsById(serviceTicket.getServiceId());
		}
		return "{\"flag\" : \"success\",\"serviceId\" : \""+serviceTicket.getServiceId()+"\"}";
	}

	@Override
	public void deleteRepairReceipt(String serviceId) {
		hxServiceTicketDao.deleteServiceTicketByPrimaryKey(serviceId);
	}

	@Override
	public void submit(String serviceId) {
		// 根据id查询对应实体类
		HxServiceTicket serviceTicket = hxServiceTicketDao.getServiceTicketByServiceId(serviceId);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("dataSource","1");
		this.jbpmService.startProcessInstanceByKey(JbpmProcessDefinations.repairReceipt,serviceTicket,param);
		hxServiceTicketDao.updateServiceTicketByPrimaryKeySelective(serviceTicket);
	}

	@Override
	public List<Map<String, Object>> getHxMaintenanceTree(String type) {
		return hxServiceTicketDao.getHxMaintenanceTree(type);
	}

}
