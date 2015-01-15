
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<script type="text/javascript"
	src="${ctx}/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
	/* var settingPositionCategory = {
		data : {
			simpleData : {
				enable : true
			}
		},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "p",
				"N" : "s"
			}

		},
		callback : {
			onCheck : onPositionCategory
		}
	};
	function onPositionCategory(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("positionCategoryDemo");
		nodes = zTree.getCheckedNodes(true);
		v = "";
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].id + ",";
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		var resu = $("#positionCategoryId");
		resu.attr("value", v);
	}
	
	var zNodes;

	var positionCategoryUrl = "${ctx}/hxPosition/getPositionCategoryTree?cs=" + Math.random();
	$.getJSON(positionCategoryUrl, function(data) {
		if (data) {
			zNodes = data;
			$.fn.zTree.init($("#positionCategoryDemo"), settingPositionCategory, zNodes);
		}
	}); */
	
	$(function() {
		parent.$.messager.progress('close');
		/* $('#positionType').combobox({
			 onSelect: function (record) {
			 	var positionType = record.value;
			    if(positionType==0 || positionType == null ){
			    	$("#orgZb").hide();
			    	$("#orgFb").hide(); 
			    	$("#orgWd").hide();
			    }else if(positionType==1){
			      	$("#orgZb").show();
			      	$("#orgFb").hide();
			      	$("#orgWd").hide();
			    }else if(positionType==2){
			    	$("#orgZb").hide();
			      	$("#orgFb").show();
			      	$("#orgWd").hide();
			    }else if(positionType==3){
			    	$("#orgZb").hide();
			      	$("#orgFb").hide();
			      	$("#orgWd").show();
			    }
			}
		 }); */
	});
	
	function submitForm(){
		if(validateForm()){
			var origin = $("#positionOrigin").val();
			$.post("${ctx}/hxPosition/addPosition",$('#form').serialize(),
					function(msg){
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('提示:','保存成功!');
							var continueToAddFlag = $("#continueToAddFlag").val();
							if(continueToAddFlag=="1"){//如果是继续添加则会执行页面跳转
								window.location.href="${ctx}/hxPosition/addView?origin="+origin;
							}else{
								window.location.href="${ctx}/hxPosition/positionView?origin="+origin;
							}
						}else if($.parseJSON(msg).flag == "duplicatekey"){
							$.messager.alert('提示:','添加失败,岗位代码重复!');
						}
			});
		}
		
	}
	
	function goBack(){
		var origin = $("#positionOrigin").val();
		window.location.href="${ctx}/hxPosition/positionView?origin="+origin;
	}
	
	function continueToAdd(){
		//继续添加标志位置1
		$("#continueToAddFlag").val("1");
		submitForm();
	}
	
	function validateForm(){
		
		var positionCode = $("#positionCode").val();
		if(positionCode==''||positionCode==null){
			alert("岗位代码不能为空");
			return false;
		}else{
			if(positionCode.length>100){
				alert("岗位代码的长度不能大于100");
				return false;
			}else{
				var reg = /^[0-9a-zA-Z]*$/g;
				if(!reg.test(positionCode)){
					alert("岗位代码只能输入字母或数字");
					return false;
				}
			}
		}
		
		var positionName = $("#positionName").val();
		if(positionName==''||positionName==null){
			alert("岗位名称不能为空");
			return false;
		}else{
			if(positionName.length>25){
				alert("岗位名称的长度不能大于25");
				return false;
			}
		}
		//var positionType = $('#positionType').combobox('getValues');
		//if(positionType==''||positionType==null){
		//	alert("岗位类型不能为空");
		//	return false;
		//}
		var roleId = $('#roleId').combobox('getValues');
		if(roleId==''||roleId==null){
			alert("关联角色不能为空");
			return false;
		}
		
		var orgId = $('#orgId').combobox('getValues');
		if(orgId==''||orgId==null){
			alert("组织机构不能为空");
			return false;
		}
		/* 
		var orgZbId = $('#orgZbId').combobox('getValues');
		var orgFbId = $('#orgFbId').combobox('getValues');
		var orgWdId = $('#orgWdId').combobox('getValues');
		if(positionType==1){
			$("#orgId").val(orgZbId);
		}else if(positionType==2){
			$("#orgId").val(orgFbId);
		}else if(positionType==3){
			$("#orgId").val(orgWdId);
		}else{
			//清空之前的值
			$("#orgId").val("");
		} 
		var orgId = $("#orgId").val();
		if(positionType==2||positionType==3){
			if(orgId==''||orgId==null){
				alert("物料机构不能为空");
				return false;
			}
		}
		*/
		return true;
	}
</script>
	<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				新建岗位
			</div>
			<input type="hidden" id="continueToAddFlag" title="继续添加标志位" value="0">
			<input type="hidden" value="${origin}" name="positionOrigin" id="positionOrigin" title="请求来源标记位"/>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">岗位代码<font color="red">*</font></td>
					<td width="25%"><input id="positionCode" name="positionCode" type="text" placeholder="请输入岗位代码" value=""></td>	
					<td width="25%" align="center">岗位名称<font color="red">*</font></td>
					<td width="25%"><input id="positionName" name="positionName" type="text" placeholder="请输入岗位名称" value=""></td>
					<!--  
					<td width="25%" align="center">岗位类型<font color="red">*</font></td>
					<td width="25%"><input id="positionType" name="positionType" type="text" placeholder="请选择岗位类型" editable="false" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getCombobox/gwlx'" ></td>
					-->
				</tr>
				<tr>
					<td width="25%" align="center">关联角色<font color="red">*</font></td>
					<td width="25%"><input id="roleId" name="roleId" type="text" placeholder="请选择角色" editable="false" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getRoleCombobox'" ></td>
					<td width="25%" align="center">组织机构<font color="red">*</font></td>
					<td width="25%"><input id="orgId" name="orgId" type="text" placeholder="请选择组织机构" editable="true" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getOrgCombobox'" ></td>	
				</tr>
				<!--
				<tr id="orgZb" style="display:none;">
					<td width="25%" align="center">物料机构<font color="red">*</font></td>
					<td width="25%" colspan="3"><input id="orgZbId" name="orgZbId" type="text" placeholder="请选择物料机构" editable="true" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getZbOrgCombobox'" ></td>	
				</tr>
				<tr id="orgFb" style="display:none;">
					<td width="25%" align="center">物料机构<font color="red">*</font></td>
					<td width="25%" colspan="3"><input id="orgFbId" name="orgFbId" type="text" placeholder="请选择物料机构" editable="true" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getFbOrgCombobox'" ></td>	
				</tr>
				<tr id="orgWd" style="display:none;">
					<td width="25%" align="center">物料机构<font color="red">*</font></td>
					<td width="25%" colspan="3"><input id="orgWdId" name="orgWdId" type="text" placeholder="请选择物料机构" editable="true" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getWdOrgCombobox'" ></td>	
				</tr>
				  
				<tr>
					<td colspan="4">
						<strong>岗位品类</strong><div id="positionCategoryDiv"
							style="width: 300px; height: 500px; overflow-x: hidden; overflow-y: auto; border:solid 1px #E6E2E2;text-align:left;padding-left:1.2em;padding-right:1.2em;border-style:solid;margin-top:0.25em;margin-bottom:0.25em;">
							<ul id="positionCategoryDemo" class="ztree"></ul>
						</div> <input type="hidden"
						id="positionCategoryId" name="positionCategoryId"/>
					</td>
				</tr>
				-->
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-add'" onclick="continueToAdd();">继续添加</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>