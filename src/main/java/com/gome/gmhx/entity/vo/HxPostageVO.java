package com.gome.gmhx.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class HxPostageVO {
	private int posId;
	private String posNum;
	private String posContent;
	private String posSendUnit;
	private String posPayUnit;
	private String posWeight;
	private String posReceiptUnit;
	private BigDecimal posMoney;
	private String posRecipient;
	private String posAddress;
	private String posWay;
	private String posHandlers;
	private String posUnit;
	private String posNote;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posUpdateDate_sta;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posUpdateDate_end;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posDate_sta;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posDate_end;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posCreateDate_sta;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posCreateDate_end;

	
	
	
	public int getPosId() {
		return posId;
	}
	public void setPosId(int posId) {
		this.posId = posId;
	}
	public String getPosNum() {
		return posNum;
	}
	public void setPosNum(String posNum) {
		this.posNum = posNum;
	}
	public String getPosContent() {
		return posContent;
	}
	public void setPosContent(String posContent) {
		this.posContent = posContent;
	}
	public String getPosSendUnit() {
		return posSendUnit;
	}
	public void setPosSendUnit(String posSendUnit) {
		this.posSendUnit = posSendUnit;
	}
	public String getPosPayUnit() {
		return posPayUnit;
	}
	public void setPosPayUnit(String posPayUnit) {
		this.posPayUnit = posPayUnit;
	}
	public String getPosWeight() {
		return posWeight;
	}
	public void setPosWeight(String posWeight) {
		this.posWeight = posWeight;
	}
	public String getPosReceiptUnit() {
		return posReceiptUnit;
	}
	public void setPosReceiptUnit(String posReceiptUnit) {
		this.posReceiptUnit = posReceiptUnit;
	}
	public BigDecimal getPosMoney() {
		return posMoney;
	}
	public void setPosMoney(BigDecimal posMoney) {
		this.posMoney = posMoney;
	}
	public String getPosRecipient() {
		return posRecipient;
	}
	public void setPosRecipient(String posRecipient) {
		this.posRecipient = posRecipient;
	}
	public String getPosAddress() {
		return posAddress;
	}
	public void setPosAddress(String posAddress) {
		this.posAddress = posAddress;
	}
	public String getPosWay() {
		return posWay;
	}
	public void setPosWay(String posWay) {
		this.posWay = posWay;
	}
	public String getPosHandlers() {
		return posHandlers;
	}
	public void setPosHandlers(String posHandlers) {
		this.posHandlers = posHandlers;
	}
	public String getPosUnit() {
		return posUnit;
	}
	public void setPosUnit(String posUnit) {
		this.posUnit = posUnit;
	}
	public String getPosNote() {
		return posNote;
	}
	public void setPosNote(String posNote) {
		this.posNote = posNote;
	}
	public Date getPosUpdateDate_sta() {
		return posUpdateDate_sta;
	}
	public void setPosUpdateDate_sta(Date posUpdateDate_sta) {
		this.posUpdateDate_sta = posUpdateDate_sta;
	}
	public Date getPosUpdateDate_end() {
		return posUpdateDate_end;
	}
	public void setPosUpdateDate_end(Date posUpdateDate_end) {
		this.posUpdateDate_end = posUpdateDate_end;
	}
	public Date getPosDate_sta() {
		return posDate_sta;
	}
	public void setPosDate_sta(Date posDate_sta) {
		this.posDate_sta = posDate_sta;
	}
	public Date getPosDate_end() {
		return posDate_end;
	}
	public void setPosDate_end(Date posDate_end) {
		this.posDate_end = posDate_end;
	}
	public Date getPosCreateDate_sta() {
		return posCreateDate_sta;
	}
	public void setPosCreateDate_sta(Date posCreateDate_sta) {
		this.posCreateDate_sta = posCreateDate_sta;
	}
	public Date getPosCreateDate_end() {
		return posCreateDate_end;
	}
	public void setPosCreateDate_end(Date posCreateDate_end) {
		this.posCreateDate_end = posCreateDate_end;
	}
	
	
}
