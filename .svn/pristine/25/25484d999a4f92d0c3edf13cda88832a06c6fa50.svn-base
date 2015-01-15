package com.gome.gmhx.service.materialmanage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.gmhx.dao.materialmanage.HxMaterialDao;
import com.gome.gmhx.dao.materialmanage.HxParcelDeliveryDao;
import com.gome.gmhx.dao.materialmanage.HxParcelReceiptDao;
import com.gome.gmhx.dao.storeinfo.HxCurrentStockDao;
import com.gome.gmhx.entity.HxCurrentStock;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.HxParcelDelivery;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.jbpm.JbpmTransactions;
import com.gome.gmhx.jbpm.JbpmWorkEntityStatus;
import com.gome.gmhx.service.materialmanage.HxMaterialService;
import com.gome.gmhx.service.materialmanage.HxParcelReceiptService;

@Service("hxParcelReceiptService")
public class HxParcelReceiptServiceImpl implements HxParcelReceiptService {
	@Resource
	private HxParcelReceiptDao hxParcelReceiptDao;
	@Resource
	private HxCurrentStockDao hxCurrentStockDao;
	@Resource
	private HxParcelDeliveryDao hxParcelDeliveryDao;
	@Resource
	private JbpmService jbpmService;
	@Resource
	private HxMaterialDao hxMaterialDao;
	@Resource
	private HxMaterialService hxMaterialService;//同步获取批次号
	
	@Override
	public List<Map<String, Object>> getHxParcelReceiptPageList(Page page) {
			List<String> list = this.jbpmService.findMyTaskWorkEntityIdByActivityName(JbpmWorkEntityStatus.status_post_receipt);
			
			StringBuffer sb = new StringBuffer();
			for (String string : list) {
				sb.append("'"+string+"',");
			}
			if(sb.indexOf(",")>0){
				String s = sb.toString();
				s = s.substring(0, s.lastIndexOf(","));
				sb = new StringBuffer(s);
			}else{
				sb.append("''");
			}
			((Map<String, Object>)page.getParam()).put("list", sb);
			List<Map<String, Object>> tmp = hxParcelReceiptDao.getHxParcelReceiptPageList(page);
//			List<Map<String, Object>> returnObj = new ArrayList<Map<String,Object>>();
//			for(Map<String, Object> m: tmp){
//				for(String s: list){
//					if(m.get("list_number").equals(s) && !SysUtils.isMapContainValue(returnObj, (String)m.get("parcel_code"))){//由于listNumber出现1对多的parcel,过滤。){
//						returnObj.add(m);
//					}
//				}
//			}
			return tmp;
	}

	@Override
	public void addHxParcelReceipt(HxParcelDelivery hxParcelReceipt) {
		hxParcelReceiptDao.addHxParcelReceipt(hxParcelReceipt);
	}

	@Override
	public HxParcelDelivery getHxParcelReceiptById(String orgId) {
		return hxParcelReceiptDao.getHxParcelReceiptById(orgId);
	}

	@Override
	public void updateHxParcelReceipt(HxParcelDelivery hxParcelReceipt) {
		hxParcelReceiptDao.updateHxParcelReceipt(hxParcelReceipt);
	}

	@Override
	public List<Map<String, Object>> getHxParcelReceiptExport(Map<String, Object> map) {
		return hxParcelReceiptDao.getHxParcelReceiptExport(map);
	}

	@Override
	public HxParcelDelivery getShowById(String orgId) {
		return hxParcelReceiptDao.getShowById(orgId);
	}

	@Override
	public void updateHxCurrentStock(HxParcelDelivery hxParcelReceipt) {
		hxParcelReceiptDao.updateHxCurrentStock(hxParcelReceipt);
		
	}

	@Override
	public void updateReceiveStock(HxParcelDelivery delivery) {

		String parcelCode = delivery.getParcelCode();
		List<String> listNumbers = new ArrayList<String>();
		HxParcelDelivery parcel = this.hxParcelDeliveryDao.selectByPrimaryKey(delivery.getParcelCode());
		for (Map<String, Object> map : this.hxParcelDeliveryDao.selectMaterialsByCode(parcelCode)) {
			listNumbers.add((String)map.get("list_number"));
			HxMaterialManage hmm = hxMaterialDao.getHxMaterialManage((String)map.get("list_number"));
			jbpmService.completeTask(hmm, JbpmTransactions.transactionOperationComplete, "邮包已收货");
		}

		for (String string : listNumbers) {
			HxMaterialManage hmm = hxMaterialDao.getHxMaterialManage(string);
			String type = hmm.getType();
			Map<String, Object> stockMap = new HashMap<String, Object>();
			stockMap.put("listNumber", string);
			Integer isNew = (type.indexOf("-o-") > 0) ? 0: 1;
			if(type.indexOf("-o-") > 0){
				stockMap.put("isNew", 0);
			}else{
				stockMap.put("isNew", 1);
			}
			List<Map<String, Object >> list = this.hxMaterialDao.getHxMaterialDetail2(stockMap);
			for (Map<String, Object> map : list) {
				HxCurrentStock stock = new HxCurrentStock();
				stock.setOrgId((String)map.get("receive_company"));
				stock.setFittingCode((String)map.get("fitting_code"));
				stock.setIsNew(isNew);
				HxCurrentStock tmpCurrentStock = this.hxCurrentStockDao.getCurrentStock(stock);
				Integer audit = (Integer)map.get("audit_count");
				if(tmpCurrentStock != null){
					stock.setStock(tmpCurrentStock.getStock() + audit);
					stock.setCountWay(tmpCurrentStock.getCountWay() - audit);
					this.hxCurrentStockDao.updateHxCurrentStock(stock);
				}else{
					stock.setStock(audit);
					stock.setCountWay(0);
					if(type.equals("fb-n-db") || type.startsWith("fb-n-sq")){//总部新料调拨，分部新料申请
						stock.setType(Constrants.FB);
					}else if(type.equals("wd-n-sq")){
						stock.setType(Constrants.WD);
					}else{
						throw new RuntimeException("配件当前库存的的类型有误！");
					}
					this.hxCurrentStockDao.insertCurrentStock(stock);
				}
			}
			
			//更新申请单状态及入库批次号
			hmm.setStatus(JbpmWorkEntityStatus.status_end);
			hmm.setInBatch(this.hxMaterialService.getInBatch());
			this.hxMaterialDao.updateHxMaterialManage(hmm);
		}
		parcel.setState("03");
		parcel.setUpdateDate(new Date());
		parcel.setReceiveDate(new Date());
		this.hxParcelDeliveryDao.updateParcelDelivery(parcel);	
	}

}
