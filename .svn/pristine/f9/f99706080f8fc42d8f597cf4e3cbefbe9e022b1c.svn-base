package com.gome.gmhx.service.sysconfig.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.sysconfig.HxMenuDao;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxUserPosition;
import com.gome.gmhx.service.sysconfig.HxMenuService;

@Service("hxMenuService")
public class HxMenuServiceImpl implements HxMenuService {
	@Resource
	private HxMenuDao hxMenuDao;
	
	@Override
	public List<HxMenu> getMenuTree() {
		return hxMenuDao.getMenuTree();
	}
	
	@Override
	public List<Map<String, Object>> getMenuPageList(Page page) {
		return hxMenuDao.getMenuPageList(page);
	}

	@Override
	public void addMenu(HxMenu hxMenu) {
		hxMenuDao.addMenu(hxMenu);
	}
	
	@Override
	public HxMenu getMenuById(String menuId) {
		return hxMenuDao.getMenuById(menuId);
	}

	@Override
	public void updateMenu(HxMenu hxMenu) {
		hxMenuDao.updateMenu(hxMenu);
	}

	@Override
	public List<HxMenu> getUserMenus(HxUserPosition userPosition) {
		return hxMenuDao.getUserMenus(userPosition);
	}

	@Override
	public List<HxMenu> getAllMenuTree() {
		return hxMenuDao.getAllMenuTree();
	}

	@Override
	public List<HxMenu> getPositionMenuTree(String sysPositionId) {
		return hxMenuDao.getPositionMenuTree(sysPositionId);
	}

	@Override
	public List<HxMenu> getPositionRoleMenuTree(String sysPositionId) {
		return hxMenuDao.getPositionRoleMenuTree(sysPositionId);
	}


}
