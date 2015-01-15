package com.gome.gmhx.service.servicemanage.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.gmhx.dao.servicemanage.JlCustomerProductDao;
import com.gome.gmhx.entity.vo.JlCustomerProductVO;
import com.gome.gmhx.service.servicemanage.JlCustomerProductService;

@Service("jlCustomerProductService")
public class JlCustomerProductServiceImpl implements JlCustomerProductService{
	@Resource
	JlCustomerProductDao jlCustomerProductDao;

	@Override
	public List<Map<String, Object>> getCustomerProductList(JlCustomerProductVO entity) {
		return jlCustomerProductDao.getCustomerProductList(entity);
	}

	@Override
	public JlCustomerProductVO getCustomerProductById(BigDecimal key) {
		return jlCustomerProductDao.getCustomerProductById(key);
	}
}
