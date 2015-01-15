package com.gome.gmhx.dao.servicemanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxServiceRetreatReplacement;

@Repository("hxServiceRetreatReplacementDao")
public interface HxServiceRetreatReplacementDao {

	public Integer getMaxSequence();

	public void deleteServiceTicketByPrimaryKey(String serviceId);

	public HxServiceRetreatReplacement selectRetreatReplacementById(String applyId);

	public List<Map<String, Object>> getRetreatReplacementCreatePageList(Page page);

	public void insertRetreatReplacement(HxServiceRetreatReplacement retreatReplacement);

	public void updateRetreatReplacementById(HxServiceRetreatReplacement retreatReplacement);

	public void updateRetreatReplacementEffective(String key);

}
