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
import com.gome.gmhx.dao.servicemanage.HxInstallProjectServiceDao;
import com.gome.gmhx.dao.servicemanage.HxServiceCustomerDao;
import com.gome.gmhx.dao.servicemanage.HxServiceProductDao;
import com.gome.gmhx.dao.servicemanage.HxServiceProjectDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;
import com.gome.gmhx.entity.HxServiceCustomer;
import com.gome.gmhx.entity.HxServiceProduct;
import com.gome.gmhx.entity.HxServiceProject;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;
import com.gome.gmhx.jbpm.JbpmProcessDefinations;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.service.common.MachineReviewService;
import com.gome.gmhx.service.servicemanage.HxInstallProjectService;

@Service("hxInstallProjectService")
public class HxInstallProjectServiceImpl implements HxInstallProjectService {
	
	@Resource
	HxServiceCustomerDao hxServiceCustomerDao;
	
	@Resource
	HxServiceProductDao hxServiceProductDao;
	
	@Resource
	HxServiceTicketDao hxServiceTicketDao;
	
	@Resource
	HxServiceProjectDao hxServiceProjectDao;
	
	
	@Resource
	HxInstallProjectServiceDao hxInstallProjectServiceDao;
	
	@Resource
	JbpmService jbpmService;
	
	@Resource
	MachineReviewService machineReviewService;
	

	@Override
	public List<Map<String, Object>> getServiceInstallReceiptPageList(Page page) {
		return hxServiceTicketDao.getServiceTicketPageList(page);
	}

	@Override
	public String saveInstallProject(HxServiceTicketVO serviceTicketVO, SysUser sysUser) {
		HxServiceCustomer serviceCustomer = serviceTicketVO.getServiceCustomer();
		HxServiceProduct serviceProduct = serviceTicketVO.getServiceProduct();
		HxServiceTicket serviceTicket = serviceTicketVO.getServiceTicket();
		// 生成用户ID
		String cusotmerID = UUIDGenerator.getUUID();
		serviceCustomer.setCustomerType("02");
		serviceCustomer.setCustomerId(cusotmerID);
		serviceCustomer.setCreateManC(sysUser.getUserAccount());
		serviceCustomer.setCreateTimeC(new Date());
		serviceCustomer.setCreateOrganizationC(sysUser.getCompanyId());
		hxServiceCustomerDao.insertServiceCustomer(serviceCustomer);
		String message = "";
		List<HxServiceProject> projects = serviceTicketVO.getServiceProjects();
		if(projects != null && !projects.isEmpty()){
			String serviceID = "";
			Integer sequence = hxServiceTicketDao.getMaxSequence();
			for(HxServiceProject project : projects){
				// 生成产品ID
				String productID = UUIDGenerator.getUUID();
				serviceProduct.setCustomerId(cusotmerID);
				serviceProduct.setProductId(productID);
				serviceProduct.setCategory(project.getCategory());
				serviceProduct.setMachineType(project.getMachineType());
				serviceProduct.setGomeCode(project.getGomeCode());
				serviceProduct.setBrand(project.getBrand());
				
				serviceProduct.setInternalMachineCode1(project.getInternalMachineCode1());
				serviceProduct.setInternalMachineCode2(project.getInternalMachineCode2());
				serviceProduct.setExternalMachineCode(project.getExternalMachineCode());
				serviceProduct.setMachineCode(project.getMachineCode());
				
				
				String category = project.getCategory();
				if(category.startsWith("R03")||category.startsWith("r03")){
					
					if(!serviceProduct.getInternalMachineCode1().equals("")){
						String messageInter1 = machineReviewService.velidateBarCodeIsUsed(serviceProduct.getInternalMachineCode1());
						if(messageInter1!=null&&!messageInter1.equals(""))
							message = "内机条码1已经使用过，请修改后重试！";
					}else{
						message = "内机条码1不能为空！"; 
					}
					
					
					if(!serviceProduct.getInternalMachineCode2().equals("")){
						String messageInter2 = machineReviewService.velidateBarCodeIsUsed(serviceProduct.getInternalMachineCode2());
						if(messageInter2!=null&&!messageInter2.equals(""))
							message = "内机条码2已经使用过，请修改后重试！";
					}
					
					if(!serviceProduct.getExternalMachineCode().equals("")){
						String messageExter = machineReviewService.velidateBarCodeIsUsed(serviceProduct.getExternalMachineCode());
						if(messageExter!=null&&!messageExter.equals(""))
							message = "外机条码已经使用过，请修改后重试！";
					}else{
						message = "外机条码不能为空！"; 
					}
					
				}else{
					String messageMachine = machineReviewService.velidateBarCodeIsUsed(serviceProduct.getMachineCode());
					if(messageMachine!=null&&messageMachine!="")
						message = "机器条码已经使用过，请修改后重试！";
				}
				if(message!=""){
					return "{\"flag\" : \"falure\",\"message\" : \""+message+"\"}";
				}
				
				
				serviceProduct.setInstallCardNum(project.getInstallCardNum());
				serviceProduct.setInstallDate(project.getInstallDate());
				serviceProduct.setCreateManP(sysUser.getUserAccount());
				serviceProduct.setCreateTimeP(new Date());
				serviceProduct.setCreateOrganizationP(sysUser.getCompanyId());
				serviceProduct.setProductNote(project.getCommentP());
				
				SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
				String currentDateString = format.format(new Date());
				
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
				serviceID = "IN-"+currentDateString + str;
				
				serviceTicket.setServiceId(serviceID);
				serviceTicket.setCustomerId(cusotmerID);
				serviceTicket.setProductId(productID);
				serviceTicket.setSequence(sequence);
				serviceTicket.setServiceType("02");
				serviceTicket.setCreateManS(sysUser.getUserAccount());
				serviceTicket.setCreateTimeS(new Date());
				serviceTicket.setCreateOrganizationS(sysUser.getCompanyId());
				serviceTicket.setWarrantyPolicy(project.getWarrantyPolicy());
				serviceTicket.setServiceMan(project.getInstaller());
				serviceTicket.setIsDoubleInstall(project.getIsDoubleInstall().toString());
				serviceTicket.setDelayFee(project.getDelayFee());
				serviceTicket.setOtherFee(project.getOtherFee());
				hxServiceProductDao.insertServiceProduct(serviceProduct);
				hxServiceTicketDao.insertServiceTicket(serviceTicket);
				Map ve = new HashMap();
				ve.put("dataSource", "1");//数据来源为1代表安装回执中系统建单 0 其他系统推送
				this.jbpmService.startProcessInstanceByKey(JbpmProcessDefinations.installationReceipt, serviceTicket, ve);
				hxServiceTicketDao.updateServiceTicketByPrimaryKeySelective(serviceTicket);
			}
		}
		return "{\"flag\" : \"success\"}";
	}

