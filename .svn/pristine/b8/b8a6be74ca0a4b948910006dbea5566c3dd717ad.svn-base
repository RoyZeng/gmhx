package com.gome.gmhx.entity;

import java.util.Date;

import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.jbpm.CurrentSysUser;
/**
 * 移换机实体类 
 */
public class HxFeeMoveMachine {
	private String feeID;
	private String classifyCode;
	private Float wholefee;
	private Float innerfee;
	private Float outerfee;
	private String createUsername;
	private Date createDate;
	private String modifyUsername;
	private Date modifyDate;
	private String operationType;
	
	public String getFeeID() {
		return feeID;
	}
	public void setFeeID(String feeID) {
		this.feeID = feeID;
	}
	
	public String getClassifyCode() {
		return classifyCode;
	}
	public void setClassifyCode(String classifyCode) {
		this.classifyCode = classifyCode;
	}
	
	public Float getWholefee() {
		return wholefee;
	}
	public void setWholefee(Float wholefee) {
		this.wholefee = wholefee;
	}
	public Float getInnerfee() {
		return innerfee;
	}
	public void setInnerfee(Float innerfee) {
		this.innerfee = innerfee;
	}
	public Float getOuterfee() {
		return outerfee;
	}
	public void setOuterfee(Float outerfee) {
		this.outerfee = outerfee;
	}
	public String getCreateUsername() {
		return createUsername;
	}
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUsername() {
		return modifyUsername;
	}
	public void setModifyUsername(String modifyUsername) {
		this.modifyUsername = modifyUsername;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
	public HxFeeMoveMachine(){
		this.feeID=UUIDGenerator.getUUID();
		this.createUsername=CurrentSysUser.getCurrentSysUser().getUserName();
		this.createDate=new Date();
		this.modifyUsername=CurrentSysUser.getCurrentSysUser().getUserName();
		this.modifyDate=new Date();
	}
}