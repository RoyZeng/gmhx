package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.gmhx.entity.HxProductClassify;

public interface HxProductClassifyService {
	List<HxProductClassify> getHxProductClassifyTree();
	
	void createHxProductClassify(HxProductClassify hxProductClassify);
	
	void updateHxProductClassify(Map<String, Object> map);
	
	void delHxProductClassify(String classifyCode);

	List<Map> getHxProductClassifyTreeData(String roleId);
}
