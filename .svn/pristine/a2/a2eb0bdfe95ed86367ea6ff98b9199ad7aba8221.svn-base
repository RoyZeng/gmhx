<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		$(function() {
			parent.$.messager.progress('close');
		});
		
		$('#settlementFeeDetailGrid').datagrid({
			url : "${ctx}/hxExamineSettlement/getSettlementFeeDetailGrid/${es.settlementId}",
			title : "结算费用明细",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [ {field : 'service_id',title : '服务单据编号',align:'center',width : 30,
				formatter:function(value, row, index) {
					if('${es.settlementType}'=="安装"){
						return '<a href="#" onclick="showInstallServiceDetail(\'' + row.service_id + '\');">'+row.service_id+'</a>';
					}else{
						return '<a href="#" onclick="showRepairServiceDetail(\'' + row.service_id + '\');">'+row.service_id+'</a>';
					}
					
				}
			}, {field : 'service_type',title : '服务单类型',align:'center',width : 30
			}, {field : 'category',title : '品类',align:'center',width : 30
			}, {field : 'brand',title : '品牌',align:'center',width : 30
			}, {field : 'machine_type',title : '机型',align:'center',width : 30
			}, {field : 'recall_type',title : '回访类型',align:'center',width : 30
			}, {field : 'service_fee',title : '服务单费用',align:'center',width : 30
			}, {field : 'settlement_fee',title : '结算费用',align:'center',width : 30
			}, {field : 'examine_fee',title : '审核费用',align:'center',width : 30
			}, {field : 'management_fee',title : '管理费',align:'center',width : 30
			}, {field : 'comment',title : '备注',align:'center',width : 30
			}, {field : 'action',title : '操作',align:'center',width : 30
			} ] ],
			toolbar : '#toolbarForSettlement'
		});
		
		$('#otherFeeGrid').datagrid({
			url : "${ctx}/hxExamineSettlement/getOtherFeeGrid/${es.settlementId}",
			title : "其它费用明细",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [ {
				width : 10 , checkbox : true
			}, {field : 'rewardAmount',title : '奖励金额',editor:'text',align:'center',width : 30,
			}, {field : 'rewardQuantity',title : '奖励数量',editor:'text',align:'center',width : 30
			}, {field : 'punishAmount',title : '处罚金额',editor:'text',align:'center',width : 30
			}, {field : 'punishQuantity',title : '处罚数量',editor:'text',align:'center',width : 30
			}, {field : 'otherFee',title : '其他调整金额',editor:'text',align:'center',width : 30
			}, {field : 'otherAmount',title : '其他调整数量',editor:'text',align:'center',width : 30
			}] ],
			toolbar : '#toolbarForOther',
			onLoadSuccess : function(data){
				indexOther = data.total;
				for(var i = 0; i < indexOther; i++){
					$('#otherFeeGrid').datagrid('beginEdit', i);
				}
			}
		});
		$("div.easyui-layout").css("height", "auto");
	});
	
	var indexOther = 0;
	
	function addForOther(){
		var count = $("#number").val();
		for(var i = 0; i < count; i++){
			$('#otherFeeGrid').datagrid('appendRow',{});
			$('#otherFeeGrid').datagrid('beginEdit', indexOther);
			indexOther ++;
		}
	}
	
	function deleteForOther(){
		var checkedData = $('#otherFeeGrid').datagrid('getRowNum');
		indexOther = indexOther - checkedData.length;
		$.each(checkedData, function(){
			$('#otherFeeGrid').datagrid('deleteRow', checkedData.pop() - 1);
		});
	}
	
	function prepare(){
		$('#otherFeeGrid').datagrid('acceptChanges');
		var others = $("#otherFeeGrid").datagrid('getRows');
		for(var i = 0; i <= others.length - 1; i++){
			if($.trim(others[i].rewardAmount) == ""&&
				$.trim(others[i].rewardQuantity) == ""&&
				$.trim(others[i].punishAmount) == ""&&
				$.trim(others[i].punishQuantity) == ""&&
				$.trim(others[i].otherFee) == ""&&
				$.trim(others[i].otherAmount) == ""){
				others.splice(i, 1);
			}
		}  
		var settlementVO = {};
		settlementVO.examineSettlementTicket =  $.serializeObject($('#formOther'));
		settlementVO.settlementDetailOthers = others;
		return settlementVO;
	}
	
	function save(){
		$.ajax({
            type:"POST", 
            url:"${ctx}/hxExamineSettlement/examineSettlementUpdate", 
            dataType:"json",      
            contentType:"application/json",               
            data:JSON.stringify(prepare()), 
            success:function(data){
            	if(data.flag == "success"){
            		$.messager.alert('', '保存成功!',null,function(){
            			window.location.href = "${ctx}/hxExamineSettlement/examineSettlementShow/" + data.settlementId;  
            		});
            	}else{
            		$.messager.alert('','保存失败!');
            	}
            } 
        });
	}
	
	$.serializeObject = function(form) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	};
	
	function showInstallServiceDetail(serviceId){
		window.location.href="${ctx}/hxExamineSettlement/examineSettlementInstallDetail?serviceId="+ serviceId+"&settlementId=${es.settlementId}";
	}
	
	function showRepairServiceDetail(serviceId){
		window.location.href="${ctx}/hxExamineSettlement/examineSettlementRepairDetail?serviceId="+ serviceId+"&settlementId=${es.settlementId}";
	}
	
	function showOtherDetail(serviceId){
		alert(serviceId);
		//window.location.href="${ctx}/hxExamineSettlement/examineSettlementView";
	}
	
	function goBack(){
		window.location.href="${ctx}/hxExamineSettlement/examineSettlementView";
	}
	
