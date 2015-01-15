package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.BarCode;

public interface HxBarCodeService {
	public List<Map<String, Object>> getHxBarCodePageList(Page page);

	public List<Map<String, Object>> getBarCodeExport(BarCode barCode);
	
	public void insertBarCode(BarCode barCode);
	
	public Map<String,String> insertBarCodes(List<BarCode> barCodes);
	
	public void insertBarCodes(Map<String,List<BarCode>> barCodes);
	
	public  List<BarCode> getBarCodeByMachineTypeAndBarCode(String machineType,String barCode);

	public List<Map<String, Object>> getHxTempBarCodePageList(Page page);
	
}
