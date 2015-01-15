package com.gome.gmhx.entity;

import java.util.Date;

import com.gome.gmhx.jbpm.CurrentSysUser;

public class Brand {
	private String id;
	private String brand;
	private String gm_code;
	private String note; 
	private Date rep_date;
	private String creater;
	private String modifier;
	private Date mod_date;
	private String isUse;
	
	    
	public String getBrand() {
		return brand;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getGm_code() {
		return gm_code;
	}
	public void setGm_code(String gm_code) {
		this.gm_code = gm_code;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	 public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRep_date() {
		return rep_date;
	}
	public void setRep_date(Date rep_date) {
		this.rep_date =rep_date; 
		
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date =mod_date; 
	}
	@Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append(getClass().getSimpleName());
	        sb.append(" [");
	        sb.append("Hash = ").append(hashCode());
	        sb.append(", brand=").append(brand);
	        sb.append(", gm_code=").append(gm_code);
	        sb.append(", note=").append(note);
	        sb.append(", rep_date=").append(rep_date);
	        sb.append(", creater=").append(creater);
	        sb.append(", modifier=").append(modifier);
	        sb.append(", mod_date=").append(mod_date);
	        sb.append("]");
	        return sb.toString();
	    }
   
	public Brand(){
		//this.creater=CurrentSysUser.getCurrentSysUser().getUserName();
		this.rep_date=new Date();
	//	this.modifier=CurrentSysUser.getCurrentSysUser().getUserName();
		this.mod_date=new Date();
	}

}
