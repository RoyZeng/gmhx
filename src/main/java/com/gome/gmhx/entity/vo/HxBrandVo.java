package com.gome.gmhx.entity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class HxBrandVo {
	private Integer id;
	private String brand;
	private String gm_code;
	private String note; 
	private Date rep_date;
	private String creater;
	private String modifier;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mod_date_st;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mod_date_end;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
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
	public Date getRep_date() {
		return rep_date;
	}
	public void setRep_date(Date rep_date) {
		this.rep_date = rep_date;
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
	public Date getMod_date_st() {
		return mod_date_st;
	}
	public void setMod_date_st(Date mod_date_st) {
		this.mod_date_st = mod_date_st;
	}
	public Date getMod_date_end() {
		return mod_date_end;
	}
	public void setMod_date_end(Date mod_date_end) {
		this.mod_date_end = mod_date_end;
	}
}
