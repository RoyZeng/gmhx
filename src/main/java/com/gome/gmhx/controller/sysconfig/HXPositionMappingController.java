package com.gome.gmhx.controller.sysconfig;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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

import com.gome.common.page.Page;
import com.gome.common.util.JsonUtil;
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.entity.HXPositionMapping;
import com.gome.gmhx.entity.HxPosition;
import com.gome.gmhx.service.sysconfig.HxPositionMappingService;
import com.gome.gmhx.service.sysconfig.HxPositionService;

@Controller
@RequestMapping(value="/hxPositionMapping")
public class HXPositionMappingController {

	@Resource
	private HxPositionMappingService hxPositionMappingService;
	
	@Resource
	private HxPositionService hxPositionService;
	
	@RequestMapping(value="/positionMappingView")
	public String positionMappingView(){
		return "sysconfig/positionmapping/positionMapList";
	}

	@RequestMapping(value="/getPositionMappingPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionMappingList(Page page, HXPositionMapping positionMapping) throws Exception{
		page.setParam(positionMapping);
		List<Map<String, Object>> list = hxPositionMappingService.getPositionMappingPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "sysconfig/positionmapping/positionMappingAdd";
	}
	
	@RequestMapping(value="/addPositionMapping")
	@ResponseBody
	public String addPositionMapping(HXPositionMapping positionMapping){
		try {
        	String id = UUIDGenerator.getUUID();
        	positionMapping.setId(id);//设置主键
        	hxPositionMappingService.addPositionMapping(positionMapping);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/updateView")
	public ModelAndView updateView(HXPositionMapping positionMapping) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("sysconfig/positionmapping/positionMappingUpdate");
		// 解决页面传参中文乱码
		positionMapping.setJbpmRoleCode(URLDecoder.decode(positionMapping.getJbpmRoleCode(),"utf-8"));
		positionMapping.setJbpmRoleName(URLDecoder.decode(positionMapping.getJbpmRoleName(),"utf-8"));
		mav.addObject("positionMapping", positionMapping);
		return mav;
	}
	
	@RequestMapping(value="/updatePositionMapping")
	@ResponseBody
	public String updatePositionMapping(HXPositionMapping positionMapping){
		try {
        	hxPositionMappingService.updatePositionMapping(positionMapping);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/connectionView")
	public ModelAndView connectionView(HXPositionMapping positionMapping) throws UnsupportedEncodingException{
	    // 解决页面传参中文乱码
		positionMapping.setJbpmRoleCode(URLDecoder.decode(positionMapping.getJbpmRoleCode(),"utf-8"));
		positionMapping.setJbpmRoleName(URLDecoder.decode(positionMapping.getJbpmRoleName(),"utf-8"));
		ModelAndView mav = new ModelAndView("sysconfig/positionmapping/connectionView");
		mav.addObject("positionMapping", positionMapping);
		return mav;
	}
	
	@RequestMapping(value="/getPositionListBYCodeName", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionListBYCodeName(Page page, HXPositionMapping positionMapping,HxPosition position) throws Exception{
		Map<String, Object> mapParam=new HashMap<String, Object>();
		mapParam.put("jbpmRoleCode", positionMapping.getJbpmRoleCode());
		mapParam.put("jbpmRoleName", positionMapping.getJbpmRoleName());
		mapParam.put("positionName", position.getPositionName());
		mapParam.put("positionCode", position.getPositionCode());
		page.setParam(mapParam);
		List<Map<String, Object>> list = hxPositionService.getPositionPageListBYCodeName(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/getOtherPositionListBYCodeName", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getOtherPositionListBYCodeName(Page page, HXPositionMapping positionMapping,HxPosition position) throws Exception{
		Map<String, Object> mapParam=new HashMap<String, Object>();
		mapParam.put("jbpmRoleCode", positionMapping.getJbpmRoleCode());
		mapParam.put("jbpmRoleName", positionMapping.getJbpmRoleName());
		mapParam.put("positionName", position.getPositionName());
		mapParam.put("positionCode", position.getPositionCode());
		page.setParam(mapParam);
		List<Map<String, Object>> list = hxPositionService.getOtherPositionPageListBYCodeName(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/connect", produces="text/plain;charset=UTF-8")
    public @ResponseBody String connect(HttpServletRequest request,HXPositionMapping positionMapping) throws Exception{
        String result = null;
        try {
            String positionIds = request.getParameter("positionIds");
            String type=request.getParameter("type"); // 关联/解除关联类型
            String positionIdsArray[]=positionIds.split(",");
            hxPositionMappingService.connect(positionMapping,positionIdsArray,type);
            result = "关联岗位成功";
        } catch (Exception e) {
            e.printStackTrace();
            result = "服务器异常";
        }
        return result;
    }
	
	@RequestMapping(value="/delete", produces="text/plain;charset=UTF-8")
    public @ResponseBody String delete(HttpServletRequest request) throws Exception{
        String result = null;
        try {
            String codes = request.getParameter("codes");
            String codesArray[]=codes.split(",");
            hxPositionMappingService.delete(codesArray);
            result = "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            result = "服务器异常";
        }
        return result;
    }
	
	@RequestMapping(value = "/importView")
	public String importView() {
		return "positionmapping/hxPositionImport";
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
		List<HxPosition> positions = new ArrayList<HxPosition>();
		try {
			InputStream input = file.getInputStream();
			HSSFWorkbook workBook = new HSSFWorkbook(input);
			HSSFSheet sheet = workBook.getSheetAt(0);
			if (sheet != null) {
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					HSSFRow row = sheet.getRow(i);
					HxPosition position = new HxPosition();
					boolean flag=true;
					for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
						HSSFCell cell = row.getCell(j);
						String cellStr = cell.toString();
						// 该行第一个字段“岗位名称”有值才确认开始录入改行
						if(j==0 &&(cellStr==null || "".equals(cellStr.trim()))){
							flag=false; 
						}else {
							if(j==0){
								position.setPositionName(cellStr);
							}else if(j==1){
								//岗位类型(0普通岗 1总部物料岗 2分部物料岗 3网点物料岗)
								if("普通岗".equals(cellStr))
									position.setPositionType("0");
								else if("总部物料岗".equals(cellStr))
									position.setPositionType("1");
								else if("分部物料岗".equals(cellStr))
									position.setPositionType("2");
								else if("网点物料岗".equals(cellStr))
									position.setPositionType("3");
							}else if(j==2){
								position.setOrgId(cellStr);
							}else if(j==3){
								position.setPositionCode(cellStr);
							}else if(j==4){
								position.setPositionOrigin(cellStr);
							}
						}
					}
					if(flag){
						position.setModifyDate(new Date());
						positions.add(position);
					}
				}
			}
			Map<String, String> MapResult = hxPositionService.insertPositions(positions);
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
		String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "WEB-INF\\jsp\\positionmapping\\template\\";
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
