package com.gome.gmhx.jbpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.task.Participation;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.client.ClientProcessInstance;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;


/*
 * 服务说明：
 * 1.所有的流程启动以最高版本号，即通过流程定义的key启动。
 * 2.任务轨迹暂不支持会签的情况，系统暂无此需求。
 * 3.任务实体需实现JBPMWorkEntity 接口，流程实例的key值为workEntityType_workEntityId的约定形式
 * 4.为了兼容一用户多岗的情况，系统的用户标识为岗位类型+岗位id+用户名
 * 
 * */

/**
* @ClassName: JbpmService
* @Description: jbpm工作流统一接口
* @author liuchao43
* @date Aug 27, 2014 1:51:04 PM
*
*/
@Service("jbpmService")
public class JbpmService {
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private ExecutionService executionService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private HibernateTemplate hibernateTemplate;
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	/**
	* @Title: startProcessInstanceByKey
	* @Description:启动工作流，业务主键为type~id形式
	* @param @param processDefineKey流程定义的key 已最高版本号启动
	* @param @param jBPMWorkEntity
	* @return void 返回类型
	* @throws
	*/
	public void startProcessInstanceByKey(String processDefineKey,JbpmWorkEntity jBPMWorkEntity){
		Map<String,Object> variables = new HashMap<String,Object>();
		String applicant = "";
		if(jBPMWorkEntity.getApplicant() !=null&&!"".equals(jBPMWorkEntity.getApplicant())){
			applicant = jBPMWorkEntity.getApplicant();
		}else{
			applicant = this.getCurrentUsername();
			jBPMWorkEntity.setApplicant(applicant);
		}
		variables.put("previousApplicant",applicant);
		variables.put("applicant", applicant);
		ProcessInstance pi =  executionService.startProcessInstanceByKey(processDefineKey,variables,jBPMWorkEntity.getWorkEntityId());
		jBPMWorkEntity.setProcessInstanceId(pi.getId());
		jBPMWorkEntity.setStatus("S2");
		if(jBPMWorkEntity.getApplyDate()==null){
			jBPMWorkEntity.setApplyDate(new Date());
		}
		this.recordTaskTrajectoryAndUpdateStatus(jBPMWorkEntity,JbpmTransactions.transactionSubmit,"提交申请");
	}
	
	/**
	* @Title: startProcessInstanceByKey
	* @Description:启动工作流，业务主键为type~id形式
	* @param @param processDefineKey流程定义的key 已最高版本号启动
	* @param @param jBPMWorkEntity
	* @param @param variables 设定文件
	* @return void 返回类型
	* @throws
	*/
	public void startProcessInstanceByKey(String processDefineKey,JbpmWorkEntity jBPMWorkEntity,Map<String,Object> variables){
		String applicant = "";
		if(jBPMWorkEntity.getApplicant() !=null&&!"".equals(jBPMWorkEntity.getApplicant())){
			applicant = jBPMWorkEntity.getApplicant();
		}else{
			applicant = this.getCurrentUsername();
			jBPMWorkEntity.setApplicant(applicant);
		}
		variables.put("previousApplicant",applicant);
		variables.put("applicant", applicant);
		ProcessInstance pi =  executionService.startProcessInstanceByKey(processDefineKey,variables,jBPMWorkEntity.getWorkEntityId());
		jBPMWorkEntity.setStatus("S2");
		jBPMWorkEntity.setProcessInstanceId(pi.getId());
		if(jBPMWorkEntity.getApplyDate()==null){
			jBPMWorkEntity.setApplyDate(new Date());
		}
		this.recordTaskTrajectoryAndUpdateStatus(jBPMWorkEntity,(String)variables.get("outGoingName")==null?JbpmTransactions.transactionSubmit:(String)variables.get("outGoingName"),"提交申请");
	}
	
