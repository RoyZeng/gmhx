package com.gome.gmhx.entity;

public class ValidateBarCode {
	
	private String PPB01;//品牌代码
	private String SPFL0;//品类代码(取空调)
	private String SPBM;//商品编码(拆分目标)
	private String YJTM;//外机条码()
	private String NJTM;//内机条码()
	private String JLNUM;//金力单号
	
	public String getJLNUM() {
		return JLNUM;
	}
	public void setJLNUM(String jLNUM) {
		JLNUM = jLNUM;
	}
	public String getPPB01() {
		return PPB01;
	}
	public void setPPB01(String pPB01) {
		PPB01 = pPB01;
	}
	public String getSPFL0() {
		return SPFL0;
	}
	public void setSPFL0(String sPFL0) {
		SPFL0 = sPFL0;
	}
	public String getSPBM() {
		return SPBM;
	}
	public void setSPBM(String sPBM) {
		SPBM = sPBM;
	}
	public String getYJTM() {
		return YJTM;
	}
	public void setYJTM(String yJTM) {
		YJTM = yJTM;
	}
	public String getNJTM() {
		return NJTM;
	}
	public void setNJTM(String nJTM) {
		NJTM = nJTM;
	}

}
