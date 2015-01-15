package com.gome.gmhx.controller.basicdata;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.bean.ViewExcel;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.DateUtils;
import com.gome.common.util.DecoderUtil;
import com.gome.common.util.JsonUtil;
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.entity.HxFitting;
import com.gome.gmhx.entity.HxMaintenance;
import com.gome.gmhx.entity.HxProductDetail;
import com.gome.gmhx.jbpm.CurrentSysUser;
import com.gome.gmhx.service.basicdata.HxMaintenanceService;

@Controller
@RequestMapping(value="/maintenance")
public class HxMaintenanceController {
	
	@Resource
	private HxMaintenanceService hxMaintenanceService;
   
	@RequestMapping(value="/maintenanceView")
	public String hxCodeView(){
		return "basicData/hxFualtMaintenance/hxFualtMaintenanceList";
		
	}
	
	@RequestMapping(value = "/importView")
	public String importView() {
		return "basicData/hxFualtMaintenance/hxFualtMaintenanceImport";
	}
	
	@RequestMapping(value="/ListMainView")
	public String ListMainView(){
		return "basicData/hxFualtMaintenance/hxFualtMaintenanceListMain";
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "basicData/hxFualtMaintenance/hxFualtMaintenanceAdd";
	}
	
	@RequestMapping(value="/updateView/{fault_code}")
	public ModelAndView updateView(@PathVariable String fault_code) throws Exception{
		ModelAndView mav = new ModelAndView("basicData/hxFualtMaintenance/hxFualtMaintenanceUpdate");
		HxMaintenance maint = hxMaintenanceService.getHxMaintenanceNoChangeByFaultCode(fault_code);
		mav.addObject("maint",maint);
		return mav;
	}
	
	@RequestMapping(value="/getMaintenancePageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getMaintenancePageList(HttpServletResponse response, Page page, HxMaintenance Maintenance) throws Exception{
		page.setParam(Maintenance);
		List<Map<String, Object>> list = hxMaintenanceService.getMaintenancePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/getHxMaintenanceTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxMaintenanceTree(){
		List<Map<String ,Object>> classifyList = hxMaintenanceService.getHxMaintenanceTree();
        JSONArray array = new JSONArray();
        for (Map<String, Object> map : classifyList) {
            JSONObject object = new JSONObject();
              object.put("id", map.get("classify_code"));
             object.put("pId", map.get("parent_code"));
          
              object.put("category", map.get("category"));
              object.put("categoryId", map.get("categoryId"));
              object.put("chose", map.get("chose"));
              object.put("choseId", map.get("choseId"));
              object.put("validity", map.get("validity"));
              object.put("validityId", map.get("validityId"));
            object.put("arrangeNumber", map.get("arrange_number"));
            object.put("maintenanceCosts", map.get("maintenance_costs"));
            object.put("modifier", map.get("modifier"));
           // object.put("modDate", map.get("modDate"));
            object.put("name", map.get("classify_name"));
            object.put("open", true);
            array.add(object);
        }
		return array.toString();
	}

	@RequestMapping(value="/createHxMaintenance", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String createHxMaintenance(HxMaintenance maint){
		try{
			hxMaintenanceService.createHxMaintenance(maint);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "failure";
		}
	}
	
	// 新的增加
	@RequestMapping(value="/addHxMaintenance")
	@ResponseBody
	public String addHxMaintenance(HxMaintenance maint) throws Exception{
		try {
			hxMaintenanceService.addHxMaintenance(maint);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/viewHxMaintenance/{fault_code}")
	public ModelAndView viewHxMaintenance(@PathVariable String fault_code) throws Exception{
		ModelAndView mav = new ModelAndView("basicData/hxFualtMaintenance/hxFualtMaintenanceView");
		HxMaintenance maint = hxMaintenanceService.getHxMaintenanceByFaultCode(fault_code);
		mav.addObject("maint",maint);
		return mav;
	}
	
	@RequestMapping(value="/updateHxMaintenance", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String updateHxMaintenance(HxMaintenance maint, String classifyCodeOld){
		try{
			maint.setModDate(new Date());//设置创建日期
			Map<String, Object> map = BeanMapUtils.convertBean(maint);
			map.put("classifyCodeOld", classifyCodeOld);
			hxMaintenanceService.updateHxMaintenance(map);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value="/modifyHxMaintenance", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String modifyHxMaintenance(HxMaintenance maint){
		try{
			maint.setModifier(CurrentSysUser.getCurrentSysUser().getUserName());
			maint.setModDate(new Date());//设置修改日期
			hxMaintenanceService.modifyHxMaintenance(maint);
			return "{\"flag\" : \"success\"}";
		}catch(Exception e){
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	
	//导出
	@RequestMapping(value = "/exportExcel")
	public ModelAndView exportExcel(HxMaintenance Maint, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception {
		new DecoderUtil<HxMaintenance>().decodeURI(Maint);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = hxMaintenanceService.getHxMaintenanceExport(Maint);
		ViewExcel viewExcel = new ViewExcel("故障维修单导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE),tableField, header, list);
		return new ModelAndView(viewExcel);
	}

	//导入
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
		List<HxMaintenance> maint = new ArrayList<HxMaintenance>();
		try {
			InputStream input = file.getInputStream();
			HSSFWorkbook workBook = new HSSFWorkbook(input);
			HSSFSheet sheet = workBook.getSheetAt(0);
			if (sheet != null) {
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					HSSFRow row = sheet.getRow(i);
					HxMaintenance Maint1 = new HxMaintenance();
					for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
						HSSFCell cell = row.getCell(j);
						String cellStr = cell.toString();
						if(j==0){
							Maint1.setCategory(cellStr);
							Maint1.setClassifyCode(cellStr);
							Maint1.setClassifyName(cellStr);
						}else if(j==1){
							Maint1.setFaultCode(cellStr);
							Maint1.setFaultName(cellStr);
						}
					}
					  maint.add(Maint1);
				}
			}
			Map<String, String> MapResult = hxMaintenanceService.insertHxMaintenance(maint);
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
		String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "WEB-INF\\jsp\\basicData\\hxFualtMaintenance\\";
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
