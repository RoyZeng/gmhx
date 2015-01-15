package com.gome.gmhx.dao.sysconfig;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.entity.HxUserRole;

@Repository("hxUserRoleDao")
public interface HxUserRoleDao {
	List<HxRole> getRoleTree();
	List<HxRole> getRoleTreeData(String userId);
	void addUserRole(HxUserRole userRole);
	void deleteCompleteByUserId(String userId);
}
