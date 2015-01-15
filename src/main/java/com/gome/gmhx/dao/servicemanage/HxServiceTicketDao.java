package com.gome.gmhx.dao.servicemanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxExamineSettlementTicket;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.HxSettlementDetail;

@Repository("hxServiceTicketDao")
public interface HxServiceTicketDao {

	Integer getMaxSequence();

	void affirmServiceTicketByPrimaryKey(String serviceId);

	void insertServiceTicket(HxServiceTicket serviceTicket);

	void deleteServiceTicketByPrimaryKey(String serviceId);
	
	Map<String, Object> selectHxProductDetailById(String spbm);

	List<Map<String, Object>> getGMZBPageList(Page page);
	
	List<Map<String, Object>> getServiceTicketPageList(Page page);

	Map<String, Object> selectServiceTicketByPrimaryKey(String serviceId);

	void updateServiceTicketByPrimaryKeySelective(HxServiceTicket serviceTicket);

	void updateServiceTicketToFinish(String serviceId);

	List<Map<String, Object>> getHxMaintenanceTree(String type);

	List<Map<String, Object>> getExistWD(Date time);

	HxServiceTicket getServiceTicketByServiceId(String serviceId);

	void updateServiceTicketEffective(String serviceId);

	List<HxSettlementDetail> getStandardSettlementTicket(HxExamineSettlementTicket hest);

	List<HxSettlementDetail> getOverproofSettlementTicket(HxExamineSettlementTicket hest);
	
	public void insertServiceTicketList(List<HxServiceTicket> tickets);

	Integer getServiceTicketCounts(String internalMachineCode1, String internalMachineCode2, String machineCode);

	Integer getServiceTicketCount(String machineCode);

}
