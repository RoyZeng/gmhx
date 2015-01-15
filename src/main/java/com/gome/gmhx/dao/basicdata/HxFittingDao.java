package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxFitting;

@Repository("hxFittingDao")
public interface HxFittingDao {

	List<Map<String, Object>> getHxFittingPageList(Page page);

	HxFitting getHxFittingById(String id);

	void addHxFitting(HxFitting hxFitting);

	void addHxFittingModel(Map<String, Object> suitMap);

	void addHxFittingProvider(Map<String, Object> providerMap);
	
	void addHxFittingFaultCode(Map<String, Object> faultCodeMap);

	HxFitting getHxFittingByFittingCode(String fittingCode);

	List<Map<String, Object>> getHxFittingModelByFittingCode(String fittingCode);

	List<Map<String, Object>> getHxFittingProviderByFittingCode(String fittingCode);
	
	List<Map<String, Object>> getHxFittingFaultCodeByFittingCode(String fittingCode);

	void updateHxFitting(HxFitting hxFitting);

	void deleteModelByFittingCode(String fittingCode);

	void deleteProviderByFittingCode(String fittingCode);
	
	void deleteFaultCodeByFittingCode(String fittingCode);

	void insertFittingDetailBatch(List<Object> list);
	
	void insertFittingModelBatch(List<Object> list);

}
