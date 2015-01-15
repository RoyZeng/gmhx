package com.gome.gmhx.service.servicemanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;

public interface HxRepairReceiptService {

	void deleteRepairReceipt(String serviceId);

	void submit(String serviceId);

	Map<String, Object> getRepairReceiptById(String serviceId);

	List<Map<String, Object>> getRepairReceiptPageList(Page page);

	List<Map<String, Object>> getPartsByServiceId(String serviceId);

	List<Map<String, Object>> getTroublesByServiceId(String serviceId);
	
	String saveRepairReceipt(HxServiceTicketVO serviceTicketVO,SysUser sysUser);

	String updateRepairReceipt(HxServiceTicketVO serviceTicketVO, SysUser sysUser);

	List<Map<String, Object>> getHxMaintenanceTree(String type);

}
