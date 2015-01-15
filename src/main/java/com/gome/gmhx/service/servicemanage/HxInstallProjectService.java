package com.gome.gmhx.service.servicemanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;

public interface HxInstallProjectService {

	List<Map<String, Object>> getServiceInstallReceiptPageList(Page page);

	String saveInstallProject(HxServiceTicketVO serviceTicketVO, SysUser sysUser);

	Map<String, Object> getInstallProjectById(String serviceId);

	List<Map<String, Object>> getProjects(String productId);

	String updateInstallProject(HxServiceTicketVO serviceTicketVO,SysUser sysUser);

	void deleteServiceInstallReceipt(String serviceId);

}
