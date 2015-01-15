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
			title : "机构额度",
			url : "${ctx}/hxLimit/getLimitPageList",
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
				field:	'limitOrgId',
				hidden:true
			},{
				field : 'limitOrgName',
				title : '机构名称',
				width : 10,
				align:'center',
				formatter:function(value, row, index){ 
					return '<a href="#" onclick="show(\'' + row.limitId + '\');">'+row.limitOrgName+'</a>';
                }  
			}, {
				field : 'limitOrgParentName',
				title : '上级机构',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'limitCash',
				hidden : true
			}, {
				field : 'limitCredit',
				hidden : true
			}, {
				field : 'limitCashChange',
				title : '现金额度',
				width : 10,
				align:'center',
				sortable : true,
				formatter : function(value, row, index) {
					return getOriLimitCashTotal(row.limitOrgId);
				}
			} , {
				field : 'limitCreditChange',
				title : '信用额度',
				width : 10,
				align:'center',
				sortable : true,
				formatter : function(value, row, index) {
					return getOriLimitCreditTotal(row.limitOrgId);
				}
			} , {
				field : 'limitAvailability',
				title : '可用额度',
				width : 10,
				align:'center',
				formatter : function(value, row, index) {
					return (row.limitCash+row.limitCashChange+row.limitCredit+row.limitCreditChange);
				}
			} ,{
				field : 'action',
				title : '操作',
				align:'center',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="update(\'' + row.limitId + '\',\'' + row.limitOrgId  + '\');">修改</a>';
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
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

	function getOriLimitCashTotal(orgId){
		var result="";
		$.ajax({url:"${ctx}/hxLimit/getOriLimitCashTotal/"+orgId,
				async:false,
	            success:function(msg){
					if($.parseJSON(msg).flag == "success"){
						result = $.parseJSON(msg).info;
					}else {
						result = "无";
					}
				}}
		);
		return result;
	}
	
	function getOriLimitCreditTotal(orgId){
		var result="";
		$.ajax({url:"${ctx}/hxLimit/getOriLimitCreditTotal/"+orgId,
				async:false,
	            success:function(msg){
					if($.parseJSON(msg).flag == "success"){
						result = $.parseJSON(msg).info;
					}else {
						result = "无";
					}
				}}
		);
		return result;
	}
	
	function refreshDataGrid() {
		$('#dataGrid').datagrid("reload");
		parent.$.modalDialog.handler.dialog('close');	
	}	
	
	function add(){
		window.location.href="${ctx}/hxLimit/addView";
	}
	
	function update(limitId, limitOrgId){
		window.location.href="${ctx}/hxLimit/updateView?limitId="+ limitId;
	}
	
	function show(limitId){
		window.location.href="${ctx}/hxLimit/showView?limitId="+ limitId;
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 155px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%">机构名称:</td>
						<td width="5%"><input name="limitOrgId" placeholder="输入查询条件" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td>
						<td width="5%"></td>
						<td width="10%">上级机构:</td>
						<td width="5%"><input name="limitOrgParentId" placeholder="输入查询条件" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td>
						<td width="5%"></td>
						<td width="10%">现金额度:</td>
						<td width="5%"><input name="limitCash" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="40%" align="left"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
