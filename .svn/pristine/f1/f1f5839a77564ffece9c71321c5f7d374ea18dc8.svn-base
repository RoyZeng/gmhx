<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
td {white-space: nowrap;}
</style>
<script type="text/javascript">
	var dataGrid;
	$(function(){
		onStart(document.body.clientHeight - 150);
		$('#dataGrid').datagrid('cancelCellTip');
		$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
		
		$(".combo").attr("style","width: 85%; height: 20px;" );
		$(".combo-text").attr("style","width: 85%; height: 20px; line-height: 20px;" );
		$(".combo-panel").attr("style","width: 100%; height: auto;" );
	});
	
	function clos(){
		onStart(document.body.clientHeight - 28);
		$('#dataGrid').datagrid('cancelCellTip');
		$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
	}
	
	function ope(){
		onStart(document.body.clientHeight - 150);
		$('#dataGrid').datagrid('cancelCellTip');
		$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
	}
	
	function onStart(heigh){
	 	dataGrid = $('#dataGrid').datagrid({
			title : "工程机服务单",
			url : "${ctx}/installProject/getInstallReceiptPageList",
			striped : true,
            height : heigh,
            collapsible : true,
            autoRowHeight : false,
            remoteSort : false,
            idField : 'service_id',
            rownumbers : true,
            fitColumns : true,
            nowrap : true,
            pagination : true,
            checkOnSelect : false,
            selectOnCheck : false,
            queryParams : {
                currentPage : 1,
                pageCount : 10
            },
			columns : [ [ 
			   {field : 'service_id',title : '服务单号', width : 10 ,align:'center'
			}, {field : 'machine_type',title : '机型', width : 10,align:'center'
			}, {field : 'gome_code',title : '国美代码', width : 10,align:'center'
			}, {field : 'brand',title : '品牌', width : 10,align:'center'
			}, {field : 'machine_code',title : '机器条码', width : 10,align:'center'
			}, {field : 'customer_name',title : '客户姓名', width : 10,align:'center'
			}, {field : 'phone',title : '手机 ', width : 10,align:'center'
			}, {field : 'telephone',title : '固定电话 ', width : 10,align:'center'
			}, {field : 'bargain_code',title : '合同编码 ', width : 10,align:'center'
			}, {field : 'create_organization_p',title : '创建单位 ', width : 10,align:'center'
			}, {field : 'create_time_p',title : '创建日期 ', width : 10,align:'center'
			}, {field : 'action',title : '操作', width : 13,align:'center',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="checkTicket(\'' + row.service_id  + '\');">查看</a> <a href="#" onclick="updateTicket(\'' + row.service_id  + '\');">继续添加</a>';
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
			pageSize: 10,         
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
	};

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
	
	function checkTicket(serviceId){
		window.location.href = "${ctx}/installProject/installProjectShow/"+serviceId;
	}
	
	function updateTicket(serviceId){
		window.location.href = "${ctx}/installProject/installProjectUpdateView/"+serviceId;
	}
	
	function add(){
		window.location.href = "${ctx}/installProject/addview";
	}
	
	function del(){
		var ids = [];
		var checkedData = dataGrid.datagrid('getSelections');
		if(checkedData.length == 0){
			$.messager.alert('提示:','没有选择行!','warning');
			return;
		}
		$.each(checkedData, function(){
			ids.push($(this)[0].service_id);
		});
		parent.$.messager.confirm('提示', '确认要删除么？', function(r){
			if (r){
				$.post("${ctx}/installProject/deleteInstallProject",{
					ids : ids.join(",")
				},function(msg){
					if($.parseJSON(msg).flag == "success"){
						$.messager.alert('提示:','删除成功!');
						$('#dataGrid').datagrid("reload");
					}else{
						$.messager.alert('提示:','删除失败!');
					}
				});
			}
		});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:true,onBeforeCollapse:function(){clos();},onBeforeOpen:function(){ope();}"  style="height: 150px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" width="100%;" style="padding:10px 10px 10px 10px">
					<tr>
						<td width="7%">服务单号</td> 
						<td width="12%"><input style="width:85%" name="serviceTicket.serviceId" placeholder="请输入查询条件" class="span2" ></td>
						<td width="7%">机型</td> 
						<td width="12%"><input style="width:85%" name="serviceProduct.machineType" placeholder="请输入查询条件" class="span2" ></td>
						<td width="7%">国美代码</td> 
						<td width="12%"><input name="serviceProduct.gomeCode" placeholder="请输入查询条件" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/gmdm',panelHeight:'auto',editable:false"></td>
						<td width="7%">品牌</td> 
						<td width="12%"><input name="serviceProduct.brand"  placeholder="请输入查询条件" class="easyui-combobox" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/pp',panelHeight:'auto',editable:false "></td>
						<td width="7%">机器条码</td>
						<td width="12%"><input style="width:85%" name="serviceProduct.machineCode" placeholder="请输入查询条件" class="span2" ></td>
						<td width="5%" rowspan="4" align="center"><a href="#" id="query" class="easyui-linkbutton">查询</a></td>
					</tr>
					<tr>
						<td>内机条码1(空调)</td>
						<td><input style="width:85%" name="serviceProduct.internalMachineCode1" placeholder="请输入查询条件" class="span2" ></td>
						<td>内机条码2(空调)</td>
						<td><input style="width:85%" name="serviceProduct.internalMachineCode2" placeholder="请输入查询条件" class="span2" ></td>
						<td>外机条码(空调)</td>
						<td><input style="width:85%" name="serviceProduct.externalMachineCode" placeholder="请输入查询条件" class="span2" ></td>
						<td>客户姓名</td> 
						<td><input style="width:85%" name="serviceCustomer.customerName" placeholder="请输入查询条件" class="span2" ></td>
						<td>手机</td> 
						<td><input style="width:85%" name="serviceCustomer.phone" placeholder="请输入查询条件" class="span2" ></td>
					</tr>
					<tr>
						<td>固定电话</td> 
						<td><input style="width:85%" name="serviceCustomer.telephone" placeholder="请输入查询条件" class="span2" ></td>
						<td>合同编码</td> 
						<td><input style="width:85%" name="serviceProduct.bargainCode" placeholder="请输入查询条件" class="span2" ></td>
						<td>创建单位</td> 
						<td><input style="width:85%" name="serviceTicket.createOrganizationS" placeholder="请输入查询条件" class="span2" ></td>
						<td>接收分部</td> 
						<td><input style="width:85%" name="serviceTicket.center" placeholder="请输入查询条件" class="span2" ></td>
						<td>创建日期</td> 
						<td>
							<input style="width:85%" id="ksrq" placeholder="选择起始日期" class="Wdate" name="mod_createTime_st" type="text" readonly="readonly"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/><br/>
							<input style="width:85%" id="jsrq" placeholder="选择结束日期" class="Wdate" name="mod_createTime_end" type="text" readonly="readonly"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')}'})"/> 
						</td>
				</table>
			</form>
		</div>
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div id="toolbar" style="display: "";">
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>