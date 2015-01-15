package com.gome.gmhx.dao.servicemanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("hxServiceProjectDao")
public interface HxServiceProjectDao {

	void insertProjects(Map<String, Object> projectsMap);

	List<Map<String, Object>> selectProjects(String productId);

	void deleteProjects(String productId);

}
