<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		var origin = $("#positionOrigin").val();
		dataGrid = $('#dataGrid').datagrid({
			title : "岗位",
			url : "${ctx}/hxPosition/getPositionPageList?origin="+origin,
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 155,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'posotionId',
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
				field : 'positionCode',
				title : '岗位编码',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'positionName',
				title : '岗位名称',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					return '<a href="#" onclick="show(\'' + row.positionCode + '\',\'' +row.fromType+ '\');">'+row.positionName+'</a>';
                }  
			},{
				field : 'positionOrgName',
				title : '机构名称',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'roleName',
				title : '关联角色',
				width : 10,
				align:'center',
				sortable : true
			}, {
				field : 'fromType',
				title : '岗位来源',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					if(row.fromType=='0'){
						return '系统创建';
					}else if(row.fromType=='1'){
						return '身份管理平台';
					}
                }  
			},{
				field : 'action',
				title : '操作',
				align:'center',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="update(\'' + row.positionCode + '\',\'' + row.fromType  + '\');">修改</a>';
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
		var origin = $("#positionOrigin").val();
		window.location.href="${ctx}/hxPosition/addView?origin="+origin;
	}
	
	function update(positionCode, fromType){
		var origin = $("#positionOrigin").val();
		window.location.href="${ctx}/hxPosition/updateView/"+ positionCode+"?origin="+origin+"&fromType="+fromType;
	}
	
	function show(positionCode,fromType){
		window.location.href="${ctx}/hxPosition/showView/"+ positionCode+"?fromType="+fromType;
	}
	
	/* function importExcel() {
		var positionOrigin = $("#positionOrigin").val();
		window.location.href = "${ctx}/hxPosition/importView?origin="+positionOrigin;
	} */
	
	function importPositionRoleJbpmExcel(){
		var positionOrigin = $("#positionOrigin").val();
		var templateName="";
		if(positionOrigin=='hx'){
			templateName="position_hx"; 
		}else{
			templateName="position_gome";
		}
		parent.$.modalDialog({
			title : '岗位数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importPositionData?templateName='+templateName+'&actionName=importHxPosition&positionOrigin='+positionOrigin,
			buttons : [ 
			{
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	}
	
	/* function exportExcel() {
		var opts = dataGrid.datagrid('getColumnFields');
		var fieldArray = new Array();
		var titleArray = new Array();
		for (var i = 0; i < opts.length ; i++) {
			var column = dataGrid.datagrid('getColumnOption', opts[i]);
			fieldArray.push(column.field);
			titleArray.push(column.title);
		}
		window.location.href = "${ctx}/hxPosition/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}  */
	
	function exportPositionRoleJbpmExcel() {
		var positionOrigin = $("#positionOrigin").val();
		window.location.href = "${ctx}/hxPosition/exportPositionRoleJbpmExcel?" + encodeURI($('#searchForm').serialize())+"&positionOrigin="+positionOrigin;
	}
	
	function importPositionPermissionExcel() {
		var positionOrigin = $("#positionOrigin").val();
		window.location.href = "${ctx}/hxPosition/importPositionPermissionView?origin="+positionOrigin;
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 155px; overflow: hidden;">
			<form id="searchForm">
				<input type="hidden" value="${origin}" name="positionOrigin" id="positionOrigin" title="请求来源标记位"/>
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%">岗位编码:</td>
						<td width="5%"><input name="positionCode" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">岗位名称:</td>
						<td width="5%"><input name="positionName" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
					</tr>
					<tr>
						<td width="10%">机构名称:</td>
						<td width="5%"><input name="orgId" placeholder="输入查询条件" editable="true" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getOrgCombobox'"/></td>
						<td width="5%"></td>
						<td width="10%">关联角色:</td>
						<td width="5%"><input id="roleId" name="roleId" type="text" placeholder="请选择角色" editable="false" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getRoleCombobox'" ></td>
						<td width="5%"></td>
						<td width="20%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="importPositionRoleJbpmExcel();">导入</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true" onclick="exportPositionRoleJbpmExcel();">导出</a>
<!-- 			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="importPositionPermissionExcel();">导入岗位权限</a>
 -->		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
