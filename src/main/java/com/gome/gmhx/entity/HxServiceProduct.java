package com.gome.gmhx.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class HxServiceProduct {
	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.product_id
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.customer_id
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.install_card_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private BigDecimal installCardNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.delivery_order_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private BigDecimal deliveryOrderNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.install_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private BigDecimal installNum;

    private String productCode;
    
    private String productName;
    
    private String machineType;
    
    private String category;
    
    private String mode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.gome_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String gomeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.brand
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String brand;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.machine_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String machineCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.internal_machine_code1
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String internalMachineCode1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.internal_machine_code2
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String internalMachineCode2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.external_machine_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String externalMachineCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.ticket_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private BigDecimal ticketNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.invoice_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String invoiceNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.buyer
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String buyer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.buy_date
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date buyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.install_date
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date installDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.install_unit
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String installUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.sale_center
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String saleCenter;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.sale_market
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String saleMarket;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.sale_price
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private BigDecimal salePrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.is_sale
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String isSale;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.warranty
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date warranty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.install_province
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String installProvince;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.install_detail_address
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String installDetailAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.bargain_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String projectName;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.bargain_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    
    private String bargainCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.product_note
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String productNote;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.create_man_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String createManP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.create_organization_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String createOrganizationP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.create_time_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTimeP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.alter_man_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String alterManP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.alter_organization_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    private String alterOrganizationP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hx_service_product.alter_time_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date alterTimeP;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.product_id
     *
     * @return the value of hx_service_product.product_id
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.product_id
     *
     * @param productId the value for hx_service_product.product_id
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.customer_id
     *
     * @return the value of hx_service_product.customer_id
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.customer_id
     *
     * @param customerId the value for hx_service_product.customer_id
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.install_card_num
     *
     * @return the value of hx_service_product.install_card_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public BigDecimal getInstallCardNum() {
        return installCardNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.install_card_num
     *
     * @param installCardNum the value for hx_service_product.install_card_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setInstallCardNum(BigDecimal installCardNum) {
        this.installCardNum = installCardNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.delivery_order_num
     *
     * @return the value of hx_service_product.delivery_order_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public BigDecimal getDeliveryOrderNum() {
        return deliveryOrderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.delivery_order_num
     *
     * @param deliveryOrderNum the value for hx_service_product.delivery_order_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setDeliveryOrderNum(BigDecimal deliveryOrderNum) {
        this.deliveryOrderNum = deliveryOrderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.install_num
     *
     * @return the value of hx_service_product.install_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public BigDecimal getInstallNum() {
        return installNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.install_num
     *
     * @param installNum the value for hx_service_product.install_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setInstallNum(BigDecimal installNum) {
        this.installNum = installNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.machine_type
     *
     * @return the value of hx_service_product.machine_type
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getMachineType() {
        return machineType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.machine_type
     *
     * @param machineType the value for hx_service_product.machine_type
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.gome_code
     *
     * @return the value of hx_service_product.gome_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getGomeCode() {
        return gomeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.gome_code
     *
     * @param gomeCode the value for hx_service_product.gome_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setGomeCode(String gomeCode) {
        this.gomeCode = gomeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.brand
     *
     * @return the value of hx_service_product.brand
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.brand
     *
     * @param brand the value for hx_service_product.brand
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.machine_code
     *
     * @return the value of hx_service_product.machine_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getMachineCode() {
        return machineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.machine_code
     *
     * @param machineCode the value for hx_service_product.machine_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.internal_machine_code1
     *
     * @return the value of hx_service_product.internal_machine_code1
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getInternalMachineCode1() {
        return internalMachineCode1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.internal_machine_code1
     *
     * @param internalMachineCode1 the value for hx_service_product.internal_machine_code1
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setInternalMachineCode1(String internalMachineCode1) {
        this.internalMachineCode1 = internalMachineCode1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.internal_machine_code2
     *
     * @return the value of hx_service_product.internal_machine_code2
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getInternalMachineCode2() {
        return internalMachineCode2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.internal_machine_code2
     *
     * @param internalMachineCode2 the value for hx_service_product.internal_machine_code2
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setInternalMachineCode2(String internalMachineCode2) {
        this.internalMachineCode2 = internalMachineCode2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.external_machine_code
     *
     * @return the value of hx_service_product.external_machine_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getExternalMachineCode() {
        return externalMachineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.external_machine_code
     *
     * @param externalMachineCode the value for hx_service_product.external_machine_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setExternalMachineCode(String externalMachineCode) {
        this.externalMachineCode = externalMachineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.ticket_num
     *
     * @return the value of hx_service_product.ticket_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public BigDecimal getTicketNum() {
        return ticketNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.ticket_num
     *
     * @param ticketNum the value for hx_service_product.ticket_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setTicketNum(BigDecimal ticketNum) {
        this.ticketNum = ticketNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.invoice_num
     *
     * @return the value of hx_service_product.invoice_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getInvoiceNum() {
        return invoiceNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.invoice_num
     *
     * @param invoiceNum the value for hx_service_product.invoice_num
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.buyer
     *
     * @return the value of hx_service_product.buyer
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getBuyer() {
        return buyer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.buyer
     *
     * @param buyer the value for hx_service_product.buyer
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.buy_date
     *
     * @return the value of hx_service_product.buy_date
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public Date getBuyDate() {
        return buyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.buy_date
     *
     * @param buyDate the value for hx_service_product.buy_date
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.install_date
     *
     * @return the value of hx_service_product.install_date
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public Date getInstallDate() {
        return installDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.install_date
     *
     * @param installDate the value for hx_service_product.install_date
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.install_unit
     *
     * @return the value of hx_service_product.install_unit
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getInstallUnit() {
        return installUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.install_unit
     *
     * @param installUnit the value for hx_service_product.install_unit
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setInstallUnit(String installUnit) {
        this.installUnit = installUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.sale_center
     *
     * @return the value of hx_service_product.sale_center
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getSaleCenter() {
        return saleCenter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.sale_center
     *
     * @param saleCenter the value for hx_service_product.sale_center
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setSaleCenter(String saleCenter) {
        this.saleCenter = saleCenter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.sale_market
     *
     * @return the value of hx_service_product.sale_market
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getSaleMarket() {
        return saleMarket;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.sale_market
     *
     * @param saleMarket the value for hx_service_product.sale_market
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setSaleMarket(String saleMarket) {
        this.saleMarket = saleMarket;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.sale_price
     *
     * @return the value of hx_service_product.sale_price
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.sale_price
     *
     * @param salePrice the value for hx_service_product.sale_price
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.is_sale
     *
     * @return the value of hx_service_product.is_sale
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getIsSale() {
        return isSale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.is_sale
     *
     * @param isSale the value for hx_service_product.is_sale
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setIsSale(String isSale) {
        this.isSale = isSale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.warranty
     *
     * @return the value of hx_service_product.warranty
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public Date getWarranty() {
        return warranty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.warranty
     *
     * @param warranty the value for hx_service_product.warranty
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setWarranty(Date warranty) {
        this.warranty = warranty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.install_province
     *
     * @return the value of hx_service_product.install_province
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getInstallProvince() {
        return installProvince;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.install_province
     *
     * @param installProvince the value for hx_service_product.install_province
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setInstallProvince(String installProvince) {
        this.installProvince = installProvince;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.install_detail_address
     *
     * @return the value of hx_service_product.install_detail_address
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getInstallDetailAddress() {
        return installDetailAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.install_detail_address
     *
     * @param installDetailAddress the value for hx_service_product.install_detail_address
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setInstallDetailAddress(String installDetailAddress) {
        this.installDetailAddress = installDetailAddress;
    }

    public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.bargain_code
     *
     * @return the value of hx_service_product.bargain_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getBargainCode() {
        return bargainCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.bargain_code
     *
     * @param bargainCode the value for hx_service_product.bargain_code
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setBargainCode(String bargainCode) {
        this.bargainCode = bargainCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.product_note
     *
     * @return the value of hx_service_product.product_note
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getProductNote() {
        return productNote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.product_note
     *
     * @param productNote the value for hx_service_product.product_note
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.create_man_p
     *
     * @return the value of hx_service_product.create_man_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getCreateManP() {
        return createManP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.create_man_p
     *
     * @param createManP the value for hx_service_product.create_man_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setCreateManP(String createManP) {
        this.createManP = createManP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.create_organization_p
     *
     * @return the value of hx_service_product.create_organization_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getCreateOrganizationP() {
        return createOrganizationP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.create_organization_p
     *
     * @param createOrganizationP the value for hx_service_product.create_organization_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setCreateOrganizationP(String createOrganizationP) {
        this.createOrganizationP = createOrganizationP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.create_time_p
     *
     * @return the value of hx_service_product.create_time_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public Date getCreateTimeP() {
        return createTimeP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.create_time_p
     *
     * @param createTimeP the value for hx_service_product.create_time_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setCreateTimeP(Date createTimeP) {
        this.createTimeP = createTimeP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.alter_man_p
     *
     * @return the value of hx_service_product.alter_man_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getAlterManP() {
        return alterManP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.alter_man_p
     *
     * @param alterManP the value for hx_service_product.alter_man_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setAlterManP(String alterManP) {
        this.alterManP = alterManP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.alter_organization_p
     *
     * @return the value of hx_service_product.alter_organization_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public String getAlterOrganizationP() {
        return alterOrganizationP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.alter_organization_p
     *
     * @param alterOrganizationP the value for hx_service_product.alter_organization_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setAlterOrganizationP(String alterOrganizationP) {
        this.alterOrganizationP = alterOrganizationP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hx_service_product.alter_time_p
     *
     * @return the value of hx_service_product.alter_time_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public Date getAlterTimeP() {
        return alterTimeP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hx_service_product.alter_time_p
     *
     * @param alterTimeP the value for hx_service_product.alter_time_p
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    public void setAlterTimeP(Date alterTimeP) {
        this.alterTimeP = alterTimeP;
    }

    public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hx_service_product
     *
     * @mbggenerated Fri Aug 29 14:56:02 CST 2014
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", customerId=").append(customerId);
        sb.append(", installCardNum=").append(installCardNum);
        sb.append(", deliveryOrderNum=").append(deliveryOrderNum);
        sb.append(", installNum=").append(installNum);
        sb.append(", machineType=").append(machineType);
        sb.append(", gomeCode=").append(gomeCode);
        sb.append(", brand=").append(brand);
        sb.append(", machineCode=").append(machineCode);
        sb.append(", internalMachineCode1=").append(internalMachineCode1);
        sb.append(", internalMachineCode2=").append(internalMachineCode2);
        sb.append(", externalMachineCode=").append(externalMachineCode);
        sb.append(", ticketNum=").append(ticketNum);
        sb.append(", invoiceNum=").append(invoiceNum);
        sb.append(", buyer=").append(buyer);
        sb.append(", buyDate=").append(buyDate);
        sb.append(", installDate=").append(installDate);
        sb.append(", installUnit=").append(installUnit);
        sb.append(", saleCenter=").append(saleCenter);
        sb.append(", saleMarket=").append(saleMarket);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", isSale=").append(isSale);
        sb.append(", warranty=").append(warranty);
        sb.append(", installProvince=").append(installProvince);
        sb.append(", installDetailAddress=").append(installDetailAddress);
        sb.append(", bargainCode=").append(bargainCode);
        sb.append(", productNote=").append(productNote);
        sb.append(", createManP=").append(createManP);
        sb.append(", createOrganizationP=").append(createOrganizationP);
        sb.append(", createTimeP=").append(createTimeP);
        sb.append(", alterManP=").append(alterManP);
        sb.append(", alterOrganizationP=").append(alterOrganizationP);
        sb.append(", alterTimeP=").append(alterTimeP);
        sb.append("]");
        return sb.toString();
    }
}