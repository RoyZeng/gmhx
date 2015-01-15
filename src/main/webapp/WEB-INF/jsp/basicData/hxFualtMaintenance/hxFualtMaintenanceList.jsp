<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<script type="text/javascript"
	src="${ctx}/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
		var setting = {
			async: {
				enable: true,
				url:"${ctx}/maintenance/getHxMaintenanceTree"
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
			$("#showDiv input[name='classifyName']").val(treeNode.name);
			$("#showDiv input[name='category']").val(treeNode.category);
			$("#categoryId").val(treeNode.categoryId);
			$("#showDiv input[name='chose']").val(treeNode.chose);
			$("#choseId").val(treeNode.choseId);
			$("#showDiv input[name='validity']").val(treeNode.validity);
			$("#validityId").val(treeNode.validityId);
			$("#showDiv input[name='arrangeNumber']").val(treeNode.arrangeNumber);
			$("#showDiv input[name='maintenanceCosts']").val(treeNode.maintenanceCosts);
			$("#showDiv input[name='modifier']").val(treeNode.modifier);
			$("#showDiv input[name='modDate']").val(treeNode.modDate);
			$("#showDiv input[name='classifyCode']").val(treeNode.id);
			if(treeNode.getParentNode() != null){
				$("#showDiv input[name='parentCode']").val(treeNode.getParentNode().name);
				$("#parentId").val(treeNode.getParentNode().id);
			}else{
				$("#showDiv input[name='parentCode']").val("");
				$("#parentId").val("0");
			}
			$("#showDiv").css({"display":""});
		}
		
		function create(){
			$("#createDiv").css({"display":""});
			$("#updateDiv").css({"display":"none"});
			//$("#createDiv input[name='classifyCode']").val($("#showDiv input[name='parentCode']").val());
			$("#createDiv input[name='parentCode']").val($("#showDiv input[name='classifyCode']").val());
			$("#createDiv input[name='category']").val($("#showDiv input[name='category']").val());
		}
		
// 		跳转主界面
	 function ListMain(){
		
		  window.location.href="${ctx}/maintenance/ListMainView/";  
	 }
	 
// 	 新建保存
		function save1(){
			if($.trim($("#createDiv input[name='classifyName']").val())==""||
				$.trim($("#createDiv input[name='classifyCode']").val()) == ""){
				$.messager.show({
					title:'我的消息',
					msg:'不能为空',
					timeout:2000,
					showType:'slide'
				});
				return;
			}
			$.post("${ctx}/maintenance/createHxMaintenance",{
				category :$("#createDiv input[name='category']").val(),
				classifyName : $.trim($("#createDiv input[name='classifyName']").val()),
				classifyCode : $.trim($("#createDiv input[name='classifyCode']").val()),
				maintenanceCosts :$("#createDiv input[name='maintenanceCosts']").val(),
				arrangeNumber :$.trim($("#createDiv input[name='arrangeNumber']").val()),
				chose :$("#createDiv input[name='chose']").val(),
				validity :$("#createDiv input[name='validity']").val(),
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
		
// 	 修改保存
		function save2(){
			if($.trim($("#updateDiv input[name='classifyName']").val())==""||
				$.trim($("#updateDiv input[name='classifyCode']").val()) == ""){
				$.messager.show({
					title:'我的消息',
					msg:'不能为空',
					timeout:2000,
					showType:'slide'
				});
				return;
			}
			$.post("${ctx}/maintenance/updateHxMaintenance",{
			
				classifyName : $.trim($("#updateDiv input[name='classifyName']").val()),
				classifyCode : $.trim($("#updateDiv input[name='classifyCode']").val()),
				classifyCodeOld : $("#showDiv input[name='classifyCode']").val(),
				parentCode : $("#parentId").val(),
				category :$("#updateDiv input[name='category']").val(),
				maintenanceCosts :$("#updateDiv input[name='maintenanceCosts']").val(),
				arrangeNumber :$.trim($("#updateDiv input[name='arrangeNumber']").val()),
				chose :$("#updateDiv input[name='chose']").val(),
				validity :$("#updateDiv input[name='validity']").val()
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
 					$("#showDiv input[name='category']").val($("#updateDiv #updateCategory").combo('getText'));
					$("#showDiv input[name='maintenanceCosts']").val($("#updateDiv input[name='maintenanceCosts']").val());
					$("#showDiv input[name='arrangeNumber']").val($("#updateDiv input[name='arrangeNumber']").val());
					$("#showDiv input[name='chose']").val($("#updateDiv #updateChose").combo('getText'));
					$("#showDiv input[name='validity']").val($("#updateDiv #updateValidity").combo('getText'));
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
 			$("#updateDiv input[name='parentCode']").val($("#showDiv input[name='parentCode']").val());
 			
 			$("#updateDiv input[name='maintenanceCosts']").val($("#showDiv input[name='maintenanceCosts']").val());
 			$("#updateDiv input[name='arrangeNumber']").val($("#showDiv input[name='arrangeNumber']").val());
 		
			$("#updateDiv input[name='category']").val($("#categoryId").val());
			$("#updateDiv input[name='category']").parent().find("input").eq(0).val($("#showDiv input[name='category']").val());
			$("#updateDiv input[name='chose']").val($("#choseId").val());
			$("#updateDiv input[name='chose']").parent().find("input").eq(0).val($("#showDiv input[name='chose']").val());
			$("#updateDiv input[name='validity']").val($("#validityId").val());
			$("#updateDiv input[name='validity']").parent().find("input").eq(0).val($("#showDiv input[name='validity']").val());
			

		
		
	
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
		<div class="zTreeDemoBackground left"
			style="float: left; width: 250px;">
			<div   style="background-color: #95b8e7; text-align: center;;; color: #fff; font-weight: bold; border-bottom: 1px solid #000;" >
				分类结构管理<div align="right"><a href="#" align="right"  class="easyui-linkbutton"  data-options="iconCls:'icon-redo'" onclick="ListMain()">列表</a></div>
			</div>
			<ul id="treeDemo" class="ztree" style="background-color: #eff5ff"></ul>
		</div>
		<div id="showDiv" class="right"
			style="float: left; width: 400px; margin-top: 20px; margin-left: 20px; display: none;">
			<div
				style="background-color: #95b8e7; text-align: center; color: #fff; font-weight: bold; border-bottom: 1px solid #000;">分类明细</div>
			<table cellSpacing=1 cellPadding=3 width=100% bgColor=#eff5ff
				border=0>
				<tr>
					<td width="30%" align="center">品类:</td>
					<td width="70%"><input type="text" name="category"
						disabled="disabled" size="34"> <input type="hidden"
						id="categoryId"></td>
				</tr>
				<tr>
					<td width="30%" align="center">分类名称:</td>
					<td width="70%"><input type="text" name="classifyName"
						disabled="disabled" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">分类代码:</td>
					<td width="70%"><input type="text" name="classifyCode"
						disabled="disabled" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">维修费用:</td>
					<td width="70%"><input type="text" name="maintenanceCosts"
						disabled="disabled" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">排列序号:</td>
					<td width="70%"><input type="text" name="arrangeNumber"
						disabled="disabled" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">是否选择:</td>
					<td width="70%"><input type="text" name="chose"
						disabled="disabled" size="34"> <input type="hidden"
						id="choseId"></td>

				</tr>
				<tr>
					<td width="30%" align="center">是否有效:</td>
					<td width="70%"><input type="text" name="validity"
						disabled="disabled" size="34"> <input type="hidden"
						id="validityId"></td>
				</tr>
				<tr>
					<td width="30%" align="center">上级名称:</td>
					<td width="70%"><input type="text" name="parentCode"
						disabled="disabled" size="34"> <input type="hidden"
						id="parentId"></td>
				</tr>
				<tr>
					<td width="30%" align="center">修改人:</td>
					<td width="70%"><input type="text" name="modifier"
						disabled="disabled" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">修改时间:</td>
					<td width="70%"><input type="text" name="modDate"
						disabled="disabled" size="34"></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><a href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						onclick="create();">新建</a> <a href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-edit'" onclick="update();">修改</a> <a
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-back'" onclick="cancel1();">返回</a></td>
				</tr>
			</table>
		</div>
		<div id="createDiv" class="right"
			style="float: left; width: 400px; margin-top: 20px; margin-left: 20px; display: none;">
			<div
				style="background-color: #95b8e7; text-align: center; color: #fff; font-weight: bold; border-bottom: 1px solid #000;">新建分类</div>
			<table cellSpacing=1 cellPadding=3 width=100% bgColor=#eff5ff
				border=0>
				<tr>
					<td width="30%" align="center">品类:</td>
					<td width="70%"><input class="easyui-combobox" name="category"
						data-options="url:'${ctx}/hxCode/getCombobox/wxpl',editable:false" /></td>
				</tr>
				<tr>
					<td width="30%" align="center">分类名称:<font color="red">*</font>:
					</td>
					<td width="70%"><input type="text" name="classifyName"
						data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">分类代码:<font color="red">*</font>:
					</td>
					<td width="70%"><input type="text" name="classifyCode"
						data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">维修费用:<font color="red">*</font>:
					</td>
					<td width="70%"><input type="text" name="maintenanceCosts"
						data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">排列序号:</td>
					<td width="70%"><input type="text" name="arrangeNumber"
						data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">是否选择:</td>
					<td width="70%"><input class="easyui-combobox" name="chose"
						data-options="url:'${ctx}/hxCode/getCombobox/sf',editable:false" /></td>
				</tr>
				<tr>
					<td width="30%" align="center">是否有效:</td>
					<td width="70%"><input class="easyui-combobox" name="validity"
						data-options="url:'${ctx}/hxCode/getCombobox/sf',editable:false" /></td>
				</tr>
				<tr>
					<td width="30%" align="center">上级名称:</td>
					<td width="70%"><input type="text" name="parentCode" size="34"
						disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><a href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-save'"
						onclick="save1()">保存</a> <a href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-back'" onclick="cancel2();">返回</a></td>
				</tr>
			</table>
		</div>
		<div id="updateDiv" class="right"
			style="float: left; width: 400px; margin-top: 20px; margin-left: 20px; display: none;">
			<div
				style="background-color: #95b8e7; text-align: center; color: #fff; font-weight: bold; border-bottom: 1px solid #000;">修改分类</div>
			<table cellSpacing=1 cellPadding=3 width=100% bgColor=#eff5ff
				border=0>
				<tr>
					<td width="30%" align="center">品类:</td>
					<td width="70%"><input id="updateCategory" class="easyui-combobox" name="category" data-options="url:'${ctx}/hxCode/getCombobox/wxpl'" /></td>
				</tr>
				<tr>
					<td width="30%" align="center">分类名称:<font color="red">*</font>:
					</td>
					<td width="70%"><input type="text" name="classifyName"
						data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">分类代码:<font color="red">*</font>:
					</td>
					<td width="70%"><input type="text" name="classifyCode"
						data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">维修费用:</td>
					<td width="70%"><input type="text" name="maintenanceCosts"
						data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">排列序号:</td>
					<td width="70%"><input type="text" name="arrangeNumber"
						data-options="required:true" size="34"></td>
				</tr>
				<tr>
					<td width="30%" align="center">是否选择:</td>
					<td width="70%"><input id="updateChose" class="easyui-combobox" name="chose"
						data-options="url:'${ctx}/hxCode/getCombobox/sf'" /></td>
				</tr>
				<tr>
					<td width="30%" align="center">是否有效:</td>
					<td width="70%"><input id="updateValidity" class="easyui-combobox" name="validity"
						data-options="url:'${ctx}/hxCode/getCombobox/sf'" /></td>
				</tr>
				<tr>
					<td width="30%" align="center">上级名称:</td>
					<td width="70%"><input type="text" name="parentCode" size="34"
						disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><a href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-save'"
						onclick="save2()">保存</a> <a href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-back'" onclick="cancel3();">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>