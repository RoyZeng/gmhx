package com.gome.gmhx.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class HxPostage {
	private int posId;
	private String posNum;
	private String posContent;
	private String posSendUnit;
	private String posPayUnit;
	private String posWeight;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posUpdateDate;
	private String posReceiptUnit;
	private BigDecimal posMoney;
	private String posRecipient;
	private String posAddress;
	private String posWay;
	private String posHandlers;
	private String posUnit;
	private String posNote;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posCreateDate;

	
	
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
	public Date getPosUpdateDate() {
		return posUpdateDate;
	}
	public void setPosUpdateDate(Date posUpdateDate) {
		this.posUpdateDate = posUpdateDate;
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
	public Date getPosDate() {
		return posDate;
	}
	public void setPosDate(Date posDate) {
		this.posDate = posDate;
	}
	public Date getPosCreateDate() {
		return posCreateDate;
	}
	public void setPosCreateDate(Date posCreateDate) {
		this.posCreateDate = posCreateDate;
	}

	public HxPostage(){
		this.posCreateDate=new Date();
		this.posUpdateDate=new Date();
	}
	
}