package com.gome.gmhx.service.basicdata.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxPostageDao;
import com.gome.gmhx.entity.HxPostage;
import com.gome.gmhx.service.basicdata.HxPostageService;

@Service("hxPostageService")
public class HxPostageServiceImpl implements HxPostageService {
	@Resource
	private HxPostageDao hxPostageDao;
	
	@Override
	public List<Map<String, Object>> getHxPostagePageList(Page page) {
		return hxPostageDao.getHxPostagePageList(page);
	}

	@Override
	public void addHxPostage(HxPostage hxPostage) {
		hxPostageDao.addHxPostage(hxPostage);
	}

	@Override
	public HxPostage getHxPostageById(String posId) {
		return hxPostageDao.getHxPostageById(posId);
	}

	@Override
	public void updateHxPostage(HxPostage hxPostage) {
		hxPostageDao.updateHxPostage(hxPostage);
	}

	@Override
	public List<Map<String, Object>> getHxPostageExport(HxPostage hxPostage) {
		return hxPostageDao.getHxPostageExport(hxPostage);
	}

	@Override
	public HxPostage getShowById(String posId) {
		return hxPostageDao.getShowById(posId);
	}

	@Override
	public void deleteHxPostage(String posId) {
        hxPostageDao.deleteHxPostage(posId);		
	}

	@Override
	public int validatePosNum(String posNum) {
		return hxPostageDao.validatePosNum(posNum);
	}

}
