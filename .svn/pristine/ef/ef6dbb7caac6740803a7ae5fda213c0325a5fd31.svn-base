package com.gome.gmhx.service.basicdata.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxFreeDao;
import com.gome.gmhx.entity.HxFree;
import com.gome.gmhx.service.basicdata.HxFreeService;

@Service("HxFreeService")
public class HxFreeServiceImpl implements HxFreeService {
	
	@Resource
	private HxFreeDao freeDao;

	@Override
	public List<Map<String, Object>> getFreePageList(Page page) {
		return  freeDao.getFreePageList(page);
	}

	@Override
	public void addHxFree(HxFree free) {
		freeDao.addHxFree(free);
		
	}

	@Override
	public HxFree getHxFreeById(String aa) {
	
		return freeDao.getHxFreeById(aa);
	}

	@Override
	public HxFree getShowById(String id) {
	
		return freeDao.getShowById(id);
	}

	@Override
	public void updateHxFree(HxFree free) {
		 freeDao.updateHxFree(free);
		
	}

	@Override
	public List<Map<String, Object>> getHxFreeExport(HxFree free) {
		
		return freeDao.getHxFreeExport(free);
	}

	@Override
	public int insertHxFree(List<HxFree> free) {
		return 0;
	}
	

}
