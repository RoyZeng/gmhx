package com.gome.gmhx.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class HxUser {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_name
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_org_id
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userOrgId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_login_name
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userLoginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_login_password
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userLoginPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_login_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date userLoginDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_phone
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_mobile
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userMobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_email
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userEmail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_work_no
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userWorkNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_sex
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userSex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_birthday
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date userBirthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_worked
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userWorked;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_education
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userEducation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_origin
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userOrigin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_title
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_work_limit
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userWorkLimit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_indent_no
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userIndentNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_in_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date userInDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_area
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userArea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_address
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_post_code
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userPostCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_active
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userActive;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_create_id
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userCreateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_create_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date userCreateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_update_id
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userUpdateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_update_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date userUpdateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_password_change_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date userPasswordChangeDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_unlock_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date userUnlockDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_user.user_note
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    private String userNote;
    
    private String orgCode;
    
    private String orgParentId;
    
    private String orgType;
    
    private String userOrgName;
    
    private String originTable;
    
    private String fromType;
    
    private String origin;//请求来源  ：  权限组：gome 恒信:  hx
    
    private String oldUserLoginName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_name
     *
     * @return the value of hx_user.user_name
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_name
     *
     * @param userName the value for hx_user.user_name
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_org_id
     *
     * @return the value of hx_user.user_org_id
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserOrgId() {
        return userOrgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_org_id
     *
     * @param userOrgId the value for hx_user.user_org_id
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserOrgId(String userOrgId) {
        this.userOrgId = userOrgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_login_name
     *
     * @return the value of hx_user.user_login_name
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserLoginName() {
        return userLoginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_login_name
     *
     * @param userLoginName the value for hx_user.user_login_name
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_login_password
     *
     * @return the value of hx_user.user_login_password
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserLoginPassword() {
        return userLoginPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_login_password
     *
     * @param userLoginPassword the value for hx_user.user_login_password
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserLoginPassword(String userLoginPassword) {
        this.userLoginPassword = userLoginPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_login_date
     *
     * @return the value of hx_user.user_login_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public Date getUserLoginDate() {
        return userLoginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_login_date
     *
     * @param userLoginDate the value for hx_user.user_login_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserLoginDate(Date userLoginDate) {
        this.userLoginDate = userLoginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_phone
     *
     * @return the value of hx_user.user_phone
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_phone
     *
     * @param userPhone the value for hx_user.user_phone
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_mobile
     *
     * @return the value of hx_user.user_mobile
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_mobile
     *
     * @param userMobile the value for hx_user.user_mobile
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_email
     *
     * @return the value of hx_user.user_email
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_email
     *
     * @param userEmail the value for hx_user.user_email
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_work_no
     *
     * @return the value of hx_user.user_work_no
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserWorkNo() {
        return userWorkNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_work_no
     *
     * @param userWorkNo the value for hx_user.user_work_no
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserWorkNo(String userWorkNo) {
        this.userWorkNo = userWorkNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_sex
     *
     * @return the value of hx_user.user_sex
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_sex
     *
     * @param userSex the value for hx_user.user_sex
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_birthday
     *
     * @return the value of hx_user.user_birthday
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public Date getUserBirthday() {
        return userBirthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_birthday
     *
     * @param userBirthday the value for hx_user.user_birthday
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_worked
     *
     * @return the value of hx_user.user_worked
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserWorked() {
        return userWorked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_worked
     *
     * @param userWorked the value for hx_user.user_worked
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserWorked(String userWorked) {
        this.userWorked = userWorked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_education
     *
     * @return the value of hx_user.user_education
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserEducation() {
        return userEducation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_education
     *
     * @param userEducation the value for hx_user.user_education
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserEducation(String userEducation) {
        this.userEducation = userEducation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_origin
     *
     * @return the value of hx_user.user_origin
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserOrigin() {
        return userOrigin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_origin
     *
     * @param userOrigin the value for hx_user.user_origin
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserOrigin(String userOrigin) {
        this.userOrigin = userOrigin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_title
     *
     * @return the value of hx_user.user_title
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserTitle() {
        return userTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_title
     *
     * @param userTitle the value for hx_user.user_title
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_work_limit
     *
     * @return the value of hx_user.user_work_limit
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserWorkLimit() {
        return userWorkLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_work_limit
     *
     * @param userWorkLimit the value for hx_user.user_work_limit
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserWorkLimit(String userWorkLimit) {
        this.userWorkLimit = userWorkLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_indent_no
     *
     * @return the value of hx_user.user_indent_no
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserIndentNo() {
        return userIndentNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_indent_no
     *
     * @param userIndentNo the value for hx_user.user_indent_no
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserIndentNo(String userIndentNo) {
        this.userIndentNo = userIndentNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_in_date
     *
     * @return the value of hx_user.user_in_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public Date getUserInDate() {
        return userInDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_in_date
     *
     * @param userInDate the value for hx_user.user_in_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserInDate(Date userInDate) {
        this.userInDate = userInDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_area
     *
     * @return the value of hx_user.user_area
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserArea() {
        return userArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_area
     *
     * @param userArea the value for hx_user.user_area
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_address
     *
     * @return the value of hx_user.user_address
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_address
     *
     * @param userAddress the value for hx_user.user_address
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_post_code
     *
     * @return the value of hx_user.user_post_code
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserPostCode() {
        return userPostCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_post_code
     *
     * @param userPostCode the value for hx_user.user_post_code
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserPostCode(String userPostCode) {
        this.userPostCode = userPostCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_active
     *
     * @return the value of hx_user.user_active
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserActive() {
        return userActive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_active
     *
     * @param userActive the value for hx_user.user_active
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserActive(String userActive) {
        this.userActive = userActive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_create_id
     *
     * @return the value of hx_user.user_create_id
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserCreateId() {
        return userCreateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_create_id
     *
     * @param userCreateId the value for hx_user.user_create_id
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserCreateId(String userCreateId) {
        this.userCreateId = userCreateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_create_date
     *
     * @return the value of hx_user.user_create_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public Date getUserCreateDate() {
        return userCreateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_create_date
     *
     * @param userCreateDate the value for hx_user.user_create_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserCreateDate(Date userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_update_id
     *
     * @return the value of hx_user.user_update_id
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserUpdateId() {
        return userUpdateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_update_id
     *
     * @param userUpdateId the value for hx_user.user_update_id
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserUpdateId(String userUpdateId) {
        this.userUpdateId = userUpdateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_update_date
     *
     * @return the value of hx_user.user_update_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public Date getUserUpdateDate() {
        return userUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_update_date
     *
     * @param userUpdateDate the value for hx_user.user_update_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserUpdateDate(Date userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_password_change_date
     *
     * @return the value of hx_user.user_password_change_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public Date getUserPasswordChangeDate() {
        return userPasswordChangeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_password_change_date
     *
     * @param userPasswordChangeDate the value for hx_user.user_password_change_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserPasswordChangeDate(Date userPasswordChangeDate) {
        this.userPasswordChangeDate = userPasswordChangeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_unlock_date
     *
     * @return the value of hx_user.user_unlock_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public Date getUserUnlockDate() {
        return userUnlockDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_unlock_date
     *
     * @param userUnlockDate the value for hx_user.user_unlock_date
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserUnlockDate(Date userUnlockDate) {
        this.userUnlockDate = userUnlockDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_user.user_note
     *
     * @return the value of hx_user.user_note
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public String getUserNote() {
        return userNote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_user.user_note
     *
     * @param userNote the value for hx_user.user_note
     *
     * @mbggenerated Mon Jul 14 17:17:26 CST 2014
     */
    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgParentId() {
		return orgParentId;
	}

	public void setOrgParentId(String orgParentId) {
		this.orgParentId = orgParentId;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getUserOrgName() {
		return userOrgName;
	}

	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}

	public String getOriginTable() {
		return originTable;
	}

	public void setOriginTable(String originTable) {
		this.originTable = originTable;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOldUserLoginName() {
		return oldUserLoginName;
	}

	public void setOldUserLoginName(String oldUserLoginName) {
		this.oldUserLoginName = oldUserLoginName;
	}
	
}