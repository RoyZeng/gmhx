<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			title : "浏览安装结算标准资料",
			url : "${ctx}/StandardSetupFree/getSetupFreePageList", 
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 70,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'freeCode',
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
				field : 'model1',
				title : '型号',
				width : 25,
				align:'center'
			}, 
			 {
				field : 'free_organization1',
				title : '机构',
				width : 25,
				align:'center'
			}, {
				field : 'free_cost',
				title : '费用',
				width : 20,
				align:'center'
			}, {
				field : 'manager_cost',
				title : '管理费',
				width : 20,
				align:'center'
			},{
				field : 'brand1',
				title : '品牌',
				width : 20,
				align:'center'
			},
			{
				field : 'action',
				title : '操作',
				width : 20,
				align:'center',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="show(\'' + row.free_code + '\',\'' + row.brand + '\');">查看</a> <a href="#" onclick="update(\'' + row.free_code + '\',\'' + row.brand + '\');">修改</a>';
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
			pageSize: 10,//每页显示的记录条数，默认为10   
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
	
	function show(freeCode){
		window.location.href="${ctx}/StandardSetupFree/showView/" + freeCode;
	}
	
	
	function add(){
		window.location.href="${ctx}/StandardSetupFree/addView";  
	}
	
	function update(freeCode){
		
		window.location.href="${ctx}/StandardSetupFree/updateView/" + freeCode;  
	}
	
	function importExcel(freeCode){
		//window.location.href = "${ctx}/StandardSetupFree/importView";
		parent.$.modalDialog({
			title : '安装结算标准调整费用数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=standardsetupfree&actionName=importHxSetupeFree',
			buttons : [ 
			{
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	}
	
	function exportExcel(){
		var opts = dataGrid.datagrid('getColumnFields');
		var fieldArray = new Array();
		var titleArray = new Array();
		for(var i = 0; i < opts.length - 1 ;i++){//最后的操作不要
			var column = dataGrid.datagrid('getColumnOption', opts[i]);
			fieldArray.push(column.field);
			titleArray.push(column.title);
		}
		window.location.href = "${ctx}/StandardSetupFree/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div
			data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 150px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed"
					style="width: 100%; padding: 7px 100px 0px 80px">
					<tr>
					  <td width="10%">型号</td>
						<td width="25%"><input  class="easyui-combobox"  class="span2" name="model" data-options="url:'${ctx}/hxCode/getCombobox/jx'"/></td>
						<td width="10%">机构</td>
						<td width="25%"><input name="freeOrganization" class="easyui-combobox" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/jg'"/></td>
						<td width="10%">费用</td>
						<td width="25%"><input name="freeCost"  class="span2" /></td>
					</tr>
					<tr>	
				  	<td width="10%">管理费</td>
						<td width="25%"><input class="span2" name="managerCost"/></td>
						<td width="10%">品牌</td>
						<td width="25%"><input class="easyui-combobox" name="brand" data-options="url:'${ctx}/hxCode/getCombobox/pp'" /></td>
					   <td></td>
					    <td width="30%" align="right"><a href="#" id="query"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>	
					
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">新建</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true"
				onclick="exportExcel();">导出</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true"
				onclick="importExcel();">费用导入</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
