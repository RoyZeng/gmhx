package com.gome.gmhx.service.basicdata.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxSetupeFreeDao;
import com.gome.gmhx.entity.HxSetupeFree;
import com.gome.gmhx.service.basicdata.HxSetupeFreeService;

@Service("HxSetupeFreeService")
public class HxSetupeFreeServiceImpl implements HxSetupeFreeService{
	
	@Resource
    private HxSetupeFreeDao sfDao;
	@Override
	public List<Map<String, Object>> getSetupFreePageList(Page page) {
		return sfDao.getSetupFreePageList(page);
	}

	@Override
	public void addHxSetupeFree(HxSetupeFree free) {
		  sfDao.addHxSetupeFree(free);
		
	} 
	@Override
	public HxSetupeFree getHxSetupeFreeById(String aa) {
		return sfDao.getHxSetupeFreeById(aa);
	}

	@Override
	public HxSetupeFree getShowById(String id) {
		return sfDao.getShowById(id);
	}

	@Override
	public void updateHxSetupeFree(HxSetupeFree free) {
        sfDao.updateHxSetupeFree(free);
		
	}

	@Override
	public List<Map<String, Object>> getHxSetupeFreeExport(HxSetupeFree free) {
		return sfDao.getHxSetupeFreeExport(free);
	}


	

}
