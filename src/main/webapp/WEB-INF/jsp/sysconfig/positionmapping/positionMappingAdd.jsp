<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		$("#jbpmRoleCode,#jbpmRoleName").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
		parent.$.messager.progress('close');
	});
	
	function submitForm(){
	  if(validateForm()){
		$.post("${ctx}/hxPositionMapping/addPositionMapping",$('#form').serialize(),
		function(msg){
			if($.parseJSON(msg).flag == "success"){
				$.messager.alert('提示:','保存成功!');
				window.location.href="${ctx}/hxPositionMapping/positionMappingView";
			}
		});
	  }
	}
	
	function goBack(){
		window.location.href="${ctx}/hxPositionMapping/positionMappingView";
	}
	
	function validateForm(){
		var result = $('#form').form('validate');
		return result;
	}
</script>
	<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				新建流程岗位
			</div>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">角色编码</td>
					<td width="25%"><input id="jbpmRoleCode" name="jbpmRoleCode" type="text" placeholder="请输入角色编码" value=""><font color="red">*</font></td>
					<td width="25%" align="center">角色名称</td>
					<td width="25%"><input id="jbpmRoleName" name="jbpmRoleName" type="text" placeholder="请输入角色名称" value=""><font color="red">*</font></td>
				</tr>
			</table>
	</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton"  data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>