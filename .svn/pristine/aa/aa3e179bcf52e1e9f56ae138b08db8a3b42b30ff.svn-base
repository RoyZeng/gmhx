package com.gome.gmhx.service.sysconfig.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.gmhx.dao.sysconfig.HxUserRoleDao;
import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.entity.HxUserRole;
import com.gome.gmhx.service.sysconfig.UserRoleService;

@Service("userRoleService")
public class HxUserRoleServiceImpl implements UserRoleService {
	@Resource
	private HxUserRoleDao hxUserRoleDao;
	
	@Override
	public List<HxRole> getRoleTree() {
		return hxUserRoleDao.getRoleTree();
	}
	
	@Override
	public List<HxRole> getRoleTreeData(String userId) {
		return hxUserRoleDao.getRoleTreeData(userId);
	}
	
	@Override
	public void addUserRole(HxUserRole userRole) {
		hxUserRoleDao.addUserRole(userRole);
	}

	@Override
	public void deleteCompleteByUserId(String userId) {
		hxUserRoleDao.deleteCompleteByUserId(userId);
	}

}
