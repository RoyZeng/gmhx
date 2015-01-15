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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.HxPosition;
import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.entity.HxUserPosition;
import com.gome.gmhx.entity.vo.HxPositionPermissionVO;
import com.gome.gmhx.service.sysconfig.HxPositionService;
import com.gome.gmhx.service.sysconfig.HxUserService;

@Controller
@RequestMapping(value="/hxPosition")
public class HxPositionController {
	@Resource
	private HxPositionService positionService;
	
	@Resource
	private HxUserService userService;
	
	@RequestMapping(value="/positionView")
	public ModelAndView positionView(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxPosition/positionList");
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value="/getPositionPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionPageList(HttpServletResponse response, Page page, HxPosition position,HttpServletRequest request) throws Exception{
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		position.setPositionOrigin(origin);
		page.setParam(position);
		List<Map<String, Object>> list = positionService.getPositionPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView")
	public ModelAndView addView(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxPosition/positionAdd");
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value="addPosition")
	@ResponseBody
	public String addPosition(HxPosition position) {
        try {
        	String positionCode = position.getPositionCode();
        	//String positionCategoryIdTemp = position.getPositionCategoryId();
        	String roleIdTemp = position.getRoleId();
        	position.setModifyDate(new Date());
        	positionService.addPosition(position);
			/*if (StringUtils.isNotEmpty(positionCategoryIdTemp)) {
				String[] positionCategoryIdTempArr = positionCategoryIdTemp.split(",");
				if(positionCategoryIdTempArr.length>0){
					for(int i = 0; i < positionCategoryIdTempArr.length; i++){
						HxPosition hp = new HxPosition();
						if(StringUtils.isNotEmpty(positionCategoryIdTempArr[i])){
							hp.setPositionCategoryId(positionCategoryIdTempArr[i]);
							hp.setPositionCode(positionCode);
							positionService.addPositionCategory(hp);
						}
					}
				}
			}*/
        	if(StringUtils.isNotBlank(positionCode)){
        		positionService.deletePositionRoleByPositionId(positionCode);
        	}
			if (StringUtils.isNotEmpty(roleIdTemp)) {
				HxPosition hp = new HxPosition();
				hp.setRoleId(roleIdTemp);
				hp.setPositionCode(positionCode);
				positionService.addPositionRole(hp);
			}
            return "{\"flag\" : \"success\"}";
        } catch(DuplicateKeyException e1){
        	return "{\"flag\" : \"duplicatekey\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	@RequestMapping(value="/updateView/{positionCode}")
	public ModelAndView updateView(@PathVariable(value = "positionCode") String positionCode,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxPosition/positionUpdate");
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		String fromType = request.getParameter("fromType");
		HxPosition position = null;
		if("0".equals(fromType)){
			position = positionService.getPositionById(positionCode);//系统自建岗位
		}else if("1".equals(fromType)){
			List<HxPosition> positionList = positionService.getPositionByCode(positionCode);//身份管理平台岗位
			if(positionList!=null){
				if(positionList.size()>0){
					position = positionList.get(0);
				}
			}
		}
		mav.addObject("position", position);
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value="/updatePosition")
	@ResponseBody
	public String updatePosition(HxPosition position){
		try {
			String positionCodeTemp = position.getPositionCode();
			//String positionCategoryIdTemp = position.getPositionCategoryId();
			String roleIdTemp = position.getRoleId();
			position.setModifyDate(new Date());
			String fromType = position.getFromType();
			if(StringUtils.isNotBlank(fromType)){
				if("1".equals(fromType)){
				}else if("0".equals(fromType)){
					positionService.updatePosition(position);
				}
			}
			
			/*if (StringUtils.isNotEmpty(positionCodeTemp)) {
				positionService.deletePositionCategoryByPositionId(positionCodeTemp);
			}
			if (StringUtils.isNotEmpty(positionCategoryIdTemp)) {
				String[] positionCategoryIdTempArr = positionCategoryIdTemp.split(",");
				if(positionCategoryIdTempArr.length>0){
					for(int i = 0; i < positionCategoryIdTempArr.length; i++){
						HxPosition hp = new HxPosition();
						if(StringUtils.isNotEmpty(positionCategoryIdTempArr[i])){
							hp.setPositionCategoryId(positionCategoryIdTempArr[i]);
							hp.setPositionCode(positionCodeTemp);
							positionService.addPositionCategory(hp);
						}
					}
				}
			}*/
			if(StringUtils.isNotBlank(positionCodeTemp)){
        		positionService.deletePositionRoleByPositionId(positionCodeTemp);
        	}
			if (StringUtils.isNotEmpty(roleIdTemp)) {
				HxPosition hp = new HxPosition();
				hp.setRoleId(roleIdTemp);
				hp.setPositionCode(positionCodeTemp);
				positionService.addPositionRole(hp);
			}
            return "{\"flag\" : \"success\"}";
        } catch(DuplicateKeyException e1){
        	return "{\"flag\" : \"duplicatekey\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/showView/{positionCode}")
	public ModelAndView showView(@PathVariable(value = "positionCode") String positionCode,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxPosition/positionShow");
		String fromType = request.getParameter("fromType");
		HxPosition position = null;
		if("0".equals(fromType)){
			position = positionService.getPositionById(positionCode);//系统自建岗位
		}else if("1".equals(fromType)){
			List<HxPosition> positionList = positionService.getPositionByCode(positionCode);//身份管理平台岗位
			if(positionList!=null){
				if(positionList.size()>0){
					position = positionList.get(0);
				}
			}
		}
		mav.addObject("position", position);
		return mav;
	}
	
	@RequestMapping(value="/getPositionUserPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionUserPageList(HttpServletResponse response, Page page, HxUserPosition hxUserPosition,HttpServletRequest request) throws Exception{
		String origin=request.getParameter("origin"); //请求来源  ：  权限组：gome 恒信:  hx
		page.setParam(hxUserPosition);
		List<Map<String, Object>> list =null;
		if("gome".equals(origin))
		    list = userService.getPositionUserPageList_gome(page);
		else
		    list = userService.getPositionUserPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/getPositionCategoryTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionCategoryTree(){
		List<EccGoodsCategory> categoryList = positionService.getPositionCategoryTree();
        JSONArray array = new JSONArray();
        for (EccGoodsCategory category : categoryList) {
            JSONObject object = new JSONObject();
            object.put("id", category.getClass_());
            object.put("pId", category.getZsjfldm());
            object.put("name", category.getKschl()+"["+category.getClass_()+"]");//显示岗位品类名称和代码
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getPositionCategoryTreeData", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionCategoryTreeData(HttpServletRequest request){
		String positionId = request.getParameter("positionId");
		List<EccGoodsCategory> categoryList = positionService.getPositionCategoryTreeData(positionId);
		JSONArray array = new JSONArray();
	    for (EccGoodsCategory category : categoryList) {
	         JSONObject object = new JSONObject();
	         if(category!=null){
	        	 object.put("id", category.getClass_());
		         array.add(object);
	         }
	     }
		return array.toString();
	}
	
	@RequestMapping(value = "/importView")
	public ModelAndView importView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("sysconfig/hxPosition/positionImport");
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value = "/exportExcel")
	public ModelAndView exportExcel(HxPosition hxPosition, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception {
		new DecoderUtil<HxPosition>().decodeURI(hxPosition);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = positionService.getPositionExport(hxPosition);
		ViewExcel viewExcel = new ViewExcel("岗位导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE),tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
	// 岗位管理-恒信,权限 导出
	@RequestMapping(value="/exportPositionRoleJbpmExcel")
	public ModelAndView exportPositionRoleJbpmExcel(HxPosition hxPosition,HttpServletRequest request) throws Exception{
		String tableField="positionName|roleName|orgName|jbpmRoleName";
		String header="岗位名称|关联角色名称|组织机构名称|审批角色名称";
		String positionOrigin=request.getParameter("positionOrigin");
		hxPosition.setPositionOrigin(positionOrigin);
		List<Map<String, Object>> list = positionService.exportPositionRoleJbpmExcel(hxPosition);
		ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
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
		List<HxPosition> positions = new ArrayList<HxPosition>();
		try {
			InputStream input = file.getInputStream();
			HSSFWorkbook workBook = new HSSFWorkbook(input);
			HSSFSheet sheet = workBook.getSheetAt(0);
			HSSFRow  row0 = sheet.getRow(0);//第一列
	        int colNum = row0.getPhysicalNumberOfCells();//标题总列数
			if (sheet != null) {
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					HSSFRow row = sheet.getRow(i);
					HxPosition hxPosition = new HxPosition();
					for (int j = 0; j < colNum; j++) {
						HSSFCell cell = row.getCell(j);
						String cellStr = "";
						if(cell != null){
							cellStr = cell.toString();
						}
						if(j==0){
							hxPosition.setPositionName(cellStr);
						}else if(j==1){
							hxPosition.setPositionType(cellStr);
						}else if(j==2){
							hxPosition.setOrgId(cellStr);
						}else if(j==3){
							hxPosition.setPositionCode(cellStr);
						}else if(j==4){
							hxPosition.setPositionOrigin(cellStr);
						}
					}
					positions.add(hxPosition);
				}
			}
			Map<String, String> MapResult = positionService.insertPositions(positions);
			result = JsonUtil.toJson(MapResult);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
		return result;
	}

	@RequestMapping(value = "/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = "HxPositionTemplate.xls";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "WEB-INF"+System.getProperty("file.separator") +"jsp"+System.getProperty("file.separator") +"sysconfig"+System.getProperty("file.separator") +"hxPosition"+System.getProperty("file.separator");
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
	
	@RequestMapping(value = "/importPositionPermissionView")
	public ModelAndView importPositionPermissionView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("sysconfig/hxPosition/positionPermissionImport");
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value = "/importPositionPermissionExcel")
	@ResponseBody
	public String importPositionPermissionExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = null;
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = mulRequest.getFile("file");
		String filename = file.getOriginalFilename();
		if (filename == null || "".equals(filename)) {
			return null;
		}
		List<HxPositionPermissionVO> positionPermissionVOs = new ArrayList<HxPositionPermissionVO>();
		try {
			InputStream input = file.getInputStream();
			HSSFWorkbook workBook = new HSSFWorkbook(input);
			HSSFSheet sheet = workBook.getSheetAt(0);
			HSSFRow  row0 = sheet.getRow(0);//第一列
	        int colNum = row0.getPhysicalNumberOfCells();//标题总列数
			DecimalFormat df = new DecimalFormat("0");  
			if (sheet != null) {
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					HSSFRow row = sheet.getRow(i);
					HxPositionPermissionVO hxPositionPermissionVO = new HxPositionPermissionVO();
					for (int j = 0; j < colNum; j++) {
						HSSFCell cell = row.getCell(j);
						String cellStr = "";
						if(cell != null){
							cellStr = cell.toString();
						}
						if(j==0){
							//岗位名称
						}else if(j==1){
							//岗位代码
							hxPositionPermissionVO.setPositionCode(cellStr);
						}else if(j==2){
							//岗位品类
							hxPositionPermissionVO.setPositionCategoryId(cellStr);
						}else if(j==3){
							//岗位菜单
							hxPositionPermissionVO.setMenuId(cellStr);
						}else if(j==4){
							//岗位物料菜单
							hxPositionPermissionVO.setFittingAuthId(cellStr);
						}else if(j==5){
							//岗位审批角色
							hxPositionPermissionVO.setJbpmRoleCode(cellStr);
						}else if(j==6){
							//岗位人员
							hxPositionPermissionVO.setUserLoginName(cellStr);
						}else if(j==7){
							//人员类型
							cellStr = df.format(cell.getNumericCellValue());
							hxPositionPermissionVO.setFromType(cellStr);
						}
					}
					positionPermissionVOs.add(hxPositionPermissionVO);
				}
			}
			Map<String, String> MapResult = positionService.insertPositionPermissions(positionPermissionVOs);
			result = JsonUtil.toJson(MapResult);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
		return result;
	}

	@RequestMapping(value = "/downloadPositionPermissionTemplate")
	public void downloadPositionPermissionTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = "HxPositionPermissionTemplate.xls";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "WEB-INF"+System.getProperty("file.separator") +"jsp"+System.getProperty("file.separator") +"sysconfig"+System.getProperty("file.separator") +"hxPosition"+System.getProperty("file.separator");
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