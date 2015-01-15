package com.gome.gmhx.service.wsdl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.wsdl.JlSaleDao;
import com.gome.gmhx.entity.JlSale;
import com.gome.gmhx.entity.JlSaleKey;
import com.gome.gmhx.service.wsdl.service.JlSaleService;

@Service("jlSaleService")
public class JlSaleServiceImpl implements JlSaleService {
	@Resource
	private JlSaleDao saleDao;
	
	@Override
	public void insertSale(JlSale entity) {
		JlSaleKey saleKey = new JlSaleKey();
		saleKey.setGsxx01(entity.getGsxx01());
		saleKey.setThdh(entity.getThdh());
		saleKey.setXslx(entity.getXslx());
		if(saleDao.selectSaleByPrimaryKey(saleKey)==null){
			saleDao.insertSale(entity);
		}else{
			saleDao.updateSaleByPrimaryKey(entity);
		}
	}

	@Override
	public void deleteSaleById(JlSaleKey saleKey) {
		saleDao.deleteSaleByPrimaryKey(saleKey);
	}

	@Override
	public void updateSale(JlSale entity) {
		saleDao.updateSaleByPrimaryKey(entity);
	}

	@Override
	public List<Map<String, Object>> querySale(Page page) {
		return saleDao.getSalePageList(page);
	}

	@Override
	public JlSale selectSaleByPrimaryKey(String gsdm,String thdh) {
		return saleDao.selectSaleByPrimaryKey(gsdm,thdh);
	}

	@Override
	public List<Map<String, Object>> getJlSaleDataPageList(Page page) {
		return saleDao.getJlSaleDataPageList(page);
	}

	@Override
	public JlSale selectSaleByPK(JlSale sale) {
		return saleDao.selectSaleByPK(sale);
	}

	@Override
	public List<Map<String, Object>> getJlSaleDataExport(JlSale sale) {
		return saleDao.getJlSaleDataExport(sale);
	}

}
