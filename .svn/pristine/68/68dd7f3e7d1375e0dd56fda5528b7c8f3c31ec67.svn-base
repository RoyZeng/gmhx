package com.gome.gmhx.controller.sysconfig;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.dao.DuplicateKeyException;
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
import com.gome.gmhx.entity.HxOrganization;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;

@Controller
@RequestMapping(value="/hxOrganization")
public class HxOrganizationController {
	@Resource
	private HxOrganizationService hxOrganizationService;
	
	@RequestMapping(value="/organizationView")
	public String organizationView(){
		return "sysconfig/hxOrganization/organizationList";
	}
	
	@RequestMapping(value="/organizationTree")
	public String organizationTree(){
		return "sysconfig/hxOrganization/organizationTree";
	}
	
	@RequestMapping(value="/getOrganizationPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getOrganizationList(HttpServletResponse response, Page page, HxOrganization organization) throws Exception{
		page.setParam(organization);
		List<Map<String, Object>> list = hxOrganizationService.getOrganizationPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView/{pageMarkup}")
	public ModelAndView addView(@PathVariable(value = "pageMarkup") String pageMarkup){
		ModelAndView mav = new ModelAndView("sysconfig/hxOrganization/organizationAdd");
		HxOrganization organization =new HxOrganization();
		organization.setPageMarkup(pageMarkup);
		mav.addObject("organization", organization);
		return mav;
	}
	
	@RequestMapping(value="addOrganization")
	@ResponseBody
	public String addOrganization(HxOrganization organization) {
        try {
        	organization.setOrgCreateDate(new Date());//设置创建日期
        	organization.setOrgActive(true);//机构状态标记（可用）
        	organization.setOrgSource("0");//系统内部
        	hxOrganizationService.addOrganization(organization);
            return "{\"flag\" : \"success\"}";
        } catch(DuplicateKeyException e1){
        	return "{\"flag\" : \"duplicatekey\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	@RequestMapping(value="/updateView/{orgCode}/{pageMarkup}")
	public ModelAndView updateView(@PathVariable(value = "orgCode") String orgCode,@PathVariable(value = "pageMarkup") String pageMarkup){
		ModelAndView mav = new ModelAndView("sysconfig/hxOrganization/organizationUpdate");
		HxOrganization organization = hxOrganizationService.getOrganizationById(orgCode);
		organization.setPageMarkup(pageMarkup);
		mav.addObject("organization", organization);
		return mav;
	}
	
	@RequestMapping(value="/updateOrganization")
	@ResponseBody
	public String updateOrganization(HxOrganization organization){
		try {
			organization.setOrgUpdateDate(new Date());//设置修改日期
			String fromType = organization.getFromType();
			String orgCode = organization.getOrgCode();
			if("hx001".equals(fromType)){
				hxOrganizationService.updateOrganization(organization);
			}else{
				hxOrganizationService.delOrganizationById(orgCode);
				organization.setOrgSource("1");//外围系统
				hxOrganizationService.addOrganization(organization);
			}
            return "{\"flag\" : \"success\"}";
        } catch(DuplicateKeyException e1){
        	return "{\"flag\" : \"duplicatekey\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/showView/{orgCode}/{pageMarkup}")
	public ModelAndView showView(@PathVariable(value = "orgCode") String orgCode,@PathVariable(value = "pageMarkup") String pageMarkup,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxOrganization/organizationShow");
		HxOrganization organization = hxOrganizationService.getOrganizationById(orgCode);
		organization.setPageMarkup(pageMarkup);
		mav.addObject("organization", organization);
		return mav;
	}
	
	@RequestMapping(value="/logout/{orgCode}/{pageMarkup}")
	public ModelAndView logout(@PathVariable(value = "orgCode") String orgCode,@PathVariable(value = "pageMarkup") String pageMarkup){
		ModelAndView mav = new ModelAndView("sysconfig/hxOrganization/organizationShow");
		HxOrganization organization = hxOrganizationService.getOrganizationById(orgCode);
		organization.setOrgActive(false);//注销
		organization.setOrgActiveName("停用");
		hxOrganizationService.updateOrganization(organization);
		organization.setPageMarkup(pageMarkup);
		mav.addObject("organization", organization);
		return mav;
	}
	
	@RequestMapping(value="/logon/{orgCode}/{pageMarkup}")
	public ModelAndView logon(@PathVariable(value = "orgCode") String orgCode,@PathVariable(value = "pageMarkup") String pageMarkup){
		ModelAndView mav = new ModelAndView("sysconfig/hxOrganization/organizationShow");
		HxOrganization organization = hxOrganizationService.getOrganizationById(orgCode);
		organization.setOrgActive(true);//启用
		organization.setOrgActiveName("正常使用");
		hxOrganizationService.updateOrganization(organization);
		organization.setPageMarkup(pageMarkup);
		mav.addObject("organization", organization);
		return mav;
	}
	
	@RequestMapping(value="/moveView/{orgCode}/{pageMarkup}")
	public ModelAndView moveView(@PathVariable(value = "orgCode") String orgCode,@PathVariable(value = "pageMarkup") String pageMarkup){
		ModelAndView mav = new ModelAndView("sysconfig/hxOrganization/organizationMove");
		HxOrganization organization = hxOrganizationService.getOrganizationById(orgCode);
		organization.setPageMarkup(pageMarkup);
		mav.addObject("organization", organization);
		return mav;
	}
	
	@RequestMapping(value="/move")
	@ResponseBody
	public String move(HxOrganization organization){
		try {
			HxOrganization organizationTemp = hxOrganizationService.getOrganizationById(organization.getOrgCode());
			organizationTemp.setOrgParentId(organization.getOrgParentId());;//设置修改日期
			organizationTemp.setOldOrgCode(organization.getOrgCode());
			hxOrganizationService.updateOrganization(organizationTemp);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/combineView/{orgCode}/{pageMarkup}")
	public ModelAndView combineView(@PathVariable(value = "orgCode") String orgCode,@PathVariable(value = "pageMarkup") String pageMarkup){
		ModelAndView mav = new ModelAndView("sysconfig/hxOrganization/organizationCombine");
		HxOrganization organization = hxOrganizationService.getOrganizationById(orgCode);
		organization.setPageMarkup(pageMarkup);
		mav.addObject("organization", organization);
		return mav;
	}
	
	@RequestMapping(value="/combine")
	@ResponseBody
	public String combine(HxOrganization organization){
		try {
			HxOrganization organizationTemp = hxOrganizationService.getOrganizationById(organization.getOrgCode());
			organizationTemp.setOrgParentId(organization.getOrgParentId());;//设置修改日期
			hxOrganizationService.updateOrganization(organizationTemp);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HxOrganization organization, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<HxOrganization>().decodeURI(organization);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = hxOrganizationService.getOrganizationExport(organization);
		ViewExcel viewExcel = new ViewExcel("组织机构导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
	@RequestMapping(value="/getOrganizationTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getOrganizationTree(){
		List<HxOrganization> organizationList = hxOrganizationService.getOrganizationTree();
        JSONArray array = new JSONArray();
        for (HxOrganization organization : organizationList) {
            JSONObject object = new JSONObject();
            object.put("id", organization.getOrgCode());
            object.put("pId", organization.getOrgParentId());
            object.put("name", organization.getOrgName());
            object.put("fromType", organization.getFromType());
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/fittingStockManageView")
	public ModelAndView userPositionView(String orgCode){
		ModelAndView mav = new ModelAndView("sysconfig/hxOrganization/fittingStockManageTree");
		HxOrganization organization = hxOrganizationService.getOrganizationById(orgCode);
		mav.addObject("organization", organization);
		return mav;
	}
	
	@RequestMapping(value="/getFittingStockTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingStockTree(){
		List<HxOrganization> organizationList = hxOrganizationService.getFittingStockTree();
        JSONArray array = new JSONArray();
        for (HxOrganization hxOrganization : organizationList) {
            JSONObject object = new JSONObject();
            object.put("id", hxOrganization.getOrgCode());
            object.put("name", hxOrganization.getOrgName());
            object.put("pId", hxOrganization.getOrgParentId());
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getFittingStockTreeData", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingStockTreeData(String orgCode){
		HxOrganization hxOrganization = new HxOrganization();
		hxOrganization.setOrgCode(orgCode);
		List<HxOrganization> organizationList = hxOrganizationService.getFittingStockTreeData(hxOrganization);
		JSONArray array = new JSONArray();
	    for (HxOrganization hxOrganizationTemp : organizationList) {
	         JSONObject object = new JSONObject();
	         object.put("id", hxOrganizationTemp.getPartId());
	         array.add(object);
	     }
		return array.toString();
	}
	
	@RequestMapping(value="addFittingStockPart")
	@ResponseBody
	public String addFittingStockPart(HxOrganization hxOrganization) {
        try {
        	String orgCodeTemp = hxOrganization.getOrgCode();
        	String partIdTemp = hxOrganization.getPartId();
        	if (StringUtils.isNotEmpty(orgCodeTemp)) {
        		hxOrganizationService.deleteCompleteByOrgId(hxOrganization);
        	}
        	if (StringUtils.isNotEmpty(partIdTemp)) {
    			String[] partIdArr = partIdTemp.split(",");
    			for(int i = 0; i < partIdArr.length; i++){
    				HxOrganization hxOrganizationTemp = new HxOrganization();
    				if(StringUtils.isNotEmpty(partIdArr[i])){
    					hxOrganizationTemp.setPartId(partIdArr[i]);
    					hxOrganizationTemp.setOrgCode(orgCodeTemp);
        				hxOrganizationService.addFittingStockPart(hxOrganizationTemp);
    				}
    			}
        	}
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	@RequestMapping(value = "/importView")
	public ModelAndView importView() {
		ModelAndView mav = new ModelAndView("sysconfig/hxOrganization/organizationImport");
		return mav;
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
		List<HxOrganization> organizations = new ArrayList<HxOrganization>();
		try {
			InputStream input = file.getInputStream();
			HSSFWorkbook workBook = new HSSFWorkbook(input);
			HSSFSheet sheet = workBook.getSheetAt(0);
			DecimalFormat df = new DecimalFormat("0"); 
			HSSFRow  row0 = sheet.getRow(0);//第一列
	        int colNum = row0.getPhysicalNumberOfCells();//标题总列数
			if (sheet != null) {
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					HSSFRow row = sheet.getRow(i);
					HxOrganization hxOrganization = new HxOrganization();
					for (int j = 0; j < colNum; j++) {
						HSSFCell cell = row.getCell(j);
						String cellStr = "";
						if(cell != null){
							cellStr = cell.toString();
						}
						if(j==0){
							hxOrganization.setOrgName(cellStr);
						}else if(j==1){
							hxOrganization.setOrgLevel(cellStr);
						}else if(j==2){
							hxOrganization.setOrgCode(cellStr);
						}else if(j==3){
							hxOrganization.setOrgParentId(cellStr);
						}else if(j==4){
							cellStr = df.format(cell.getNumericCellValue());
							hxOrganization.setOrgType(cellStr);
						}else if(j==5){
							hxOrganization.setOrgCity(cellStr);
						}else if(j==6){
							hxOrganization.setOrgReference(cellStr);
						}else if(j==7){
							hxOrganization.setOrgAddress(cellStr);
						}else if(j==8){
							cellStr = df.format(cell.getNumericCellValue());
							hxOrganization.setOrgPostCode(cellStr);
						}else if(j==9){
							hxOrganization.setOrgTelephone(cellStr);
						}else if(j==10){
							hxOrganization.setOrgManager(cellStr);
						}else if(j==11){
							cellStr = df.format(cell.getNumericCellValue());
							hxOrganization.setOrgManagerPhone(cellStr);
						}else if(j==12){
							hxOrganization.setOrgFax(cellStr);
						}else if(j==13){
							cellStr = df.format(cell.getNumericCellValue());
							hxOrganization.setOrgApproval(cellStr);
						}else if(j==14){
							cellStr = df.format(cell.getNumericCellValue());
							hxOrganization.setIsFittingStock(cellStr);
						}else if(j==15){
							cellStr = df.format(cell.getNumericCellValue());
							hxOrganization.setFittingPid(cellStr);
						}else if(j==16){
							hxOrganization.setOrgNote(cellStr);
						}
					}
					organizations.add(hxOrganization);
				}
			}
			Map<String, String> MapResult = hxOrganizationService.insertOrganizations(organizations);
			result = JsonUtil.toJson(MapResult);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
		return result;
	}

	@RequestMapping(value = "/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = "HxOrganizationTemplate.xls";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "WEB-INF"+System.getProperty("file.separator") +"jsp"+System.getProperty("file.separator") +"sysconfig"+System.getProperty("file.separator") +"hxOrganization"+System.getProperty("file.separator");
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