<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程岗位关联</title>
<script type="text/javascript">
   		$(function() {
			$('#ttleft').datagrid({
				title : "${positionMapping.jbpmRoleName}-可选关联岗位",
				url : "${ctx}/hxPositionMapping/getOtherPositionListBYCodeName",
				nowrap : false,
				striped : true,
				//height : document.body.clientHeight - 155,
				height : 'auto',
				collapsible : true,
				autoRowHeight : false,
				remoteSort : false,
				rownumbers : true,
				fitColumns : true,
				pagination : true,
				checkOnSelect : false,
				selectOnCheck : false,
				queryParams : {
					jbpmRoleCode:"${positionMapping.jbpmRoleCode}",
					jbpmRoleName:"${positionMapping.jbpmRoleName}",
					currentPage : 1,
					pageCount : 10
				},
				columns : [ [ {
	                checkbox:true
	            },{
					field : 'positionName',
					title : '岗位名称',
					width : 10,
					align:'center',
					sortable : true
				}, {
					field : 'positionType',
					title : '岗位类型',
					width : 10,
					align:'center',
					formatter:function(value, row, index){ 
						if(row.positionType=='0'){
							return '普通岗';
						}else if(row.positionType=='1'){
							return '总部物料岗';
						}else if(row.positionType=='2'){
							return '分部物料岗';
						}else if(row.positionType=='3'){
							return '网点物料岗';
						}
	                }  
				} , {
					field : 'orgId',
					title : '机构id',
					width : 10,
					align:'center',
					sortable : true
				}, 
				{
					field : 'positionCode',
					title : '岗位编码',
					width : 10,
					align:'center',
					sortable : true
				}, 
				{
					field : 'positionOrigin',
					title : '岗位来源',
					width : 10,
					align:'center',
					sortable : true
				}] ],
				toolbar : '#toolbarleft',
				onLoadSuccess : function() {
					$('#searchForm1 table').show();
					parent.$.messager.progress('close');
				}
			});
			
			$('#ttleft').parent().next('.datagrid-pager').pagination({
				onSelectPage : function(pageNumber, pageSize) {
					$(this).pagination('loading');
					var queryParams = $.serializeObject($('#searchForm1'));
					queryParams.currentPage = pageNumber;
					queryParams.pageCount = pageSize;
					$('#ttleft').datagrid("options").queryParams = queryParams;
					$('#ttleft').datagrid("reload");
					$(this).pagination('loaded');
				}
			});
			$("#query1").on("click", function() {
				$('#ttleft').datagrid('load', $.serializeObject($('#searchForm1')));
			});
			
			$('#ttright').datagrid({
				title : "${positionMapping.jbpmRoleName}-已关联岗位",
				url : "${ctx}/hxPositionMapping/getPositionListBYCodeName",
				nowrap : false,
				striped : true,
				//height : document.body.clientHeight - 155,
				height : 'auto',
				collapsible : true,
				autoRowHeight : false,
				remoteSort : false,
				rownumbers : true,
				fitColumns : true,
				pagination : true,
				checkOnSelect : false,
				selectOnCheck : false,
				queryParams : {
					jbpmRoleCode:"${positionMapping.jbpmRoleCode}",
					jbpmRoleName:"${positionMapping.jbpmRoleName}",
					currentPage : 1,
					pageCount : 10
				},
				columns : [ [ {
	                checkbox:true
	            },{
					field : 'positionName',
					title : '岗位名称',
					width : 10,
					align:'center',
					sortable : true
				}, {
					field : 'positionType',
					title : '岗位类型',
					width : 10,
					align:'center',
					formatter:function(value, row, index){ 
						if(row.positionType=='0'){
							return '普通岗';
						}else if(row.positionType=='1'){
							return '总部物料岗';
						}else if(row.positionType=='2'){
							return '分部物料岗';
						}else if(row.positionType=='3'){
							return '网点物料岗';
						}
	                }  
				} , {
					field : 'orgId',
					title : '机构id',
					width : 10,
					align:'center',
					sortable : true
				}, 
				{
					field : 'positionCode',
					title : '岗位编码',
					width : 10,
					align:'center',
					sortable : true
				}, 
				{
					field : 'positionOrigin',
					title : '岗位来源',
					width : 10,
					align:'center',
					sortable : true
				} ] ],
				//toolbar : '#toolbarright',
				onLoadSuccess : function() {
					$('#searchForm2 table').show();
					parent.$.messager.progress('close');
				}
		});
			
			$('#ttright').parent().next('.datagrid-pager').pagination({
				onSelectPage : function(pageNumber, pageSize) {
					$(this).pagination('loading');
					var queryParams = $.serializeObject($('#searchForm2'));
					queryParams.currentPage = pageNumber;
					queryParams.pageCount = pageSize;
					$('#ttright').datagrid("options").queryParams = queryParams;
					$('#ttright').datagrid("reload");
					$(this).pagination('loaded');
				}
			});
			$("#query2").on("click", function() {
				$('#ttright').datagrid('load', $.serializeObject($('#searchForm2')));
			});
			

	});
   		
   		$.serializeObject = function(form) {
   			var o = {
   				jbpmRoleCode:"${positionMapping.jbpmRoleCode}",
				jbpmRoleName:"${positionMapping.jbpmRoleName}",
   				currentPage : 1,
   				pageCount : 10
   			};
   			$.each(form.serializeArray(), function(index) {
   				if (o[this['name']]) {
   					o[this['name']] = o[this['name']] + "," + this['value'];
   				} else {
   					o[this['name']] = this['value'];
   				}
   			});
   			return o;
   		};
   		
   	function goBack(){
   		window.location.href="${ctx}/hxPositionMapping/positionMappingView";
   	}
   	
   	function searchs() {
		$('#ttleft,#ttright').datagrid('load');
	};
   	
   	function connect(type) {
		var selectRows1 = $("#ttleft").datagrid('getChecked');
		var selectRows2 = $("#ttright").datagrid('getChecked');
		if(type==1 && selectRows1.length == 0){
			$.messager.alert('提示', '请选择左侧您要关联的行!', 'info');
		}else if(type==1 && selectRows1.length > 0){
			$.messager.confirm('确认', '您确认关联所选的 ' + selectRows1.length + ' 数据吗？', function(r) {
				if (r) {
					var positionIds = [];
					for (var i = 0; i < selectRows1.length; i++) {
						positionIds.push(selectRows1[i].positionCode);
					}
					$.post(ctx + "/hxPositionMapping/connect", {
						positionIds : positionIds.join(","),
						type:1,
						jbpmRoleCode:"${positionMapping.jbpmRoleCode}",
						jbpmRoleName:"${positionMapping.jbpmRoleName}",
					}, function(data) {
						$.messager.alert('消息提示', data, 'info');
						searchs();
					});
				}
			});
		}else if(type==2 && selectRows2.length == 0){
			$.messager.alert('提示', '请选择右侧您要解除关联的行!', 'info');
		}else{
			$.messager.confirm('确认', '您确认解除关联所选的 ' + selectRows2.length + ' 数据吗？', function(r) {
				if (r) {
					var positionIds = [];
					for (var i = 0; i < selectRows2.length; i++) {
						positionIds.push(selectRows2[i].positionCode);
					}
					$.post(ctx + "/hxPositionMapping/connect", {
						positionIds : positionIds.join(","),
						type:2,
						jbpmRoleCode:"${positionMapping.jbpmRoleCode}",
						jbpmRoleName:"${positionMapping.jbpmRoleName}",
					}, function(data) {
						$.messager.alert('消息提示', data, 'info');
						searchs();
					});
				}
			});
		}
		
	};
	
	function importExcel() {
		window.location.href = "${ctx}/hxPositionMapping/importView";
	}
	
	function exportExcel() {
		var opts = dataGrid.datagrid('getColumnFields');
		var fieldArray = new Array();
		var titleArray = new Array();
		for (var i = 0; i < opts.length ; i++) {
			var column = dataGrid.datagrid('getColumnOption', opts[i]);
			fieldArray.push(column.field);
			titleArray.push(column.title);
		}
		window.location.href = "${ctx}/hxPositionMapping/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
   	
</script>
</head>
<body>
	<div>
	
			<div style="width: 47%;  float: left;overflow: auto;" >
					<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="overflow: hidden;">
			      <form id="searchForm1">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td >岗位编码:</td>
						<td ><input name="positionCode" placeholder="输入查询条件" class="span2" /></td>
						<td >岗位名称:</td>
						<td ><input name="positionName" placeholder="输入查询条件" class="span2" /></td>
						<td><a href="#" id="query1" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
					<table id="ttleft"></table>
				
			</div>
			<div style="width: 5%;  float: left;">
				<div style="width: 5%; height: 100px;"></div>
				<table>
					<tr>
						<td></td>
						<td>&nbsp;&nbsp;<input type="button" value=">>"
							style="width: 50px;" onclick="connect(1)"></td>
					</tr>
				</table>
				<div style="width: 6%; height: 100px;"></div>
				<table>
					<tr>
						<td></td>
						<td>&nbsp;&nbsp;<input type="button" value="<<"
							style="width: 50px;" onclick="connect(2)"></td>
					</tr>
				</table>
				<div style="width: 6%; height: 100px;"></div>
				<table>
					<tr>
						<td></td>
						<td>&nbsp;&nbsp;<a href="#" class="easyui-linkbutton"  data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a></td>
					</tr>
				</table>
			</div>

			<div style="width: 47%;  float: right;">
					<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="overflow: hidden;">
			      <form id="searchForm2">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td >岗位编码:</td>
						<td ><input name="positionCode" placeholder="输入查询条件" class="span2" /></td>
						<td >岗位名称:</td>
						<td ><input name="positionName" placeholder="输入查询条件" class="span2" /></td>
						<td><a href="#" id="query2" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
			<div id="toolbarleft" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="importExcel();">导入</a>
		</div>
		<div id="toolbarright" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
		</div>
		</div>
					<table id="ttright"></table>
			</div>

			<br /> <br />
			


	</div>

</body>
</html>
