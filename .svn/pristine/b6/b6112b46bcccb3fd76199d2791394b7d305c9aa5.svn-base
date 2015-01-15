package com.gome.gmhx.service.servicemanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;

public interface HxInstallReceiptService {
	
	void updateInstallReceiptStatus(String serviceId);

	void deleteServiceInstallReceipt(String serviceId);
	
	Map<String, Object> getHxProductDetailById(String spbm);

	Map<String, Object> getInstallReceiptById(String serviceId);
	
	List<Map<String, Object>> getServiceInstallReceiptPageList(Page page);
	
	String saveInstallReceipt(HxServiceTicketVO serviceTicketVO, SysUser sysUser);
	
	String updateInstallReceipt(HxServiceTicketVO serviceTicketVO, SysUser sysUser);

	void submit(String serviceId);

}