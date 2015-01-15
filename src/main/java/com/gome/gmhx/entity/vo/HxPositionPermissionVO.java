package com.gome.gmhx.entity.vo;

/*
 * 岗位权限VO
 */
public class HxPositionPermissionVO {
	private String positionCode; // 岗位代码
	private String menuId;// 菜单编码
	private String fittingAuthId;// 物料菜单代码
	private String positionCategoryId;// 品类代码
	private String jbpmRoleCode;// 岗位审批角色
	private String userLoginName;// 用户
	private String fromType;// 用户
	public String getPositionCode() {
		return positionCode;
		
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getFittingAuthId() {
		return fittingAuthId;
	}

	public void setFittingAuthId(String fittingAuthId) {
		this.fittingAuthId = fittingAuthId;
	}

	public String getPositionCategoryId() {
		return positionCategoryId;
	}

	public void setPositionCategoryId(String positionCategoryId) {
		this.positionCategoryId = positionCategoryId;
	}

	public String getJbpmRoleCode() {
		return jbpmRoleCode;
	}

	public void setJbpmRoleCode(String jbpmRoleCode) {
		this.jbpmRoleCode = jbpmRoleCode;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}
	
}
