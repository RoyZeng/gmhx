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
				title : "代码表管理",
				url : "${ctx}/hxCode/getHxCodePageList",
				nowrap : false,
				striped : true,
				collapsible : true,
				autoRowHeight : false,
				remoteSort : false,
				idField : 'codeId',
				rownumbers : true,
				fitColumns : true,
				pagination : true,
				singleSelect : true,
				height : document.body.clientHeight,
				queryParams : {
					pageNo : 1,
					pageCount : 20
				},
				columns : [ [ {
					field : 'codeId',
					title : '代码Id',
					hidden :true
				}, {
					field : 'codeName',
					title : '代码表名称',
					align:'center',
					width : 3
				},{
					field : 'action',
					title : '操作',
					align:'center',
					width : 1,
					formatter : function(value, row, index) {
						return '<a href="#" onclick="update(\'' + row.codeId + '\',\'' + row.codeName + '\');">修改</a>';
					}
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
	
		function refreshDataGrid() {
			$('#dataGrid').datagrid("reload");
			parent.$.modalDialog.handler.dialog('close');	
		}	
		
		function update(codeId, codeName){
			window.location.href = "${ctx}/hxCode/updateView?codeId=" + codeId + "&codeName=" + encodeURI(encodeURI(codeName));
		}
	</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="dataGrid"></table>
	</div>
</body>
</html>
