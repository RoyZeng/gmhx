package com.gome.gmhx.controller.jasperreport;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.gome.gmhx.entity.vo.HxMaterialDetailVO;

public class MaterialDetailDataSource implements JRDataSource {

	private int index = -1;

	private List<HxMaterialDetailVO> personList = getAllPerson();

	public Object getFieldValue(JRField field) throws JRException {
		Object value = null;
		String fieldName = field.getName();
		if ("listNumber".equals(fieldName)) {
			value = personList.get(index).getListNumber();
		} else if ("type".equals(fieldName)) {
			value = personList.get(index).getType();
		} else if ("sendNumber".equals(fieldName)) {
			value = personList.get(index).getSendNumber();
		} else if ("fittingCodeDetail".equals(fieldName)) {
			value = personList.get(index).getFittingCodeDetail();
		}else if ("fittingNameDetail".equals(fieldName)) {
			value = personList.get(index).getFittingNameDetail();
		}else if ("spec".equals(fieldName)) {
			value = personList.get(index).getSpec();
		}else if ("auditCount".equals(fieldName)) {
			value = personList.get(index).getAuditCount();
		}
		return value;
	}

	public boolean next() throws JRException {
		index++;
		return index < personList.size();
	}
	
	public List<HxMaterialDetailVO> getAllPerson() {
		List<HxMaterialDetailVO> perList = new ArrayList<HxMaterialDetailVO>();
//		perList.add(new HxMaterialDetailVO("101", "小博", "22", "湖北","aa","adsa",2));
//		perList.add(new HxMaterialDetailVO("102", "张三", "21", "湖南","aa","adsa",5));
		return perList;
	}
}
