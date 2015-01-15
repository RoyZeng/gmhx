package com.gome.gmhx.dao.sysconfig;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.HxFittingAuth;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.entity.HxRoleMenu;

@Repository("hxRoleMenuDao")
public interface HxRoleMenuDao {
	
	List<HxMenu> getMenuTree();

	List<HxMenu> getMenuTreeData(String roleId);

	void addRoleMenu(HxRoleMenu roleMenu);

	void deleteCompleteByRoleId(String roleId);

	List<EccGoodsCategory> getRoleCategoryTree();

	List<EccGoodsCategory> getRoleCategoryTreeData(String roleId);

	List<HxFittingAuth> getFittingAuthTree();

	List<HxFittingAuth> getFittingAuthTreeData(String roleId);

	void deleteFittingAuthByRoleId(String roleId);

	void deleteRoleCategoryByRoleId(String roleId);

	void addRoleCategory(HxRoleMenu hxRoleMenu);

	void addFittingAuth(HxRoleMenu hxRoleMenu);
	
	void insertRoleCategoryBatch(List<Object> list);
	
	void insertRoleFittingAuthBatch(List<Object> list);
	
	void insertRoleMenuBatch(List<Object> list);

	List<Map<String, Object>> getHxRoleAuthorityExport(HxRole role);
}