	/**
	* @Title: startProcessInstanceByKey
	* @Description:启动工作流
	* @param @param processDefineKey流程定义的key 已最高版本号启动
	* @param @param jBPMWorkEntity
	* @param @param variables 设定文件
	* @param @param approveComment 设定文件
	* @return void 返回类型
	* @throws
	*/
	public void startProcessInstanceByKey(String processDefineKey,JbpmWorkEntity jBPMWorkEntity,Map<String,Object> variables,String applyComment){
		String applicant = "";
		if(jBPMWorkEntity.getApplicant() !=null&&!"".equals(jBPMWorkEntity.getApplicant())){
			applicant = jBPMWorkEntity.getApplicant();
		}else{
			applicant = this.getCurrentUsername();
			jBPMWorkEntity.setApplicant(applicant);
		}
		variables.put("previousApplicant",applicant);
		variables.put("applicant", applicant);
		ProcessInstance pi =  executionService.startProcessInstanceByKey(processDefineKey,variables,jBPMWorkEntity.getWorkEntityId());
		jBPMWorkEntity.setStatus("S2");
		jBPMWorkEntity.setProcessInstanceId(pi.getId());
		if(jBPMWorkEntity.getApplyDate()==null){
			jBPMWorkEntity.setApplyDate(new Date());
		}
		this.recordTaskTrajectoryAndUpdateStatus(jBPMWorkEntity,(String)variables.get("outGoingName")==null?JbpmTransactions.transactionSubmit:(String)variables.get("outGoingName"),applyComment);
	}
	
	/**
	* @Title: startProcessInstanceById
	* @Description:启动工作流(默认已最高版本号启动，此方法废弃)，业务主键为type-id形式
	* @param @param processDefineKey流程定义的id id=key-version 
	* @param @param jBPMWorkEntity
	* @param @param variables 设定文件
	* @return void 返回类型
	* @throws
	*/
	@Deprecated
	public void startProcessInstanceById(String processDefineId,JbpmWorkEntity jBPMWorkEntity,Map<String,Object> variables,String approveComment){
		throw new RuntimeException("暂不支持");
	}
	


	/**
	* @Title: findAllMyTaskWorkEntityId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return 设定文件
	* @return List<String> 返回类型
	* @throws
	*/
	public List<String> findAllMyTaskWorkEntityId(){
		List<Task> tasks = taskService.createTaskQuery().candidate(this.getCurrentUsername()).list();
		return findProcessIntancenKeyByTaskIds(tasks);
	}
	


	/**
	* @Title: findMyTaskWorkEntityIdByActivityName
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param activityName
	* @param @return 设定文件
	* @return List<String> 返回类型
	* @throws
	*/
	public List<String> findMyTaskWorkEntityIdByActivityName(String activityName){
		List<Task> tasks = taskService.createTaskQuery().candidate(this.getCurrentUsername()).activityName(activityName).list();
		return findProcessIntancenKeyByTaskIds(tasks);
	}
	
	


