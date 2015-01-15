<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$.ajaxSetup({
	  	async: false
	  });
  
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			type: 'POST',
			url:'${ctx}/hxInoutStock/getInoutSotckHistoryPageList',
			striped : true,
			height : document.documentElement.clientHeight * 0.98,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			checkOnSelect : false,
			singleSelect : true,
			selectOnCheck : false,
			toolbar : '#toolbar',
			pageSize : 20,
			queryParams : {
				pageNo : 1,
				pageCount : 20
			},
			columns:[[
		         {
					field : 'action',
					title : '操作',
					align:'center',
					width : 20,
					formatter : function(value, row, index) {
						return '<a href="#" onclick="view(\'' + row.list_number + '\', \'' + row.inout + '\',\''+row.fittingPositionType+'\', \'' + row.isNew + '\');">查看</a>';
					}
				},{
					field : 'list_number',
					title : '申请单号',
					align:'center',
					width : 50
				},{
					field : 'inout',
					hidden:true
				},{
					field : 'fittingPositionType',
					hidden:true
				}, {
					field : 'receive_company',
					title : '单位名称',
					align:'center',
					styler: cellStyler,
					width : 50
				}, {
					field : 'send_company',
					title : '关联仓库',
					align:'center',
					width : 50
				},{
					field : 'type',
					title : '科目',
					align:'center',
					width : 50
				}, {
					field : 'fitting_code',
					title : '物料编码',
					align:'center',
					width : 50
				}, {
					field : 'fitting_name',
					title : '物料名称',
					align:'center',
					width : 50,
					styler: cellStyler
				}, {
					field : 'count',
					title : '数量',
					align:'center',
					width : 50
				}, {
					field : 'price',
					title : '单价',
					hidden:true
				}, {
					field : 'totalPrice',
					title : '金额',
					align:'center',
					width : 50,
					formatter : function(value, row, index) {
						return row.count * row.price;
					}
				}, {
					field : 'create_person',
					title : '操作员',
					align:'center',
					width : 50
				},{
					field : 'update_time',
					title : '操作日期',
					align:'center',
					width : 50
				}]],
			loadMsg : "数据加载中...",
			onBeforeLoad : function(param) {
				$.serializeObject($('#searchForm'), param);
				param.fittingPositionType =  $("#fittingPositionType").combobox("getValue");
				param.inout =  $("#inout").combobox("getValue");
				param.isNew =  $("#isNew").combobox("getValue");
				param.currentPage = param.page;
				param.pageCount = param.rows;
			},
			onLoadSuccess : function() {
				$('#searchForm table').show();
				$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
				parent.$.messager.progress('close');
			}
		});
		fetchData();
	});
	
	function fetchData(){
		var title = getTitle();
		$("#dataGrid").datagrid({title: title}); 
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
		$('#dataGrid').datagrid('cancelCellTip');
    	$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
	};
	
	function getTitle(){
		var ptype = $("#fittingPositionType").combobox("getValue");
		inout = $("#inout").combobox("getValue");
		isNew = $("#isNew").combobox("getValue");
		var title = "";
		if(ptype==1){
			title = "总部配件";
		}else if(ptype==2){
			title = "分部配件";
		}else if(ptype == 3){
			title = "网点配件";
		}
		if(isNew == "1"){
			title += '新料';
		}else{
			title +='旧料';
		}
		if(inout =="1"){
			title += '出库';
		}else{
			title += '入库';
		}
		return "浏览"+title+"历史";
	}
	
	function cellStyler(value,row,index){
		return 'height: fix;';
	}
	$.serializeObject = function(form) {
		var o = {};
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
	
	function view(listNumber, inout, fittingPositionType,isNew){
		window.location.href="${ctx}/hxInoutStock/viewInoutStock/"+ listNumber+"?inout="+inout+"&fittingPositionType="+fittingPositionType
			+"&isNew="+isNew;

	}

</script>
</head>
<body>
   <div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 180px;">
	   <form id="searchForm">
	   	<input type="hidden" id="ptype" name="ptype" value="${ptype}">
	     <table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
		     <tr>
		     	<td width="10%">申请单号</td>
				<td width="5%"><input name="listNumber" type="text" placeholder="请输入申请单号" class="span2"></td>
				<td width="5%"></td>
				<td width="10%">单位名称</td>
				<td width="5%"><input class="easyui-combobox" name="receiveCompany"  id="receiveCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"/></td>
				<td width="5%"></td>
				<td width="10%">关联仓库</td>
				<td width="5%"><input name="sendCompany" id="sendCompany" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"></td>
				<td width="5%"></td>
				<td width="10%"></td>
				<td width="10%"></td>
				<td width="20%"></td>
			</tr>
			<tr>
				<td width="10%">科目</td>
				<td width="5%"><input name="type" class="easyui-combobox" placeholder="请输入科目"  data-options="url:'${ctx}/hxCode/getCombobox/fitting_type',editable:false"/></td> 
				<td width="5%"></td>
				<td width="10%">适应机型</td>
				<td width="5%"><input name="suitModel" type="text" placeholder="请输入适应机型" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/jx',hasDownArrow:false"/></td> 
				<td width="5%"></td>
				<td width="10%">配件分类</td>
				<td width="5%"><input name="fittingType" class="easyui-combobox" placeholder="请输入配件分类"  data-options="url:'${ctx}/hxCode/getCombobox/pjfl',editable:false"/></td>
				<td width="5%"></td>
				<td width="10%"></td>
				<td width="10%"></td>
				<td width="20%"></td>
			</tr>
			<tr>
		     	<td width="10%">物料编码</td>
				<td width="5%"><input name="fittingCode" type="text" placeholder="请输入配件编码" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/pjbm',hasDownArrow:false"/></td> 
				<td width="5%"></td>
				<td width="10%">部品号</td>
				<td width="5%"><input name="partsCode" type="text" placeholder="请输入部品号" class="span2"></td>
				<td width="5%"></td>
				<td width="10%">操作日期</td>
				<td width="5%">
							<input id="ksrq" placeholder="选择起始日期" class="Wdate" name="ksrq" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>至
							<input id="jsrq" placeholder="选择结束日期" class="Wdate" name="jsrq" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')}'})"/> 
				</td>
				<td width="5%"></td>
				<td width="10%"></td>
				<td width="10%"></td>
				<td width="20%"></td>
			</tr>
			<tr>
				<td width="15%">当前库存位置</td>
				<c:if test="${ptype == 1}">
				<td width="5%">
					<select id="fittingPositionType" name="fittingPositionType" class="easyui-combobox" style='width:100%;'>   
					    <option value="1" selected="selected">总部仓库</option>   
					    <option value="2">分部仓库</option>   
					    <option value="3">网点仓库</option>   
					</select>  
				</td>
				</c:if> 
				<c:if test="${ptype == 2}">
				<td width="5%">
					<select id="fittingPositionType" name="fittingPositionType" class="easyui-combobox" style='width:100%;'>
					    <option value="2" selected="selected">分部仓库</option>   
					    <option value="3">网点仓库</option>   
					</select>  
				</td>
				</c:if>
				<c:if test="${ptype == 3}">
				<td width="5%">
					<select id="fittingPositionType" name="fittingPositionType" class="easyui-combobox" style='width:100%;'>   
					    <option value="3" selected="selected">网点仓库</option>   
					</select> 
				</td>
				</c:if> 
				<td width="5%"/>
				<td width="15%">出入/入库信息</td>
				<td width="5%">
					<select id="inout" name="inout" class="easyui-combobox" style='width:100%;'>
					    <option value="1" selected="selected">出库</option>   
					    <option value="2">入库</option>   
					</select>  
				</td>
				<td width="5%"/>
				<td width="15%">配件新料/旧料</td>
				<td width="5%">
					<select id="isNew" name="isNew" class="easyui-combobox" style='width:100%;'>
					    <option value="1" selected="selected">新料</option>   
					    <option value="0">旧料</option>   
					</select>  
				</td>
				<td width="5%"></td>
				<td width="5%" align="left"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search" onclick="fetchData()">查询</a></td>
				<td width="5%"></td>
				<td width="15%"></td>
			</tr>
		</table>
	  </form>
	</div>
	<div id="toolbar" style="display: none;">
<!-- 		<a href="javascript:void(0);" class="easyui-linkbutton" -->
<!-- 			data-options="iconCls:'icon-excel',plain:true" onclick="importStocks();">导入</a> -->
		<a href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
	</div>
	<div data-options="region:'center',border:false">
		<table id="dataGrid"></table>
	</div>
	</div>
</body>
</html>
