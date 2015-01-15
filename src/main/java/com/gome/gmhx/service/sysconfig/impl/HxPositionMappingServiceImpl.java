package com.gome.gmhx.service.sysconfig.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.dao.sysconfig.HxPositionMappingDao;
import com.gome.gmhx.entity.HXPositionMapping;
import com.gome.gmhx.service.sysconfig.HxPositionMappingService;

@Service("hxPositionMappingService")
public class HxPositionMappingServiceImpl implements HxPositionMappingService {
	@Resource
	private HxPositionMappingDao hxPositionMappingDao;

	@Override
	public List<Map<String, Object>> getPositionMappingPageList(Page page) {
		
		return hxPositionMappingDao.getPositionMappingPageList(page);
	}

	@Override
	public void addPositionMapping(HXPositionMapping positionMapping) {
		hxPositionMappingDao.addPositionMapping(positionMapping);
	}

	@Override
	public void updatePositionMapping(HXPositionMapping positionMapping) {
		hxPositionMappingDao.updatePositionMapping(positionMapping);
	}

	@Override
	public List<HXPositionMapping> getPositionMappingByCodeAndName(HXPositionMapping positionMapping) {
		hxPositionMappingDao.getPositionByCodeAndName(positionMapping);
		return null;		
	}

	@Override
	public void connect(HXPositionMapping positionMapping,String positionIds[],String type) {
		if("1".equals(type)){
			hxPositionMappingDao.deletePMByCodeName(positionMapping);
			for(String positionId:positionIds){
	        	positionMapping.setId(UUIDGenerator.getUUID());//设置主键
				positionMapping.setPositionCode(positionId);
				hxPositionMappingDao.addPositionMapping(positionMapping);
			}
		}else{
			for(String positionId:positionIds){
				positionMapping.setPositionCode(positionId);
				hxPositionMappingDao.deletePMByPositionId(positionMapping);
				Integer count=hxPositionMappingDao.selectPMByCodeName(positionMapping);
				if(count.intValue()<1){
					positionMapping.setPositionCode(null);
					positionMapping.setId(UUIDGenerator.getUUID());//设置主键
					hxPositionMappingDao.addPositionMapping(positionMapping);
				}
			}
		}
		
	}

	@Override
	public void delete(String[] codesArray) {
		for(String code:codesArray){
			hxPositionMappingDao.delete(code);
		}
	}
}
