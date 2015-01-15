package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxSetupeFree;

public interface HxSetupeFreeDao {
	 List<Map<String,Object>> getSetupFreePageList(Page page);
	
	 void addHxSetupeFree(HxSetupeFree free);
	  
	 HxSetupeFree getHxSetupeFreeById(String aa); 
	  
	 HxSetupeFree getShowById(String id); 
	  
	 void updateHxSetupeFree(HxSetupeFree free);
	  
	 List<Map<String, Object>> getHxSetupeFreeExport(HxSetupeFree free); //导出
	  
	 public int insertHxSetupeFree(List<HxSetupeFree> free);  //导入操作
	 
	 HxSetupeFree selectHxSetupeFreePrimaryKey(String arrangeNumber);   //导入查询

	 void insertSetupeFreeBatch(List<Object> list);

}
