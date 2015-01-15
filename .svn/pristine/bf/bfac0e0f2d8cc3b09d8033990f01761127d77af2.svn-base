<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	var dataGrid;
	$(function(){
		onStart(document.body.clientHeight - 340);
		$('#dataGrid').datagrid('cancelCellTip');
		$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
	});
	
	function clos(){
		onStart(document.body.clientHeight - 28);
		$('#dataGrid').datagrid('cancelCellTip');
		$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
	}
	
	function ope(){
		onStart(document.body.clientHeight - 340);
		$('#dataGrid').datagrid('cancelCellTip');
		$('#dataGrid').datagrid('doCellTip',{'max-width':'300px'});
	}
	
	function onStart(heigh){
        dataGrid = $('#dataGrid').datagrid({
            title : "服务单",
            url : "${ctx}/serviceTicketCreate/getServiceTicketPageList",
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
              {field : 'service_id',title :'服务单号',width : 30,align:'center'
            },{field : 'category',title : '品<br/>类',width : 6,align:'center'	  
            },{field : 'machine_type',title : '机<br/>型',width : 6,align:'center'
            },{field : 'gome_code',title : '国美<br/>代码',width : 10,align:'center'
            },{field : 'brand',title : '品<br/>牌',width : 6,align:'center'
            },{field : 'machine_code',title : '机器<br/>条码',width : 10,align:'center'	
            },{field : 'internal_machine_code1',title : '内机<br/>条码1',width : 12,align:'center'
            },{field : 'internal_machine_code2',title : '内机<br/>条码2',width : 12,align:'center'
            },{field : 'external_machine_code',title : '外机<br/>条码',width : 10,align:'center'
            },{field : 'delivery_order_num',title : '提货<br/>单号',width : 10,align:'center'
            },{field : 'customer_name',title : '客户<br/>姓名',width : 10,align:'center'
            },{field : 'customer_type',title : '客户<br/>类别',width : 10,align:'center'
            },{field : 'customer_phone',title : '客户<br/>电话',width : 10,align:'center'
            },{field : 'phone',title : '手<br/>机',width : 6,align:'center'
            },{field : 'install_unit',title : '安装<br/>单位',width : 10,align:'center'
            },{field : 'install_date',title : '安装<br/>日期',width : 10,align:'center'
            },{field : 'invoice_num',title : '发票<br/>&nbsp;号',width : 10,align:'center'
            },{field : 'buyer',title : '购买<br/>者姓名',width : 14,align:'center'
            },{field : 'sale_price',title : '销售<br/>价格',width : 10,align:'center'
            },{field : 'sale_center',title : '销售<br/>分部',width : 10,align:'center'
            },{field : 'sale_market',title : '销售<br/>门店',width : 10,align:'center'
            },{field : 'sale_date',title : '销售<br/>日期',width : 10,align:'center'
            },{field : 'install_province',title : '安装<br/>省份',width : 10,align:'center'
            },{field : 'install_detail_address',title : '安装详<br/>细地址',width : 14,align:'center'
            },{field : 'voucher_type',title : '单据<br/>类型',width : 10,align:'center'
            },{field : 'center',title : '接收<br/>分部',width : 10,align:'center'	
            },{field : 'service_date',title : '服务<br/>日期',width : 10,align:'center'
            },{field : 'service_status',title : '单据<br/>状态',width : 10,align:'center', 
            	formatter:function(value, row, index){ 
            	return statusVal(row.service_status);
            }
            },{field : 'check_result',title : '审核<br/>结果',width : 10,align:'center'
            },{field : 'check_fault_reason',title : '机审无<br/>效原因',width : 14,align:'center'
            },{field : 'center_check_comment',title : '分部审<br/>核意见',width : 14,align:'center'
            },{field : 'headquarters_check_comment',title : '总部审<br/>核意见',width : 14,align:'center'
            },{field : 'create_organization_s',title : '创建<br/>机构',width : 10,align:'center'
            },{field : 'create_time_s',title : '创建<br/>时间',width : 10,align:'center'
            },{field : 'invalid_code',title : '故障<br/>代码',width : 10,align:'center'
            },{field : 'invalid_name',title : '故障<br/>名称',width : 10,align:'center'
            },{field : 'invalid_reason',title : '故障<br/>原因',width : 10,align:'center'
            },{field : 'distance_apply_num',title : '远程申<br/>请单号',width : 14,align:'center'
            },{field : 'maintain_way',title : '维修<br/>方式',width : 10,align:'center'
            },{field : 'parts_code',title : '配件<br/>编码',width : 10,align:'center'
            },{field : 'parts_name',title : '配件<br/>名称',width : 10,align:'center'
            },{field : 'parts_price',title : '配件<br/>价格',width : 10,align:'center'
            },{field : 'parts_num',title : '配件<br/>数量',width : 10,align:'center'
            },{field : 'old_parts_code',title : '旧配<br/>件编码',width : 14,align:'center'
            },{field : 'old_parts_name',title : '旧配<br/>件名称',width : 14,align:'center'
            },{field : 'old_parts_price',title : '旧配<br/>件价格',width : 14,align:'center'
            },{field : 'callback_result',title : '回访<br/>结果',width : 10,align:'center'
            },{field : 'action',title : '操作',width : 30,align:'center',
				formatter : function(value, row, index) {     
					if(row.service_status=="已录入"){
						return '<a href="#" onclick="showService(\'' + row.service_id + '\');">查看</a>|<a href="#" onclick="update(\'' 
								+ row.service_id + '\');">修改</a>|<a href="#" onclick="deleteTicket(\'' + row.service_id + '\');">删除</a>';
					}else{
						return '<a href="#" onclick="show(\'' + row.service_id + '\');">查看</a>';
					}
				}
			}] ],
            toolbar : '#toolbar',
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
    function refreshDataGrid() {
        $('#dataGrid').datagrid("reload");
        parent.$.modalDialog.handler.dialog('close');    
    }
    
    function add(){
    	window.location.href="${ctx}/serviceTicketCreate/addServiceTicketView";
    }

    function show(serviceId){
    	window.location.href="${ctx}/serviceTicketCreate/serviceTicketDetailView/"+ serviceId;
    }
    
    function update(serviceId){
    	window.location.href="${ctx}/serviceTicketCreate/updateServiceTicketView/"+ serviceId;
    }
    
    function showService(serviceId){
    	window.location.href="${ctx}/serviceTicketCreate/serviceTicketView/"+ serviceId;
    }
    
    function deleteTicket(serviceId){
    	$.messager.confirm("", "您确定要删除吗？", function (data) {
    		if(data){
    			$.post("${ctx}/serviceTicketCreate/deleteServiceTicket/"+ serviceId, 
        				function(msg){
        					if($.parseJSON(msg).flag == "success"){
        						$.messager.alert('','删除成功!');
        						$('#dataGrid').datagrid("reload");
        					}else{
        						$.messager.alert('','删除失败!');
        					}
        				}
        			); 
    		}
    	});
    }
    
    $(document).keydown(function(event){
    	if(event.keyCode==13){
    		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    	}
    }); 
    
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
        <div data-options="region:'north',title:'查询条件',border:false,collapsible:true,onBeforeCollapse:function(){clos();},onBeforeOpen:function(){ope();}" style="height: 340px; overflow: hidden;">
            <form id="searchForm">
            	<table style="width:100%; padding: 10px 10px 10px 10px;">
            		<tr>
            			<td width="5%">安装日期:</td>
                       	<td width="15%">
                        	<input id="ksrq" size="14" placeholder="起始安装日期" class="Wdate" name="mod_installDate_st" type="text" readonly="readonly" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>
							<input id="jsrq" size="14" placeholder="结束安装日期" class="Wdate" name="mod_installDate_end" type="text" readonly="readonly" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')||\'%y-%M-%d\'}'})"/> 
                       	</td>
                       	<td width="5%">销售日期:</td>
                        <td width="15%">
                        	<input id="ksrq" size="14" placeholder="起始销售日期" class="Wdate" name="mod_saleDate_st" type="text" readonly="readonly" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>
							<input id="jsrq" size="14" placeholder="结束销售日期" class="Wdate" name="mod_saleDate_end" type="text" readonly="readonly" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')||\'%y-%M-%d\'}'})"/>
                        </td>
                        <td width="5%">服务日期:</td>
                        <td width="15%">
                        	<input id="ksrq" size="14" placeholder="起始服务日期" class="Wdate" name="mod_serviceDate_st" type="text" readonly="readonly" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>
							<input id="jsrq" size="14" placeholder="结束服务日期" class="Wdate" name="mod_serviceDate_end" type="text" readonly="readonly" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')||\'%y-%M-%d\'}'})"/>
                        </td>
                        <td width="5%">审核时间:</td>
                        <td width="15%">
                       		<input id="ksrq" size="14" placeholder="起始审核日期" class="Wdate" name="mod_checkDate_st" type="text" readonly="readonly" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>
							<input id="jsrq" size="14" placeholder="结束审核日期" class="Wdate" name="mod_checkDate_end" type="text" readonly="readonly" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')||\'%y-%M-%d\'}'})"/>
                        </td>
                        <td width="5%">创建时间:</td>
                        <td width="15%">
                        	<input id="ksrq" size="14" placeholder="起始创建日期" class="Wdate" name="mod_createDate_st" type="text" readonly="readonly" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>
							<input id="jsrq" size="14" placeholder="结束创建日期" class="Wdate" name="mod_createDate_end" type="text" readonly="readonly" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')||\'%y-%M-%d\'}'})"/>
                        </td>
            		</tr>
                    <tr>
                        <td>服务单号:</td><td><input name="serviceTicket.serviceId" placeholder="输入查询条件" class="span2"/></td>
                        <td>机型:</td><td><input name="serviceProduct.machineType" placeholder="输入查询条件" class="span2"/></td>
                        <td>国美代码:</td><td><input id="gomeCode" name="serviceProduct.gomeCode" placeholder="输入查询条件" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/gmdm',panelHeight:'auto',editable:false"/></td>
                        <td>品牌:</td><td><input name="serviceProduct.brand" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/pp',panelHeight:'auto',editable:false "/></td>
                        <td>内机条码1:</td><td><input name="serviceProduct.internalMachineCode1" placeholder="输入查询条件" class="span2"/></td>
                    </tr>
                    <tr>
                        <td>内机条码2:</td><td><input name="serviceProduct.internalMachineCode2" placeholder="输入查询条件" class="span2"/></td>
                        <td>外机条码:</td><td><input name="serviceProduct.externalMachineCode" placeholder="输入查询条件" class="span2"/></td>
                        <td>提货单号:</td><td><input name="serviceProduct.deliveryOrderNum" placeholder="输入查询条件" class="span2"/></td>
                        <td>是否售前机:</td><td><input name="serviceProduct.isSale" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/sf',panelHeight:'auto',editable:false"/></td>
                        <td>客户姓名:</td><td><input name="serviceCustomer.customerName" placeholder="输入查询条件" class="span2"/></td>
                    </tr>
                    <tr>
                        <td>客户类别:</td><td><input name="serviceCustomer.customerType" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/khlb',panelHeight:'auto',editable:false"/></td>
                        <td>客户电话:</td><td><input name="serviceCustomer.phone" placeholder="输入查询条件" class="span2"/></td>
                        <td>安装单位:</td><td><input name="serviceProduct.installUnit" placeholder="输入查询条件" class="span2"/></td>
                        <td>购买者姓名:</td><td><input name="serviceProduct.buyer" placeholder="输入查询条件" class="span2"/></td>
                        <td>销售分部:</td><td><input name="serviceProduct.saleCenter" placeholder="输入查询条件" class="span2"/></td>
                    </tr>
                 	<tr>
                        <td>销售门店:</td><td><input name="serviceProduct.saleMarket" placeholder="输入查询条件" class="span2"/></td>
                        <td>安装省份:</td><td><input name="serviceProduct.installProvince" placeholder="输入查询条件" class="span2"/></td>
                        <td>安装详细地址:</td><td><input name="serviceProduct.installDetailAddress" placeholder="输入查询条件" class="span2"/></td>
                        <td>单据类型:</td><td><input name="serviceTicket.serviceType" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/djlx',panelHeight:'auto',editable:false "/></td>
                        <td>联系人:</td><td><input name="serviceTicket.linkMan" placeholder="输入查询条件" class="span2"/></td>
                    <tr>
                   	</tr>
                        <td>联系电话:</td><td><input name="serviceTicket.linkPhone" placeholder="输入查询条件" class="span2"/></td>
                        <td>接收分部:</td><td><input name="serviceTicket.center" placeholder="输入查询条件" class="span2"/></td>
                        <td>接收网点:</td><td><input name="serviceTicket.website" placeholder="输入查询条件" class="span2"/></td>
                        <td>网点名称:</td><td><input name="websiteName" placeholder="输入查询条件" class="span2"/></td>
                        <td>单据状态:</td><td><input name="serviceTicket.serviceStatus" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/hzzt',panelHeight:'auto',editable:false "/></td>
                	</tr>
                    <tr>
                        <td>审核结果:</td><td><input name="serviceTicket.checkResult" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/shjg',panelHeight:'auto',editable:false "/></td>
                        <td>机审无效原因:</td><td><input name="serviceTicket.machineCheckFaultReason" placeholder="输入查询条件" class="span2"/></td>
                        <td>分部审核意见:</td><td><input name="serviceTicket.centerCheckComment" placeholder="输入查询条件" class="span2"/></td>
                        <td>总部审核意见:</td><td><input name="serviceTicket.headquartersCheckComment" placeholder="输入查询条件" class="span2"/></td>
                        <td>创建机构:</td><td><input name="serviceTicket.createOrganizationS" placeholder="输入查询条件" class="span2"/></td>
                    </tr>
                    <tr>
                        <td>机型分类:</td><td><input name="serviceProduct.machineType" placeholder="输入查询条件" class="span2"/></td>
                        <td>故障代码:</td><td><input name="invalidCode" placeholder="输入查询条件" class="span2"/></td>
                        <td>故障原因:</td><td><input name="invalidReason" placeholder="输入查询条件" class="span2"/></td>
                        <td>是否远程:</td><td><input name="isDistance" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/sf',panelHeight:'auto',editable:false "/></td>
                        <td>远程申请单号:</td><td><input name="serviceTicket.distanceApplyNum" placeholder="输入查询条件" class="span2"/></td>
                    </tr>
                    <tr>
                        <td>是否充氟:</td><td><input name="isChargeFluor" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/sf',panelHeight:'auto',editable:false "/></td>
                        <td>是否更换配件:</td><td><input name="serviceTicket.isReplaceParts" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/sf',panelHeight:'auto',editable:false "/></td>
                        <td>维修方式:</td><td><input name="serviceTicket.repairMode" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/wxfs',panelHeight:'auto',editable:false "/></td>
                        <td>配件编码:</td><td><input name="partsCode" placeholder="输入查询条件" class="span2"/></td>
                        <td>配件名称:</td><td><input name="partsName" placeholder="输入查询条件" class="span2"/></td>
                    </tr>
                    <tr>
                        <td>配件价格:</td><td><input name="partsPrice" placeholder="输入查询条件" class="span2"/></td>
                        <td>配件数量:</td><td><input name="partsNum" placeholder="输入查询条件" class="span2"/></td>
                        <td>旧配件编码:</td><td><input name="oldPartsCode" placeholder="输入查询条件" class="span2"/></td>
                        <td>旧配件名称:</td><td><input name="oldPartsName" placeholder="输入查询条件" class="span2"/></td>
                        <td>旧配件价格:</td><td><input name="oldPartsPrice" placeholder="输入查询条件" class="span2"/></td>
                    </tr>
                    <tr>
                        <td>回访结果:</td><td><input name="serviceTicket.recallResult" placeholder="输入查询条件" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/hfjg',panelHeight:'auto',editable:false "/></td>
                        <td></td><td></td>
                        <td></td><td></td>
                        <td></td><td></td>
                        <td></td><td align="center"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
		</div>
        <div data-options="region:'center',border:false">
            <table id="dataGrid"></table>
        </div>
    </div>
</body>
</html>