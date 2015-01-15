package com.gome.gmhx.service.materialmanage.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.DateUtils;
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.dao.basicdata.HxCodeDao;
import com.gome.gmhx.dao.materialmanage.HxMaterialDao;
import com.gome.gmhx.dao.orgmanage.HxLimitDao;
import com.gome.gmhx.dao.storeinfo.HxCurrentStockDao;
import com.gome.gmhx.entity.HxCurrentStock;
import com.gome.gmhx.entity.HxLimit;
import com.gome.gmhx.entity.HxMaterialDetail;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.vo.HxMaterialDetailVO;
import com.gome.gmhx.entity.vo.HxMaterialManageVO;
import com.gome.gmhx.jbpm.JbpmProcessDefinations;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.jbpm.JbpmTransactions;
import com.gome.gmhx.jbpm.JbpmWorkEntityStatus;
import com.gome.gmhx.service.materialmanage.HxMaterialService;

@Service("hxMaterialService")
public class HxMaterialSeviceImpl implements HxMaterialService {
	@Resource
	private HxMaterialDao hxMaterialDao;
	@Resource
	private HxCurrentStockDao hxCurrentStockDao;
	@Resource
	private HxLimitDao hxLimitDao;
	@Resource HxCodeDao hxCodeDao;
	@Resource
	JbpmService jbpmService;

	@Override
	public List<String> getFittingBySuit(String suitModel,String faultCode) {
		return hxMaterialDao.getFittingBySuit(suitModel,faultCode);
	}

