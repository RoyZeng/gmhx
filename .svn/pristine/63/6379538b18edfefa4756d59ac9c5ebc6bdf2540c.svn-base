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
			title : "品牌数据",
			url : "${ctx}/brand/getBrandPageList",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 70,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'id',
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
			columns : [ [ {
				field : 'id',
				title : '品牌编码',
				width : 25,
				align:'center'
			},
			{
				field : 'brand',
				title : '品牌名称',
				width : 25,
				align:'center'
			}, 
			{
				field : 'repDate',
				title : '创建日期',
				width : 20,
				align:'center'
			},
			{
				field : 'modDate',
				title : '修改日期',
				width : 20,
				align:'center'
			},
			{
				field : 'isUse',
				title : '是否使用',
				width : 20,
				align:'center',
				formatter : function(value, row, index) {
					if(value=='0'||value==0){
						return "否";
					}else{
						return "是";
					}
				}
			},
			{
				field : 'action',
				title : '操作',
				width : 20,
				align:'center',
				formatter : function(value, row, index) {
					if(row.isUse=='0'){
						return '<a href="#" onclick="update(\'' + row.id + '\',\'' +'1'  + '\');">启用</a>';
					}else{
						return '<a href="#" onclick="update(\'' + row.id + '\',\''+'0'  + '\');">禁用</a>';
					}
					
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
					var queryParams = {
							brand:$('#brand').val(),
							isUse:$('#isUse').combobox('getValue')
					};
					queryParams.currentPage = pageNumber;
					queryParams.pageCount = pageSize;
					$('#dataGrid').datagrid("options").queryParams = queryParams;
					$('#dataGrid').datagrid("reload");
					$(this).pagination('loaded');
				}
		});
		$("#query").on("click", function() {
			dataGrid.datagrid('load', {
				brand:$('#brand').val(),
				isUse:$('#isUse').combobox('getValue')
		});
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
	
	function show(id){
		window.location.href="${ctx}/brand/showView/"+ id;
	}
	
	function add(){
		window.location.href="${ctx}/brand/addView";  
	}
	
	function update(id,flag){
		$.post(
			"${ctx}/brand/updateBrand",
			{
				id:id,
				isUse:flag
			},
			function(data){
				if(data.flag=='success'){
					$.messager.alert('提示:','修改成功!');
					dataGrid.datagrid('load', {brand:$('#brand').val(),isUse:$('#isUse').combobox('getValue')});
				}else{
					$.messager.alert('提示:','修改失败!');
				}
			},
			"json"
		);
	}
	
	function importExcel() {
		parent.$.modalDialog({
			title : '品牌厂家数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=brandinformation&actionName=importBrand',
			buttons : [ 
			{
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
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
		window.location.href = "${ctx}/brand/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
</script>
</head>
<body>
   <div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 120px; overflow: hidden;">
	   <form id="searchForm">
	     <table class="table table-hover table-condensed" style="width:80%; padding: 7px 80px 0px 80px">
	               <tr>
						<td width="10%" align="right">品牌:</td>
						<td width="15%" align="left"><input name="brand"  id="brand" placeholder="输入查询条件"  class="span2"/></td>
						<td width="10%" align="right">是否使用:</td>
							<td width="15%"  align="left"><input id="isUse"  name="isUse" type="text"  editable="false" class="easyui-combobox" class="span2"  data-options="url:'${ctx}/hxCode/getCombobox/yxzt'"  ></td> 
						<td width="10%" align="right"  colspan="6"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
				</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
