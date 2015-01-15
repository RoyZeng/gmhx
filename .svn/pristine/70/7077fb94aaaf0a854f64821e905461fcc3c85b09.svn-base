package com.gome.gmhx.dao.sysconfig;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gome.gmhx.entity.HxFittingAuth;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxPositionMenu;

@Repository("hxPositionMenuDao")
public interface HxPositionMenuDao {
	List<HxMenu> getMenuTree();

	List<HxMenu> getMenuTreeData(String positionId);

	void addPositionMenu(HxPositionMenu positionMenu);

	void deleteCompleteByPositionId(String positionId);

	void deleteFittingAuthByPositionId(String positionid);

	void addFittingAuth(HxPositionMenu positionMenu);

	List<HxFittingAuth> getFittingAuthTree(String param);

	List<HxFittingAuth> getFittingAuthTreeData(String positionId);
}
