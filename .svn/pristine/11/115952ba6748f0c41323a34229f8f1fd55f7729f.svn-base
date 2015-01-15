package com.gome.gmhx.service.sysconfig.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.sysconfig.HxOrganizationDao;
import com.gome.gmhx.entity.HxOrganization;
import com.gome.gmhx.entity.HxOrganizationUnload;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;

@Service("hxOrganizationService")
public class HxOrganizationServiceImpl implements HxOrganizationService {
	@Resource
	private HxOrganizationDao hxOrganizationDao;

	@Override
	public List<Map<String, Object>> getOrganizationPageList(Page page) {
		return hxOrganizationDao.getOrganizationPageList(page);
	}

	@Override
	public void addOrganization(HxOrganization organization) {
		hxOrganizationDao.addOrganization(organization);
	}
	
	@Override
	public HxOrganization getOrganizationById(String orgCode) {
		return hxOrganizationDao.getOrganizationById(orgCode);
	}

	@Override
	public void updateOrganization(HxOrganization organization) {
		hxOrganizationDao.updateOrganization(organization);
	}

	@Override
	public List<Map<String, Object>> getOrganizationExport(
			HxOrganization organization) {
		return hxOrganizationDao.getOrganizationExport(organization);
	}

	@Override
	public List<HxOrganization> getOrganizationTree() {
		return hxOrganizationDao.getOrganizationTree();
	}

	@Override
	public String getChild(String rootId) {
		StringBuilder result = new StringBuilder();
		String children = rootId;
		int level = 0;
		while(children != null){
			if(level == 0){
				result.append(rootId);
			}else{
				result.append("," + children.toString());
			}
			children = hxOrganizationDao.getChild(children);
			level ++;
		}
		return result.toString();
	}

	@Override
	public HxOrganization getOrganizationByPeripheralId(String orgCode) {
		return hxOrganizationDao.getOrganizationByPeripheralId(orgCode);
	}

	@Override
	public void delOrganizationById(String orgCode) {
		hxOrganizationDao.delOrganizationById(orgCode);
	}

	@Override
	public void delOrganizationUnload(HxOrganizationUnload hxOrganizationUnload) {
		hxOrganizationDao.delOrganizationUnload(hxOrganizationUnload);
	}
	
	@Override
	public List<Map<String, Object>> queryAllOrganizationList() {
		return hxOrganizationDao.queryAllOrganizationList();
	}
	
	@Override
	public void addOrganizationUnload(HxOrganizationUnload hxOrganizationUnload) {
		hxOrganizationDao.addOrganizationUnload(hxOrganizationUnload);
	}

	@Override
	public List<Map<String, Object>> getAllOrganizationList() {
		return hxOrganizationDao.getAllOrganizationList();
	}

	@Override
	public void updateOrganizationUnload(
			HxOrganizationUnload hxOrganizationUnload) {
		hxOrganizationDao.updateOrganizationUnload(hxOrganizationUnload);
	}
	
	@Override
	public List<Map<String, Object>> queryHxOrganizationList() {
		return hxOrganizationDao.queryHxOrganizationList();
	}
	
	@Override
	public List<Map<String, Object>> getHxOrganizationList() {
		return hxOrganizationDao.getHxOrganizationList();
	}

	@Override
	public List<HxOrganization> getFittingStockTree() {
		return hxOrganizationDao.getFittingStockTree();
	}

	@Override
	public List<HxOrganization> getFittingStockTreeData(
			HxOrganization hxOrganization) {
		return hxOrganizationDao.getFittingStockTreeData(hxOrganization);
	}

	@Override
	public void deleteCompleteByOrgId(HxOrganization hxOrganization) {
		hxOrganizationDao.deleteCompleteByOrgId(hxOrganization);
	}

	@Override
	public void addFittingStockPart(HxOrganization hxOrganization) {
		hxOrganizationDao.addFittingStockPart(hxOrganization);
	}

	@Override
	public Map<String, String> insertOrganizations(
			List<HxOrganization> organizations){
	Map<String, String> resultMap = new HashMap<String, String>();
	String exists = "{";
	String success = "{";
	String failure = "{";
	
	for(int i=0 ; i< organizations.size() ; i++){
		HxOrganization hxOrganization = organizations.get(i);
		HxOrganization temp = hxOrganizationDao.getOrganizationById(hxOrganization.getOrgCode());
		if(temp!=null){
			exists +=(i+2)+",";
		}else{
			String orgCode =hxOrganization.getOrgCode().trim();
			String orgName = hxOrganization.getOrgName().trim();
			String orgParentId = hxOrganization.getOrgParentId().trim();
			String orgType = hxOrganization.getOrgType().trim();
			String orgManager = hxOrganization.getOrgManager().trim();
			String orgManagerPhone = hxOrganization.getOrgManagerPhone().trim();
			if(orgCode==null || ("").equals(orgCode.trim()) ||
					orgName==null || ("").equals(orgName.trim()) ||
							orgParentId==null || ("").equals(orgParentId.trim())||
									orgType==null || ("").equals(orgType.trim())||		
											orgManager==null || ("").equals(orgManager.trim())||	
													orgManagerPhone==null || ("").equals(orgManagerPhone.trim())){
				failure +=(i+2)+",";
			}else{
				try{
					hxOrganizationDao.addOrganization(hxOrganization);
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

}
