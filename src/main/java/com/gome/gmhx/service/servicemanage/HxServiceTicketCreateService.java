package com.gome.gmhx.service.servicemanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;

public interface HxServiceTicketCreateService {

	void deleteServiceTicketById(String serviceId);

	void updateServiceTicket(HxServiceTicket serviceTicket);

	Map<String, Object> getServiceTicketById(String serviceId);

	List<Map<String, Object>> getServiceTicketPageList(Page page);

	String saveServiceTicket(HxServiceTicketVO serviceTicketVO, SysUser sysUser);

	void updateServiceTicketToFinish(String serviceId);

	List<Map<String, Object>> getGMZBPageList(Page page);

}
