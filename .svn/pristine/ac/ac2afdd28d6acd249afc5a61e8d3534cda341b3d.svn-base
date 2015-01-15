package com.gome.gmhx.controller.workflow;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.bean.ViewExcel;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.Conf;
import com.gome.common.util.DateUtils;
import com.gome.common.util.JsonUtil;
import com.gome.common.util.StatusToValUtil;
import com.gome.gmhx.entity.HxExamineSettlementTicket;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.HxServiceLongDistance;
import com.gome.gmhx.entity.HxServiceRetreatReplacement;
import com.gome.gmhx.entity.HxUser;
import com.gome.gmhx.entity.vo.AdditionalParamsVO;
import com.gome.gmhx.entity.vo.HxJBPMOrderVO;
import com.gome.gmhx.jbpm.JbpmProcessDefinations;
import com.gome.gmhx.jbpm.JbpmService;
import com.gome.gmhx.jbpm.JbpmTaskTrajectory;
import com.gome.gmhx.jbpm.JbpmTransactions;
import com.gome.gmhx.jbpm.JbpmWorkEntityStatus;
import com.gome.gmhx.service.materialmanage.HxMaterialService;
import com.gome.gmhx.service.orgmanage.HxExamineSettlementService;
import com.gome.gmhx.service.servicemanage.HxInstallProjectService;
import com.gome.gmhx.service.servicemanage.HxInstallReceiptService;
import com.gome.gmhx.service.servicemanage.HxLongDistanceService;
import com.gome.gmhx.service.servicemanage.HxRepairReceiptService;
import com.gome.gmhx.service.servicemanage.HxRetreatReplacementCreateService;
import com.gome.gmhx.service.sysconfig.HxUserService;
import com.gome.gmhx.service.workflow.HxWorkFlowService;

@Controller
@RequestMapping(value="/hxWorkFlow")
public class HxWorkFlowController {
	
	@Resource
	private HxWorkFlowService hxWorkFlowService;
	
	@Resource
	private JbpmService jbpmService;
	
	@Resource
	private HxMaterialService hxMaterialService;
	
	@Resource
	private HxUserService hxUserService;
	
	@Resource
	HxLongDistanceService longDistanceService;

	@Resource
	private HxInstallProjectService installProjectService;
	
	@Resource
	private HxRetreatReplacementCreateService hxRetreatReplacementCreateService;
	
	@Resource
	private HxRepairReceiptService repairReceiptService;
	
	@Resource
	HxExamineSettlementService hxExamineSettlementService;
	
	@Resource
	private HxInstallReceiptService installReceiptService;

	@RequestMapping(value="/list")
	public String workList(){
		return "workflow/workList";
	}
	
	@RequestMapping(value="/done")
	public String workDone(){
		return "workflow/workDone";
	}
	
	@RequestMapping(value="/ing")
	public String workIng(){
		return "workflow/workIng";
	}
	
	@RequestMapping(value="/monitoring")
	public String workMonitoring(){
		return "workflow/workMonitoring";
	}
	
