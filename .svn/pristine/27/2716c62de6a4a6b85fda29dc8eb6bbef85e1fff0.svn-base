<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			title : "机构额度历史",
			url : "${ctx}/hxLimit/getLimitHistoryPageList",
			nowrap : false,
			striped : true,
			height : document.documentElement.clientHeight * 0.98,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'limitId',
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
				field : 'limitOrgName',
				title : '机构名称',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					return '<a href="#" onclick="show(\'' + row.limitOrgId + '\');">'+row.limitOrgName+'</a>';
                }  
			}, {
				field : 'limitOrgParentName',
				title : '上级机构',
				width : 10,
				align:'center',
				sortable : true
			}, {
				field : 'limitOriginNumber',
				title : '原始单号',
				width : 10,
				align:'center',
				sortable : true
			}, {
				field : 'limitOperateType',
				title : '方式',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					if(row.limitOperateType=='0'){
						return "手动";
					}else if(row.limitOperateType=='1'){
						return "自动";
					}
                }  
			}, {
				field : 'limitAddTag',
				title : '增减标志',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					if(row.limitAddTag=='0'){
						return "减";
					}else if(row.limitAddTag=='1'){
						return "增";
					}
                }  
			}, {
				field : 'limitOperateReason',
				title : '更改原因',
				width : 10,
				align:'center'  
			}, {
				field : 'limitCashChange',
				title : '现金',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'limitCreditChange',
				title : '信用',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'limitMatter',
				title : '物料',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'limitOperateDate',
				title : '操作日期',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'limitDesc',
				title : '备注',
				width : 10,
				align:'center',
				sortable : true
			}, {
				field : 'limitOperateId',
				title : '操作人',
				width : 10,
				align:'center',
				sortable : true
			}] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');
			}
		});
		$('.datagrid-pager').pagination({
			onSelectPage : function(pageNumber, pageSize) {
				$(this).pagination('loading');
				$('#dataGrid').datagrid("options").queryParams = {
					currentPage : pageNumber,
					pageCount : pageSize
				};
				$('#dataGrid').datagrid("reload");
				$(this).pagination('loaded');
			}
		});
		$("#query").on("click", function() {
			dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
		});
	});
	
	$.serializeObject = function(form) {
		var o = {
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

	function refreshDataGrid() {
		$('#dataGrid').datagrid("reload");
		parent.$.modalDialog.handler.dialog('close');	
	}	
	
	function show(orgId){
		var pageMarkup = "limit";//页面标记位
		window.location.href="${ctx}/hxOrganization/showView/"+orgId+"/"+pageMarkup;
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 195px;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<c:if test="${sessionScope.user.sysPositionType == 1}">
							<td width="15%">机构名称:</td>
							<td width="5%"><input  class="easyui-combobox" name="limitOrgId" placeholder="输入查询条件" class="span2" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td> 
							<td width="5%"></td>
						</c:if>
				     	<c:if test="${sessionScope.user.sysPositionType == 2}">
							<td width="15%">机构名称:</td>
							<td width="5%"><input  class="easyui-combobox" name="limitOrgId" placeholder="输入查询条件" class="span2" data-options="url:'${ctx}/hxLimit/getFbOrgNames',hasDownArrow:false"/></td> 
							<td width="5%"></td>
						</c:if>
						<c:if test="${sessionScope.user.sysPositionType == 3}">
							<td width="15%">机构名称:</td>
							<td width="5%"><input  class="easyui-combobox" name="limitOrgId" placeholder="输入查询条件" class="span2" data-options="url:'${ctx}/hxLimit/getFbOrgNames',hasDownArrow:false"/></td> 
							<td width="5%"></td>
						</c:if>
						<td width="10%">上级机构:</td>
						<td width="5%"><input name="limitOrgParentId" placeholder="输入查询条件" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false" /></td>
						<td width="5%"></td>
						<td width="10%">原始单号:</td>
						<td width="5%"><input name="limitOriginNumber" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
					</tr>
					<tr>
						<td width="10%">方式:</td>
						<td width="5%"><input name="limitOperateType" placeholder="输入查询条件" editable="false" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getCombobox/fs'"/></td>
						<td width="5%"></td>
						<td width="10%">增减标志:</td>
						<td width="5%"><input name="limitAddTag" placeholder="输入查询条件" editable="false" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getCombobox/zjbj'"/></td>
						<td width="5%"></td>
						<td width="10%">更改原因:</td>
						<td width="5%"><input name="limitOperateReason" placeholder="输入更改原因" class="spa2" /></td>
						<td width="5%"></td>
					</tr>
					<tr>
						<td width="10%">现金:</td>
						<td width="5%"><input name="limitCashChange" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">信用:</td>
						<td width="5%"><input name="limitCreditChange" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">物料:</td>
						<td width="5%"><input name="limitMatter" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
					</tr>
					<tr>
						<td width="10%">操作日期:</td>
						<td width="20%">
							<input id="ksrq" placeholder="选择起始日期" class="Wdate" name="ksrq" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>至
							<input id="jsrq" placeholder="选择结束日期" class="Wdate" name="jsrq" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')}'})"/> 
						</td>
						<td width="5%" align=right><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
					
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
