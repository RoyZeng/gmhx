package com.gome.gmhx.service.servicemanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxServiceProgressInfo;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.SysUser;

public interface HxServiceTicketCheckService {

	List<Map<String, Object>> getRepairReceiptPageList(Page page);

	Map<String, Object> getServiceTicketById(String serviceId);

	void saveServiceTicketCheck(HxServiceTicket serviceTicket, SysUser sysUser);

	void saveServiceProgress(List<HxServiceProgressInfo> progressInfos,SysUser sysUser);

}
