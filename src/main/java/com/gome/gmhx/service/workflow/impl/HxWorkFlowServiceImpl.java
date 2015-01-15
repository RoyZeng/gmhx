package com.gome.gmhx.service.workflow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.materialmanage.HxMaterialDao;
import com.gome.gmhx.dao.servicemanage.HxServiceLongDistanceDao;
import com.gome.gmhx.dao.servicemanage.HxServiceRetreatReplacementDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;
import com.gome.gmhx.dao.workflow.JbpmTaskTrajectoryDao;
import com.gome.gmhx.entity.HxMaterialDetail;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.HxServiceLongDistance;
import com.gome.gmhx.entity.HxServiceRetreatReplacement;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.AdditionalParamsVO;
import com.gome.gmhx.jbpm.CurrentSysUser;
import com.gome.gmhx.jbpm.JbpmProcessDefinations;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.jbpm.JbpmTransactions;
import com.gome.gmhx.jbpm.JbpmWorkEntityStatus;
import com.gome.gmhx.service.workflow.HxWorkFlowService;

@Service("hxWorkFlowService")
public class HxWorkFlowServiceImpl implements HxWorkFlowService {
	@Resource
	JbpmService jbpmService;

	@Resource
	JbpmTaskTrajectoryDao jbpmTaskTrajectoryDao;
	
	@Resource
	HxMaterialDao hxMaterialDao;
	
	@Resource
	HxServiceLongDistanceDao hxServiceLongDistanceDao;
	
	@Resource
	HxServiceTicketDao hxServiceTicketDao;
	
	@Resource
	private HxServiceRetreatReplacementDao hxRetreatReplacementCreateDao;
	
	@Override
	public List<Map<String, Object>> getWorkPageList(Page page) {
		List<String> ids = jbpmService.findAllMyTaskWorkEntityId();
		if(ids!=null && ids.size()>0){
			Map<String, Object>  map = (Map<String, Object>) page.getParam();
			String idList="";
			for(String id:ids){
				idList+="'"+id+"',";
			}
			idList=idList.substring(0, idList.length()-1);
			idList="("+idList+")";
			map.put("ids", idList);
			page.setParam(map);
			return jbpmTaskTrajectoryDao.getWorkPageList(page);
		}else
			return new ArrayList<Map<String, Object>>();
		
	}
	
	@Override
	public List<Map<String, Object>> getWorkMonitoringPageList(Page page) {
		SysUser currentSysUser = CurrentSysUser.getCurrentSysUser();
		Map<String, Object>  map = (Map<String, Object>) page.getParam();
		map.put("participant", currentSysUser.getSysPositionId()+"_"+currentSysUser.getUserAccount());
		page.setParam(map);
		return jbpmTaskTrajectoryDao.getWorkMonitoringPageList(page);

	}
	
