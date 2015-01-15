package com.gome.gmhx.service.servicemanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;

/** 
 * @author 作者:wanghaojie
 * @date 创建时间：2015年1月12日 上午10:32:12 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public interface HxHistoryTicketService {

	List<Map<String, Object>> getHistoryTicketPageList(Page page);

	Map<String, Object> getServiceTicketById(String serviceId);

}
