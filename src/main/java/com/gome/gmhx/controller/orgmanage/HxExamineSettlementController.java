package com.gome.gmhx.controller.orgmanage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
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
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxExamineSettlementTicket;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxJBPMOrderVO;
import com.gome.gmhx.entity.vo.HxSettlementVO;
import com.gome.gmhx.service.common.MachineReviewService;
import com.gome.gmhx.service.orgmanage.HxExamineSettlementService;
import com.gome.gmhx.service.servicemanage.HxInstallReceiptService;
import com.gome.gmhx.service.servicemanage.HxRepairReceiptService;

@Controller
@RequestMapping(value="/hxExamineSettlement")
public class HxExamineSettlementController {
	
	@Resource
	HxExamineSettlementService hxExamineSettlementService;
	
	@Resource
	private HxInstallReceiptService installReceiptService;
	
	@Resource
	private HxRepairReceiptService repairReceiptService;
	
	@Resource
	private MachineReviewService machineReviewService;
	
	@RequestMapping(value="/examineSettlementView")
	public String examineSettlementView(){
		return "orgmanage/hxExamineSettlement/examineSettlementList";
	}
	
	@RequestMapping(value="/goTest",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String goTest(){
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, -1);    //得到前一个月  手工开启时结算当月的
		try{
			hxExamineSettlementService.settlementFee(calendar);
		}catch(DuplicateKeyException e){
			e.printStackTrace();
			return "结算失败,因为结算单号重复!";
		}catch(Exception e){
			e.printStackTrace();
			return "结算失败！";
		}
		 return "结算完成！";
	}
	
