<%@ page  pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
	
	$('#traildataGrid').datagrid({
		title : "任务轨迹列表",
		url : "${ctx}/hxWorkFlow/getTaskTrajeCttoryPageList/${order.listNumber}",
		height : document.documentElement.clientHeight * 0.3,
		striped : true,
		collapsible : true,
		autoRowHeight : false,
		singleSelect : true,
		remoteSort : false,
		rownumbers : true,
		fitColumns : true,
		showFooter: true,
		columns : [ [ {
			field : 'applicant',
			title : '申请人',
			align:'center',
			width : 300,
			formatter:function(value, row, index){ 
             	return changeToName(row.applicant,1);
            }
		}, {
			field : 'applyTime',
			title : '申请时间',
			align : 'center',
			width : 300
		}, {
			field : 'currentActivity',
			title : '当前状态',
			align : 'center',
			width : 300,
			formatter:function(value, row, index){ 
             	return statusVal(row.currentActivity);
            }
		}, {
			field : 'participant',
			title : '参与者',
			align : 'center',
			width : 300,
			formatter:function(value, row, index){ 
             	return changeToName(row.participant,1);
            }
		}, {
			field : 'approveComment',
			title : '审批说明',
			align:'center',
			width : 300
		},{
			field : 'startTime',
			title : '任务开始时间',
			align:'center',
			width : 300
		},{
			field : 'endTime',
			title : '任务结束时间',
			align:'center',
			width : 300
		}, {
			field : 'nextActivityName',
			title : '下一级任务',
			align:'center',
			width : 300,
			formatter:function(value, row, index){ 
             	return statusVal(row.nextActivityName);
            }
		}, {
			field : 'nextParticipants',
			title : '下一级任务参与者',
			align:'center',
			width : 300,
			formatter:function(value, row, index){ 
             	return changeToName(row.nextParticipants,2);
            }
		} ] ]
	});
});

function statusVal(status) {
	if (status == 'S0') {
		return '流程结束';
	} else if (status == 'S1') {
		return '暂存';
	} else if (status == 'S2') {
		return '提交';
	} else if (status == 'S3') {
		return '分部审核';
	} else if (status == 'S4') {
		return '总部审核';
	} else if (status == 'S5') {
		return '填写出库数量';
	} else if (status == 'S6') {
		return '出库';
	} else if (status == 'S7') {
		return '邮包发货';
	} else if (status == 'S8') {
		return '邮包收货';
	} else if (status == 'S9') {
		return '网店确认';
	}else if(status=='S10'){
		return "退回修改";
	}else if(status=='S11'){
		return "发货";
	}else if(status=='S12'){
		return "收货";
	}else if(status=='S13'){
		return "检测";
	}else if(status=='S14'){
		return "入库";
	}else if(status=='S15'){
		return "移库";
	}
}

function changeToName(applicant,type) {
	var result="";
	$.ajax({
         url: ctx + "/hxWorkFlow/changeToName",
         data: {value:applicant, type:type},
         async:false,
         success: function(data){
        	 result=data;
          }
     });
	 return result;
}
</script>

<div class="easyui-layout">
	<table id="traildataGrid"></table>
</div>

