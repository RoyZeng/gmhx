package com.gome.gmhx.entity;

import java.util.Date;

import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.jbpm.CurrentSysUser;

public class HxFree {
	private String model;
	private String expenseStandard;
	private String managerFee;
	private String monthlyBonus;
	private String freeId;
	private String brand;
	private Date createDate;
	private String founder;
	private Date modifiedDate;
	private String modified;
	private String modelId;
	
	
	public String getFreeId() {
		return freeId;
	}
	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getFounder() {
		return founder;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	
	public String getExpenseStandard() {
		return expenseStandard;
	}
	public void setExpenseStandard(String expenseStandard) {
		this.expenseStandard = expenseStandard;
	}
	public String getManagerFee() {
		return managerFee;
	}
	public void setManagerFee(String managerFee) {
		this.managerFee = managerFee;
	}
	public String getMonthlyBonus() {
		return monthlyBonus;
	}
	public void setMonthlyBonus(String monthlyBonus) {
		this.monthlyBonus = monthlyBonus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	
	public HxFree(){
		this.freeId=UUIDGenerator.getUUID();
		this.founder=CurrentSysUser.getCurrentSysUser().getUserName();
		this.createDate=new Date();
		this.modified=CurrentSysUser.getCurrentSysUser().getUserName();
		this.modifiedDate=new Date();
	}
	
}
