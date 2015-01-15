package com.gome.gmhx.entity;

public class HXPositionMapping {
    private String id;

    private String jbpmRoleCode;

    private String jbpmRoleName;
    
    private String positionCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getJbpmRoleCode() {
        return jbpmRoleCode;
    }

    public void setJbpmRoleCode(String jbpmRoleCode) {
        this.jbpmRoleCode = jbpmRoleCode == null ? null : jbpmRoleCode.trim();
    }

    public String getJbpmRoleName() {
        return jbpmRoleName;
    }

    public void setJbpmRoleName(String jbpmRoleName) {
        this.jbpmRoleName = jbpmRoleName == null ? null : jbpmRoleName.trim();
    }

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
}