	@RequestMapping(value="/getWorkPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getWorkPageList(Page page,HxJBPMOrderVO order,@DateTimeFormat(pattern="yyyy-MM-dd")Date ksrq, @DateTimeFormat(pattern="yyyy-MM-dd")Date jsrq,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date operksrq,@DateTimeFormat(pattern="yyyy-MM-dd")Date operjsrq) throws Exception{
		Map<String, Object> map = BeanMapUtils.convertBean(order);
		map.put("ksrq", ksrq);
		map.put("jsrq", jsrq);
		map.put("operksrq", operksrq);
		map.put("operjsrq", operjsrq);
		page.setParam(map);
		List<Map<String, Object>> list = hxWorkFlowService.getWorkPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	
	@RequestMapping(value="/updateJbpmWorkEntity", produces="text/plain;charset=UTF-8")
    public @ResponseBody String updateJbpmWorkEntity(HttpServletRequest request, @RequestBody AdditionalParamsVO additionalParams) throws Exception{
		String workEntityId = additionalParams.getWorkEntityId();
		String checkAgree = additionalParams.getCheckAgree();
		String approveComment = additionalParams.getApproveComment();
		String orderType = additionalParams.getOrderType();
        String result = null;
        try {
            if("1".equals(checkAgree)){
            	hxWorkFlowService.updateJbpmWorkEntity(workEntityId,JbpmTransactions.transactionPass, approveComment,orderType,checkAgree, additionalParams);
            	result = "同意成功，该流程继续向下执行!";
            }else if("0".equals(checkAgree)){
            	hxWorkFlowService.updateJbpmWorkEntity(workEntityId,JbpmTransactions.transactionRefuse, approveComment,orderType,checkAgree, null);
            	result = "不同意成功，该流程无法向下执行!";
            }else if("2".equals(checkAgree)){
            	hxWorkFlowService.updateJbpmWorkEntity(workEntityId, JbpmTransactions.transactionReturnModify, approveComment, orderType, checkAgree, null);
            	result = "退回修改，该流程无法向下执行!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "服务器异常";
        }
        return result;
    }
	
	@RequestMapping(value = "/endProcessJbpmWorkEntity", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String endProcessJbpmWorkEntity(String workEntityId,String processInstanceId) throws Exception {
		String result = null;
		String orderType=null;
		try {
			if(processInstanceId!=null && processInstanceId.contains(".")){
        		String[] processInstanceIdSplit = processInstanceId.split("\\.");
        		orderType=processInstanceIdSplit[0];
        	}
			hxWorkFlowService.endProcessJbpmWorkEntity(workEntityId, orderType);
			result = "流程结束成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "服务器异常";
		}
		return result;
	}
	
	
	@RequestMapping(value="/statusOperator")
	public ModelAndView statusOperator(HttpServletRequest request,HxJBPMOrderVO order,String type){
		ModelAndView mav = new ModelAndView();
		String codekey = "";
		String function=null;
		String button=null;
        if(order.getProcessInstanceId()!=null && order.getProcessInstanceId().contains(".")){
        		String[] processInstanceIdSplit = order.getProcessInstanceId().split("\\.");
        		codekey=processInstanceIdSplit[0];
        		if(JbpmProcessDefinations.remoteInstallationFeeApplication.equals(codekey)){
        			HxServiceLongDistance distanceApply = longDistanceService.getDistanceApplyById(order.getListNumber());
        			//远程安装单返回的status是中文状态值,需转换
        			order.setStatus(StatusToValUtil.getValByStatus(distanceApply.getStatus()));
        			mav.addObject("distanceApply", distanceApply);
        		}else if(JbpmProcessDefinations.engineeringMachineServiceApply.equals(codekey)){
        			Map<String,Object> map = installProjectService.getInstallProjectById(order.getListNumber());
        			order.setStatus((String) map.get("service_status"));
        			mav.addObject("map", map);
        		}else if(JbpmProcessDefinations.returnMachineApply.equals(codekey)){
        			HxServiceRetreatReplacement sr = hxRetreatReplacementCreateService.getRetreatReplacementById(order.getListNumber());
        			order.setStatus(sr.getStatus());
        			mav.addObject("sr", sr);
        		}else if(JbpmProcessDefinations.repairReceipt.equals(codekey)){
        			Map<String, Object> map = repairReceiptService.getRepairReceiptById(order.getListNumber());
        			order.setStatus((String) map.get("service_status"));
        			mav.addObject("map", map);
        		}else if(JbpmProcessDefinations.installationReceipt.equals(codekey)){
        			Map<String, Object> map = installReceiptService.getInstallReceiptById(order.getListNumber());
        			order.setStatus((String) map.get("service_status"));
        			mav.addObject("map", map);
        		}else if(JbpmProcessDefinations.serviceBillAuditing.equals(codekey)){
        			HxExamineSettlementTicket es = hxExamineSettlementService.geExamineSettlementById(order.getListNumber());
        			mav.addObject("es", es);
        		}else if(codekey.startsWith("MP")){
        			HxMaterialManage manage = hxMaterialService.getHxMaterialManage(order.getListNumber());
        			order.setStatus(manage.getStatus());
        			String applicant=manage.getApplicant();
        			String updatePerson=manage.getUpdatePerson();
        			String createPerson=manage.getCreatePerson();
        			if(applicant!=null){
        				String[] array=applicant.split("_");
        				HxUser applicantUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
        				manage.setApplicant(applicantUser.getUserName());
        			}
        			if(updatePerson!=null){
        				String[] array=updatePerson.split("_");
        				HxUser updatePersonUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
        				manage.setUpdatePerson(updatePersonUser.getUserName());
        			}
        			if(createPerson!=null){
        				String[] array=createPerson.split("_");
        				HxUser createPersonUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
        				manage.setCreatePerson(createPersonUser.getUserName());
        			}
        			mav.addObject("m", manage);
    				mav.addObject("title", Constrants.FITTING_TITLE_MAP.get(manage.getType()));
        		}
        }
		mav.addObject("codekey", codekey);
		mav.addObject("order", order);
		mav.addObject("processInstance_Status", Conf.getValue(codekey+"_"+order.getStatus()));		
		mav.addObject("processInstanceToView", Conf.getValue(codekey));
		mav.addObject("processInstanceToCheck", Conf.getValue(codekey+"_CHECK"));
		mav.addObject("processInstanceToSave", Conf.getValue(codekey+"_SAVE"));
		mav.addObject("flag", request.getParameter("flag"));
		if(Constrants.WORK_MONITORING_TYPE.equals(type) || Constrants.WORK_ING_TYPE.equals(type) || Constrants.WORK_DONE_TYPE.equals(type)){
			mav.setViewName("workflow/workMonitoring_status");
			mav.addObject("goback", Conf.getValue(type+"_goback"));
		}else if(Constrants.WORK_LIST_TYPE_VIEW.equals(type)){
			mav.setViewName("workflow/workMonitoring_status");
			mav.addObject("goback", Conf.getValue("0_goback"));
		}
		else{
			mav.setViewName("workflow/workProcess_status");
		}
		
		if (JbpmWorkEntityStatus.status_out_bound.equals(order.getStatus())
				|| JbpmWorkEntityStatus.status_send.equals(order.getStatus())
				|| JbpmWorkEntityStatus.status_receive.equals(order.getStatus())
				|| JbpmWorkEntityStatus.status_check.equals(order.getStatus())
				|| JbpmWorkEntityStatus.status_in_bound.equals(order .getStatus())
				|| JbpmWorkEntityStatus.status_move_bound.equals(order.getStatus())) 
		{
			mav.setViewName("workflow/workConfirm_status");
			function=Conf.getValue(order.getStatus()+".function");
			button=Conf.getValue(order.getStatus()+".button");
			mav.addObject("function", function);
			mav.addObject("button", button);
		}
		
		return mav;
	}
	
	
	@RequestMapping(value="/getTaskTrajeCttoryPageList/{listNumber}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getTaskTrajeCttoryPageList(@PathVariable String listNumber,Page page) throws Exception{
		String workEntityId=listNumber;
		JbpmTaskTrajectory task=new JbpmTaskTrajectory();
		task.setWorkEntityId(workEntityId);
		page.setParam(task);
		List<Map<String, Object>> list=hxWorkFlowService.getTaskTrajeCttoryPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/changeToName", produces="text/plain;charset=UTF-8")
    public @ResponseBody String changeToName(String value,String type) throws Exception{
        String result = "";
        try {
			if("1".equals(type) && value!=null && value.trim().contains("_")){
				String[] array=value.split("_");
				HxUser applicantUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
				result=applicantUser.getUserName();
			}else if(value!=null && value.trim().contains("_")){
				if(value.contains(",")){
					String[] arrayFa=value.split(",");
					for(String str:arrayFa){
						String[] array=str.split("_");
						HxUser applicantUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
						result+=applicantUser.getUserName()+",";
					}
					result=result.substring(0, result.length()-1);
				}else{
					String[] array=value.split("_");
					HxUser applicantUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
					result=applicantUser.getUserName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
            result = "服务器异常";
		}
        return result;
    }
	
	@RequestMapping(value="/processInstanceIdToVal", produces="text/plain;charset=UTF-8")
    public @ResponseBody String processInstanceIdToVal(String processInstanceId) throws Exception{
        String result = null;
        try {
        	if(processInstanceId!=null && processInstanceId.contains(".")){
        		String[] processInstanceIdSplit = processInstanceId.split("\\.");
        		result=hxWorkFlowService.processInstanceIdToVal(processInstanceIdSplit[0]);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            result = "服务器异常";
        }
        return result;
    }
	
	@RequestMapping(value="/getWorkMonitoringPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getWorkMonitoringPageList(Page page,HxJBPMOrderVO order,String flag,@DateTimeFormat(pattern="yyyy-MM-dd")Date ksrq, @DateTimeFormat(pattern="yyyy-MM-dd")Date jsrq,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date operksrq,@DateTimeFormat(pattern="yyyy-MM-dd")Date operjsrq) throws Exception{
		Map<String, Object> map = BeanMapUtils.convertBean(order);
		map.put("ksrq", ksrq);
		map.put("jsrq", jsrq);
		map.put("operksrq", operksrq);
		map.put("operjsrq", operjsrq);
		map.put("flag", flag);
		page.setParam(map);
		List<Map<String, Object>> list = hxWorkFlowService.getWorkMonitoringPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/exportMP08Excel")
	public ModelAndView exportMP08Excel(String listNumber) throws Exception{
		String tableField="listNumber|fittingAuthName|createPerson|applyDate|participants3|createdates3|participants4|createdates4|fittingCode|fittingName|price|applyCount|COMMENT";
		String header="单据编号|单据类型|申请人|申请日期|分部审批人|分部审批日期|总部审批人|总部审批日期|配件编码|配件名称|单价|数量|备注";
		List<Map<String, Object>> list = hxWorkFlowService.exportMP08Excel(listNumber);
		for(Map<String, Object> map:list){
			if(map.get("participants3")!=null && !"".equals(map.get("participants3").toString())){
				String[] array=((String) map.get("participants3")).split("_");
				HxUser applicantUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
				map.put("participants3", applicantUser.getUserName());
			}
			if(map.get("participants4")!=null && !"".equals(map.get("participants4").toString())){
				String[] array=((String) map.get("participants4")).split("_");
				HxUser applicantUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
				map.put("participants4", applicantUser.getUserName());
			}
		}
		ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
		}
	
		@RequestMapping(value="/exportBatchMP08Excel")
		public ModelAndView exportBatchMP08Excel(HxJBPMOrderVO order,@DateTimeFormat(pattern="yyyy-MM-dd")Date ksrq, @DateTimeFormat(pattern="yyyy-MM-dd")Date jsrq) throws Exception{
			String tableField="listNumber|fittingAuthName|createPerson|applyDate|participants3|createdates3|participants4|createdates4|fittingCode|fittingName|price|applyCount|COMMENT";
			String header="单据编号|单据类型|申请人|申请日期|分部审批人|分部审批日期|总部审批人|总部审批日期|配件编码|配件名称|单价|数量|备注";
			Map<String, Object> mapParam = BeanMapUtils.convertBean(order);
			mapParam.put("ksrq", ksrq);
			mapParam.put("jsrq", jsrq);
			List<Map<String, Object>> list = hxWorkFlowService.exportBatchMP08Excel(mapParam);
			for(Map<String, Object> map:list){
				if(map.get("participants3")!=null && !"".equals(map.get("participants3").toString())){
					String[] array=((String) map.get("participants3")).split("_");
					HxUser applicantUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
					map.put("participants3", applicantUser.getUserName());
				}
				if(map.get("participants4")!=null && !"".equals(map.get("participants4").toString())){
					String[] array=((String) map.get("participants4")).split("_");
					HxUser applicantUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
					map.put("participants4", applicantUser.getUserName());
				}
			}
			ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
			return new ModelAndView(viewExcel);
			}
}
