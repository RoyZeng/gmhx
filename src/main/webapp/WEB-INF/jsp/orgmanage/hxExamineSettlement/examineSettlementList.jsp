<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">

	var dataGrid;
	$(function(){
		onStart(document.body.clientHeight - 118);
		$('#dataGrid').datagrid('cancelCellTip');
		$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
	});
	
	function clos(){
		onStart(document.body.clientHeight - 28);
		$('#dataGrid').datagrid('cancelCellTip');
		$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
	}
	
	function ope(){
		onStart(document.body.clientHeight - 118);
		$('#dataGrid').datagrid('cancelCellTip');
		$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
	}
	
	function onStart(heigh){
	 	dataGrid = $('#dataGrid').datagrid({
			title : "安装服务单",
			url : "${ctx}/hxExamineSettlement/getExamineSettlementPageList",
			striped : true,
            height : heigh,
            collapsible : true,
            autoRowHeight : false,
            remoteSort : false,
            idField : 'settlement_id',
            rownumbers : true,
            fitColumns : true,
            nowrap : true,
            pagination : true,
            checkOnSelect : false,
            selectOnCheck : false,
            queryParams : {
                currentPage : 1,
                pageCount : 10
            },
			columns : [ [ 
			   {field : 'service_unit',title : '服务单位', width : 10 ,align:'center'
			}, {field : 'parent_organization',title : '上级机构', width : 10,align:'center'
			}, {field : 'brand',title : '品牌', width : 10,align:'center'
			}, {field : 'settlement_id',title : '结算单编号', width : 10,align:'center'
			}, {field : 'settlement_type',title : '结算类型', width : 10,align:'center'
			}, {field : 'settlement_fee',title : '结算费用', width : 10,align:'center'
			}, {field : 'service_fee',title : '服务费用', width : 10,align:'center'
			}, {field : 'reward_fee',title : '奖励费用', width : 10,align:'center'
			}, {field : 'punish_fee',title : '处罚费用', width : 10,align:'center'
			}, {field : 'other_fee',title : '其它费用', width : 10,align:'center'
			}, {field : 'settlement_status',title : '结算单状态', width : 10,align:'center'
			}, {field : 'settlement_time',title : '结算时间 ', width : 10,align:'center'
			}, {field : 'action',title : '操作', width : 13,align:'center',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="examineTicket(\'' + row.settlement_id + '\');">审核</a> | <a href="#" onclick="updateTicket(\'' + row.settlement_id + '\');">修改</a>';
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');
			}
		});
		$('.datagrid-pager').pagination({
			pageSize: 20,         
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
	};
	
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
	
	function examineTicket(settlementId){
		window.location.href="${ctx}/hxExamineSettlement/examineSettlementShow/"+settlementId;
	}
	
	function updateTicket(settlementId){
		window.location.href="${ctx}/hxExamineSettlement/examineSettlementUpdate/"+settlementId;
	}
	
	function test(){
		 $.ajax({  
             type : "post",  
             url : "${ctx}/hxExamineSettlement/goTest",  
             async : false,
             cache: false,
             success : function(msg){  
					parent.$.messager.alert('',msg);
				  }
       });
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:true,onBeforeCollapse:function(){clos();},onBeforeOpen:function(){ope();}" style="height: 118px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" width="100%;"
					style="padding: 10px 10px 10px 10px">
					<tr>
						<td width="5%">服务单位</td>
						<td width="15%"><input name="serviceUnit" placeholder="请输入查询条件" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getStoreCombobox',panelHeight:'100',editable:true "></td>
						<td width="5%">服务单位名称</td>
						<td width="15%"><input name="serviceUnitName" placeholder="请输入查询条件" class="span2"></td>
						<td width="5%">上级机构 </td>
						<td width="15%"><input name="parentOrganization" placeholder="请输入查询条件" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/jg',panelHeight:'100',editable:true "></td>
						<td width="5%">品牌</td>
						<td width="15%"><input name="brand" placeholder="请输入查询条件" class="easyui-combobox" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/pp',panelHeight:'100',editable:false "></td>
					</tr>
					<tr>
						<td>结算单编号</td>
						<td><input name="settlementId" placeholder="请输入查询条件" class="span2"></td>
						<td>结算类型</td>
						<td><input name="settlementType" placeholder="请输入查询条件" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/st',panelHeight:'auto',editable:false "></td>
						<td>结算单状态</td>
						<td><input name="settlementStatus" placeholder="请输入查询条件" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/jszt',panelHeight:'auto',editable:false "></td>
						<td>结算时间</td>
						<td>
							<input id="iksrq" placeholder="选择起始日期" class="Wdate" name="settlementTime_st" type="text" readonly="readonly"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'ijsrq\')}'})"/><br/>
							<input id="ijsrq" placeholder="选择结束日期" class="Wdate" name="settlementTime_end" type="text" readonly="readonly"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'iksrq\')}'})"/> 
						</td>
						<td width="10%" colspan="2" align="center">
							<a href="#" id="query" class="easyui-linkbutton">查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div id="toolbar" style="display: "";">
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="test();">机审入口</a>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
