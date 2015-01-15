package com.gome.gmhx.controller.storeinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.bean.ViewExcel;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.DateUtils;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxCurrentStockVO;
import com.gome.gmhx.service.materialmanage.HxMaterialService;
import com.gome.gmhx.service.storeinfo.HxCurrentStockService;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;
import com.gome.gmhx.service.sysconfig.HxPositionService;

@Controller
@RequestMapping(value="/hxCurrentStock")
public class HxCurrentStockController {
	@Resource
	private HxCurrentStockService hxCurrentStockService;
	@Resource
	private HxOrganizationService hxOrganizationService;
	@Resource
	private HxPositionService hxPositoryService;
	@Resource
	private HxMaterialService hxMaterialService;
	
	@RequestMapping("/stockView")
	public ModelAndView stockView(HttpServletRequest request){		
		ModelAndView mav = new ModelAndView("storeinfo/stock/stockList");
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		mav.addObject("ptype", sysUser.getSysPositionType());
		return mav;
	}
	
	@RequestMapping(value="/getFbOrgNames", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFbOrgNames(HttpServletRequest request){		
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		Integer ptype = sysUser.getSysPositionType();
		Map<String, String> orgMap = new HashMap<String, String>();
		if(sysUser.getSysPositionType() != 1)
			orgMap.put(hxOrganizationService.getOrganizationById(sysUser.getOrgId()).getOrgName(),sysUser.getOrgId());
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
	@RequestMapping(value="/getHxCurrentStockPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxCurrentStockPageList(HttpServletRequest request, Page page, HxCurrentStockVO hxCurrentStockVO) throws Exception{
		Map<String, Object> map = BeanMapUtils.convertBean(hxCurrentStockVO);
		String specifiedType = request.getParameter("specifiedType");
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		String isNew = request.getParameter("isNew");
		if(specifiedType == null || isNew == null){//解决前2次加载时 参数为空的异常
			isNew = "1";
			specifiedType = request.getParameter("ptype");
		}
		map.put("specifiedType", specifiedType);
		map.put("isNew", isNew);
		
		map.put("innerOrgs", getInnerOrgs(sysUser));
		
		page.setParam(map);
		List<Map<String, Object>> list = hxCurrentStockService.getHxCurrentStockPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	private String getInnerOrgs(SysUser sysUser){
		List<String> webs = new ArrayList<String>();
		if(sysUser.getSysPositionType() != 1) webs.add(sysUser.getOrgId());
		if(sysUser.getSysPositionType() == 2){
			List<Map<String, String>> orgs = hxMaterialService.getWebFittingOrgByFatherId(sysUser.getOrgId());
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
		if(sb == null) return "";
		else if(sb.toString().equals("")) return "";
		else return sb.toString();
	}
	@RequestMapping(value="/exportBatchExcel")
	public ModelAndView exportBatchMP08Excel(HttpServletRequest request, Page page, HxCurrentStockVO hxCurrentStockVO) throws Exception{
		String type = hxCurrentStockVO.getSpecifiedType();
		
		String tableField = "";
		String header = "";
		if(type !=null){
			if(type.equals("1")){
				tableField="fittingCode|fittingName|stock|totalPrice";
				header="配件编码|配件名称|库存(可用)|金额";
			}else if(type.equals("2")){
				tableField="orgName|fittingCode|fittingName|countWay|stock|totalWdPrice|totalPrice";
				header="分部名称|配件编码|配件名称|在途数量|库存(可用)|金额(元)|分部金额(元)";
			}else if(type.equals("3")){
				if("1".equals(hxCurrentStockVO.getIsNew())){
					tableField="orgName|fittingCode|fittingName|countWay|stock|totalPrice";
					header="网点名称|配件编码|配件名称|在途数量|库存(可用)|金额";
				}else{
					tableField="orgName|fittingCode|fittingName|stock|totalPrice";
					header="网点名称|配件编码|配件名称|库存(可用)|金额";
				}
			}
		}
		Map<String, Object> mapParam = BeanMapUtils.convertBean(hxCurrentStockVO);
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		mapParam.put("innerOrgs", getInnerOrgs(sysUser));
		
		int startPage = Integer.valueOf(request.getParameter("startPage"));
		int endPage = Integer.valueOf(request.getParameter("endPage"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));
//		page.setCurrentResult((startPage-1)*pageSize);
//		page.setPageCount((endPage-startPage)*pageSize);
//		List<Map<String, Object>> list = hxCurrentStockService.getHxCurrentStockPageList(page);
//		page.setParam(mapParam);
		mapParam.put("currentResult", (startPage-1)*pageSize);
		mapParam.put("pageCount", (endPage-startPage+1)*pageSize);
		List<Map<String, Object>> list = hxCurrentStockService.exportBatchExcel(mapParam);
		
		ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
}
