package com.gome.gmhx.service.sysconfig.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.gmhx.dao.sysconfig.HxPositionMenuDao;
import com.gome.gmhx.entity.HxFittingAuth;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxPositionMenu;
import com.gome.gmhx.service.sysconfig.HxPositionMenuService;

@Service("hxPositionMenuService")
public class HxPositionMenuServiceImpl implements HxPositionMenuService {
	@Resource
	private HxPositionMenuDao hxPositionMenuDao;
	
	@Override
	public List<HxMenu> getMenuTree() {
		return hxPositionMenuDao.getMenuTree();
	}
	
	@Override
	public List<HxMenu> getMenuTreeData(String positionId) {
		return hxPositionMenuDao.getMenuTreeData(positionId);
	}
	
	@Override
	public void addPositionMenu(HxPositionMenu positionMenu) {
		hxPositionMenuDao.addPositionMenu(positionMenu);
	}

	@Override
	public void deleteCompleteByPositionId(String positionId) {
		hxPositionMenuDao.deleteCompleteByPositionId(positionId);
	}

	@Override
	public void deleteFittingAuthByPositionId(String positionid) {
		hxPositionMenuDao.deleteFittingAuthByPositionId(positionid);
	}

	@Override
	public void addFittingAuth(HxPositionMenu positionMenu) {
		hxPositionMenuDao.addFittingAuth(positionMenu);
	}

	@Override
	public List<HxFittingAuth> getFittingAuthTree(String param) {
		return hxPositionMenuDao.getFittingAuthTree(param);
	}

	@Override
	public List<HxFittingAuth> getFittingAuthTreeData(String positionId) {
		return hxPositionMenuDao.getFittingAuthTreeData(positionId);
	}

}
