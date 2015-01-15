<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			title : "邮包发货",
			url : "${ctx}/ParcelDelivery/getParcelDeliveryPageList", 
			nowrap : false,
			striped : true,
			height : document.documentElement.clientHeight * 0.86,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'parcelCode',
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			checkOnSelect : false,
			singleSelect : true,
			selectOnCheck : false,
			queryParams : {
				currentPage : 1,
				pageCount : 10
			},
			columns : [ [ 
            {
				field : 'action',
				title : '操作',
				width : 20,
				align:'center',
				formatter : function(value, row, index) {
					if(row.status_hidden=="01"){
						return '<a href="#" onclick="show(\'' + row.parcel_code + '\');">查看</a> <a href="#" onclick="update(\'' + row.parcel_code + '\');">修改</a> <a href="#" onclick="del(\'' + row.parcel_code + '\');">删除</a>';
					}
					return '<a href="#" onclick="show(\'' + row.parcel_code + '\');">查看</a>';
				
				}
			}, {
				field : 'parcel_code',
				title : '邮包编号',
				width : 25,
				align:'center'
			}, 
			 {
				field : 'bills_code',
				title : '货运单号',
				width : 25,
				align:'center'
			}, {
				field : 'send_unit',
				title : '发货单位',
				width : 20,
				align:'center'
			}, {
				field : 'receive_unit',
				title : '收货单位',
				width : 20,
				align:'center'
			},{
				field : 'carrier_unit',
				title : '承运单位',
				width : 20,
				align:'center'
			},{
				field : 'state1',
				title : '状态',
				width : 20,
				align:'center'
			},
			{
				field : 'status_hidden',
				hidden : true
			},
			{
				field : 'update_date',
				title : '更新时间',
				width : 25,
				align:'center'
			}] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
				parent.$.messager.progress('close');
			}
		});
		$('.datagrid-pager').pagination({
			pageSize: 10,//每页显示的记录条数，默认为10   
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
	

	
	function show(parcelCode){
		window.location.href="${ctx}/ParcelDelivery/showView/" + parcelCode;
	}
	
	function update(parcelCode){
		window.location.href="${ctx}/ParcelDelivery/updateView/" + parcelCode;
	}
	
	function del(parcelCode){
		parent.$.messager.confirm('提示', '确认要删除么？', function(r){
			if (r){
				$.post("${ctx}/ParcelDelivery/delParcelDelivery",{
					parcelCode : parcelCode
				},function(msg){
					if(msg == "success"){
						parent.$.messager.alert('','删除成功!',null,function(){
							$('#dataGrid').datagrid("reload");
		            	});
					}else{
						parent.$.messager.alert('删除失败!');
					}
				});
			}
		});
	}
	
	
	function refreshDataGrid() {
		$('#dataGrid').datagrid("reload");
		parent.$.modalDialog.handler.dialog('close');	
	}	
	
	
	
	function add(){
		window.location.href="${ctx}/ParcelDelivery/addView";  
	}
	
	function exportExcel(){
		var opts = dataGrid.datagrid('getColumnFields');
		var fieldArray = new Array();
		var titleArray = new Array();
		for(var i = 1; i < opts.length - 1 ;i++){//最前面的操作不要
			var column = dataGrid.datagrid('getColumnOption', opts[i]);
			fieldArray.push(column.field);
			titleArray.push(column.title);
		}
		window.location.href = "${ctx}/ParcelDelivery/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
</script>
</head>
<body>
	<input type="hidden" name="companyId" value="${sessionScope.user.companyId}">
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div
			data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 140px;">
			<form id="searchForm">
				<table class="table table-hover table-condensed"
						style="width: 100%; padding: 7px 80px 0px 80px">
					<tr>
					    <td width="10%">邮包编号</td>
						<td width="5%"><input  class="span2" name="parcelCode" /></td>
						<td width="5%"></td>
						<td width="10%">货运单号</td>
						<td width="5%"><input name="billsCode" class="span2" class="span2"/></td>
						<td width="5%"></td>
						<td width="10%">收货单位</td>
						<td width="5%"><input name="receiveUnit"  class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td>
						<td width="5%"></td>
						<td width="40%"></td>
					</tr>
					<tr>	
				     	<td width="10%">承运单位</td>
						<td width="5%"><input class="span2" name="carrierUnit"/></td>
						<td width="5%"></td>
					    <td width="10%">更新日期</td>
					    <td width="5%">
							<input id="updateDate_sta" placeholder="选择起始日期" class="Wdate" name="updateDate_sta" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'updateDate_end\')}'})"/>至
							<input id="updateDate_end" placeholder="选择结束日期" class="Wdate" name="updateDate_end" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'updateDate_sta\')}'})"/> 
						</td>
					    <td width="5%">
					    <td width="5%">
						<td width="5%" align="left"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>	
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">新建</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true"
				onclick="exportExcel();">导出</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
