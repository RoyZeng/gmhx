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
	});
function initPositionCategoryZtree() {
	var zTree;
	$.getJSON(positionCategoryUrl, function(data) {
		if (data) {
			zNodes = data;
			$.fn.zTree.init($("#positionCategoryDemo"), settingPositionCategory, zNodes);
			zTree = $.fn.zTree.getZTreeObj("positionCategoryDemo");
			var positionCode = $("#positionCode").val();
			var url = "${ctx}/hxPosition/getPositionCategoryTreeData?positionId="+positionCode;
			$.getJSON(url, function(data) {
				if (data) {
					if (data != "" && data != null) {
						var resu = $("#positionCategoryId");
						var positionCategoryIdTemp = "";
						for(var i=0; i<data.length; i++)  
						{  
							positionCategoryIdTemp += data[i].id +",";
							var node = zTree.getNodeByParam("id", data[i].id,
									null);
							if (node != null) {
								zTree.selectNode(node, true);//选中节点
								node.checked = true;
								zTree.updateNode(node);//复选框勾选
							}
						}  
						resu.attr("value", positionCategoryIdTemp);
					}
				}
			});
		}
	});
} */
	$(function() {
		parent.$.messager.progress('close');
		/* var positionType = $("#positionType").val();
		if(positionType==1){
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
		$('#positionType').combobox({
			 onSelect: function (record) {
			 	var positionType = record.value;
			 	//清空之前的值
				$("#orgZbId").combobox('setValue','');
				$("#orgFbId").combobox('setValue','');
				$("#orgWdId").combobox('setValue','');
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
		});
		
		initPositionCategoryZtree(); 
		var fromType = $("#fromType").val();
		if(fromType=='1'){
			$('#positionType').combobox('disable');   //不可用
		}
		*/
	});
	
	function submitForm(){
		if(validateForm()){
			$.post("${ctx}/hxPosition/updatePosition",$('#form').serialize(),
					function(msg){
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('提示:','修改成功!');
						}else if($.parseJSON(msg).flag == "duplicatekey"){
							$.messager.alert('提示:','修改失败,岗位代码重复!');
						}
			});
		}
	}
	
	function goBack(){
		var origin = $("#positionOrigin").val();
		window.location.href="${ctx}/hxPosition/positionView?origin="+origin;
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
		
		var roleId = $('#roleId').combobox('getValues');
		if(roleId==''||roleId==null){
			alert("关联角色不能为空");
			return false;
		}
		
		var fromType = $("#fromType").val();
		if (fromType == '0') {
			var orgId = $('#orgId').combobox('getValues');
			if (orgId == '' || orgId == null) {
				alert("组织机构不能为空");
				return false;
			}
		}

		/* var positionType = $('#positionType').combobox('getValues');
		if(positionType==''||positionType==null){
			alert("岗位类型不能为空");
			return false;
		} */

		/* var orgZbId = $('#orgZbId').combobox('getValues');
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
		} */
		return true;
	}
</script>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				修改岗位
			</div>
			<input type="hidden" id="fromType" name="fromType" value="${position.fromType}">
			<input type="hidden" value="${origin}" name="positionOrigin" id="positionOrigin" title="请求来源标记位"/>
			<input id="oldPositionCode" name="oldPositionCode" type="hidden" value="${position.positionCode}" />
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">岗位代码<font color="red">*</font></td>
					<td width="25%"><input id="positionCode" name="positionCode" type="text" placeholder="请输入岗位代码" value="${position.positionCode}" <c:if test="${position.fromType==1}">readonly</c:if>></td>	
					<td width="25%" align="center">岗位名称<font color="red">*</font></td>
					<td width="25%"><input id="positionName" name="positionName" type="text" placeholder="请输入岗位名称"  value="${position.positionName}" <c:if test="${position.fromType==1}">readonly</c:if>></td>
					<!-- 
					<td width="25%" align="center">岗位类型<font color="red">*</font></td>
					<td width="25%"><input id="positionType" name="positionType" type="text" placeholder="请选择岗位类型" editable="false" class="easyui-combobox" class="span2" value="${position.positionType}" data-options="url:'${ctx}/hxCode/getCombobox/gwlx'"  ></td>
					-->
				</tr>
				<tr>
					<td width="25%" align="center">关联角色<font color="red">*</font></td>
					<td width="25%"><input id="roleId" name="roleId" type="text" placeholder="请选择角色" editable="false" class="easyui-combobox" class="span2" value="${position.roleId}" data-options="url:'${ctx}/hxCode/getRoleCombobox'" ></td>
					<c:if test="${position.fromType==1}">
						<td width="25%" align="center">组织机构</td>
						<td width="25%"><input id="positionOrgName" name="positionOrgName" type="text" placeholder="请输入组织机构"  value="${position.positionOrgName}" readonly/></td>
					</c:if>
					<c:if test="${position.fromType==0}">
						<td width="25%" align="center">组织机构<font color="red">*</font></td>
						<td width="25%"><input id="orgId" name="orgId" type="text" placeholder="请选择组织机构" editable="true" class="easyui-combobox" class="span2" value="${position.orgId}" data-options="url:'${ctx}/hxCode/getOrgCombobox'" ></td>	
					</c:if>
				</tr>
				
				<!--
				<tr id="orgZb" style="display:none;">
					<td width="25%" align="center">物料机构<font color="red">*</font></td>
					<td width="25%" colspan="3"><input id="orgZbId" name="orgZbId" type="text" placeholder="请选择物料机构" editable="true" class="easyui-combobox" class="span2" value="${position.orgId}" data-options="url:'${ctx}/hxCode/getZbOrgCombobox'" ></td>	
				</tr>
				<tr id="orgFb" style="display:none;">
					<td width="25%" align="center">物料机构<font color="red">*</font></td>
					<td width="25%" colspan="3"><input id="orgFbId" name="orgFbId" type="text" placeholder="请选择物料机构" editable="true" class="easyui-combobox" class="span2" value="${position.orgId}" data-options="url:'${ctx}/hxCode/getFbOrgCombobox'" ></td>	
				</tr>
				<tr id="orgWd" style="display:none;">
					<td width="25%" align="center">物料机构<font color="red">*</font></td>
					<td width="25%" colspan="3"><input id="orgWdId" name="orgWdId" type="text" placeholder="请选择物料机构" editable="true" class="easyui-combobox" class="span2" value="${position.orgId}" data-options="url:'${ctx}/hxCode/getWdOrgCombobox'" ></td>	
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
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>