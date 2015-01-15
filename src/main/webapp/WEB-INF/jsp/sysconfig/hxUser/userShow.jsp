<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
var dataGrid;
$(function() {
	parent.$.messager.progress('close');
	var userLoginName = $("#userLoginName").val();
	var fromType = $("#fromType").val();
	var origin = $("#origin").val();  // 恒信,权限
	
	dataGrid = $('#dataGrid').datagrid({
		title : "岗位",
		url : "${ctx}/hxUser/getPositionPageListByUserLoginName?userLoginName="+userLoginName+"&fromType="+fromType+"&origin="+origin,
		nowrap : false,
		striped : true,
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
			field : 'positionName',
			title : '岗位名称',
			width : 10,
			align:'center',
			sortable : true 
		}, {
			field : 'positionTypeName',
			title : '岗位类型',
			width : 10,
			align:'center',
			formatter:function(value, row, index){ 
				if(row.positionType=='0'){
					return '普通岗';
				}else if(row.positionType=='1'){
					return '总部物料岗';
				}else if(row.positionType=='2'){
					return '分部物料岗';
				}else if(row.positionType=='3'){
					return '网点物料岗';
				}
            }  
		} , {
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
			$('#dataGrid').datagrid("options").queryParams = {
				currentPage : pageNumber,
				pageCount : pageSize
			};
			$('#dataGrid').datagrid("reload");
			$(this).pagination('loaded');
		}
	});
});

function update(){
	var pageMarkup = $("#pageMarkup").val();
	var userLoginName = $("#userLoginName").val();
	var origin = $("#origin").val();
	if(pageMarkup=='organization'||pageMarkup=='position'){
		window.location.href="${ctx}/hxUser/updateView/"+userLoginName+"?pageMarkup="+pageMarkup+"&origin="+origin;
	}else {
		window.location.href="${ctx}/hxUser/updateView/"+userLoginName+"?origin="+origin;
	}
	
}

function goBack(){
	var pageMarkup = $("#pageMarkup").val();
	var origin = $("#origin").val();
	if(pageMarkup=='organization'||pageMarkup=='position'){
		history.back();
	}else {
		window.location.href="${ctx}/hxUser/userView?origin="+origin;
	}
}

function authority(){
	var userLoginName = $("#userLoginName").val();
	var fromType = $("#fromType").val();
	var pageMarkup = $("#pageMarkup").val();
	var origin = $("#origin").val();
	if(pageMarkup=='organization'||pageMarkup=='position'){
		window.location.href="${ctx}/hxUserPosition/userPositionView/"+ userLoginName+"?fromType="+fromType+"&pageMarkup="+pageMarkup+"&origin="+origin;
	}else{
		window.location.href="${ctx}/hxUserPosition/userPositionView/"+ userLoginName+"?fromType="+fromType+"&origin="+origin;
	}
}

function modifyPwd(){
	var pageMarkup = $("#pageMarkup").val();
	var userLoginName = $("#userLoginName").val();
	var origin = $("#origin").val();
	if(pageMarkup=='organization'||pageMarkup=='position'){
		window.location.href="${ctx}/hxUser/modifyPwdView/"+userLoginName+"?pageMarkup="+pageMarkup+"&origin="+origin;
	}else {
		window.location.href="${ctx}/hxUser/modifyPwdView/"+ userLoginName+"?origin="+origin;
	}
}
</script>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				用户详细资料
			</div>
			<input type="hidden" id="modify" onclick="update();"/>
			<input id="userLoginName" type="hidden" value="${user.userLoginName}"/>
			<input id="userName" type="hidden" value="${user.userName}"/>
			<input type="hidden" id="pageMarkup" title="页面标记位" value="${pageMarkup}">
			<input type="hidden" id="fromType" value="${fromType}">
			<input type="hidden" value="${origin}" name="origin" id="origin" title="请求来源标记位"/>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">用户名:</td>
					<td width="25%"><label>${user.userName}</label></td>
					<td width="25%" align="center">登录名:</td>
					<td width="25%"><label>${user.userLoginName}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">所属机构:</td>
					<td><label>${user.userOrgId}</label></td>
					<td width="25%" align="center">工号:</td>
					<td><label>${user.userWorkNo}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">职位:</td>
					<td><label>${user.userTitle}</label></td>
					<td width="25%" align="center">曾从事岗位:</td>
					<td><label>${user.userWorked}</label></td>						
				</tr>
				<tr>
					<td width="25%" align="center">电话:</td>
					<td><label>${user.userPhone}</label></td>
					<td width="25%" align="center">手机:</td>
					<td><label>${user.userMobile}</label></td>
				</tr>
					<td width="25%" align="center">E-mail:</td>
					<td><label>${user.userEmail}</label></td>
					<td width="25%" align="center">性别:</td>
					<td><label>${user.userSex}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">学历:</td>
					<td><label>${user.userEducation}</label></td>
					<td width="25%" align="center">籍贯:</td>
					<td><label>${user.userOrigin}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">工作年限:</td>
					<td><label>${user.userWorkLimit}</label></td>
					<td width="25%" align="center">出生日期:</td>
					<td><label><fmt:formatDate value="${user.userBirthday}" pattern="yyyy-MM-dd" /></label></td>
				</tr>
				<tr>
					<td width="25%" align="center">身份证号:</td>
					<td><label>${user.userIndentNo}</label></td>
					<td width="25%" align="center">进司时间:</td>
					<td><label><fmt:formatDate value="${user.userInDate}" pattern="yyyy-MM-dd" /></label></td>
				</tr>
				<tr>
					<td width="25%" align="center">地区:</td>
					<td><label>${user.userArea}</label></td>
					<td width="25%" align="center">地址:</td>
					<td><label>${user.userAddress}</label></td>	
				</tr>
				<tr>
					<td width="25%" align="center">邮编:</td>
					<td><label>${user.userPostCode}</label></td>		
					<td width="25%" align="center">有效用户:</td>
					<td><label>${user.userActive}</label></td>			
				</tr>
				<tr>
					<td width="25%" align="center">备注:</td>
					<td width="25%"colspan="3"><label>${user.userNote}</label></td>				
				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-edit'" onclick="update();">修改</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-edit'" onclick="modifyPwd();">修改密码</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
	<div id="main-center" class="easyui-tabs" style="width:auto;height:550px;"> 
		<div title="该用户拥有的岗位" style="padding:20px;"> 
			<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="authority();">岗位管理</a>
			</div>
			<div data-options="region:'center',border:false">
				<table id="dataGrid"></table>
			</div>
		</div> 
	</div>
</div>