	@Override
	public Map<String, Object> getInstallProjectById(String serviceId) {
		return hxServiceTicketDao.selectServiceTicketByPrimaryKey(serviceId);
	}

	@Override
	public List<Map<String, Object>> getProjects(String serviceId) {
		return hxServiceProjectDao.selectProjects(serviceId);
	}

	@Override
	public String updateInstallProject(HxServiceTicketVO serviceTicketVO, SysUser sysUser) {
		HxServiceCustomer serviceCustomer = serviceTicketVO.getServiceCustomer();
		HxServiceProduct serviceProduct = serviceTicketVO.getServiceProduct();
		HxServiceTicket serviceTicket = serviceTicketVO.getServiceTicket();
		// 生成用户ID
		serviceCustomer.setAlterManC(sysUser.getUserAccount());
		serviceCustomer.setAlterTimeC(new Date());
		serviceCustomer.setAlterOrganizationC(sysUser.getCompanyId());
		hxServiceCustomerDao.updateServiceCustomer(serviceCustomer);
		
		String message = "";
		List<HxServiceProject> projects = serviceTicketVO.getServiceProjects();
		if(projects != null && !projects.isEmpty()){
			String serviceID = "";
			Integer sequence = hxServiceTicketDao.getMaxSequence();
			for(HxServiceProject project : projects){
				// 生成产品ID
				String productID = UUIDGenerator.getUUID();
				serviceProduct.setCustomerId(serviceCustomer.getCustomerId());
				serviceProduct.setProductId(productID);
				
				serviceProduct.setCategory(project.getCategory());
				serviceProduct.setMachineType(project.getMachineType());
				serviceProduct.setGomeCode(project.getGomeCode());
				serviceProduct.setBrand(project.getBrand());
				
				serviceProduct.setInternalMachineCode1(project.getInternalMachineCode1());
				serviceProduct.setInternalMachineCode2(project.getInternalMachineCode2());
				serviceProduct.setExternalMachineCode(project.getExternalMachineCode());
				serviceProduct.setMachineCode(project.getMachineCode());
				
				String category = project.getCategory();
				if(category.startsWith("R03")||category.startsWith("r03")){
					if(!serviceProduct.getInternalMachineCode1().equals("")){
						String messageInter1 = machineReviewService.velidateBarCodeIsUsed(serviceProduct.getInternalMachineCode1());
						if(messageInter1!=null&&!messageInter1.equals(""))
							message = "内机条码1已经使用过，请修改后重试！";
					}else{
						message = "内机条码1不能为空！"; 
					}
					
					if(!serviceProduct.getInternalMachineCode2().equals("")){
						String messageInter2 = machineReviewService.velidateBarCodeIsUsed(serviceProduct.getInternalMachineCode2());
						if(messageInter2!=null&&!messageInter2.equals(""))
							message = "内机条码2已经使用过，请修改后重试！";
					}
					
					if(!serviceProduct.getExternalMachineCode().equals("")){
						String messageExter = machineReviewService.velidateBarCodeIsUsed(serviceProduct.getExternalMachineCode());
						if(messageExter!=null&&!messageExter.equals(""))
							message = "外机条码已经使用过，请修改后重试！";
					}else{
						message = "外机条码不能为空！"; 
					}
				}else{
					String messageMachine = machineReviewService.velidateBarCodeIsUsed(serviceProduct.getMachineCode());
					if(messageMachine!=null&&messageMachine!="")
						message = "机器条码已经使用过，请修改后重试！";
				}
				if(message!=""){
					return "{\"flag\" : \"falure\",\"message\" : \""+message+"\"}";
				}
				
				serviceProduct.setInstallCardNum(project.getInstallCardNum());
				serviceProduct.setInstallDate(project.getInstallDate());
				serviceProduct.setCreateManP(sysUser.getUserAccount());
				serviceProduct.setCreateTimeP(new Date());
				serviceProduct.setCreateOrganizationP(sysUser.getCompanyId());
				serviceProduct.setProductNote(project.getCommentP());
				
				SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
				String currentDateString = format.format(new Date());
				
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
				serviceID = "IN-"+currentDateString + str;
				
				serviceTicket.setServiceId(serviceID);
				serviceTicket.setCustomerId(serviceCustomer.getCustomerId());
				serviceTicket.setProductId(productID);
				serviceTicket.setSequence(sequence);
				serviceTicket.setServiceType("02");
				serviceTicket.setCreateManS(sysUser.getUserAccount());
				serviceTicket.setCreateTimeS(new Date());
				serviceTicket.setCreateOrganizationS(sysUser.getCompanyId());
				serviceTicket.setWarrantyPolicy(project.getWarrantyPolicy());
				serviceTicket.setServiceMan(project.getInstaller());
				serviceTicket.setIsDoubleInstall(project.getIsDoubleInstall().toString());
				serviceTicket.setDelayFee(project.getDelayFee());
				serviceTicket.setOtherFee(project.getOtherFee());
				hxServiceProductDao.insertServiceProduct(serviceProduct);
				hxServiceTicketDao.insertServiceTicket(serviceTicket);
				Map ve = new HashMap();
				ve.put("dataSource", "1");//数据来源为1代表安装回执中系统建单 0 其他系统推送
				this.jbpmService.startProcessInstanceByKey(JbpmProcessDefinations.installationReceipt, serviceTicket, ve);
				hxServiceTicketDao.updateServiceTicketByPrimaryKeySelective(serviceTicket);
			}
		}
		return "{\"flag\" : \"success\"}";
	}

	@Override
	public void deleteServiceInstallReceipt(String serviceId) {
		hxInstallProjectServiceDao.deleteServiceInstallProjectByPrimaryKey(serviceId);
	}
}
















