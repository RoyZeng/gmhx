<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			title : "系统角色",
			url : "${ctx}/demo/getDemoPageList",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 70,
			/* collapsible : true, */
			autoRowHeight : false,
			remoteSort : false,
			idField : 'aa',
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			pagination : true,
			checkOnSelect : false,
			selectOnCheck : false,
			queryParams : {
				currentPage : 1,
				pageCount : 10
			},
			columns : [ [ {
				field : 'aa',
				title : '编号',
				width : 10,
				align:'center'
			}, {
				field : 'bb',
				title : '姓名',
				width : 5,
				align:'center'
			}, {
				field : 'cc',
				title : '描述',
				width : 20,
				align:'center',
				sortable : true
			} ,{
				field : 'action',
				title : '操作',
				align:'center',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="update(\'' + row.aa + '\',\'' + row.bb  + '\');">修改</a> <a href="#" onclick="authority(\'' + row.roleId + '\',\'' + row.roleName  + '\');">授权</a>';
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
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
	
	function authority(roleId, roleName){
		parent.$.modalDialog({
			title : '角色授权(' + roleName + ')',
			width : 300,
			height : 500,
			href : '${ctx}/role/role_authorityView.action?roleId=' + roleId,
			buttons : [ {
				text : '确定',
				handler : function() {
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	
	function add(){
		parent.$.modalDialog({
			title : '添加角色',
			width : 500,
			height : 300,
			href : '${ctx}/demo/addView',
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	
	function update(aa, bb){
		parent.$.modalDialog({
			title : '修改角色(' + bb + ')',
			width : 500,
			height : 300,
			href : '${ctx}/demo/updateView/' + aa,
			buttons : [ {
				text : '确定',
				handler : function() {
					$.post("${ctx}/demo/updateDemo",parent.$.modalDialog.handler.find('#form').serialize(),
					function(msg){
						parent.$.messager.progress('close');
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('提示:','修改成功!');
							dataGrid.datagrid("reload");
							parent.$.modalDialog.handler.dialog('close');
						}
					});
				}
			} ]
		});
	}
	
	function del(){
		var ids = [];
		var checkedData = dataGrid.datagrid('getSelections');
		if(checkedData.length == 0){
			$.messager.alert('提示:','没有选择行!','warning');
			return;
		}
		$.each(checkedData, function(){
			ids.push($(this)[0].roleId);
		});
		parent.$.messager.confirm('提示', '确认要删除么？', function(r){
			if (r){
				$.post("${ctx}/role/role_roleDel.action",{
					ids : ids.join(",")
				},function(msg){
					if(msg.flag == "success"){
						$.messager.alert('提示:','删除成功!');
						$('#dataGrid').datagrid("reload");
					}else{
						$.messager.alert('提示:','删除失败!');
					}
				});
			}
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
		window.location.href = "${ctx}/demo/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="5%">aaaaaaaa:</td>
						<td width="5%"><input class="easyui-combobox" name="aa" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/pjfl'"/></td>
						<td width="5%"></td>
						<td width="5%">bbbbbbbb:</td>
						<td width="5%"><input name="bb" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="5%">cccccccc:</td>
						<td width="5%"><input name="cc" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="55%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-cut',plain:true" onclick="del();">删除</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
