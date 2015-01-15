<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		title : "用户",
		url : "${ctx}/hxUser/getUserPageList?userOrgId="+$("#orgCode").val(),
		nowrap : false,
		striped : true,
		collapsible : true,
		autoRowHeight : false,
		remoteSort : false,
		idField : 'userId',
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
			title : '姓名',
			width : 10,
			align:'center',
			formatter:function(value, row, index){ 
				return '<a href="#" onclick="show(\'' + row.userLoginName + '\',\'' + row.originTable  + '\');">'+row.userName+'</a>';
            }  
		}, {
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
			field : 'userTitle',
			title : '职位',
			width : 10,
			align:'center',
			sortable : true
		} ,{
			field : 'action',
			title : '操作',
			align:'center',
			formatter : function(value, row, index) {
				if(row.originTable=='hx_user'){
					return '<a href="#" onclick="update(\'' + row.userLoginName + '\',\'' + row.userName  + '\');">修改</a>';
				}else {
					return '<a href="#" onclick="show(\'' + row.userLoginName + '\',\'' + row.originTable  + '\');">查看</a>';
				}
			}
		} ] ],
		toolbar : '#toolbar',
		onLoadSuccess : function() {
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
});

function goBack(){
	var pageMarkup = $("#pageMarkup").val();
	if("tree"==pageMarkup){
		window.location.href="${ctx}/hxOrganization/organizationTree";
	}else if("list"==pageMarkup){
		window.location.href="${ctx}/hxOrganization/organizationView";
	}
}

function update(userLoginName, userName){
	var pageMarkup = "organization";
	window.location.href="${ctx}/hxUser/updateView/"+ userLoginName+"?pageMarkup="+pageMarkup;
}

function show(userLoginName, originTable){
	var pageMarkup = "organization";
	if(originTable=='hx_user'){
		window.location.href="${ctx}/hxUser/showSysUserView/"+ userLoginName+"?pageMarkup="+pageMarkup;
	}else if(originTable=='jl_account'){
		window.location.href="${ctx}/hxUser/showJlUserView/"+ userLoginName+"?pageMarkup="+pageMarkup;
	}else if(originTable=='emp_account'){
		window.location.href="${ctx}/hxUser/showEmpUserView/"+ userLoginName+"?pageMarkup="+pageMarkup;
	}
}

function add(){
	var pageMarkup = "organization";
	window.location.href="${ctx}/hxUser/addView?pageMarkup="+pageMarkup;
}
</script>
<div class="easyui-panel" title="机构详细信息" style="width:auto;heigth:auto;">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<input id="orgCode" type="hidden" value="${organization.orgCode}"/>
			<input id="orgName" type="hidden" value="${organization.orgName}"/>
			<input type="hidden" id="pageMarkup" title="页面标记位" value="${organization.pageMarkup}">
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td width="20%" >机构名称</td>
					<td width="25%"><label>${organization.orgName}</label></td>
					<td>机构代码</td>
					<td><label>${organization.orgCode}</label></td>
				</tr>
				<tr>
					<td>上级机构</td>
					<td><label>${organization.orgParentName}</label></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
	
	<div id="main-center" class="easyui-tabs" style="width:auto;height:550px;"> 
		<div title="机构的员工" style="padding:20px;"> 
			<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			</div>
			<div data-options="region:'center',border:false">
				<table id="dataGrid"></table>
			</div>
		</div> 
	</div>
</div>