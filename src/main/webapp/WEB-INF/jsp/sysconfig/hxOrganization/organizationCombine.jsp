<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		
		$('#form').form({
			url : '${ctx}/hxOrganization/combine',
			onSubmit : function(param) {
				param.orgCode = $("#orgCode").val();
				param.orgParentId = $("#orgParentId").val();
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(msg) {
				parent.$.messager.progress('close');
				if($.parseJSON(msg).flag == "success"){
					$.messager.alert('提示:','合并成功!');
				}
			}
		});
	});
	
	function submitForm(){
		var f = $("#form");
		f.submit();
	}
	
	function goBack(){
		var orgCode = $("#orgCode").val();
		var pageMarkup = $("#pageMarkup").val();
		window.location.href="${ctx}/hxOrganization/showView/"+orgCode+"/"+pageMarkup;
	}
	
</script>
<div class="easyui-panel" title="组织机构合并" style="width:auto">
		<div style="padding:10px 0 10px 60px">
		<input type="hidden" id="pageMarkup" title="页面标记位" value="${organization.pageMarkup}">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%">
				<tr>
					<td width="20%">当前机构</td>
					<td width="80%"><input id="orgCode" type="hidden" value="${organization.orgCode}">${organization.orgName}</td>
				</tr>
				<tr>
					<td>目标机构</td>
					<td><input id="orgParentId" type="text" placeholder="请选择目标机构" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>