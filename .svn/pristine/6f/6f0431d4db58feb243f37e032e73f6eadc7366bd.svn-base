<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
	
	
	function submitForm() {
		if(validateForm()){
			$.post("${ctx}/personalSetting/updatePersonPWD", $('#form').serialize(),
					   function(msg) {
					    if($.parseJSON(msg).flag == "success"){
			                $.messager.alert('提示:','修改成功!');
			                window.location.href="${ctx}/personalSetting/personView";
			            }else{
			                $.messager.alert('提示:','修改失败!');
			            }   
			});
		}
		
	}

	function goBack(){
        window.location.href="${ctx}/personalSetting/personView";
    }
	
	function validateForm(){
		var oldPassword = $("#oldPassword").val();
		if(oldPassword==''||oldPassword==null){
			alert("旧密码不能为空");
			return false;
		}else{
			if(oldPassword.length>15){
				alert("旧密码的长度不能大于15");
				return false;
			}
		}
		
		var userLoginPassword = $("#userLoginPassword").val();
		if(userLoginPassword==''||userLoginPassword==null){
			alert("新密码不能为空");
			return false;
		}else{
			if(userLoginPassword.length>40){
				alert("新密码的长度不能大于40");
				return false;
			}
		}
		var userLoginPasswordTemp = $("#userLoginPasswordTemp").val();
		if (userLoginPassword != userLoginPasswordTemp) {
			alert("两次输入的密码不一致，请重新输入");
			return false;
		}
		return true;
	}
</script>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				修改密码
			</div>
			<input type="hidden" id="pageMarkup" title="页面标记位" value="${pageMarkup}">
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">登录名称</td>
					<td width="25%"><input id="userLoginName" name="userLoginName" type="label" value="${userLoginName}" readonly></td>
					<td width="25%" align="center">旧密码<font color="red">*</font></td>
                    <td width="25%"><input id="oldPassword" name="oldPassword" type="password" placeholder="请输入旧密码"  value=""></td>
					
				</tr>
				<tr>
					<td width="25%" align="center">新密码<font color="red">*</font></td>
					<td width="25%"><input id="userLoginPassword" name="newPassword" type="password" placeholder="请输入新密码"  value=""></td>
					<td width="25%" align="center">确认新密码</td>
					<td width="25%"><input id="userLoginPasswordTemp" name="userLoginPasswordTemp" type="password" placeholder="请再次输入新密码" value=""></td>
				<tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>