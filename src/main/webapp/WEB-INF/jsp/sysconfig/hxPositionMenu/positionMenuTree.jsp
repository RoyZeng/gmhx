<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<script type="text/javascript"
	src="${ctx}/js/jquery.ztree.all-3.5.min.js"></script>
<SCRIPT type="text/javascript">
	var settingMenu = {
		data : {
			simpleData : {
				enable : true
			}
		},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}

		},
		callback : {
			onCheck : onMenuCheck
		}
	};
	
	var settingFittingAuth = {
			data : {
				simpleData : {
					enable : true
				}
			},
			check : {
				enable : true,
				chkStyle : "checkbox",
				chkboxType : {
					"Y" : "ps",
					"N" : "ps"
				}

			},
			callback : {
				onCheck : onFittingAuthCheck
			}
		};

	var zNodes;

	function onMenuCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("menuTreeDemo");
		nodes = zTree.getCheckedNodes(true);
		v = "";
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].id + ",";
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		var resu = $("#menuId");
		resu.attr("value", v);
	}
	
	function onFittingAuthCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("fittingAuthDemo");
		nodes = zTree.getCheckedNodes(true);
		v = "";
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].id + ",";
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		var resu = $("#fittingAuthId");
		resu.attr("value", v);
	}

	function initMenuZtree() {
		
		var positionType = $("#positionType").val();
		if(positionType!='0'){
			var fittingAuthTree;
			$("#menuDiv").css("style","display:none;");
			var fittingAuthUrl = "${ctx}/hxPositionMenu/getFittingAuthTree?cs=" + Math.random()+"&positionType="+positionType;
			if(positionType!='0'){
				$.getJSON(fittingAuthUrl, function(data) {
					if (data) {
						zNodes = data;
						$.fn.zTree.init($("#fittingAuthDemo"), settingFittingAuth, zNodes);
						fittingAuthTree = $.fn.zTree.getZTreeObj("fittingAuthDemo");
						var positionId = $("#positionId").val();
						var url = "${ctx}/hxPositionMenu/getFittingAuthTreeData/" + positionId;
						$.getJSON(url, function(data) {
							if (data) {
								if (data != "" && data != null) {
									var resu = $("#fittingAuthId");
									var fittingAuthIdTemp = "";
									for(var i=0; i<data.length; i++)  
									{  
										fittingAuthIdTemp += data[i].id +",";
										var node = fittingAuthTree.getNodeByParam("id", data[i].id,
												null);
										if (node != null) {
											fittingAuthTree.selectNode(node, true);//选中节点
											node.checked = true;
											fittingAuthTree.updateNode(node);//复选框勾选
										}
									}  
									resu.attr("value", fittingAuthIdTemp);
								}
							}
						});
					}
				});
			}
		}else{
			var zTree;
			$("#fittingAuthDiv").css("style","display:none;");
			var menuUrl = "${ctx}/hxPositionMenu/getMenuTree?cs=" + Math.random();
			$.getJSON(menuUrl, function(data) {
				if (data) {
					zNodes = data;
					$.fn.zTree.init($("#menuTreeDemo"), settingMenu, zNodes);
					zTree = $.fn.zTree.getZTreeObj("menuTreeDemo");
					var positionId = $("#positionId").val();
					var url = "${ctx}/hxPositionMenu/getMenuTreeData/" + positionId;
					$.getJSON(url, function(data) {
						if (data) {
							if (data != "" && data != null) {
								//选中用户时，同时将该用户的positionId放到隐藏域中
								var resu = $("#menuId");
								var menuIdTemp = "";
								for(var i=0; i<data.length; i++)  
								{  
									menuIdTemp += data[i].id +",";
									var node = zTree.getNodeByParam("id", data[i].id,
											null);
									if (node != null) {
										zTree.selectNode(node, true);//选中节点
										node.checked = true;
										zTree.updateNode(node);//复选框勾选
									}
								}  
								resu.attr("value", menuIdTemp);
							}
						}
					});
				}
			});
		}
		
	}

	$(function() {
		//初始化菜单树
		initMenuZtree();
		parent.$.messager.progress('close');

	});
	
	function submitForm(){
		$.post("${ctx}/hxPositionMenu/addPositionMenu",$('#form').serialize(),
		function(msg){
			if($.parseJSON(msg).flag == "success"){
				$.messager.alert('提示:','保存成功!');
			}
		});
	}
	
	function goBack(){
		var fromType = $("#fromType").val();
		var positionCode = $("#positionId").val();
		window.location.href="${ctx}/hxPosition/showView/"+ positionCode+"?fromType="+fromType;
	}
	
</SCRIPT>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				配置岗位所使用的菜单
			</div>
			<input type="hidden" id="positionType" name="positionType" value="${position.positionType}"/>
			<input type="hidden" id="fromType" name="fromType" value="${position.fromType}"/>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">岗位名称:</td>
					<td width="25%"><label>${position.positionName} </label></td>
				</tr>
				<tr>
					<td>
						<strong>菜单权限</strong><div id="menuDiv"
							style="width: 300px; height: 500px; overflow-x: hidden; overflow-y: auto; border:solid 1px #E6E2E2;text-align:left;padding-left:1.2em;padding-right:1.2em;border-style:solid;margin-top:0.25em;margin-bottom:0.25em;">
							<ul id="menuTreeDemo" class="ztree"></ul>
						</div> <input type="hidden" id="positionId" name="positionId" value="${position.positionCode}"/> <input type="hidden"
						id="menuId" name="menuId"/>
					</td>
					<td>
						<strong>物料权限</strong><div id="fittingAuthDiv"
							style="width: 300px; height: 500px; overflow-x: hidden; overflow-y: auto; border:solid 1px #E6E2E2;text-align:left;padding-left:1.2em;padding-right:1.2em;border-style:solid;margin-top:0.25em;margin-bottom:0.25em;">
							<ul id="fittingAuthDemo" class="ztree"></ul>
						</div> <input type="hidden"
						id="fittingAuthId" name="fittingAuthId"/>
					</td>
				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>
