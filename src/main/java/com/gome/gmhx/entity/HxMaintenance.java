package com.gome.gmhx.entity;

import java.util.Date;

public class HxMaintenance {
	
	private String category;
	private String classifyName;
	private String classifyCode;
	private String maintenanceCosts;
	private String arrangeNumber;
	private String chose;
	private String validity;
	private String parentCode;
	private String modifier;
	private Date modDate;
	private String parentClassify;
	private String wetEnable;
	private String faultName;
	private String PNumber;
	private String wetUnion;
	private String faultCode;
	private String note;
	
	public String getPNumber() {
		return PNumber;
	}
	public void setPNumber(String pNumber) {
		PNumber = pNumber;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public String getClassifyCode() {
		return classifyCode;
	}
	public void setClassifyCode(String classifyCode) {
		this.classifyCode = classifyCode;
	}
	public String getMaintenanceCosts() {
		return maintenanceCosts;
	}
	public void setMaintenanceCosts(String maintenanceCosts) {
		this.maintenanceCosts = maintenanceCosts;
	}
	public String getArrangeNumber() {
		return arrangeNumber;
	}
	public void setArrangeNumber(String arrangeNumber) {
		this.arrangeNumber = arrangeNumber;
	}
	public String getChose() {
		return chose;
	}
	public void setChose(String chose) {
		this.chose = chose;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public String getParentClassify() {
		return parentClassify;
	}
	public void setParentClassify(String parentClassify) {
		this.parentClassify = parentClassify;
	}
	public String getWetEnable() {
		return wetEnable;
	}
	public void setWetEnable(String wetEnable) {
		this.wetEnable = wetEnable;
	}
	public String getFaultName() {
		return faultName;
	}
	public void setFaultName(String faultName) {
		this.faultName = faultName;
	}
	public String getWetUnion() {
		return wetUnion;
	}
	public void setWetUnion(String wetUnion) {
		this.wetUnion = wetUnion;
	}
	public String getFaultCode() {
		return faultCode;
	}
	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public HxMaintenance(){
		this.chose="1";
		this.validity="1";
		this.wetEnable="1";
	}

}
