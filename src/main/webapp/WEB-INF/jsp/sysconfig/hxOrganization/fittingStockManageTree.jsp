<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<script type="text/javascript"
	src="${ctx}/js/jquery.ztree.all-3.5.min.js"></script>
<SCRIPT type="text/javascript">
	var settingFittingStock = {
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
			onCheck : onFittingStockCheck
		}
	};

	var zNodes;

	var fittingStockUrl = "${ctx}/hxOrganization/getFittingStockTree?cs=" + Math.random();
	$.getJSON(fittingStockUrl, function(data) {
		if (data) {
			zNodes = data;
			$.fn.zTree.init($("#fittingStockTreeDemo"), settingFittingStock, zNodes);
		}
	});

	function onFittingStockCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("fittingStockTreeDemo");
		nodes = zTree.getCheckedNodes(true);
		v = "";
		fittingStockArr = [];
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].id + ",";
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		var resu = $("#partId");
		resu.attr("value", v);
	}

	function initFittingStockZtree() {
		var zTree;
		$.getJSON(fittingStockUrl, function(data) {
			if (data) {
				zNodes = data;
				$.fn.zTree.init($("#fittingStockTreeDemo"), settingFittingStock, zNodes);
				zTree = $.fn.zTree.getZTreeObj("fittingStockTreeDemo");
				var orgCode = $("#orgCode").val();
				var url = "${ctx}/hxOrganization/getFittingStockTreeData?orgCode=" + orgCode;
				$.getJSON(url, function(data) {
					if (data) {
						if (data != "" && data != null) {
							//选中用户时，同时将该用户的positionid放到隐藏域中
							var resu = $("#partId");
							var partIdTemp = "";
							for(var i=0; i<data.length; i++)  
							{  
								partIdTemp += data[i].id +",";
								var node = zTree.getNodeByParam("id", data[i].id,
										null);
								if (node != null) {
									zTree.selectNode(node, true);//选中节点
									node.checked = true;
									zTree.updateNode(node);//复选框勾选
								}
							}  
							resu.attr("value", partIdTemp);
						}
					}
				});
			}
		});

	}

	$(function() {
		//初始化菜单树
		initFittingStockZtree();
	});
	
	function submitForm(){
		var flag = true;
		var partId = $("#partId").val();
		if(partId==''||partId==null){
			alert("请选择分管分部");
			flag = false;
		}
		if(flag){
			$.post("${ctx}/hxOrganization/addFittingStockPart",$('#form').serialize(),
					function(msg){
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('提示:','保存成功!');
						}
			});
		}
	}
	
	function goBack(){
		window.close();
	}
	
</SCRIPT>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				配件库分管权限配置
			</div>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">配件库名称:</td>
					<td width="25%"><label>${organization.orgName} </label></td>
				</tr>
				<tr>
					<td colspan="2">
						<div
							style="width: 300px; height: 500px; overflow-x: hidden; overflow-y: auto; border:solid 1px #E6E2E2;text-align:left;padding-left:1.2em;padding-right:1.2em;border-style:solid;margin-top:0.25em;margin-bottom:0.25em;">
							<ul id="fittingStockTreeDemo" class="ztree"></ul>
						</div> <input type="hidden" id="orgCode" name="orgCode"  value="${organization.orgCode}"/> <input type="hidden"
						id="partId" name="partId"/>
					</td>

				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-cancel'" onclick="goBack();">关闭</a>
	</div>
</div>
