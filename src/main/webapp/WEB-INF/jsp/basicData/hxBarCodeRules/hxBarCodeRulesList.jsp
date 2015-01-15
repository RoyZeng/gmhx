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
			url : "${ctx}/hxBarCodeRules/getHxBarCodeRulesPageList",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 140,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'rules_id',
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
				field : 'rules_id',
				title : 'rules_id',
				hidden: true
			}, {
				field : 'gome_code',
				title : '国美代码',
				width : 10,
				align:'center',
			}, {
				field : 'category',
				title : '品类',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'bar_code_number',
				title : '条码位数',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'inside_machine',
				title : '内机位',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'inside_machine_content',
				title : '内机位内容',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'inside_machine_one',
				title : '内机位1',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'inside_machine_content_one',
				title : '内机位内容1',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'inside_machine_two',
				title : '内机位2',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'inside_machine_content_two',
				title : '内机位内容2',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'outside_machine',
				title : '外机位',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'outside_machine_content',
				title : '外机位内容',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'outside_machine_one',
				title : '外机位1',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'outside_machine_content_one',
				title : '外机位内容1',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'outside_machine_two',
				title : '外机位2',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'outside_machine_content_two',
				title : '外机位内容2',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'note',
				title : '备注',
				width : 10,
				align:'center',
				sortable : true
			} ,{
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
					return '<a href="#" onclick="update(\'' + row.rules_id + '\');">修改</a> <a href="#" onclick="show  (\'' + row.rules_id + '\');">查看</a>  ';
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
		
		window.location.href="${ctx}/hxBarCodeRules/addView/";
	}
	
	
	function update(rules_id){
		var rulesId=rules_id;
		var pageMarkup = "list";//页面标记位
		window.location.href="${ctx}/hxBarCodeRules/updateView/"+ rulesId;
	}
	
	function show(rules_id){
		var rulesId=rules_id;
		var pageMarkup = "list"; //页面标记位
		window.location.href="${ctx}/hxBarCodeRules/showView/"+ rulesId ;
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
		window.location.href = "${ctx}/hxBarCodeRules/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
	
		function importExcel(){
			parent.$.modalDialog({
				title : '数据导入',
				width : 400,
				height :250,
				closable : false,
				href : '${ctx}/common/import/importData?templateName=bar_code_rules&actionName=importBarCodeRules',
				buttons : [ 
				{
					text : '关闭',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
					}
				}]
			});
			
		}
	
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 140px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
				<tr>
					<td width="5%">国美代码</td>
					<td width="5%"><input name="gomeCode" type="text" placeholder="请选择国美代码" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/gmdm'" ></td>
					<td width="5%">品类</td>
					<td width="5%"><input class="easyui-combobox" name="category" placeholder="请选择品类" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/wxpl'"/></td>            
               </tr>
			   <tr>
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
				data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="importExcel();">导入</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
