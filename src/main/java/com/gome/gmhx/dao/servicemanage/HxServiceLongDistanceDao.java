package com.gome.gmhx.dao.servicemanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxServiceLongDistance;

@Repository("hxServiceLongDistanceDao")
public interface HxServiceLongDistanceDao {

	public Integer getMaxSequence();

	public void insertLongDistanceApply(HxServiceLongDistance hxServiceLongDistance);

	public List<Map<String, Object>> getApplyPageList(Page page);

	public HxServiceLongDistance selectDistanceApplyById(String applyId);

	public HxServiceLongDistance selectDistanceApplyProById(String applyId);

	public void updateLongDistanceApply(HxServiceLongDistance hxServiceLongDistance);

	public void deleteDistanceApply(String applyId);
	
	public void updateDistanceApplyEffective(String applyId);

	public HxServiceLongDistance selectDistanceApplyByIdNotConverte(String applyId);
	
}
