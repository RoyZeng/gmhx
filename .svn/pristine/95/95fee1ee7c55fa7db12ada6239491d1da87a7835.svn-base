package com.gome.gmhx.service.orgmanage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxExamineSettlementTicket;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxSettlementVO;

public interface HxExamineSettlementService {

	List<Map<String, Object>> getExamineSettlementPageList(Page page);

	HxExamineSettlementTicket geExamineSettlementById(String settlementId);

	List<Map<String, Object>> getOtherFeeGridById(String settlementId);

	List<Map<String, Object>> getSettlementFeeDetailGrid(String settlementId);

	public void settlementFee(Calendar calender)throws Exception;

	String updateSettlement(SysUser sysUser, HxSettlementVO settlementVO);

	void examineSettlement(String settlementId);

	List<Map<String, Object>> getComfirmSettlementPageList(Page page);

	String confirmSettlement(SysUser sysUser, String workEntityId,String approveComment, String checkAgree);

	List<Map<String, Object>> getExamineSettlementHistoryPageList(Page page);

}
