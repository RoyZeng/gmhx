package com.gome.gmhx.service.sysconfig;

import java.util.List;
import java.util.Map;

import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.HxFittingAuth;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.entity.HxRoleMenu;

public interface RoleMenuService {
	
	public List<HxMenu> getMenuTree();
	
	public List<HxMenu> getMenuTreeData(String roleId);
	
	void addRoleMenu(HxRoleMenu roleMenu);

	public void deleteCompleteByRoleId(String roleId);

	public List<EccGoodsCategory> getRoleCategoryTree();

	public List<EccGoodsCategory> getRoleCategoryTreeData(String roleId);

	public List<HxFittingAuth> getFittingAuthTree();

	public List<HxFittingAuth> getFittingAuthTreeData(String roleId);

	public void deleteFittingAuthByRoleId(String roleId);

	public void deleteRoleCategoryByRoleId(String roleId);

	public void addRoleCategory(HxRoleMenu hxRoleMenu);

	public void addFittingAuth(HxRoleMenu hxRoleMenu);
	
	public List<Map<String, Object>> getHxRoleAuthorityExport(HxRole hxRole);
}
