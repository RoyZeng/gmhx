package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.BarCode;

@Repository("hxBarCodeDao")
public interface HxBarCodeDao {
	public List<Map<String, Object>> getHxBarCodePageList(Page page);

	public List<Map<String, Object>> getBarCodeExport(BarCode barCode);
	
	public BarCode selectBarCodeByPrimaryKey(String barCode);
	
	public int updateBarCodeByPrimaryKey(BarCode barCode);
	
	public int insertBarCode(BarCode barCode);

	public void insertMapBarCodes(Map<String,List<BarCode>> barCodes);
	
	void insertBarCodeBatch(List<Object> list);

	public List<BarCode> getBarCodeByMachineTypeAndBarCode(BarCode barCode);
	
	public List<Map> getTempBarCode(String barCode);
	
	public void addTempBarCode(Map tmplBarCode);
	
	public void setIsSubmit(Map param);

	public List<Map<String, Object>> getHxTempBarCodePageList(Page page);

}
