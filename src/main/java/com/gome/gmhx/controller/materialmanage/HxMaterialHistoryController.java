package com.gome.gmhx.controller.materialmanage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.HxParcelDelivery;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.materialmanage.HxMaterialService;

@Controller
@RequestMapping(value="/hxMaterialHistory")
public class HxMaterialHistoryController {

	@Resource
	private HxMaterialService hxMaterialService;
	
	@RequestMapping(value="/hxMaterialHistoryView")
	private String ParcelDeliveryView(){//配件 申请历史
		return "materialmanage/hxMaterialHistory/hxMaterialHistoryList";
	} 
	
	@RequestMapping(value="/hxParcelView") //查询邮包收发货历史
	public String hxParcelView(){
		return "materialmanage/hxMaterialHistory/hxParcelHistoryList";
	}
	
	
	@RequestMapping(value="/getHxMaterialHistoryPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxMaterialPageList(HttpServletRequest request, Page page, HxMaterialManage hxMaterialManage) throws Exception{
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		Map<String, Object> map = BeanMapUtils.convertBean(hxMaterialManage);
		map.put("ksrq", request.getParameter("ksrq"));
		map.put("jsrq", request.getParameter("jsrq"));
		if (sysUser.getSysPositionType() != 1) {
//			map.put("fittingPositionId", sysUser.getSysPositionId());
			map.put("userAccount", sysUser.getUserAccount());
			map.put("fittingOrgId", sysUser.getCompanyId());
		}
		page.setParam(map);
		List<Map<String, Object>> list = hxMaterialService.getHxMaterialHistoryPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/getHxParcelPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxParcelPageList(HttpServletRequest request,Page page, HxParcelDelivery hxParcelReceipt) throws Exception{
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		Map <String , Object> map =BeanMapUtils.convertBean(hxParcelReceipt);
		map.put("sendDate_sta", request.getParameter("sendDate_sta"));
		map.put("sendDate_end", request.getParameter("sendDate_end"));
		map.put("founders", sysUser.getUserAccount());
		map.put("userAccount", sysUser.getUserAccount());
		if (sysUser.getSysPositionType() != 1) {
//			map.put("fittingPositionId", sysUser.getSysPositionId());
			map.put("fittingOrgId", sysUser.getCompanyId());
		}
		page.setParam(map);
		List<Map<String, Object>> list = hxMaterialService.getHxParcelPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
}
