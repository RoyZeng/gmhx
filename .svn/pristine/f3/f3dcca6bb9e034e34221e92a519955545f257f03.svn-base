package com.gome.gmhx.jbpm;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: TaskTrajectory
 * @Description: 工作流任务轨迹
 * @author liuchao
 * @date 2014年10月11日 上午10:18:25
 * 
 */
public class JbpmTaskTrajectory {

	private String pkId;// 主键
	
	private String applicant;//申请者
	
	private Date applyTime;//申请时间
	
	private String currentActivity;//当前状态

	private String participant;// 参与者

	private String workEntityId;// 实体主键

	private String processInstanceId;// 流程实例id

	private String processDefinitionKey;// 流程定义key

	private String outGoingName;// 决策
	
	private String approveComment;//审批说明
	
	private Date createDate;// 创建时间
	
	private String nextActivityName;//下一级任务
	
	private String nextParticipants;//下一级任务参与者
	
	private Date startTime;//任务开始时间
	
	private Date endTime;//任务结束时间
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public JbpmTaskTrajectory(){
		this.pkId = UUID.randomUUID().toString();
	}
	
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getCurrentActivity() {
		return currentActivity;
	}
	public void setCurrentActivity(String currentActivity) {
		this.currentActivity = currentActivity;
	}

	public String getNextActivityName() {
		return nextActivityName;
	}
	public void setNextActivityName(String nextActivityName) {
		this.nextActivityName = nextActivityName;
	}
	public String getNextParticipants() {
		return nextParticipants;
	}
	public void setNextParticipants(String nextParticipants) {
		this.nextParticipants = nextParticipants;
	}
	public String getPkId() {
		return pkId;
	}
	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getWorkEntityId() {
		return workEntityId;
	}

	public void setWorkEntityId(String workEntityId) {
		this.workEntityId = workEntityId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getOutGoingName() {
		return outGoingName;
	}

	public void setOutGoingName(String outGoingName) {
		this.outGoingName = outGoingName;
	}
	public void cpi(String outGoingName) {
		this.outGoingName = outGoingName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getApproveComment() {
		return approveComment;
	}

	public void setApproveComment(String approveComment) {
		this.approveComment = approveComment;
	}

}
