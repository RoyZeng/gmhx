package com.gome.gmhx.dao.servicemanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxProductDetail;
import com.gome.gmhx.entity.HxServiceProduct;

@Repository("hxServiceProductDao")
public interface HxServiceProductDao {

	public void insertServiceProduct(HxServiceProduct entity);

	public List<Map<String, Object>> getProductSerivcePageList(Page page);

	public void updateServiceProductByPrimaryKey(HxServiceProduct entity);

	public void updateServiceProduct(HxServiceProduct serviceProduct); 
	
	public void insertServiceProductList(List<HxServiceProduct> products);
	
	public HxServiceProduct getServiceProductByPrimaryKey(String ProductId);
	
	List<Map> getHxServiceProductByBarCode(String barCode);

	public HxServiceProduct selectServiceProductBySaleInfo(String companyCode, String deliveryCode);

	public HxServiceProduct getProductsByBarCodes(String internalMachineCode1, String internalMachineCode2, String machineCode);

	public HxServiceProduct getProductsByBarCode(String machineCode);
	
}
