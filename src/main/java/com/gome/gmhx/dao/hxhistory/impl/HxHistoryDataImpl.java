package com.gome.gmhx.dao.hxhistory.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.hxhistory.HxHistoryData;
import com.gome.gmhx.entity.HxServiceTicket;

/** 
 * @author 作者:wanghaojie
 * @date 创建时间：2015年1月12日 上午10:09:37 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@Repository("hxHistoryDataImpl")
public class HxHistoryDataImpl implements HxHistoryData {
	@Resource(name="sqlSessionTemplateHistory")
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Map<String,Object>> getHistoryTicketPageList(Page page){
		String statement = "com.gome.entity.HxHistoryData.selectHistoryTicketPage";
		page.setOrderColumn("serviceId");
		List<Map<String,Object>> list =  sqlSessionTemplate.selectList(statement,page);
		return list;
	}

	@Override
	public Map<String, Object> getHistoryTicketById(String serviceId) {
		String statement = "com.gome.entity.HxHistoryData.selectHistoryTicketById";
		Map<String, Object> map = sqlSessionTemplate.selectOne(statement, serviceId);
		return map;
	}

	@Override
	public List<String> getHistoryTicketByBarCode(String barCode) {
		String statement = "com.gome.entity.HxHistoryData.selectHistoryTicketByBarCode";
		List<String> list = sqlSessionTemplate.selectOne(statement, barCode);
		return list;
	}
	
	
}
