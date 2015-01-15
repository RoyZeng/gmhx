<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
	
	function submitForm(){
		if(validateForm()){
			$.post("${ctx}/hxOrganization/move",$('#form').serialize(),
					function(msg){
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('提示:','移动成功!');
						}
			});
		}
		
	}
	
	function goBack(){
		var orgCode = $("#orgCode").val();
		var pageMarkup = $("#pageMarkup").val();
		window.location.href="${ctx}/hxOrganization/showView/"+orgCode+"/"+pageMarkup;
	}
	
	function validateForm(){
		var orgParentId = $('#orgParentId').combobox('getValues');
		if(orgParentId==''||orgParentId==null){
			alert("目标机构不能为空");
			return false;
		}
		return true;
	}
</script>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				组织机构移动
			</div>
			<input type="hidden" id="pageMarkup" title="页面标记位" value="${organization.pageMarkup}">
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">当前机构</td>
					<td width="25%"><input id="orgCode" name="orgCode" type="hidden" value="${organization.orgCode}">${organization.orgName}</td>
					<td width="25%" align="center">目标机构<font color="red">*</font></td>
					<td width="25%"><input id="orgParentId" name="orgParentId" type="text" placeholder="请选择目标机构" editable="false" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getOrgCombobox'"></td>
				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>