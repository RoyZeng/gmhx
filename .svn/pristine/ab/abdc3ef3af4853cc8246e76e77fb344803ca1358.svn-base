package com.gome.gmhx.service.basicdata.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxProductDetailDao;
import com.gome.gmhx.entity.HxProductDetail;
import com.gome.gmhx.service.basicdata.HxProductDetailService;

@Service("hxProductDetailService")
public class HxProductDetailServiceImpl implements HxProductDetailService {
	
	@Resource
	private HxProductDetailDao hxProductDetailDao;

	@Override
	public List<Map<String, Object>> getHxProductDetailPageList(Page page) {
		return hxProductDetailDao.getHxProductDetailPageList(page);
	}

	@Override
	public HxProductDetail getHxProductDetailById(String id) {
		return hxProductDetailDao.getHxProductDetailById(id);
	}

	@Override
	public void updateHxProductDetail(HxProductDetail hxProductDetail) {
		hxProductDetailDao.updateHxProductDetail(hxProductDetail);
	}

	@Override
	public void addHxProductDetail(HxProductDetail hxProductDetail) {
		hxProductDetailDao.addHxProductDetail(hxProductDetail);
	}

}
