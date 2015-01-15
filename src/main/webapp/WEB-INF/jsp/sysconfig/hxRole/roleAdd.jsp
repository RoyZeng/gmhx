<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
		$(function() {
			parent.$.messager.progress('close');
		});
	
		function submitForm(){
		  if($('#form').form('validate')){
			$.post("${ctx}/hxRole/addRole",$('#form').serialize(),
			function(msg){
				if($.parseJSON(msg).flag == "success"){
					$(":input").attr('disabled',true);
					$.messager.alert('提示:','保存成功!');
				}else{
					$.messager.alert('提示:','保存失败!');
				}
			});
		  }
		}
	
	function goBack(){
		window.location.href="${ctx}/hxRole/roleView";
	}
	
	function addContinue(){
		$(":input").val('');
	}

</script>
<div class="easyui-panel" title="新建角色" style="width:auto">
		<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%">
				<tr>
					<td width="20%">角色代码</td>
					<td width="80%"><input id="roleId"  name="roleId"  type="text" placeholder="请输入角色代码" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>角色名称</td>
					<td><input id="roleName"  name="roleName"  type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>角色描述</td>
					<td><input id="roleDesc"  name="roleDesc"  placeholder="请输入角色描述" class="easyui-validatebox span2" data-options="required:false" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>