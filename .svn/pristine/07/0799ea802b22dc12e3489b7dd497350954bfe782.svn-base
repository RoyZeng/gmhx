package com.gome.gmhx.service.sysconfig.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.gmhx.dao.sysconfig.HxRoleMenuDao;
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.HxFittingAuth;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.entity.HxRoleMenu;
import com.gome.gmhx.service.sysconfig.RoleMenuService;

@Service("roleMenuService")
public class HxRoleMenuServiceImpl implements RoleMenuService {
	@Resource
	private HxRoleMenuDao hxRoleMenuDao;
	
	@Override
	public List<HxMenu> getMenuTree() {
		return hxRoleMenuDao.getMenuTree();
	}
	
	@Override
	public List<HxMenu> getMenuTreeData(String roleId) {
		return hxRoleMenuDao.getMenuTreeData(roleId);
	}
	
	@Override
	public void addRoleMenu(HxRoleMenu roleMenu) {
		hxRoleMenuDao.addRoleMenu(roleMenu);
	}

	@Override
	public void deleteCompleteByRoleId(String roleId) {
		hxRoleMenuDao.deleteCompleteByRoleId(roleId);
	}

	@Override
	public List<EccGoodsCategory> getRoleCategoryTree() {
		return hxRoleMenuDao.getRoleCategoryTree();
	}

	@Override
	public List<EccGoodsCategory> getRoleCategoryTreeData(String roleId) {
		return hxRoleMenuDao.getRoleCategoryTreeData(roleId);
	}

	@Override
	public List<HxFittingAuth> getFittingAuthTree() {
		return hxRoleMenuDao.getFittingAuthTree();
	}

	@Override
	public List<HxFittingAuth> getFittingAuthTreeData(String roleId) {
		return hxRoleMenuDao.getFittingAuthTreeData(roleId);
	}

	@Override
	public void deleteFittingAuthByRoleId(String roleId) {
		hxRoleMenuDao.deleteFittingAuthByRoleId(roleId);
	}

	@Override
	public void deleteRoleCategoryByRoleId(String roleId) {
		hxRoleMenuDao.deleteRoleCategoryByRoleId(roleId);
	}

	@Override
	public void addRoleCategory(HxRoleMenu hxRoleMenu) {
		hxRoleMenuDao.addRoleCategory(hxRoleMenu);		
	}

	@Override
	public void addFittingAuth(HxRoleMenu hxRoleMenu) {
		hxRoleMenuDao.addFittingAuth(hxRoleMenu);		
	}

	@Override
	public List<Map<String, Object>> getHxRoleAuthorityExport(HxRole role) {
		return hxRoleMenuDao.getHxRoleAuthorityExport(role);
	}

}
