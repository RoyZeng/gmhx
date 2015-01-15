package com.gome.gmhx.controller.sysconfig;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.util.MD5EncryptUtils;
import com.gome.gmhx.entity.HxUser;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.sysconfig.HxUserService;

@Controller
@RequestMapping("personalSetting")
public class PersonalSettingController {
    
    @Resource
    private HxUserService userService;
    
    @RequestMapping(value="personView")
    public ModelAndView personView(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("sysconfig/hxPersonal/personShow");
        String userLoginName = ((SysUser) request.getSession().getAttribute(Constrants.USER_INFO)).getUserAccount();
        HxUser user = userService.getShowById(userLoginName);
        mav.addObject("user", user);
        return mav;
    }
//    @RequestMapping(value="personView")
//    public String personView(HttpServletRequest request, HttpServletResponse response){
//        ModelAndView mav = new ModelAndView("sysconfig/hxPersonal/personShow");
//        
//        String userLoginName = ((SysUser) request.getSession().getAttribute(Constrants.USER_INFO)).getUserAccount();
//        HxUser user = userService.getUserById(userLoginName);
//        mav.addObject("user", user);
//        return "sysconfig/hxPersonal/personShow";
//    }

    @RequestMapping(value="/updateView")
    public ModelAndView updateView(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("sysconfig/hxPersonal/personUpdate");
        String userLoginName = ((SysUser) request.getSession().getAttribute(Constrants.USER_INFO)).getUserAccount();
        HxUser user = userService.getUserById(userLoginName);
        mav.addObject("user", user);
        return mav;
    }
    
    @RequestMapping(value="updatePerson", method= RequestMethod.POST)
    @ResponseBody
    public String updatePerson(HttpServletRequest request, HxUser user){
        try {
            user.setUserUpdateDate(new Date());//当前用户修改信息
            userService.updateUser(user);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping(value="/personModifyPwdView")
    public ModelAndView updatePersonPWDView(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("sysconfig/hxPersonal/personModifyPwd");
        String userLoginName = ((SysUser) request.getSession().getAttribute(Constrants.USER_INFO)).getUserAccount();
//        HxUser user = userService.getUserById(userLoginName);
        mav.addObject("userLoginName", userLoginName);
        
        return mav;
    }
    
    @RequestMapping(value="updatePersonPWD", method=RequestMethod.POST)
    @ResponseBody
    public String updatePersonPWD(String userLoginName, String oldPassword, String newPassword){
        try {
            HxUser userTemp = userService.getUserById(userLoginName);
//            String newPasswd = request.getAttribute("newPassword");
            if(!MD5EncryptUtils.MD5Encode(oldPassword).equals(userTemp.getUserLoginPassword())){
                return "{\"flag\" : \"failure\"}";
            }
            userTemp.setUserLoginPassword(MD5EncryptUtils.MD5Encode(newPassword));
            userTemp.setUserPasswordChangeDate(new Date());//设置密码修改日期
            userService.updateUser(userTemp);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
