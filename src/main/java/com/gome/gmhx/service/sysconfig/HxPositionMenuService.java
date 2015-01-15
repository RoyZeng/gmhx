package com.gome.gmhx.service.sysconfig;

import java.util.List;

import com.gome.gmhx.entity.HxFittingAuth;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxPositionMenu;

public interface HxPositionMenuService {
	
	public List<HxMenu> getMenuTree();
	
	public List<HxMenu> getMenuTreeData(String positionId);
	
	void addPositionMenu(HxPositionMenu positionMenu);

	public void deleteCompleteByPositionId(String positionId);

	public void deleteFittingAuthByPositionId(String positionid);

	public void addFittingAuth(HxPositionMenu positionMenu);

	public List<HxFittingAuth> getFittingAuthTree(String param);

	public List<HxFittingAuth> getFittingAuthTreeData(String positionId);
}
