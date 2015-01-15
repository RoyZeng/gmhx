package com.gome.gmhx.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.MD5EncryptUtils;
import com.gome.gmhx.entity.EmpAccount;
import com.gome.gmhx.entity.HxUser;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.basicdata.HxCodeService;
import com.gome.gmhx.service.sysconfig.HxUserService;
import com.gome.gmhx.service.wsdl.service.EmpService;
import com.gome.gmhx.service.wsdl.service.JlAccountService;

@Controller
public class LoginController {
	
	@Resource
	private HxUserService userService;
	
	@Resource
	private EmpService empService;
	
	@Resource
	private JlAccountService accountService;
	
	@Resource
	private HxCodeService hxCodeService;
	
	@RequestMapping(value="/toLogin")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request){
		String indexLoginId = request.getParameter("indexLoginId");
		String indexLoginPwd = request.getParameter("indexLoginPwd");
		//String indexLoginType = request.getParameter("indexLoginType");
		String positionId = request.getParameter("positionId");
		String indexVerificationcode = request.getParameter("indexVerificationcode");
		//获取session中放的验证码（谷歌开源工具KAPTCHA生成）的值
		String verificationcodeTemp = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
		if(StringUtils.isBlank(indexLoginId)) return "{\"flag\" : \"loginIdNull\"}";
		if(StringUtils.isBlank(indexLoginPwd)) return "{\"flag\" : \"loginPwdNull\"}";
		if(StringUtils.isBlank(positionId)) return "{\"flag\" : \"positionNull\"}";
		if(StringUtils.isBlank(indexVerificationcode))return "{\"flag\" : \"verificationcodeNull\"}";
		
		SysUser sysUser = null;
		HxUser hxUser = userService.queryUserByUserLoginName(indexLoginId);//从用户转储表中查询用户信息
		String indexLoginType = hxUser.getFromType();
		
		if(Constrants.GOME_USER.equals(indexLoginType)){
			sysUser = empService.getAccountByApAccount(indexLoginId);//身份管理平台导入用户
			if(sysUser==null){
				return "{\"flag\" : \"userNull\"}";
			}else{
				EmpAccount empAccount = empService.getResetPassword(sysUser.getUserAccount());
				if(empAccount==null){
					if(!indexLoginPwd.equals(sysUser.getUserPassword())){
						//密码错误之后session中的验证码值置空（防止暴力破解）
						request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
						return "{\"flag\" : \"loginPwdError\"}";
					}
				}else{
					if(!MD5EncryptUtils.MD5Encode(indexLoginPwd).equals(empAccount.getApAccountpwd())){//校验重置后的密码
						//密码错误之后session中的验证码值置空（防止暴力破解）
						request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
						return "{\"flag\" : \"loginPwdError\"}";
					}
				}
				//状态(1，在职，3，离职，5，锁定)
				String userState = sysUser.getUserState();
				if(StringUtils.isNotBlank(userState)){
					if("3".equals(userState)){
						return "{\"flag\" : \"loginDimission\"}";
					}else if("5".equals(userState)){
						return "{\"flag\" : \"loginLock\"}";
					}
				}
			}
		}else if(Constrants.THIRD_NETWORK_USER.equals(indexLoginType)){
			sysUser = accountService.selectAccountByPrimaryKey(indexLoginId);//第三方网点用户
			if(sysUser==null){
				return "{\"flag\" : \"userNull\"}";
			}else{
				if(!MD5EncryptUtils.MD5Encode(indexLoginPwd).equals(sysUser.getUserPassword())){//第三方网点用户密码使用了md5加密
					//密码错误之后session中的验证码值置空（防止暴力破解）
					request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
					return "{\"flag\" : \"loginPwdError\"}";
				}
				//状态(0正常 1锁定)
				String userState = sysUser.getUserState();
				if(StringUtils.isNotBlank(userState)){
					if("1".equals(userState)){
						return "{\"flag\" : \"loginLock\"}";
					}
				}
			}
		}else if(Constrants.SYS_DEFINE_USER.equals(indexLoginType)){
			sysUser = userService.getUserByUserLoginName(indexLoginId);//系统创建用户
			if(sysUser==null){
				return "{\"flag\" : \"userNull\"}";
			}else{
				if(!MD5EncryptUtils.MD5Encode(indexLoginPwd).equals(sysUser.getUserPassword())){
					//密码错误之后session中的验证码值置空（防止暴力破解）
					request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
					return "{\"flag\" : \"loginPwdError\"}";
				}
				//状态(0，锁定，1，解锁)
				String userState = sysUser.getUserState();
				if(StringUtils.isNotBlank(userState)){
					if("0".equals(userState)){
						return "{\"flag\" : \"loginLock\"}";
					}
				}
			}
		}
		//根据用户名和岗位id查询其登录时选择的岗位信息
		SysUser sysUserTemp = null;
		sysUser.setSysPositionId(positionId);//设置为登录时选择的岗位id
		sysUserTemp = userService.getPositionInfoBySysUser(sysUser);//系统创建用户
		if(sysUserTemp!=null){
			sysUser.setSysPositionId(sysUserTemp.getSysPositionId());
			sysUser.setSysPositionName(sysUserTemp.getSysPositionName());
			sysUser.setCompanyId(sysUserTemp.getOrgId());
			sysUser.setCompanyName(sysUserTemp.getCompanyName());
			sysUser.setOrgId(sysUserTemp.getOrgId());
		}
		
		request.getSession().setAttribute(Constrants.USER_INFO, sysUser);
		if(StringUtils.isNotBlank(verificationcodeTemp)){
			if(!verificationcodeTemp.equalsIgnoreCase(indexVerificationcode)){//验证码输入错误
				//密码错误之后session中的验证码值置空（防止暴力破解）
				request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
				return "{\"flag\" : \"verificationcodeError\"}";
			} //是否账号首次登录（初始密码123）
			else if("123".equals(indexLoginPwd)){
				return "{\"flag\" : \"needModifyPass\"}";
			}else{
				//根据岗位机构确定物料岗位类型(fittingPositionType)
				String orgId = "";
				if(Constrants.GOME_USER.equals(indexLoginType)){
					orgId = sysUser.getCompanyId();
				}else{
					orgId = sysUser.getOrgId();
				}
				int positionType = getPositionTypeByOrgId(orgId);
				sysUser.setSysPositionType(positionType);
				request.getSession().setAttribute(Constrants.USER_INFO, sysUser);
				return "{\"flag\" : \"success\"}";
			}
		}
		return null;
	}
	
	@RequestMapping(value="/alterUserInitPwd",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
    @ResponseBody
    public String alterUserInitPwd(Page page, HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        String newPwd = request.getParameter("newPwd");
        if(StringUtils.isBlank(newPwd)) return "{\"flag\" : \"newPwdNull\"}";
        String account = request.getParameter("name");
        HxUser userTemp = userService.getUserById(account);
        if(userTemp == null){
            return "{\"flag\" : \"accountNull\"}";
        }else {
        	//保存加密后的密码
			userTemp.setUserLoginPassword(MD5EncryptUtils.MD5Encode(newPwd));
			userTemp.setUserPasswordChangeDate(new Date());//设置密码修改日期
			userService.updateUserPwd(userTemp);
        	return "{\"flag\" : \"success\"}";
        }
    }
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginGet(HttpServletRequest request, HttpServletResponse response){
		return "login";
	}
	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}
	@RequestMapping(value="/toValidateAuth")
	public String toValidateAuth(){
		return "validateAuth";
	}
	@RequestMapping(value="/toModifyFirstPwd")
	public ModelAndView toModifyFirstPwd(String indexLoginId){
		ModelAndView mav=new ModelAndView("modifyFirstPwd");
		mav.addObject("indexLoginId", indexLoginId);
		return mav;
	}
	@RequestMapping(value="/validateAuth", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String validateAuth(HttpServletRequest request){
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		List<Map<String, String>> list = userService.getFittingPosition(sysUser);
		if(list == null || list.isEmpty()){
			return "true";
		}else{
			if(list.size() == 1){
				String positionType = list.get(0).get("positionType");
				sysUser.setSysPositionType(Integer.parseInt(positionType));
				request.getSession().setAttribute(Constrants.USER_INFO, sysUser);
				return "true";
			}else{
				JSONArray array = new JSONArray();
				for(Map<String, String> map : list){
					JSONObject object = new JSONObject();
					object.put("positionType", map.get("positionType"));
					object.put("positionId", map.get("positionId"));
					object.put("positionName", map.get("positionName"));
					object.put("orgId", map.get("orgId"));
					object.put("orgName", map.get("orgName"));
					array.add(object);
				}
				return array.toString();
			}
		}
	}
	
	@RequestMapping(value="/fittingPosition", produces="text/plain;charset=utf-8")
	public String fittingPosition(HttpServletRequest request, String orgId, String positionId, String positionType){
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		sysUser.setSysPositionType(Integer.parseInt(positionType));
		request.getSession().setAttribute(Constrants.USER_INFO, sysUser);
		return "index";
	}
	
	/**
	 * @描述: 注销后跳转到系统登陆面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginout")
	public String loginout(HttpServletRequest request) throws Exception {
		request.getSession().removeAttribute(Constrants.USER_INFO);
		return "login";
	}
	
	private int getPositionTypeByOrgId(String orgId) {
		int positionType = 0;
		List<Map<String, String>> list = hxCodeService.getFbOrgCombobox();
		List<String> orgIds = new ArrayList<String>();
		for(Map<String, String> map : list){
			String orgIdTemp = map.get("id");
			orgIds.add(orgIdTemp);
		}
		if("GMZB".equals(orgId)){
			positionType = 1;
		}else if(orgIds.contains(orgId)){
			positionType = 2;
		}else {
			positionType = 3;
		}
		return positionType;
	}

}
