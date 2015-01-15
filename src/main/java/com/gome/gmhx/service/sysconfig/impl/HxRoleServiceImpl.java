package com.gome.gmhx.service.sysconfig.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.sysconfig.HxRoleDao;
import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.service.sysconfig.RoleService;

@Service("roleService")
public class HxRoleServiceImpl implements RoleService {
	@Resource
	private HxRoleDao hxRoleDao;

	@Override
	public List<Map<String, Object>> getRolePageList(Page page) {
		return hxRoleDao.getRolePageList(page);
	}

	@Override
	public void addRole(HxRole role) {
		hxRoleDao.addRole(role);
	}
	
	@Override
	public HxRole getRoleById(String roleId) {
		return hxRoleDao.getRoleById(roleId);
	}

	@Override
	public void updateRole(HxRole role) {
		hxRoleDao.updateRole(role);
	}

	@Override
	public List<Map<String, Object>> getHxRoleExport(HxRole role) {
		return hxRoleDao.getHxRoleExport(role);
	}

}
