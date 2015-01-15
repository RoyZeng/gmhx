package com.gome.gmhx.entity;

import java.util.Date;

import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.jbpm.CurrentSysUser;

public class HxSetupeFree {
	
	private String freeCode;
	private String model;
	private String freeOrganization;
	private String freeCost;
	private String managerCost;
	private String brand;
	private String founders;
	private Date founderDate;
	private String modified;
	private Date modifiedDate;
	
	public String getFreeCode() {
		return freeCode;
	}
	public void setFreeCode(String freeCode) {
		this.freeCode = freeCode;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFreeOrganization() {
		return freeOrganization;
	}
	public void setFreeOrganization(String freeOrganization) {
		this.freeOrganization = freeOrganization;
	}
	public String getFreeCost() {
		return freeCost;
	}
	public void setFreeCost(String freeCost) {
		this.freeCost = freeCost;
	}
	public String getManagerCost() {
		return managerCost;
	}
	public void setManagerCost(String managerCost) {
		this.managerCost = managerCost;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getFounders() {
		return founders;
	}
	public void setFounders(String founders) {
		this.founders = founders;
	}
	public Date getFounderDate() {
		return founderDate;
	}
	public void setFounderDate(Date founderDate) {
		this.founderDate = founderDate;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public HxSetupeFree(){
		this.freeCode=UUIDGenerator.getUUID();
		this.founders=CurrentSysUser.getCurrentSysUser().getUserName();
		this.founderDate=new Date();
		this.modified=CurrentSysUser.getCurrentSysUser().getUserName();
		this.modifiedDate=new Date();
	}
	
}