	@RequestMapping(value="/getExamineSettlementPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getExamineSettlementPageList(HttpServletResponse response, Page page, HxExamineSettlementTicket hxExamineSettlementTicket,
			String serviceUnitName,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date headquartersCheckTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date headquartersCheckTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementUnitConfirmTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementUnitConfirmTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date createTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date createTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date alterTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date alterTime_end
			) throws Exception{
		Map <String , Object> map =BeanMapUtils.convertBean(hxExamineSettlementTicket);
		map.put("settlementTime_st", settlementTime_st);
		map.put("settlementTime_end", settlementTime_end);
		map.put("headquartersCheckTime_st", headquartersCheckTime_st);
		map.put("headquartersCheckTime_end", headquartersCheckTime_end);
		map.put("settlementUnitConfirmTime_st", settlementUnitConfirmTime_st);
		map.put("settlementUnitConfirmTime_end", settlementUnitConfirmTime_end);
		map.put("createTime_st", createTime_st);
		map.put("createTime_end", createTime_end);
		map.put("alterTime_st", alterTime_st);
		map.put("alterTime_end", alterTime_end);
		page.setParam(map);
		List<Map<String, Object>> list = hxExamineSettlementService.getExamineSettlementPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/examineSettlementShow/{settlementId}")
	@ResponseBody
	public ModelAndView examineSettlementShow(@PathVariable String settlementId){
		ModelAndView mav = new ModelAndView("orgmanage/hxExamineSettlement/examineSettlementShow");
		HxExamineSettlementTicket es = hxExamineSettlementService.geExamineSettlementById(settlementId);
		HxJBPMOrderVO order=new HxJBPMOrderVO();
		order.setListNumber(settlementId);
		mav.addObject("order", order);
		mav.addObject("es", es);
		return mav;
	}
	
	@RequestMapping(value="/examineSettlementUpdate/{settlementId}")
	@ResponseBody
	public ModelAndView examineSettlementUpdate(@PathVariable String settlementId){
		ModelAndView mav = new ModelAndView("orgmanage/hxExamineSettlement/examineSettlementUpdate");
		HxExamineSettlementTicket es = hxExamineSettlementService.geExamineSettlementById(settlementId);
		HxJBPMOrderVO order=new HxJBPMOrderVO();
		order.setListNumber(settlementId);
		mav.addObject("order", order);
		mav.addObject("es", es);
		return mav;
	}
	
	@RequestMapping(value="/getSettlementFeeDetailGrid/{settlementId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getSettlementFeeDetailGrid(@PathVariable String settlementId) throws Exception{
		List<Map<String, Object>> list = hxExamineSettlementService.getSettlementFeeDetailGrid(settlementId);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	
	@RequestMapping(value="/getOtherFeeGrid/{settlementId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getOtherFeeGrid(@PathVariable String settlementId) throws Exception{
		List<Map<String, Object>> list = hxExamineSettlementService.getOtherFeeGridById(settlementId);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	
	@RequestMapping(value="/examineSettlementInstallDetail")
	@ResponseBody
	public ModelAndView examineSettlementInstallDetail(String serviceId,String settlementId){
		ModelAndView mav = new ModelAndView("orgmanage/hxExamineSettlement/installServiceTicketDetailShow");
		Map<String, Object> map = installReceiptService.getInstallReceiptById(serviceId);
		map.put("settlementId", settlementId);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/examineSettlementRepairDetail")
	@ResponseBody
	public ModelAndView examineSettlementRepairDetail(String serviceId,String settlementId){
		ModelAndView mav = new ModelAndView("orgmanage/hxExamineSettlement/repairServiceTicketDetailShow");
		Map<String, Object> map = repairReceiptService.getRepairReceiptById(serviceId);
		map.put("settlementId", settlementId);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/examineSettlementUpdate", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String examineSettlementUpdate(HttpServletRequest request,@RequestBody HxSettlementVO settlementVO){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String settlementId = hxExamineSettlementService.updateSettlement(sysUser,settlementVO);
            return "{\"flag\" : \"success\",\"settlementId\" : \""+settlementId+"\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
/*	@RequestMapping(value="/examineSettlementSend", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String examineSettlementSend(HttpServletRequest request,@RequestBody HxSettlementVO settlementVO){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String settlementId = hxExamineSettlementService.examineSettlementSend(sysUser,settlementVO);
            return "{\"flag\" : \"success\",\"settlementId\" : \""+settlementId+"\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}*/
	
	@RequestMapping(value="/examineSettlement", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String examineSettlement(HttpServletRequest request, String settlementId){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			hxExamineSettlementService.examineSettlement(settlementId);
            return "{\"flag\" : \"success\",\"settlementId\" : \""+settlementId+"\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	//------------确认结算
	
	@RequestMapping(value="/confirmSettlementView")
	public String confirmSettlementView(){
		return "orgmanage/hxExamineSettlement/confirmSettlementList";
	}
	
	@RequestMapping(value="/confirmSettlementUpdateView/{settlementId}")
	@ResponseBody
	public ModelAndView confirmSettlementUpdateView(@PathVariable String settlementId){
		ModelAndView mav = new ModelAndView("orgmanage/hxExamineSettlement/confirmSettlementUpdateShow");
		HxExamineSettlementTicket es = hxExamineSettlementService.geExamineSettlementById(settlementId);
		mav.addObject("es", es);
		return mav;
	}

	@RequestMapping(value="/confirmSettlementUpdate", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String confirmSettlementUpdate(HttpServletRequest request,@RequestBody HxSettlementVO settlementVO){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String settlementId = hxExamineSettlementService.updateSettlement(sysUser,settlementVO);
            return "{\"flag\" : \"success\",\"settlementId\" : \""+settlementId+"\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}	 

	@RequestMapping(value="/confirmSettlementView/{settlementId}")
	@ResponseBody
	public ModelAndView confirmSettlementView(@PathVariable String settlementId){
		ModelAndView mav = new ModelAndView("orgmanage/hxExamineSettlement/confirmSettlementShow");
		HxExamineSettlementTicket es = hxExamineSettlementService.geExamineSettlementById(settlementId);
		HxJBPMOrderVO order=new HxJBPMOrderVO();
		order.setListNumber(settlementId);
		mav.addObject("order", order);
		mav.addObject("es", es);
		return mav;
	}
	
	@RequestMapping(value="/getComfirmSettlementPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getComfirmSettlementPageList(HttpServletResponse response, Page page, HxExamineSettlementTicket hxExamineSettlementTicket,
			String serviceUnitName,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date headquartersCheckTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date headquartersCheckTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementUnitConfirmTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementUnitConfirmTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date createTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date createTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date alterTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date alterTime_end
			) throws Exception{
		Map <String , Object> map =BeanMapUtils.convertBean(hxExamineSettlementTicket);
		map.put("settlementTime_st", settlementTime_st);
		map.put("settlementTime_end", settlementTime_end);
		map.put("headquartersCheckTime_st", headquartersCheckTime_st);
		map.put("headquartersCheckTime_end", headquartersCheckTime_end);
		map.put("settlementUnitConfirmTime_st", settlementUnitConfirmTime_st);
		map.put("settlementUnitConfirmTime_end", settlementUnitConfirmTime_end);
		map.put("createTime_st", createTime_st);
		map.put("createTime_end", createTime_end);
		map.put("alterTime_st", alterTime_st);
		map.put("alterTime_end", alterTime_end);
		page.setParam(map);
		List<Map<String, Object>> list = hxExamineSettlementService.getComfirmSettlementPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/confirmSettlement", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String confirmSettlement(HttpServletRequest request,String workEntityId,String checkAgree,String approveComment,String orderType){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String result = hxExamineSettlementService.confirmSettlement(sysUser,workEntityId,approveComment,checkAgree);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "服务器异常！";
        }
	}
	
	//------------结算历史
	@RequestMapping(value="/examineSettlementHistoryView")
	public String examineSettlementHistoryView(){
		return "orgmanage/hxExamineSettlement/examineSettlementHistoryList";
	}
	
	@RequestMapping(value="/getExamineSettlementHistoryPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getExamineSettlementHistoryPageList(HttpServletRequest request, Page page, HxExamineSettlementTicket hxExamineSettlementTicket,
			String serviceUnitName,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date headquartersCheckTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date headquartersCheckTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementUnitConfirmTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date settlementUnitConfirmTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date createTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date createTime_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date alterTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date alterTime_end
			) throws Exception{
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		Map <String , Object> map =BeanMapUtils.convertBean(hxExamineSettlementTicket);
		map.put("settlementTime_st", settlementTime_st);
		map.put("settlementTime_end", settlementTime_end);
		map.put("headquartersCheckTime_st", headquartersCheckTime_st);
		map.put("headquartersCheckTime_end", headquartersCheckTime_end);
		map.put("settlementUnitConfirmTime_st", settlementUnitConfirmTime_st);
		map.put("settlementUnitConfirmTime_end", settlementUnitConfirmTime_end);
		map.put("createTime_st", createTime_st);
		map.put("createTime_end", createTime_end);
		map.put("alterTime_st", alterTime_st);
		map.put("alterTime_end", alterTime_end);
		map.put("settlementStatus", "S0");
		if(!(sysUser.getCompanyId()).equals("GMZB")){
			map.put("companyCode",sysUser.getCompanyId());
		}
		page.setParam(map);
		List<Map<String, Object>> list = hxExamineSettlementService.getExamineSettlementHistoryPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/examineSettlementHistoryShow/{settlementId}")
	@ResponseBody
	public ModelAndView examineSettlementHistoryShow(@PathVariable String settlementId){
		ModelAndView mav = new ModelAndView("orgmanage/hxExamineSettlement/examineSettlementHistoryShow");
		HxExamineSettlementTicket es = hxExamineSettlementService.geExamineSettlementById(settlementId);
		HxJBPMOrderVO order=new HxJBPMOrderVO();
		order.setListNumber(settlementId);
		mav.addObject("order", order);
		mav.addObject("es", es);
		return mav;
	}
	
}

















