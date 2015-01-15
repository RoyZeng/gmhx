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
			title : "商品主数据",
			url : "${ctx}/eccGoods/getGoodClassPageList",
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
				field : 'matnr',
				title : '商品编码',
				align:'center'
			},{
				field : 'maktx',
				title : '商品名称',
				align:'center'
			}, {
				field : 'ean11',
				title : '商品条码',
				align:'center'
			}, {
				field : 'groes',
				title : '规格型号',
				align:'center'
			}, {
				field : 'matkl',
				title : '商品分类',
				align:'center'
			},{
				field : 'prdha',
				title : '品牌',
				align:'center'
			},
			{
				field : 'inhal',
				title : '延保价格上限',
				align:'center'
			},
			{
				field : 'inhbr',
				title : '延保价格下限',
				align:'center'
			},
			{
				field : 'taklv',
				title : '销项税率',
				align:'center'
			},
			{
				field : 'meins',
				title : '计量单位',
				align:'center'
			},
			{
				field : 'msehl',
				title : '度量单位文本',
				align:'center'
			},
			{
				field : 'flag',
				title : '标志',
				align:'center'
			},
			{
				field : 'sfzp',
				title : '是否经营赠品',
				align:'center'
			},
			{
				field : 'xchpf',
				title : '批次标识',
				align:'center'
			},
			{
				field : 'zzspsxz',
				title : '商品属性',
				align:'center'
			},
			{
				field : 'mtart',
				title : '商品类型',
				align:'center'
			},
			{
				field : 'zzspcd',
				title : '产地',
				align:'center'
			},
			{
				field : 'brgew',
				title : '重量（含包装)',
				align:'center'
			},
			{
				field : 'hoehe',
				title : '高（mm）',
				align:'center'
			},
			{
				field : 'class1',
				title : '一级分类',
				align:'center'
			},
			{
				field : 'class2',
				title : '二级分类',
				align:'center'
			},
			{
				field : 'class3',
				title : '三级分类',
				align:'center'
			},
			{
				field : 'zzspmd',
				title : '产品卖点',
				align:'center'
			},
			{
				field : 'updateFlag',
				title : '更新标志',
				align:'center'
			}
			] ],
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
					        	$('#matkl').combobox({
					        		url: "${ctx}/eccGoods/getGoodClass?classification=4&upperCode="+$('#class3').combobox('getValue'),
									valueField:'value', 
									textField:'text',
								});
					        	$("#matkl").combobox("clear");
				         	} 
						});
			        	$("#class3").combobox("clear");
			        	$("#matkl").combobox("clear");
		         	} 
				});
	        	$("#class2").combobox("clear");
	        	$("#class3").combobox("clear");
	        	$("#matkl").combobox("clear");
	     	} 
		}); 
		
		$('#prdha').combobox({
			url: "${ctx}/brand/getAllBrand",
			valueField:'id', 
			textField:'brand'
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

</script>
</head>
<body>
   <div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 150px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
				<tr align="center">
					<td width="10%" align="right">一级分类:</td>
					<td width="10%" align="left"><input name="class1"  id="class1" type="text"  class="easyui-combobox" ></td>
					<td width="10%" align="right">二级分类:</td>
					<td width="10%" align="left"><input name="class2"  id="class2" type="text"  class="easyui-combobox" ></td>
				  </tr>
				<tr>
					<td width="10%" align="right">三级分类:</td>
			    	<td width="10%"  align="left"><input name="class3" id="class3" type="text" class="easyui-combobox"></td>
					<td width="10%" align="right">商品分类:</td>
			    	<td width="10%" align="left"><input name="matkl"  id="matkl"  type="text" class="easyui-combobox"></td>
                  </tr>
                  <tr align="center">
					<td width="10%" align="right">品牌:</td>
					<td width="10%" align="left"><input name="prdha"  id="prdha" type="text"  class="easyui-combobox" ></td>
               </tr>
               	<tr>
               		<td align="right" colspan="4"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
               </tr>
			 </table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
