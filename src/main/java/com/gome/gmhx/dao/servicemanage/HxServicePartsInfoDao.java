package com.gome.gmhx.dao.servicemanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.gmhx.entity.HxServicePartsInfo;

@Repository("hxServicePartsInfoDao")
public interface HxServicePartsInfoDao {

	public void insertParts(Map<String, Object> partsMap);

	public List<Map<String, Object>> selectPartsByPrimaryKey(String serviceId);

	public void deletePartsById(String serviceId);
	
	public List<HxServicePartsInfo> selectPartsById(String serviceId);
	
	public List<String> selectFittingByMachineType(String machinetype);

}
