<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<script type="text/javascript"
	src="${ctx}/js/jquery.ztree.all-3.5.min.js"></script>
<SCRIPT type="text/javascript">
	//var positionTypeArr = [];
	var settingPosition = {
		data : {
			simpleData : {
				enable : true
			}
		},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "",
				"N" : "s"
			}

		},
		callback : {
			onCheck : onPositionCheck
		}
	};

	var zNodes;

	var positionUrl = "${ctx}/hxUserPosition/getPositionTree?cs=" + Math.random();
	$.getJSON(positionUrl, function(data) {
		if (data) {
			zNodes = data;
			$.fn.zTree.init($("#positionTreeDemo"), settingPosition, zNodes);
		}
	});

	function onPositionCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("positionTreeDemo");
		nodes = zTree.getCheckedNodes(true);
		v = "";
		//positionTypeArr = [];
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].id + ",";
			//positionTypeArr.push(nodes[i].positionType);
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		var resu = $("#positionId");
		resu.attr("value", v);
	}

	function initPositionZtree() {
		//从新载入positiontree
		var zTree;
		var fromType = $("#fromType").val();
		
		$.getJSON(positionUrl, function(data) {
			if (data) {
				zNodes = data;
				$.fn.zTree.init($("#positionTreeDemo"), settingPosition, zNodes);
				zTree = $.fn.zTree.getZTreeObj("positionTreeDemo");
				var userId = $("#userLoginName").val();
				var url = "${ctx}/hxUserPosition/getPositionTreeData/" + userId+"?fromType="+fromType;
				$.getJSON(url, function(data) {
					if (data) {
						if (data != "" && data != null) {
							//选中用户时，同时将该用户的positionid放到隐藏域中
							var resu = $("#positionId");
							var positionIdTemp = "";
							for(var i=0; i<data.length; i++)  
							{  
								positionIdTemp += data[i].id +",";
								var node = zTree.getNodeByParam("id", data[i].id,
										null);
								if (node != null) {
									zTree.selectNode(node, true);//选中节点
									node.checked = true;
									zTree.updateNode(node);//复选框勾选
								}
							}  
							resu.attr("value", positionIdTemp);
						}
					}
				});
			}
		});

	}

	$(function() {
		//初始化菜单树
		initPositionZtree();
	});
	
	function submitForm(){
		var flag = true;
		var positionId = $("#positionId").val();
		if(positionId==''||positionId==null){
			alert("请选择岗位");
			flag = false;
		}
		
		if(flag){
			$.post("${ctx}/hxUserPosition/addUserPosition",$('#form').serialize(),
					function(msg){
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('提示:','保存成功!');
						}
			});
		}
	}
	
	function goBack(){
		var userLoginName = $("#userLoginName").val();
		var pageMarkup = $("#pageMarkup").val();
		var fromType = $("#fromType").val();
		var origin = $("#origin").val();
		if(pageMarkup=='organization'||pageMarkup=='position'){
			history.back();
		}else{
			window.location.href="${ctx}/hxUser/showView/"+ userLoginName+"?fromType="+fromType+"&origin="+origin;
		}
		
	}
	
</SCRIPT>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				管理用户岗位
			</div>
			<input type="hidden" id="pageMarkup" title="页面标记位" value="${pageMarkup}">
			<input type="hidden" id="fromType" name="fromType" value="${sysuser.fromType}"/>
			<input type="hidden" value="${origin}" name="origin" id="origin" title="请求来源标记位"/>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">用户姓名:</td>
					<td width="25%"><label>${sysuser.userName} </label></td>
				</tr>
				<tr>
					<td colspan="2">
						<div
							style="width: 300px; height: 500px; overflow-x: hidden; overflow-y: auto; border:solid 1px #E6E2E2;text-align:left;padding-left:1.2em;padding-right:1.2em;border-style:solid;margin-top:0.25em;margin-bottom:0.25em;">
							<ul id="positionTreeDemo" class="ztree"></ul>
						</div> <input type="hidden" id="userLoginName" name="userLoginName"  value="${sysuser.userAccount}"/> <input type="hidden"
						id="positionId" name="positionId"/>
					</td>

				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>
