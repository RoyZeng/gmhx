package com.gome.gmhx.dao.wsdl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.gome.gmhx.entity.JlRepair;
import com.gome.gmhx.entity.JlRepairMeasures;
import com.gome.gmhx.entity.JlRepairParts;

@Repository("jlRepaireDao")
public interface JlRepaireDao {

	Object selectRepairByPrimaryKey(BigDecimal wxd01);

	void insertRepair(JlRepair repair);

	void updateRepairByPrimaryKey(JlRepair repair);

	Object selectRepairPartByPrimaryKey(BigDecimal wxd01);

	void insertRepairPart(JlRepairParts repairPart);

	void updateRepairPartByPrimaryKey(JlRepairParts repairPart);

	Object selectRepairMeasureByPrimaryKey(BigDecimal wxd01);

	void insertRepairMeasure(JlRepairMeasures repairMeasure);

	void updateRepairMeasureByPrimaryKey(JlRepairMeasures repairMeasure);

}
