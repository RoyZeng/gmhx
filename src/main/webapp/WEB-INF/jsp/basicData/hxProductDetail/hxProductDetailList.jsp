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
				title : "产品资料",
				url : "${ctx}/hxProductDetail/getHxProductDetailPageList",
				striped : true,
				collapsible : true,
				autoRowHeight : false,
				remoteSort : false,
				rownumbers : true,
				fitColumns : true,
				pagination : true,
				singleSelect : true,
				height : document.body.clientHeight*0.75,
				queryParams : {
					pageNo : 1,
					pageCount : 20
				},
				columns : [ [ {
					field : 'id',
					hidden : true
				}, {
					field : 'product_code',
					title : '产品编码',
					align:'center',
					width : 50
				},{
					field : 'matnr',
					title : '商品编码',
					align:'center',
					width : 50
				}, {
					field : 'product_name',
					title : '产品名称',
					align:'center',
					width : 50
				}, {
					field : 'classify_code',
					title : '产品分类',
					align:'center',
					width : 50
				}, {
					field : 'model',
					title : '机型',
					align:'center',
					width : 50
				}, {
					field : 'model_classify',
					title : '机型类别',
					align:'center',
					width : 50
				}, {
					field : 'product_model',
					title : '产品类型',
					align:'center',
					width : 50
				}, {
					field : 'gome_code',
					title : '国美代码',
					align:'center',
					width : 50
				}, {
					field : 'brand',
					title : '品牌',
					align:'center',
					width : 50
				}, {
					field : 'is_new',
					title : '是否新机',
					align:'center',
					formatter : function(value, row, index) {
				  		if(value){
				  			return '<image src="${ctx}/images/icons/ok.png">';
				  		}else{
				  			return '<image src="${ctx}/images/icons/cancel.png">';
				  		}
				  	},
					width : 50
				}, {
					field : 'is_preferential',
					title : '是否惠民',
					align:'center',
					formatter : function(value, row, index) {
						if(value){
				  			return '<image src="${ctx}/images/icons/ok.png">';
				  		}else{
				  			return '<image src="${ctx}/images/icons/cancel.png">';
				  		}
				  	},
					width : 50
				}, {
					field : 'spec',
					title : '产品规格',
					align:'center',
					width : 50
				}, {
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
						return '<a href="#" onclick="view(\'' + row.id + '\',\'' + row.model + '\');">查看</a> <a href="#" onclick="update(\'' + row.id + '\',\'' + row.model + '\');">修改</a>';
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
						        	$('#classifyCode').combobox({
						        		url: "${ctx}/eccGoods/getGoodClass?classification=4&upperCode="+$('#class3').combobox('getValue'),
										valueField:'value', 
										textField:'text',
									});
						        	$("#classifyCode").combobox("clear");
					         	} 
							});
				        	$("#class3").combobox("clear");
				        	$("#classifyCode").combobox("clear");
			         	} 
					});
		        	$("#class2").combobox("clear");
		        	$("#class3").combobox("clear");
		        	$("#classifyCode").combobox("clear");
		     	} 
			});
			
		});
		$.serializeObject = function(form) {
			var o = {
				pageNo : 1,
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
		
		function update(id, model){
			parent.$.modalDialog({
				title : '修改机型(' + model + ')',
				width : 600,
				height : 400,
				closable : false,
				href : '${ctx}/hxProductDetail/updateView/' + id,
				buttons : [ {
					text : '确定',
					handler : function() {
						$.post("${ctx}/hxProductDetail/updateHxProductDetail",parent.$.modalDialog.handler.find('#form').serialize(),
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
		
		function view(id, model){
			parent.$.modalDialog({
				title : '查看机型(' + model + ')',
				width : 600,
				height : 400,
				closable : false,
				href : '${ctx}/hxProductDetail/viewHxProductDetail/' + id,
				buttons : [ {
					text : '修改',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
						update(id, model);
					}
				}, {
					text : '取消',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
					}
				}]
			});
		}
	
		function refreshDataGrid() {
			$('#dataGrid').datagrid("reload");
			parent.$.modalDialog.handler.dialog('close');	
		}
		
		function add(){
			parent.$.modalDialog({
				title : '新增产品资料',
				width : 600,
				height : 400,
				closable : false,
				href : '${ctx}/hxProductDetail/addView/',
				buttons : [ {
					text : '保存',
					handler : function() {
						$.post("${ctx}/hxProductDetail/addHxProductDetail",
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
						$.post("${ctx}/hxProductDetail/addHxProductDetail",
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
		
		function importExcel(){
			parent.$.modalDialog({
				title : '数据导入',
				width : 400,
				height :250,
				closable : false,
				href : '${ctx}/common/import/importData?templateName=product_detail&actionName=importHxProductDetail',
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
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height:230px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
				    <tr>
				       <td width="5%">一级分类</td>
			           <td width="5%"><input id="class1" type="text"  class="easyui-combobox"/></td>
			           <td width="5%"></td>
			           <td width="5%">二级分类</td>
			           <td width="5%"><input id="class2" type="text"  class="easyui-combobox"></td>
			           <td width="5%"></td>
			           <td width="5%">三级分类</td>
			           <td width="5%"><input id="class3" type="text"  class="easyui-combobox"></td>
			           <td width="5%"></td>
					   <td width="55%"></td>
				    </tr>
					<tr>
					    <td width="5%">产品分类:</td>
						<td width="5%"><input class="easyui-combobox" name="classifyCode" id="classifyCode"/></td>
						<td width="5%"></td>
						<td width="5%">产品编码:</td>
						<td width="5%"><input name="productCode" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="5%">商品编码:</td>
						<td width="5%"><input name="matnr" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="55%"></td>
					</tr>
					<tr>
					    <td width="5%">产品名称:</td>
						<td width="5%"><input name="productName" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="5%">机型:</td>
						<td width="5%"><input name="model" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="5%">机型类别:</td>
						<td width="5%"><input class="easyui-combobox" name="modelClassify" data-options="url:'${ctx}/hxCode/getCombobox/jxlb'"/></td>
						<td width="5%"></td>
						<td width="55%"></td>
					</tr>
					<tr>
					    <td width="5%">产品类型:</td>
						<td width="5%"><input class="easyui-combobox" name="productModel" data-options="url:'${ctx}/hxCode/getCombobox/cplx'"/></td>
						<td width="5%"></td>
						<td width="5%">国美代码:</td>
						<td width="5%"><input class="easyui-combobox" name="gomeCode" data-options="url:'${ctx}/hxCode/getCombobox/gmdm'"/></td>
						<td width="5%"></td>
						<td width="5%">品牌:</td>
						<td width="5%"><input class="easyui-combobox" name="brand" data-options="url:'${ctx}/hxCode/getCombobox/pp'"/></td>
						<td width="5%"></td>
						<td width="55%"></td>
					</tr>
					<tr>
					    <td width="5%">是否新机:</td>
						<td width="5%"><input class="easyui-combobox" name="isNew" data-options="url:'${ctx}/hxCode/getCombobox/sf'"/></td>
						<td width="5%"></td>
						<td width="5%">是否惠民:</td>
						<td width="5%"><input class="easyui-combobox" name="isPreferential" data-options="url:'${ctx}/hxCode/getCombobox/sf'"/></td>
						<td width="5%"></td>
						<td width="5%">产品规格:</td>
						<td width="5%"><input name="spec" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="55%"></td>
					</tr>
				    <tr>
						<td width="5%">更新日期:</td>
						<td width="25%">
							<input id="ksrq" placeholder="选择起始日期" class="Wdate" name="ksrq" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>至
							<input id="jsrq" placeholder="选择结束日期" class="Wdate" name="jsrq" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')}'})"/> 
						</td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="5%"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
						<td width="5%"></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="importExcel();">产品导入</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
