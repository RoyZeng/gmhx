package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxFree;

public interface HxFreeDao {
	 List<Map<String,Object>> getFreePageList(Page page);
	
	 void addHxFree(HxFree free);
	  
	 HxFree getHxFreeById(String aa);
	  
     HxFree getShowById(String id);
	  
	 void updateHxFree(HxFree free);
	  
	 List<Map<String, Object>> getHxFreeExport(HxFree free); //导出
	  
	 public int insertHxFree(List<HxFree> free);  //导入操作
	 
	 HxFree selectHxFreePrimaryKey(String arrangeNumber);   //导入查询
	 
	 void insertFreeBatch(List<Object> list);

}
