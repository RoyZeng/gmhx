<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp"%>
<%@ include file="inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
	<script type="text/javascript">
		function check() {
			var userLoginPassword=$("#userLoginPassword").val();
			var CheckUserLoginPassword=$("#CheckUserLoginPassword").val();
			if(userLoginPassword==""){
				$.messager.alert('提示:', '请您输入新密码!');
			}else if(userLoginPassword=="123"){
				$.messager.alert('提示:', '新密码不能与原密码相同!');
			}else if(userLoginPassword!=CheckUserLoginPassword){
				$.messager.alert('提示:', '确认密码与新密码不一样，请重新确认!');
			}else{
				$.post("${ctx}/hxUser/updateUserPwd", $('#baseForm').serialize(), function(msg) {
					if ($.parseJSON(msg).flag == "success"){
						window.location.href = "${ctx}/index";
					}
				});
			}
		}
	</script>
	</head>
	<body>
	<div align="center">
	    <form id="baseForm"  method="post">
		<table>
		<input type="hidden" name="userLoginName" value="${indexLoginId }"/>
		<tr>
	    <td>请输入新密码:</td>
	    <td><input type="password" id="userLoginPassword" name="userLoginPassword" /></td>
	    </tr>
	    <tr>
	    <td>确认新密码:</td>
	    <td><input type="password" id="CheckUserLoginPassword"/></td>
		</tr>
		<tr>
		</tr>
		</table>
		</form>
	</div>
	<div align="center">
	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="check();">确认</a>
    <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="javascript:window.location.href = '${ctx}/login'">返回</a>
	</div>  
	</body>
</html>