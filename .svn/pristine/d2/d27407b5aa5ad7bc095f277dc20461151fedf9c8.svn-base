package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.Brand;


@Repository("brandDao")
public interface BrandDao {
	
  List<Map<String,Object>> getBrandPageList(Page page);
  
  void addBrand(Brand rand);
  
  Brand getBrandById(String aa);
  
  Brand getShowById(String id);
  
  void updateBrand(Brand brand);
  
  List<Map<String, Object>> getBrandExport(Brand brand);
  
  void insertBrandBatch(List<Object> list);

List<Map> getAllBrand();

void setIsUse(Brand brand);
}
