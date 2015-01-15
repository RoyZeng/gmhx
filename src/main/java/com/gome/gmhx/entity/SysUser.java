package com.gome.gmhx.entity;


public class SysUser {
	private String userAccount;
	private String userName;
	private String userPassword;
	private int fromType;//用户来源 1国美 2第三方 3系统自建
	private String companyId;
	private String companyName;
	private String sysPositionId;//系统岗位Id
	private String sysPositionName;//系统岗位名称
	private int sysPositionType;//当前系统岗位级别1总部 2分部 3网点
	private String userState;//用户状态
	private String orgId;//岗位组织机构ID
	
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getFromType() {
		return fromType;
	}
	public void setFromType(int fromType) {
		this.fromType = fromType;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSysPositionId() {
		return sysPositionId;
	}
	public void setSysPositionId(String sysPositionId) {
		this.sysPositionId = sysPositionId;
	}
	public String getSysPositionName() {
		return sysPositionName;
	}
	public void setSysPositionName(String sysPositionName) {
		this.sysPositionName = sysPositionName;
	}
	
	public int getSysPositionType() {
		return sysPositionType;
	}
	public void setSysPositionType(int sysPositionType) {
		this.sysPositionType = sysPositionType;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getWorkFlowUser(){
		return this.sysPositionId+"_"+this.userAccount;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
