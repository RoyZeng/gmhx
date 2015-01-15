package com.gome.gmhx.service.basicdata.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxFittingLocationDao;
import com.gome.gmhx.entity.HxFittingLocation;
import com.gome.gmhx.service.basicdata.HxFittingLocationService;

@Service("hxFittingLocationService")
public class HxFittingLocationServiceImpl implements HxFittingLocationService {
	@Resource
	private HxFittingLocationDao hxFittingLocationDao;
	
	@Override
	public List<Map<String, Object>> getHxFittingLocationPageList(Page page) {
		return hxFittingLocationDao.getHxFittingLocationPageList(page);
	}

	@Override
	public void addHxFittingLocation(HxFittingLocation hxFittingLocation) {
		hxFittingLocationDao.addHxFittingLocation(hxFittingLocation);
	}

	@Override
	public HxFittingLocation getHxFittingLocationById(String fittingId) {
		return hxFittingLocationDao.getHxFittingLocationById(fittingId);
	}

	@Override
	public void updateHxFittingLocation(HxFittingLocation hxFittingLocation) {
		hxFittingLocationDao.updateHxFittingLocation(hxFittingLocation);
	}

	@Override
	public List<Map<String, Object>> getHxFittingLocationExport(HxFittingLocation hxFittingLocation) {
		return hxFittingLocationDao.getHxFittingLocationExport(hxFittingLocation);
	}

	@Override
	public HxFittingLocation getShowById(String fittingId) {
		return hxFittingLocationDao.getShowById(fittingId);
	}



}
