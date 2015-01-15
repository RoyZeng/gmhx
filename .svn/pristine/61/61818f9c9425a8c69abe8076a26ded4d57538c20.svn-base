<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		title : "新建邮包",
		url : "${ctx}/ParcelDelivery/getApplayCodeList", 
		nowrap : false,
		striped : true,
		height : document.body.clientHeight ,
		collapsible : true,
		autoRowHeight : false,
		remoteSort : false,
		rownumbers : true,
		fitColumns : true,
		pagination : true,
		checkOnSelect : false,
		singleSelect : true,
		selectOnCheck : false,
		queryParams : {
			currentPage : 1,
			pageCount : 10
		},
		columns : [ [ 
        {
		field : 'list_number',
		title : '申请单号',
		align:'center',
		width : 25
     	},{
		field : 'send_number',
		title : '发货单号',
		align:'center',
		width : 25
		 },
		{
		field : 'apply_date',
		title : '出单日期',
		align:'center',
		width : 25
		},{
		field : 'comment',
		title : '备注',
		align:'center',
		editor:'text',
		width : 25
		},{
			field : 'action',
			title : '操作',
			width : 20,
			align:'center',
			formatter : function(value, row, index) {
				return '<a href="#" onclick="show(\'' + row.parcel_code + '\',\'' + row.bills_code + '\');">查看</a> ';
			
		} }]],
		toolbar : '#toolbar',
		onLoadSuccess : function() {
			$('#searchForm table').show();
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
			parent.$.messager.progress('close');
		}
	  });
	
	$.serializeObject = function(form) {
		var o = {
			currentPage : 1,
			pageCount : 10
		};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	};
});
function Update(){
	 var parcelCode = $("#parcelCode").val();
 	window.location.href="${ctx}/ParcelDelivery/updateView/" + parcelCode;
	
}
 

function send(){
		$.post("${ctx}/ParcelDelivery/sendViewShow",{
			parcelCode : $("#parcelCode").val()
		},function(msg){
			if(msg == "success"){
				window.location.href="${ctx}/ParcelDelivery/SendParcelView?parcelCode=" + $("#parcelCode").val();
			}else{
				parent.$.messager.alert('','发送失败!');
			}
		});
	}

 function goBack(){
	
	window.location.href="${ctx}/ParcelDelivery/ParcelDeliveryView";
    }
</script>
<div class="easyui-panel" title="详细查看邮包" style="width:auto">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<input id="parcelCode" type="hidden" name="parcelCode" value="${parcel.parcelCode}"/>
			<input id="packageNumber" type="hidden" name="packageNumber" value="${parcel.packageNumber}"/>
			<input id="receiveUnit" type="hidden" name="receiveUnit" value="${parcel.receiveUnit}"/>
			<input id="orgId" type="hidden" name="orgId" value="${parcel.orgId}"/>
			<table class="table table-hover table-condensed" width="100%">
				<tr>
					<td>邮包编号</td>
					<td><label>${parcel.parcelCode}</label></td>
					<td>货运单号</td>
					<td><label>${parcel.billsCode}</label></td>
					<td>发货单位</td>
					<td><label>${parcel.sendUnit}</label></td>
				</tr>
				<tr>
					<td>收货单位</td>
					<td><label>${parcel.receiveUnit}</label></td>
					<td>发运时间</td>
					<td><label><fmt:formatDate value="${parcel.sendDate}"
								pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
					<td>发货方式</td>
					<td><label>${parcel.deliveryWay}</label></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td>收货时间</td>
					<td><label><fmt:formatDate value="${parcel.receiveDate}"
								pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
					<td>承运单位</td>
					<td><label>${parcel.carrierUnit}</label></td>
					<td>运输费用</td>
					<td><label>${parcel.trantransportationExpenses}</label></td>
				</tr>
				<tr>
					<td>物料件数</td>
					<td><label>${parcel.packageNumber}</label></td>
					<td>重量</td>
					<td><label>${parcel.packageWeight}</label></td>
					<td>体积</td>
					<td><label>${parcel.packageVolume}</label></td>
				</tr>
				<tr>
					
				</tr>
				<tr>
					<td>体积重量</td>
					<td><label>${parcel.volumeWeight}</label></td>
					<td>收货联系人</td>
					<td><label>${parcel.orgManager}</label></td>
					<td>联系电话</td>
					<td><label>${parcel.orgReference}</label></td>
				</tr>
				<tr>
					<td>收货邮编</td>
					<td><label>${parcel.orgPostCode}</label></td>
					<td>收货地址</td>
					<td><label>${parcel.orgAddress}</label></td>
					<td>状态</td>
					<td><label>${parcel.state}</label></td>
				</tr>
				<tr>
					<td>备注</td>
					<td><label>${parcel.note}</label></td>
					<td></td>
					<td><label></label></td>
					<td></td>
					<td><label></label></td>
				</tr>
				<tr>
					<td>创建人</td>
					<td><label>${parcel.founders}</label></td>
					<td>创建时间</td>
					<td><label><fmt:formatDate value="${parcel.founderDate}"
								pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
								<td></td>
					<td><label></label></td>
				</tr>
				<tr>
					<td>修改人</td>
					<td><label>${parcel.modefieds}</label></td>
					<td>修改时间</td>
					<td><label><fmt:formatDate value="${parcel.modefiedDate}"
								pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
								<td></td>
					<td><label></label></td>
				</tr>
			</table>
		</form>
	</div>
	<div align="right" class="easyui-panel" style="padding:5px;">
	<c:if test="${isEdit=='true'}">
		<c:if test="${isSend=='true'}">
		<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g2',plain:true,iconCls:'icon-redo'" onclick="send();">发送</a>
		</c:if>
		<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g2',plain:true,iconCls:'icon-edit'" onclick="Update();">修改</a>
	</c:if>
	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g2',plain:true,iconCls:'icon-back'" onclick="goBack();">返回</a>
</div>

	<div id="main-center" class="easyui-tabs" style="width:auto;height:auto;"> 
			<div data-options="region:'center',border:false">
				<table id="dataGrid"></table>
			</div>
		</div> 
	</div>