	@Override
	public List<Map<String, Object>> getTaskTrajeCttoryPageList(Page page) {
		return jbpmTaskTrajectoryDao.getTaskTrajeCttoryPageList(page);
	}
	@Override
	public String processInstanceIdToVal(String processInstanceId) {
		return jbpmTaskTrajectoryDao.processInstanceIdToVal(processInstanceId);
	}
	@Override
	public void updateJbpmWorkEntity(String workEntityId,
			String transactionpass, String approveComment, String orderType,String checkAgree, AdditionalParamsVO additionalParams) {
		if(JbpmProcessDefinations.remoteInstallationFeeApplication.equals(orderType)){
			 HxServiceLongDistance distanceApply = hxServiceLongDistanceDao.selectDistanceApplyByIdNotConverte(workEntityId);
			 if(JbpmWorkEntityStatus.status_subDept_review.equals(distanceApply.getStatus())){
				 distanceApply.setIsCenterCheckAgree(checkAgree);
				 distanceApply.setCenterCheckComment(approveComment);
				 distanceApply.setCenterCheckMan(CurrentSysUser.getCurrentSysUser().getUserName());
				 distanceApply.setCenterCheckDate(new Date());
			 }else if(JbpmWorkEntityStatus.status_headDept_review.equals(distanceApply.getStatus())){
				 distanceApply.setIsHeadquartersCheckAgree(checkAgree);
				 distanceApply.setHeadquartersCheckComment(approveComment);
				 distanceApply.setHeadquartersCheckMan(CurrentSysUser.getCurrentSysUser().getUserName());
				 distanceApply.setHeadquartersCheckDate(new Date());
			 }
			 jbpmService.completeTask(distanceApply, transactionpass, approveComment);
			 hxServiceLongDistanceDao.updateLongDistanceApply(distanceApply);
		}else if(JbpmProcessDefinations.installationReceipt.equals(orderType) || JbpmProcessDefinations.repairReceipt.equals(orderType)){
			HxServiceTicket serviceTicket=hxServiceTicketDao.getServiceTicketByServiceId(workEntityId);
			if(JbpmWorkEntityStatus.status_subDept_review.equals(serviceTicket.getServiceStatus())){
				serviceTicket.setCenterCheckResult(checkAgree);
				serviceTicket.setCenterCheckComment(approveComment);
				serviceTicket.setCenterCheckMan(CurrentSysUser.getCurrentSysUser().getUserName());
				serviceTicket.setCenterCheckTime(new Date());
			}else if(JbpmWorkEntityStatus.status_headDept_review.equals(serviceTicket.getStatus())){
				serviceTicket.setHeadquartersCheckResult(checkAgree);
				serviceTicket.setHeadquartersCheckComment(approveComment);
				serviceTicket.setHeadquartersCheckMan(CurrentSysUser.getCurrentSysUser().getUserName());
				serviceTicket.setHeadquartersCheckTime(new Date());
			}
			if(JbpmProcessDefinations.repairReceipt.equals(orderType)){  // 维修回执
				jbpmService.completeTask(serviceTicket, transactionpass, approveComment, new HashMap());
			}else{
				jbpmService.completeTask(serviceTicket, transactionpass, approveComment);
			}
			hxServiceTicketDao.updateServiceTicketByPrimaryKeySelective(serviceTicket);
		}else if(JbpmProcessDefinations.returnMachineApply.equals(orderType)){
			HxServiceRetreatReplacement retreatReplacement = hxRetreatReplacementCreateDao.selectRetreatReplacementById(workEntityId);
			if(JbpmWorkEntityStatus.status_subDept_review.equals(retreatReplacement.getStatus())){
				retreatReplacement.setCenterCheckResult(checkAgree);
				retreatReplacement.setCenterCheckComment(approveComment);
				retreatReplacement.setCenterCheckMan(CurrentSysUser.getCurrentSysUser().getUserName());
				retreatReplacement.setCenterCheckTime(new Date());
			}else if(JbpmWorkEntityStatus.status_headDept_review.equals(retreatReplacement.getStatus())){
				retreatReplacement.setHeadquartersCheckResult(checkAgree);
				retreatReplacement.setHeadquartersCheckComment(approveComment);
				retreatReplacement.setHeadquartersCheckMan(CurrentSysUser.getCurrentSysUser().getUserName());
				retreatReplacement.setHeadquartersCheckTime(new Date());
			}
			jbpmService.completeTask(retreatReplacement, transactionpass, approveComment);
			hxRetreatReplacementCreateDao.updateRetreatReplacementById(retreatReplacement);
		}else if(JbpmProcessDefinations.MATERIAL.equals(orderType)){
			HxMaterialManage hmm = hxMaterialDao.getHxMaterialManage(workEntityId);
			jbpmService.completeTask(hmm, transactionpass, approveComment);
			hxMaterialDao.updateHxMaterialManage(hmm);
			String type = hmm.getType();
			if(JbpmTransactions.transactionPass.equals(transactionpass)){//仅在同意流程下执行
				if(type.equals("wd-n-sq") || type.equals("fb-n-sq") || type.equals("fb-n-db")){//网点/分部新料申请，审核需要填写审核数量等信息
					addMaterialDetails(hmm, additionalParams);
				}
			}
		}
	}
	
	private void addMaterialDetails(HxMaterialManage hmm, AdditionalParamsVO additionalParams) {
		hxMaterialDao.delHxMaterialDetail(hmm.getListNumber());
		if(additionalParams.getDetails().size() < 1) {
			throw new RuntimeException("无效的配件参数信息(行数为0)");
		}else {
			for (HxMaterialDetail detail : additionalParams.getDetails()) {
				detail.setListNumber(hmm.getListNumber());
			}
			hxMaterialDao.addHxMaterialDetail(additionalParams.getDetails());
		}
		
	}

	@Override
	public void endProcessJbpmWorkEntity(String workEntityId,String orderType) {
		if(JbpmProcessDefinations.remoteInstallationFeeApplication.equals(orderType)){
			 HxServiceLongDistance distanceApply = hxServiceLongDistanceDao.selectDistanceApplyByIdNotConverte(workEntityId);
			 jbpmService.terminationTask(distanceApply);
			 hxServiceLongDistanceDao.updateLongDistanceApply(distanceApply);
		}else if(JbpmProcessDefinations.installationReceipt.equals(orderType)){
			HxServiceTicket serviceTicket=hxServiceTicketDao.getServiceTicketByServiceId(workEntityId);
			jbpmService.terminationTask(serviceTicket);
			hxServiceTicketDao.updateServiceTicketByPrimaryKeySelective(serviceTicket);
		}else if(orderType.startsWith("MP")){
			HxMaterialManage hmm = hxMaterialDao.getHxMaterialManage(workEntityId);
			jbpmService.terminationTask(hmm);
			hxMaterialDao.updateHxMaterialManage(hmm);	
		}
	}

	@Override
	public List<Map<String, Object>> exportMP08Excel(String listNumber) {
		return hxMaterialDao.exportMP08Excel(listNumber);
	}

	@Override
	public List<Map<String, Object>> exportBatchMP08Excel(Map<String, Object> map) {
		return hxMaterialDao.exportBatchMP08Excel(map);
	}

}
