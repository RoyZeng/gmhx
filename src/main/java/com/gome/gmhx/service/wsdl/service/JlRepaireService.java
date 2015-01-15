package com.gome.gmhx.service.wsdl.service;

import com.gome.gmhx.entity.JlRepair;
import com.gome.gmhx.entity.JlRepairMeasures;
import com.gome.gmhx.entity.JlRepairParts;

public interface JlRepaireService {

	public void insertRepair(JlRepair repair);

	public void insertRepairPart(JlRepairParts repairPart);

	public void insertRepairMeasure(JlRepairMeasures repairMeasure);
}
