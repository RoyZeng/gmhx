package com.gome.gmhx.service.materialmanage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.gmhx.dao.materialmanage.HxMaterialDao;
import com.gome.gmhx.dao.materialmanage.HxParcelDeliveryDao;
import com.gome.gmhx.dao.storeinfo.HxCurrentStockDao;
import com.gome.gmhx.dao.sysconfig.HxOrganizationDao;
import com.gome.gmhx.entity.HxCurrentStock;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.HxOrganization;
import com.gome.gmhx.entity.HxParcelDelivery;
import com.gome.gmhx.entity.vo.HxMaterialDetailVO;
import com.gome.gmhx.entity.vo.HxParcelDelivery_MaterialDetailVO;
import com.gome.gmhx.entity.vo.HxParcelDelivery_MaterialDetailVO2;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.jbpm.JbpmTransactions;
import com.gome.gmhx.jbpm.JbpmWorkEntityStatus;
import com.gome.gmhx.service.materialmanage.HxParcelDeliveryService;

@Service("HxParcelDeliveryService")
public class HxParcelDeliveryServiceImpl implements HxParcelDeliveryService {

	@Resource
	private HxParcelDeliveryDao parcelDeliveryDao;
	@Resource
	private HxMaterialDao hxMaterialDao;
	@Resource
	private HxOrganizationDao hxOrganizationDao;
	@Resource
	private HxCurrentStockDao hxCurrentStockDao;
	@Resource
	JbpmService jbpmService;

	@Override
	public List<Map<String, Object>> getParcelDeliveryPageList(Page page) {
		List<String> list = this.jbpmService.findMyTaskWorkEntityIdByActivityName(JbpmWorkEntityStatus.status_post_sending);
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
		List<Map<String, Object>> tmp = parcelDeliveryDao.getParcelDeliveryPageList(page);
		return tmp;
	}

	@Override
	public HxParcelDelivery getHxParcelDeliveryById(String parcelCode) {
		return parcelDeliveryDao.getHxParcelDeliveryById(parcelCode);
	}

	@Override
	public List<Map<String, Object>> getParcelDeliveryExport(
			HxParcelDelivery parcel) {
		return parcelDeliveryDao.getParcelDeliveryExport(parcel);
	}

	@Override
	public HxOrganization getOrgnazationBySuit(String receiveUnit) {
		return hxOrganizationDao.getOrganizationById(receiveUnit);
	}

	@Override
	public void updateParcelDelivery(HxParcelDelivery parcel, List<String> listNumbers) {
		this.parcelDeliveryDao.deleteMaterialsByCode(parcel.getParcelCode());//批量删
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parcelCode", parcel.getParcelCode());
		for (String string : new HashSet<String>(listNumbers)) {
			map.put("listNumber", string);
			parcelDeliveryDao.addMaterials(map);//批量加
		}
		parcelDeliveryDao.updateParcelDelivery(parcel);
	}

	@Override
	public HxParcelDelivery getsendParcelDeliveryShow(String parcelCode) {
		return parcelDeliveryDao.getsendParcelDeliveryShow(parcelCode);
	}

	@Override
	public String getParcelCode(String number) {
		return parcelDeliveryDao.getParcelCode(number);
	}

	@Override
	public void addHxParcelDelivery(Map<String, Object> map, List<String> listNumbers) {

		parcelDeliveryDao.addHxParcelDelivery(map);
		for (String string : new HashSet<String>(listNumbers)) {
			map.put("listNumber", string);
			parcelDeliveryDao.addMaterials(map);
		}
	}

	@Override
	public HxParcelDelivery getsendById(String parcelCode) {
		return parcelDeliveryDao.getsendById(parcelCode);
	}

	@Override
	public List<Map<String, Object>> getApplayCodeList(Page page) {
		List<String> list = this.jbpmService.findMyTaskWorkEntityIdByActivityName(JbpmWorkEntityStatus.status_post_sending);
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

		return parcelDeliveryDao.getMaterialByReceiveUnitPageList(page);
	}

	@Override
	public List<Map<String, Object>> getParcelDeliveryDetail(String parcelCode) {
		return parcelDeliveryDao.getParcelDeliveryDetail(parcelCode);
	}

	@Override
	public String getListNumber(String companyId) {
		return parcelDeliveryDao.getListNumber(companyId);

	}

