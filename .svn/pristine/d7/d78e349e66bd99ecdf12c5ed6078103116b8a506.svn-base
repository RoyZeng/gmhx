package com.gome.gmhx.dao.orgmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxExamineSettlementTicket;
import com.gome.gmhx.entity.HxSettlementDetail;
import com.gome.gmhx.entity.HxSettlementDetailOther;

@Repository("hxExamineSettlementTicketDao")
public interface HxExamineSettlementTicketDao {

	List<Map<String, Object>> getExamineSettlementPageList(Page page);

	HxExamineSettlementTicket getExamineSettlementById(String settlementId);

	List<Map<String, Object>> getOtherFeeGridById(String settlementId);

	List<Map<String, Object>> getSettlementFeeDetailGrid(String settlementId);

	void insertExamineSettlementTicket(HxExamineSettlementTicket est);

	void insertSettlementDetails(List<HxSettlementDetail> hsds);

	void updateExamineSettlementTicket(HxExamineSettlementTicket examineSettlementTicket);

	void deleteOthersById(String settlementId);

	void insertOthers(Map<String, Object> otherMap);

	Integer getMaxSequence(HxExamineSettlementTicket est);

}
