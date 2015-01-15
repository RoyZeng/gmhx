<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		var origin = $("#origin").val();
		dataGrid = $('#dataGrid').datagrid({
			title : "用户",
			url : "${ctx}/hxUser/getUserPageList?origin="+origin,
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 155,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'userLoginName',
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
				field : 'userName',
				title : '用户名',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					return '<a href="#" onclick="show(\'' + row.userLoginName + '\',\'' + row.fromType  + '\');">'+row.userName+'</a>';
                }  
			}, {
				field : 'userLoginName',
				title : '登录名称',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'userOrgName',
				title : '所属机构',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'userOrgId',
				title : '机构代码',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'userPhone',
				title : '联系电话',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'userMobile',
				title : '手机',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'userEmail',
				title : 'E-mail',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'userActive',
				title : '有效用户',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					if(row.userActive=='0'){
						return '否';
					}else if(row.userActive=='1'){
						return '是';
					}
                }  
			} , {
				field : 'fromType',
				title : '用户类型',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					if(row.fromType=='1'){
						return '国美用户';
					}else if(row.fromType=='2'){
						return '第三方网点用户';
					}else if(row.fromType=='3'){
						return '系统自建用户';
					}
                }  
			},{
				field : 'action',
				title : '操作',
				align:'center',
				formatter : function(value, row, index) {
					if(row.fromType==3){
						return '<a href="#" onclick="update(\'' + row.userLoginName + '\',\'' + row.userName  + '\');">修改</a>';
					}else {
						return '<a href="#" onclick="show(\'' + row.userLoginName + '\',\'' + row.fromType  + '\');">查看</a>';
					}
					
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
		var origin = $("#origin").val();
		window.location.href="${ctx}/hxUser/addView?origin="+origin
	}
	
	function update(userLoginName, userName){
		var origin = $("#origin").val();
		window.location.href="${ctx}/hxUser/updateView/"+ userLoginName+"?origin="+origin;
	}
	
	function show(userLoginName, fromType){
		var origin = $("#origin").val();
		window.location.href="${ctx}/hxUser/showView/"+ userLoginName+"?fromType="+fromType+"&origin="+origin;
	}
	
	function importExcel() {
		var origin = $("#origin").val();
		window.location.href = "${ctx}/hxUser/importView?origin="+origin;
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
		window.location.href = "${ctx}/hxUser/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 155px; overflow: hidden;">
			<form id="searchForm">
				<input type="hidden" value="${origin}" name="origin" id="origin" title="请求来源标记位"/> 
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%">用户名:</td>
						<td width="5%"><input name="userName" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">登录名称:</td>
						<td width="5%"><input name="userLoginName" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">所属机构:</td>
						<td width="5%"><input name="userOrgId" placeholder="输入查询条件" editable="true" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getOrgCombobox'"/></td>
						<td width="5%"></td>
					</tr>
					<tr>
						<td width="10%">机构代码:</td>
						<td width="5%"><input name="orgCode" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">上级机构:</td>
						<td width="5%"><input name="orgParentId" placeholder="输入查询条件" editable="true" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getOrgCombobox'"/></td>
						<td width="5%"></td>
						<td width="10%">有效用户:</td>
						<td width="5%"><input name="userActive" placeholder="输入查询条件" editable="false" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getCombobox/sf'"/></td>
						<td width="5%"></td>
						<td width="40%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<c:if test="${origin=='hx'}">
			<div id="toolbar" style="display: none;">
				<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="importExcel();">导入</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
			</div>
		</c:if>
		<c:if test="${origin=='gome'}">
			<div id="toolbar" style="display: none;">
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
			</div>
		</c:if>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
