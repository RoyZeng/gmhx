package com.gome.gmhx.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

public class JlCustomerProductVO {
	//T-sale
    private String khmc;		//客户名称
    private String fph;         //发票号
    private Date jzrq;			//记账日期
    private String yyydm;       //营业员代码
    private String yyymc;		//营业员名称
    private BigDecimal thdh;	//提货单号
    private BigDecimal xsje;	//销售金额
    private String spbm;		//商品编码
    private String spmc;		//商品名称
    private String dzbm01;		//门店代码
    private String bmmc;		//门店名称
    private String spfl01;		//品类代码
    private String spflmc;		//品类名称
    private String ppb01;		//品牌代码
    private String ppbmc;		//品牌名称
    private int zpbj;			//赠品标记（0：主商品 1：商品）
    private int zpThdh;			//当ZPBJ=1时此字段对应的主商品提单号
    private int xslx;			//销售类型 （0：正常零售 1：其他建档）
    //T-install
    private BigDecimal azd01;	//安装单号
    private String lxdz;		//联系地址
    private String lxdh;		//联系电话
    private String qtdh;		//其他电话
    private Date yysj;			//预约安装时间
    private Date pwdsj;			//派网点时间
    private Date hzrq;			//回执时间
    private String azwd01;		//安装网点代码
    private String wdmc;		//安装网点名称
    private String gsxx01;		//售后公司代码
    private String gsmc;		//售后公司名称
    private String wbGsxx01;	//销售公司代码
    private String wbGsmc;		//销售公司名称
    private BigDecimal sjly;	//数据来源(201：JL CRM； 202：SAP CRM；203：JL POS； 204：CALL CENTER；205：电商；206：库巴)
    private String njtm;		//内机条码
    private String wjtm;		//外机条码
    //vo
    private String queryName;
    private String queryphone;
    private String queryMachineCode;
	private String queryInternalCode;
    private String queryExternalCode;
    private String queryAddress;
    private String queryInstallCard;
    
    private String machineType;
    private String gomeCode;
    private String brand;
    
