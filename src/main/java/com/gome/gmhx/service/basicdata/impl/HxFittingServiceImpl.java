package com.gome.gmhx.service.basicdata.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxFittingDao;
import com.gome.gmhx.entity.HxFitting;
import com.gome.gmhx.entity.HxFittingFaultCode;
import com.gome.gmhx.entity.HxFittingModel;
import com.gome.gmhx.entity.HxFittingProvider;
import com.gome.gmhx.entity.vo.HxFittingVO;
import com.gome.gmhx.service.basicdata.HxFittingService;

@Service("hxFittingService")
public class HxFittingServiceImpl implements HxFittingService {
	
	@Resource
	private HxFittingDao hxFittingDao;

	@Override
	public List<Map<String, Object>> getHxFittingPageList(Page page) {
		return hxFittingDao.getHxFittingPageList(page);
	}

	@Override
	public void addHxFitting(HxFittingVO hxFittingVO) {
		HxFitting hxFitting = hxFittingVO.getFitting();
		List<HxFittingModel> suits = hxFittingVO.getSuits();
		List<HxFittingProvider> providers = hxFittingVO.getProviders();
		List<HxFittingFaultCode> faultCodes = hxFittingVO.getFaultCodes();
		
		hxFittingDao.addHxFitting(hxFitting);
		
		if(suits != null && !suits.isEmpty()){
			Map<String, Object> suitMap = new HashMap<String, Object>();
			suitMap.put("fittingCode", hxFitting.getFittingCode());
			suitMap.put("list", suits);
			hxFittingDao.addHxFittingModel(suitMap);
		}
		
		if(providers != null && !providers.isEmpty()){
			Map<String, Object> providerMap = new HashMap<String, Object>();
			providerMap.put("fittingCode", hxFitting.getFittingCode());
			providerMap.put("list", hxFittingVO.getProviders());
			hxFittingDao.addHxFittingProvider(providerMap);
		}
		
		if(faultCodes != null && !faultCodes.isEmpty()){
			Map<String, Object> faultCodeMap = new HashMap<String, Object>();
			faultCodeMap.put("fittingCode", hxFitting.getFittingCode());
			faultCodeMap.put("list", hxFittingVO.getFaultCodes());
			hxFittingDao.addHxFittingFaultCode(faultCodeMap);
		}
	}


	public HxFitting getHxFittingByFittingCode(String fittingCode) {
		return hxFittingDao.getHxFittingByFittingCode(fittingCode);
	}

	public List<Map<String, Object>> getHxFittingModelByFittingCode(String fittingCode) {
		return hxFittingDao.getHxFittingModelByFittingCode(fittingCode);
	}

	public List<Map<String, Object>> getHxFittingProviderByFittingCode(String fittingCode) {
		return hxFittingDao.getHxFittingProviderByFittingCode(fittingCode);
	}

	@Override
	public void updateHxFitting(HxFittingVO hxFittingVO) {
		HxFitting hxFitting = hxFittingVO.getFitting();
		List<HxFittingModel> suits = hxFittingVO.getSuits();
		List<HxFittingProvider> providers = hxFittingVO.getProviders();
		List<HxFittingFaultCode> faultCodes = hxFittingVO.getFaultCodes();
		
		hxFitting.setUpdateTime(new Date());
		hxFittingDao.updateHxFitting(hxFitting);
		
		hxFittingDao.deleteModelByFittingCode(hxFitting.getFittingCode());
		if(suits != null && !suits.isEmpty()){
			Map<String, Object> suitMap = new HashMap<String, Object>();
			suitMap.put("fittingCode", hxFitting.getFittingCode());
			suitMap.put("list", suits);
			hxFittingDao.addHxFittingModel(suitMap);
		}
		
		hxFittingDao.deleteProviderByFittingCode(hxFitting.getFittingCode());
		if(providers != null && !providers.isEmpty()){
			Map<String, Object> providerMap = new HashMap<String, Object>();
			providerMap.put("fittingCode", hxFitting.getFittingCode());
			providerMap.put("list", hxFittingVO.getProviders());
			hxFittingDao.addHxFittingProvider(providerMap);
		}
		
		hxFittingDao.deleteFaultCodeByFittingCode(hxFitting.getFittingCode());
		if(faultCodes != null && !faultCodes.isEmpty()){
			Map<String, Object> faultCodeMap = new HashMap<String, Object>();
			faultCodeMap.put("fittingCode", hxFitting.getFittingCode());
			faultCodeMap.put("list", hxFittingVO.getFaultCodes());
			hxFittingDao.addHxFittingFaultCode(faultCodeMap);
		}
	}

	@Override
	public List<Map<String, Object>> getHxFittingFaultCodeByFittingCode(
			String fittingCode) {
		return hxFittingDao.getHxFittingFaultCodeByFittingCode(fittingCode);
	}

	
}
