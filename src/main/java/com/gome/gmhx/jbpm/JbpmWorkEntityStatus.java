package com.gome.gmhx.jbpm;

/**
* @ClassName: JBPMActivities
* @Description:JBPM业务实体的状态（流程的每一个任务节点对应一个状态。任务节点名即状态名）
* @author liuchao
* @date 2014年8月27日 下午4:57:37
*/
public interface JbpmWorkEntityStatus {
	
	public final String status_end = "S0";//流程结束
	
	public final String status_unsubmit = "S1";//暂存
	
	public final String status_submit = "S2";//提交
	
	public final String status_subDept_review = "S3";//分部审核
	
	public final String status_headDept_review = "S4";//总部审核
	
	public final String status_write_amount = "S5";//填写出库数量
	
	public final String status_out_bound  = "S6";//出库
	
	public final String status_post_sending = "S7";//邮包发货
	
	public final String status_post_receipt = "S8";//邮包收货
	
	public final String status_website_confirm = "S9";//网店确认
	
	public final String status_return_modify = "S10";//退回修改
	
	public final String status_send = "S11";//发货
	
	public final String status_receive = "S12";//收货
	
	public final String status_check = "S13";//检测
	
	public final String status_in_bound = "S14";//入库
	
	public final String status_move_bound = "S15";//移库
	

}
