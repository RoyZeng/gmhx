package com.gome.gmhx.service.orgmanage.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.orgmanage.HxNetWorkDao;
import com.gome.gmhx.entity.HxNetWork;
import com.gome.gmhx.service.orgmanage.HxNetWorkService;



@Service("NetWorkService")
public class NetWorkServiceImpl implements HxNetWorkService{
	@Resource  
	private  HxNetWorkDao networkDao;

	@Override
	public List<Map<String, Object>> getNetWorkPageList(Page page) {
	
		return networkDao.getNetWorkPageList(page);
	}

	@Override
	public HxNetWork getShowById(String aa) {

		return networkDao.getShowById(aa);
	}
	
	

}
