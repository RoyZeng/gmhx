package com.gome.gmhx.dao.wsdl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.JlSale;
import com.gome.gmhx.entity.JlSaleKey;

@Repository("jlSaleDao")
public interface JlSaleDao {
	public List<Map<String, Object>> getSalePageList(Page page);
	public void insertSale(JlSale sale);
	public JlSale selectSaleByPrimaryKey(JlSaleKey saleKey);
	public void updateSaleByPrimaryKey(JlSale sale);
	public void deleteSaleByPrimaryKey(JlSaleKey saleKey);
	public JlSale selectSaleByPrimaryKey(String gsdm,String thdh);
	public List<Map<String, Object>> getJlSaleDataPageList(Page page);
	public JlSale selectSaleByPK(JlSale sale);
	void insertSaleDataBatch(List<Object> list);
	public List<Map<String, Object>> getJlSaleDataExport(JlSale sale);
}
