<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<script type="text/javascript"
	src="${ctx}/js/jquery.ztree.all-3.5.min.js"></script>
<SCRIPT type="text/javascript">
	var settingOrganization = {
		data : {
			simpleData : {
				enable : true
			}
		},
		
		callback : {
			onClick: zTreeOnClick
		}
	};

	var zNodes;

	var organizationUrl = "${ctx}/hxOrganization/getOrganizationTree?cs=" + Math.random();
	$.getJSON(organizationUrl, function(data) {
		if (data) {
			zNodes = data;
			$.fn.zTree.init($("#organizationTreeDemo"), settingOrganization, zNodes);
		}
	});

	function zTreeOnClick(e, treeId, treeNode) {
		var pageMarkup = "tree";//页面标记位
		var orgCode = treeNode.id;
		var fromType = treeNode.fromType;
		window.location.href="${ctx}/hxOrganization/showView/"+orgCode+"/"+pageMarkup+"?fromType="+fromType;
	}

	function toList(){
		window.location.href="${ctx}/hxOrganization/organizationView";
	}
	
	function add(){
		var pageMarkup = "tree";//页面标记位
		window.location.href="${ctx}/hxOrganization/addView/"+pageMarkup;
	}
	
</SCRIPT>
<div class="easyui-panel" title="组织机构树形列表" style="width:auto">
		<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%">
				<tr>
					<td colspan="2">
						<div
							style="width: 300px; height: 500px; overflow-x: hidden; overflow-y: auto; border:solid 1px #E6E2E2;text-align:left;padding-left:1.2em;padding-right:1.2em;border-style:solid;margin-top:0.25em;margin-bottom:0.25em;">
							<ul id="organizationTreeDemo" class="ztree"></ul>
						</div> 
					</td>

				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-excel'" onclick="toList();">列表</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-add'" onclick="add();">新建</a>
	</div>
</div>
