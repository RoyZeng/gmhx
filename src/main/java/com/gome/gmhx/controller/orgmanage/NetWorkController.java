package com.gome.gmhx.controller.orgmanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxNetWork;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.materialmanage.HxMaterialService;
import com.gome.gmhx.service.orgmanage.HxNetWorkService;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;

@Controller
@RequestMapping(value="/Network")
public class NetWorkController {   
    
	@Resource
	private HxNetWorkService networkService;
	@Resource
	private HxMaterialService hxMaterialService;
	@Resource
	private HxOrganizationService hxOrganizationService;
	
	@RequestMapping(value="/NetworkView")
	public String NetworkView(){
		return "orgmanage/NetworkInformation/NetworkList";
	}
	
	@RequestMapping(value="/getFbOrgNames", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFbOrgNames(HttpServletRequest request){//获取下拉网点机构
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		Integer ptype = sysUser.getSysPositionType();
		Map<String, String> orgMap = new HashMap<String, String>();
//		if(sysUser.getSysPositionType() != 1)
//			orgMap.put(hxOrganizationService.getOrganizationById(sysUser.getOrgId()).getOrgName(),sysUser.getOrgId());
		if(ptype==2){
			List<Map<String, String>> map = hxMaterialService.getWebFittingOrgByFatherId(sysUser.getOrgId());
			for (Map<String, String> map2 : map) {
				orgMap.put(map2.get("org_name")+"("+map2.get("org_code")+")", map2.get("org_code"));
			}
		}
		Set<Entry<String, String>> set = orgMap.entrySet();
		Iterator<Entry<String, String>> iter = set.iterator();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		
		while(iter.hasNext()){
			Entry<String, String> entry = iter.next();
			JSONObject object = new JSONObject();
			object.put("text", entry.getKey());
			object.put("value", entry.getValue());
			if(ptype == 3){//网点用户选择当前网点
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();
	}

	@RequestMapping(value="/getNetWorkPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getNetWorkList(HttpServletResponse response,HttpServletRequest request, Page page, HxNetWork network) throws Exception{
		Map<String,Object> map = BeanMapUtils.convertBean(network);
		//登录用户为分部， 只能查询机构名称是下级网点s的记录
		//登录用户是总部，不过滤机构条件
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		List<String> webs = new ArrayList<String>();
		if(sysUser.getSysPositionType() == 2){
			List<Map<String, String>> orgs = hxMaterialService.getWebFittingOrgByFatherId(sysUser.getOrgId());
			if(orgs.size() == 0){//该分部不存在下级网点
				return JsonUtil.writeListToDataGrid(0, new ArrayList<Map<String, Object>>());
			}
			for (Map<String, String> map2 : orgs) {
				webs.add(map2.get("org_code"));
			}
		}
		StringBuffer sb = new StringBuffer();
		for (String string : webs) {
			sb.append("'"+string+"',");
		}
		if(sb.indexOf(",")>0){
			String s = sb.toString();
			s = s.substring(0, s.lastIndexOf(","));
			sb = new StringBuffer(s);
		}
		if(sb.toString().equals("")) sb = null;
		map.put("innerOrgs", sb);
		page.setParam(map);
		List<Map<String, Object>> list = networkService.getNetWorkPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/showView/{id}")
	public ModelAndView showView(@PathVariable(value = "id") String aa){
		ModelAndView mav = new ModelAndView("orgmanage/NetworkInformation/NetworkShow");
		HxNetWork network = networkService.getShowById(aa);
		mav.addObject("network", network);
		return mav;
	}
}
