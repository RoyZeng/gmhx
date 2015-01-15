package com.gome.gmhx.entity;

public class CustomerserviceSaleMapping {

	private String number_int; // 对照编号
	
	private String zsales_org_1; // 售后公司代码
	
	private String zsales_org_dscr_1; // 售后公司名称
	
	private String zsales_org_2; // 销售公司代码
	
	private String zsales_org_dscr_2; // 销售公司名称
	
	private String zqypp; // 企业品牌
	
	private String update_flag; // 操作标志;c-创建;m-修改;d-删除

	public String getNumber_int() {
		return number_int;
	}

	public void setNumber_int(String number_int) {
		this.number_int = number_int;
	}

	public String getZsales_org_1() {
		return zsales_org_1;
	}

	public void setZsales_org_1(String zsales_org_1) {
		this.zsales_org_1 = zsales_org_1;
	}

	public String getZsales_org_dscr_1() {
		return zsales_org_dscr_1;
	}

	public void setZsales_org_dscr_1(String zsales_org_dscr_1) {
		this.zsales_org_dscr_1 = zsales_org_dscr_1;
	}

	public String getZsales_org_2() {
		return zsales_org_2;
	}

	public void setZsales_org_2(String zsales_org_2) {
		this.zsales_org_2 = zsales_org_2;
	}

	public String getZsales_org_dscr_2() {
		return zsales_org_dscr_2;
	}

	public void setZsales_org_dscr_2(String zsales_org_dscr_2) {
		this.zsales_org_dscr_2 = zsales_org_dscr_2;
	}

	public String getZqypp() {
		return zqypp;
	}

	public void setZqypp(String zqypp) {
		this.zqypp = zqypp;
	}

	public String getUpdate_flag() {
		return update_flag;
	}

	public void setUpdate_flag(String update_flag) {
		this.update_flag = update_flag;
	}

}
