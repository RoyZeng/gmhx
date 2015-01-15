package com.gome.gmhx.service.sysconfig.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.sysconfig.HxPositionDao;
import com.gome.gmhx.dao.sysconfig.HxPositionMenuDao;
import com.gome.gmhx.dao.sysconfig.HxUserPositionDao;
import com.gome.gmhx.entity.HXPositionMapping;
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.HxPosition;
import com.gome.gmhx.entity.HxPositionMenu;
import com.gome.gmhx.entity.HxUserPosition;
import com.gome.gmhx.entity.vo.HxPositionPermissionVO;
import com.gome.gmhx.service.sysconfig.HxPositionService;

@Service("hxPositionService")
public class HxPositionServiceImpl implements HxPositionService {
	@Resource
	private HxPositionDao hxPositionDao;
	
	@Resource
	private HxPositionMenuDao hxPositionMenuDao;
	
	@Resource
	private HxUserPositionDao hxUserPositionDao;

	@Override
	public List<Map<String, Object>> getPositionPageList(Page page) {
		return hxPositionDao.getPositionPageList(page);
	}

	@Override
	public void addPosition(HxPosition position) {
		hxPositionDao.addPosition(position);
	}
	
	@Override
	public HxPosition getPositionById(String positionCode) {
		return hxPositionDao.getPositionById(positionCode);
	}

	@Override
	public void updatePosition(HxPosition position) {
		hxPositionDao.updatePosition(position);
	}

	@Override
	public List<HxPosition> getPositionByCode(String positionCode) {
		return hxPositionDao.getPositionByCode(positionCode);
	}

	@Override
	public List<HxPosition> getPositionByCodeAndName(HXPositionMapping positionMapping) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> getPositionPageListBYCodeName(Page page) {
		return hxPositionDao.getPositionPageListBYCodeName(page);
	}

	@Override
	public List<Map<String, Object>> getOtherPositionListBYCodeName(Page page) {
		return hxPositionDao.getOtherPositionListBYCodeName(page);
	}


	@Override
	public List<EccGoodsCategory> getPositionCategoryTree() {
		return hxPositionDao.getPositionCategoryTree();
	}

	@Override
	public List<EccGoodsCategory> getPositionCategoryTreeData(String positionCode) {
		return hxPositionDao.getPositionCategoryTreeData(positionCode);
	}

	@Override
	public void addPositionCategory(HxPosition hp) {
		hxPositionDao.addPositionCategory(hp);
	}

	@Override
	public void deletePositionCategoryByPositionId(String positionCode) {
		hxPositionDao.deletePositionCategoryByPositionId(positionCode);
	}

	@Override
	public List<Map<String, Object>> getOtherPositionPageListBYCodeName(
			Page page) {
		return hxPositionDao.getOtherPositionPageListBYCodeName(page);
	}

	@Override
	public Map<String, String> insertPositions(List<HxPosition> positions) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String exists = "{";
		String success = "{";
		String failure = "{";
		
		for(int i=0 ; i< positions.size() ; i++){
			HxPosition position = positions.get(i);
			int count = hxPositionDao.checkPositionBycode(position.getPositionCode());
			if(count>0){
				exists +=(position.getPositionCode())+",";
			}else{
				String code =position.getPositionCode();
				String name=position.getPositionName();
				String orgid=position.getOrgId();
				String type=position.getPositionType();
				String orgin=position.getPositionOrigin();
				if(code==null || ("").equals(code.trim()) ||
						name==null || ("").equals(name.trim()) ||
								orgid==null || ("").equals(orgid.trim()) ||
										type==null || ("").equals(type.trim()) ||
												orgin==null || ("").equals(orgin.trim())){
					failure +=(i+2)+",";
				}else{
					try{
						hxPositionDao.addPosition(position);
						success +=(i+2)+",";
					}catch(Exception e){
						failure +=(i+2)+",";
					}
				}
			}
		}
		resultMap.put("exists", exists.length()==1 ? exists+"}":exists.subSequence(0, exists.length()-1)+"}");
		resultMap.put("success", success.length()==1 ? success+"}":success.subSequence(0, success.length()-1)+"}");
		resultMap.put("failure", failure.length()==1 ? failure+"}":failure.subSequence(0, failure.length()-1)+"}");
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> getPositionExport(HxPosition hxPosition) {
		return hxPositionDao.getPositionExport(hxPosition);
	}

	@Override
	public Map<String, String> insertPositionPermissions(
			List<HxPositionPermissionVO> positionPermissionVOs) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String exists = "{";
		String success = "{";
		String failure = "{";

