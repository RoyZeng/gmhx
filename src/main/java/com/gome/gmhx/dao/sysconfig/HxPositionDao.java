package com.gome.gmhx.dao.sysconfig;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.HxPosition;

@Repository("hxPositionDao")
public interface HxPositionDao {
	
	List<Map<String, Object>> getPositionPageList(Page page);
	
	void addPosition(HxPosition position);
	
	HxPosition getPositionById(String positionId);
	
	void updatePosition(HxPosition position);

	List<HxPosition> getPositionByCode(String positionId);

	List<EccGoodsCategory> getPositionCategoryTree();

	List<EccGoodsCategory> getPositionCategoryTreeData(String positionId);

	void addPositionCategory(HxPosition hp);

	void deletePositionCategoryByPositionId(String positionId);

	List<Map<String, Object>> getPositionPageListBYCodeName(Page page);

	List<Map<String, Object>> getOtherPositionListBYCodeName(Page page);

	List<Map<String, Object>> getOtherPositionPageListBYCodeName(Page page);

	int checkPositionBycode(String positionCode);

	List<Map<String, Object>> getPositionExport(HxPosition hxPosition);

	String getParentList(String id);
	
	String getCategoryParentList(String id);

	void deletePositionRoleByPositionId(String positionCode);

	void addPositionRole(HxPosition hp);
	
	void insertPositionBatch(List<Object> list);
	
	void deletePositionRoleByPositionIdBatch(List<Object> list);
	
	void insertPositionRoleBatch(List<Object> list);
	
	void insertPositionMappingBatch(List<Object> list);

	List<Map<String, Object>> exportPositionRoleJbpmExcel(HxPosition hxPosition);
}
