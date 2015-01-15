package com.gome.gmhx.service.sysconfig.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.gmhx.dao.sysconfig.HxUserPositionDao;
import com.gome.gmhx.entity.HxPosition;
import com.gome.gmhx.entity.HxUserPosition;
import com.gome.gmhx.service.sysconfig.HxUserPositionService;

@Service("hxUserPositionService")
public class HxUserPositionServiceImpl implements HxUserPositionService {
	@Resource
	private HxUserPositionDao hxUserPositionDao;
	
	@Override
	public List<HxPosition> getPositionTree() {
		return hxUserPositionDao.getPositionTree();
	}
	
	@Override
	public List<HxPosition> getPositionTreeData(HxUserPosition userPosition) {
		return hxUserPositionDao.getPositionTreeData(userPosition);
	}
	
	@Override
	public void addUserPosition(HxUserPosition userPosition) {
		hxUserPositionDao.addUserPosition(userPosition);
	}

	@Override
	public void deleteCompleteByUserLoginName(HxUserPosition userPosition) {
		 hxUserPositionDao.deleteCompleteByUserLoginName(userPosition);
	}


}
