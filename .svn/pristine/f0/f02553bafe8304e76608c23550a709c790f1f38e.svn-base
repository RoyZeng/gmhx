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
	
	var settingRoleCategory = {
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
				onCheck : onRoleCategory
			}
		};
	var zNodes;

	var menuUrl = "${ctx}/hxRoleMenu/getMenuTree?cs=" + Math.random();
	//var menuUrl = "${ctx}/hxPositionMenu/getMenuTree?cs=" + Math.random();
	$.getJSON(menuUrl, function(data) {
		if (data) {
			zNodes = data;
			$.fn.zTree.init($("#menuTreeDemo"), settingMenu, zNodes);
		}
	}); 
	var fittingAuthUrl = "${ctx}/hxRoleMenu/getFittingAuthTree?cs=" + Math.random();
	$.getJSON(fittingAuthUrl, function(data) {
		if (data) {
			zNodes = data;
			$.fn.zTree.init($("#fittingAuthDemo"), settingFittingAuth, zNodes);
		}
	});
	var roleCategoryUrl = "${ctx}/hxRoleMenu/getRoleCategoryTree?cs=" + Math.random();
	$.getJSON(roleCategoryUrl, function(data) {
		if (data) {
			zNodes = data;
			$.fn.zTree.init($("#roleCategoryDemo"), settingRoleCategory, zNodes);
		}
	});
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
	
	function onRoleCategory(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("roleCategoryDemo");
		nodes = zTree.getCheckedNodes(true);
		v = "";
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].id + ",";
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		var resu = $("#roleCategoryId");
		resu.attr("value", v);
	}

	function initZtree() {
		//从新载入menutree
		var zTree;
		$.getJSON(menuUrl, function(data) {
			if (data) {
				zNodes = data;
				$.fn.zTree.init($("#menuTreeDemo"), settingMenu, zNodes);
				zTree = $.fn.zTree.getZTreeObj("menuTreeDemo");
				var roleId = $("#roleId").val();
				var url = "${ctx}/hxRoleMenu/getMenuTreeData?roleId=" + roleId+"&cs=" + Math.random();
				$.getJSON(url, function(data) {
					if (data) {
						if (data != "" && data != null) {
							//选中用户时，同时将该用户的roleid放到隐藏域中
							var resu = $("#menuId");
							var roleIdTemp = "";
							for(var i=0; i<data.length; i++)  
							{  
								roleIdTemp += data[i].id +",";
								var node = zTree.getNodeByParam("id", data[i].id,
										null);
								if (node != null) {
									zTree.selectNode(node, true);//选中节点
									node.checked = true;
									zTree.updateNode(node);//复选框勾选
								}
							}  
							resu.attr("value", roleIdTemp);
						}
					}
				});
			}
		});
		$.getJSON(fittingAuthUrl, function(data) {
			if (data) {
				zNodes = data;
				$.fn.zTree.init($("#fittingAuthDemo"), settingFittingAuth, zNodes);
				fittingAuthTree = $.fn.zTree.getZTreeObj("fittingAuthDemo");
				var roleId = $("#roleId").val();
				var url = "${ctx}/hxRoleMenu/getFittingAuthTreeData?roleId=" + roleId+"&cs=" + Math.random();
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
	
		$.getJSON(roleCategoryUrl, function(data) {
			if (data) {
				zNodes = data;
				$.fn.zTree.init($("#roleCategoryDemo"), settingRoleCategory, zNodes);
				zTree = $.fn.zTree.getZTreeObj("roleCategoryDemo");
				var roleId = $("#roleId").val();
				var url = "${ctx}/hxRoleMenu/getRoleCategoryTreeData?roleId="+roleId+"&cs=" + Math.random();
				$.getJSON(url, function(data) {
					if (data) {
						if (data != "" && data != null) {
							var resu = $("#roleCategoryId");
							var roleCategoryIdTemp = "";
							for(var i=0; i<data.length; i++)  
							{  
								roleCategoryIdTemp += data[i].id +",";
								var node = zTree.getNodeByParam("id", data[i].id,
										null);
								if (node != null) {
									zTree.selectNode(node, true);//选中节点
									node.checked = true;
									zTree.updateNode(node);//复选框勾选
								}
							}  
							resu.attr("value", roleCategoryIdTemp);
						}
					}
				});
			}
		});
	}

	$(function() {
		//初始化菜单树
		initZtree();
		parent.$.messager.progress('close');
	});
	
	function submitForm(){
		$.post("${ctx}/hxRoleMenu/addRoleMenu",$('#form').serialize(),
		function(msg){
			if($.parseJSON(msg).flag == "success"){
				$.messager.alert('提示:','保存成功!');
			}
		});
	}
	
	function goBack(){
		window.location.href="${ctx}/hxRole/roleView";
	}
	
</SCRIPT>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				配置角色所使用的菜单
			</div>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">角色名称</td>
					<td width="25%" colspan="3"><input type="hidden" id="roleId" name="roleId" value="${role.roleId}"/> <label>${role.roleName} </label></td>
				</tr>
				<tr>
					<td>
						<strong>岗位品类</strong><div id="positionCategoryDiv"
							style="width: 300px; height: 500px; overflow-x: hidden; overflow-y: auto; border:solid 1px #E6E2E2;text-align:left;padding-left:1.2em;padding-right:1.2em;border-style:solid;margin-top:0.25em;margin-bottom:0.25em;">
							<ul id="roleCategoryDemo" class="ztree"></ul>
						</div> <input type="hidden"
						id="roleCategoryId" name="roleCategoryId"/>
					</td>
					<td>
						<strong>菜单权限</strong><div
							style="width: 300px; height: 500px; overflow-x: hidden; overflow-y: auto; border:solid 1px #E6E2E2;text-align:left;padding-left:1.2em;padding-right:1.2em;border-style:solid;margin-top:0.25em;margin-bottom:0.25em;">
							<ul id="menuTreeDemo" class="ztree"></ul>
						</div> <input type="hidden" id="roleId" value="${role.roleId}"/> <input type="hidden"
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
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>
