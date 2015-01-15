<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
		
	$(function() {
		document.getElementById('status').innerHTML=statusVal("${map.serviceStatus}");
		
		$('#progressGrid').datagrid({
			url : "${ctx}/progress/getProgress/${map.serviceId}",
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
			url : "${ctx}/repairReceipt/getRepairReceiptTroubles/${map.serviceId}",
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
			url : "${ctx}/repairReceipt/getRepairReceiptParts/${map.serviceId}",
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
			$('#progressGrid').datagrid('appendRow',{ serviceId:'${map.serviceId}'});
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
	

	
	function goBack(){
		window.location.href="${ctx}/historyTicketQuery/historyTicketQueryView";
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
		<td width="25%" align="center">客户姓名</td><td width="25%" ><label>${map.customerName}</label></td>
		<td width="25%" align="center">客户类别</td><td width="25%" >
			<input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/khlb?value=${map.customerType}', editable:false"/>
		</td>
	</tr>
	<tr>
		<td width="25%" align="center">性别</td><td width="25%" >
			<input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/xb?value=${map.customerSex}', editable:false"/>
		</td>
		<td width="25%" align="center">会员卡号</td><td width="25%" ><label>${map.memberNum }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">省份</td>
		<td width="25%" >
			<input value="${map.province}" disabled="disabled" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',panelHeight:'200', editable:true,required:true,mode:'remote'"/>
		</td>
		<td width="25%" align="center">区号</td><td width="25%" ><label>${map.areaCode }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">手机</td><td width="25%" ><label>${map.phone }</label></td>
		<td width="25%" align="center">固定电话</td><td width="25%" ><label>${map.telephone }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">E_MAIL</td><td width="25%" ><label>${map.eMail }</label></td>
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
		<td width="25%" align="center">机型</td><td width="25%" ><label>${map.machineType }</label></td>
		<td width="25%" align="center">国美代码</td><td width="25%" ><label>${map.gomeCode }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">品牌</td><td width="25%" ><label>${map.brand }</label></td>
		<td width="25%" align="center">机器条码(非空)</td><td width="25%" ><label>${map.machineCode}</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">内机条码1</td><td width="25%" ><label>${map.internalMachineCode1 }</label></td>
		<td width="25%" align="center">内机条码2</td><td width="25%" ><label>${map.internalMachineCode2 }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">外机条码</td><td width="25%" ><label>${map.externalMachineCode }</label></td>
		<td width="25%" align="center">提货单号</td><td width="25%" ><label>${map.deliveryOrderNum }</label></td>
	</tr>
	<tr>					
		<td width="25%" align="center">安装卡号</td><td width="25%" ><label>${map.installCardNum }</label></td>
		<td width="25%" align="center">单据序号</td><td width="25%" ><label>${map.ticketNum }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">发票号码</td><td width="25%" ><label>${map.invoiceNum }</label></td>
		<td width="25%" align="center">购买者姓名</td><td width="25%" ><label>${map.buyer }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">购机日期</td><td width="25%" ><label><fmt:formatDate value='${map.buyDate}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">安装日期</td><td width="25%" ><label><fmt:formatDate value='${map.installDate}' pattern='yyyy-MM-dd' /></label></td>
	</tr>
	<tr>
		<td width="25%" align="center">安装单位</td><td width="25%" ><label>${map.installUnit }</label></td>
		<td width="25%" align="center">销售分部</td>
		<td width="25%" >
			<input value="${map.saleCenter}" disabled="disabled" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getECCombobox',panelHeight:'100', editable:true"/>
		</td>
	</tr>
	<tr>
		<td width="25%" align="center">销售门店</td>
		<td width="25%" >
			<input value="${map.saleMarket}" disabled="disabled" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getStoreCombobox',panelHeight:'100', editable:true"/>
		</td>
		<td width="25%" align="center">销售价格</td><td width="25%" ><label>${map.salePrice }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">保修截止日期</td><td width="25%" ><label><fmt:formatDate value='${map.warranty}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">安装省份</td>
		<td width="25%" >
			<input value="${map.installProvince}" disabled="disabled" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',panelHeight:'200', editable:true,required:true,mode:'remote'"/>
		</td>
	</tr>
	<tr>
		<td width="25%" align="center">安装详细地址</td><td width="25%" ><label>${map.installDetailAddress }</label></td>
		<td width="25%" align="center">备注</td><td width="25%" ><label>${map.productNote }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">创建人</td><td width="25%" ><label>${map.createManP }</label></td>
		<td width="25%" align="center">创建时间</td><td width="25%" ><label><fmt:formatDate value='${map.createTimeP }' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
	</tr>
	<tr>
		<td width="25%" align="center">修改人</td><td width="25%" ><label>${map.alterManP}</label></td>
		<td width="25%" align="center">修改时间</td><td width="25%" ><label><fmt:formatDate value='${map.alterTimeP }' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
	</tr>
</table>
<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">服务单信息</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="25%" align="center">服务单号</td><td width="25%" ><label>${map.serviceId}</label></td>
		<td width="25%" align="center">金力单号</td><td width="25%" ><label>${map.jlServiceId}</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">服务类型</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/djlx?value=${map.serviceType}',editable:false"/></td>
		<td width="25%" align="center">联系人</td><td width="25%" ><label>${map.linkMan}</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">联系电话</td><td width="25%" ><label>${map.linkPhone}</label></td>
		<td width="25%" align="center">预约日期</td><td width="25%" ><label><fmt:formatDate value='${map.appointmentDate}' pattern='yyyy-MM-dd' /></label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">预约时间段</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/sjd?value=${map.appointmentQuantum}',editable:false"/></td>
		<td width="25%" align="center">紧急程度</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/jjd?value=${map.urgencyDegree}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">派工信息</td><td width="25%" ><label>${map.dispatchInfo}</label></td>
		<td width="25%" align="center">客户描述</td><td width="25%" ><label>${map.customerDescribe}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">报修日期</td><td width="25%" ><label><fmt:formatDate value='${map.informRepairDate}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">维修方式</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/wxfs?value=${map.repairMode}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">远程申请单号</td><td width="25%" ><label>${map.distanceApplyNum}</label></td>
		<td width="25%" align="center">服务方式</td><td width="25%" ><label>${map.servicePattern}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">服务证号</td><td width="25%" ><label>${map.serviceCardNum}</label></td>
		<td width="25%" align="center">服务工</td><td width="25%" ><label>${map.serviceMan}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">服务日期</td><td width="25%" ><label><fmt:formatDate value='${map.serviceDate}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">交通费</td><td width="25%" ><label>${map.trafficFee}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">延管费</td><td width="25%" ><label>${map.delayFee}</label></td>
		<td width="25%" align="center">其它费</td><td width="25%" ><label>${map.otherFee}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">保修政策</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/bxzc?value=${map.warrantyPolicy}',editable:false"/></td>
		<td width="25%" align="center">是否更换配件</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${map.isReplaceParts}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">手工单号</td><td width="25%" ><label>${map.manulNum}</label></td>
		<td width="25%" align="center">是否二次安装</td>
		<td width="25%"><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${map.isDoubleInstall}',editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">预约安装日期</td><td width="25%" ><label><fmt:formatDate value='${map.appointmentInstallDate}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">移机地址</td><td width="25%" ><label>${map.moveAddress}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">录单时间</td><td width="25%" ><label><fmt:formatDate value='${map.inputTime}' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
		<td width="25%" align="center">机审无效原因</td><td width="25%" ><label>${map.machineCheckFaultReason}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">审核交通费</td><td width="25%" ><label>${map.centerCheckTrafficFee}</label></td>
		<td width="25%" align="center">审核延管费</td><td width="25%" ><label>${map.centerCheckDelayFee}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">审核其它费</td><td width="25%" ><label>${map.centerCheckOtherFee}</label></td>
		<td width="25%" align="center">审核人</td><td width="25%" ><label>${map.centerCheckMan}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">审核时间</td><td width="25%" ><label><fmt:formatDate value='${map.centerCheckTime}' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
		<td width="25%" align="center">审核意见</td><td width="25%" ><label>${map.centerCheckComment}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">审核结果</td><td width="25%" ><label>${map.checkResult}</label></td>
		<td width="25%" align="center">服务单状态</td>
		<td width="25%"><label id="status"></label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">分部</td>
		<td width="25%" ><input class="easyui-combobox combobox-f combo-f" disabled="disabled" data-options="url:'/gmhx/hxCode/getCombobox/jg?value=${map.center}'" style="display: none;"></td>
		<td width="25%" align="center">网点</td><td width="25%" ><label>${map.website}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">备注</td><td width="25%" ><label>${map.commentS}</label></td>
		<td width="25%" align="center">创建人</td><td width="25%" ><label>${map.createManS}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">创建机构</td><td width="25%" ><label>${map.createOrganizationS}</label></td>
		<td width="25%" align="center">创建时间</td><td width="25%" ><label><fmt:formatDate value='${map.createTimeS}' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">修改人</td><td width="25%" ><label>${map.alterManS}</label></td>
		<td width="25%" align="center">修改机构</td><td width="25%" ><label>${map.alterOrganizationS}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">修改时间</td><td width="25%" ><label><fmt:formatDate value='${map.alterTimeS}' pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
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

