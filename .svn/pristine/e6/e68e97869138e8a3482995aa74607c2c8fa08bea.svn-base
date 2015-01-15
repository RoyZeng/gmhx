<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
	
	function submitForm(){
	  if(validateForm()){
		$.post("${ctx}/hxMenu/addMenu",$('#form').serialize(),
		function(msg){
			if($.parseJSON(msg).flag == "success"){
				$.messager.alert('提示:','保存成功!');
				var continueToAddFlag = $("#continueToAddFlag").val();
				if(continueToAddFlag=="1"){//如果是继续添加则会执行页面跳转
					window.location.href="${ctx}/hxMenu/addView";
				}else{
					window.location.href="${ctx}/hxMenu/menuView";
				}
			}
		});
	  }
	}
	
	function goBack(){
		window.location.href="${ctx}/hxMenu/menuView";
	}
	
	function continueToAdd(){
		//继续添加标志位置1
		$("#continueToAddFlag").val("1");
		submitForm();
	}
	
	function chooseParent(){
		parent.$.modalDialog({
			title : '选择父菜单',
			width : 400,
			height : 600,
			closable : false,
			href : '${ctx}/hxMenu/chooseMenu',
			buttons : [ {
				text : '确定',
				handler : function() {
					var menuId = parent.$.modalDialog.handler.find('#menuId');
					var menuName = parent.$.modalDialog.handler.find('#menuName');
					$("#parentId").val(menuId.val());
					$("#parentName").val(menuName.val());
					parent.$.modalDialog.handler.dialog('close');
				}
			}, {
				text : '取消',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	}
	
	function validateForm(){
		var menuName = $("#menuName").val();
		if(menuName==''||menuName==null){
			alert("菜单名称不能为空");
			return false;
		}else{
			if(menuName.length>15){
				alert("菜单名称的长度不能大于15");
				return false;
			}
		}
		var url = $("#url").val();
		if(url.length>150){
			alert("菜单路径的长度不能大于150");
			return false;
		}
		var iconUrl = $("#iconUrl").val();
		if(iconUrl.length>150){
			alert("菜单图标位置的长度不能大于150");
			return false;
		}
		var sort = $("#sort").val();
		if(sort==''||sort==null){
			alert("序号不能为空");
			return false;
		}else{
			if(sort.length>6){
				alert("序号的长度不能大于6");
				return false;
			}
		}
		var parentId = $("#parentId").val();
		if(parentId==''||parentId==null){
			alert("父菜单不能为空");
			return false;
		}else{
			if(parentId.length>32){
				alert("父菜单的长度不能大于32");
				return false;
			}
		}
		var radios = document.getElementsByName("isLeafNodeButton");
		var isLeafNode = "";
		for (var i = 0; i < radios.length; i++) {
			if (radios[i].checked == true) {
				isLeafNode = radios[i].value;
				$("#isLeafNode").val(isLeafNode);
				break;
			}
		}
		
		var menuDesc = $("#menuDesc").val();
		if(menuDesc.length>2000){
			alert("菜单描述的长度不能大于2000");
			return false;
		}
		return true;
	}
</script>
	<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				新建菜单
			</div>
			<input type="hidden" id="continueToAddFlag" title="继续添加标志位" value="0">
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">菜单名称<font color="red">*</font></td>
					<td width="25%"><input id="menuName" name="menuName" type="text" placeholder="请输入菜单名称" value=""></td>
					<td width="25%" align="center">菜单路径</td>
					<td width="25%"><input id="url" name="url" type="text" placeholder="请输入菜单路径" value=""></td>
				</tr>
				<tr>
					<td width="25%" align="center">菜单图标位置</td>
					<td width="25%"><input id="iconUrl" name="iconUrl" type="text" placeholder="请输入菜单图标位置" value=""></td>
					<td width="25%" align="center">序号<font color="red">*</font></td>
					<td width="25%"><input id="sort" name="sort" type="text" placeholder="请输入序号" value=""></td>
				</tr>
				<tr>
					<td width="25%" align="center">父菜单<font color="red">*</font></td>
					<td width="25%"><input id="parentId" name="parentId" type="hidden"><input id="parentName" name="parentName" type="text" onFocus="chooseParent();" placeholder="请选择父菜单" value="" readonly></td>
					<td width="25%" align="center">是否叶子节点<font color="red">*</font></td>
					<td width="25%"><input id="isLeafNode" name="isLeafNode" type="hidden" /><input name="isLeafNodeButton" type="radio" value="1" checked >是</input><input name="isLeafNodeButton" type="radio" value="0">否</input></td>
				</tr>
				<tr>
					<td width="25%" align="center">菜单描述</td>
					<td width="25%" colspan="3"><textarea id="menuDesc" name="menuDesc" placeholder="请输入菜单描述" rows="3" cols="52" ></textarea></td>
				</tr>
			</table>
	</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton"  data-options="toggle:true,group:'g1',iconCls:'icon-add'" onclick="continueToAdd();">继续添加</a>
	    	<a href="#" class="easyui-linkbutton"  data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>