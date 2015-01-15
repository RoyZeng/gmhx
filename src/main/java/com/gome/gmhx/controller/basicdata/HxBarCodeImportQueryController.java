package com.gome.gmhx.controller.basicdata;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
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
import com.gome.gmhx.entity.BarCode;
import com.gome.gmhx.service.basicdata.HxBarCodeService;

@Controller
@RequestMapping(value = "/hxBarCodeImportQuery")
public class HxBarCodeImportQueryController {
	@Resource
	private HxBarCodeService hxBarCodeService;

	@RequestMapping(value = "hxBarCodeView")
	public String barCodeView() {
		return "basicData/hxBarCodeImportQuery/hxBarCodeList";
	}
	
	@RequestMapping(value = "hxTempBarCodeView")
	public String tempBarCodeView() {
		return "basicData/hxBarCodeImportQuery/hxTempBarCodeList";
	}

	@RequestMapping(value = "/importView")
	public String importView() {
		return "basicData/hxBarCodeImportQuery/hxBarCodeImport";
	}

	@RequestMapping(value = "/getHxBarCodePageList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String geHxtBarCodePageList(HttpServletResponse response, Page page, BarCode barCode) throws Exception {
		page.setParam(barCode);
		List<Map<String, Object>> list = hxBarCodeService.getHxBarCodePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value = "/getHxTempBarCodePageList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getHxTempBarCodePageList(HttpServletResponse response,HttpServletRequest request, Page page) throws Exception {
		Map param = new HashMap();
		param.put("ksrq", request.getParameter("ksrq"));
		param.put("jsrq", request.getParameter("jsrq"));
		page.setParam(param);
		List<Map<String, Object>> list = hxBarCodeService.getHxTempBarCodePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}

	@RequestMapping(value = "/exportExcel")
	public ModelAndView exportExcel(BarCode barCode, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception {
		new DecoderUtil<BarCode>().decodeURI(barCode);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = hxBarCodeService.getBarCodeExport(barCode);
		ViewExcel viewExcel = new ViewExcel("条码导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE),tableField, header, list);
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
		List<BarCode> barCodes = new ArrayList<BarCode>();
		try {
			InputStream input = file.getInputStream();
			HSSFWorkbook workBook = new HSSFWorkbook(input);
			HSSFSheet sheet = workBook.getSheetAt(0);
			if (sheet != null) {
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					HSSFRow row = sheet.getRow(i);
					BarCode barCode = new BarCode();
					for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
						HSSFCell cell = row.getCell(j);
						String cellStr = cell.toString();
						if(j==0){
							barCode.setBarCode(cellStr);
						}else if(j==1){
							barCode.setMachineType(cellStr);
						}
					}
					barCodes.add(barCode);
				}
			}
			Map<String, String> MapResult = hxBarCodeService.insertBarCodes(barCodes);
			result = JsonUtil.toJson(MapResult);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
		return result;
	}

	@RequestMapping(value = "/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = "Template.xls";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "WEB-INF\\jsp\\basicData\\hxBarCodeImportQuery\\";
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
