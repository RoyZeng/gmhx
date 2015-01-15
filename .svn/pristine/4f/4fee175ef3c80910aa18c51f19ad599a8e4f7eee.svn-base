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
import com.gome.common.util.MD5EncryptUtils;
import com.gome.gmhx.entity.HxUser;
import com.gome.gmhx.entity.HxUserPosition;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.sysconfig.HxUserService;
import com.gome.gmhx.service.wsdl.service.EmpService;
import com.gome.gmhx.service.wsdl.service.JlAccountService;

@Controller
@RequestMapping(value="/hxUser")
public class HxUserController {
	@Resource
	private HxUserService userService;
	
	@Resource
	private EmpService empService;
	
	@Resource
	private JlAccountService jlAccountService;
	
	@RequestMapping(value="/userView")
	public ModelAndView userView(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxUser/userList");
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value="/getUserPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getUserList(HttpServletResponse response, Page page, HxUser user,HttpServletRequest request) throws Exception{
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		user.setOrigin(origin);
		page.setParam(user);
		List<Map<String, Object>> list = userService.getUserPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView")
	public ModelAndView addView(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxUser/userAdd");
		String pageMarkup = request.getParameter("pageMarkup");
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		mav.addObject("pageMarkup", pageMarkup);
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value="addUser")
	@ResponseBody
	public String addUser(HxUser user) {
        try {
        	user.setUserCreateDate(new Date());//设置创建日期
        	user.setUserLoginPassword(MD5EncryptUtils.MD5Encode("123"));  // 默认密码：123
        	userService.addUser(user);
            return "{\"flag\" : \"success\"}";
        }catch(DuplicateKeyException e1){
        	return "{\"flag\" : \"duplicatekey\"}";
        }catch (Exception e) {
        }
        return null;
    }
	
	@RequestMapping(value="/updateView/{userLoginName}")
	public ModelAndView updateView(@PathVariable(value = "userLoginName") String userLoginName,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxUser/userUpdate");
		String pageMarkup = request.getParameter("pageMarkup");
		HxUser user = userService.getUserById(userLoginName);
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		mav.addObject("pageMarkup", pageMarkup);
		mav.addObject("user", user);
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value="/updateUser")
	@ResponseBody
	public String updateUser(HxUser user){
		try {
			user.setUserUpdateDate(new Date());//设置修改日期
			userService.updateUser(user);
            return "{\"flag\" : \"success\"}";
        } catch(DuplicateKeyException e1){
        	return "{\"flag\" : \"duplicatekey\"}";
        }catch (Exception e) {
        }
        return null;
	}
	
	@RequestMapping(value="/showView/{userLoginName}")
	public ModelAndView showView(@PathVariable(value = "userLoginName") String userLoginName, String pageMarkup, String fromType,String origin){
		ModelAndView mav = null;
		HxUser user = null;
		SysUser sysuser = null;
		if("3".equals(fromType)){
			mav = new ModelAndView("sysconfig/hxUser/userShow");
			user = userService.getShowById(userLoginName);
			mav.addObject("user", user);
		}else if("2".equals(fromType)){
			mav = new ModelAndView("sysconfig/hxUser/userPeripheralShow");
			sysuser = jlAccountService.selectAccountByPrimaryKey(userLoginName);
			mav.addObject("sysuser", sysuser);
		}else if("1".equals(fromType)){
			mav = new ModelAndView("sysconfig/hxUser/userPeripheralShow");
			sysuser = empService.getAccountByApAccount(userLoginName);
			mav.addObject("sysuser", sysuser);
		}
		mav.addObject("fromType", fromType);
		mav.addObject("pageMarkup", pageMarkup);
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value="/modifyPwdView/{userLoginName}")
	public ModelAndView modifyPwdView(@PathVariable(value = "userLoginName") String userLoginName,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxUser/userModifyPwd");
		String pageMarkup = request.getParameter("pageMarkup");
		HxUser user = userService.getUserById(userLoginName);
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		mav.addObject("user", user);
		mav.addObject("pageMarkup", pageMarkup);
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value="/modifyUserPwd")
	@ResponseBody
	public String modifyUserPwd(HxUser user){
		try {
			HxUser userTemp = userService.getUserById(user.getUserLoginName());
			//保存加密后的密码
			userTemp.setUserLoginPassword(MD5EncryptUtils.MD5Encode(user.getUserLoginPassword()));
			userTemp.setUserPasswordChangeDate(new Date());//设置密码修改日期
			userService.updateUserPwd(userTemp);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	//新密码修改版
	@RequestMapping(value="/updateUserPwd")
	@ResponseBody
	public String updateUserPwd(HxUser user){
		try {
			HxUser userTemp = userService.getUserById(user.getUserLoginName());
			//保存加密后的密码
			userTemp.setUserLoginPassword(MD5EncryptUtils.MD5Encode(user.getUserLoginPassword()));
			userTemp.setUserPasswordChangeDate(new Date());//设置密码修改日期
			userService.updateUserPwd(userTemp);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/getRolePageListByUserId", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRolePageListByUserId(HttpServletResponse response, Page page, HxUser user) throws Exception{
		page.setParam(user);
		List<Map<String, Object>> list = userService.getRolePageListByUserId(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/getPositionPageListByUserLoginName", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionPageListByUserLoginName(HttpServletResponse response, Page page, HxUserPosition userPosition,HttpServletRequest request) throws Exception{
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		page.setParam(userPosition);
		List<Map<String, Object>> list=null;
		if("gome".equals(origin))
		    list = userService.getPositionPageListByUserLoginName_gome(page);
		else
			list = userService.getPositionPageListByUserLoginName(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value = "/importView")
	public ModelAndView importView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("sysconfig/hxUser/userImport");
		String origin = request.getParameter("origin");//请求来源  ：  权限组：gome 恒信:  hx
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value = "/exportExcel")
	public ModelAndView exportExcel(HxUser hxUser, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception {
		new DecoderUtil<HxUser>().decodeURI(hxUser);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = userService.getUserExport(hxUser);
		ViewExcel viewExcel = new ViewExcel("用户导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE),tableField, header, list);
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
		List<HxUser> users = new ArrayList<HxUser>();
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
					HxUser hxUser = new HxUser();
					for (int j = 0; j < colNum; j++) {
						HSSFCell cell = row.getCell(j);
						String cellStr = "";
						if(cell != null){
							cellStr = cell.toString();
						}
						// 设置初始密码123
						hxUser.setUserLoginPassword(MD5EncryptUtils.MD5Encode("123"));
						if(j==0){
							hxUser.setUserLoginName(cellStr);
						}else if(j==1){
							hxUser.setUserName(cellStr);
						}else if(j==2){
							hxUser.setUserOrgId(cellStr);
						}else if(j==3){
							hxUser.setUserPhone(cellStr);
						}else if(j==4){
							cellStr = df.format(cell.getNumericCellValue()); 
							hxUser.setUserMobile(cellStr);
						}else if(j==5){
							hxUser.setUserEmail(cellStr);
						}else if(j==6){
							cellStr = df.format(cell.getNumericCellValue()); 
							hxUser.setUserWorkNo(cellStr);
						}else if(j==7){
							cellStr = df.format(cell.getNumericCellValue()); 
							hxUser.setUserSex(cellStr);
						}else if(j==8){
							hxUser.setUserBirthday(new Date());
						}else if(j==9){
							hxUser.setUserWorked(cellStr);
						}else if(j==10){
							hxUser.setUserEducation(cellStr);
						}else if(j==11){
							hxUser.setUserOrigin(cellStr);
						}else if(j==12){
							hxUser.setUserTitle(cellStr);
						}else if(j==13){
							hxUser.setUserWorkLimit(cellStr);
						}else if(j==14){
							cellStr = df.format(cell.getNumericCellValue()); 
							hxUser.setUserIndentNo(cellStr);
						}else if(j==15){
							hxUser.setUserInDate(new Date());
						}else if(j==16){
							hxUser.setUserArea(cellStr);
						}else if(j==17){
							hxUser.setUserAddress(cellStr);
						}else if(j==18){
							hxUser.setUserPostCode(cellStr);
						}else if(j==19){
							hxUser.setUserNote(cellStr);
						}
					}
					users.add(hxUser);
				}
			}
			Map<String, String> MapResult = userService.insertUsers(users);
			result = JsonUtil.toJson(MapResult);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
		return result;
	}

	@RequestMapping(value = "/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = "HxUserTemplate.xls";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "WEB-INF"+System.getProperty("file.separator") +"jsp"+System.getProperty("file.separator") +"sysconfig"+System.getProperty("file.separator") +"hxUser"+System.getProperty("file.separator");
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