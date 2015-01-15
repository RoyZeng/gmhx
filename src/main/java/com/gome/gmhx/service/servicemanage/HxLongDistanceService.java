package com.gome.gmhx.service.servicemanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxServiceLongDistance;
import com.gome.gmhx.entity.SysUser;

public interface HxLongDistanceService {

	String saveLongDistance(HxServiceLongDistance hxServiceLongDistance,SysUser sysUser);

	List<Map<String, Object>> getApplyPageList(Page page);

	HxServiceLongDistance getDistanceApplyById(String applyId);

	HxServiceLongDistance getDistanceApplyProById(String applyId);

	String updateLongDistance(HxServiceLongDistance hxServiceLongDistance,SysUser sysUser);

	void deleteDistanceApply(String applyId);

	void hxDistanceApplySend(String applyId);

}
