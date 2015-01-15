package com.gome.gmhx.service.sysconfig;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HXPositionMapping;
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.HxPosition;
import com.gome.gmhx.entity.vo.HxPositionPermissionVO;

public interface HxPositionService {
	
	List<Map<String, Object>> getPositionPageList(Page page);
	
	void addPosition(HxPosition position);
	
	HxPosition getPositionById(String positionCode);
	
	void updatePosition(HxPosition position);

	List<HxPosition> getPositionByCode(String positionCode);

	List<HxPosition> getPositionByCodeAndName(HXPositionMapping positionMapping);

	List<Map<String, Object>> getPositionPageListBYCodeName(Page page);

	List<Map<String, Object>> getOtherPositionListBYCodeName(Page page);

	List<EccGoodsCategory> getPositionCategoryTree();

	List<EccGoodsCategory> getPositionCategoryTreeData(String positionCode);

	void addPositionCategory(HxPosition hp);

	void deletePositionCategoryByPositionId(String positionCode);

	List<Map<String, Object>> getOtherPositionPageListBYCodeName(Page page);

	Map<String, String> insertPositions(List<HxPosition> positions);

	List<Map<String, Object>> getPositionExport(HxPosition hxPosition);

	Map<String, String> insertPositionPermissions(
			List<HxPositionPermissionVO> positionPermissionVOs);

	void deletePositionRoleByPositionId(String positionCode);

	void addPositionRole(HxPosition hp);

	List<Map<String, Object>> exportPositionRoleJbpmExcel(HxPosition hxPosition);
}
