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
					title : "浏览提单号信息",
					//url : "${ctx}/hxBillNumImportQuery/getHxBillNumPageList",
					url : "${ctx}/SaleData/getJlSaleDataPageList",
					nowrap : false,
					striped : true,
					height : document.body.clientHeight - 210,
					collapsible : true,
					autoRowHeight : false,
					remoteSort : false,
					//rownumbers : true,
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
								field : 'gsmc',
								title : '公司名称',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'thdh',
								title : '提货单号',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'khmc',
								title : '顾客姓名',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'spmc',
								title : '商品名称',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'xsje',
								title : '销售金额',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'fph',
								title : '发票号',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'jzrq',
								title : '销售日期',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'bmmc',
								title : '购自门店',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'yyymc',
								title : '营业员',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'zpbj',
								title : '赠品信息',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'spflmc',
								title : '品类',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'ppbmc',
								title : '品牌',
								width : 10,
								align : 'center',
								sortable : true
							}, {
								field : 'ds',
								title : 'POS标识',
								width : 10,
								align : 'center',
								sortable : true
							},{
								field : 'ts',
								title : '迁移时间',
								width : 10,
								align : 'center',
								sortable : true
							},{
								field : 'operation',
								title : '操作',
								width : 10,
								align : 'center',
								formatter : function(value, row, index) {
									return '<a href="#" onclick="show(\'' + row.gsxx01 + '\', \''+ row.thdh + '\', \'' + row.xslx + '\');"> 查看 </a>';
								}
							} ] ],
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
		//window.location.href = "${ctx}/hxBillNumImportQuery/importView";
		parent.$.modalDialog({
			title : '提单号数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=saledata&actionName=importJlSaleData',
			buttons : [ 
			{
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	}

	function show(gsxx01, thdh, xslx) {
		window.location.href = "${ctx}/SaleData/showView?gsxx01="+ gsxx01+"&thdh="+thdh+"&xslx="+xslx;
	}
	function exportExcel() {
		var opts = dataGrid.datagrid('getColumnFields');
		var fieldArray = new Array();
		var titleArray = new Array();
		for (var i = 0; i < opts.length -1; i++) {
			var column = dataGrid.datagrid('getColumnOption', opts[i]);
			fieldArray.push(column.field);
			titleArray.push(column.title);
		}
		window.location.href = "${ctx}/SaleData/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 250px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%"></td>
						<td width="10%">公司名称</td>
						<td width="5%"><input name="gsmc" placeholder="输入查询条件" class="span2" /></td>
						<td width="10%"></td>
						<td width="10%">提货单号</td>
						<td width="5%"><input name="thdh" placeholder="输入查询条件" class="span2" /></td>
						<td width="10%"></td>
						<td width="10%">POS标识</td>
						<td width="5%"><input name="ds" placeholder="输入查询条件" class="span2" /></td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="10%"></td>
						<td width="10%">顾客姓名</td>
						<td width="5%"><input name="khmc" placeholder="输入查询条件" class="span2" /></td>
						<td width="10%"></td>
						<td width="10%">商品名称</td>
						<td width="5%"><input name="spmc" placeholder="输入查询条件" class="span2" /></td>
						<td width="10%"></td>
						<td width="10%">销售金额</td>
						<td width="5%"><input name="xsje" placeholder="输入查询条件" class="span2" /></td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="10%"></td>
						<td width="10%">发票号</td>
						<td width="5%"><input name="fph" placeholder="输入查询条件" class="span2" /></td>
						<td width="10%"></td>
						<td width="10%">销售日期</td>
						<td width="25%">
							<input id="startDate" placeholder="选择起始日期" class="Wdate" name="startDate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')}'})"/>至
							<input id="endDate" placeholder="选择结束日期" class="Wdate" name="endDate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}'})"/> 
						</td>
						<td width="10%"></td>
						<td width="10%">购自门店</td>
						<td width="5%"><input name="bmmc" placeholder="输入查询条件" class="span2" /></td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="10%"></td>
						<td width="10%">营业员</td>
						<td width="5%"><input name="yyymc" placeholder="输入查询条件" class="span2" /></td>
						<td width="10%"></td>
						<td width="10%">赠品信息</td>
						<td width="5%"><input name="zpbj" placeholder="输入查询条件" class="span2" /></td>
						<td width="10%"></td>
						<td width="10%">品类</td>
						<td width="5%"><input name="spflmc" placeholder="输入查询条件" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/thjlx'"/></td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="10%"></td>
						<td width="10%">品牌</td>
						<td width="5%"><input name="ppbmc" placeholder="输入查询条件" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/pp'"/></td>
						<td width="10%"></td>
						<td width="10%">迁移日期</td>
						<td width="25%">
							<input id="starttsDate" placeholder="选择起始日期" class="Wdate" name="starttsDate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'starttsDate\')}'})"/>至
							<input id="endtsDate" placeholder="选择结束日期" class="Wdate" name="endtsDate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'endtsDate\')}'})"/> 
						</td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="5%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
			<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="importExcel();">导入</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	</div>
</body>
</html>














