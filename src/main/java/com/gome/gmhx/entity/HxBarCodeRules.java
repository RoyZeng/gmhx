package com.gome.gmhx.entity;

import java.util.Date;

import com.gome.common.util.UUIDGenerator;

public class HxBarCodeRules {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.rules_id
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private String rulesId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.gome_code
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private String gomeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.category
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private String category;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.bar_code_number
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private Integer barCodeNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.inside_machine
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private Integer insideMachine;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.inside_machine_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private Integer insideMachineOne;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.inside_machine_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private Integer insideMachineTwo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.inside_machine_content
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private String insideMachineContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.inside_machine_content_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private String insideMachineContentOne;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.inside_machine_content_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private String insideMachineContentTwo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.outside_machine
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private Integer outsideMachine;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.outside_machine_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private Integer outsideMachineOne;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.outside_machine_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private Integer outsideMachineTwo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.outside_machine_content
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private String outsideMachineContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.outside_machine_content_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private String outsideMachineContentOne;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.outside_machine_content_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private String outsideMachineContentTwo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.note
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private String note;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.create_date
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_bar_code_rules.modifie_date
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    private Date modifieDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.rules_id
     *
     * @return the value of hx_bar_code_rules.rules_id
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.gome_code
     *
     * @return the value of hx_bar_code_rules.gome_code
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     * 
     */
    
    public HxBarCodeRules(){
    	this.rulesId = UUIDGenerator.getUUID();
    	this.createDate = new Date();
    	this.modifieDate = new Date();
    }
    
    
    public String getGomeCode() {
        return gomeCode;
    }

    public String getRulesId() {
		return rulesId;
	}

