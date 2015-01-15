package com.gome.gmhx.dao.wsdl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.gmhx.entity.CrmWebsite;
import com.gome.gmhx.entity.CustomerserviceSaleMapping;
import com.gome.gmhx.entity.HxBarCodeRules;
import com.gome.gmhx.entity.HxCodeBar;
import com.gome.gmhx.entity.HxResetPassword;

@Repository("crmWebsiteDao")
public interface CrmWebsiteDao {
	
	public void delCrmwebsite();
	
	public void insertCrmwebsite(List<CrmWebsite> list);

	public List<HxBarCodeRules> getHxBarCodeRulesByProductCode(String productCode);

	public HxCodeBar getHxCodeBarByProductCode(String productCode);

	public int getHxServiceProductCountByInnerCode1(String innerCode);

	public int getHxServiceProductCountByInnerCode2(String innerCode);

	public int getHxServiceProductCountByOuterCode(String outerCode);

	public int getHxServiceProductCountByMachineCode(String innerCode);

	public void delCustomerserviceSaleMapping();

	public void insertCustomerserviceSaleMapping(
			List<CustomerserviceSaleMapping> list);

	public void insertResetPassword(HxResetPassword hxResetPassword);

	public List<Map<String, Object>> queryAllWebsiteList();

	public void updateCrmwebsite(CrmWebsite crmWebsite);

	public void addCrmwebsite(CrmWebsite crmWebsite);

	public List<Map<String, Object>> queryAllCustomerserviceSaleMappingList();

	public void updateCustomerserviceSaleMapping(
			CustomerserviceSaleMapping customerserviceSaleMapping);

	public void addCustomerserviceSaleMapping(
			CustomerserviceSaleMapping customerserviceSaleMapping);

	public void delSaleMappingBatch(List<String> saleComCodes);

	public void insertSaleMappingBatch(List<CustomerserviceSaleMapping> csms);
	
}
