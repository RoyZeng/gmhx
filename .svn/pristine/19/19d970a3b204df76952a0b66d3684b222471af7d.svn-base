<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
	
	function submitForm(){
		if(validateForm()){
			$.post("${ctx}/hxUser/updateUser",$('#form').serialize(),
					function(msg){
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('提示:','修改成功!');
						}else if($.parseJSON(msg).flag == "duplicatekey"){
							$.messager.alert('提示:','修改失败,登陆名重复!');
						}
			});
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
	
	function validateForm(){
		var userName = $("#userName").val();
		if(userName==''||userName==null){
			alert("用户名不能为空");
			return false;
		}else{
			if(userName.length>50){
				alert("用户名的长度不能大于50");
				return false;
			}
		}
		var userLoginName = $("#userLoginName").val();
		if(userLoginName==''||userLoginName==null){
			alert("登录名不能为空");
			return false;
		}else{
			if(userLoginName.length>50){
				alert("登录名的长度不能大于50");
				return false;
			}
		}
		
		var userOrgId = $('#userOrgId').combobox('getValues');
		if(userOrgId==''||userOrgId==null){
			alert("机构名称不能为空");
			return false;
		}
		var userWorkNo = $("#userWorkNo").val();
		if(userWorkNo.length>64){
			alert("工号的长度不能大于64");
			return false;
		}
		var userTitle = $("#userTitle").val();
		if(userTitle.length>50){
			alert("职位的长度不能大于50");
			return false;
		}
		var userWorked = $("#userWorked").val();
		if(userWorked.length>255){
			alert("曾从事岗位的长度不能大于255");
			return false;
		}
		var userPhone = $("#userPhone").val();
		if(userPhone==''||userPhone==null){
		}else{
			if(userPhone.length>50){
				alert("电话的长度不能大于50");
				return false;
			}else{
				var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
				if(!reg.test(userPhone)){
					alert("电话的格式不正确");
					return false;
				}
			}
		}
		
		var userMobile = $("#userMobile").val();
		if(userMobile==''||userMobile==null){
		}else{
			if(userMobile.length>50){
				alert("手机的长度不能大于50");
				return false;
			}else{
				var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
				if(!reg.test(userMobile)){
					alert("手机的格式不正确");
					return false;
				}
			}
		}
		
		var userEmail = $("#userEmail").val();
		var emailFilter  = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(userEmail==''||userEmail==null){
		}else{
			if(userEmail.length >100){
				 alert('邮箱字符长度不能大于100');
				 return false;
			 }else{
				if(!userEmail.match(emailFilter)){
					 alert("邮箱格式不正确");
					 return false;
				 }
			}
		}
		 
		var userWorkLimit = $("#userWorkLimit").val();
		if(userWorkLimit==''||userWorkLimit==null){
		}else{
			if(userWorkLimit.length>255){
				alert("工作年限的长度不能大于255");
				return false;
			}else{
				var reg = /^(?:0|[1-9][0-9]?|100)$/;
				if(!reg.test(userWorkLimit)){
					alert("工作年限的值应该在0和100之间");
					return false;
				}
			}
		}
		
		var userIndentNo = $("#userIndentNo").val();
		if(userIndentNo.length>18){
			alert("身份证号的长度不能大于18");
			return false;
		}
		var userPostCode = $("#userPostCode").val();
		if(userPostCode.length>20){
			alert("邮编的长度不能大于20");
			return false;
		}
		var userAddress = $("#userAddress").val();
		if(userAddress.length>255){
			alert("地址的长度不能大于255");
			return false;
		}
		var userActive = $('#userActive').combobox('getValues');
		if(userActive==''||userActive==null){
			alert("请选择是否为有效用户");
			return false;
		}
		var userNote = $("#userNote").val();
		if(userNote.length>2000){
			alert("备注的长度不能大于2000");
			return false;
		}
		return true;
	}
</script>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				修改登录用户资料
			</div>
			<input type="hidden" id="pageMarkup" title="页面标记位" value="${pageMarkup}">
			<input type="hidden" value="${origin}" name="origin" id="origin" title="请求来源标记位"/>
			<input type="hidden" id="oldUserLoginName" name="oldUserLoginName" value="${user.userLoginName}">
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">用户名<font color="red">*</font></td>
					<td width="25%"><input id="userName" name="userName" type="text" placeholder="请输入用户名" value="${user.userName}"></td>
					<td width="25%" align="center">登录名<font color="red">*</font></td>
					<td width="25%"><input id="userLoginName" name="userLoginName" type="text" placeholder="请输入登录名" value="${user.userLoginName}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">所属机构<font color="red">*</font></td>
					<td width="25%"><input id="userOrgId" name="userOrgId" type="text" placeholder="请选择所属机构" editable="true" class="easyui-combobox" class="span2" value="${user.userOrgId}" data-options="url:'${ctx}/hxCode/getOrgCombobox'"></td>
					<td width="25%" align="center">工号</td>
					<td width="25%"><input id="userWorkNo" name="userWorkNo" type="text" placeholder="请输入工号"  value="${user.userWorkNo}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">职位</td>
					<td width="25%"><input id="userTitle" name="userTitle" type="text" placeholder="请输入职位"  value="${user.userTitle}"></td>
					<td width="25%" align="center">曾从事岗位</td>
					<td width="25%"><input id="userWorked" name="userWorked" type="text" placeholder="请输入曾从事岗位"  value="${user.userWorked}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">电话</td>
					<td width="25%"><input id="userPhone" name="userPhone" type="text" placeholder="请输入电话"  value="${user.userPhone}"></td>
					<td width="25%" align="center">手机</td>
					<td width="25%"><input id="userMobile" name="userMobile" type="text" placeholder="请输入手机"  value="${user.userMobile}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">E-mail</td>
					<td width="25%"><input id="userEmail" name="userEmail" type="text" placeholder="请输入E-mail"  value="${user.userEmail}"></td>
					<td width="25%" align="center">性别</td>
					<td width="25%"><input id="userSex" name="userSex" type="text" placeholder="请选择性别" editable="false" class="easyui-combobox" class="span2" value="${user.userSex}" data-options="url:'${ctx}/hxCode/getCombobox/xb'"></td>
				</tr>
				<tr>
					<td width="25%" align="center">学历</td>
					<td width="25%"><input id="userEducation" name="userEducation" type="text" placeholder="请选择学历" editable="false" class="easyui-combobox" class="span2" value="${user.userEducation}" data-options="url:'${ctx}/hxCode/getCombobox/xl'"></td>
					<td width="25%" align="center">籍贯</td>
					<td width="25%"><input id="userOrigin" name="userOrigin" type="text" placeholder="请输入籍贯"  value="${user.userOrigin}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">工作年限</td>
					<td width="25%"><input id="userWorkLimit" name="userWorkLimit" type="text" placeholder="请输入工作年限"  value="${user.userWorkLimit}"></td>
					<td width="25%" align="center">出生日期</td>
					<td width="25%"><input id="userBirthday" name="userBirthday" type="text" placeholder="请选择出生日期"  value="<fmt:formatDate value='${user.userBirthday}' pattern='yyyy-MM-dd'/>" readonly="true">
						<img onclick="WdatePicker({el:'userBirthday'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
					</td>
				</tr>
				<tr>
					<td width="25%" align="center">身份证号</td>
					<td width="25%"><input id="userIndentNo" name="userIndentNo" type="text" placeholder="请输入身份证号"  value="${user.userIndentNo}" ></td>
					<td width="25%" align="center">进司时间</td>
					<td width="25%"><input id="userInDate" name="userInDate" type="text" placeholder="请选择进司时间"  value="<fmt:formatDate value='${user.userInDate}' pattern='yyyy-MM-dd'/>" readonly="true">
						<img onclick="WdatePicker({el:'userInDate'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
					</td>
				</tr>
				<tr>
					<td width="25%" align="center">地区</td>
					<td width="25%"><input id="userArea" name="userArea" type="text" placeholder="请输入地区"  value="${user.userArea}"></td>
					<td width="25%" align="center">地址</td>
					<td width="25%"><input id="userAddress" name="userAddress" type="text" placeholder="请输入地址" c value="${user.userAddress}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">邮编</td>
					<td width="25%"><input id="userPostCode" name="userPostCode" type="text" placeholder="请输入邮编" value="${user.userPostCode}"></td>
					<td width="25%" align="center">有效用户<font color="red">*</font></td>
					<td width="25%"><input id="userActive" name="userActive" type="text" placeholder="请选择是否为有效用户" editable="false" class="easyui-combobox" class="span2" value="${user.userActive}" data-options="url:'${ctx}/hxCode/getCombobox/sf'"></td>
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" colspan="3"><textarea id="userNote"  name="userNote" placeholder="请输入备注" rows="3" cols="52">${user.userNote}</textarea></td>
				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>