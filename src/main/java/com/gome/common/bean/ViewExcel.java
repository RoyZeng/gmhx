package com.gome.common.bean;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

@Component("viewExcel")
public class ViewExcel extends AbstractExcelView {
	private String fileName;	// 文件名
	private String tableField;	// 表字段名称
	private String tableHeader;	// 表头名称
	private List<Map<String, Object>> list;	// 内容

	public ViewExcel() {
	}

	public ViewExcel(String fileName, String tableField, String tableHeader,
			List<Map<String, Object>> list) {
		this.fileName = fileName;
		this.tableField = tableField;
		this.tableHeader = tableHeader;
		this.list = list;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
		response.setContentType("application/vnd.ms-excel; charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"),"ISO8859-1") + ".xls");
		
		List<String> fieldList = Arrays.asList(tableField.split("\\|"));
		List<String> headerList = Arrays.asList(tableHeader.split("\\|"));
		// 产生Excel表头
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow header = sheet.createRow(0); // 第0行
		// 产生标题列
		for(int i=0;i<headerList.size();i++){
			header.createCell(i).setCellValue(headerList.get(i).toString());
		}
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-mm-dd"));

		// 填充数据
		int rowNum = 1;
		for (Iterator<Map<String, Object>> iter = list.iterator(); iter.hasNext();) {
			Map<String, Object> values = (Map<String, Object>) iter.next();
			HSSFRow row = sheet.createRow(rowNum++);
			for(int i = 0 ; i < fieldList.size() ; i++ ){
				String field = String.valueOf(values.get(fieldList.get(i)));
				row.createCell(i).setCellValue(field);
			}
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTableField() {
		return tableField;
	}

	public void setTableField(String tableField) {
		this.tableField = tableField;
	}

	public String getTableHeader() {
		return tableHeader;
	}

	public void setTableHeader(String tableHeader) {
		this.tableHeader = tableHeader;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
}
