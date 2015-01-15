<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid(
				{
					title : "临时条码库存",
					url : "${ctx}/hxBarCodeImportQuery/getHxTempBarCodePageList",
					nowrap : false,
					striped : true,
					height : document.body.clientHeight*0.85,
					collapsible : true,
					autoRowHeight : false,
					remoteSort : false,
					idField : 'barCode',
					rownumbers : true,
					fitColumns : true,
					pagination : true,
					checkOnSelect : false,
					selectOnCheck : false,
					queryParams : {
						currentPage : 1,
						pageCount : 10
					},
					columns : [ [
							{
								field : 'barCode',
								title : '条码',
								width : 10,
								align : 'center',
							}, 
							{
								field : 'hxNum',
								title : '服务单号',
								width : 10,
								align : 'center',
							},
							{
								field : 'jlNum',
								title : '金力单号',
								width : 10,
								align : 'center',
								sortable : true
							},
							 {
								field : 'createTime',
								title : '创建时间',
								width : 10,
								align : 'center',
								sortable : true
							},
							 {
								field : 'updateTime',
								title : '更新时间',
								width : 10,
								align : 'center',
								sortable : true
							}
							] ],
					toolbar : '#toolbar',
					onLoadSuccess : function() {
						$('#searchForm table').show();
						parent.$.messager.progress('close');
					}
				});
		$('.datagrid-pager').pagination({
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

	function refreshDataGrid() {
		$('#dataGrid').datagrid("reload");
		parent.$.modalDialog.handler.dialog('close');
	}

	function importExcel() {
		//window.location.href = "${ctx}/hxBarCodeImportQuery/importView";
		parent.$.modalDialog({
			title : '条形码数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=barcode&actionName=importHxBarCode',
			buttons : [ 
			{
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	}

	function update(userId, userName) {
		window.location.href = "${ctx}/user/updateView/" + barCode;
	}

	function show(userId, userName) {
		window.location.href = "${ctx}/user/showView/" + barCode;
	}
	function exportExcel() {
		var opts = dataGrid.datagrid('getColumnFields');
		var fieldArray = new Array();
		var titleArray = new Array();
		for (var i = 0; i < opts.length ; i++) {
			var column = dataGrid.datagrid('getColumnOption', opts[i]);
			fieldArray.push(column.field);
			titleArray.push(column.title);
		}
		window.location.href = "${ctx}/serviceTicketQuery/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width: 100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="25%"></td>
						<td width="5%">创建日期:</td>
						<td width="25%">
							<input id="ksrq" placeholder="选择起始日期" class="Wdate" name="ksrq" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>至
							<input id="jsrq" placeholder="选择结束日期" class="Wdate" name="jsrq" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')}'})"/> 
						</td>
						<td width="35%" align="left"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>














