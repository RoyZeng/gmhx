<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<html>
<head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			title : "故障维修代码",
	       url : "${ctx}/maintenance/getMaintenancePageList/",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight*0.7,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'classifyCode',
			rownumbers : true,
			fitColumns : true,
			singleSelect : true,
			pagination : true,
			checkOnSelect : false,
			selectOnCheck : false,
			queryParams : {
				currentPage : 1,
				pageCount : 10
			},
			columns : [ [ {
				field : 'category1',
				title : '品类',
				width : 10,
				align:'center',
			} ,{
				field : 'fault_name',
				title : '故障名称',
				width : 25,
				align:'center',
				sortable : true
			} , {
				field : 'fault_code',
				title : '故障代码',
				width : 15,
				align:'center',
				sortable : true
			} , {
				field : 'p_number1',
				title : '空调P数',
				width : 15,
				align:'center',
				sortable : true
			} , {
				field : 'maintenance_costs',
				title : '维修费用',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'chose1',
				title : '是否选择',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'wet_enable1',
				title : '是否启用',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'wet_union1',
				title : '是否关联配件',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'note',
				title : '备注',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'action',
				title : '操作',
				align:'center',
				width:10,
				formatter : function(value, row, index) {
					return '<a href="#" onclick="view(\'' + row.fault_code + '\');">查看</a> <a href="#" onclick="update(\'' + row.fault_code + '\');">修改</a>';
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
		$("#query").on("click", function() {
			dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
		});
		
		$('#class1').combobox({
			url: "${ctx}/eccGoods/getGoodClass?classification=1",
			valueField:'value', 
			textField:'text',
			onChange:function(record){  
				$('#class2').combobox({
					url: "${ctx}/eccGoods/getGoodClass?classification=2&upperCode="+$('#class1').combobox('getValue'),
					valueField:'value', 
					textField:'text',
					onChange:function(record){
			        	$('#class3').combobox({
							valueField:'value', 
							textField:'text',
							url: "${ctx}/eccGoods/getGoodClass?classification=3&upperCode="+$('#class2').combobox('getValue'),
							onChange:function(record){
					        	$('#category').combobox({
					        		url: "${ctx}/eccGoods/getGoodClass?classification=4&upperCode="+$('#class3').combobox('getValue'),
									valueField:'value', 
									textField:'text',
								});
					        	$("#category").combobox("clear");
				         	} 
						});
			        	$("#class3").combobox("clear");
			        	$("#category").combobox("clear");
		         	} 
				});
	        	$("#class2").combobox("clear");
	        	$("#class3").combobox("clear");
	        	$("#category").combobox("clear");
	     	} 
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
	
	function back(){
		window.location.href="${ctx}/maintenance/maintenanceView/";
	}
	
	function importExcel(fault_code){
		//window.location.href = "${ctx}/maintenance/importView";
		parent.$.modalDialog({
			title : '故障代码数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=faultmaintenancecode&actionName=importHxMaintenance',
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
		window.location.href = "${ctx}/maintenance/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
	
	function add(){
		parent.$.modalDialog({
			title : '新增故障维修代码',
			width : 600,
			height : 400,
			closable : false,
			href : '${ctx}/maintenance/addView/',
			buttons : [ {
				text : '保存',
				handler : function() {
					$.post("${ctx}/maintenance/addHxMaintenance",
					parent.$.modalDialog.handler.find('#form').serialize(),
					function(msg){
						parent.$.messager.progress('close');
						if($.parseJSON(msg).flag == "success"){
							parent.$.messager.alert('','新增成功!');
							dataGrid.datagrid("reload");
							parent.$.modalDialog.handler.dialog('close');
						}else{
							parent.$.messager.alert('','新增失败!');
						}
					});
				}
			}, {
				text : '继续添加',
				handler : function() {
					$.post("${ctx}/maintenance/addHxMaintenance",
					parent.$.modalDialog.handler.find('#form').serialize(),
					function(msg){
						parent.$.messager.progress('close');
						if($.parseJSON(msg).flag == "success"){
							dataGrid.datagrid("reload");
							parent.$.modalDialog.handler.dialog('close');
							add();
						}else{
							parent.$.messager.alert('','新增失败!');
						}
					});
				}
			}, {
				text : '取消',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	}
	
	function update(fault_code){
		parent.$.modalDialog({
			title : '修改故障维修代码',
			width : 600,
			height : 400,
			closable : false,
			href : '${ctx}/maintenance/updateView/' + fault_code,
			buttons : [ {
				text : '确定',
				handler : function() {
					$.post("${ctx}/maintenance/modifyHxMaintenance",parent.$.modalDialog.handler.find('#form').serialize(),
					function(msg){
						parent.$.messager.progress('close');
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('','修改成功!');
							dataGrid.datagrid("reload");
							parent.$.modalDialog.handler.dialog('close');
						}else{
							$.messager.alert('','修改失败!');
						}
					});
				}
			}, {
				text : '取消',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	}
	
	function view(fault_code){
		parent.$.modalDialog({
			title : '查看故障维修代码',
			width : 600,
			height : 400,
			closable : false,
			href : '${ctx}/maintenance/viewHxMaintenance/' + fault_code,
			buttons : [ {
				text : '修改',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
					update(fault_code);
				}
			}, {
				text : '取消',
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
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false"
			style="height: 160px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed"
					style="width: 100%; padding: 7px 80px 0px 80px">
					<tr>
		                <td width="10%">一级分类</td>
			            <td width="25%"><input id="class1" type="text"  class="easyui-combobox"/></td>
			            <td width="10%">二级分类</td>
			            <td width="25%"><input id="class2" type="text"  class="easyui-combobox"></td>
			            <td width="10%">三级分类</td>
			            <td width="25%"><input id="class3" type="text"  class="easyui-combobox"></td>
		            </tr>
					<tr>
						<td width="10%">品类</td>
						<td width="25%"><input class="easyui-combobox" id="category" name="category"/></td>
						<td width="10%">故障名称</td>
						<td width="25%"><input name="faultName"  class="span2" /></td>
						<td width="10%">故障代码</td>
						<td width="25%"><input name="faultCode"  class="span2" /></td>
					</tr>
					<tr>
						<td width="10%">空调P数</td>
						<td width="25%"><input name="PNumber"  class="easyui-combobox"
								data-options="url:'${ctx}/hxCode/getCombobox/ktps'" /></td>
						<td width="10%">是否选择</td>
						<td width="25%"><input class="easyui-combobox" name="chose"
							data-options="url:'${ctx}/hxCode/getCombobox/sf'" /></td>
						<td width="10%">是否启用</td>
						<td width="25%"><input class="easyui-combobox"
							name="wetEnable"
							data-options="url:'${ctx}/hxCode/getCombobox/sf'" /></td>
					</tr>
					<tr>
						<td width="10%">是否关联配件</td>
						<td width="25%"><input class="easyui-combobox"
							name="wetUnion"
							data-options="url:'${ctx}/hxCode/getCombobox/sf'" /></td>
						<td></td>
						
						<td width="40%" align="right" colspan="4"><a href="#" id="query"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
		    <a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true"
				onclick="importExcel();">故障代码导入</a>
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
