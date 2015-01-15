<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	
$(function() {
	parent.$.messager.progress('close');
	var dataGrid;
	var limitOrgId = $("#limitOrgId").val();
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			title : "额度更改历史",
			url : "${ctx}/hxLimit/getLimitHistoryPageList?limitOrgId="+limitOrgId,
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 185,
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
			columns : [ [  {
				field : 'limitOrgId',
				hidden : true
			},{
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
	
});

function show(orgId){
	var pageMarkup = "limitShow";//页面标记位
	window.location.href="${ctx}/hxOrganization/showView/"+orgId+"/"+pageMarkup;
};
function update(){
	var limitId = $("#limitId").val();
	window.location.href="${ctx}/hxLimit/updateView?limitId="+limitId;
}

function goBack(){
	window.location.href="${ctx}/hxLimit/limitView";
}

</script>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				详细查看机构额度资料
			</div>
			<input id="limitId" type="hidden" value="${limit.limitId}"/>
			<input id="limitOrgId" type="hidden" value="${limit.limitOrgId}"/>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">网点名称:</td>
					<td width="25%"><label>${limit.limitOrgName}</label></td>
					<td width="25%" align="center">现金额度:</td>
					<td width="25%"><label>${limit.limitCash+limit.limitCashChange}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">信用额度:</td>
					<td width="25%"><label>${limit.limitCredit+limit.limitCreditChange}</label></td>
					<td width="25%" align="center">可用额度:</td>
					<td width="25%"><label>${limit.limitCash+limit.limitCredit+limit.limitCashChange+limit.limitCreditChange}</label></td>
				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-edit'" onclick="update();">修改</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
	
	<div id="main-center" class="easyui-tabs" style="width:auto;height:550px;"> 
		<div title="额度更改历史" style="padding:20px;"> 
			<div data-options="region:'center',border:false">
				<table id="dataGrid"></table>
			</div>
		</div> 
	</div>
</div>