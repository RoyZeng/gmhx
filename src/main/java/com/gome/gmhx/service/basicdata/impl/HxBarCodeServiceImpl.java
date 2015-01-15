package com.gome.gmhx.service.basicdata.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxBarCodeDao;
import com.gome.gmhx.entity.BarCode;
import com.gome.gmhx.service.basicdata.HxBarCodeService;

@Service("hxBarCodeService")
public class HxBarCodeServiceImpl implements HxBarCodeService {
	@Resource
	private HxBarCodeDao hxBarCodeDao;

	@Override
	public List<Map<String, Object>> getHxBarCodePageList(Page page) {
		return hxBarCodeDao.getHxBarCodePageList(page);
	}

	@Override
	public List<Map<String, Object>> getBarCodeExport(BarCode barCode) {
		return hxBarCodeDao.getBarCodeExport(barCode);
	}

	@Override
	public void insertBarCode(BarCode barCode) {
		hxBarCodeDao.insertBarCode(barCode);
	}

	@Override
	public void insertBarCodes(Map<String, List<BarCode>> barCodes) {
		hxBarCodeDao.insertMapBarCodes(barCodes);
		
	}

	@Override
	public Map<String, String> insertBarCodes(List<BarCode> barCodes) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String exists = "{";
		String success = "{";
		String failure = "{";
		
		for(int i=0 ; i< barCodes.size() ; i++){
			BarCode barCode = barCodes.get(i);
			BarCode temp = hxBarCodeDao.selectBarCodeByPrimaryKey(barCode.getBarCode());
			if(temp!=null){
				exists +=(i+2)+",";
			}else{
				String code =barCode.getBarCode().trim();
				String machineType = barCode.getMachineType().trim();
				if("".equals(code)||"".equals(machineType)){
					failure +=(i+2)+",";
				}else{
					try{
						hxBarCodeDao.insertBarCode(barCode);
						success +=(i+2)+",";
					}catch(Exception e){
						failure +=(i+2)+",";
					}
				}
			}
		}
		resultMap.put("exists", exists.length()==1 ? exists+"}":exists.subSequence(0, exists.length()-1)+"}");
		resultMap.put("success", success.length()==1 ? success+"}":success.subSequence(0, success.length()-1)+"}");
		resultMap.put("failure", failure.length()==1 ? failure+"}":failure.subSequence(0, failure.length()-1)+"}");
		return resultMap;
	}

	@Override
	public List<BarCode> getBarCodeByMachineTypeAndBarCode(String machineType,
			String barCode) {
		BarCode barcode = new BarCode();
		barcode.setBarCode(barCode);
		barcode.setMachineType(machineType);
		return this.hxBarCodeDao.getBarCodeByMachineTypeAndBarCode(barcode);
	}

	@Override
	public List<Map<String, Object>> getHxTempBarCodePageList(Page page) {
		// TODO Auto-generated method stub
		return this.hxBarCodeDao.getHxTempBarCodePageList(page);
	}

}
