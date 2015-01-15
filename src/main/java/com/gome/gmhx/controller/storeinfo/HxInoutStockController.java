package com.gome.gmhx.controller.storeinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxFitting;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.HxPosition;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.basicdata.HxFittingService;
import com.gome.gmhx.service.materialmanage.HxMaterialService;
import com.gome.gmhx.service.storeinfo.HxInoutStockService;
import com.gome.gmhx.service.sysconfig.HxPositionService;

@Controller
@RequestMapping(value="/hxInoutStock")
public class HxInoutStockController {
	@Resource
	private HxInoutStockService hxInoutStockService;
	@Resource
	private HxPositionService hxPositoryService;
	@Resource
	private HxMaterialService hxMaterialService;
	@Resource
	private HxFittingService hxFittingService;
	
	@RequestMapping("/stockView")
	public ModelAndView stockView(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("storeinfo/inoutstock/inoutStockList");
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		mav.addObject("ptype", sysUser.getSysPositionType());
		return mav;
	}
	
	@RequestMapping(value="/getInoutSotckHistoryPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getInoutSotckHistoryPageList(HttpServletRequest request, Page page) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		//页面查询参数
		map.put("listNumber", request.getParameter("listNumber"));
		map.put("receiveCompany", request.getParameter("receiveCompany"));
		map.put("sendCompany", request.getParameter("sendCompany"));
		map.put("type", request.getParameter("type"));
		map.put("suitModel", request.getParameter("suitModel"));
		map.put("fittingType", request.getParameter("fittingType"));
		map.put("fittingCode", request.getParameter("fittingCode"));
		map.put("partsCode", request.getParameter("partsCode"));
		map.put("ksrq", request.getParameter("ksrq"));
		map.put("jsrq", request.getParameter("jsrq"));
		
		String fittingPositionType = request.getParameter("fittingPositionType");
		map.put("fittingPositionType", fittingPositionType);
		map.put("inout", request.getParameter("inout"));
		map.put("isNew", request.getParameter("isNew"));
		//登录用户为分部，  对于分部当前库存 只能查询收发货单位 是登录用户org的的记录；对于网点当前库存，只能查询收发货单位 是下级网点s的记录
		//登录用户为网点，只能查询登录网点当前库存并且收发货单位是用户org的记录
		//登录用户是总部，不过滤收发货单位条件
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		List<String> webs = new ArrayList<String>();
		if(sysUser.getSysPositionType() == 2){
			if("2".equals(fittingPositionType)){
				webs.add(sysUser.getOrgId());
			}else{
				List<Map<String, String>> orgs = hxMaterialService.getWebFittingOrgByFatherId(sysUser.getOrgId());
				for (Map<String, String> map2 : orgs) {
					webs.add(map2.get("org_code"));
				}
			}
		}else if("3".equals(fittingPositionType) && sysUser.getSysPositionType() ==3 ){
			webs.add(sysUser.getOrgId());
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
		//向datagrid中加入出入库详细信息，为查询详细页面准备值
		List<Map<String, Object>> list =hxInoutStockService.getInoutSotckHistoryPageList(page);
		for (Map<String, Object> map2 : list) {
			map2.put("inout", request.getParameter("inout"));
			map2.put("fittingPositionType", request.getParameter("fittingPositionType"));
			map2.put("isNew", request.getParameter("isNew"));
		}
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping("/viewInoutStock/{listNumber}")
	public ModelAndView viewInoutStock(HttpServletRequest request, @PathVariable String listNumber) {
		ModelAndView mav = new ModelAndView("storeinfo/inoutstock/inoutStockView");
		HxMaterialManage m = this.hxMaterialService.getHxMaterialShow(listNumber);
		mav.addObject("m", m);
		mav.addObject("inout", request.getParameter("inout"));
		mav.addObject("isNew", request.getParameter("isNew"));
		mav.addObject("fittingPositionType", request.getParameter("fittingPositionType"));
		mav.addObject("type", Constrants.FITTING_TITLE_MAP.get(m.getType()));
		StringBuffer title = new StringBuffer();
		if("1".equals(request.getParameter("fittingPositionType"))){
			title.append("总部");
		}else if("2".equals(request.getParameter("fittingPositionType"))){
			title.append("分部");
		}else if("3".equals(request.getParameter("fittingPositionType"))){
			title.append("网点");
		}
		if("1".equals(request.getParameter("isNew"))){
			title.append("配件新料");
		}else if("0".equals(request.getParameter("isNew"))){
			title.append("配件旧料");
		}
		if("1".equals(request.getParameter("inout"))){
			title.append("出库");
		}else if("2".equals(request.getParameter("inout"))){
			title.append("入库");
		}
		
		mav.addObject("title", title);
		return mav;
	}
	
	@RequestMapping(value="/getInoutStockDetail/{listNumber}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getInoutStockDetail(HttpServletRequest request, @PathVariable String listNumber) throws Exception{
		List<Map<String, Object>> lists = hxMaterialService.getHxMaterialDetails(listNumber);
		HxMaterialManage m = this.hxMaterialService.getHxMaterialShow(listNumber);
		String type = m.getType();
		String inout = request.getParameter("inout");//出入库
		String fittingPositionType = request.getParameter("fittingPositionType");//当前库存位置
		for (Map<String, Object> map : lists) {
			HxFitting fitting = hxFittingService.getHxFittingByFittingCode((String) map.get("fittingCode"));
			map.put("count", (map.get("auditCount") != null) ? (Integer)(map.get("auditCount")) : (Integer)(map.get("applyCount")));
			if("1".equals(fittingPositionType)){
				if("1".equals(inout)){//出库
					map.put("in_price", fitting.getBranchPrice());
					map.put("out_price", fitting.getBranchPrice());
				}else{//入库
					map.put("in_price", fitting.getCost());
				}

			} else if("2".equals(fittingPositionType)){
				if("1".equals(inout)){//出库
					map.put("in_price", fitting.getBranchPrice());
					if(type.startsWith("fb-kc-zy") || type.startsWith("fb-o-bf")){
						map.put("out_price", fitting.getBranchPrice());
					}else{
						//分部出库对象只有网点，所以对应出库价格为网点价格
						map.put("out_price", fitting.getNetworkPrice());
					}
				}else{//入库
					map.put("in_price", fitting.getBranchPrice());
				}

			}else if("3".equals(fittingPositionType)){
				if("1".equals(inout)){//出库
					map.put("in_price", fitting.getNetworkPrice());
					map.put("out_price", fitting.getNetworkPrice());
				}else{//入库
					map.put("in_price", fitting.getNetworkPrice());
				}

			}
		}
		return JsonUtil.writeListToDataGrid(lists.size(), lists);
	}
}
