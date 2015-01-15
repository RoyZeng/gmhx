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
			title : "角色管理",
			url : "${ctx}/hxRole/getRolePageList",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 155,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
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
                checkbox:true
            },{
				field : 'roleId',
				title : '角色编码',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'roleName',
				title : '角色名称',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'roleDesc',
				title : '角色描述',
				width : 10,
				align:'center',
				sortable : true
			} ,
			{
				field : 'action',
				title : '操作',
				width : 10,
				align:'center',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="bindResource(\'' + row.roleId + '\',\'' + row.roleName  + '\');">设置权限</a>';
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
	
	function searchs() {
		$('#dataGrid').datagrid('load');
	};

	function refreshDataGrid() {
		$('#dataGrid').datagrid("reload");
		parent.$.modalDialog.handler.dialog('close');	
	}	
	
	function addData(){
		window.location.href="${ctx}/hxRole/addView";
	}
	
	function editData() {
		var selectRows = $("#dataGrid").datagrid('getChecked');
		if (selectRows.length == 0) {
			$.messager.alert('提示', '请选择您要修改的行!', 'info');
		} else if (selectRows.length > 1) {
			$.messager.alert('提示', '一次只能编辑一条数据!', 'info');
		} else {
			var roleId = selectRows[0].roleId;
			var roleName= selectRows[0].roleName;
			var roleDesc= selectRows[0].roleDesc;
			var url="${ctx}/hxRole/updateView?roleId="+ roleId+"&roleName="+roleName+"&roleDesc="+roleDesc;
			url = encodeURI(encodeURI(url));
			window.location.href=url;
		}
	};
	
	function bindResource(roleId, roleName){
		var url="${ctx}/hxRoleMenu/roleMenuView?roleId="+ roleId;
		url = encodeURI(encodeURI(url));
		window.location.href=url;
	}
	
	function importExcel(){
		parent.$.modalDialog({
			title : '角色数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=role&actionName=importHxRole',
			buttons : [ 
			{
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	}
	
	function importAuthorityExcel(){
		parent.$.modalDialog({
			title : '角色权限数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=roleauthority&actionName=importHxRoleAuthority',
			buttons : [ 
			{
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	}
	
	function importRoleAuthorityExcel(){
		parent.$.modalDialog({
			title : '角色权限数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=roleauthority&actionName=importHxRoleAuthority',
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
		for(var i = 1; i < opts.length - 1 ;i++){//第一个勾选框不要,最后的操作不要
			var column = dataGrid.datagrid('getColumnOption', opts[i]);
			fieldArray.push(column.field);
			titleArray.push(column.title);
		}
		window.location.href = "${ctx}/hxRole/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
	
	function exportRoleAuthorityExcel(){
		window.location.href = "${ctx}/hxRoleMenu/exportRoleAuthorityExcel?" + encodeURI($('#searchForm').serialize());
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 155px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%">角色编码:</td>
						<td width="5%"><input name="roleId" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">角色名称:</td>
						<td width="5%"><input name="roleName" placeholder="输入查询条件" class="span2"></td>
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
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
			onclick="addData();">添加</a> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-edit'" onclick="editData();">修改</a> 
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="importExcel();">角色导入</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">角色导出</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="importAuthorityExcel();">权限导入</a> -->
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="importRoleAuthorityExcel();">角色权限导入</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="exportRoleAuthorityExcel();">角色权限导出</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
