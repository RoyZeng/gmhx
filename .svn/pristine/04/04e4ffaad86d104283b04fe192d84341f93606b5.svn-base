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
			title : "配件管理",
			url : "${ctx}/hxMaterial/getHxMaterialPageList",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			singleSelect : true,
			height : document.documentElement.clientHeight * 0.86,
			queryParams : {
				pageNo : 1,
				pageCount : 20
			},
			columns : [ [ 
			{
				field : 'type_hidden',
				hidden : true
			},{
				field : 'list_number',
				title : '单据编号',
				align:'center',
				width : 50
			}, {
				field : 'send_company',
				title : '发货单位',
				align:'center',
				width : 50
			}, {
				field : 'receive_company',
				title : '收货单位',
				align:'center',
				width : 50
			},
			{
				field : 'type',
				title : '单据类型',
				align:'center',
				width : 50
			}, 
			{
				field : 'status',
				title : '状态',
				align:'center',
				width : 50
			}, {
				field : 'status_hidden',
				hidden : true
			}, 
			{
				field : 'update_time',
				title : '更新日期',
				align:'center',
				width : 50
			}, {
				field : 'action',
				title : '操作',
				align:'center',
				width : 50,
				formatter : function(value, row, index) {
					if(row.status_hidden=="S1"){
						return '<a href="#" onclick="view(\'' + row.list_number + '\', \'' + row.type_hidden + '\');">查看</a> <a href="#" onclick="update(\'' + row.list_number + '\', \'' + row.type_hidden + '\');">修改</a> <a href="#" onclick="del(\'' + row.list_number + '\');">删除</a>';
					}
					return '<a href="#" onclick="view(\'' + row.list_number + '\', \'' + row.type_hidden + '\');">查看</a>';
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
				parent.$.messager.progress('close');
			}
		});
		
		
		$('.datagrid-pager').pagination({
			pageSize: 20,//每页显示的记录条数，默认为10 
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
		
		$.post("${ctx}/hxCode/getFittingMenu",{},function(msg){
			var obj = $.parseJSON(msg);
			$.each(obj, function(){
				$("#mm").append("<div fittingid='" + $(this)[0].value + "' onclick='add(this);'>" + $(this)[0].text + "</div>");
			});
			$("#addButton").menubutton({    
			    iconCls: 'icon-add',    
			    menu: '#mm'   
			});  
		});
		
		$("#query").on("click", function() {
			dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
		});
		
		$('#type').combobox({    
		    url:'${ctx}/hxCode/getFittingMenu',
		    editable:false
		});
		
		$('#sendCompany').combobox({    
			onChange: function (n,o) {
				if(n=='') return;
				else{
					//
				}
			}
		});
	});
	
	$.serializeObject = function(form) {
		var o = {
				currentPage : 1,
				pageCount : 20
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
	
	function view(listNumber, type){
		window.location.href = "${ctx}/hxMaterial/viewHxMaterial?listNumber=" + listNumber + "&type=" + type;
	}
	
	function update(listNumber, type){
		window.location.href = "${ctx}/hxMaterial/updateHxMaterial?listNumber=" + listNumber + "&type=" + type;
	}
	
	function del(listNumber){
		parent.$.messager.confirm('提示', '确认要删除么？', function(r){
			if (r){
				$.post("${ctx}/hxMaterial/delHxMaterial",{
					listNumber : listNumber
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
	
	function add(obj){
		window.location.href = "${ctx}/hxMaterial/hxMaterialAdd?type=" + $(obj).attr("fittingid");
	}
</script>
</head>
<body>
	<input type="hidden" name="companyId" value="${sessionScope.user.companyId}">
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 140px;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%">单据类型:</td>
						<td width="5%"><input id="type" name="type"/></td>
						<td width="5%"></td>
						<td width="10%">单据编号:</td>
						<td width="5%"><input name="listNumber" placeholder="输入单据编号"/></td>
						<td width="5%"></td>
						<td width="10%">发货单位:</td>
						<td width="5%"><input class="easyui-combobox" name="sendCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td>
						<td width="5%"></td>
						<td width="40%"></td>
					</tr>
					<tr>
						<td width="10%">收货单位:</td>
						<td width="5%"><input class="easyui-combobox" name="receiveCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td>
						<td width="5%"></td>
						<td width="10%">状态:</td>
						<td width="5%"><input class="easyui-combobox" name="status" data-options="url:'${ctx}/hxCode/getCombobox/djzt',editable:false"/></td>
						<td width="5%"></td>
						<td width="10%">更新日期:</td>
						<td width="5%">
							<input id="ksrq" placeholder="选择起始日期" class="Wdate" name="ksrq" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>至
							<input id="jsrq" placeholder="选择结束日期" class="Wdate" name="jsrq" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')}'})"/> 
						</td>
						<td width="5%"></td>
						<td width="40%" align="left"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a id="addButton" href="#">增加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
		<div id="mm"></div>
	</div>
</body>
</html>
