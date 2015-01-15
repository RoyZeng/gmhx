package com.gome.gmhx.jbpm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;
import org.mybatis.spring.SqlSessionTemplate;

import com.gome.common.util.SpringUtil;
public class JbpmAuthorityService implements AssignmentHandler{
	
	
	@Override
	public void assign(Assignable assignable, OpenExecution execution)
			throws Exception {
		ProcessInstance pi = execution.getProcessInstance();
		String currentStatus = execution.getActivity().getName();
		String entityId = pi.getKey();
		String processDefinitionKey = execution.getProcessDefinitionId().split("-")[0];
		List<String> users = this.findCandidateUser(entityId, currentStatus, processDefinitionKey);
		for(String user:users){
			assignable.addCandidateUser(user);
		}
	}
	
	/**
	* @Title: findCandidateUser
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param workEntityId
	* @param @param workEntityType
	* @param @return 设定文件
	* @return String 返回类型
	* @throws
	*/
	public List<String> findCandidateUser(String entityId,String currentStatus,String processDefinitionKey){
		
		SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate)SpringUtil.getBean("sqlSessionTemplate");
		
		//查询该流程的该状态的配置
		Map<String,String> configparam = new HashMap<String,String>();
		configparam.put("currentStatus", currentStatus);
		configparam.put("processDefinitionKey", processDefinitionKey);
		Map<String,String> config = sqlSessionTemplate.selectOne("com.gome.jbpm.config_query",configparam);
		//查询该工作里岗位下的系统岗位
		List<String> positions = sqlSessionTemplate.selectList("com.gome.jbpm.role_query",config);
		
		List<JbpmParticipant> jbpmParticipants = null;
		//处理简单逻辑直接调用sql
		if(config.get("mybatisStatementId")!=null&&!"".equals(config.get("mybatisStatementId"))){
			Map param = new HashMap<String,String>();
			param.put("positions", positions);
			param.put("id", entityId);
			jbpmParticipants = sqlSessionTemplate.selectList(config.get("mybatisStatementId"), param);
		}else{//处理复杂逻辑由处理器返回
			JbpmParticipantHandler handler = JbpmParticipantHandlerFactory.getInstance(processDefinitionKey);
			jbpmParticipants = handler.getParticipants(entityId,currentStatus,positions);
		}
		List<String> users = new ArrayList<String>();
		for(JbpmParticipant jbpmParticipant:jbpmParticipants){
			users.add(jbpmParticipant.workFlowUserId());
		}
		return users;
	}

}
