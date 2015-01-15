<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$.ajaxSetup({//设置ajax同步请求,防止多次请求顺序错乱
	  	async: false
	  });
	
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			type: 'POST',
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
			pageSize: 20,
			queryParams : {
				pageNo : 1,
				pageCount : 20
			},
			loadMsg : "数据加载中..."
		});
		var ptype = ${ptype};
		fetchData(ptype);//生成datagrid column
		
// 		$('.datagrid-pager').pagination({
// 			buttons: $("#exportDiv")
// 		});
		
// 		$('.datagrid-pager').pagination({
// 			pageSize: 10,//每页显示的记录条数，默认为10   
// 			 onSelectPage : function(pageNumber, pageSize) {
// 					$(this).pagination('loading');
// // 					var queryParams = $.serializeObject($('#searchForm'));
// // 					queryParams.currentPage = pageNumber;
// // 					queryParams.pageCount = pageSize;
// 					$('#dataGrid').datagrid("options").currentPage = pageNumber;
// 					$('#dataGrid').datagrid("options").pageCount = pageCount;
// 					alert(pageSize);
// 					$('#dataGrid').datagrid("reload");
// 					$(this).pagination('loaded');
// 				}
// 		});
	});
	
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
	function fetch(){
		var ptype = $("#specifiedType").combobox("getValue");
		//系统物料类型到库存类型的转换
		fetchData(ptype);//生成datagrid column
	}
	function refreshDataGrid() {
		$('#dataGrid').datagrid("reload");
		parent.$.modalDialog.handler.dialog('close');	
	}	
	
	function show(fittingCode){
		var pageMakeup = "stockList";
		window.location.href="${ctx}/hxFitting/viewHxFitting/"+ fittingCode+"/"+pageMakeup;
	}
	
	function totalFBMoney(row){
		var num =  row.price*row.stock*1.3;
		var total = Math.round(num*100)/100;
		return total;
	}
	
	function totalMoney(row){
		var num =  row.price*row.stock;
		var total = Math.round(num*100)/100;
		return total;
	}
	
	function totalWDMoney(row){
		var num =  row.wdPrice*row.stock;
		var total = Math.round(num*100)/100;
		return total;
	}
	
	function fetchData(ptype) {
		var s = "";
		var isNew = $("#isNew").combobox("getValue");
		s = "[[";
		if (ptype == 1) {
			s = s + "{field : 'fittingCode',title : '配件编码',width : 25,	align:'center'}," + 
					"{field : 'fittingName',title : '配件名称',width : 25,	align:'center'}," +
					"{field : 'availableStock',		title : '库存(可用)',   width : 20, align:'center'}," +
					"{field : 'stock',		hidden:true}," +
					"{field : 'price',		hidden:true}," +
					"{field : 'totalPrice',title : '金额(元)',	width : 20}";

		} else if (ptype == 2) {
			s = s + "{field : 'orgName',	title : '分部名称',width : 25,	align:'center'}," +
					"{field : 'fittingCode',title : '配件编码',width : 25,	align:'center'}," + 
					"{field : 'fittingName',title : '配件名称',width : 25,	align:'center'}," +
					"{field : 'countWay',   title : '在途数量',width : 20, align:'center'}," +
					"{field : 'availableStock',		title : '库存(可用)',   width : 20, align:'center'}," +
					"{field : 'stock',		hidden:true}," +
					"{field : 'price',		hidden:true}," +
					"{field : 'wdPrice',		hidden:true}," +
					"{field : 'available',		title : '金额(元)',   width : 20, align:'center',formatter:function(value, row, index){return totalWDMoney(row)}}," +
					"{field : 'totalPrice', title : '分部金额(元)',width : 20, align:'center'}";

		} else if(ptype == 3){
				s = s + "{field : 'orgName',	title : '网点名称',width : 25,	align:'center'}," +
					"{field : 'fittingCode',title : '配件编码',width : 25,	align:'center'}," + 
					"{field : 'fittingName',title : '配件名称',width : 25,	align:'center'},";
				if(isNew == "1"){
					s = s +
					"{field : 'countWay',   title : '在途数量',width : 20, align:'center'},";
				}
				s = s +
					"{field : 'availableStock',		title : '库存(可用)',   width : 20, align:'center'}," +
					"{field : 'stock',		hidden:true}," +
					"{field : 'price',		hidden:true}," +
					"{field : 'totalPrice',title : '金额(元)',	width : 20, align:'center'}";
		}
		s = s + "]]";
		var options = {};
		options.url = '${ctx}/hxCurrentStock/getHxCurrentStockPageList?ptype='+ptype;
		var queryParams = $.serializeObject($('#searchForm'));
		options.queryParams = queryParams;
		options.columns = eval(s);
		options.columns[0].push({
			field : 'desc',
			title : '查看详情',
			width : 20,
			formatter : function(value, rec) {
				return "<a href=\"javascript:show(\'" + rec.fittingCode
						+ "\');\">详情</a>";
			}
		});
		var title = "";
		if(ptype==1){
			title = "总部";
		}else if(ptype==2){
			title = "分部";
		}else if(ptype == 3){
			title = "网点";
		}
		if(isNew == "1"){
			title += '新';
		}else{
			title +='旧';
		}
		options.onBeforeLoad=function(param) {
			param.currentPage = param.page;
			param.pageCount = param.rows;
		};
		$("#dataGrid").datagrid({title:"浏览"+title+"配件库存信息"}); 
		$('#dataGrid').datagrid(options);
		
		$('#dataGrid').datagrid('cancelCellTip');
    	$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
    	
		var pager = $('#dataGrid').datagrid('getPager');
	    pager.pagination({buttons:$('#exportDiv').clone()});
	};
	function importStocks(){
		parent.$.modalDialog({
			title : '库存数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=current_stock&actionName=importStocks',
			buttons : [ 
			{
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	};
	function exportDataGrid(){
		var bbb = $.serializeObject($('#searchForm'));
		var ptype = $("#specifiedType").combobox("getValue");
		var startPage = $("#exp_firstpage").val();
		var endPage = $("#exp_endpage").val();
		var pageSize = $('#dataGrid').datagrid('options').pageSize;
		bbb.ptype=ptype;bbb.startPage=startPage;bbb.endPage=endPage;bbb.pageSize=pageSize;
	    window.location.href = '${ctx}/hxCurrentStock/exportBatchExcel?startPage='+startPage+'&endPage='+endPage+'&pageSize='+pageSize+"&"+ encodeURI($('#searchForm').serialize());
	};
	
</script>
</head>
<body>
   <div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 140px; overflow: hidden;">
	   <form id="searchForm">
	   	<input type="hidden" id="ptype" name="ptype" value="${ptype}">
	     <table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
		     <tr>
		    	<c:if test="${ptype == 1}">
					<td width="10%">机构名称</td>
					<td width="5%"><input name="orgName" placeholder="请输入机构名称" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"></td>
					<td width="5%"></td>
				</c:if>
		     	<c:if test="${ptype == 2}">
					<td width="10%">机构名称</td>
					<td width="5%"><input name="orgName" placeholder="请输入机构名称" class="easyui-combobox" data-options="url:'${ctx}/hxCurrentStock/getFbOrgNames',hasDownArrow:false"></td>
					<td width="5%"></td>
				</c:if>
				<c:if test="${ptype == 3}">
					<td width="10%">网点名称</td>
					<td width="5%"><input name="orgName" placeholder="请输入机构名称" class="easyui-combobox" data-options="url:'${ctx}/hxCurrentStock/getFbOrgNames',hasDownArrow:false"></td> 
					<td width="5%"></td>
				</c:if>
				<td width="10%">适应机型</td>
				<td width="5%"><input name="suitModel" class="easyui-combobox" placeholder="请输入科目"  data-options="url:'${ctx}/hxCode/getCombobox/jx',hasDownArrow:false"/></td>
				<td width="5%"></td>
				<td width="10%">配件编码</td>
				<td width="5%"><input name="fittingCode" class="easyui-combobox" placeholder="请输入科目"  data-options="url:'${ctx}/hxCode/getCombobox/pjbm',hasDownArrow:false"/></td>
				<td width="5%"></td>
<!-- 				<td width="5%"></td> -->
				<td width="30%"></td>
			</tr>
			<tr>
				<td width="10%">配件名称</td>
				<td width="5%"><input name="fittingName" type="text" placeholder="请输入配件名称" class="span2"></td> 
				<td width="5%"></td>
				<td width="10%">部品号</td>
				<td width="5%"><input name="partsCode" type="text" placeholder="请输入部品号" class="span2"></td>
				<td width="10%"></td>
				<td width="5%"></td>
				<td width="5%"></td>
				<td width="5%"></td>
				<td width="30%"></td>
			</tr>
			<tr>
				<td width="15%">当前库存位置</td>
				<c:if test="${ptype == 1}">
					<td width="5%">
						<select id="specifiedType" name="specifiedType" class="easyui-combobox" width="100%">   
						    <option value="1" selected="selected">总部当前库存</option>   
						    <option value="2">分部当前库存</option>   
						    <option value="3">网点当前库存</option>   
						</select>  
					</td>
				</c:if> 
				<c:if test="${ptype == 2}">
					<td width="5%">
						<select id="specifiedType" name="specifiedType" class="easyui-combobox" width="100%">
						    <option value="2" selected="selected">分部当前库存</option>   
						    <option value="3">网点当前库存</option>   
						</select>  
					</td>
				</c:if>
				<c:if test="${ptype == 3}">
					<td width="5%">
						<select id="specifiedType" name="specifiedType" class="easyui-combobox" width="100%">   
						    <option value="3" selected="selected">网点当前库存</option>   
						</select> 
					</td>
				</c:if> 
				<td width="5%"/>
				<td width="15%">新料/旧料</td>
				<td width="5%">
					<select id="isNew" name="isNew" class="easyui-combobox" width="100%">
					    <option value="1" selected="selected">新料</option>   
					    <option value="0">旧料</option>   
					</select>  
				</td>
				<td width="5%"></td>
				<td width="20%">只显示有库存配件</td>
				<td width="5%" align="left">
               		 <input type="checkbox" name="showAll"/>
				</td>
				<td width="5%"></td>
				<td width="5%" align="left"><input id="query" type="button"  onclick="fetch()" value="查询"/></td>
				<td width="5%"></td>
				<td width="15%"></td>
			</tr>
		</table>
	  </form>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${ptype == 1}">
		
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-excel',plain:true" onclick="importStocks();">导入</a>
		</c:if>
<!-- 		<a href="javascript:void(0);" class="easyui-linkbutton" -->
<!-- 			data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a> -->
	</div>
	<div data-options="region:'center',border:false">
		<table id="dataGrid"></table>
	</div>
	</div>
	<div id="exportDiv">
		从第
		<input name="exp_firstpage" class="easyui-numberbox" id="exp_firstpage" type="text" size="3" value="1">
		页 到第
		<input name="exp_endpage" class="easyui-numberbox" id="exp_endpage" type="text" size="3" value="1">
		页
		<input name="btnExport" id="btnExport" type="button" value="导出" onClick="exportDataGrid();">
	</div>
</body>
</html>