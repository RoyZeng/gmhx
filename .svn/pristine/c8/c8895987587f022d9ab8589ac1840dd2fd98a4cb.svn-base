<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});

	$(function() {
		dataGrid = $('#detailGrid')
				.datagrid(
						{
							title : "物料明细",
							url : "${ctx}/ParcelDelivery/getParcelDeliveryDetail/${parcelCode}",
							nowrap : false,
							striped : true,
							height : document.body.clientHeight,
							collapsible : true,
							autoRowHeight : false,
							remoteSort : false,
							rownumbers : true,
							fitColumns : true,
							pagination : true,
							checkOnSelect : false,
							singleSelect : true,
							selectOnCheck : false,
							columns : [ [ {
								field : 'parcel_code',
								hidden : 'true'
							}, {
								field : 'list_number',
								title : '申请单号',
								width : 10,
								align : 'center'
							}, {
								field : 'type',
								title : '单据类型',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'send_number',
								title : '发货单号',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'fitting_code',
								title : '配件编码',
								width : 10,
								align : 'center',

							}, {
								field : 'fitting_name',
								title : '备件名称',
								width : 10,
								align : 'center'
							}, {
								field : 'spec',
								title : '规格',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'audit_count',
								title : '数量',
								width : 10,
								align : 'center',
								sortable : true
							} ] ],
							toolbar : '#toolbar',
							onLoadSuccess : function() {
								$('#searchForm table').show();
								$(this).datagrid('doCellTip', {
									'max-width' : '300px',
									'delay' : 500
								});
								parent.$.messager.progress('close');
							}
						});
	});
	
	function submitForm() {
		$.post("${ctx}/hxParcelReceipt/updateHxParcelReceipt/", $("#form")
				.serialize(), function(returnObj) {
			returnObj = $.parseJSON(returnObj);
			if (returnObj.flag == "success") {
				parent.$.messager.alert('提示','收货成功!',null,function(){
					window.location.href="${ctx}/hxParcelReceipt/hxParcelReceiptView";
            	});
			} else {
				$.messager.alert('提示:', '收货失败!');
			}
		});
	}

	function goBack() {
		window.location.href = "${ctx}/hxParcelReceipt/hxParcelReceiptView";
	}
</script>
<div class="easyui-panel" title="邮包信息" style="width: auto">
	<div style="padding: 10px 0 10px 60px">
		<form id="form" method="post">
			<input id="orgId" type="hidden" name="orgId" value="${hxParcelReceipt.orgId}" /> <input id="number" type="hidden" name="number" value="${hxParcelReceipt.number}" />
			<input id="receiveUnit" type="hidden" name="receiveUnit" value="${hxParcelReceipt.receiveUnit}" />
			<input id="fittingCode" type="hidden" name="fittingCode"value="${hxParcelReceipt.fittingCode}" />
			<input id="parcelCode" type="hidden" name="parcelCode" value="${hxParcelReceipt.parcelCode}">
			<table class="table table-hover table-condensed" width="100%">
				<tr>
					<td>邮包编号</td>
					<td><label>${hxParcelReceipt.parcelCode}</label></td>
					<td>货运单号</td>
					<td><label>${hxParcelReceipt.billsCode}</label></td>
				</tr>
				<tr>
					<td>发货单位</td>
					<td><label>${hxParcelReceipt.sendUnit}</label></td>
					<td>收货单位</td>
					<td><label>${hxParcelReceipt.receiveUnit}</label></td>
				</tr>
				<tr>
					<td>发运时间</td>
					<td><label><fmt:formatDate
								value="${hxParcelReceipt.sendDate}"
								pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
					<td>发货方式</td>
					<td><label>${hxParcelReceipt.deliveryWay}</label></td>
				</tr>
				<tr>
					<td>收货时间</td>
					<td><label><fmt:formatDate
								value="${hxParcelReceipt.receiveDate}"
								pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
					<td>承运单位</td>
					<td><label>${hxParcelReceipt.carrierUnit}</label></td>
				</tr>
				<tr>
					<td>运输费用</td>
					<td><label>${hxParcelReceipt.trantransportationExpenses}</label></td>
					<td>物料件数</td>
					<td><label>${hxParcelReceipt.packageNumber}</label></td>
				</tr>
				<tr>
					<td>重量</td>
					<td><label>${hxParcelReceipt.packageWeight}</label></td>
					<td>体积</td>
					<td><label>${hxParcelReceipt.packageVolume}</label></td>
				</tr>
				<tr>
					<td>体积重量</td>
					<td><label>${hxParcelReceipt.volumeWeight}</label></td>
					<td>收货联系人</td>
					<td><label>${hxParcelReceipt.receiveName}</label></td>
				</tr>
				<tr>
					<td>联系电话</td>
					<td><label>${hxParcelReceipt.receivePhone}</label></td>
					<td>收货邮编</td>
					<td><label>${hxParcelReceipt.receivePost}</label></td>
				</tr>
				<tr>
					<td>收货地址</td>
					<td><label>${hxParcelReceipt.receiveAddress}</label></td>
					<td>状态</td>
					<td><label>${hxParcelReceipt.state}</label></td>
				</tr>
				<tr>
					<td>备注</td>
					<td><label>${hxParcelReceipt.note}</label></td>
					<td></td>
					<td><label></label></td>
				</tr>
				<tr>
					<td>创建人</td>
					<td><label>${hxParcelReceipt.founders}</label></td>
					<td>创建时间</td>
					<td><label><fmt:formatDate
								value="${hxParcelReceipt.founderDate}"
								pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
				<tr>
					<td>修改人</td>
					<td><label>${hxParcelReceipt.modefieds}</label></td>
					<td>修改时间</td>
					<td><label><fmt:formatDate
								value="${hxParcelReceipt.modefiedDate}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align: center; padding: 5px"  class="easyui-panel">
		<a href="#" class="easyui-linkbutton" id="receipt" onclick="submitForm();">收货</a>
		 <a href="#" class="easyui-linkbutton" onclick="goBack();">返回</a>
	</div>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="detailGrid"></table>   
	</div>
</div>
