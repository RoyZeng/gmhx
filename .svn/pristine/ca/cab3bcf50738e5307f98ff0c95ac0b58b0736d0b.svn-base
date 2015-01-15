<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
		
	$(function() {
		document.getElementById('status').innerHTML=statusVal("${map.service_status}");
		
		$('#progressGrid').datagrid({
			url : "${ctx}/progress/getProgress/${map.service_id}",
			title : "服务进度",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [
			   {field : 'serviceId',title : '服务单号',align:'center',width : 30 ,hidden:true
			}, {field : 'progressDescription',title : '进程描述',editor:'text',align:'center',width : 30
			}, {field : 'recorder',title : '记录人',align:'center',width : 30
			}, {field : 'recordTime',title : '记录时间',align:'center',width : 30
			} ] ],
			toolbar : '#toolbarForProgress',
			onLoadSuccess : function(data){
				indexProgress = data.total;
			}
		});
		
		$('#troubleGrid').datagrid({
			url : "${ctx}/repairReceipt/getRepairReceiptTroubles/${map.service_id}",
			title : "故障栏",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [ {field : 'troubleCode',title : '故障维修代码',align:'center',width : 30
			}, {field : 'repairContent',title : '维修内容',align:'center',width : 30
			}, {field : 'troubleReason',title : '故障原因',align:'center',width : 30
			}, {field : 'troubleDescription',title : '详细描述',align:'center',width : 30
			} ] ],
			toolbar : '#toolbarForTrouble'
		});
	
		$('#partsGrid').datagrid({
			url : "${ctx}/repairReceipt/getRepairReceiptParts/${map.service_id}",
			title : "配件栏",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [  {field : 'fitMachineType',title : '适用机型',align:'center',width : 30
			}, {field : 'partsCode',title : '配件编码',align:'center',width : 30
			}, {field : 'partsName',title : '配件名称',align:'center',width : 30
			}, {field : 'price',title : '配件价格',align:'center',width : 30
			}, {field : 'amount',title : '配件数量',align:'center',width : 30
			}, {field : 'oldPartsCode',title : '旧配件编码',align:'center',width : 30
			}, {field : 'oldPartsPrice',title : '旧配件价格 ',align:'center',width : 30
			} ] ],
			toolbar : '#toolbarForParts'
		});
		$("div.easyui-layout").css("height", "auto");
	});
	
	var indexProgress = 0;
	
	function addForProgress(){
		var count = $("#numberForProgress").val();
		for(var i = 0; i < count; i++){
			$('#progressGrid').datagrid('appendRow',{ serviceId:'${map.service_id}'});
			$('#progressGrid').datagrid('beginEdit', indexProgress);
			indexProgress ++;
		}
	}
	
	function deleteForProgress(){
		$('#progressGrid').datagrid("reload");	
		/* var checkedData = $('#progressGrid').datagrid('getRowNum');
		indexProgress = indexProgress - checkedData.length;
		$.each(checkedData, function(){
			$('#progressGrid').datagrid('deleteRow', checkedData.pop() - 1);
		}); */
	}
	
	function add(){
		$.messager.confirm("", "您确定要保存吗？", function (data) {
    		if(data){
    			$('#progressGrid').datagrid('acceptChanges');
    			var progress = $("#progressGrid").datagrid('getRows');
    			for(var i = progress.length - 1; i >= 0; i--){
    				if($.trim(progress[i].progressDescription) == "" || progress[i].recordTime!=null){
    					progress.splice(i, 1);
    				}
    			} 
    			var serviceTicketVO = {};
    			serviceTicketVO.serviceProgressInfos = progress;
    			$.ajax({
    	            type:"POST", 
    	            url:"${ctx}/serviceTicketCheck/addProgress", 
    	            dataType:"json",      
    	            contentType:"application/json",               
    	            data:JSON.stringify(serviceTicketVO), 
    	            success:function(data){
    	            	if(data.flag == "success"){
    	            		$.messager.alert('', '保存成功!',null,function(){
    	            			$('#progressGrid').datagrid("reload");
    	            		});
    	            	}else{
    	            		$.messager.alert('','新增失败!');
    	            		$('#progressGrid').datagrid("reload");
    	            	}
    	            } 
    	        });
    			
    		}
    	});
	}
	
	function update(){
		window.location.href="${ctx}/serviceTicketQuery/updateServiceTicketView/${map.service_id}";
	}
	
	function send(){
		window.location.href="${ctx}/serviceTicketQuery/serviceTicketSendView/${map.service_id}";
	}
	
	function goBack(){
		window.location.href="${ctx}/serviceTicketQuery/serviceTicketQueryView";
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

<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">客户信息</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="25%" align="center">客户姓名</td><td width="25%" ><label>${map.customer_name}</label></td>
		<td width="25%" align="center">客户类别</td><td width="25%" >
			<input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/khlb?value=${map.customer_type}', editable:false"/>
		</td>
	</tr>
	<tr>
		<td width="25%" align="center">性别</td><td width="25%" >
			<input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/xb?value=${map.customer_sex}', editable:false"/>
		</td>
		<td width="25%" align="center">会员卡号</td><td width="25%" ><label>${map.member_num }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">省份</td>
		<td width="25%" >
			<input value="${map.province}" disabled="disabled" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',panelHeight:'200', editable:true,required:true"/>
		</td>
		<td width="25%" align="center">区号</td><td width="25%" ><label>${map.area_code }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">手机</td><td width="25%" ><label>${map.phone }</label></td>
		<td width="25%" align="center">固定电话</td><td width="25%" ><label>${map.telephone }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">E_MAIL</td><td width="25%" ><label>${map.e_mail }</label></td>
		<td width="25%" align="center">邮编</td><td width="25%" ><label>${map.postcode }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">联系地址</td><td width="25%" ><label>${map.address }</label></td>
		<td width="25%" align="center">备注</td><td width="25%" ><label>${map.note }</label></td>
	</tr>
</table>
<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">产品信息</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="25%" align="center">机型</td><td width="25%" ><label>${map.machine_type }</label></td>
		<td width="25%" align="center">国美代码</td><td width="25%" >
			<input class="easyui-combobox" disabled="disabled" name="gomeCode" data-options="url:'${ctx}/hxCode/getCombobox/gmdm?value=${map.gome_code}', editable:false"/>
		</td>
	</tr>
	<tr>
		<td width="25%" align="center">品牌</td><td width="25%" >
			<input class="easyui-combobox" value="${map.brand}" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/pp', editable:false"/>
		</td>
		<td width="25%" align="center">机器条码(非空)</td><td width="25%" ><label>${map.machine_code}</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">内机条码1</td><td width="25%" ><label>${map.internal_machine_code1 }</label></td>
		<td width="25%" align="center">内机条码2</td><td width="25%" ><label>${map.internal_machine_code2 }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">外机条码</td><td width="25%" ><label>${map.external_machine_code }</label></td>
		<td width="25%" align="center">提货单号</td><td width="25%" ><label>${map.delivery_order_num }</label></td>
	</tr>
	<tr>					
		<td width="25%" align="center">安装卡号</td><td width="25%" ><label>${map.install_card_num }</label></td>
		<td width="25%" align="center">单据序号</td><td width="25%" ><label>${map.ticket_num }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">发票号码</td><td width="25%" ><label>${map.invoice_num }</label></td>
		<td width="25%" align="center">购买者姓名</td><td width="25%" ><label>${map.buyer }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">购机日期</td><td width="25%" ><label><fmt:formatDate value='${map.buy_date}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">安装日期</td><td width="25%" ><label><fmt:formatDate value='${map.install_date}' pattern='yyyy-MM-dd' /></label></td>
	</tr>
	<tr>
		<td width="25%" align="center">安装单位</td>
		<td width="25%" >
			<input type="text" disabled="disabled" value="${map.install_unit}" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getWebsiteCombobox?serviceUnit='">
		</td>
		<td width="25%" align="center">销售分部</td>
		<td width="25%" >
			<input value="${map.sale_center}" disabled="disabled" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getECCombobox',panelHeight:'100', editable:true"/>
		</td>
	</tr>
	<tr>
		<td width="25%" align="center">销售门店</td>
		<td width="25%" >
			<input value="${map.sale_market}" disabled="disabled" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getStoreCombobox',panelHeight:'100', editable:true"/>
		</td>
		<td width="25%" align="center">销售价格</td><td width="25%" ><label>${map.sale_price }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">保修截止日期</td><td width="25%" ><label><fmt:formatDate value='${map.warranty}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">安装省份</td>
		<td width="25%" >
			<input value="${map.install_province}" disabled="disabled" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',panelHeight:'200', editable:true,required:true"/>
		</td>
	</tr>
	<tr>
		<td width="25%" align="center">安装详细地址</td><td width="25%" ><label>${map.install_detail_address }</label></td>
		<td width="25%" align="center">备注</td><td width="25%" ><label>${map.product_note }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">创建人</td><td width="25%" ><label>${map.create_man_p }</label></td>
		<td width="25%" align="center">创建时间</td><td width="25%" ><label><fmt:formatDate value='${map.create_time_p }' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
	</tr>
	<tr>
		<td width="25%" align="center">修改人</td><td width="25%" ><label>${map.alter_man_p }</label></td>
		<td width="25%" align="center">修改时间</td><td width="25%" ><label><fmt:formatDate value='${map.alter_time_p }' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
	</tr>
</table>
<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">服务单信息</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="25%" align="center">服务单号</td><td width="25%" ><label>${map.service_id}</label></td>
		<td width="25%" align="center">金力单号</td><td width="25%" ><label>${map.jl_service_id}</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">服务类型</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/djlx?value=${map.service_type}',editable:false"/></td>
		<td width="25%" align="center">联系人</td><td width="25%" ><label>${map.link_man}</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">联系电话</td><td width="25%" ><label>${map.link_phone}</label></td>
		<td width="25%" align="center">预约日期</td><td width="25%" ><label><fmt:formatDate value='${map.appointment_date}' pattern='yyyy-MM-dd' /></label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">预约时间段</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/sjd?value=${map.appointment_quantum}',editable:false"/></td>
		<td width="25%" align="center">紧急程度</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/jjd?value=${map.urgency_degree}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">派工信息</td><td width="25%" ><label>${map.dispatch_info}</label></td>
		<td width="25%" align="center">客户描述</td><td width="25%" ><label>${map.customer_describe}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">报修日期</td><td width="25%" ><label><fmt:formatDate value='${map.inform_repair_date}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">维修方式</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/wxfs?value=${map.repair_mode}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">远程申请单号</td><td width="25%" ><label>${map.distance_apply_num}</label></td>
		<td width="25%" align="center">服务方式</td><td width="25%" ><label>${map.service_pattern}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">服务证号</td><td width="25%" ><label>${map.service_card_num}</label></td>
		<td width="25%" align="center">服务工</td><td width="25%" ><label>${map.service_man}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">服务日期</td><td width="25%" ><label><fmt:formatDate value='${map.service_date}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">交通费</td><td width="25%" ><label>${map.traffic_fee}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">延管费</td><td width="25%" ><label>${map.delay_fee}</label></td>
		<td width="25%" align="center">其它费</td><td width="25%" ><label>${map.other_fee}</label></td>
	</tr>
		<%-- <td width="25%" align="center">产品外观满意度</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.product_exterior_satisfaction}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">产品外观重要性</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.product_exterior_significance}',editable:false"/></td>
		<td width="25%" align="center">价格满意度</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.price_satisfaction}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">价格重要性</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.price_significance}',editable:false"/></td>
		<td width="25%" align="center">服务满意度</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.service_satisfaction}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">服务重要性</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.service_significance}',editable:false"/></td>
		<td width="25%" align="center">质量满意度</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.quality_satisfaction}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">质量重要性</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.quality_significance}',editable:false"/></td>
		<td width="25%" align="center">生理感受满意度</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.physiology_satisfaction}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">生理感受重要性</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.physiology_significance}',editable:false"/></td>
		<td width="25%" align="center">客户满意度</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.customer_satisfaction}',editable:false"/></td>
	</tr> --%>
	<tr>	
		<td width="25%" align="center">保修政策</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/bxzc?value=${map.warranty_policy}',editable:false"/></td>
		<td width="25%" align="center">是否更换配件</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${map.is_replace_parts}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">手工单号</td><td width="25%" ><label>${map.manul_num}</label></td>
		<td width="25%" align="center">是否二次安装</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${map.is_double_install}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">预约安装日期</td><td width="25%" ><label><fmt:formatDate value='${map.appointment_install_date}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">移机地址</td><td width="25%" ><label>${map.move_address}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">录单时间</td><td width="25%" ><label><fmt:formatDate value='${map.input_time}' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
		<td width="25%" align="center">机审无效原因</td><td width="25%" ><label>${map.machine_check_fault_reason}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">审核交通费</td><td width="25%" ><label>${map.center_check_traffic_fee}</label></td>
		<td width="25%" align="center">审核延管费</td><td width="25%" ><label>${map.center_check_delay_fee}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">审核其它费</td><td width="25%" ><label>${map.center_check_other_fee}</label></td>
		<td width="25%" align="center">审核人</td><td width="25%" ><label>${map.center_check_man}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">审核时间</td><td width="25%" ><label><fmt:formatDate value='${map.center_check_time}' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
		<td width="25%" align="center">审核意见</td><td width="25%" ><label>${map.center_check_comment}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">审核结果</td><td width="25%" ><label>${map.check_result}</label></td>
		<td width="25%" align="center">服务单状态</td>
		<td width="25%"><label id="status"></label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">分部</td>
		<td width="25%" ><input class="easyui-combobox combobox-f combo-f" value="${map.service_unit}" disabled="disabled" data-options="url:'${ctx}/hxCode/getServiceUnit'" style="display: none;"></td>
		<td width="25%" align="center">网点</td>
		<td width="25%" >
			<input type="text" disabled="disabled" value="${map.create_organization_s}" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getWebsiteCombobox?serviceUnit=',required:true">
		</td>
	</tr>
	<tr>	
		<td width="25%" align="center">备注</td><td width="25%" ><label>${map.comment_s}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">创建人</td><td width="25%" ><label>${map.create_man_s}</label></td>
		<td width="25%" align="center">创建机构</td><td width="25%" ><label>${map.create_organization_s}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">创建时间</td><td width="25%" ><label><fmt:formatDate value='${map.create_time_s}' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
		<td></td>
	</tr>
	<tr>	
		<td width="25%" align="center">修改人</td><td width="25%" ><label>${map.alter_man_s}</label></td>
		<td width="25%" align="center">修改机构</td><td width="25%" ><label>${map.alter_organization_s}</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">修改时间</td><td width="25%" ><label><fmt:formatDate value='${map.alter_time_s}' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
		<td></td>
	</tr>
</table>
<div id="tools" style="text-align:right;padding:5px">
	<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="goBack();">返回</a>
</div>
<div id="toolbarForProgress" style="display: none;">
	<input type="text" id="numberForProgress" hidden="true" style="width: 20px;" value="1">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addForProgress();">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteForProgress();">取消</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="add();">保存</a>
</div>
<div>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="progressGrid"></table>
	</div>
	<br/>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="troubleGrid"></table>
	</div>
	<br/>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="partsGrid"></table>
	</div>
</div>

