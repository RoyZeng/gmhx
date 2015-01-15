package com.gome.gmhx.entity;

import java.util.Date;

public class HxLimit {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_id
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private String limitId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_org_id
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private String limitOrgId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_cash
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private Long limitCash;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_credit
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private Long limitCredit;
    
    private Long limitCashChange;
    
    private Long limitCreditChange;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_matter
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private Long limitMatter;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_add_tag
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private String limitAddTag;// 0： 减，  1：增

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_desc
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private String limitDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_origin_number
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private String limitOriginNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_operate_type
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private String limitOperateType;// 0： 手动，  1：自动

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_operate_reason
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private String limitOperateReason;// 0:初始化  ,1:手工修改

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_operate_id
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private String limitOperateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_limit.limit_operate_date
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    private Date limitOperateDate;

    private String limitOrgParentId;
    
    private Long limitAvailability;
    
    private String limitOriginId;//关联初始化的记录主键
    
    private String limitOrgName;
    
    private String limitOrgParentName;
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_id
     *
     * @return the value of hx_limit.limit_id
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public String getLimitId() {
        return limitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_id
     *
     * @param limitId the value for hx_limit.limit_id
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitId(String limitId) {
        this.limitId = limitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_org_id
     *
     * @return the value of hx_limit.limit_org_id
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public String getLimitOrgId() {
        return limitOrgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_org_id
     *
     * @param limitOrgId the value for hx_limit.limit_org_id
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitOrgId(String limitOrgId) {
        this.limitOrgId = limitOrgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_cash
     *
     * @return the value of hx_limit.limit_cash
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public Long getLimitCash() {
        return limitCash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_cash
     *
     * @param limitCash the value for hx_limit.limit_cash
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitCash(Long limitCash) {
        this.limitCash = limitCash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_credit
     *
     * @return the value of hx_limit.limit_credit
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public Long getLimitCredit() {
        return limitCredit;
    }

    public Long getLimitCashTotal(){
    	return (this.limitCash==null?0L:this.limitCash) + (this.limitCashChange==null?0L:this.limitCashChange);
    }
    
    public Long getLimitCreditTotal(){
    	return (this.limitCredit==null?0L:this.limitCredit) + (this.limitCreditChange==null?0L:this.limitCreditChange);
    }
    
    /**
     * 返回金额总和
     * @return
     */
    public Long getLimitTotal(){
    	return getLimitCashTotal() + getLimitCreditTotal();
    }
    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_credit
     *
     * @param limitCredit the value for hx_limit.limit_credit
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitCredit(Long limitCredit) {
        this.limitCredit = limitCredit;
    }

    public Long getLimitCashChange() {
		return limitCashChange;
	}

	public void setLimitCashChange(Long limitCashChange) {
		this.limitCashChange = limitCashChange;
	}

	public Long getLimitCreditChange() {
		return limitCreditChange;
	}

	public void setLimitCreditChange(Long limitCreditChange) {
		this.limitCreditChange = limitCreditChange;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_matter
     *
     * @return the value of hx_limit.limit_matter
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public Long getLimitMatter() {
        return limitMatter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_matter
     *
     * @param limitMatter the value for hx_limit.limit_matter
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitMatter(Long limitMatter) {
        this.limitMatter = limitMatter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_add_tag
     *
     * @return the value of hx_limit.limit_add_tag
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public String getLimitAddTag() {
        return limitAddTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_add_tag
     *
     * @param limitAddTag the value for hx_limit.limit_add_tag
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitAddTag(String limitAddTag) {
        this.limitAddTag = limitAddTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_desc
     *
     * @return the value of hx_limit.limit_desc
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public String getLimitDesc() {
        return limitDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_desc
     *
     * @param limitDesc the value for hx_limit.limit_desc
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitDesc(String limitDesc) {
        this.limitDesc = limitDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_origin_number
     *
     * @return the value of hx_limit.limit_origin_number
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public String getLimitOriginNumber() {
        return limitOriginNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_origin_number
     *
     * @param limitOriginNumber the value for hx_limit.limit_origin_number
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitOriginNumber(String limitOriginNumber) {
        this.limitOriginNumber = limitOriginNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_operate_type
     *
     * @return the value of hx_limit.limit_operate_type
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public String getLimitOperateType() {
        return limitOperateType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_operate_type
     *
     * @param limitOperateType the value for hx_limit.limit_operate_type
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitOperateType(String limitOperateType) {
        this.limitOperateType = limitOperateType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_operate_reason
     *
     * @return the value of hx_limit.limit_operate_reason
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public String getLimitOperateReason() {
        return limitOperateReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_operate_reason
     *
     * @param limitOperateReason the value for hx_limit.limit_operate_reason
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitOperateReason(String limitOperateReason) {
        this.limitOperateReason = limitOperateReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_operate_id
     *
     * @return the value of hx_limit.limit_operate_id
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public String getLimitOperateId() {
        return limitOperateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_operate_id
     *
     * @param limitOperateId the value for hx_limit.limit_operate_id
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitOperateId(String limitOperateId) {
        this.limitOperateId = limitOperateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_limit.limit_operate_date
     *
     * @return the value of hx_limit.limit_operate_date
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public Date getLimitOperateDate() {
        return limitOperateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_limit.limit_operate_date
     *
     * @param limitOperateDate the value for hx_limit.limit_operate_date
     *
     * @mbggenerated Tue Aug 19 10:18:43 CST 2014
     */
    public void setLimitOperateDate(Date limitOperateDate) {
        this.limitOperateDate = limitOperateDate;
    }

	public String getLimitOrgParentId() {
		return limitOrgParentId;
	}

	public void setLimitOrgParentId(String limitOrgParentId) {
		this.limitOrgParentId = limitOrgParentId;
	}

	public Long getLimitAvailability() {
		return limitAvailability;
	}

	public void setLimitAvailability(Long limitAvailability) {
		this.limitAvailability = limitAvailability;
	}

	public String getLimitOriginId() {
		return limitOriginId;
	}

	public void setLimitOriginId(String limitOriginId) {
		this.limitOriginId = limitOriginId;
	}

	public String getLimitOrgName() {
		return limitOrgName;
	}

	public void setLimitOrgName(String limitOrgName) {
		this.limitOrgName = limitOrgName;
	}

	public String getLimitOrgParentName() {
		return limitOrgParentName;
	}

	public void setLimitOrgParentName(String limitOrgParentName) {
		this.limitOrgParentName = limitOrgParentName;
	}

}