    public String getMachineType() {
		return machineType;
	}
	public String getGomeCode() {
		return gomeCode;
	}
	public String getBrand() {
		return brand;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public void setGomeCode(String gomeCode) {
		this.gomeCode = gomeCode;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getQueryName() {
		return queryName;
	}
	public String getQueryphone() {
		return queryphone;
	}
	public String getQueryMachineCode() {
		return queryMachineCode;
	}
	public void setQueryMachineCode(String queryMachineCode) {
		this.queryMachineCode = queryMachineCode;
		this.njtm = queryMachineCode;
	}
	public String getQueryInternalCode() {
		return queryInternalCode;
	}
	public String getQueryExternalCode() {
		return queryExternalCode;
	}
	public String getQueryAddress() {
		return queryAddress;
	}
	public String getQueryInstallCard() {
		return queryInstallCard;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
		this.khmc = queryName;
	}
	public void setQueryphone(String queryphone) {
		this.queryphone = queryphone;
		this.lxdh = queryphone;
	}
	public void setQueryInternalCode(String queryInternalCode) {
		this.queryInternalCode = queryInternalCode;
		this.njtm = queryInternalCode;
	}
	public void setQueryExternalCode(String queryExternalCode) {
		this.queryExternalCode = queryExternalCode;
		this.wjtm = queryExternalCode;
	}
	public void setQueryAddress(String queryAddress) {
		this.queryAddress = queryAddress;
		this.lxdz = queryAddress;
	}
	public void setQueryInstallCard(String queryInstallCard) {
		this.queryInstallCard = queryInstallCard;
	}
	public String getKhmc() {
		return khmc;
	}
	public String getFph() {
		return fph;
	}
	public Date getJzrq() {
		return jzrq;
	}
	public String getYyydm() {
		return yyydm;
	}
	public String getYyymc() {
		return yyymc;
	}
	public BigDecimal getThdh() {
		return thdh;
	}
	public BigDecimal getXsje() {
		return xsje;
	}
	public String getSpbm() {
		return spbm;
	}
	public String getSpmc() {
		return spmc;
	}
	public String getDzbm01() {
		return dzbm01;
	}
	public String getBmmc() {
		return bmmc;
	}
	public String getSpfl01() {
		return spfl01;
	}
	public String getSpflmc() {
		return spflmc;
	}
	public String getPpb01() {
		return ppb01;
	}
	public String getPpbmc() {
		return ppbmc;
	}
	public int getZpbj() {
		return zpbj;
	}
	public int getZpThdh() {
		return zpThdh;
	}
	public int getXslx() {
		return xslx;
	}
	public BigDecimal getAzd01() {
		return azd01;
	}
	public String getLxdz() {
		return lxdz;
	}
	public String getLxdh() {
		return lxdh;
	}
	public String getQtdh() {
		return qtdh;
	}
	public Date getYysj() {
		return yysj;
	}
	public Date getPwdsj() {
		return pwdsj;
	}
	public Date getHzrq() {
		return hzrq;
	}
	public String getAzwd01() {
		return azwd01;
	}
	public String getWdmc() {
		return wdmc;
	}
	public String getGsxx01() {
		return gsxx01;
	}
	public String getGsmc() {
		return gsmc;
	}
	public String getWbGsxx01() {
		return wbGsxx01;
	}
	public String getWbGsmc() {
		return wbGsmc;
	}
	public BigDecimal getSjly() {
		return sjly;
	}
	public String getNjtm() {
		return njtm;
	}
	public String getWjtm() {
		return wjtm;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	public void setFph(String fph) {
		this.fph = fph;
	}
	public void setJzrq(Date jzrq) {
		this.jzrq = jzrq;
	}
	public void setYyydm(String yyydm) {
		this.yyydm = yyydm;
	}
	public void setYyymc(String yyymc) {
		this.yyymc = yyymc;
	}
	public void setThdh(BigDecimal thdh) {
		this.thdh = thdh;
	}
	public void setXsje(BigDecimal xsje) {
		this.xsje = xsje;
	}
	public void setSpbm(String spbm) {
		this.spbm = spbm;
	}
	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}
	public void setDzbm01(String dzbm01) {
		this.dzbm01 = dzbm01;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public void setSpfl01(String spfl01) {
		this.spfl01 = spfl01;
	}
	public void setSpflmc(String spflmc) {
		this.spflmc = spflmc;
	}
	public void setPpb01(String ppb01) {
		this.ppb01 = ppb01;
	}
	public void setPpbmc(String ppbmc) {
		this.ppbmc = ppbmc;
	}
	public void setZpbj(int zpbj) {
		this.zpbj = zpbj;
	}
	public void setZpThdh(int zpThdh) {
		this.zpThdh = zpThdh;
	}
	public void setXslx(int xslx) {
		this.xslx = xslx;
	}
	public void setAzd01(BigDecimal azd01) {
		this.azd01 = azd01;
	}
	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public void setQtdh(String qtdh) {
		this.qtdh = qtdh;
	}
	public void setYysj(Date yysj) {
		this.yysj = yysj;
	}
	public void setPwdsj(Date pwdsj) {
		this.pwdsj = pwdsj;
	}
	public void setHzrq(Date hzrq) {
		this.hzrq = hzrq;
	}
	public void setAzwd01(String azwd01) {
		this.azwd01 = azwd01;
	}
	public void setWdmc(String wdmc) {
		this.wdmc = wdmc;
	}
	public void setGsxx01(String gsxx01) {
		this.gsxx01 = gsxx01;
	}
	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}
	public void setWbGsxx01(String wbGsxx01) {
		this.wbGsxx01 = wbGsxx01;
	}
	public void setWbGsmc(String wbGsmc) {
		this.wbGsmc = wbGsmc;
	}
	public void setSjly(BigDecimal sjly) {
		this.sjly = sjly;
	}
	public void setNjtm(String njtm) {
		this.njtm = njtm;
	}
	public void setWjtm(String wjtm) {
		this.wjtm = wjtm;
	}
	@Override
	public String toString() {
		return "HxCustomerProductVO [khmc=" + khmc + ", fph=" + fph + ", jzrq="
				+ jzrq + ", yyydm=" + yyydm + ", yyymc=" + yyymc + ", thdh="
				+ thdh + ", xsje=" + xsje + ", spbm=" + spbm + ", spmc=" + spmc
				+ ", dzbm01=" + dzbm01 + ", bmmc=" + bmmc + ", spfl01="
				+ spfl01 + ", spflmc=" + spflmc + ", ppb01=" + ppb01
				+ ", ppbmc=" + ppbmc + ", zpbj=" + zpbj + ", zpThdh=" + zpThdh
				+ ", xslx=" + xslx + ", azd01=" + azd01 + ", lxdz=" + lxdz
				+ ", lxdh=" + lxdh + ", qtdh=" + qtdh + ", yysj=" + yysj
				+ ", pwdsj=" + pwdsj + ", hzrq=" + hzrq + ", azwd01=" + azwd01
				+ ", wdmc=" + wdmc + ", gsxx01=" + gsxx01 + ", gsmc=" + gsmc
				+ ", wbGsxx01=" + wbGsxx01 + ", wbGsmc=" + wbGsmc + ", sjly="
				+ sjly + ", njtm=" + njtm + ", wjtm=" + wjtm + ", queryName="
				+ queryName + ", queryphone=" + queryphone
				+ ", queryInternalCode=" + queryInternalCode
				+ ", queryExternalCode=" + queryExternalCode
				+ ", queryAddress=" + queryAddress + ", queryInstallCard="
				+ queryInstallCard + "]";
	}
    
}
