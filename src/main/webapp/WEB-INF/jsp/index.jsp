<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp"%>
<%@ include file="inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>国美恒信售后系统V1.0</title>
		<script type="text/javascript">
			var t;
			var tm;
			function closeAllTabs(t){
				var allTabs = t.tabs('tabs');
				var closeTabsTitle = [];
				$.each(allTabs, function() {
					var opt = this.panel('options');
					closeTabsTitle.push(opt.title);
				});
				for ( var i = 0; i < closeTabsTitle.length; i++) {
					t.tabs('close', closeTabsTitle[i]);
				}
			}
			
			function add(url, treeNode){
				if (t.tabs("exists", treeNode.name)) {
					t.tabs("select", treeNode.name);
				}else{
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍候....'
					});
					closeAllTabs(t);
					t.tabs("add", {
						title : treeNode.name,
						content : "<iframe src='" + url +"' frameborder='0' style='border:0;width:100%;height:100%;'></iframe>",
						cache : true,
						closable : true,
						selected : true,
						border : false,
						fit : true
					});
					parent.$.messager.progress('close');
				}	
			}
			
			function addTab(url, name){
				if (t.tabs("exists", name)) {
					t.tabs("select", name);
				}else{
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍候....'
					});
					closeAllTabs(t);
					t.tabs("add", {
						title : name,
						content : "<iframe src='" + url +"' frameborder='0' style='border:0;width:100%;height:100%;'></iframe>",
						cache : true,
						closable : true,
						selected : true,
						border : false,
						fit : true
					});
					parent.$.messager.progress('close');
				}	
			}
			$(function() {
				t = $('#tabs').tabs({
					fit : true,
					border: false,
					onContextMenu : function(e, title) {
						e.preventDefault();
						tm.menu("show", {
							left : e.pageX,
							top : e.pageY
						}).data("tabTitle", title);
					}
				});
				
				tm = $('#tabsMenu').menu({
					onClick : function(item) {
						var curTabTitle = tm.data('tabTitle');
						var type = $(item.target).attr('title');

						if (type === 'refresh') {
							t.tabs('getTab', curTabTitle).panel('refresh');
							return;
						}

						if (type === 'close') {
							var ct = t.tabs('getTab', curTabTitle);
							if (ct.panel('options').closable) {
								t.tabs('close', curTabTitle);
							}
							return;
						}

						var allTabs = t.tabs('tabs');
						var closeTabsTitle = [];

						$.each(allTabs, function() {
							var opt = this.panel('options');
							if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
								closeTabsTitle.push(opt.title);
							} else if (opt.closable && type === 'closeAll') {
								closeTabsTitle.push(opt.title);
							}
						});

						for ( var i = 0; i < closeTabsTitle.length; i++) {
							t.tabs('close', closeTabsTitle[i]);
						}
					}
				});
				
			});
			//禁用鼠标右键
			document.oncontextmenu=function(e){return false;}
		</script>
	</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false,href:'${ctx}/index/getNorth'" style="height:55px;background:#B3DFDA;padding:0px"></div>
	<div data-options="region:'west',split:true,title:'系统菜单',href:'${ctx}/index/getWest'" style="width:230px;padding:0px;"></div>
	<div region="center" style="overflow: hidden;">
		<div id="tabs" style="overflow: hidden;">
			<div title="首页" data-options="border:false" style="overflow: hidden;">
				<iframe src="${ctx}/index/getMain" id="indexFrame" frameborder="0" style="border: 0; width: 100%; height: 100%;"></iframe>
			</div>
		</div>
	</div>
	
	<div id="tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'icon-reload'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'icon-remove'">关闭</div>
		<div title="closeOther" data-options="iconCls:'icon-remove'">关闭其他</div>
		<div title="closeAll" data-options="iconCls:'icon-remove'">关闭所有</div>
	</div>
</body>
</html>