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
			title : "邮寄费用",
			url : "${ctx}/hxPostage/getHxPostagePageList",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 210,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : 'pos_id',
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
				field : 'pos_id',
				title : 'pos_id',
				hidden: true
			}, {
				field : 'pos_num',
				title : '邮件单号',
				width : 10,
				align:'center',
			}, {
				field : 'pos_receipt_unit',
				title : '收件单位',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'pos_recipient',
				title : '收件人',
				width : 10,
				align:'center',
				sortable : true
			},{
				field : 'pos_pay_unit',
				title : '邮寄付款单位',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'pos_send_unit',
				title : '发件单位',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'pos_way',
				title : '邮寄方式',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'pos_content',
				title : '邮寄内容',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'pos_date',
				title : '邮寄日期',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'pos_unit',
				title : '邮寄单位',
				width : 10,
				align:'center',
				sortable : true
			}  ,{
				field : 'pos_handlers',
				title : '经手人',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'pos_create_date',
				title : '创建日期',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'action',
				title : '操作',
				width : 10,
				align:'center',
				formatter : function(value, row, index) {                                                                                                                                                     
					return '<a href="#" onclick="update(\'' + row.pos_id + '\',\'' + row.pos_handlers + '\');">修改</a> <a href="#" onclick="show  (\'' + row.pos_id + '\',\'' + row.pos_handlers + '\');">查看</a> <a href="#" onclick="del(\'' + row.pos_id +'\',\''+ row.pos_num +'\');">删除</a> ';
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
		
		window.location.href="${ctx}/hxPostage/addView/";
	}
	
    function del(posId,pos_num){
    	var msg= "确认删除邮寄单号为:"+pos_num+"这条数据吗?";
    	if(confirm(msg)==true){
    		$.post
    		("${ctx}/hxPostage/deleteHxPostage/" +posId,
    	    $("#form").serialize(),
    	    function(returnObj){
    			if(returnObj.flag="success"){
    				$.messager.alert('提示:','删除成功!');
    				window.location.href="${ctx}/hxPostage/hxPostageView";
    			}else{
    				$.messager.alert('提示:','删除失败!');
    			}	
    		});
    	}
    }
	
	function update(pos_id, pos_handlers){
		var posId=pos_id;
		var pageMarkup = "list";//页面标记位
		window.location.href="${ctx}/hxPostage/updateView/"+ posId;
	}
	
	function show(pos_id, pos_handlers){
		var posId=pos_id;
		var pageMarkup = "list"; //页面标记位
		window.location.href="${ctx}/hxPostage/showView/"+ posId ;
	}
	
	function importExcel(){
		parent.$.modalDialog({
			title : '邮寄费用数据导入',
			width : 400,
			height :250,
			closable : false,
			href : '${ctx}/common/import/importData?templateName=postage&actionName=importHxPostage',
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
		window.location.href = "${ctx}/hxPostage/exportExcel?" + encodeURI($('#searchForm').serialize()) + "&tableField=" + fieldArray.join("|") + "&tableHeader=" + encodeURI(encodeURI(titleArray.join("|")));
	}
	
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 210px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
				<tr>
					<td width="5%">邮寄单号</td>
					<td width="5%"><input name="posNum" type="text" placeholder="请输入邮寄单号" class="span2" ></td>
					<td width="5%"></td>
					<td width="5%">收件单位</td>
			    	<td width="5%"><input name="posReceiptUnit" type="text" placeholder="请输入发件单位" class="span2" ></td>
					<td width="5%"></td>
					<td width="5%">收件人</td>
					<td width="5%"><input name="posRecipient" type="text" placeholder="请输入收件人" class="span2" ></td>
                    <td width="5%"></td>
                    <td width="55%"></td>              
               </tr>
			   <tr>
					<td width="5%">邮寄付款单位</td>
						<td width="25%"><input name="posPayUnit" type="text" placeholder="请输入邮寄付款单位" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/jg'"></td>
                    <td width="5%"></td>
                    <td width="5%">发件单位</td>
					<td width="25%"><input name="posSendUnit" type="text" placeholder="请输入发件单位" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/jg' " ></td>
					<td width="5%"></td>
					<td width="5%">邮寄方式</td>
					<td width="5%"><input class="easyui-combobox" name="posWay" placeholder="请输入邮寄方式" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/yjfs'"/></td>
	                <td width="5%"></td>	
	                <td width="55%"></td>		
				</tr>
				<tr>
					<td width="5%">邮寄日期</td>
						<td width="25%">
							<input id="posDate_sta" placeholder="选择起始日期" class="Wdate" name="posDate_sta" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'posDate_end\')}'})"/>至
							<input id="posDate_end" placeholder="选择结束日期" class="Wdate" name="posDate_end" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'posDate_sta\')}'})"/> 
						</td>
					<td width="5%"></td>
					<td width="5%">邮寄内容</td>
					<td width="5%"><input name="posContent" type="text" placeholder="请输入邮寄内容" class="span2" ></td>
					<td width="5%"></td>
					<td width="5%">邮寄单位</td>
					<td width="5%"><input name="posUnit" type="text" placeholder="请输入邮寄单位" class="easyui-validatebox span2"  value=""></td>
	                <td width="5%"></td>
	                <td width="55%"></td>			
				</tr>
				<tr>
				<td width="5%">创建日期:</td>
						<td width="25%">
							<input id="posCreateDate_sta" placeholder="选择起始日期" class="Wdate" name="posCreateDate_sta" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'posCreateDate_end\')}'})"/>至
							<input id="posCreateDate_end" placeholder="选择结束日期" class="Wdate" name="posCreateDate_end" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'posCreateDate_sta\')}'})"/> 
						</td>
					<td width="5%"></td>	
					<td width="5%">经手人</td>
					<td width="5%"><input name="posHandlers" type="text" placeholder="请输入经手人" class="easyui-validatebox span2" ></td>
					<td width="5%"></td>
					<td width="35%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
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
