package com.gome.gmhx.service.servicemanage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.servicemanage.HxServiceProgressInfoDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;
import com.gome.gmhx.entity.HxServiceProgressInfo;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.servicemanage.HxServiceTicketCheckService;

@Service("hxServiceTicketCheckService")
public class HxServiceTicketCheckServiceImpl implements HxServiceTicketCheckService {

	@Resource
	HxServiceTicketDao hxServiceTicket;
	
	@Resource
	HxServiceProgressInfoDao hxServiceProgressInfoDao;
	
	@Override
	public List<Map<String, Object>> getRepairReceiptPageList(Page page) {
		return hxServiceTicket.getServiceTicketPageList(page);
	}

	@Override
	public Map<String, Object> getServiceTicketById(String serviceId) {
		return hxServiceTicket.selectServiceTicketByPrimaryKey(serviceId);
	}

	@Override
	public void saveServiceTicketCheck(HxServiceTicket serviceTicket,SysUser sysUser) {
		serviceTicket.setCenterCheckMan(sysUser.getUserAccount());
		serviceTicket.setCenterCheckTime(new Date());
		hxServiceTicket.updateServiceTicketByPrimaryKeySelective(serviceTicket);
	}

	@Override
	public void saveServiceProgress(List<HxServiceProgressInfo> progressInfos,SysUser sysUser) {
		List<HxServiceProgressInfo> infos = new ArrayList<HxServiceProgressInfo>();
		for(HxServiceProgressInfo info : progressInfos){
			info.setRecorder(sysUser.getUserAccount());
			info.setRecordTime(new Date());
			infos.add(info);
		}
		hxServiceProgressInfoDao.insertProgresses(infos);
	}

}
