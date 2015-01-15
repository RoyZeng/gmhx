package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxProductDetail;

public interface HxProductDetailService {
	
	List<Map<String, Object>> getHxProductDetailPageList(Page page);
	
	HxProductDetail getHxProductDetailById(String id);

	void updateHxProductDetail(HxProductDetail hxProductDetail);

	void addHxProductDetail(HxProductDetail hxProductDetail);

}
