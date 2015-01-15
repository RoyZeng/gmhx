<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		
		$('#form').form({
			url : '${ctx}/demo/addDemo',
			onSubmit : function(param) {
				param.aa = $("#aa").val();
				param.bb = $("#bb").val();
				param.cc = $("#cc").val();
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
					$.messager.alert('提示:','保存成功!');
					parent.$.modalDialog.openner_dataGrid.datagrid("reload");
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});
	});
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<td>aa</td>
					<td><input id="aa" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>bb</td>
					<td><input id="bb" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>cc</td>
					<td><input id="cc" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
			</table>
		</form>
	</div>
</div>