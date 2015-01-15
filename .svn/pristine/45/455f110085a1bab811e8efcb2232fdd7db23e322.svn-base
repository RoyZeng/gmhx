package com.gome.gmhx.service.sysconfig;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxUserPosition;

public interface HxMenuService {
	
	List<HxMenu> getMenuTree();
	
	List<Map<String, Object>> getMenuPageList(Page page);
	
	void addMenu(HxMenu hxMenu);
	
	HxMenu getMenuById(String menuId);
	
	void updateMenu(HxMenu hxMenu);
	
	List<HxMenu> getUserMenus(HxUserPosition userPosition);
	
	List<HxMenu> getAllMenuTree();

	List<HxMenu> getPositionMenuTree(String sysPositionId);

	List<HxMenu> getPositionRoleMenuTree(String sysPositionId);
	
}
