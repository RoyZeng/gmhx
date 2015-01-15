package com.gome.gmhx.service.sysconfig;

import java.util.List;

import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.entity.HxUserRole;

public interface UserRoleService {
	
	public List<HxRole> getRoleTree();
	
	public List<HxRole> getRoleTreeData(String userId);
	
	void addUserRole(HxUserRole userRole);

	public void deleteCompleteByUserId(String userId);
}
