package com.gome.gmhx.entity.vo;

import java.util.List;

import com.gome.gmhx.entity.HxMaterialDetail;
import com.gome.gmhx.entity.HxMaterialManage;

public class HxMaterialVO {
	private String authId;
	private HxMaterialManage manage;
	private List<HxMaterialDetail> detail;
	private String moveDirection;
	
	public String getMoveDirection() {
		return moveDirection;
	}
	public void setMoveDirection(String moveDirection) {
		this.moveDirection = moveDirection;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public HxMaterialManage getManage() {
		return manage;
	}
	public void setManage(HxMaterialManage manage) {
		this.manage = manage;
	}
	public List<HxMaterialDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<HxMaterialDetail> detail) {
		this.detail = detail;
	}
}