</script>
<form id="formOther" method="post">
	<input type="hidden" name="settlementId" value="${es.settlementId}">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">客户信息</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td width="20%" align="right">结算单号</td><td align="center" width="30%" ><label>${es.settlementId}</label></td>
			<td width="20%" align="right">结算日期</td><td align="center" width="30%" ><label><fmt:formatDate value='${es.settlementTime }' pattern='yyyy-MM-dd'/></label></td>
		</tr>
		<tr>
			<td align="right">服务网点</td><td align="center" ><label>${es.serviceUnit }</label></td>
			<td align="right">分部</td><td align="center" ><label>${es.parentOrganization }</label></td>
		</tr>
		<tr>
			<td align="right">结算费用</td><td align="center" ><label>${es.settlementFee }</label></td>
			<td align="right">结算系数</td>
			<td align="left" >
				<input name="settlementFactor" value="${es.settlementFactor }">
			</td>
		</tr>
		<tr>
			<td align="right">服务费用</td><td align="center" ><label>${es.serviceFee }</label></td>
			<td align="right">奖励费用</td><td align="center" ><label>${es.rewardFee }</label></td>
		</tr>
		<tr>
			<td align="right">奖励数量</td><td align="center" ><label>${es.rewardQuantity }</label></td>
			<td align="right">处罚费用</td><td align="center" ><label>${es.punishFee }</label></td>
		</tr>
		<tr>
			<td align="right">处罚数量</td><td align="center" ><label>${es.punishQuantity }</label></td>
			<td align="right">其他费用</td><td align="center" ><label>${es.otherFee }</label></td>
		</tr>
		<tr>
			<td align="right">其他数量</td><td align="center" ><label>${es.otherQuantity }</label></td>
			<td align="right">假单扣罚倍数</td><td align="center" ><label>${es.fakeDeductFactor }</label></td>
		</tr>
		<tr>
			<td align="right">单据状态</td><td align="center" ><label>${es.settlementStatus }</label></td>
			<td align="right"></td><td align="center" ></td>
		</tr>
		<tr>
			<td align="right">总部审核人</td><td align="center" ><label>${es.headquartersCheckMan }</label></td>
			<td align="right">总部审核时间</td><td align="center" ><label><fmt:formatDate value='${es.headquartersCheckTime }' pattern='yyyy-MM-dd HH:mm:ss'/></label></td>
		</tr>
		<tr>
			<td align="right">结算单位确认人</td><td align="center" ><label>${es.settlementUnitConfirmMan }</label></td>
			<td align="right">结算单位确认时间</td><td align="center" ><label><fmt:formatDate value='${es.settlementUnitConfirmTime }' pattern='yyyy-MM-dd HH:mm:ss'/></label></td>
		</tr>
		<tr>
			<td align="right">结算单位备注</td><td align="center" ><label>${es.settlementUnitComment }</label></td>
			<td align="right"></td><td align="center" ></td>
		</tr>
		<tr>
			<td align="right">备注</td>
			<td align="left" >
				<textarea rows="2" name="comment" cols="60">${es.comment }</textarea>
			</td>
			<td align="right"></td><td align="center" ></td>
		</tr>
		<tr>
			<td align="right">创建人</td><td align="center" ><label>${es.createMan }</label></td>
			<td align="right">创建时间</td><td align="center" ><label><fmt:formatDate value='${es.createTime }' pattern='yyyy-MM-dd HH:mm:ss'/></label></td>
		</tr>
		<tr>
			<td align="right">修改人</td><td align="center" ><label>${es.alterMan }</label></td>
			<td align="right">修改时间</td><td align="center" ><label><fmt:formatDate value='${es.alterTime }' pattern='yyyy-MM-dd HH:mm:ss'/></label></td>
		</tr>
	</table>
</form>
<div style="text-align:right;padding:5px">
	<a href="#" class="easyui-linkbutton"  onclick="save();">保存</a>
	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
</div>
<br/>
<div>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="settlementFeeDetailGrid"></table>
	</div>
	<br/>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="otherFeeGrid"></table>
	</div>
</div>
<div id="toolbarForOther" style="display: none;">
	<input type="text" id="number" style="width: 20px;" value="1">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addForOther();">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save();">保存</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteForOther();">删除</a>
</div>
<%@ include file="../../workflow/workProcessTour.jsp"%>
<br/>
<br/>
<br/>
