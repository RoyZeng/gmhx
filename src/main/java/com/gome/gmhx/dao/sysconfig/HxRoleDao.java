package com.gome.gmhx.dao.sysconfig;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxRole;

@Repository("hxRoleDao")
public interface HxRoleDao {
	
	List<Map<String, Object>> getRolePageList(Page page);
	
	void addRole(HxRole role);
	
	HxRole getRoleById(String roleId);
	
	void updateRole(HxRole role);
	
	List<String> getAllRole();
	
	void insertRoleBatch(List<Object> list);
	
	List<Map<String, Object>> getHxRoleExport(HxRole role);
}
