package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.gmhx.entity.HxProductClassify;

@Repository("hxProductClassifyDao")
public interface HxProductClassifyDao {
	List<HxProductClassify> getHxProductClassifyTree();
	
	void createHxProductClassify(HxProductClassify hxProductClassify);
	
	void updateHxProductClassify(Map<String, Object> map);
	
	void delHxProductClassify(String classifyCode);
	
	HxProductClassify getHxProductClassifyByCassifyCode(String cassifyCode);

	List<Map> getHxProductClassifyTreeData(String roleId);
}
