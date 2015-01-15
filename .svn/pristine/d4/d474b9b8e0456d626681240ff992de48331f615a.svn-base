package com.gome.gmhx.service.workflow;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.vo.AdditionalParamsVO;

public interface HxWorkFlowService {

	List<Map<String, Object>> getWorkPageList(Page page);

	List<Map<String, Object>> getTaskTrajeCttoryPageList(Page page);

	String processInstanceIdToVal(String processInstanceId);

	void updateJbpmWorkEntity(String workEntityId, String transactionpass,
			String approveComment, String orderType, String checkAgree, AdditionalParamsVO additionParams);

	List<Map<String, Object>> getWorkMonitoringPageList(Page page);

	void endProcessJbpmWorkEntity(String workEntityId,
			String transactiontermination);
	
	List<Map<String, Object>> exportMP08Excel(String listNumber);

	List<Map<String, Object>> exportBatchMP08Excel(Map<String, Object> map);

}