	/**
	* @Title: findMyTaskWorkEntityIdByProcessDefinitionKey
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param processDefinitionKey
	* @param @return 设定文件
	* @return List<String> 返回类型
	* @throws
	*/
	public List<String> findMyTaskWorkEntityIdByProcessDefinitionKey(String processDefinitionKey){
		List<ProcessDefinition> pds = this.repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).list();
		List<Task> allTasks = new ArrayList<Task>();
		for(ProcessDefinition pd:pds){
				List<Task> tasks =  taskService.createTaskQuery().candidate(this.getCurrentUsername()).processDefinitionId(pd.getId()).list();
				allTasks.addAll(tasks);
		}
		return findProcessIntancenKeyByTaskIds(allTasks);
	}
	


	/**
	* @Title: findMyTaskWorkEntityIdByActivityNames
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param activityNames
	* @param @return 设定文件
	* @return List<String> 返回类型
	* @throws
	*/
	public List<String> findMyTaskWorkEntityIdByActivityNames(List<String> activityNames){
		List<Task> allTasks = new ArrayList<Task>();
		for(String activityName:activityNames){
			List<Task> tasks = taskService.createTaskQuery().candidate(this.getCurrentUsername()).activityName(activityName).list();
			allTasks.addAll(tasks);
		}
		return findProcessIntancenKeyByTaskIds(allTasks);
	}
	
	/**
	* @Title: findAllMyTaskWorkEntityId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param activityName
	* @param @return 设定文件
	* @return Map<String,List<String>> 返回类型
	* @throws
	*/
	public List<String> findMyTaskWorkEntityIdByProcessDefinitionKeyAndActivityName(String processDefinitionKey,String activityName){
		List<ProcessDefinition> pds = this.repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).list();
		List<Task> allTasks = new ArrayList<Task>();
		for(ProcessDefinition pd:pds){
				List<Task> serviceTasks =  taskService.createTaskQuery().candidate(this.getCurrentUsername()).processDefinitionId(pd.getId()).activityName(activityName).list();
				allTasks.addAll(serviceTasks);
			}
		return findProcessIntancenKeyByTaskIds(allTasks);
	}
	
	

	/**
	* @Title: findProcessIntancenKeyByTaskIds
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param tasks
	* @param @return 设定文件
	* @return List<String> 返回类型
	* @throws
	*/
	private List<String> findProcessIntancenKeyByTaskIds(List<Task> tasks){
		if(tasks.isEmpty()){
			return new ArrayList<String>();
		}
		StringBuffer sb = new StringBuffer();
		for(Task t:tasks){
			sb.append("'"+t.getId()+"',");
		}
		String idString = sb.toString().substring(0,sb.length()-1);
		String hql = "select p.processInstance.key from org.jbpm.pvm.internal.task.TaskImpl t,ExecutionImpl p where t.processInstance.id = p.id and t.id in ("+idString+")";
		List<String> resultList = hibernateTemplate.find(hql);
		return resultList;
	}
	
	/**
	* @Title: completeTask
	* @Description: TODO 触发任务流转
	* @param @param processIntanceId
	* @param @param outGoing
	* @param @param variables 设定文件
	* @return void 返回类型
	* @throws
	*/
	public void completeTask(JbpmWorkEntity jBPMWorkEntity,String outGoing,String approveComment){
		this.completeTask(jBPMWorkEntity, outGoing, approveComment,  new HashMap());
	}
	
	/**
	* @Title: completeTask
	* @Description: TODO 触发任务流转
	* @param @param processIntanceId
	* @param @param outGoing
	* @param @param variables 设定文件
	* @return void 返回类型
	* @throws
	*/
	public void completeTask(JbpmWorkEntity jBPMWorkEntity,String outGoing,String approveComment,Map<String,Object> variables){

		Task task = taskService.createTaskQuery().candidate(getCurrentUsername()).processInstanceId(jBPMWorkEntity.getProcessInstanceId()).uniqueResult();
		if(task!=null){
			taskService.completeTask(task.getId(),outGoing,variables);
		}else{
			throw new RuntimeException("操作失败！");
		}
		this.recordTaskTrajectoryAndUpdateStatus(jBPMWorkEntity,outGoing,approveComment);
	}
	
	
	/**
	* @Title: delProcecessInstance
	* @Description: TODO删除申请单时删除流程实例
	* @param @param processIntanceId 设定文件
	* @return void 返回类型
	* @throws
	*/
	public void delProcecessInstance(String processIntanceId){
		this.executionService.deleteProcessInstanceCascade(processIntanceId);
	}
	/**
	* @Title: taskTask
	* @Description: 多人共享任务锁定
	* @param  设定文件
	* @return void 返回类型
	* @throws
	*/
	public void  takeTask(String processIntanceId ){
		//一个user在一个流程实例中最多有一个任务
		Task task = taskService.createTaskQuery().candidate(getCurrentUsername()).processInstanceId(processIntanceId).uniqueResult();
		if(task!=null){
			taskService.takeTask(task.getId(), getCurrentUsername());
		}else{
			throw new RuntimeException("操作失败！");
		}
	}
	
	/**
	* @Title: recordTaskTrajectory
	* @Description: 保存任务轨迹
	* @param @param jBPMWorkEntity
	* @param @param processDefinitionKey
	* @param @param outGoingName 设定文件
	* @return void 返回类型
	* @throws
	*/
	private void recordTaskTrajectoryAndUpdateStatus(JbpmWorkEntity jBPMWorkEntity,String outGoingName,String approveComment){
		//暂无对会签的支持，任务一个任务实例指向一个节点，只有一个任务
		//查询任务流转后的流程实例的对应情况
		ProcessInstance  pi = this.executionService.createProcessInstanceQuery().processInstanceId(jBPMWorkEntity.getProcessInstanceId()).uniqueResult();
		JbpmTaskTrajectory tt = new JbpmTaskTrajectory();
		tt.setCurrentActivity(jBPMWorkEntity.getStatus());
		List<HistoryActivityInstance> hais = this.historyService.createHistoryActivityInstanceQuery().activityName(jBPMWorkEntity.getStatus()).processInstanceId(jBPMWorkEntity.getProcessInstanceId()).list();
		
		if(hais.isEmpty()){
			tt.setStartTime(new Date());
			tt.setEndTime(new Date());
		}else{
			HistoryActivityInstance hai = hais.get(0);
			tt.setStartTime(hai.getStartTime());
			tt.setEndTime(hai.getEndTime());
		}

		if(pi!=null){
			ClientProcessInstance cpi = (ClientProcessInstance)pi;
			Task task = this.taskService.createTaskQuery().processInstanceId(pi.getId()).uniqueResult();
			List<Participation> participations = this.taskService.getTaskParticipations(task.getId());
			if(!participations.isEmpty()){
				StringBuffer participationString = new StringBuffer();
				for(int i=0;i<participations.size();i++){
					if(i==participations.size()-1){
						participationString.append(participations.get(i).getUserId());
					}else{
						participationString.append(participations.get(i).getUserId()+",");
					}
				}
				tt.setNextParticipants(participationString.toString());
			}
			tt.setNextActivityName(cpi.getActivity().getName());
			jBPMWorkEntity.setStatus(cpi.getActivity().getName());
		}else{
			jBPMWorkEntity.setStatus(JbpmWorkEntityStatus.status_end);
			tt.setNextActivityName(JbpmWorkEntityStatus.status_end);
		}
		
		tt.setCreateDate(new Date());
		tt.setOutGoingName(outGoingName);
		tt.setApplicant(jBPMWorkEntity.getApplicant());
		tt.setApplyTime(jBPMWorkEntity.getApplyDate());
		tt.setOutGoingName(outGoingName);
		tt.setParticipant(getCurrentUsername());
		tt.setProcessInstanceId(jBPMWorkEntity.getProcessInstanceId());
		tt.setWorkEntityId(jBPMWorkEntity.getWorkEntityId());
		tt.setProcessDefinitionKey(jBPMWorkEntity.getProcessInstanceId().split("\\.")[0]);
		tt.setApproveComment(approveComment);
		this.sqlSessionTemplate.insert("com.gome.gmhx.dao.workflow.JbpmTaskTrajectoryDao.insert", tt);
	}

	/**
	* @Title: 管理员结束任务
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param jBPMWorkEntity 设定文件
	* @return void 返回类型
	* @throws
	*/
	public void terminationTask(JbpmWorkEntity jBPMWorkEntity){
		ProcessInstance  pi = this.executionService.createProcessInstanceQuery().processInstanceId(jBPMWorkEntity.getProcessInstanceId()).uniqueResult();
		
		if(pi!=null){
			this.executionService.signalExecutionById(jBPMWorkEntity.getProcessInstanceId(), JbpmTransactions.transactionTermination);
			this.recordTaskTrajectoryAndUpdateStatus(jBPMWorkEntity,JbpmTransactions.transactionTermination,"管理员结束！");
		}else{
			throw new RuntimeException("流程已结束！");
		}
	}
	
	private String getCurrentUsername(){
			if(CurrentSysUser.getCurrentSysUser()==null){
				return "admin";
			}
			return CurrentSysUser.getCurrentSysUser().getWorkFlowUser();
	}
	
	
	/**
	* @Title: serviceMachineTrial 服务机审
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param jBPMWorkEntity
	* @param @param outging 设定文件
	* @return void 返回类型
	* @throws
	*/
	public void serviceMachineTrial(JbpmWorkEntity jBPMWorkEntity,String outging){
		ProcessInstance  pi = this.executionService.createProcessInstanceQuery().processInstanceId(jBPMWorkEntity.getProcessInstanceId()).uniqueResult();
		if(pi!=null){
			this.executionService.signalExecutionById(jBPMWorkEntity.getProcessInstanceId(),outging);
			this.recordTaskTrajectoryAndUpdateStatus(jBPMWorkEntity,outging,"");
		}else{
			throw new RuntimeException("流程已结束！");
		}
	}

	
	/**
	* @Title: setExecutionVariable
	* @Description: 设置流程变量
	* @param @param executionId
	* @param @param name
	* @param @param value 设定文件
	* @return void 返回类型
	* @throws
	*/
	public void setExecutionVariable(String executionId,String name,String value){
		this.executionService.setVariable(executionId, name, value);
	}
	
	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public ExecutionService getExecutionService() {
		return executionService;
	}

	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
