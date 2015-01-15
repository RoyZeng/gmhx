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
		onStart(document.body.clientHeight - 175);
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
		onStart(document.body.clientHeight - 175);
		$('#dataGrid').datagrid('cancelCellTip');
		$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
	}
	
	function onStart(heigh){
		dataGrid = $('#dataGrid').datagrid({
            title : "服务单",
            url : "${ctx}/serviceTicketCheck/getServiceTicketCheckPageList",
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
              {field:'service_id', title:'服务单号', width:30, align:'center', 
            },{field:'machine_type', title:'机型', width:10, align:'center', 
            },{field:'gome_code', title:'国美代码', width:20, align:'center', 
            },{field:'brand', title:'品牌', width:20, align:'center', 
            },{field:'internal_machine_code1', title:'内机条码1', width:20, align:'center', 
            },{field:'internal_machine_code2', title:'内机条码2', width:20, align:'center', 
            },{field:'external_machine_code', title:'外机条码',width:20, align:'center',
            },{field:'delivery_order_num', title:'提货单号',width:20, align:'center',
            },{field:'customer_name', title:'客户姓名',width:20, align:'center',
            },{field:'install_unit', title:'安装单位',width:20, align:'center',
            },{field:'install_date', title:'安装日期',width:20, align:'center',
            },{field:'service_type', title:'单据类型',width:20, align:'center',
            },{field:'service_date', title:'服务日期',width:20, align:'center',
            },{field:'service_status', title:'单据状态',width:20, align:'center',
            	formatter:function(value, row, index){ 
                	return statusVal(row.service_status);
                }
            },{field:'check_result', title:'审核结果',width:20, align:'center',
            },{field:'create_organization_s', title:'创建机构',width:20, align:'center',
            },{field:'create_time_s', title:'创建时间',width:20, align:'center',
            },{field:'action', title:'操作',width : 20, align:'center', 
            	formatter : function(value, row, index) {                                                                                                                                                     
					return '<a href="#" onclick="check(\'' + row.service_id + '\');">审核</a> <a href="#" onclick="show(\'' + row.service_id + '\');">查看</a>';
				}
            } ] ],
            onLoadSuccess : function() {
                $('#searchForm table').show();
                $(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
                parent.$.messager.progress('close');
            }
        });
		$('.datagrid-pager').pagination({
			  onSelectPage : function(pageNumber, pageSize) {
					$(this).pagination('loading');
					var queryParams = $.serializeObject($('#searchForm'));
					queryParams.currentPage = pageNumber;
					kParams.pageCount = pageSize;
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
	
	function check(serviceId){
		window.location.href = "${ctx}/serviceTicketCheck/serviceTicketCheckView/"+serviceId;
	}
	
	function show(serviceId){
		window.location.href = "${ctx}/serviceTicketCheck/serviceTicketView/"+serviceId;
	}
	
	function statusVal(status) {
		if (status == 'S0') {
			return '流程结束';
		} else if (status == 'S1') {
			return '暂存';
		} else if (status == 'S2') {
			return '提交';
		} else if (status == 'S3') {
			return '分部审核';
		} else if (status == 'S4') {
			return '总部审核';
		} else if (status == 'S5') {
			return '填写出库数量';
		} else if (status == 'S6') {
			return '出库';
		} else if (status == 'S7') {
			return '邮包发货';
		} else if (status == 'S8') {
			return '邮包收货';
		} else if (status == 'S9') {
			return '网店确认';
		}else if(status=='S10'){
			return "退回修改";
		}else if(status=='S11'){
			return "发货";
		}else if(status=='S12'){
			return "收货";
		}else if(status=='S13'){
			return "检测";
		}else if(status=='S14'){
			return "入库";
		}else if(status=='S15'){
			return "移库";
		}
	}
	
</script>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'north',title:'查询条件',border:false,collapsible:true,onBeforeCollapse:function(){clos();},onBeforeOpen:function(){ope();}"  style="height: 175px; overflow: hidden;">
            <form id="searchForm">
            	<table class="table table-hover table-condensed" width="100%;" style="width:100%; padding: 7px 20px 0px 50px">
					<tr>
						<td width="5%">服务单号</td>
						<td width="15%"><input style="width:85%" name="serviceTicket.serviceId" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td width="5%">机型</td>
						<td width="15%"><input style="width:85%" name="serviceProduct.machineType" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td width="5%">国美代码</td>
						<td width="15%"><input name="serviceProduct.gomeCode" placeholder="请输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/gmdm',panelHeight:'auto',editable:false "></td>
						<td width="5%">品牌</td>
						<td width="15%"><input name="serviceProduct.brand" placeholder="请输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/pp',panelHeight:'auto',editable:false "></td>
						<td width="5%">内机条码1</td>
						<td width="15%"><input style="width:85%" name="serviceProduct.internalMachineCode1" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
					</tr>
					<tr>
						<td>内机条码2</td>
						<td><input style="width:85%" name="serviceProduct.internalMachineCode2" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>外机条码</td>
						<td><input style="width:85%" name="serviceProduct.externalMachineCode" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>提货单号</td>
						<td><input style="width:85%" name="serviceProduct.deliveryOrderNum" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>客户姓名</td>
						<td><input style="width:85%" name="serviceCustomer.customerName" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>客户电话</td>
						<td><input style="width:85%" name="serviceCustomer.phone" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
					</tr>
					<tr>
						<td>安装单位</td>
						<td><input style="width:85%" name="serviceProduct.installUnit" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>安装日期</td>
						<td><input style="width:85%" name="serviceProduct.installDate" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>单据类型</td>
						<td width="20%"><input name="serviceTicket.serviceType"  placeholder="请输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/djlx',panelHeight:'auto',editable:false "></td>
						<td>联系人</td>
						<td><input style="width:85%" name="serviceTicket.linkMan" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>联系电话</td>
						<td><input style="width:85%" name="serviceTicket.linkPhone" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
					</tr>
					<tr>
						<td>接受分部</td>
						<td><input style="width:85%" name="serviceTicket.center" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>接收网点</td>
						<td><input style="width:85%" name="serviceTicket.website" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>服务日期</td>
						<td><input style="width:85%" name="serviceTicket.serviceDate" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>创建机构</td>
						<td><input style="width:85%" name="serviceTicket.createOrganizationS" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td>创建时间</td>
						<td><input style="width:85%" name="serviceTicket.createTimeS" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
					</tr>
					<tr>
						<td>机型分类</td>
						<td><input style="width:85%" ID="queryCard" type="text" placeholder="请输入查询条件" class="easyui-validatebox span2" value=""></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td align="right">
							<a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>
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