package com.gome.gmhx.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.DemoDao;
import com.gome.gmhx.entity.Demo;
import com.gome.gmhx.service.DemoService;

@Service("demoService")
public class DemoServiceImpl implements DemoService {
	@Resource
	private DemoDao demoDao;

	@Override
	public List<Map<String, Object>> getDemoPageList(Page page) {
		return demoDao.getDemoPageList(page);
	}

	@Override
	public void addDemo(Demo demo) {
		demoDao.addDemo(demo);
	}

	@Override
	public Demo getDemoById(String aa) {
		return demoDao.getDemoById(aa);
	}

	@Override
	public void updateDemo(Demo demo) {
		demoDao.updateDemo(demo);
	}

	@Override
	public List<Map<String, Object>> getDemoExport(Demo demo) {
		return demoDao.getDemoExport(demo);
	}

}
