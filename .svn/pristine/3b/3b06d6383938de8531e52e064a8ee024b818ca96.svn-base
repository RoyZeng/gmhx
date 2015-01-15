package com.gome.gmhx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.Demo;

@Repository("demoDao")
public interface DemoDao {
	List<Map<String, Object>> getDemoPageList(Page page);
	
	void addDemo(Demo demo);
	
	Demo getDemoById(String aa);
	
	void updateDemo(Demo demo);
	
	List<Map<String, Object>> getDemoExport(Demo demo);
}
