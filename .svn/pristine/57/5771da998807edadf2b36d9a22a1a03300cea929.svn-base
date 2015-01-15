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
			title : "换移机结算标准",
			url : "${ctx}/standardFee/getHxMoveChangePageList",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 210,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'fee_id',
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			checkOnSelect : false,
			selectOnCheck : false,
			singleSelect : false,
			queryParams : {
				currentPage : 1,
				pageCount : 10
			},
			columns : [ [ { field:'ck',checkbox:true
			},{
				field : 'fee_id',
				title : 'fee_id',
				hidden: true
			},{
				field : 'classify_name',
				title : '产品分类',
				width : 10,
				align:'center',
			}, {
				field : 'whole_fee',
				title : '整机费用',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'inner_fee',
				title : '内机费用',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'outer_fee',
				title : '外机费用',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'operation_type',
				title : '移/换机',
				width : 10,
				align:'center'
			},{
				field : 'action',
				title : '操作',
				width : 10,
				align:'center',
				formatter : function(value, row, index) {                                                                                                                                                     
					return '<a href="#" onclick="update(\'' + row.fee_id + '\');">修改</a> <a href="#" onclick="show  (\'' + row.fee_id + '\');">查看</a> ';
 					<!--<a href="#" onclick="del(\'' + row.pos_id +'\',\''+ row.classify_name +'\');">删除</a> -->
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
				$('#dataGrid').datagrid("options").queryParams = {
					currentPage : pageNumber,
					pageCount : pageSize
				};
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
		
		window.location.href="${ctx}/standardFee/addView/";
	}
	
    function del(){
    	var selected = $("#dataGrid").datagrid("getChecked");
        if (selected.length == 0) {
            alert("请选择要删除的内容!");
            return;
        }
        var idString = "";
        $.each(selected, function (index, item) {
            idString += item.fee_id + ",";
        });
    	var msg= "确认删除选中的 "+selected.length+" 条数据吗?";
    	
    	if(confirm(msg)==true){
    	var url = "${ctx}/standardFee/deleteMoveCheckout/" +idString;
    		$.post(url,
	    	    function(returnObj){
	    			if(returnObj.flag="success"){
	    				$.messager.alert('提示:','删除成功!');
	    				//$("#dataGrid").datagrid("clearChecked");
	    				window.location.href="${ctx}/standardFee/moveChangeCheckoutView";
	    			}else{
	    				$.messager.alert('提示:','删除失败!');
	    			}	
	    		});
    	}
    }
	
	function update(feeID){
		var pageMarkup = "list";//页面标记位
		window.location.href="${ctx}/standardFee/updateView/"+ feeID;
	}
	
	function show(feeID){
		var pageMarkup = "list"; //页面标记位
		window.location.href="${ctx}/standardFee/showView/"+ feeID ;
	}
	
	function importExcel(){
		parent.$.modalDialog({
			title : '换移机结算费用数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=feemovemachine&actionName=importHxFeeMoveMachine',
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
		window.location.href = "${ctx}/standardFee/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
	
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 210px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
				<tr>
					<td width="15%">移/换机:</td>
					<td width="5%"><input name="operationType" type="text" placeholder="请选择移换机选项" class="easyui-combobox" data-options="url:'${ctx}/standardFee/getCombobox/mcm'"></td>
					<td width="10"></td>
					<td width="15%">产品分类:</td>
					<td width="5%"><input name="classifyCode" type="text" placeholder="请输入产品分类" class="easyui-combobox" data-options="url:'${ctx}/standardFee/getCombobox/cpfl'"></td>
					<td width="10%"></td>
					<td width="15%">整机费用:</td>
			    	<td width="5%"><input name="wholefee" type="text" placeholder="请输入整机费用" class="span2"></td>
					<td width="10%"></td>
					<td width="25%"></td>
                  </tr>
                  <tr>
                  	<td width="15%">内机费用:</td>
					<td width="5%"><input name="innerfee" type="text" placeholder="请输入内机费用" class="span2"></td> 
					<td width="10"></td>
                    <td width="15%">外机费用:</td>
					<td width="5%"><input name="outerfee" type="text" placeholder="请输入外机费用" class="span2"></td>
                    <td width="5 %"></td>
					<td width="5%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
               </tr>
			 </table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-cut',plain:true" onclick="return del();">删除</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true"
				onclick="importExcel();">费用导入</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
