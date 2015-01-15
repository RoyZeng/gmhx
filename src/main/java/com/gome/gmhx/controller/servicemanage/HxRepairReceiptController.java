package com.gome.gmhx.controller.servicemanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.DecoderUtil;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxServiceCustomer;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;
import com.gome.gmhx.service.basicdata.HxMaintenanceService;
import com.gome.gmhx.service.servicemanage.HxInstallReceiptService;
import com.gome.gmhx.service.servicemanage.HxRepairReceiptService;
import com.gome.gmhx.service.servicemanage.HxServiceCustomerService;
import com.gome.gmhx.service.servicemanage.JlCustomerProductService;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;

@Controller
@RequestMapping(value="/repairReceipt")
public class HxRepairReceiptController {
	
	@Resource
	private HxMaintenanceService hxMaintenanceService;
	
	@Resource
	HxOrganizationService hxOrganizationService;
	
	@Resource
	private HxInstallReceiptService installReceiptService;
	
	@Resource
	private HxRepairReceiptService repairReceiptService;
	
	@Resource
	private JlCustomerProductService jlCustomerProductService;
	
	@Resource
	private HxServiceCustomerService hxServiceCustomerService; 
	
	@RequestMapping(value="/repairReceiptView")
	public String ServiceTicketCreateView(){
		return "servicemanage/RepairReceipt/RepairReceiptList";
	}
	
	@RequestMapping(value="/addRepairReceiptView")
	public String addRepairReceiptView(){
		return "servicemanage/RepairReceipt/RepairReceiptAdd";
	}
	
	@RequestMapping(value="/addview")
	public String ServiceTicketAdd(){
		return "servicemanage/RepairReceipt/SelectCustomer";
	}
	
	@RequestMapping(value="/saveRepairReceipt")
	@ResponseBody
	public String saveRepairReceipt(HttpServletRequest request,@RequestBody HxServiceTicketVO serviceTicketVO) throws Exception{
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String message = repairReceiptService.saveRepairReceipt(serviceTicketVO,sysUser);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/selectCustomer", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView selectCustomer(Page page,HxServiceCustomer customer,String machineCode,String installNum) throws Exception{
		ModelAndView mav = new ModelAndView("servicemanage/RepairReceipt/Customers");
		new DecoderUtil<HxServiceCustomer>().decodeURI(customer);
		Map <String , Object> map = BeanMapUtils.convertBean(customer);
		map.put("machineCode", machineCode);
		map.put("installNum", installNum);
		page.setParam(map);
		List<Map<String,Object>> list = hxServiceCustomerService.getCustomerList(page);
		mav.addObject("list", JsonUtil.writeListToDataGrid(list.size(),list));
		return mav;
	}
	
	@RequestMapping(value="/getCustomer", produces = "text/plain;charset=utf-8")
	@ResponseBody 
	public ModelAndView getCustomer(HxServiceCustomer customer){
		ModelAndView mav = new ModelAndView("servicemanage/RepairReceipt/RepairReceiptAdd");
		customer = hxServiceCustomerService.getCustomerById(customer.getCustomerId());
		mav.addObject("customer", customer);
		return mav;
	}
	
	@RequestMapping(value="/getRepairReceiptPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRepairReceiptPageList(Page page, HxServiceTicketVO serviceTicketVO,HttpServletRequest request,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_serviceDate_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_serviceDate_end
			) throws Exception{
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		Map<String, Object> map = BeanMapUtils.convertBean(serviceTicketVO);
		map.put("mod_createTime_st", mod_createTime_st);
		map.put("mod_createTime_end", mod_createTime_end);
		map.put("mod_serviceDate_st", mod_serviceDate_st);
		map.put("mod_serviceDate_end", mod_serviceDate_end);
		map.put("serviceType", "01");
		map.put("serviceStatus", "04");
		map.put("createOrganization", hxOrganizationService.getChild(sysUser.getCompanyId()));
		page.setParam(map);
		List<Map<String, Object>> list = repairReceiptService.getRepairReceiptPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/repairReceiptView/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView repairReceiptView(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/RepairReceipt/RepairReceiptShow");
		Map<String, Object> map = repairReceiptService.getRepairReceiptById(serviceId);
		mav.addObject("map", map);
		return mav;
	}

	@RequestMapping(value="/updateRepairReceiptView/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView updateRepairReceiptView(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/RepairReceipt/RepairReceiptUpdate");
		Map<String, Object> map = repairReceiptService.getRepairReceiptById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
	@RequestMapping(value="/updateRepairReceipt", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updateRepairReceipt(HttpServletRequest request,@RequestBody HxServiceTicketVO serviceTicketVO){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String message = repairReceiptService.updateRepairReceipt(serviceTicketVO,sysUser);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/getRepairReceiptTroubles/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRepairReceiptTroubles(@PathVariable String serviceId) throws Exception{
		List<Map<String, Object>> list = repairReceiptService.getTroublesByServiceId(serviceId);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	
	@RequestMapping(value="/getRepairReceiptParts/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRepairReceiptParts(@PathVariable String serviceId) throws Exception{
		List<Map<String, Object>> list = repairReceiptService.getPartsByServiceId(serviceId);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	
	@RequestMapping(value="/deleteServiceTicket/{serviceId}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String deleteServiceTicket(@PathVariable String serviceId){
		try {
			repairReceiptService.deleteRepairReceipt(serviceId);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e){
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	
	@RequestMapping(value="/affirmRepairReceipt/{serviceId}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String affirmRepairReceipt(@PathVariable String serviceId){
		try {
			repairReceiptService.submit(serviceId);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e){
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	
	@RequestMapping(value="/selectTroubleView")
	@ResponseBody
	public ModelAndView selectTroubleView(String type){
		ModelAndView mav = new ModelAndView("servicemanage/RepairReceipt/SelectTroubleView");
		mav.addObject("type", type);
		return mav;
	}
	
	@RequestMapping(value="/selectFaultView")
	@ResponseBody
	public ModelAndView selectFaultView(String machineType,String faultCode,String brand,String category){
		ModelAndView mav = new ModelAndView("servicemanage/RepairReceipt/SelectFaultView");
		mav.addObject("machineType", machineType);
		mav.addObject("faultCode",faultCode);
		mav.addObject("brand",brand);
		mav.addObject("category",category);
		return mav;
	}
	
	@RequestMapping(value="/getHxMaintenanceTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxMaintenanceTree(String type){
		List<Map<String ,Object>> classifyList = repairReceiptService.getHxMaintenanceTree(type);
        JSONArray array = new JSONArray();
        for (Map<String, Object> map : classifyList) {
			JSONObject object = new JSONObject();
	        object.put("id", map.get("fault_code"));
	        object.put("name", map.get("fault_name"));
	        object.put("pId", map.get("parent_code"));
	        object.put("chose", map.get("chose"));
			/*object.put("category", map.get("category"));
			object.put("categoryId", map.get("categoryId"));
			object.put("choseId", map.get("choseId"));
			object.put("validity", map.get("validity"));
			object.put("validityId", map.get("validityId"));
			object.put("arrangeNumber", map.get("arrange_number"));
			object.put("maintenanceCosts", map.get("maintenance_costs"));
			object.put("modifier", map.get("modifier"));*/
			// object.put("modDate", map.get("modDate"));
			object.put("open", false);
			array.add(object);
        }
		return array.toString();
	}
}





