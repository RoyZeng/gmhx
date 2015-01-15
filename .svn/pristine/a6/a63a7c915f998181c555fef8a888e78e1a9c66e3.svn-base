package com.gome.gmhx.jbpm.activityBehaviour;

import java.util.List;
import java.util.Map;

import org.jbpm.api.activity.ActivityBehaviour;
import org.jbpm.api.activity.ActivityExecution;

import com.gome.common.util.SpringUtil;
import com.gome.gmhx.dao.servicemanage.HxServiceLongDistanceDao;
import com.gome.gmhx.dao.servicemanage.HxServicePartsInfoDao;
import com.gome.gmhx.dao.servicemanage.HxServiceRetreatReplacementDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;
import com.gome.gmhx.dao.storeinfo.HxCurrentStockDao;
import com.gome.gmhx.entity.HxCurrentStock;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.jbpm.CurrentSysUser;


public class ModifySuccessMarkBehaviour implements ActivityBehaviour {

	@Override
	public void execute(ActivityExecution execution) throws Exception {
		String key = execution.getProcessInstance().getKey();
		String processDefinationId = execution.getProcessDefinitionId();
		
		if(processDefinationId.startsWith("SP02")){//安装回执、维修回执、远程安装费申请
			HxServiceTicketDao hxServiceTicketDao = (HxServiceTicketDao) SpringUtil.getBean("hxServiceTicketDao");
			hxServiceTicketDao.updateServiceTicketEffective(key);
		}else if(processDefinationId.startsWith("SP04")){//工程机
			HxServiceLongDistanceDao serviceLongDistanceDao = (HxServiceLongDistanceDao) SpringUtil.getBean("hxServiceLongDistanceDao");
			serviceLongDistanceDao.updateDistanceApplyEffective(key);
		}else if(processDefinationId.startsWith("SP05")){
			HxServiceRetreatReplacementDao serviceRetreatReplacementDao = (HxServiceRetreatReplacementDao) SpringUtil.getBean("hxServiceRetreatReplacementDao");
			serviceRetreatReplacementDao.updateRetreatReplacementEffective(key);
		}else if(processDefinationId.startsWith("SP06")){
			HxCurrentStockDao hxCurrentStockDao = (HxCurrentStockDao) SpringUtil.getBean("hxCurrentStockDao");
			HxServiceTicketDao hxServiceTicketDao = (HxServiceTicketDao) SpringUtil.getBean("hxServiceTicketDao");
			HxServicePartsInfoDao hxServicePartsInfoDao = (HxServicePartsInfoDao) SpringUtil.getBean("hxServicePartsInfoDao");
			
			// 更新维修单标志位有效
			hxServiceTicketDao.selectServiceTicketByPrimaryKey(key);
			hxServiceTicketDao.updateServiceTicketEffective(key);
			
			HxCurrentStock newStock = new HxCurrentStock();
			HxCurrentStock oldStock = new HxCurrentStock();
			SysUser usr = CurrentSysUser.getCurrentSysUser();
			newStock.setOrgId(usr.getCompanyId());
			oldStock.setOrgId(usr.getCompanyId());
			
			// 变更该用户的配件库存
			List<Map<String, Object>> list = hxServicePartsInfoDao.selectPartsByPrimaryKey(key);
			for(Map<String,Object> map : list){
				String fittingCode = (String) map.get("partsCode");		// 配件编码
				Integer num = (Integer) map.get("amount");				// 配件数量
				String oldPartsCode = (String) map.get("oldPartsCode");	// 旧配件编码
				
				newStock.setFittingCode(fittingCode);
				oldStock.setFittingCode(oldPartsCode);
				newStock.setIsNew(1);
				// 查询新配件库存,新料减少
				newStock = hxCurrentStockDao.getCurrentStock(newStock);
				newStock.setStock(newStock.getStock() - num);
				
				// 查询旧库存,旧料增加
				oldStock = hxCurrentStockDao.getCurrentStock(oldStock);
				oldStock.setStock(oldStock.getStock() + num);
				
				hxCurrentStockDao.updateHxCurrentStock(newStock);
				hxCurrentStockDao.updateHxCurrentStock(oldStock);
			}
		}
	}
}
