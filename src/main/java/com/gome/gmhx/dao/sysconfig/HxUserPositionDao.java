package com.gome.gmhx.dao.sysconfig;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gome.gmhx.entity.HxPosition;
import com.gome.gmhx.entity.HxUserPosition;

@Repository("hxUserPositionDao")
public interface HxUserPositionDao {
	List<HxPosition> getPositionTree();
	List<HxPosition> getPositionTreeData(HxUserPosition userPosition);
	void addUserPosition(HxUserPosition userPosition);
	void deleteCompleteByUserLoginName(HxUserPosition userPosition);
	void deleteCompleteByPositionId(HxUserPosition userPosition);
}
