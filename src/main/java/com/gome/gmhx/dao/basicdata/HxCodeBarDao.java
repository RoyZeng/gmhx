package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxCodeBar;

@Repository("hxCodeBarDao")
public interface HxCodeBarDao {

	List<Map<String, Object>> getHxCodeBarPageList(Page page);

	int validateInnerCode1(String code);

	int validateInnerCode2(String code);

	int validateOuterCode(String code);

	void addHxCodeBar(HxCodeBar hxCodeBar);

	int validateModel(String model);

	HxCodeBar getHxCodeBarById(String id);

	Object updateHxCodeBar(HxCodeBar hxCodeBar);
	
	List<HxCodeBar> getHxCodeBarByWholeModel(String wholeModal);
	
	List<HxCodeBar> getHxCodeBarByMachineCode(String machineCode);
	
	void insertCodeBarBatch(List<Object> list);

}
