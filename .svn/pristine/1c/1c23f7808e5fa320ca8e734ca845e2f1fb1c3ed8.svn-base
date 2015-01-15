package com.gome.gmhx.service.servicemanage.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.service.servicemanage.HxServiceTicketQueryService;

@Service("hxServiceTicketQueryService")
public class HxServiceTicketQueryServiceImpl implements HxServiceTicketQueryService {
	
	@Resource
	HxServiceTicketDao serviceTicketDao;

	@Override
	public List<Map<String, Object>> getServiceTicketPageList(Page page) {
		return serviceTicketDao.getServiceTicketPageList(page);
	}

	@Override
	public Map<String, Object> getServiceTicketById(String serviceId) {
		return serviceTicketDao.selectServiceTicketByPrimaryKey(serviceId);
	}

	@Override
	public HxServiceTicket getServiceTicketByServiceId(String key) {
		return serviceTicketDao.getServiceTicketByServiceId(key);
	}

	@Override
	public void updateServiceTickeetSelective(HxServiceTicket serviceTicket) {
		serviceTicketDao.updateServiceTicketByPrimaryKeySelective(serviceTicket);
	}

}
