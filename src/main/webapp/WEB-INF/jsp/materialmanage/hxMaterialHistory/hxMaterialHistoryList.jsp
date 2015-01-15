<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
		var dataGrid;
		$(function() {
			dataGrid = $('#dataGrid').datagrid({
				title : "配件申请历史",
				url : "${ctx}/hxMaterialHistory/getHxMaterialHistoryPageList",
				striped : true,
				collapsible : true,
				autoRowHeight : false,
				remoteSort : false,
				rownumbers : true,
				fitColumns : true,
				pagination : true,
				singleSelect : true,
				height : document.documentElement.clientHeight * 0.84,
				queryParams : {
					pageNo : 1,
					pageCount : 20
				},
				columns : [ [ {
					field : 'action',
					title : '操作',
					align:'center',
					width : 10,
					formatter : function(value, row, index) {                                                                                                                                                     
						return '<a href="#" onclick="view(\'' + row.list_number + '\', \'' + row.type_hidden + '\');">查看</a>';
					}
				}, {
					field : 'type_hidden',
					hidden : true
				},{
					field : 'list_number',
					title : '单据编号',
					align:'center',
					width : 20
				}, {
					field : 'send_company',
					title : '发货单位',
					align:'center',
					width : 30
				}, {
					field : 'receive_company',
					title : '收货单位',
					align:'center',
					width : 30
				}, {
					field : 'STATUS',
					title : '状态',
					align:'center',
					width : 10
				}, {
					field : 'type',
					title : '单据类型',
					align:'center',
					width : 10
				},{
					field : 'proposer',
					title : '申请人',
					align:'center',
					width : 10
				}, {
					field : 'status_hidden',
					hidden : true
				}, {
					field : 'update_time',
					title : '修改日期',
					align:'center',
					width : 20
				} ] ],
				toolbar : '#toolbar',
				onLoadSuccess : function() {
					$('#searchForm table').show();
					$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
					parent.$.messager.progress('close');
				}
			});
			$('.datagrid-pager').pagination({
				pageSize: 20,//每页显示的记录条数，默认为10 
				onSelectPage : function(pageNumber, pageSize) {
					$(this).pagination('loading');
					var queryParams = $.serializeObject($('#searchForm'));
					queryParams.currentPage = pageNumber;
					queryParams.pageCount = pageSize;
					$('#dataGrid').datagrid("options").queryParams = queryParams;
					$('#dataGrid').datagrid("reload");
					$(this).pagination('loaded');
				}
			});
			$("#query").on("click", function() {
				dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
			});
		});
		$.serializeObject = function(form) {
			var o = {
				pageNo : 1,
				pageCount : 20
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
		
		function update(fittingCode){
			window.location.href = "${ctx}/hxFitting/updateView/" + fittingCode;
		}
		
		function view(fittingCode){
			window.location.href = "${ctx}/hxFitting/viewHxFitting/" + fittingCode;
		}
	
		function refreshDataGrid() {
			$('#dataGrid').datagrid("reload");
			parent.$.modalDialog.handler.dialog('close');	
		}
		
		function view(listNumber, type){
			window.location.href = "${ctx}/hxMaterial/viewHxMaterial?listNumber=" + listNumber + "&type=" + type;
		}
	</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 160px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%">单据类型:</td>
						<td width="5%"><input class="easyui-combobox" name="type" data-options="url:'${ctx}/hxCode/getCombobox/fitting_type',editable:false"/></td>
						<td width="5%"></td>
						<td width="10%">单据编号:</td>
						<td width="5%"><input name="listNumber" placeholder="输入单据编号"/></td>
						<td width="5%"></td>
						<td width="10%">发货单位:</td>
						<td width="5%"><input class="easyui-combobox" name="sendCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td>
						<td width="5%"></td>
						<td width="40%"></td>
					</tr>
					<tr>
						<td width="10%">收货单位:</td>
						<td width="5%"><input class="easyui-combobox" name="receiveCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td>
						<td width="5%"></td>
						<td width="10%">状态:</td>
						<td width="5%"><input class="easyui-combobox" name="status" data-options="url:'${ctx}/hxCode/getCombobox/djzt',editable:false"/></td>
						<td width="5%"></td>
						<td width="10%">申请人:</td>
						<td width="5%"><input name="proposer" placeholder="输入申请人"/></td>
						<td width="5%"></td>
						<td width="40%" align="left"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
					<tr>
						<td width="10%">更新日期:</td>
						<td width="25%">
							<input id="ksrq" placeholder="选择起始日期" class="Wdate" name="ksrq" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>至
							<input id="jsrq" placeholder="选择结束日期" class="Wdate" name="jsrq" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')}'})"/> 
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
