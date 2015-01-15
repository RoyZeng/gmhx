package com.gome.gmhx.entity.vo;

import java.util.List;

import com.gome.gmhx.entity.HxExamineSettlementTicket;
import com.gome.gmhx.entity.HxSettlementDetail;
import com.gome.gmhx.entity.HxSettlementDetailOther;

public class HxSettlementVO {
	
	private HxExamineSettlementTicket examineSettlementTicket;
	
	private List<HxSettlementDetailOther> settlementDetailOthers;

	public HxExamineSettlementTicket getExamineSettlementTicket() {
		return examineSettlementTicket;
	}

	public List<HxSettlementDetailOther> getSettlementDetailOthers() {
		return settlementDetailOthers;
	}

	public void setExamineSettlementTicket(
			HxExamineSettlementTicket examineSettlementTicket) {
		this.examineSettlementTicket = examineSettlementTicket;
	}

	public void setSettlementDetailOthers(
			List<HxSettlementDetailOther> settlementDetailOthers) {
		this.settlementDetailOthers = settlementDetailOthers;
	}

}
