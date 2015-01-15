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
			title : "功能菜单",
			url : "${ctx}/hxMenu/getMenuPageList",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 155,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'menuId',
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			checkOnSelect : false,
			selectOnCheck : false,
			queryParams : {
				currentPage : 1,
				pageCount : 10
			},
			columns : [ [ {
				field : 'menuName',
				title : '菜单名称',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					return '<a href="#" onclick="show(\'' + row.menuId + '\');">'+row.menuName+'</a>';
                }  
			}, {
				field : 'parentName',
				title : '父菜单',
				width : 10,
				align:'center',
				sortable : true
			}, {
				field : 'url',
				title : '菜单路径',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'iconUrl',
				title : '菜单图标位置',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'action',
				title : '操作',
				align:'center',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="update(\'' + row.menuId + '\');">修改</a>';
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
	
	function add(){
		window.location.href="${ctx}/hxMenu/addView";
	}
	
	function update(menuId, menuName){
		window.location.href="${ctx}/hxMenu/updateView?menuId="+ menuId;
	}
	
	function show(menuId){
		window.location.href="${ctx}/hxMenu/showView?menuId="+ menuId;
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 155px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%">菜单名称:</td>
						<td width="5%"><input name="menuName" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">父菜单:</td>
						<td width="5%"><input name="parentName" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="40%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
