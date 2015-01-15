package com.gome.gmhx.service.wsdl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.dao.basicdata.HxCodeDao;
import com.gome.gmhx.dao.servicemanage.HxServiceCustomerDao;
import com.gome.gmhx.dao.servicemanage.HxServiceProductDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;
import com.gome.gmhx.dao.wsdl.JlInstallDao;
import com.gome.gmhx.entity.HxServiceCustomer;
import com.gome.gmhx.entity.HxServiceProduct;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.JlInstall;
import com.gome.gmhx.entity.JlSale;
import com.gome.gmhx.jbpm.JbpmProcessDefinations;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.service.wsdl.service.JlInstallService;
import com.gome.gmhx.service.wsdl.service.JlSaleService;

@Service("installService")
public class JlInstallServiceImpl implements JlInstallService {
	@Resource
	private JlInstallDao installDao;
	
	@Resource
	HxServiceCustomerDao hxServiceCustomerDao;
	
	@Resource
	HxServiceTicketDao hxServiceTicketDao;
	
	@Resource
	HxServiceProductDao hxServiceProductDao;
	
	@Resource
	JbpmService jbpmService;
	
	@Resource 
	JlSaleService jlSaleService;
	
	@Resource
	HxCodeDao hxCodeDao;
	
	@Override
	public String insertInstall(List<JlInstall> installs) {
		List<HxServiceTicket> ticketList =  new ArrayList<HxServiceTicket>();
		List<HxServiceCustomer> customerList = new ArrayList<HxServiceCustomer>();
		List<HxServiceProduct> productList = new ArrayList<HxServiceProduct>();
		Integer sequence = hxServiceTicketDao.getMaxSequence();
		for(JlInstall install:installs){
			HxServiceTicket ticket = new HxServiceTicket();
			HxServiceCustomer customer = new HxServiceCustomer();
			HxServiceProduct product = new HxServiceProduct();
			
			//sale.setXslx();"0正常零售,1其他建档,3是代表电商"
			JlSale sal = jlSaleService.selectSaleByPrimaryKey(install.getWbGsxx01(),install.getThdh().toString());
			if(sal!=null){
				product.setInvoiceNum(sal.getFph());
			}
			String customerId =  UUIDGenerator.getUUID();
			String productId = UUIDGenerator.getUUID();
			String serviceId = "";
			
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
			serviceId = "IN-"+currentDateString + str;
			customer.setCustomerId(customerId);
			customer.setCustomerName(install.getKhmc());
			customer.setAddress(install.getLxdz());
			customer.setPhone(install.getLxdh());
			customer.setTelephone(install.getQtdh());
			product.setProductId(productId);
			product.setCustomerId(customerId);
			product.setBrand(install.getPpb01());
			product.setCategory(install.getSpfl01());
			product.setProductCode(install.getSpbm());
			
			Map<String, String> map= hxCodeDao.getProductInfo(install.getSpbm());
			if(map!=null){
				if(map.get("gomeCode")!=null)
				product.setGomeCode(map.get("gomeCode").toString());
				if(map.get("machineType")!=null)
				product.setMachineType(map.get("machineType").toString());
			}
			product.setProductName(install.getSpmc());
			if(install.getSpfl01().startsWith("R03")||install.getSpfl01().startsWith("r03")){
				product.setInternalMachineCode1(install.getNjtm());
				product.setExternalMachineCode(install.getWjtm());
			}else{
				product.setMachineCode(install.getNjtm());
			}
			product.setDeliveryOrderNum(install.getThdh());
			product.setInstallUnit(install.getAzwd01());
			product.setSaleCenter(install.getWbGsxx01());
			product.setInstallDate(install.getHzrq());
			product.setSaleMarket(install.getDzbm01());
			// LG窗机空调PEHD-2AAKH2-S 从商品名称中得到商品类型
			//String spmc = install.getSpmc().replaceAll("[\u4e00-\u9fa5]+", "*");
			
			ticket.setAppointmentDate(install.getYysj());
			ticket.setDataFrom(install.getSjly());
			ticket.setLinkMan(install.getKhmc());
			ticket.setLinkPhone(install.getLxdh());
			ticket.setSequence(sequence);
			ticket.setServiceId(serviceId);
			ticket.setJlServiceId(install.getAzd01().toString());
			// 01 维修  02 安装
			ticket.setServiceType("02");
			ticket.setCustomerId(customerId);
			ticket.setProductId(productId);
			ticket.setServiceUnit(install.getGsxx01());
			ticket.setAppointmentInstallDate(install.getYysj());
			ticket.setCreateTimeS(new Date());
			
			String orgName = null;
			if(install.getAzwd01().startsWith("S")){//自建网点
				orgName = install.getAzwd01()+"W";
			}else{//国美第三方网点
				orgName= install.getAzwd01()+"_"+install.getGsxx01();
			}
			ticket.setCreateOrganizationS(orgName);
			ticket.setAlterOrganizationS(orgName);
			ticket.setApplicant(orgName);
			
			ticketList.add(ticket);
			customerList.add(customer);
			productList.add(product);
		}
		hxServiceTicketDao.insertServiceTicketList(ticketList);
		hxServiceCustomerDao.insertServiceCustomerList(customerList);
		hxServiceProductDao.insertServiceProductList(productList);
		for(HxServiceTicket ticket:ticketList){
			HxServiceTicket serviceTicket = hxServiceTicketDao.getServiceTicketByServiceId(ticket.getServiceId());
			Map ve = new HashMap();
			ve.put("dataSource", "0");//数据来源为1代表安装回执中系统建单 0 其他系统推送
			this.jbpmService.startProcessInstanceByKey(JbpmProcessDefinations.installationReceipt, serviceTicket, ve);
			hxServiceTicketDao.updateServiceTicketByPrimaryKeySelective(serviceTicket);
		}
		return "ok";
	}

	@Override
	public void deleteInstallById(BigDecimal installId) {
		installDao.deleteInstallByPrimaryKey(installId);
	}

	@Override
	public void updateInstall(JlInstall entity) {
		installDao.updateInstallByPrimaryKey(entity);
	}
	
	@Override
	public List<Map<String, Object>> getInstallPageList(Page page) {
		return installDao.getInstallPageList(page);
	}

	@Override
	public List<Map<String, Object>> getInstallList(JlInstall install) {
		return installDao.getInstallList(install);
	}

	@Override
	public JlInstall getInstallById(BigDecimal installId) {
		return installDao.selectInstallByPrimaryKey(installId);
	}

}