		for (int i = 0; i < positionPermissionVOs.size(); i++) {
			HxPositionPermissionVO hxPositionPermissionVO = positionPermissionVOs
					.get(i);
			String positionCode = hxPositionPermissionVO.getPositionCode();
			String positionCategoryId = hxPositionPermissionVO
					.getPositionCategoryId();
			String menuId = hxPositionPermissionVO.getMenuId();
			String fittingAuthId = hxPositionPermissionVO.getFittingAuthId();
			String jbpmRoleCode = hxPositionPermissionVO.getJbpmRoleCode();
			String userLoginName = hxPositionPermissionVO.getUserLoginName();
			String fromType = hxPositionPermissionVO.getFromType();
			if (positionCode == null || ("").equals(positionCode.trim())) {
				failure += (i + 2) + ",";
			} else {
				try {
					if (StringUtils.isNotBlank(positionCategoryId)) {
						String[] positionCategoryIdArr = positionCategoryId
								.split(",");
						if (positionCategoryIdArr.length > 0) {
							hxPositionDao.deletePositionCategoryByPositionId(positionCode);//添加前先删除
							for (int m = 0; m < positionCategoryIdArr.length; m++) {
								HxPosition hxPosition = new HxPosition();
								hxPosition.setPositionCode(positionCode);
								hxPosition.setPositionCategoryId(positionCategoryIdArr[m]);
								hxPositionDao.addPositionCategory(hxPosition);
							}
						}
					}
					if (StringUtils.isNotBlank(menuId)) {
						String[] menuIdArr = menuId.split(",");
						HashSet<String> set = new HashSet<String>();
						if (menuIdArr.length > 0) {
							hxPositionMenuDao.deleteCompleteByPositionId(positionCode);//添加前先删除
							for (int m = 0; m < menuIdArr.length; m++) {
								String id = menuIdArr[m];
								//递归查询所有的父节点
								String parentIds = hxPositionDao.getParentList(id);//根据函数查询叶子节点所有的父节点id
								if(StringUtils.isNotBlank(parentIds)){
									String[] parentIdsArr =  parentIds.split(",");
									for(int n=0;n<parentIdsArr.length;n++){
										String parentId = parentIdsArr[n];
										set.add(parentId);
									}
								}
							}
							Iterator<String> iterator=set.iterator();		
							while(iterator.hasNext()){	
								String menuIdTemp = iterator.next();
								if(!"0".equals(menuIdTemp)){
									HxPositionMenu hxPositionMenu = new HxPositionMenu();
									hxPositionMenu.setPositionId(positionCode);
									hxPositionMenu.setMenuId(menuIdTemp);
									hxPositionMenuDao.addPositionMenu(hxPositionMenu);	
								}
							}
						}
						
					}
					if (StringUtils.isNotBlank(fittingAuthId)) {
						String[] fittingAuthIdArr = fittingAuthId.split(",");
						if (fittingAuthIdArr.length > 0) {
							hxPositionMenuDao.deleteFittingAuthByPositionId(positionCode);//添加前先删除
							for (int m = 0; m < fittingAuthIdArr.length; m++) {
								HxPositionMenu hxPositionMenu = new HxPositionMenu();
								hxPositionMenu.setPositionId(positionCode);
								hxPositionMenu.setFittingAuthId(fittingAuthIdArr[m]);
								hxPositionMenuDao.addFittingAuth(hxPositionMenu);
							}
						}
					}
					if (StringUtils.isNotBlank(userLoginName)) {
						String[] userLoginNameArr = userLoginName.split(",");
						if (userLoginNameArr.length > 0) {
							HxUserPosition upTemp = new HxUserPosition();
							upTemp.setPositionId(positionCode);
							hxUserPositionDao.deleteCompleteByPositionId(upTemp);//添加前先删除
							int from_type = Integer.valueOf(fromType);//用户来源 3（系统创建）  2（网点）  1 （国美）
							for (int m = 0; m < userLoginNameArr.length; m++) {
								HxUserPosition up = new HxUserPosition();
								up.setPositionId(positionCode);
								up.setUserLoginName(userLoginNameArr[m]);
								up.setFromType(from_type);
								hxUserPositionDao.addUserPosition(up);
							}
						}
					}
					success += (i + 2) + ",";
				} catch (Exception e) {
					failure += (i + 2) + ",";
				}
			}
		}
		resultMap.put("exists",exists.length() == 1 ? exists + "}" : exists.subSequence(0,exists.length() - 1) + "}");
		resultMap.put("success", success.length() == 1 ? success + "}": success.subSequence(0, success.length() - 1) + "}");
		resultMap.put("failure", failure.length() == 1 ? failure + "}": failure.subSequence(0, failure.length() - 1) + "}");
		return resultMap;
	}

	@Override
	public void deletePositionRoleByPositionId(String positionCode) {
		hxPositionDao.deletePositionRoleByPositionId(positionCode);
	}

	@Override
	public void addPositionRole(HxPosition hp) {
		hxPositionDao.addPositionRole(hp);
	}

	@Override
	public List<Map<String, Object>> exportPositionRoleJbpmExcel(HxPosition hxPosition) {
		return hxPositionDao.exportPositionRoleJbpmExcel(hxPosition);
	}

}
