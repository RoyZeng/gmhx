package com.gome.gmhx.service.servicemanage.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.gmhx.dao.servicemanage.HxServiceProgressInfoDao;
import com.gome.gmhx.service.servicemanage.HxProgressInfoService;

@Service("hxProgressInfoService")
public class HxProgressInfoServiceImpl implements HxProgressInfoService {
	
	@Resource
	HxServiceProgressInfoDao serviceProgressInfoDao;

	@Override
	public List<Map<String, Object>> getProgressInfo(String serviceId) {
		return serviceProgressInfoDao.getProgressInfo(serviceId);
	}

}
