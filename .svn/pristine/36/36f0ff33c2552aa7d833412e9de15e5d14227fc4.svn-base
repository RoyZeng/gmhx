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
			title : "邮包收货",
			url : "${ctx}/hxParcelReceipt/getHxParcelReceiptPageList",
			nowrap : false,
			striped : true,
			height : document.documentElement.clientHeight * 0.86,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'parcel_code',
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			checkOnSelect : false,
			selectOnCheck : false,
			queryParams : {
				currentPage : 1,
				pageCount : 10
			},
			columns : [ [{
				field : 'action',
				title : '操作',
				width : 5,
				align:'center',
				formatter : function(value, row, index) {                                                                                                                                                     
					return ' <a href="#" onclick="show  (\'' + row.parcel_code + '\');">查看</a>  ';
				}
			}, {
				field : 'parcel_code',
				title : '包裹单号',
				width : 10,
				align:'center',
			}, {
				field : 'bills_code',
				title : '货运单号',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'send_unit',
				title : '发货单位',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'receive_unit',
				title : '收货单位',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'carrier_unit',
				title : '承运单位',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'send_date',
				title : '发货日期',
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

	function refreshDataGrid() {
		$('#dataGrid').datagrid("reload");
		parent.$.modalDialog.handler.dialog('close');	
	}	
	
	function add(){
		window.location.href="${ctx}/hxBarCodeRules/addView/";
	}
	
	
	function update(rules_id){
		var rulesId=rules_id;
		var pageMarkup = "list";//页面标记位
		window.location.href="${ctx}/hxBarCodeRules/updateView/"+ rulesId;
	}
	
	function show(parcelCode){
		var pageMarkup = "list"; //页面标记位
		window.location.href="${ctx}/hxParcelReceipt/showView/"+ parcelCode ;
	}
	
	function exportExcel(){
		var opts = dataGrid.datagrid('getColumnFields');
		var fieldArray = new Array();
		var titleArray = new Array();
		for(var i = 0; i < opts.length - 1 ;i++){//最后的操作不要
			var column = dataGrid.datagrid('getColumnOption', opts[i]);
			fieldArray.push(column.field);
			titleArray.push(column.title);
		}
		window.location.href = "${ctx}/hxParcelReceipt/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
	
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 130px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
				<tr>
					<td width="10%">包裹单号</td>
					<td width="5%"><input name="parcelCode" type="text" placeholder="请输入包裹单号" class="span2" ></td>
					<td width="5%"></td>
					<td width="10%">货运单号</td>
					<td width="5%"><input name="billsCode" type="text" placeholder="请输入货运单号" class="span2" ></td>
					<td width="5%"></td>
             	    <td width="10%">发货单位</td>
					<td width="5%"><input class="easyui-combobox" name="sendUnit" placeholder="请选择发货单位" class="span2" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td>            
					<td width="5%"></td>
					<td width="40"></td>
               </tr>
			   <tr>
			        <td width="10%">收货单位</td>
					<td width="5%"><input class="easyui-combobox" name="receiveUnit" placeholder="请选择收货单位" class="span2" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td>            
					<td width="5%"></td>
                    <td width="10%">承运单位</td>
					<td width="5%"><input name="carrierUnit" type="text" placeholder="请输入承运单位" class="span2" ></td>
					<td width="5%"></td>
					<td width="10%">发货日期</td>
			        <td width="5%">
			    	   <input id="sendDate_sta" placeholder="选择起始日期" class="Wdate" name="sendDate_sta" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'sendDate_end\')}'})"/>至
			    	   <input id="sendDate_end" placeholder="选择结束日期" class="Wdate" name="sendDate_end" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sendDate_sta\')}'})"/> 
		    	    </td>
		    	    <td width="5%"></td>
		    	    <td width="5%"></td>
					<td width="20%" align="left"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
				</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
	<!--  		<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>   预留  -->
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
