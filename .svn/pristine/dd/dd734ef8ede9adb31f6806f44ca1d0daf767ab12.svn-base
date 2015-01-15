<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			columns : [ [ {field : 'service_id',title : '服务单据编号',align:'center',width : 30
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
			columns : [ [  {field : 'rewardAmount',title : '奖励金额',editor:'text',align:'center',width : 30,
			}, {field : 'rewardQuantity',title : '奖励数量',editor:'text',align:'center',width : 30
			}, {field : 'punishAmount',title : '处罚金额',editor:'text',align:'center',width : 30
			}, {field : 'punishQuantity',title : '处罚数量',editor:'text',align:'center',width : 30
			}, {field : 'otherFee',title : '其他调整金额',editor:'text',align:'center',width : 30
			}, {field : 'otherAmount',title : '其他调整数量',editor:'text',align:'center',width : 30
			}] ],
			toolbar : '#toolbarForOther',
		});
		$("div.easyui-layout").css("height", "auto");
	});
	
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
			<td align="right">服务网点</td>
			<td align="center" >
				<input value="${es.serviceUnit }" disabled="disabled" type="text" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getWebsiteCombobox',required:true">
			</td>
			<td align="right">分部</td>
			<td align="center" >
				<input value="${es.parentOrganization }" disabled="disabled" type="text" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getECCombobox',required:true">
			</td>
		</tr>
		<tr>
			<td align="right">结算费用</td><td align="center" ><label>${es.settlementFee }</label></td>
			<td align="right">结算系数</td><td align="center" ><label>${es.settlementFactor }</label></td>
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
			<td align="left" ><td align="center" ><label>${es.comment }</label></td>
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

