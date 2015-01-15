package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxFitting;
import com.gome.gmhx.entity.vo.HxFittingVO;

public interface HxFittingService {

	List<Map<String, Object>> getHxFittingPageList(Page page);

	void addHxFitting(HxFittingVO hxFittingVO);

	void updateHxFitting(HxFittingVO hxFittingVO);

	HxFitting getHxFittingByFittingCode(String fittingCode);

	List<Map<String, Object>> getHxFittingModelByFittingCode(String fittingCode);

	List<Map<String, Object>> getHxFittingProviderByFittingCode(String fittingCode);

	List<Map<String, Object>> getHxFittingFaultCodeByFittingCode(
			String fittingCode);

}
