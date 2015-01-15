package com.gome.gmhx.schedule;

import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gome.gmhx.service.orgmanage.HxExamineSettlementService;

@Component
public class CreateSettlementTicketJob {
	@Resource
	HxExamineSettlementService hxExamineSettlementService;
	
	public void createSettlementTicket(){
		/**
		 * 每月的一号结算上一个月的服务单
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);    //得到前一个月
		try{
			hxExamineSettlementService.settlementFee(calendar);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}





















