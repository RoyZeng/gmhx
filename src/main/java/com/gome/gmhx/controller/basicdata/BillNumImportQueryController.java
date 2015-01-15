package com.gome.gmhx.controller.basicdata;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.bean.ViewExcel;
import com.gome.common.page.Page;
import com.gome.common.util.DateUtils;
import com.gome.common.util.DecoderUtil;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxGoodbill;
import com.gome.gmhx.entity.vo.HxGoodbillVO;
import com.gome.gmhx.service.basicdata.HxGoodbillService;

@Controller
@RequestMapping(value = "/hxBillNumImportQuery")
public class BillNumImportQueryController {

	@Resource
	private HxGoodbillService billService;
	
	@RequestMapping(value = "hxBillNumView")
	public String BarCodeView() {
		return "basicData/hxBillNumImportQuery/hxBillNumList";
	}

	@RequestMapping(value = "/importView")
	public String importView() {
		return "basicData/hxBillNumImportQuery/hxBillNumImport";
	}
	

	@RequestMapping(value="/showView/{id}")
	public ModelAndView showView(@PathVariable(value = "id") String key){
		ModelAndView mav = new ModelAndView("basicData/hxBillNumImportQuery/hxBillNumShow");
		
		HxGoodbill sale = billService.getGoodbillByPrimaryKey(Arrays.asList(key.split(",")));
		mav.addObject("sale", sale);
		return mav;
	}
	

	@RequestMapping(value = "/getHxBillNumPageList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String geHxtBarCodePageList(HttpServletResponse response, Page page, HxGoodbillVO bill) throws Exception {
		page.setParam(bill);
		List<Map<String, Object>> list = billService.getHxGoodbillPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}

	@RequestMapping(value = "/exportExcel")
	public ModelAndView exportExcel(HxGoodbill bill, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception {
		new DecoderUtil<HxGoodbill>().decodeURI(bill);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = billService.getBillNumExport(bill);
		ViewExcel viewExcel = new ViewExcel("提单号导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE),tableField, header, list);
		return new ModelAndView(viewExcel);
	}

	@RequestMapping(value = "/importExcel")
	@ResponseBody
	public String importExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = null;
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = mulRequest.getFile("file");
		String filename = file.getOriginalFilename();
		if (filename == null || "".equals(filename)) {
			return null;
		}
		List<HxGoodbill> bills = new ArrayList<HxGoodbill>();
		try {
			InputStream input = file.getInputStream();
			Workbook workBook = null;
			try {
				workBook = new XSSFWorkbook(input);//Excel 2007
	        } catch (Exception ex) {
	        	workBook = new HSSFWorkbook(input);//兼容Excel 2003
	        }
			Sheet sheet = workBook.getSheetAt(0);
			if (sheet != null) {
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					Row row = sheet.getRow(i);
					HxGoodbill sale = new HxGoodbill();
					for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
						Cell cell = row.getCell(j);
						String cellStr = cell.toString();
						if(j==0){
							sale.setGsxx01(cellStr.trim());
						}else if(j==1){
							sale.setThdh(new Long(cellStr));
						}else if(j==2){
							sale.setKhmc(cellStr.trim());
						}else if(j==3){
							sale.setSpmc(cellStr.trim());
						}else if(j==4){
							sale.setXsje(new BigDecimal(cellStr));
						}else if(j==5){
							sale.setFph(cellStr.trim());
						}else if(j==6){
							switch(cell.getCellType()){
								case Cell.CELL_TYPE_STRING:
									DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
									sale.setJzrq(df.parse(cellStr));
									break;
								case Cell.CELL_TYPE_NUMERIC:
									short format = cell.getCellStyle().getDataFormat();  
									SimpleDateFormat sdf = null;  
									if(format == 14 || format == 31 || format == 57 || format == 58 || format == 22){  
										//日期  
										sdf = new SimpleDateFormat("yyyy-MM-dd");  
									}else if (format == 20 || format == 32) {  
										//时间  
										sdf = new SimpleDateFormat("HH:mm");  
									}  
									double value = cell.getNumericCellValue();  
									Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
									result = sdf.format(date);
									break;
								default: throw new Exception("日期格式不符！");
							}
						}else if(j==7){
							sale.setBmmc(cellStr.trim());
						}else if(j==8){
							sale.setYyymc(cellStr.trim());
						}else if(j==9){
							sale.setZpbj(new Integer(cellStr));
						}
					}
					bills.add(sale);
				}
			}
			Map<String, String> MapResult = billService.insertGoodbills(bills);
			result = JsonUtil.toJson(MapResult);
		} catch (Exception e) {
			e.printStackTrace();
			StringBuffer sb = new StringBuffer("{\"flag\" : \"failure\",");
			sb.append("\"msg\":"+e.getMessage());
			sb.append("}");
		}
		return result;
	}

	@RequestMapping(value = "/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = "Template.xls";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "WEB-INF\\jsp\\basicData\\hxBillNumImportQuery\\";
		String downLoadPath = ctxPath + filename;
		File file = new File(downLoadPath);
		long fileLength = file.length();
		response.setContentType(request.getSession().getServletContext().getMimeType(filename));
		response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));
		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}
}
