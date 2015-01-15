package com.gome.gmhx.service.wsdl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.gmhx.dao.wsdl.JlRepaireDao;
import com.gome.gmhx.entity.JlRepair;
import com.gome.gmhx.entity.JlRepairMeasures;
import com.gome.gmhx.entity.JlRepairParts;
import com.gome.gmhx.service.wsdl.service.JlRepaireService;

@Service("jlRepaireService")
public class JlRepaireServiceImpl implements JlRepaireService {

	@Resource
	private JlRepaireDao jlRepaireDao; 
	
	@Override
	public void insertRepair(JlRepair repair) {
		if(null==jlRepaireDao.selectRepairByPrimaryKey(repair.getWxd01())){
			jlRepaireDao.insertRepair(repair);
		}else{
			jlRepaireDao.updateRepairByPrimaryKey(repair);
		}
	}

	@Override
	public void insertRepairPart(JlRepairParts repairPart) {
		if(null==jlRepaireDao.selectRepairPartByPrimaryKey(repairPart.getWxd01())){
			jlRepaireDao.insertRepairPart(repairPart);
		}else{
			jlRepaireDao.updateRepairPartByPrimaryKey(repairPart);
		}
	}

	@Override
	public void insertRepairMeasure(JlRepairMeasures repairMeasure) {
		if(null==jlRepaireDao.selectRepairMeasureByPrimaryKey(repairMeasure.getWxd01())){
			jlRepaireDao.insertRepairMeasure(repairMeasure);
		}else{
			jlRepaireDao.updateRepairMeasureByPrimaryKey(repairMeasure);
		}
	}

}
