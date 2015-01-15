package com.gome.gmhx.service.orgmanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxNetWork;

public interface HxNetWorkService {
	
   List<Map<String, Object>> getNetWorkPageList(Page page);
	
   HxNetWork getShowById(String  aa);

}
