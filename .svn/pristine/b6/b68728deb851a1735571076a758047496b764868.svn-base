package com.gome.gmhx.service.basicdata.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxCodeBarDao;
import com.gome.gmhx.entity.HxCodeBar;
import com.gome.gmhx.service.basicdata.HxCodeBarService;

@Service("hxCodeBarService")
public class HxCodeBarServiceImpl implements HxCodeBarService {

	@Resource
	private HxCodeBarDao hxCodeBarDao;
	
	@Override
	public List<Map<String, Object>> getHxCodeBarPageList(Page page) {
		return hxCodeBarDao.getHxCodeBarPageList(page);
	}

	@Override
	public int validateInnerCode1(String code) {
		return hxCodeBarDao.validateInnerCode1(code);
	}

	@Override
	public int validateInnerCode2(String code) {
		return hxCodeBarDao.validateInnerCode2(code);
	}

	@Override
	public int validateOuterCode(String code) {
		return hxCodeBarDao.validateOuterCode(code);
	}

	@Override
	public void addHxCodeBar(HxCodeBar hxCodeBar) {
		hxCodeBarDao.addHxCodeBar(hxCodeBar);
	}

	@Override
	public int validateModel(String model) {
		return hxCodeBarDao.validateModel(model);
	}

	@Override
	public HxCodeBar getHxCodeBarById(String id) {
		return hxCodeBarDao.getHxCodeBarById(id);
	}

	@Override
	public void updateHxCodeBar(HxCodeBar hxCodeBar) {
		hxCodeBarDao.updateHxCodeBar(hxCodeBar);		
	}

}
