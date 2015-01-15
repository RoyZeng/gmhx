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
			title : "配件库位",
			url : "${ctx}/hxFittingLocation/getHxFittingLocationPageList",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 140,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'fitting_id',
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
				field : 'fitting_id',
				title : 'fitting_id',
				hidden: true
			}, {
				field : 'organization_name',
				title : '机构名称',
				width : 10,
				align:'center',
			}, {
				field : 'material_type',
				title : '物料类型',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'fittings_code',
				title : '配件编码',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'fittings_name',
				title : '配件名称',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'location',
				title : '库位',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'note',
				title : '备注',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'create_date',
				title : '创建时间',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'modifie_date',
				title : '修改时间',
				width : 10,
				align:'center',
				sortable : true
			}  ,{
				field : 'action',
				title : '操作',
				width : 10,
				align:'center',
				formatter : function(value, row, index) {                                                                                                                                                     
					return '<a href="#" onclick="update(\'' + row.fitting_id + '\');">修改</a> <a href="#" onclick="show  (\'' + row.fitting_id + '\');">查看</a>  ';
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

	function refreshDataGrid() {
		$('#dataGrid').datagrid("reload");
		parent.$.modalDialog.handler.dialog('close');	
	}	
	
	function add(){
		
		window.location.href="${ctx}/hxFittingLocation/addView/";
	}
	
	
	function update(fitting_id, pos_handlers){
		var fittingId=fitting_id;
		var pageMarkup = "list";//页面标记位
		window.location.href="${ctx}/hxFittingLocation/updateView/"+ fittingId;
	}
	
	function show(fitting_id, pos_handlers){
		var fittingId=fitting_id;
		var pageMarkup = "list"; //页面标记位
		window.location.href="${ctx}/hxFittingLocation/showView/"+ fittingId ;
	}
	
	function importExcel(){
		parent.$.modalDialog({
			title : '配件库位数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=fittinglocation&actionName=importHxFittingLocation',
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
		window.location.href = "${ctx}/hxFittingLocation/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
	
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 140px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
				<tr>
					<td width="5%">机构名称</td>
					<td width="5%"><input name="organizationName" type="text" placeholder="请输入机构名称" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/jg'" ></td>
					<td width="5%"></td>
					<td width="5%">物料类型</td>
					<td width="5%"><input class="easyui-combobox" name="materialType" placeholder="请选择物料类型" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/wllx'"/></td>
					<td width="5%"></td>
					<td width="5%">配件编码</td>
					<td width="5%"><input class="easyui-combobox" name="fittingsCode" placeholder="请选择物料类型" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/pjbm'"/></td>
                    <td width="5%"></td>
                    <td width="55%"></td>              
               </tr>
			   <tr>
					<td width="5%">库位</td>
					<td width="5%"><input name="location" type="text" placeholder="请输入库位信息" class="span2" ></td>
					<td width="5%"></td>
					<td width="5%">修改日期</td>
						<td width="25%">
							<input id="modifieDate_sta" placeholder="选择起始日期" class="Wdate" name="modifieDate_sta" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'modifieDate_end\')}'})"/>至
							<input id="modifieDate_end" placeholder="选择结束日期" class="Wdate" name="modifieDate_end" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'modifieDate_sta\')}'})"/> 
						</td>
					<td width="5%"></td>
					<td width="50%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
	            
				</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="importExcel();">导入</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
