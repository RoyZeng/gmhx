package com.gome.gmhx.controller.schedule;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.common.bean.InitCodeBean;
import com.gome.common.util.SpringUtil;
import com.gome.gmhx.schedule.CreateSettlementTicketJob;
import com.gome.gmhx.schedule.GetAccountJob;
import com.gome.gmhx.schedule.SynchronizeOrganizationUser;
import com.gome.gmhx.schedule.UnloadEmpUser;
import com.gome.gmhx.schedule.UnloadOrganization;
import com.gome.gmhx.schedule.UnloadUser;

@Controller
@RequestMapping(value="/schedule")
public class ScheduleController {

	@Resource
	private UnloadUser unloadUser;
	@Resource
	private UnloadOrganization unloadOrganization;
	@Resource
	private UnloadEmpUser unloadEmpUser;
	@Resource
	private SynchronizeOrganizationUser synchronizeOrganizationUser;
	@Resource
	private GetAccountJob getAccountJob;
	@Resource
	private CreateSettlementTicketJob createSettlementTicketJob;
	
	@RequestMapping("/scheduleList")
	public String scheduleList(){
		return "sysconfig/schedule/scheduleList";
	}
	
	@RequestMapping(value="unloadUser", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String unloadUser(){
		try {
			this.unloadUser.unloadUser();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value="unloadOrganization", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String unloadOrganization(){
		try {
			this.unloadOrganization.unloadOrganization();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value="unloadEmpUser", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String unloadEmpUser(){
		try {
			this.unloadEmpUser.unloadEmpUser();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value="syncOrganizationUser", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String syncOrganizationUser(){
		try {
			this.synchronizeOrganizationUser.synchronizeOrganizationUser();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value="getAccountJob", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getAccountJob(){
		try {
			this.getAccountJob.getAccount();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value="createSettlmentTicketJob", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String createSettlmentTicketJob(){
		try {
			this.createSettlementTicketJob.createSettlementTicket();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value="codeSynchCache", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String codeSynchCache(){
		try {
			InitCodeBean initCodeBean = (InitCodeBean)SpringUtil.getBean("codeSynchCachebean");
			initCodeBean.codeSynchCache();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
}
