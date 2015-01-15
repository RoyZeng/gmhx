<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">

function update(){
	window.location.href="${ctx}/personalSetting/updateView";
}

function goBack(){
// 	window.location.href="${ctx}/index/getMain";
    $('#tabs').tabs('close', 1);
}

function modifyPwd(){
	window.location.href="${ctx}/personalSetting/personModifyPwdView";
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
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">用户名:</td>
					<td width="25%"><label>${user.userName}</label></td>
					<td width="25%" align="center">登录名:</td>
					<td width="25%"><label>${user.userLoginName}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">所属机构:</td>
					<td width="25%"><label>${user.userOrgId}</label></td>
					<td width="25%" align="center">工号:</td>
					<td width="25%"><label>${user.userWorkNo}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">职位:</td>
					<td width="25%"><label>${user.userTitle}</label></td>
					<td width="25%" align="center">曾从事岗位:</td>
					<td width="25%"><label>${user.userWorked}</label></td>						
				</tr>
				<tr>
					<td width="25%" align="center">电话:</td>
					<td width="25%"><label>${user.userPhone}</label></td>
					<td width="25%" align="center">手机:</td>
					<td width="25%"><label>${user.userMobile}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">E-mail:</td>
					<td width="25%"><label>${user.userEmail}</label></td>
					<td width="25%" align="center">性别:</td>
					<td width="25%"><label>${user.userSex}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">学历:</td>
					<td width="25%"><label>${user.userEducation}</label></td>
					<td width="25%" align="center">籍贯:</td>
					<td width="25%"><label>${user.userOrigin}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">工作年限:</td>
					<td width="25%"><label>${user.userWorkLimit}</label></td>
					<td width="25%" align="center">出生日期:</td>
					<td width="25%"><label><fmt:formatDate value="${user.userBirthday}" pattern="yyyy-MM-dd" /></label></td>
				</tr>
				<tr>
					<td width="25%" align="center">身份证号:</td>
					<td width="25%"><label>${user.userIndentNo}</label></td>
					<td width="25%" align="center">进司时间:</td>
					<td width="25%"><label><fmt:formatDate value="${user.userInDate}" pattern="yyyy-MM-dd" /></label></td>
				</tr>
				<tr>
					<td width="25%" align="center">地区:</td>
					<td width="25%"><label>${user.userArea}</label></td>
					<td width="25%" align="center">地址:</td>
					<td width="25%"><label>${user.userAddress}</label></td>	
				</tr>
				<tr>
					<td width="25%" align="center">邮编:</td>
					<td><label>${user.userPostCode}</label></td>		
					<td width="25%" align="center">有效用户:</td>
					<td width="25%"><label>${user.userActive}</label></td>			
				</tr>
				<tr>
					<td width="25%" align="center">备注:</td>
					<td width="25%" colspan="3"><label>${user.userNote}</label></td>				
				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-edit'" onclick="update();">修改</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-edit'"  onclick="modifyPwd();">修改密码</a>
<!-- 	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a> -->
	</div>
</div>