	public void setRulesId(String rulesId) {
		this.rulesId = rulesId;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.gome_code
     *
     * @param gomeCode the value for hx_bar_code_rules.gome_code
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setGomeCode(String gomeCode) {
        this.gomeCode = gomeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.category
     *
     * @return the value of hx_bar_code_rules.category
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public String getCategory() {
        return category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.category
     *
     * @param category the value for hx_bar_code_rules.category
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.bar_code_number
     *
     * @return the value of hx_bar_code_rules.bar_code_number
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public Integer getBarCodeNumber() {
        return barCodeNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.bar_code_number
     *
     * @param barCodeNumber the value for hx_bar_code_rules.bar_code_number
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setBarCodeNumber(Integer barCodeNumber) {
        this.barCodeNumber = barCodeNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.inside_machine
     *
     * @return the value of hx_bar_code_rules.inside_machine
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public Integer getInsideMachine() {
        return insideMachine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.inside_machine
     *
     * @param insideMachine the value for hx_bar_code_rules.inside_machine
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setInsideMachine(Integer insideMachine) {
        this.insideMachine = insideMachine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.inside_machine_one
     *
     * @return the value of hx_bar_code_rules.inside_machine_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public Integer getInsideMachineOne() {
        return insideMachineOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.inside_machine_one
     *
     * @param insideMachineOne the value for hx_bar_code_rules.inside_machine_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setInsideMachineOne(Integer insideMachineOne) {
        this.insideMachineOne = insideMachineOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.inside_machine_two
     *
     * @return the value of hx_bar_code_rules.inside_machine_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public Integer getInsideMachineTwo() {
        return insideMachineTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.inside_machine_two
     *
     * @param insideMachineTwo the value for hx_bar_code_rules.inside_machine_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setInsideMachineTwo(Integer insideMachineTwo) {
        this.insideMachineTwo = insideMachineTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.inside_machine_content
     *
     * @return the value of hx_bar_code_rules.inside_machine_content
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public String getInsideMachineContent() {
        return insideMachineContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.inside_machine_content
     *
     * @param insideMachineContent the value for hx_bar_code_rules.inside_machine_content
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setInsideMachineContent(String insideMachineContent) {
        this.insideMachineContent = insideMachineContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.inside_machine_content_one
     *
     * @return the value of hx_bar_code_rules.inside_machine_content_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public String getInsideMachineContentOne() {
        return insideMachineContentOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.inside_machine_content_one
     *
     * @param insideMachineContentOne the value for hx_bar_code_rules.inside_machine_content_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setInsideMachineContentOne(String insideMachineContentOne) {
        this.insideMachineContentOne = insideMachineContentOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.inside_machine_content_two
     *
     * @return the value of hx_bar_code_rules.inside_machine_content_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public String getInsideMachineContentTwo() {
        return insideMachineContentTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.inside_machine_content_two
     *
     * @param insideMachineContentTwo the value for hx_bar_code_rules.inside_machine_content_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setInsideMachineContentTwo(String insideMachineContentTwo) {
        this.insideMachineContentTwo = insideMachineContentTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.outside_machine
     *
     * @return the value of hx_bar_code_rules.outside_machine
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public Integer getOutsideMachine() {
        return outsideMachine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.outside_machine
     *
     * @param outsideMachine the value for hx_bar_code_rules.outside_machine
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setOutsideMachine(Integer outsideMachine) {
        this.outsideMachine = outsideMachine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.outside_machine_one
     *
     * @return the value of hx_bar_code_rules.outside_machine_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public Integer getOutsideMachineOne() {
        return outsideMachineOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.outside_machine_one
     *
     * @param outsideMachineOne the value for hx_bar_code_rules.outside_machine_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setOutsideMachineOne(Integer outsideMachineOne) {
        this.outsideMachineOne = outsideMachineOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.outside_machine_two
     *
     * @return the value of hx_bar_code_rules.outside_machine_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public Integer getOutsideMachineTwo() {
        return outsideMachineTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.outside_machine_two
     *
     * @param outsideMachineTwo the value for hx_bar_code_rules.outside_machine_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setOutsideMachineTwo(Integer outsideMachineTwo) {
        this.outsideMachineTwo = outsideMachineTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.outside_machine_content
     *
     * @return the value of hx_bar_code_rules.outside_machine_content
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public String getOutsideMachineContent() {
        return outsideMachineContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.outside_machine_content
     *
     * @param outsideMachineContent the value for hx_bar_code_rules.outside_machine_content
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setOutsideMachineContent(String outsideMachineContent) {
        this.outsideMachineContent = outsideMachineContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.outside_machine_content_one
     *
     * @return the value of hx_bar_code_rules.outside_machine_content_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public String getOutsideMachineContentOne() {
        return outsideMachineContentOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.outside_machine_content_one
     *
     * @param outsideMachineContentOne the value for hx_bar_code_rules.outside_machine_content_one
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setOutsideMachineContentOne(String outsideMachineContentOne) {
        this.outsideMachineContentOne = outsideMachineContentOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.outside_machine_content_two
     *
     * @return the value of hx_bar_code_rules.outside_machine_content_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public String getOutsideMachineContentTwo() {
        return outsideMachineContentTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.outside_machine_content_two
     *
     * @param outsideMachineContentTwo the value for hx_bar_code_rules.outside_machine_content_two
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setOutsideMachineContentTwo(String outsideMachineContentTwo) {
        this.outsideMachineContentTwo = outsideMachineContentTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.note
     *
     * @return the value of hx_bar_code_rules.note
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.note
     *
     * @param note the value for hx_bar_code_rules.note
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.create_date
     *
     * @return the value of hx_bar_code_rules.create_date
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.create_date
     *
     * @param createDate the value for hx_bar_code_rules.create_date
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_bar_code_rules.modifie_date
     *
     * @return the value of hx_bar_code_rules.modifie_date
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public Date getModifieDate() {
        return modifieDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_bar_code_rules.modifie_date
     *
     * @param modifieDate the value for hx_bar_code_rules.modifie_date
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    public void setModifieDate(Date modifieDate) {
        this.modifieDate = modifieDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hx_bar_code_rules
     *
     * @mbggenerated Wed Aug 20 14:20:45 CST 2014
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rulesId=").append(rulesId);
        sb.append(", gomeCode=").append(gomeCode);
        sb.append(", category=").append(category);
        sb.append(", barCodeNumber=").append(barCodeNumber);
        sb.append(", insideMachine=").append(insideMachine);
        sb.append(", insideMachineOne=").append(insideMachineOne);
        sb.append(", insideMachineTwo=").append(insideMachineTwo);
        sb.append(", insideMachineContent=").append(insideMachineContent);
        sb.append(", insideMachineContentOne=").append(insideMachineContentOne);
        sb.append(", insideMachineContentTwo=").append(insideMachineContentTwo);
        sb.append(", outsideMachine=").append(outsideMachine);
        sb.append(", outsideMachineOne=").append(outsideMachineOne);
        sb.append(", outsideMachineTwo=").append(outsideMachineTwo);
        sb.append(", outsideMachineContent=").append(outsideMachineContent);
        sb.append(", outsideMachineContentOne=").append(outsideMachineContentOne);
        sb.append(", outsideMachineContentTwo=").append(outsideMachineContentTwo);
        sb.append(", note=").append(note);
        sb.append(", createDate=").append(createDate);
        sb.append(", modifieDate=").append(modifieDate);
        sb.append("]");
        return sb.toString();
    }
}