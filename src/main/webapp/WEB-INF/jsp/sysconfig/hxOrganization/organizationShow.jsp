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
				return '<a href="#" onclick="showUser(\'' + row.userLoginName + '\',\'' + row.fromType  + '\');">'+row.userName+'</a>';
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
				if(row.fromType==3){
					return '<a href="#" onclick="updateUser(\'' + row.userLoginName + '\',\'' + row.userName  + '\');">修改</a>';
				}else {
					return '<a href="#" onclick="showUser(\'' + row.userLoginName + '\',\'' + row.fromType  + '\');">查看</a>';
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

function update(){
	var orgCode = $("#orgCode").val();
	var pageMarkup = $("#pageMarkup").val();
	window.location.href="${ctx}/hxOrganization/updateView/"+orgCode+"/"+pageMarkup;
}

function goBack(){
	var pageMarkup = $("#pageMarkup").val();
	if("tree"==pageMarkup){
		window.location.href="${ctx}/hxOrganization/organizationTree";
	}else if("list"==pageMarkup){
		window.location.href="${ctx}/hxOrganization/organizationView";
	}else if("limit"==pageMarkup){
		window.location.href="${ctx}/hxLimit/limitHistoryView";
	}else if("limitShow"==pageMarkup){
		history.back();
// 		window.location.href="${ctx}/hxLimit/limitShow";
	}
}

function logout(){
	var orgCode = $("#orgCode").val();
	var pageMarkup = $("#pageMarkup").val();
	window.location.href="${ctx}/hxOrganization/logout/"+ orgCode+"/"+pageMarkup;
}

function logon(){
	var orgCode = $("#orgCode").val();
	var pageMarkup = $("#pageMarkup").val();
	window.location.href="${ctx}/hxOrganization/logon/"+ orgCode+"/"+pageMarkup;
}

function move(){
	var orgCode = $("#orgCode").val();
	var pageMarkup = $("#pageMarkup").val();
	window.location.href="${ctx}/hxOrganization/moveView/"+ orgCode+"/"+pageMarkup;
}

function combine(){
	var orgCode = $("#orgCode").val();
	var pageMarkup = $("#pageMarkup").val();
	window.location.href="${ctx}/hxOrganization/combineView/"+ orgCode+"/"+pageMarkup;
}

function showUser(userLoginName, fromType){
	var pageMarkup = "organization";
	window.location.href="${ctx}/hxUser/showView/"+ userLoginName+"?fromType="+fromType+"&pageMarkup="+pageMarkup;
}

function updateUser(userLoginName, userName){
	var pageMarkup = "organization";
	window.location.href="${ctx}/hxUser/updateView/"+ userLoginName+"?pageMarkup="+pageMarkup;
}

function add(){
	var pageMarkup = "organization";
	window.location.href="${ctx}/hxUser/addView?pageMarkup="+pageMarkup;
}
</script>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				机构详细信息
			</div>
			<input id="orgCode" type="hidden" value="${organization.orgCode}"/>
			<input id="orgName" type="hidden" value="${organization.orgName}"/>
			<input id="orgCode" type="hidden" value="${organization.orgCode}"/>
			<input type="hidden" id="pageMarkup" title="页面标记位" value="${organization.pageMarkup}">
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">机构名称:</td>
					<td width="25%"><label>${organization.orgName}</label></td>
					<td width="25%" align="center">机构级别:</td>
					<td width="25%"><label>${organization.orgLevel}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">机构代码:</td>
					<td width="25%"><label>${organization.orgCode}</label></td>
					<td width="25%" align="center">上级机构:</td>
					<td width="25%"><label>${organization.orgParentName}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">机构类型:</td>
					<td width="25%"><label>${organization.codeValue}</label></td>
					<td width="25%" align="center">地区:</td>
					<td width="25%"><label>${organization.orgCity}</label></td>						
				</tr>
				<tr>
					<td width="25%" align="center">联系人:</td>
					<td width="25%"><label>${organization.orgReference}</label></td>
					<td width="25%" align="center">地址:</td>
					<td width="25%"><label>${organization.orgAddress}</label></td>
				</tr>
					<td width="25%" align="center">邮编:</td>
					<td width="25%"><label>${organization.orgPostCode}</label></td>
					<td width="25%" align="center">电话:</td>
					<td width="25%"><label>${organization.orgTelephone}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">业务负责人:</td>
					<td width="25%"><label>${organization.orgManager}</label></td>
					<td width="25%" align="center">业务负责人电话:</td>
					<td width="25%"><label>${organization.orgManagerPhone}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">传真:</td>
					<td width="25%"><label>${organization.orgFax}</label></td>
					<td width="25%" align="center">退换机总部审批:</td>
					<td width="25%"><label>${organization.orgApprovalName}</label></td>	
				</tr>
				<tr>
					<td width="25%" align="center">是否为配件库:</td>
					<td width="25%"><label>${organization.isFittingStockName}</label></td>
					<td width="25%" align="center">物料上级机构:</td>
					<td width="25%"><label>${organization.fittingPidName}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">状态:</td>
					<td width="25%"><label>${organization.orgActiveName}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">备注:</td>
					<td width="25%" colspan="3"><label>${organization.orgNote}</label></td>				
				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-edit'" onclick="update();">修改</a>
	    	<c:if test="${organization.fromType=='hx001'}">
	    		<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-edit'" onclick="move();">移动</a>
	    	</c:if>
	    	<!--  <a href="#" class="easyui-linkbutton"  onclick="combine();">合并</a>-->
	    	<c:if test="${organization.orgActive==true || organization.orgActive == null}">
	    		<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-no'" onclick="logout();">注销</a>
	    	</c:if>
	    	<c:if test="${organization.orgActive==false}">
	    		<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-ok'" onclick="logon();">启用</a>
	    	</c:if>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
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