<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
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
		callback: {
			onClick: zTreeOnClick
		}
	};

	var zNodes;

	var menuUrl = "${ctx}/hxPositionMenu/getMenuTree?cs=" + Math.random();
	$.getJSON(menuUrl, function(data) {
		if (data) {
			zNodes = data;
			$.fn.zTree.init($("#menuTreeDemo"), settingMenu, zNodes);
			var treeObj = $.fn.zTree.getZTreeObj("menuTreeDemo");
			treeObj.expandAll(true);

		}
	});

	function zTreeOnClick(event, treeId, treeNode) {
		$("#menuId").val(treeNode.id);
		$("#menuName").val(treeNode.name);
	};

	$(function() {
		//初始化菜单树
		parent.$.messager.progress('close');
	});
	
</SCRIPT>
<div title="" style="width:auto">
		<form id="form" method="post">
			<table width="100%">
				<tr>
					<td colspan="2">
						<div style="overflow-x: hidden; overflow-y: auto; border:solid 1px #E6E2E2;text-align:left;padding-left:1.2em;padding-right:1.2em;border-style:solid;margin-top:0.25em;margin-bottom:0.25em;">
							<ul id="menuTreeDemo" class="ztree"></ul>
						</div><input type="hidden" id="menuId" name="menuId"/><input type="hidden" id="menuName" name="menuName"/>
					</td>

				</tr>
			</table>
		</form>
	</div>
</div>
