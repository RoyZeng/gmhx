package com.gome.gmhx.service.storeinfo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.storeinfo.HxInoutStockDao;
import com.gome.gmhx.service.storeinfo.HxInoutStockService;

@Service("hxInoutStockService")
public class HxInoutStockServiceImpl implements HxInoutStockService {
	
	@Resource
	private HxInoutStockDao hxInoutStockDao;
	@Override
	public List<Map<String, Object>> getInoutSotckHistoryPageList(Page page) {
		Map<String,Object> map = (Map<String, Object>) page.getParam();
		String inout = (String) map.get("inout");
		String fittingPositionType = (String) map.get("fittingPositionType");
		String isNew = (String) map.get("isNew");
		if("2".equals(inout) && "1".equals(fittingPositionType)){
			if("1".equals(isNew)){//总部入库只有：新料采购(新料配件)
				return this.hxInoutStockDao.getZbInStockHistoryPageList(page);
			}
			return new ArrayList<Map<String,Object>>();
		}
		return this.hxInoutStockDao.getInoutSotckHistoryPageList(page);
	}

}
