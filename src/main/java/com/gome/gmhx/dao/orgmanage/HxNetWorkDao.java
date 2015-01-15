package com.gome.gmhx.dao.orgmanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxNetWork;


@Repository("hxNetWorkDao")
public interface HxNetWorkDao {
	
	List<Map<String, Object>> getNetWorkPageList(Page page);
	
	HxNetWork getShowById(String  aa);

}
