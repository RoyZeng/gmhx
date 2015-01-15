package com.gome.gmhx.service.orgmanage.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.orgmanage.HxExamineSettlementTicketDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;
import com.gome.gmhx.dao.wsdl.EccDao;
import com.gome.gmhx.entity.HxExamineSettlementTicket;
import com.gome.gmhx.entity.HxSettlementDetail;
import com.gome.gmhx.entity.HxSettlementDetailOther;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxSettlementVO;
import com.gome.gmhx.jbpm.JbpmProcessDefinations;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.jbpm.JbpmTransactions;
import com.gome.gmhx.jbpm.JbpmWorkEntityStatus;
import com.gome.gmhx.service.basicdata.HxCodeService;
import com.gome.gmhx.service.orgmanage.HxExamineSettlementService;

@Service("hxExamineSettlementService")
public class HxExamineSettlementServiceImpl implements HxExamineSettlementService {
	
	@Resource
	HxServiceTicketDao serviceTicketDao;
	
	@Resource
	private HxCodeService hxCodeService;
	
	@Resource
	private EccDao eccDao;
	
	@Resource
	HxExamineSettlementTicketDao hxExamineSettlementTicketDao;
	
	@Resource
	JbpmService jbpmService;
	
	private Integer sequence;

	@Override
	public List<Map<String, Object>> getExamineSettlementPageList(Page page) {
		List<String> ids = this.jbpmService.findMyTaskWorkEntityIdByProcessDefinitionKeyAndActivityName(JbpmProcessDefinations.serviceBillAuditing,JbpmWorkEntityStatus.status_headDept_review);
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
			return hxExamineSettlementTicketDao.getExamineSettlementPageList(page);
		}else{
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public HxExamineSettlementTicket geExamineSettlementById(String settlementId) {
		return hxExamineSettlementTicketDao.getExamineSettlementById(settlementId);
	}

	@Override
	public List<Map<String, Object>> getOtherFeeGridById(String settlementId) {
		return hxExamineSettlementTicketDao.getOtherFeeGridById(settlementId);
	}

	@Override
	public List<Map<String, Object>> getSettlementFeeDetailGrid(String settlementId) {
		return hxExamineSettlementTicketDao.getSettlementFeeDetailGrid(settlementId);
	}

	@Override
	public void settlementFee(Calendar calender) throws Exception{
		Date date = calender.getTime();
		//1、获取所有的网点信息（用户Id，公司Id，上级机构Id）
		List<Map<String, Object>> list = serviceTicketDao.getExistWD(date);
		if(list.size()!=0){
			for(Map<String, Object> map : list){
				Object orgCode = map.get("serviceUnit");
				Object orgName = map.get("unitName");
				if(!orgCode.equals("ZGS")){
					HxExamineSettlementTicket est = new HxExamineSettlementTicket();
					est.setServiceUnit(orgCode.toString());
					est.setSettlementTime(date);
					
					// 维修单
					est.setSettlementType("01");
					this.sequence = hxExamineSettlementTicketDao.getMaxSequence(est);
					createSettlementTicket(est);
					
					// 安装单
					est.setSettlementType("02");
					this.sequence = hxExamineSettlementTicketDao.getMaxSequence(est);
					createSettlementTicket(est);
					
					// 保外销售单
					est.setSettlementType("03");
					this.sequence = hxExamineSettlementTicketDao.getMaxSequence(est);
					createSettlementTicket(est);
				}
			}
		}
	}
	
	public void createSettlementTicket(HxExamineSettlementTicket hest) throws Exception{
		Map<String,List<HxSettlementDetail>> hestMap = new HashMap<String, List<HxSettlementDetail>>();
		List<HxSettlementDetail> hsds = getServiceTicket(hest);
		
		// 将特定网点下的所有单据，根据品类放入Map中。
		if(hsds.size()!=0){
			for(HxSettlementDetail hsd : hsds){
				String category = hsd.getCategory();
				if(hestMap.get(category)==null){
					List<HxSettlementDetail> hestList= new ArrayList<HxSettlementDetail>();
					hestList.add(hsd);
					hestMap.put(category, hestList);
				}else{
					hestMap.get(category).add(hsd);
				}
			}
			
			// 遍历Map,每一个品类生成一个结算单。
			Iterator<Map.Entry<String,List<HxSettlementDetail>>> iterator = hestMap.entrySet().iterator();
			while (iterator.hasNext()){ 
				Entry<String, List<HxSettlementDetail>> entry = iterator.next();
				// 结算单品类
				hest.setCategory(entry.getKey());
				List<HxSettlementDetail> hsdList = entry.getValue();
				
				hest.setSettlementId(createSettlementId(hest));
				hest.setSequence(sequence);
				
				Float settlementFee = new Float(0.0);
				Float manageFee = new Float(0.0);
				Float serviceFee = new Float(0.0);
				
				for(HxSettlementDetail mp : hsdList){
					mp.setSettlementId(hest.getSettlementId());
					if(mp.getSettlementFee()!=null){
						settlementFee += mp.getSettlementFee();
					}
					if(mp.getManagementFee()!=null){
						manageFee += mp.getManagementFee();
					}
					if(mp.getServiceFee()!=null){
						serviceFee += mp.getServiceFee();
					}
				}
				
				hest.setSettlementFee(settlementFee);
				hest.setSettlementFactor(1);
				hest.setServiceFee(serviceFee);
				hest.setOtherFee(new Float(0.0));
				hest.setFakeDeductFactor(0);
				hest.setSettlementStatus("S1");
				hest.setCreateMan("系统管理员");
				hest.setCreateTime(new Date());
				
				hxExamineSettlementTicketDao.insertExamineSettlementTicket(hest);
				hxExamineSettlementTicketDao.insertSettlementDetails(hsdList);
				//HxExamineSettlementTicket examineSettlementTicket = hxExamineSettlementTicketDao.getExamineSettlementById(settlementId);
				this.jbpmService.startProcessInstanceByKey(JbpmProcessDefinations.serviceBillAuditing, hest);
				hxExamineSettlementTicketDao.updateExamineSettlementTicket(hest);
			}
		}
	}
	
	/**
	 * 生成结算单号
	 * @param hest(网点，类型，品类)
	 * @return 结算单号。
	 */
	private String createSettlementId(HxExamineSettlementTicket hest){
		String settlementId = "JS-";
		String unit = hest.getServiceUnit();
		List<Map<String, Object>> list = hxCodeService.getServiceUnit(unit);
		if(list.size()!=0){
			Map<String, Object> map = list.get(0);
			Object object = map.get("code");
			String serviceUnit = object.toString();
			hest.setParentOrganization(serviceUnit.substring(serviceUnit.indexOf("_")+1));
		}
		// 生成结算单号"JS-FLL0100005-1212281865"  JS-WX/AZ/BW-公司ID-六位时间+序列号
		if(hest.getSettlementType()=="01"){
			settlementId += "WX-" + hest.getServiceUnit() + "-";
		}else if(hest.getSettlementType()=="02"){
			settlementId += "AZ-" + hest.getServiceUnit() + "-";
		}else if(hest.getSettlementType()=="03"){
			settlementId += "BW-" + hest.getServiceUnit() + "-";
		}

		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String currentDateString = format.format(new Date());

		if(this.sequence!=null){
			if(this.sequence<10000){
				settlementId = settlementId + currentDateString + String.format("%04d", ++this.sequence); 
			}else{
				settlementId = settlementId + currentDateString + String.valueOf(++this.sequence);
			}
		}else{
			this.sequence = new Integer(0) ;
			settlementId = settlementId + currentDateString + String.format("%04d", ++this.sequence); 
		}
		return settlementId;
	}
	
	/**
	 * 根据网点和结算类型，查询出所有的服务单。
	 * @param est
	 * @return 服务单列表。
	 */
	public List<HxSettlementDetail> getServiceTicket(HxExamineSettlementTicket est){
		if(est.getSettlementType()=="01"||est.getSettlementType()=="02"){
			List<HxSettlementDetail> settlementTickets= serviceTicketDao.getStandardSettlementTicket(est);
			return settlementTickets;
		}else if(est.getSettlementType()=="03"){
			List<HxSettlementDetail> settlementTickets= serviceTicketDao.getOverproofSettlementTicket(est);
			return settlementTickets;
		}
		return null;
	}

	@Override
	public String updateSettlement(SysUser sysUser, HxSettlementVO settlementVO) {
		HxExamineSettlementTicket settlementTicket = settlementVO.getExamineSettlementTicket();
		List<HxSettlementDetailOther> settlementDetailOthers = settlementVO.getSettlementDetailOthers();
		
		HxExamineSettlementTicket examineSettlementTicket = hxExamineSettlementTicketDao.getExamineSettlementById(settlementTicket.getSettlementId());
		examineSettlementTicket.setComment(settlementTicket.getComment());
		examineSettlementTicket.setSettlementFactor(settlementTicket.getSettlementFactor());
		
		examineSettlementTicket.setAlterMan(sysUser.getUserAccount());
		examineSettlementTicket.setAlterTime(new Date());
		
		// 奖励费
		Float rewardFee = new Float(0.0);
		Integer rewardNum = 0;
		// 处罚费	
		Float punishFee = new Float(0.0);
		Integer punishNum = 0;
		// 其他费
		Float otherFee = new Float(0.0);
		Integer otherNum = 0;
		
		Integer factor;
		if(examineSettlementTicket.getSettlementFactor()==null||examineSettlementTicket.getSettlementFactor()==0){
			factor = 1;
		}else{
			factor = examineSettlementTicket.getSettlementFactor();
		}
		
		if(settlementDetailOthers != null && !settlementDetailOthers.isEmpty()){
			Map<String, Object> otherMap = new HashMap<String, Object>();
			otherMap.put("settlementId", examineSettlementTicket.getSettlementId());
			otherMap.put("list", settlementDetailOthers);
			otherMap.put("createTime",new Date());
			for(HxSettlementDetailOther other : settlementDetailOthers){
				if(other.getRewardAmount()!=null&&other.getRewardQuantity()!=null){
					rewardFee += other.getRewardAmount() * other.getRewardQuantity();
					rewardNum += other.getRewardQuantity();
				}
				if(other.getPunishAmount()!=null&&other.getPunishQuantity()!=null){
					punishFee += other.getPunishAmount() * other.getPunishQuantity();
					punishNum += other.getPunishQuantity();
				}
				if(other.getOtherFee()!=null&&other.getOtherAmount()!=null){
					otherFee += other.getOtherFee() * other.getOtherAmount();
					otherNum += other.getOtherAmount();
				}
			}
			Float serviceFee;
			if(examineSettlementTicket.getServiceFee()==null||examineSettlementTicket.getServiceFee()==0){
				serviceFee = new Float(0.0);
			}else{
				serviceFee = examineSettlementTicket.getServiceFee();
			}
			
			Float sumary = (serviceFee*factor) + rewardFee - punishFee + otherFee;
			examineSettlementTicket.setRewardFee(rewardFee);
			examineSettlementTicket.setRewardQuantity(rewardNum);
			
			examineSettlementTicket.setPunishFee(punishFee);
			examineSettlementTicket.setPunishQuantity(punishNum);
			
			examineSettlementTicket.setOtherFee(otherFee);
			examineSettlementTicket.setOtherQuantity(otherNum);
			
			examineSettlementTicket.setSettlementFee(sumary);
			hxExamineSettlementTicketDao.deleteOthersById(examineSettlementTicket.getSettlementId());
			hxExamineSettlementTicketDao.insertOthers(otherMap);
		}else{
			examineSettlementTicket.setRewardFee(new Float(0.0));
			examineSettlementTicket.setRewardQuantity(0);
			
			examineSettlementTicket.setPunishFee(new Float(0.0));
			examineSettlementTicket.setPunishQuantity(0);
			
			examineSettlementTicket.setOtherFee(new Float(0.0));
			examineSettlementTicket.setOtherQuantity(0);
			
			examineSettlementTicket.setSettlementFee(examineSettlementTicket.getServiceFee()*factor);
			hxExamineSettlementTicketDao.deleteOthersById(examineSettlementTicket.getSettlementId());
		}
		hxExamineSettlementTicketDao.updateExamineSettlementTicket(examineSettlementTicket);
		return examineSettlementTicket.getSettlementId();
	}

	@Override
	public void examineSettlement(String settlementId) {
		HxExamineSettlementTicket examineSettlementTicket = hxExamineSettlementTicketDao.getExamineSettlementById(settlementId);
		this.jbpmService.completeTask(examineSettlementTicket, JbpmTransactions.transactionOperationComplete,"");
		hxExamineSettlementTicketDao.updateExamineSettlementTicket(examineSettlementTicket);
	}

	@Override
	public List<Map<String, Object>> getComfirmSettlementPageList(Page page) {
		List<String> ids = this.jbpmService.findMyTaskWorkEntityIdByProcessDefinitionKeyAndActivityName(JbpmProcessDefinations.serviceBillAuditing,JbpmWorkEntityStatus.status_website_confirm);
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
			return hxExamineSettlementTicketDao.getExamineSettlementPageList(page);
		}else
			return new ArrayList<Map<String, Object>>();
	}

	@Override
	public String confirmSettlement(SysUser sysUser, String workEntityId,String approveComment, String checkAgree) {
		HxExamineSettlementTicket examineSettlementTicket = hxExamineSettlementTicketDao.getExamineSettlementById(workEntityId);
		String result = "";
		if("1".equals(checkAgree)){
			this.jbpmService.completeTask(examineSettlementTicket, JbpmTransactions.transactionPass,approveComment);
        	result = "同意成功，该流程继续向下执行!";
        }else if("0".equals(checkAgree)){
        	this.jbpmService.completeTask(examineSettlementTicket, JbpmTransactions.transactionRefuse,approveComment);
        	result = "不同意成功，该流程无法向下执行!";
        }
		hxExamineSettlementTicketDao.updateExamineSettlementTicket(examineSettlementTicket);
		return result;
	}

	@Override
	public List<Map<String, Object>> getExamineSettlementHistoryPageList(Page page) {
		return hxExamineSettlementTicketDao.getExamineSettlementPageList(page);
	}
	
}














