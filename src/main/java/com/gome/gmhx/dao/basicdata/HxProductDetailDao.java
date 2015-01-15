package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxProductDetail;

@Repository("hxProductDetailDao")
public interface HxProductDetailDao {
	List<Map<String, Object>> getHxProductDetailPageList(Page page);
	
	HxProductDetail getHxProductDetailById(String id);

	void updateHxProductDetail(HxProductDetail hxProductDetail);

	void addHxProductDetail(HxProductDetail hxProductDetail);
	
	List<HxProductDetail> getHxProductDetailByMode(String mode);
	
}
