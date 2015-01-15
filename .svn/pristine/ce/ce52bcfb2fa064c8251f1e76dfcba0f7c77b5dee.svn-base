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
			title : "组织机构",
			url : "${ctx}/hxOrganization/getOrganizationPageList",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 155,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'orgCode',
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
				field : 'orgName',
				title : '机构名称',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					return '<a href="#" onclick="show(\'' + row.orgCode + '\',\'' + row.fromType  + '\');">'+row.orgName+'</a>';
                }  
			}, {
				field : 'orgCode',
				title : '机构代码',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'orgParentName',
				title : '上级机构',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'codeValue',
				title : '机构类型',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'orgAddress',
				title : '地址',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'orgPostCode',
				title : '邮编',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'orgTelephone',
				title : '电话',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'orgManager',
				title : '业务负责人',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'orgManagerPhone',
				title : '业务负责人电话',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'orgFax',
				title : '传真',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'orgActiveName',
				title : '状态',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'action',
				title : '操作',
				align:'center',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="update(\'' + row.orgCode + '\',\'' + row.orgName  + '\');">修改</a>';
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
		var pageMarkup = "list";//页面标记位
		window.location.href="${ctx}/hxOrganization/addView/"+pageMarkup;
	}
	
	function update(orgCode, orgName){
		var pageMarkup = "list";//页面标记位
		window.location.href="${ctx}/hxOrganization/updateView/"+orgCode+"/"+pageMarkup;
	}
	
	function show(orgCode, fromType){
		var pageMarkup = "list";//页面标记位
		window.location.href="${ctx}/hxOrganization/showView/"+orgCode+"/"+pageMarkup;
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
		window.location.href = "${ctx}/hxOrganization/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
	
	function organizatinTree(){
		window.location.href = "${ctx}/hxOrganization/organizationTree";
	}
	
	function importExcel() {
		window.location.href = "${ctx}/hxOrganization/importView";
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
		window.location.href = "${ctx}/hxOrganization/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 155px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%">机构名称:</td>
						<td width="5%"><input name="orgName" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">机构代码:</td>
						<td width="5%"><input name="orgCode" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">上级机构:</td>
						<td width="5%"><input name="orgParentId" placeholder="输入查询条件" editable="true" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getOrgCombobox'"/></td>
						<td width="5%"></td>
					</tr>
					<tr>
						<td width="10%">机构类型:</td>
						<td width="5%"><input name="orgType" placeholder="输入查询条件" editable="false" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getCombobox/jglx'"/></td>
						<td width="5%"></td>
						<td width="10%">地址:</td>
						<td width="5%"><input name="orgAddress" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">邮编:</td>
						<td width="5%"><input name="orgPostCode" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
					</tr>
					<tr>
						<td width="10%">电话:</td>
						<td width="5%"><input name="orgTelephone" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">业务负责人:</td>
						<td width="5%"><input name="orgManager" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">业务负责人电话:</td>
						<td width="5%"><input name="orgManagerPhone" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
					</tr>
					<tr>
						<td width="10%">传真:</td>
						<td width="5%"><input name="orgFax" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">状态:</td>
						<td width="5%"><input name="orgActive" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="20%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
		    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="importExcel();">导入</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="organizatinTree();">组织机构</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
