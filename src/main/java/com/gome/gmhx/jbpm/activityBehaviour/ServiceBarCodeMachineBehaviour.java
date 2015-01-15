package com.gome.gmhx.jbpm.activityBehaviour;

import org.jbpm.api.activity.ActivityBehaviour;
import org.jbpm.api.activity.ActivityExecution;

import com.gome.common.util.SpringUtil;
import com.gome.gmhx.dao.servicemanage.HxServiceLongDistanceDao;
import com.gome.gmhx.dao.servicemanage.HxServiceTicketDao;


public class ServiceBarCodeMachineBehaviour implements ActivityBehaviour {

	@Override
	public void execute(ActivityExecution execution) throws Exception {
			System.out.println("========系统机审=============");
	}

}
