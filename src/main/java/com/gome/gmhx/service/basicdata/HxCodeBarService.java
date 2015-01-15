package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxCodeBar;

public interface HxCodeBarService {

	List<Map<String, Object>> getHxCodeBarPageList(Page page);

	int validateInnerCode1(String code);

	int validateInnerCode2(String code);

	int validateOuterCode(String code);

	void addHxCodeBar(HxCodeBar hxCodeBar);

	int validateModel(String model);

	HxCodeBar getHxCodeBarById(String id);

	void updateHxCodeBar(HxCodeBar hxCodeBar);

}