	@Override
	public void delParcelDelivery(String parcelCode) {
		parcelDeliveryDao.deleteMaterialsByCode(parcelCode);
		parcelDeliveryDao.delParcelDelivery(parcelCode);
	}

	@Override
	public void updateSendDelivery(String parcelCode) {
		List<String> listNumbers = new ArrayList<String>();
		HxParcelDelivery parcel = this.parcelDeliveryDao.selectByPrimaryKey(parcelCode);
		for (Map<String, Object> map : this.parcelDeliveryDao.selectMaterialsByCode(parcelCode)) {
			listNumbers.add((String)map.get("list_number"));
			HxMaterialManage hmm = hxMaterialDao.getHxMaterialManage((String)map.get("list_number"));
			jbpmService.completeTask(hmm, JbpmTransactions.transactionOperationComplete, "邮包已发货");
		}
		for (String string : listNumbers) {
			//更新申请单状态
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", JbpmWorkEntityStatus.status_post_receipt);
			map.put("listNumber", string);
			this.hxMaterialDao.updateStatus(map);
			HxMaterialManage hmm = this.hxMaterialDao.getHxMaterialManage(string);
			updateCountWayStock(string, hmm.getType(), true);
		}
		parcel.setState("02");
		parcel.setUpdateDate(new Date());
		parcel.setSendDate(new Date());
		parcel.setOutDate(new Date());
		parcelDeliveryDao.updateParcelDelivery(parcel);
	}
	
	/**
	 * 	更新收货单位在途数量
	 * @param listNumber
	 * @param type
	 * @param flag true 增加, false 减少
	 */
	private void updateCountWayStock(String listNumber, String	type, boolean flag){
		Map<String, Object> stockMap = new HashMap<String, Object>();
		stockMap.put("listNumber", listNumber);
		Integer isNew = (type.indexOf("-o-") > 0) ? 0: 1;
		if(type.indexOf("-o-") > 0){
			stockMap.put("isNew", 0);
		}else{
			stockMap.put("isNew", 1);
		}
		List<Map<String, Object >> list = this.hxMaterialDao.getHxMaterialDetail2(stockMap);
		for (Map<String, Object> map : list) {
			String fittingCode = (String)map.get("fitting_code");
			Integer apply = (Integer)map.get("apply_count");			
			HxCurrentStock fbStock = new HxCurrentStock();
			String receiveOrg = (String)map.get("receive_company");
			fbStock.setOrgId(receiveOrg);
			fbStock.setFittingCode(fittingCode);
			fbStock.setIsNew(isNew);
			HxCurrentStock tmpCurrentStock = this.hxCurrentStockDao.getCurrentStock(fbStock);
			if(tmpCurrentStock != null){
				fbStock.setCountWay(tmpCurrentStock.getCountWay() + apply);
				this.hxCurrentStockDao.updateHxCurrentStock(fbStock);
			}else{
				fbStock.setCountWay(apply);
				fbStock.setStock(0);
				if(type.equals("fb-n-db") || type.startsWith("fb-n-sq")){//总部新料调拨，分部新料申请
					fbStock.setType(Constrants.FB);
				}else if(type.equals("wd-n-sq")){
					fbStock.setType(Constrants.WD);
				}else{
					throw new RuntimeException("配件当前库存的的类型有误！");
				}
				this.hxCurrentStockDao.insertCurrentStock(fbStock);
			}
		}
	}

	@Override
	public HxParcelDelivery selectByPrimaryKey(String parcelCode) {
		return this.parcelDeliveryDao.selectByPrimaryKey(parcelCode);
	}

	@Override
	public List<HxParcelDelivery_MaterialDetailVO> selectPDMDByParcelCode(
			String parcelCode) {
		return parcelDeliveryDao.selectPDMDByParcelCode(parcelCode);
	}

	@Override
	public List<HxMaterialDetailVO> getMaterialDetail(String parcelCode) {
		return parcelDeliveryDao.getMaterialDetail(parcelCode);
	}

	@Override
	public HxParcelDelivery_MaterialDetailVO2 getHxParcelDeliveryById2(String parcelCode) {
		return parcelDeliveryDao.getHxParcelDeliveryById2(parcelCode);
	}

}
