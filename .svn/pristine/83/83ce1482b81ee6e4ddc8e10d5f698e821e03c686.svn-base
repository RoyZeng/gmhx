package com.gome.gmhx.service.basicdata.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxBarCodeRulesDao;
import com.gome.gmhx.entity.HxBarCodeRules;
import com.gome.gmhx.service.basicdata.HxBarCodeRulesService;

@Service("hxBarCodeRulesService")
public class HxBarCodeRulesServiceImpl implements HxBarCodeRulesService {
	@Resource
	private HxBarCodeRulesDao hxBarCodeRulesDao;
	
	@Override
	public List<Map<String, Object>> getHxBarCodeRulesPageList(Page page) {
		return hxBarCodeRulesDao.getHxBarCodeRulesPageList(page);
	}

	@Override
	public void addHxBarCodeRules(HxBarCodeRules hxBarCodeRules) {
		hxBarCodeRulesDao.addHxBarCodeRules(hxBarCodeRules);
	}

	@Override
	public HxBarCodeRules getHxBarCodeRulesById(String rulesId) {
		return hxBarCodeRulesDao.getHxBarCodeRulesById(rulesId);
	}

	@Override
	public void updateHxBarCodeRules(HxBarCodeRules hxBarCodeRules) {
		hxBarCodeRulesDao.updateHxBarCodeRules(hxBarCodeRules);
	}

	@Override
	public List<Map<String, Object>> getHxBarCodeRulesExport(HxBarCodeRules hxBarCodeRules) {
		return hxBarCodeRulesDao.getHxBarCodeRulesExport(hxBarCodeRules);
	}

	@Override
	public HxBarCodeRules getShowById(String rulesId) {
		return hxBarCodeRulesDao.getShowById(rulesId);
	}



}
