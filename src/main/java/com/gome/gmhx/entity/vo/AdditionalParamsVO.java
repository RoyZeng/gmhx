package com.gome.gmhx.entity.vo;

import java.util.List;

import com.gome.gmhx.entity.HxMaterialDetail;

public class AdditionalParamsVO {
	private String workEntityId;
	private String checkAgree;
	private String approveComment;
	private String orderType;
	private List<HxMaterialDetail> details;
	
	public String getWorkEntityId() {
		return workEntityId;
	}

	public void setWorkEntityId(String workEntityId) {
		this.workEntityId = workEntityId;
	}

	public String getCheckAgree() {
		return checkAgree;
	}

	public void setCheckAgree(String checkAgree) {
		this.checkAgree = checkAgree;
	}

	public String getApproveComment() {
		return approveComment;
	}

	public void setApproveComment(String approveComment) {
		this.approveComment = approveComment;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}


	public List<HxMaterialDetail> getDetails() {
		return details;
	}

	public void setDetails(List<HxMaterialDetail> details) {
		this.details = details;
	}
}
