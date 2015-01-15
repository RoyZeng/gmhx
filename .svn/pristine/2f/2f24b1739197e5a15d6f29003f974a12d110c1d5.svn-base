<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="${ctx}/js/jquery.ztree.all-3.5.min.js"></script>
<style type="text/css">
	a:hover {
	 	font-weight: bold
	}
	.ztree {padding:0px 5px 0px 0px;}
	.ztree li a.level0 {width:100%;height: 20px; text-align: center; display:block; background-color: #eff5ff; border:1px silver solid;}
	.ztree li a.level0.cur {background-color: #eff5ff;}
	.ztree li a.level0 span {display: block; color: #0e2d5f; padding-top:3px; font-size:12px; font-weight: bold;word-spacing: 2px;}
	.ztree li a.level0 span.button {	float:right; margin-left: 10px; visibility: visible;display:none;}
	.ztree li span.button.switch.level0 {display:none;}
</style>
<script type="text/javascript">
	var curMenu = null, zTree_Menu = null;
	var setting = {
		//async: {
		//	enable: true,
		//	url:"${ctx}/hxMenu/getMenuTree"
		//},
		view: {
			showLine: false,
			selectedMulti: false,
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onNodeCreated: this.onNodeCreated,
			beforeClick: this.beforeClick,
			onClick : onClick
		}
	};

	var zNodes;
	var url = "${ctx}/hxMenu/getPositionRoleMenuTree?cs="
			+ Math.random();
	$.getJSON(url, function(data) {
		if (data) {
			zNodes = data;
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
			curMenu = zTree_Menu.getNodes()[0].children[0];
			zTree_Menu.selectNode(curMenu);
			var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
			a.addClass("cur");
		}
	});
	
	function onClick(event, treeId, treeNode, clickFlag){
		if(treeNode.isParent) return;
		parent.add(treeNode.lnkUrl, treeNode);
	}
	
	function beforeClick(treeId, node) {
		if (node.isParent) {
			if (node.level === 0) {
				var pNode = curMenu;
				while (pNode && pNode.level !==0) {
					pNode = pNode.getParentNode();
				}
				if (pNode !== node) {
					var a = $("#" + pNode.tId + "_a");
					a.removeClass("cur");
					zTree_Menu.expandNode(pNode, false);
				}
				a = $("#" + node.tId + "_a");
				a.addClass("cur");

				var isOpen = false;
				for (var i=0,l=node.children.length; i<l; i++) {
					if(node.children[i].open) {
						isOpen = true;
						break;
					}
				}
				if (isOpen) {
					zTree_Menu.expandNode(node, true);
					curMenu = node;
				} else {
					zTree_Menu.expandNode(node.children[0].isParent?node.children[0]:node, true);
					curMenu = node.children[0];
				}
			} else {
				zTree_Menu.expandNode(node);
			}
		}
		return !node.isParent;
	}
	
	function goToAddTab(lnkUrl,name){
		parent.addTab(lnkUrl, name);
	}
	
	function goToLoginout(lnkUrl){
		 window.location.href = lnkUrl;
	}
	
	setInterval(function(){getWorks()},60000);//间隔一分钟取一次当前的任务数
	
	function getWorks(){
		var getWorksUrl = "${ctx}/index/getWorks?cs="
			+ Math.random();
		$.getJSON(getWorksUrl, function(data) {
		if (data) {
			$('#works').text(data[0].works);
		}
	});
	}
	
</script>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<div width="100%">
			<table width="100%">
				<tr>
					<td colspan="2">
						操作列表
					</td>
				</tr>
				<tr>
					<td>
						<a id="a_1" title="系统首页" style="" onclick="goToAddTab('${ctx}/index/getMain','首页');" href="#">
							●<span id="treeDemo_2_span">系统首页</span>
						</a>
					</td>
					<td>
						<a id="a_2" title="我的工作" style="" onclick="goToAddTab('${ctx}/hxWorkFlow/list','我的工作列表');" href="#">
							●<span id="treeDemo_2_span">我的工作</span>
						</a>
					</td>
				</tr>
				<tr>
				  <c:if test="${fromType!=1}">
					<td>
						<a id="a_3" title="个人设置" style="" onclick="goToAddTab('${ctx}/personalSetting/personView','个人设置');" href="#">
							●<span id="treeDemo_2_span">个人设置</span>
						</a>
					</td>
					<td>
						<a id="a_4" title="退出系统" style="" onclick="goToLoginout('${ctx}/loginout');" href="#">
							●<span id="treeDemo_2_span">退出系统</span>
						</a>
					</td>
				  </c:if>
				  <c:if test="${fromType==1}">
					<td colspan="2"> 
						<a id="a_5" title="退出系统" style="" onclick="goToLoginout('${ctx}/loginout');" href="#">
							●<span id="treeDemo_2_span">退出系统</span>
						</a>
					</td>
				  </c:if>
				</tr>
				<tr>
					<td colspan="2">
						<font color="red">当前任务数量：<a id="a_6" onclick="goToAddTab('${ctx}/hxWorkFlow/list','我的工作列表');" href="#"><span id="works">${works}</span></a></font>
					</td>
				</tr>
			</table>
		</div>
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>