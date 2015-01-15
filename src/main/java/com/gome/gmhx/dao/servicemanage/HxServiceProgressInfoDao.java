package com.gome.gmhx.dao.servicemanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.gmhx.entity.HxServiceProgressInfo;

@Repository("hxServiceProgressInfoDao")
public interface HxServiceProgressInfoDao {

	public List<Map<String, Object>> getProgressInfo(String serviceId);

	public void insertProgresses(List<HxServiceProgressInfo> infos);

}
