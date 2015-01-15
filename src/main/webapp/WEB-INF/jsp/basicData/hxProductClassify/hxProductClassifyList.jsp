<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
	<script type="text/javascript" src="${ctx}/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript">
		var setting = {
			async: {
				enable: true,
				url:"${ctx}/hxProductClassify/getHxProductClassifyTree"
			},
			view: {
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
		};

		$(function() {
			$.fn.zTree.init($("#treeDemo"), setting);
		});

		function onClick(event, treeId, treeNode){
			/*
			$("#showDiv input[name='classifyName']").val(treeNode.name);
			$("#showDiv input[name='classifyCode']").val(treeNode.id);
			$("#showDiv input[name='power']").val(treeNode.power);
			if(treeNode.getParentNode() != null){
				$("#showDiv input[name='parentCode']").val(treeNode.getParentNode().name);
				$("#parentId").val(treeNode.getParentNode().id);
			}else{
				$("#showDiv input[name='parentCode']").val("");
				$("#parentId").val("0");
			}
			$("#showDiv").css({"display":""});
			*/
		}
		
		function create(){
			$("#createDiv").css({"display":""});
			$("#updateDiv").css({"display":"none"});
			$("#createDiv input[name='parentCode']").val($("#showDiv input[name='classifyName']").val());
		}
		
		function save1(){
			if($.trim($("#createDiv input[name='classifyName']").val())==""||
				$.trim($("#createDiv input[name='classifyCode']").val()) == "" ||
				$("#createDiv input[name='power']").val() == ""){
				$.messager.show({
					title:'我的消息',
					msg:'不能为空',
					timeout:2000,
					showType:'slide'
				});
				return;
			}
			$.post("${ctx}/hxProductClassify/createHxProductClassify",{
				classifyName : $.trim($("#createDiv input[name='classifyName']").val()),
				classifyCode : $.trim($("#createDiv input[name='classifyCode']").val()),
				power : $("#createDiv input[name='power']").val(),
				parentCode : $("#showDiv input[name='classifyCode']").val()
			},
			function(msg){
				if(msg == "success"){
					$.messager.show({
						title:'我的消息',
						msg:'保存成功',
						timeout:2000,
						showType:'slide'
					});
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					treeObj.reAsyncChildNodes(null, "refresh");
				}
			});
		}
		
		function save2(){
			if($.trim($("#updateDiv input[name='classifyName']").val())==""||
				$.trim($("#updateDiv input[name='classifyCode']").val()) == "" ||
				$("#updateDiv input[name='power']").val() == ""){
				$.messager.show({
					title:'我的消息',
					msg:'不能为空',
					timeout:2000,
					showType:'slide'
				});
				return;
			}
			$.post("${ctx}/hxProductClassify/updateHxProductClassify",{
				classifyName : $.trim($("#updateDiv input[name='classifyName']").val()),
				classifyCode : $.trim($("#updateDiv input[name='classifyCode']").val()),
				classifyCodeOld : $("#showDiv input[name='classifyCode']").val(),
				power : $("#updateDiv input[name='power']").val(),
				parentCode : $("#parentId").val()
			},
			function(msg){
				if(msg == "success"){
					$.messager.show({
						title:'我的消息',
						msg:'保存成功',
						timeout:2000,
						showType:'slide'
					});
					$("#showDiv input[name='classifyName']").val($.trim($("#updateDiv input[name='classifyName']").val()));
					$("#showDiv input[name='classifyCode']").val($.trim($("#updateDiv input[name='classifyCode']").val()));
					$("#showDiv input[name='power']").val($("#updateDiv input[name='power']").val());
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					treeObj.reAsyncChildNodes(null, "refresh");
				}else{
					$.messager.show({
						title:'我的消息',
						msg:'保存失败',
						timeout:2000,
						showType:'slide'
					});
				}
			});
		}
		
		function update(){
			$("#createDiv").css({"display":"none"});
			$("#updateDiv").css({"display":""});
			$("#updateDiv input[name='classifyName']").val($("#showDiv input[name='classifyName']").val());
			$("#updateDiv input[name='classifyCode']").val($("#showDiv input[name='classifyCode']").val());
			$("#updateDiv input[name='power']").val($("#showDiv input[name='power']").val());
			$("#updateDiv input[name='power']").parent().find("input").eq(0).val($("#showDiv input[name='power']").val());
			$("#updateDiv input[name='parentCode']").val($("#showDiv input[name='parentCode']").val());
		}
		
		function del(){
			$.messager.confirm('我的消息', '确认要删除吗?', function(r){
				if (r){
					$.post("${ctx}/hxProductClassify/delHxProductClassify",{
						classifyCode : $.trim($("#showDiv input[name='classifyCode']").val())
					},
					function(msg){
						if(msg == "success"){
							$.messager.show({
								title:'我的消息',
								msg:'删除成功',
								timeout:2000,
								showType:'slide'
							});
							$("#showDiv").css({"display":"none"});
							$("#createDiv").css({"display":"none"});
							$("#updateDiv").css({"display":"none"});
							var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
							treeObj.reAsyncChildNodes(null, "refresh");
						}else{
							$.messager.show({
								title:'我的消息',
								msg:'保存失败',
								timeout:2000,
								showType:'slide'
							});
						}
					});
				}
			});
		}
		
		function cancel1(){
			$("#showDiv").css({"display":"none"});
		}
		
		function cancel2(){
			$("#createDiv").css({"display":"none"});
		}
		
		function cancel3(){
			$("#updateDiv").css({"display":"none"});
		}
	</script>
</head>
<body>
	<div class="content_wrap">
		<div class="zTreeDemoBackground left" style="float:left; width: 40%;">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">产品分类</div>
			<ul id="treeDemo" class="ztree" style="background-color:#eff5ff"></ul>
		</div>
		<div id="showDiv" class="right" style="float:left; width:400px; margin-top:20px; margin-left:20px; display:none;">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">物料明细</div>
			<table cellSpacing=1 cellPadding=3 width=100% bgColor=#eff5ff border=0>
				<tr>
					<td width="30%" align="center">分类名称:</td>
					<td width="70%"><input type="text" name="classifyName" disabled="disabled" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">分类代码:</td>
					<td width="70%"><input type="text" name="classifyCode" disabled="disabled" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">匹数分类:</td>
					<td width="70%" ><input type="text" name="power" disabled="disabled" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">上级名称:</td>
					<td width="70%">
						<input type="text" name="parentCode" disabled="disabled" size="34">
						<input type="hidden" id="parentId">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="create();">新建</a>
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="update();">修改</a>
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="del();">删除</a>
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="cancel1();">返回</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="createDiv" class="right" style="float:left; width:400px; margin-top:20px; margin-left:20px; display:none;">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">新建分类</div>
			<table cellSpacing=1 cellPadding=3 width=100% bgColor=#eff5ff border=0>
				<tr>
					<td width="30%" align="center">分类名称<font color="red">*</font>:</td>
					<td width="70%"><input type="text" name="classifyName" placeholder="请输入分类名称" class="easyui-validatebox" data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">分类代码<font color="red">*</font>:</td>
					<td width="70%"><input type="text" name="classifyCode" placeholder="请输入分类代码" class="easyui-validatebox" data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">匹数分类<font color="red">*</font>:</td>
					<td width="70%" ><input class="easyui-combobox" name="power" data-options="url:'${ctx}/hxCode/getCombobox/psfl',editable:false"/></td>
				</tr>
				<tr>
					<td width="30%" align="center">上级名称:</td>
					<td width="70%"><input type="text" name="parentCode" size="34" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save1()">保存</a>
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="cancel2();">返回</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="updateDiv" class="right" style="float:left; width:400px; margin-top:20px; margin-left:20px; display:none;">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">修改分类</div>
			<table cellSpacing=1 cellPadding=3 width=100% bgColor=#eff5ff border=0>
				<tr>
					<td width="30%" align="center">分类名称<font color="red">*</font>:</td>
					<td width="70%"><input type="text" name="classifyName" placeholder="请输入分类名称" class="easyui-validatebox" data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">分类代码<font color="red">*</font>:</td>
					<td width="70%"><input type="text" name="classifyCode" placeholder="请输入分类代码" class="easyui-validatebox" data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">匹数分类<font color="red">*</font>:</td>
					<td width="70%" ><input class="easyui-combobox" name="power" data-options="url:'${ctx}/hxCode/getCombobox/psfl',editable:false"/></td>
				</tr>
				<tr>
					<td width="30%" align="center">上级名称:</td>
					<td width="70%"><input type="text" name="parentCode" size="34" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save2()">保存</a>
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="cancel3();">返回</a>
					</td>
				</tr>
			</table>
		</div>
	</div>	
</body>
</html>