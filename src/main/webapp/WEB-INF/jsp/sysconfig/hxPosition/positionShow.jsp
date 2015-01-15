<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	
var dataGrid;
$(function() {
	
	var origin = $("#positionOrigin").val();
	dataGrid = $('#dataGrid').datagrid({
		title : "用户",
		url : "${ctx}/hxPosition/getPositionUserPageList?positionId="+$("#positionId").val()+"&origin="+origin,
		nowrap : false,
		striped : true,
		collapsible : true,
		autoRowHeight : false,
		remoteSort : false,
		idField : 'userId',
		rownumbers : true,
		fitColumns : true,
		pagination : true,
		checkOnSelect : false,
		selectOnCheck : false,
		queryParams : {
			currentPage : 1,
			pageCount : 10
		},
		columns : [ [ {
			field : 'userName',
			title : '用户名称',
			width : 10,
			align:'center',
			formatter:function(value, row, index){ 
				return '<a href="#" onclick="showUser(\'' + row.userLoginName + '\',\'' + row.fromType  + '\');">'+row.userName+'</a>';
            }  
		}, {
			field : 'userOrgName',
			title : '所属机构',
			width : 10,
			align:'center',
			sortable : true
		} ] ],
		toolbar : '#toolbar',
		onLoadSuccess : function() {
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
			parent.$.messager.progress('close');
		}
	});
	$('.datagrid-pager').pagination({
		onSelectPage : function(pageNumber, pageSize) {
			$(this).pagination('loading');
			var queryParams = $.serializeObject($('#searchForm'));
			queryParams.currentPage = pageNumber;
			queryParams.pageCount = pageSize;
			$('#dataGrid').datagrid("options").queryParams = queryParams;
			$('#dataGrid').datagrid("reload");
			$(this).pagination('loaded');
		}
	});
});

function update(){
	var origin = $("#positionOrigin").val();
	var positionCode = $("#positionId").val();
	var fromType = $("#fromType").val();
	window.location.href="${ctx}/hxPosition/updateView/"+ positionCode+"?origin="+origin+"&fromType="+fromType;
}

function goBack(){
	var origin = $("#positionOrigin").val();
	window.location.href="${ctx}/hxPosition/positionView?origin="+origin;
}

function authority(){
	var positionCode = $("#positionId").val();
	var fromType = $("#fromType").val();
	window.location.href="${ctx}/hxPositionMenu/positionMenuView/"+ positionCode+"?fromType="+fromType;
}

function showUser(userLoginName, fromType){
	var pageMarkup = "position";
	var origin = $("#positionOrigin").val();
	window.location.href="${ctx}/hxUser/showView/"+ userLoginName+"?fromType="+fromType+"&pageMarkup="+pageMarkup+"&origin="+origin;
}
</script>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				岗位详细信息
			</div>
			<input id="positionId" type="hidden" value="${position.positionCode}"/>
			<input id="fromType" type="hidden" value="${position.fromType}"/>
			<input type="hidden" value="${position.positionOrigin}" name="positionOrigin" id="positionOrigin" title="请求来源标记位"/>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">岗位代码：</td>
					<td width="25%"><label>${position.positionCode}</label></td>
					<td width="25%" align="center">岗位名称：</td>
					<td width="25%"><label>${position.positionName}</label></td>
					<!--  
					<td width="25%" align="center">岗位类型：</td>
					<td width="25%">
						<c:if test="${position.positionType==0}">
							<label>普通岗</label>
						</c:if>
						<c:if test="${position.positionType==1}">
							<label>总部物料岗</label>
						</c:if>
						<c:if test="${position.positionType==2}">
							<label>分部物料岗</label>
						</c:if>
						<c:if test="${position.positionType==3}">
							<label>网点物料岗</label>
						</c:if>
					</td>
					-->
				</tr>
				<tr>
					<td width="25%" align="center">关联角色：</td>
					<td width="25%"><label>${position.roleName}</label></td>
					<td width="25%" align="center">组织机构：</td>
					<td width="25%"><label>${position.positionOrgName}</label></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-edit'"  onclick="update();">修改</a>
	    	<!-- 
	    		<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="authority();">权限设置</a>
	    	 -->
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
	<div id="main-center" class="easyui-tabs" style="width:auto;height:550px;"> 
		<div title="该岗位下用户列表" style="padding:20px;"> 
			<div data-options="region:'center',border:false">
				<table id="dataGrid"></table>
			</div>
		</div> 
	</div>
</div>