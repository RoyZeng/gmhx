package com.gome.gmhx.entity.vo;

public class HxMaterialDetailVO {

	private String type;
	  
    private String listNumber;
    
	private String suitModel;
	
	private String fittingCodeDetail;
	
	private Integer applyCount;
	
	private Integer auditCount;
	
	private Float price;
	
	private String comment;
	
	private String fittingNameDetail;

	private String spec;
	
	private String sendNumber;
	
	private Integer count; // 出入库数量
	
	private Float inBatchPrice; // 入库价格
	
	private Float outBatchPrice; // 出库价格
	
	public String getListNumber() {
		return listNumber;
	}

	public void setListNumber(String listNumber) {
		this.listNumber = listNumber;
	}

	public String getSuitModel() {
		return suitModel;
	}

	public void setSuitModel(String suitModel) {
		this.suitModel = suitModel;
	}

	public String getFittingCodeDetail() {
		return fittingCodeDetail;
	}

	public void setFittingCodeDetail(String fittingCodeDetail) {
		this.fittingCodeDetail = fittingCodeDetail;
	}

	public Integer getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Integer applyCount) {
		this.applyCount = applyCount;
	}

	public Integer getAuditCount() {
		return auditCount;
	}

	public void setAuditCount(Integer auditCount) {
		this.auditCount = auditCount;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFittingNameDetail() {
		return fittingNameDetail;
	}

	public void setFittingNameDetail(String fittingNameDetail) {
		this.fittingNameDetail = fittingNameDetail;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getSendNumber() {
		return sendNumber;
	}

	public void setSendNumber(String sendNumber) {
		this.sendNumber = sendNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Float getInBatchPrice() {
		return inBatchPrice;
	}

	public void setInBatchPrice(Float inBatchPrice) {
		this.inBatchPrice = inBatchPrice;
	}

	public Float getOutBatchPrice() {
		return outBatchPrice;
	}

	public void setOutBatchPrice(Float outBatchPrice) {
		this.outBatchPrice = outBatchPrice;
	}
}