	@Override
	public Map<String, Object> getFittingInfo(Map<String, Object> map) {
		return hxMaterialDao.getFittingInfo(map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addHxMaterial(Map<String, Object> map) {
		HxMaterialManage manage = (HxMaterialManage)map.get("manage");
		if(manage.getMoveDirection() == null) {
			manage.setMoveDirection(Constrants.DEFAULT_MOVE_DIRECTION);
		}
		List<HxMaterialDetail> detailList = (List<HxMaterialDetail>)map.get("detail");
		for(HxMaterialDetail hxMaterialDetail : detailList){
			hxMaterialDetail.setListNumber(manage.getListNumber());
		}
		hxMaterialDao.addHxMaterialDetail(detailList);
		if("zb-n-cg".equals(manage.getType())){//增加总部库存，产生入库批次号
			updateZBStock(manage, detailList);
		}
		hxMaterialDao.addHxMaterialManage(manage);
	}

	private void updateZBStock(HxMaterialManage manage, List<HxMaterialDetail> detailList) {
		Integer isNew = 1;
		for (HxMaterialDetail detail : detailList) {
			HxCurrentStock stock = new HxCurrentStock();
//			String orgId = manage.getReceiveCompany();
			stock.setOrgId(Constrants.FITTING_HXZB);//物料总公司挂在'ZGS'下，人是在国美总部下。
			stock.setFittingCode(detail.getFittingCode());
			stock.setIsNew(isNew);
			HxCurrentStock tmpCurrentStock = this.hxCurrentStockDao.getCurrentStock(stock);
			Integer apply = detail.getApplyCount();
			if(tmpCurrentStock != null){
				stock.setStock(tmpCurrentStock.getStock() + apply);
				this.hxCurrentStockDao.updateHxCurrentStock(stock);
			}else {
				stock.setCountWay(0);
				stock.setStock(apply);
				stock.setType(Constrants.ZB);
				this.hxCurrentStockDao.insertCurrentStock(stock);
			}
		}
		manage.setInBatch(this.getInBatch());
	}

	@Override
	public String getListNumber(String companyId) {
		return hxMaterialDao.getListNumber(companyId);
	}
	
	@Override
	public HxMaterialManage getHxMaterialManage(String listNumber) {
		return hxMaterialDao.getHxMaterialManage(listNumber);
	}
	
	@Override
	public HxMaterialManage getHxMaterialManageShow(String listNumber){
		return hxMaterialDao.getHxMaterialManageShow(listNumber);
	}

	@Override
	public String getFatherFittingOrgId(String fittingOrgId) {
		return hxMaterialDao.getFatherFittingOrgId(fittingOrgId);
	}

	@Override
	public List<Map<String, Object>> getHxMaterialPageList(Page page) {
		return hxMaterialDao.getHxMaterialPageList(page);
	}
	
	@Override
	public List<Map<String, Object>> getHxMaterialHistoryPageList(Page page) {
		return hxMaterialDao.getHxMaterialHistoryPageList(page);
	}
	
	@Override
	public List<Map<String, Object>> getHxParcelPageList(Page page) {
		return hxMaterialDao.getHxParcelPageList(page);
	}

	@Override
	public List<Map<String, Object>> getHxMaterialDetail(String listNumber) {
		HxMaterialManage hmm = hxMaterialDao.getHxMaterialManage(listNumber);
		String type = hmm.getType();
		Map<String, Object> stockMap = new HashMap<String, Object>();
		stockMap.put("listNumber", listNumber);
		if(type.indexOf("-o-") > 0){
			stockMap.put("isNew", 0);
		}else{
			stockMap.put("isNew", 1);
		}

		return hxMaterialDao.getHxMaterialDetail2(stockMap);
	}
	
	@Override
	public List<Map<String, Object>> getHxMaterialDetails(String listNumber) {
		return hxMaterialDao.getHxMaterialDetail(listNumber);
	}
	@Override
	public List<Map<String, Object>> getHxMaterialStock(String listNumber){
		return hxMaterialDao.getHxMaterialStock(listNumber);
	}


	@Override
	public void delHxMaterial(String listNumber) {
		hxMaterialDao.delHxMaterialDetail(listNumber);
		hxMaterialDao.delHxMaterialManage(listNumber);
	}

	@Override
	public void updateStatus(Map<String, String> map) {
		hxMaterialDao.updateStatus(map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void updateCurrentStock(Map<String, Object> map) throws Exception {
		String type = String.valueOf(map.get("authId"));
		HxMaterialManage manage = (HxMaterialManage)map.get("manage");
		if(type.indexOf("-n-") > 0){
			map.put("isNew", 1);
		}
		if(type.indexOf("-o-") > 0){
			map.put("isNew", 0);
		}
		if(type.indexOf("-rk-") > 0){
			List<HxMaterialDetail> details = (List<HxMaterialDetail>)map.get("detail");
			for(HxMaterialDetail hxMaterialDetail : details){
				Map<String, Object> newMap = new HashMap<String, Object>();
				newMap.put("fittingCode", hxMaterialDetail.getFittingCode());
				newMap.put("isNew", map.get("isNew"));
				newMap.put("companyId", manage.getReceiveCompany());
				HxCurrentStock hxCurrentStock = hxMaterialDao.isExist(newMap);
				
				newMap.put("stock", hxMaterialDetail.getApplyCount());
				if(hxCurrentStock == null){
					if(type.startsWith("zb-")){
						newMap.put("type", 0);
					}
					if(type.startsWith("fb-")){
						newMap.put("type", 1);
					}
					if(type.startsWith("wd-")){
						newMap.put("type", 2);
					}
					hxMaterialDao.insertRkCount(newMap);
				}else{
					hxMaterialDao.updateRkCount(newMap);
				}
			}
		}
		if(type.indexOf("-ck-") > 0){
			map.put("companyId", manage.getSendCompany());
			hxMaterialDao.updateCkCount(map);
		}
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("listNumber", manage.getListNumber());
		statusMap.put("status", "03");
		hxMaterialDao.updateStatus(statusMap);
	}

	@Override
	public List<Map<String, Object>> getHxMaterialDetailForUpdate(Map<String, Object> map) {
		return hxMaterialDao.getHxMaterialDetailForUpdate(map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void updateHxMaterial(Map<String, Object> map) {
		HxMaterialManage manage = (HxMaterialManage)map.get("manage");
		hxMaterialDao.updateHxMaterialManage(manage);
		List<HxMaterialDetail> detailList = (List<HxMaterialDetail>)map.get("detail");
		for(HxMaterialDetail hxMaterialDetail : detailList){
			hxMaterialDetail.setListNumber(manage.getListNumber());
		}
		hxMaterialDao.delHxMaterialDetail(manage.getListNumber());
		hxMaterialDao.addHxMaterialDetail(detailList);
	}

	@Override
	public void hxMaterialSend(String listNumber, String type) {
		String processDefineKey = JbpmProcessDefinations.getProcessDefinations(type);
		HxMaterialManage hmm = this.hxMaterialDao.getHxMaterialManage(listNumber);
		this.jbpmService.startProcessInstanceByKey(processDefineKey, hmm);
		hmm.setApplyDate(Calendar.getInstance().getTime());
		this.hxMaterialDao.updateHxMaterialManage(hmm);
		if("wd-n-sq".equals(hmm.getType())){
			checkLimits(hmm);
		}
	}

	/** 检测网点新料申请费用额度**/
	private void checkLimits(HxMaterialManage hmm) {
		String listNumber = hmm.getListNumber();
		String type = hmm.getType();
		List<Map<String, Object >> list = this.hxMaterialDao.getHxMaterialDetail(listNumber);
		Long total = 0L;
		for (Map<String, Object> map : list) {
			Integer apply = (Integer)map.get("applyCount");
			total = total + apply *((Float) map.get("price")).longValue();
		}
		//网点费用额度控制
		if(type.startsWith("wd") && (type.indexOf("sq") > 0)){
			HxLimit oriLimit = hxLimitDao.getOriLimitByOrg(hmm.getReceiveCompany());
			HxLimit latestLimit = hxLimitDao.getLatestLimitByOrg(hmm.getReceiveCompany());	
			if(oriLimit != null){
				Long availablity = latestLimit.getLimitTotal();
				if(total > availablity){
					throw new RuntimeException("申请费用超出收货单位额度");
				}
        	}else{
				throw new RuntimeException("收货单位机构不存在额度记录，请联系管理员！");
			}
		}
		
	}

	@Override
	public void updateHxMaterialManage(String listNumber,String transactionpass, String type) {
		HxMaterialManage hmm = this.hxMaterialDao.getHxMaterialManage(listNumber);
		this.jbpmService.completeTask(hmm, transactionpass, type);
		this.hxMaterialDao.updateHxMaterialManage(hmm);
	}

	@Override
	public void updateHxMaterialDetails(ArrayList<HxMaterialDetail> list) {
		if(list != null && list.size() >0){
			String listNumber = list.get(0).getListNumber();
			HxMaterialManage manage = this.hxMaterialDao.getHxMaterialManage(listNumber);
			this.jbpmService.completeTask(manage, JbpmTransactions.transactionOperationComplete, "确认出库数量");
			for (HxMaterialDetail detail : list) {
				this.hxMaterialDao.updateHxMaterialDetail(detail);
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", JbpmWorkEntityStatus.status_out_bound);
			map.put("listNumber", listNumber);
			this.hxMaterialDao.updateStatus(map);
		}
	}

	@Override
	public void updateReduceStock(String listNumber) {
		HxMaterialManage hmm = this.hxMaterialDao.getHxMaterialManage(listNumber);
		Map<String, Object> stockMap = new HashMap<String, Object>();
		stockMap.put("listNumber", listNumber);
		String type = hmm.getType();
		Integer isNew = (type.indexOf("-o-") > 0) ? 0: 1;
		if(type.indexOf("-o-") > 0){
			stockMap.put("isNew", 0);
		}else{
			stockMap.put("isNew", 1);
		}
		List<Map<String, Object >> list = this.hxMaterialDao.getHxMaterialDetail2(stockMap);
		this.jbpmService.completeTask(hmm, JbpmTransactions.transactionOperationComplete, "确认出库");
		Long total = 0L;
		for (Map<String, Object> map : list) {
			HxCurrentStock stock = new HxCurrentStock();
			String orgId = (String)map.get("send_company");
			stock.setOrgId(orgId);
			stock.setFittingCode((String)map.get("fitting_code"));
			stock.setIsNew(isNew);
			HxCurrentStock tmpCurrentStock = this.hxCurrentStockDao.getCurrentStock(stock);
			Integer audit = (Integer)map.get("audit_count");
			if(tmpCurrentStock == null){
				throw new RuntimeException("库存无此"+map.get("fitting_code")+"配件！");
			}else if(tmpCurrentStock.getStock() < audit){
				throw new RuntimeException(stock.getFittingCode()+"配件出库量不足！");
			}
			stock.setStock(tmpCurrentStock.getStock() - audit);
			this.hxCurrentStockDao.updateHxCurrentStock(stock);
			total = total + audit *((Float) map.get("price")).longValue();
		}
		//费用额度控制 网点新料申请, 出库时减少费用额度
		if(type.startsWith("wd") && (type.indexOf("sq") > 0)){
			updateWdLimitDecrease(hmm, total);
		}
		//更新申请单状态
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", JbpmWorkEntityStatus.status_post_sending);
		map.put("listNumber", listNumber);
		this.hxMaterialDao.updateStatus(map);
		
		hmm.setSendNumber(getSendNumber(hmm.getSendCompany()));
		hmm.setUpdateTime(Calendar.getInstance().getTime());
		hmm.setOutBatch(this.getOutBatch());
		//生成发货单号及出货批次号
		this.hxMaterialDao.updateHxMaterialManage(hmm);
	}
	
	@Override
	public void updateMaterialLostReduceStock(String listNumber) {
		HxMaterialManage hmm = this.hxMaterialDao.getHxMaterialManage(listNumber);
		Map<String, Object> stockMap = new HashMap<String, Object>();
		stockMap.put("listNumber", listNumber);
		String type = hmm.getType();
		Integer isNew = (type.indexOf("-o-") > 0) ? 0: 1;
		if(type.indexOf("-o-") > 0){
			stockMap.put("isNew", 0);
		}else{
			stockMap.put("isNew", 1);
		}
		List<Map<String, Object >> list = this.hxMaterialDao.getHxMaterialDetail2(stockMap);
		this.jbpmService.completeTask(hmm, JbpmTransactions.transactionOperationComplete, "确认出库");
		Long total = 0L;
		for (Map<String, Object> map : list) {
			HxCurrentStock stock = new HxCurrentStock();
			String orgId = (String)map.get("send_company");
			stock.setOrgId(orgId);
			stock.setFittingCode((String)map.get("fitting_code"));
			stock.setIsNew(isNew);
			HxCurrentStock tmpCurrentStock = this.hxCurrentStockDao.getCurrentStock(stock);
			Integer audit = (Integer)map.get("apply_count");
			if(tmpCurrentStock == null){
				throw new RuntimeException("库存无此"+map.get("fitting_code")+"配件！");
			}
			if(tmpCurrentStock.getStock() < audit){
				throw new RuntimeException(stock.getFittingCode()+"配件出库量不足");
			}
			stock.setStock(tmpCurrentStock.getStock() - audit);
			this.hxCurrentStockDao.updateHxCurrentStock(stock);
			total = total + audit *((Float) map.get("price")).longValue();
		}
		//费用额度控制
		//网点来料报失/保外销售, 出库时增加费用额度
		if(type.startsWith("wd")){
			updateWdLimitIncrease(hmm, total);
		}
		//更新申请单状态及出库批次号
		hmm.setStatus(JbpmWorkEntityStatus.status_end);
		hmm.setOutBatch(getOutBatch());
		this.hxMaterialDao.updateHxMaterialManage(hmm);
	}
	
	@Override
	public void updateMaterialMove(String listNumber) {
		HxMaterialManage hmm = this.hxMaterialDao.getHxMaterialManage(listNumber);
		
		this.jbpmService.completeTask(hmm, JbpmTransactions.transactionOperationComplete, "确认库存转移");
		String type = hmm.getMoveDirection();
		Map<String, Object> stockMap = new HashMap<String, Object>();
		stockMap.put("listNumber", listNumber);
		//0 良品转残品
		Integer wdisNew = ("0".equals(type)) ? 1: 0;
		Integer fbisNew = ("0".equals(type)) ? 0: 1;
		List<Map<String, Object >> list = this.hxMaterialDao.getHxMaterialDetail2(stockMap);
		for (Map<String, Object> map : list) {
			String fittingCode = (String)map.get("fitting_code");
			Integer apply = (Integer)map.get("apply_count");	
			HxCurrentStock wdStock = new HxCurrentStock();
			String sendOrg = (String)map.get("send_company");
			wdStock.setOrgId(sendOrg);
			wdStock.setFittingCode(fittingCode);
			wdStock.setIsNew(wdisNew);
			HxCurrentStock wdCurrentStock = this.hxCurrentStockDao.getCurrentStock(wdStock);
			if(wdCurrentStock.getStock() < apply){
				throw new RuntimeException(wdStock.getFittingCode()+"配件申请数量超过库存量");
			}
			wdStock.setStock(wdCurrentStock.getStock() - apply);
			this.hxCurrentStockDao.updateHxCurrentStock(wdStock);
			
			HxCurrentStock fbStock = new HxCurrentStock();
			String receiveOrg = (String)map.get("receive_company");
			fbStock.setOrgId(receiveOrg);
			fbStock.setFittingCode(fittingCode);
			fbStock.setIsNew(fbisNew);
			HxCurrentStock tmpStock = this.hxCurrentStockDao.getCurrentStock(fbStock);
			if(tmpStock != null){
				fbStock.setStock(tmpStock.getStock() + apply);
				this.hxCurrentStockDao.updateHxCurrentStock(fbStock);
			}else{
				fbStock.setStock(apply);
				fbStock.setCountWay(0);
				fbStock.setType(Constrants.FB);
				this.hxCurrentStockDao.insertCurrentStock(fbStock);
			}
		}
		//更新申请单状态及出入库批次号
		hmm.setStatus(JbpmWorkEntityStatus.status_end);
		hmm.setInBatch(getInBatch());
		hmm.setOutBatch(getOutBatch());//在service加同步，多少线程同步访问时，会乱掉  得新建一个单例类。。
		this.hxMaterialDao.updateHxMaterialManage(hmm);
	}
	
	@Override
	public void updateDelivery(String listNumber) {
		HxMaterialManage hmm = this.hxMaterialDao.getHxMaterialManage(listNumber);
		this.jbpmService.completeTask(hmm, JbpmTransactions.transactionOperationComplete, "确认发货");
		
		updateCountWayStock(listNumber, hmm.getType(), true);
		//更新申请单状态
		hmm.setStatus(JbpmWorkEntityStatus.status_receive);
		hmm.setOutBatch(getOutBatch());
		this.hxMaterialDao.updateHxMaterialManage(hmm);
		
	}
	
	@Override
	public void updateReceive(String listNumber) {
		HxMaterialManage hmm = this.hxMaterialDao.getHxMaterialManage(listNumber);
		this.jbpmService.completeTask(hmm, JbpmTransactions.transactionOperationComplete, "确认收货");
		//更新申请单状态
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", JbpmWorkEntityStatus.status_check);
		map.put("listNumber", listNumber);
		this.hxMaterialDao.updateStatus(map);
		
	}
	
	@Override
	public void updateCheck(String listNumber) {
		HxMaterialManage hmm = this.hxMaterialDao.getHxMaterialManage(listNumber);
		this.jbpmService.completeTask(hmm, JbpmTransactions.transactionOperationComplete, "确认检测");
		
		//更新申请单状态
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", JbpmWorkEntityStatus.status_in_bound);
		map.put("listNumber", listNumber);
		this.hxMaterialDao.updateStatus(map);
		
	}
	
	@Override
	public void updateIn(String listNumber) {
		HxMaterialManage hmm = this.hxMaterialDao.getHxMaterialManage(listNumber);
		Map<String, Object> stockMap = new HashMap<String, Object>();
		stockMap.put("listNumber", listNumber);
		String type = hmm.getType();
		Integer fbisNew = ((type.indexOf("-o-") > 0) || (type.indexOf("ll-bl") > 0)) ? 0: 1;
		if(type.indexOf("-o-") > 0){
			stockMap.put("isNew", 0);
		}else{
			stockMap.put("isNew", 1);
		}
		List<Map<String, Object >> list = this.hxMaterialDao.getHxMaterialDetail2(stockMap);
		this.jbpmService.completeTask(hmm, JbpmTransactions.transactionOperationComplete, "确认入库");
		Long total = 0L;
		for (Map<String, Object> map : list) {
			String fittingCode = (String)map.get("fitting_code");
			Integer apply = (Integer)map.get("apply_count");
			total = total + apply *((Float) map.get("price")).longValue();
			//增加分部库存
			HxCurrentStock fbStock = new HxCurrentStock();
			String receiveOrg = (String)map.get("receive_company");
			fbStock.setOrgId(receiveOrg);
			fbStock.setFittingCode(fittingCode);
			fbStock.setIsNew(fbisNew);
			HxCurrentStock fbCurrentStock = this.hxCurrentStockDao.getCurrentStock(fbStock);
			if(fbCurrentStock != null){
				fbStock.setStock(fbCurrentStock.getStock() + apply);//增加库存
				fbStock.setCountWay(fbCurrentStock.getCountWay() - apply);//减少在途数量
				this.hxCurrentStockDao.updateHxCurrentStock(fbStock);
			}else{
				fbStock.setStock(apply);//增加库存
				fbStock.setCountWay(0);//不存在 则设置在途数量为0
				if(type.startsWith("wd")){
					fbStock.setType(Constrants.FB);//进入分部库存
				}else{
					throw new RuntimeException("配件当前库存的的类型有误！");
				}
				this.hxCurrentStockDao.insertCurrentStock(fbStock);
			}
		}
		//网点新料退回, 入库增加网点费用额度
		if(type.startsWith("wd")){
			updateWdLimitIncrease(hmm, total);
		}
		//更新申请单状态
		hmm.setStatus(JbpmWorkEntityStatus.status_end);
		hmm.setInBatch(getInBatch());
		this.hxMaterialDao.updateHxMaterialManage(hmm);
	}
	
	/**
	 * 网点增加费用额度控制
	 * @param hmm
	 * @param total
	 */
	private void updateWdLimitIncrease(HxMaterialManage hmm, Long total){
		HxLimit oriLimit = hxLimitDao.getOriLimitByOrg(hmm.getSendCompany());
		HxLimit latestLimit = hxLimitDao.getLatestLimitByOrg(hmm.getSendCompany());			
		if(oriLimit != null){
			HxLimit limit = new HxLimit();
			limit.setLimitOrgId(hmm.getSendCompany());
			limit.setLimitAddTag("1");
			limit.setLimitOriginId(oriLimit.getLimitId());
			String limitId = UUIDGenerator.getUUID();
        	limit.setLimitId(limitId);//设置主键
			limit.setLimitOperateDate(new Date());
        	limit.setLimitOperateId(hmm.getCreatePerson());
        	limit.setLimitOperateType("1");//系统自动扣除
        	Map<String, String> codeMap = new HashMap<String, String>();
        	codeMap.put("codeId", "fitting_type");
        	codeMap.put("codeKey", hmm.getType());
        	limit.setLimitOperateReason(this.hxCodeDao.getCodeValue(codeMap));//手动修改
        	limit.setLimitOriginNumber(hmm.getListNumber());
        	//如果最新的信用额度小于上限信用额度时，则先更新信用额度，否则更新现金额度
        	Long lCreditAll = latestLimit.getLimitCredit()+latestLimit.getLimitCreditChange();
        	Long lCashAll = latestLimit.getLimitCash()+latestLimit.getLimitCashChange();
        	Long iCreditAll = oriLimit.getLimitCredit()+ oriLimit.getLimitCreditChange();
        	limit.setLimitCash(lCashAll);
        	limit.setLimitCredit(lCreditAll);
			limit.setLimitMatter(total);
			if(lCreditAll < iCreditAll){
				Long less = iCreditAll - lCreditAll;
				if(total<less){
					limit.setLimitCreditChange(total);
					limit.setLimitCashChange(0L);
				}else{//将要增加的额度大于两者信用差值，应该小于两者信用与现金的总和差值
					limit.setLimitCreditChange(less);
					limit.setLimitCashChange(total - less);
				}
			}else {
				limit.setLimitCreditChange(0L);
				limit.setLimitCashChange(total);
			}
			this.hxLimitDao.addLimit(limit);
		}else{
			throw new RuntimeException("发货单位机构不存在额度记录，请联系管理员！");
		}
	}
	
	/**
	 * 网点减少费用额度控制
	 * @param hmm
	 * @param total
	 */
	private void updateWdLimitDecrease(HxMaterialManage hmm, Long total){
		HxLimit oriLimit = hxLimitDao.getOriLimitByOrg(hmm.getReceiveCompany());
		HxLimit latestLimit = hxLimitDao.getLatestLimitByOrg(hmm.getReceiveCompany());	
		if(oriLimit != null){
			HxLimit limit = new HxLimit();
			limit.setLimitAddTag("0");
			limit.setLimitOrgId(hmm.getReceiveCompany());
			limit.setLimitOriginId(oriLimit.getLimitId());
			String limitId = UUIDGenerator.getUUID();
			limit.setLimitId(limitId);//设置主键
			limit.setLimitOperateDate(new Date());
			limit.setLimitOperateId(hmm.getCreatePerson());
			limit.setLimitOperateType("1");//系统自动扣除
			Map<String, String> codeMap = new HashMap<String, String>();
			codeMap.put("codeId", "fitting_type");
			codeMap.put("codeKey", hmm.getType());
			limit.setLimitOperateReason(this.hxCodeDao.getCodeValue(codeMap));
			limit.setLimitOriginNumber(hmm.getListNumber());
			Long lCreditAll = latestLimit.getLimitCredit()+latestLimit.getLimitCreditChange();
        	Long lCashAll = latestLimit.getLimitCash()+latestLimit.getLimitCashChange();
			Long availablity =lCashAll + lCreditAll;
			limit.setLimitCredit(lCreditAll);
			limit.setLimitCash(lCashAll);
			limit.setLimitMatter(-1 * total);
			if(total < availablity){//减少的额度小于最新额度总和
				if(total < lCashAll){
					limit.setLimitCashChange(-1 * total);
					limit.setLimitCreditChange(0L);
				}else{//将要减少的额度大于最新额度的现金额度
					Long less = total - lCashAll;
					limit.setLimitCashChange(-1 * (total - less));
					limit.setLimitCreditChange(-1 * less);
				}
				this.hxLimitDao.addLimit(limit);
			}else{
				throw new RuntimeException("申请费用超出收货单位机构额度！");
			}
		}else{
			throw new RuntimeException("收货单位机构不存在额度记录，请联系管理员！");
		}
	}
	
	/**
	 * 	网点新料返回，旧料退回，来料不良: 更新收货单位在途数量, 减少网点库存
	 * @param listNumber
	 * @param type
	 * @param flag true 增加, false 减少
	 */
	private void updateCountWayStock(String listNumber, String	type, boolean flag){
		Map<String, Object> stockMap = new HashMap<String, Object>();
		stockMap.put("listNumber", listNumber);
		Integer wdisNew = (type.indexOf("-o-") > 0) ? 0: 1;
		Integer fbisNew = ((type.indexOf("-o-") > 0) || (type.indexOf("ll-bl") > 0)) ? 0: 1;
		List<Map<String, Object >> list = this.hxMaterialDao.getHxMaterialDetail2(stockMap);
		for (Map<String, Object> map : list) {
			String fittingCode = (String)map.get("fitting_code");
			Integer apply = (Integer)map.get("apply_count");	
			HxCurrentStock wdStock = new HxCurrentStock();
			String sendOrg = (String)map.get("send_company");
			wdStock.setOrgId(sendOrg);
			wdStock.setFittingCode(fittingCode);
			wdStock.setIsNew(wdisNew);
			HxCurrentStock wdCurrentStock = this.hxCurrentStockDao.getCurrentStock(wdStock);
			if(wdCurrentStock.getStock() < apply){
				throw new RuntimeException(wdStock.getFittingCode()+"配件申请数量超过库存量");
			}
			wdStock.setStock(wdCurrentStock.getStock() - apply);
			this.hxCurrentStockDao.updateHxCurrentStock(wdStock);
			
			HxCurrentStock fbStock = new HxCurrentStock();
			String receiveOrg = (String)map.get("receive_company");
			fbStock.setOrgId(receiveOrg);
			fbStock.setFittingCode(fittingCode);
			fbStock.setIsNew(fbisNew);
			HxCurrentStock tmpSotck = this.hxCurrentStockDao.getCurrentStock(fbStock);
			if(tmpSotck != null){
				fbStock.setCountWay(tmpSotck.getCountWay() + apply);//增加在途数量
				this.hxCurrentStockDao.updateHxCurrentStock(fbStock);
			}else{
				fbStock.setCountWay(apply);//不存在 则设置在途数量为apply
				fbStock.setStock(0);
				if(type.startsWith("wd")){
					fbStock.setType(Constrants.FB);//进入分部库存
				}else{
					throw new RuntimeException("配件当前库存的的类型有误！");
				}
				this.hxCurrentStockDao.insertCurrentStock(fbStock);
			}
		}
	}
	
	/*
	 * 根据发货单位获取发货单号
	 */
	public synchronized String getSendNumber(String companyId){
		StringBuilder sb = new StringBuilder("FH-");
		sb.append(companyId);
		sb.append("-");
		String date = DateUtils.formatDateTime(new Date(), DateUtils.EIGHT_STYLE_DATE_FORMAT);
		sb.append(date.substring(2, 8));
		sb.append(hxMaterialDao.getSendNumber(companyId));
		return sb.toString();
	}
	/*
	 * 根据入库批次号
	 */
	public String getInBatch(){
		StringBuilder sb = new StringBuilder("RK-");
		String date = DateUtils.formatDateTime(new Date(), DateUtils.EIGHT_STYLE_DATE_FORMAT);
		sb.append(date.substring(2, 8));
		sb.append(updateIncrementThenReturn());
		return sb.toString();
	}	/*
	 * 根据出库批次号
	 */
	public String getOutBatch(){
		StringBuilder sb = new StringBuilder("CK-");
		String date = DateUtils.formatDateTime(new Date(), DateUtils.EIGHT_STYLE_DATE_FORMAT);
		sb.append(date.substring(2, 8));
		sb.append(updateIncrementThenReturn());
		return sb.toString();
	}
	
	
	public void record(String listnum,String type){
		if(type.equals("1")){//ruku
			
		}
		
		
	}

	private synchronized Long updateIncrementThenReturn(){
		this.hxMaterialDao.updateIncrementBatchNumber();
		Long batchNumber = this.hxMaterialDao.getBatchNumber();
		Assert.notNull(batchNumber, "出入库批次号初始号不可为空！");
		return batchNumber;
	}
	
	@Override
	public String getWebFittingOrgId(String fittingOrgId) {
		return this.hxMaterialDao.getWebFittingOrgId(fittingOrgId);
	}
	
	@Override
	public List<Map<String, String>> getWebFittingOrgByFatherId(String fittingOrgId){
		return this.hxMaterialDao.getWebFittingOrgByFatherId(fittingOrgId);
	}

	@Override
	public List<Map<String, Object>> getHxMaterialDetail3(String listNumber) {
		return this.hxMaterialDao.getHxMaterialDetail3(listNumber);
	}

	@Override
	public HxMaterialManage getHxMaterialShow(String listNumber) {
		return this.hxMaterialDao.getHxMaterialShow(listNumber);
	}

	@Override
	public HxMaterialManageVO getMaterialManageByNumber(String listNumber) {
		return this.hxMaterialDao.getMaterialManageByNumber(listNumber);
	}

	@Override
	public List<HxMaterialDetailVO> getMaterialDetailByNumber(String listNumber) {
		return this.hxMaterialDao.getMaterialDetailByNumber(listNumber);
	}

	@Override
	public HxMaterialManageVO getMaterialByNumber(String listNumber) {
		return this.hxMaterialDao.getMaterialByNumber(listNumber);
	}

	@Override
	public Long getListStockPrice(Map<String, Object> map) {
		return this.hxMaterialDao.getListStockPrice(map);
	}
}
