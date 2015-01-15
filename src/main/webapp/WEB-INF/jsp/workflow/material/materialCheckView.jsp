<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<script type="text/javascript">
$(function() {
	$('#dataGrid').datagrid({
		title : "配件明细",
		url : "${ctx}/hxMaterial/getStock/${m.listNumber}",
		showFooter: true,
		striped : true,
		collapsible : true,
		autoRowHeight : false,
		remoteSort : false,
		rownumbers : true,
		fitColumns : true,
		columns : [ [{
			field : 'suitModel',
			title : '适应机型',
			align:'center',
			width : 300
		},{
			field : 'fittingCode',
			title : '配件编码',
			align:'center',
			width : 300,
			editor:{
				type:'combobox',
				options:{
					//url:'${ctx}/hxMaterial/getFittingBySuit/'+$(this).suit_model,
					onSelect:function(value){
			            var currentRowNo = parseInt($(this).parents('tr[id^=datagrid-row-r1-2-]').attr("id").replace("datagrid-row-r1-2-",""));
			            $.post("${ctx}/hxMaterial/getFittingInfo/" + value.text + "?authId=" + "${m.type}",{
			            },function(msg){
			            	var obj = $.parseJSON(msg);
			            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=fittingName]');").children("div").text(obj.fittingName);
			            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=spec]');").children("div").text(obj.spec);
			            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=price]');").children("div").text(obj.price);
			            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=stock]');").children("div").text(obj.stock);
			            });
					}
				}
			}
		}, {
			field : 'fittingName',
			title : '配件名称',
			align : 'center',
			width : 300
		}, 
		{
			field : 'spec',
			title : '规格',
			align : 'center',
			styler: cellStyler,
			width : 300
		}, {
			field : 'price',
			title : '单价',
			align : 'center',
			styler: cellStyler,
			width : 150
		}, {
			field : 'applyCount',
			title : '申请',
			align:'center',
			width : 150
		},{
			field : 'auditCount',
			title : '审批',
			align:'center',
			width : 200,
			editor:'numberbox'
		}, {
			field : 'receiveStock',
			title : '申请单位库存',
			align : 'center',
			width : 150,
			formatter : function(value, row, index) {
				if(value==undefined){
					return "0";
				}else{
					return value;
				}
			}
		}, {
			field : 'sendStock',
			title : '发货单位库存',
			align : 'center',
			width : 150,
			formatter : function(value, row, index) {
				if(value==undefined){
					return "0";
				}else{
					return value;
				}
			}
		},{
			field : 'comment',
			title : '备注',
			align:'center',
			width : 300
		} ] ],
		onLoadSuccess : function(data){
			$('#searchForm table').show();
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
			parent.$.messager.progress('close');
			
			indexMaterial = data.total;
			for(var i = 0; i < indexMaterial; i++){
				$('#dataGrid').datagrid('beginEdit', i);
				var partsCode = $('#dataGrid').datagrid('getEditor', {index:i,field:'fittingCode'});
				var s = $("#dataGrid").datagrid('getRows')[i]['suitModel'];
				var auditCount = $('#dataGrid').datagrid('getEditor', {index:i,field:'auditCount'});
				$(auditCount.target).numberbox('setValue',data.rows[0].applyCount);
	            var url = '${ctx}/hxMaterial/getFittingBySuit?suitModel=' + window.encodeURIComponent(s);
				$(partsCode.target).combobox('reload', window.encodeURI(url));
			}
		}
	});
	
	
	$("div.datagrid-wrap").css("height","auto");
	
	function cellStyler(value,row,index){
		if(row.spec=="总计（元）："){
			return 'background-color:#ffee00;color:red;';
		}
	}
});
</script>

<div>
<form id="form" method="post">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
		详细查看${title}
	</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td width="25%" align="center">单据编号</td>
			<td width="25%">${m.listNumber}</td>
			<td width="25%" align="center">单据类型</td>
			<td width="25%">${title}</td>
		</tr>
		<c:if test="${m.type=='fb-kc-zy'}">
			<tr>
				<td width="25%" align="center">良品,残品转移方向</td>
				<c:choose>
				   <c:when test="${m.moveDirection =='0'}"> 
				   			<td width="25%">良品转残品</td>
				   </c:when>
				    <c:when test="${m.moveDirection =='1'}"> 
				   			<td width="25%">残品转良品</td>
				   </c:when>
				 </c:choose>
			</tr>
		</c:if>
		<tr>
			<td width="25%" align="center">发货单位</td>
			<td width="25%"><input id="sendCompany" class="easyui-combobox" name="sendCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox?value=${m.sendCompany}'" disabled="disabled"/></td>
			<td width="25%" align="center">收货单位</td>
			<td width="25%"><input id="receiveCompany" class="easyui-combobox" name="receiveCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox?value=${m.receiveCompany}'" disabled="disabled"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">状态</td>
			<td width="25%"><input class="easyui-combobox" name="sendCompany" data-options="url:'${ctx}/hxCode/getStatusCombobox?status=${m.status}'" disabled="disabled"/></td>
			<td width="25%" align="center"></td>
			<td width="25%"></td>
		</tr>
		<tr>
			<td width="25%" align="center">申请人</td>
			<td width="25%">${m.applicant}</td>
			<td width="25%" align="center">申请日期</td>
			<td width="25%"><fmt:formatDate value="${m.applyDate}" pattern="yyyy-MM-dd"/></td>
		</tr>
			<c:if test="${type=='wd-yjjh-sq'}">
		<tr>
			<td width="25%" align="center">客户姓名</td>
			<td width="25%">${m.customerName}</td>
			<td width="25%" align="center">客户电话</td>
			<td width="25%">${m.customerTel}</td>
		</tr>
		<tr>
			<td width="25%" align="center">地址</td>
			<td width="25%" colspan="3">${m.address}</td>
		</tr>
			</c:if>
		<tr>
			<td width="25%" align="center">备注</td>
			<td width="25%" colspan="3">${m.comment}</td>
		</tr>
		<tr>
			<td width="25%" align="center">创建人</td>
			<td width="25%">${m.createPerson}</td>
			<td width="25%" align="center">创建时间</td>
			<td width="25%"><fmt:formatDate value="${m.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
		</tr>
		<tr>
			<td width="25%" align="center">修改人</td>
			<td width="25%">${m.updatePerson}</td>
			<td width="25%" align="center">修改时间</td>
			<td width="25%"><fmt:formatDate value="${m.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
	</table>
</form>
</div>
<div class="easyui-layout">
	<table id="dataGrid"></table>
</div>
