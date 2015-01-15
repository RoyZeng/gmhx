package com.gome.gmhx.service.servicemanage.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.servicemanage.HxServiceLongDistanceDao;
import com.gome.gmhx.entity.HxServiceLongDistance;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.jbpm.JbpmProcessDefinations;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.service.servicemanage.HxLongDistanceService;

@Service("hxLongDistanceService")
public class HxLongDistanceServiceImpl implements HxLongDistanceService {

	@Resource
	HxServiceLongDistanceDao hxServiceLongDistanceDao;
	
	@Resource
	JbpmService jbpmService;
	
	@Override
	public String saveLongDistance(HxServiceLongDistance hxServiceLongDistance,SysUser sysUser) {
		String applyID = "";
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String currentDateString = format.format(new Date());
		Integer sequence = hxServiceLongDistanceDao.getMaxSequence();
		String str = "";
		if(sequence!=null){
			if(sequence<10000){
				str = String.format("%04d", ++sequence); 
			}else{
				str = String.valueOf(++sequence);
			}
		}else{
			sequence = 1 ;
			str = String.format("%04d", sequence); 
		}
		applyID = "LI-"+currentDateString + str;
		hxServiceLongDistance.setApplyId(applyID);
		hxServiceLongDistance.setSequence(sequence);
		hxServiceLongDistance.setCreateManS(sysUser.getUserAccount());
		hxServiceLongDistance.setCreateTimeS(new Date());
		hxServiceLongDistance.setCreateOrganizationS(sysUser.getCompanyId());
		hxServiceLongDistance.setApplyUnit(sysUser.getCompanyId());
		hxServiceLongDistance.setStatus("S1");
		hxServiceLongDistance.setCreatePosition(sysUser.getSysPositionId());
		hxServiceLongDistanceDao.insertLongDistanceApply(hxServiceLongDistance);
		return applyID;
	}

	@Override
	public List<Map<String, Object>> getApplyPageList(Page page) {
		return hxServiceLongDistanceDao.getApplyPageList(page);
	}

	@Override
	public HxServiceLongDistance getDistanceApplyById(String applyId) {
		return hxServiceLongDistanceDao.selectDistanceApplyById(applyId);
	}

	@Override
	public HxServiceLongDistance getDistanceApplyProById(String applyId) {
		return hxServiceLongDistanceDao.selectDistanceApplyProById(applyId);
	}

	@Override
	public String updateLongDistance(HxServiceLongDistance hxServiceLongDistance, SysUser sysUser) {
		hxServiceLongDistance.setAlterManS(sysUser.getUserAccount());
		hxServiceLongDistance.setAlterOrganizationS(sysUser.getCompanyId());
		hxServiceLongDistance.setAlterTimeS(new Date());
		hxServiceLongDistanceDao.updateLongDistanceApply(hxServiceLongDistance);
		return hxServiceLongDistance.getApplyId();
	}

	@Override
	public void deleteDistanceApply(String applyId) {
		hxServiceLongDistanceDao.deleteDistanceApply(applyId);
	}

	@Override
	public void hxDistanceApplySend(String applyId) {
		HxServiceLongDistance longDistance = this.hxServiceLongDistanceDao.selectDistanceApplyProById(applyId);
		this.jbpmService.startProcessInstanceByKey(JbpmProcessDefinations.remoteInstallationFeeApplication, longDistance);
		this.hxServiceLongDistanceDao.updateLongDistanceApply(longDistance);
	}
}
