<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<style type="text/css">td {white-space: nowrap;}</style>
<script type="text/javascript">
	var mode;
	var category;
	var type;
	var faultCode;
	
	$(function(){
		$(".kongtiao").show();
		$(".reshuiqi").hide();
		
		
		$('#troubleGrid').datagrid({
			//url : "${ctx}/repairReceipt/getRepairReceiptTroubles/${map.service_id}",
			title : "故障栏",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [ {
				width : 10 , checkbox : true
			}, {field : 'troubleCode',title : '故障维修代码<font color="red">*</font>',editor:'text',align:'center',width : 30 
			}, {field : 'repairContent',title : '维修内容',editor:'text',align:'center',width : 30
			}, {field : 'troubleReason',title : '故障原因',editor:'text',align:'center',width : 30
			}, {field : 'troubleDescription',title:'详细描述',editor:'text',editor:'text',align:'center',width : 30
			}, {field : 'action',title : '',align:'center',width : 10,
				formatter : function(value, row, index) {
					return '<a href="javascript:void(0);" onclick="fillGrid(\''+value+'\',\''+row+'\',\''+index+'\');">编辑</a>';
				}
			}
			] ],  
			toolbar : '#toolbarForTrouble'
		});
		
		$('#partsGrid').datagrid({
			//url : "${ctx}/repairReceipt/getRepairReceiptParts/${map.service_id}",
			title : "配件栏",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [ {
				width : 10 , checkbox : true
			}, {field : 'fitMachineType',title : '适用机型<font color="red">*</font>',editor:'text',align:'center',width : 30
			}, {field : 'partsCode',title : '配件编码<font color="red">*</font>',editor:'text',align:'center',width : 30
			}, {field : 'actionOne',title : '',align:'center',width : 5,
				formatter : function(value, row, index) {
					return '编辑';
				}
			}, {field : 'partsName',title : '配件名称',align:'center',width : 30,editor:'text'
			}, {field : 'price',title : '配件价格',align:'center',width : 30,editor:'text'
			}, {field : 'amount',title : '配件数量',editor:'text',align:'center',width : 30
			}, {field : 'oldPartsCode',title : '旧配件编码',editor:'text',align:'center',width : 30
			}, {field : 'actionTwo',title : '',align:'center',width : 5,
				formatter : function(value, row, index) {
					return '编辑';
				} 
			}, {field : 'oldPartsPrice',title : '旧配件价格 ',align:'center',width : 30,editor:'text'
			}] ],
			toolbar : '#toolbarForParts',
			onLoadSuccess : function(data){
				indexParts = data.total;
				for(var i = 0; i < indexParts; i++){
					$('#partsGrid').datagrid('beginEdit', i);
					$("tr[id=datagrid-row-r2-2-" + i + "] td[field=fitMachineType] input").attr("disabled",true);
					$("tr[id=datagrid-row-r2-2-" + i + "] td[field=partsCode] input").attr("disabled",true);
					$("tr[id=datagrid-row-r2-2-" + i + "] td[field=partsName] input").attr("disabled",true);
					$("tr[id=datagrid-row-r2-2-" + i + "] td[field=oldPartsCode] input").attr("disabled",true);
					$("tr[id=datagrid-row-r2-2-" + i + "] td[field=price] input").attr("disabled",true);
					$("tr[id=datagrid-row-r2-2-" + i + "] td[field=oldPartsPrice] input").attr("disabled",true);
				}
			},
			onClickCell : function(rowIndex, field, value){
				if(field.indexOf("action")==-1){
					return;
				}
				
				var machineType = $("#machineType").combobox('getValue');
				var faultCode = $("#faultCode").val();
				if(machineType==""){
					$.messager.alert('','请选择机型！');
					return;					
				}
				if(faultCode==""){
					$.messager.alert('','请添加故障信息！')
					return;					
				}
				
				parent.$.modalDialog({
	        		title : '选择故障',
					width : 400,
					height : 250,
					closable : false,
					href : "${ctx}/repairReceipt/selectFaultView?machineType="+machineType+"&faultCode="+faultCode+"&category="+category+"&brand="+$("#brand").combobox('getValue'),
					buttons : [ {
						text : '确定',
						handler : function() {
							var partCode = parent.$.modalDialog.handler.find('#partCode').val();
							var partName = parent.$.modalDialog.handler.find('#partName').val();
							var partPrice = parent.$.modalDialog.handler.find('#partPrice').val();
							if(field=="actionOne"){
								$('#partsGrid').datagrid('getEditor',{index:rowIndex,field:'partsCode'}).target.val(partCode);
								$('#partsGrid').datagrid('getEditor',{index:rowIndex,field:'partsName'}).target.val(partName);
								$('#partsGrid').datagrid('getEditor',{index:rowIndex,field:'price'}).target.val(partPrice);
							}else if(field=="actionTwo"){
								$('#partsGrid').datagrid('getEditor',{index:rowIndex,field:'oldPartsCode'}).target.val(partCode);
								$('#partsGrid').datagrid('getEditor',{index:rowIndex,field:'oldPartsPrice'}).target.val(partPrice);
							}
							parent.$.modalDialog.handler.dialog('close');
						}
					},{
						text : '取消',
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}						
					} ]
		 	});
			},
		});
		$("div.easyui-layout").css("height", "auto");
	});
	
	function fillGrid(value,row,index){
		parent.$.modalDialog({
			title : '选择故障',
			width : 400,
			height : 250,
			closable : false,
			href : "${ctx}/repairReceipt/selectTroubleView?type=",
			buttons : [ {
				text : '确定',
				handler : function() {
					var code = parent.$.modalDialog.handler.find('#faultCode').val();
					var name = parent.$.modalDialog.handler.find('#faultName').val();
					setTroubleData(index,code,name);
					if(code.length!=0) {
					    var faultCode = $("#faultCode").val();
					    if(faultCode==''||faultCode==null){
					    	faultCode = code;
					    }else{
					    	if(faultCode.indexOf(code)==-1){
					    		faultCode +=","+code; 
					    	}
					    }
					    $("#faultCode").val(faultCode);	
				 	}
					parent.$.modalDialog.handler.dialog('close');
				}
			}, {
				text : '取消',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
		return;
	}
	
	function setTroubleData(index,code,name){
		 $("tr[id=datagrid-row-r1-2-" + index + "] td[field=troubleCode] input").val(code).attr("disabled",true);
		 $("tr[id=datagrid-row-r1-2-" + index + "] td[field=repairContent] input").val(name).attr("disabled",true);
		 $("tr[id=datagrid-row-r1-2-" + index + "] td[field=troubleReason] input").val(name).attr("disabled",true);
	}
	
	var indexTrouble = 0;
	var indexParts = 0;
	function addForTrouble(){
		var count = $("#numberForTrouble").val();
		for(var i = 0; i < count; i++){
			$('#troubleGrid').datagrid('appendRow',{});
			$('#troubleGrid').datagrid('beginEdit', indexTrouble);
			$("tr[id=datagrid-row-r1-2-" + indexTrouble + "] td[field=troubleCode] input").attr("disabled",true);
			$("tr[id=datagrid-row-r1-2-" + indexTrouble + "] td[field=repairContent] input").attr("disabled",true);
			$("tr[id=datagrid-row-r1-2-" + indexTrouble + "] td[field=troubleReason] input").attr("disabled",true);
			indexTrouble ++;
		}
	}
	
	function deleteForTrouble(){
		var checkedData = $('#troubleGrid').datagrid('getRowNum');
		indexTrouble = indexTrouble - checkedData.length;
		$.each(checkedData, function(){
			$('#troubleGrid').datagrid('deleteRow', checkedData.pop() - 1);
		});
	}
	
	function addForParts(){
		var count = $("#numberForParts").val();
		if($("#machineType").combobox('getValue')==''){
			$.messager.alert('','请先选择机型!');
			return;
		}
		for(var i = 0; i < count; i++){
			$('#partsGrid').datagrid('appendRow',{});
			$('#partsGrid').datagrid('beginEdit', indexParts);
			$("tr[id=datagrid-row-r2-2-" + indexParts + "] td[field=fitMachineType] input").val($("#machineType").combobox('getValue')).attr("disabled",true);
			$("tr[id=datagrid-row-r2-2-" + indexParts + "] td[field=partsCode] input").attr("disabled",true)
			$("tr[id=datagrid-row-r2-2-" + indexParts + "] td[field=partsName] input").attr("disabled",true)
			$("tr[id=datagrid-row-r2-2-" + indexParts + "] td[field=price] input").attr("disabled",true)
			$("tr[id=datagrid-row-r2-2-" + indexParts + "] td[field=oldPartsCode] input").attr("disabled",true)
			$("tr[id=datagrid-row-r2-2-" + indexParts + "] td[field=oldPartsPrice] input").attr("disabled",true)
			indexParts ++;
		}
		$("td[field=fitMachineType] span.combo-arrow").css("display","none");
		$("td[field=fitMachineType] input.combo-text").css("width", $('td[field=fitMachineType] span.combo').css('width'));
	}
	
	function deleteForParts(){
		var checkedData = $('#partsGrid').datagrid('getRowNum');
		indexParts = indexParts - checkedData.length;
		$.each(checkedData, function(){
			$('#partsGrid').datagrid('deleteRow', checkedData.pop() - 1);
		});
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
	
	function validate(){
		var flag = true;
		/* if($("input[name=customerName]").val() == ""){alert("请输入客户姓名！");return false;};
		if($("input[name=province]").val() == ""){alert("请输入客户省份！");return false;};
		if($("input[name=address]").val() == ""){alert("请输入客户联系地址！");return false;};
		if($("input[name=machineType]").val() == ""){alert("请输入产品机型！");return false;};
		if($("input[name=internalMachineCode1]").val() == ""){alert("请输入产品内机条码1！");return false;};
		if($("input[name=externalMachineCode]").val() == ""){alert("请输入产品外机条码！");return false;};
		if($("input[name=deliveryOrderNum]").val() == ""){alert("请输入产品提货单号！");return false;};
		if($("input[name=invoiceNum]").val() == ""){alert("请输入产品发票号码！");return false;};
		if($("input[name=buyer]").val() == ""){alert("请输入产品购买者姓名！");return false;};
		if($("input[name=buyDate]").val() == ""){alert("请输入产品购机日期！");return false;};
		if($("input[name=installDate]").val() == ""){alert("请输入产品安装日期！");return false;};
		if($("input[name=installUnit]").val() == ""){alert("请输入产品 安装单位！");return false;};
		if($("input[name=saleMarket]").val() == ""){alert("请输入产品 销售分部！");return false;};
		if($("input[name=saleMarket]").val() == ""){alert("请输入产品销售门店！");return false;};
		if($("input[name=installProvince]").val() == ""){alert("请输入产品安装省份！");return false;};
		if($("input[name=installDetailAddress]").val() == ""){alert("请输入产品安装详细地址！");return false;};
		if($("input[name=informRepaireDate]").val() == ""){alert("请输入维修单报修日期！");return false;};
		if($("input[name=repairer]").val() == ""){alert("请输入维修单维修工！");return false;};
		if($("input[name=serviceDate]").val() == ""){alert("请输入维修单维修日期！");return false;}; */
		return flag;
	}
	
	function save(){
		if($("#formCustomer").form('validate')&&
				$("#formProduct").form('validate')&&
				$("#formTicket").form('validate')){
				$('#troubleGrid').datagrid('acceptChanges');
			$('#troubleGrid').datagrid('acceptChanges');
			var troubles = $("#troubleGrid").datagrid('getRows');
			for(var i = 0; i <= troubles.length - 1; i++){
				if($.trim(troubles[i].troubleCode) == ""){
					//projects.splice(i, 1);
					$.messager.alert('','请完善记录!');
					$('#troubleGrid').datagrid('beginEdit', i);
					$("tr[id=datagrid-row-r1-2-" + i + "] td[field=troubleCode] input").attr("disabled",true);
					$("tr[id=datagrid-row-r1-2-" + i + "] td[field=repairContent] input").attr("disabled",true);
					$("tr[id=datagrid-row-r1-2-" + i + "] td[field=troubleReason] input").attr("disabled",true);
					return;
				}
			}
			$('#partsGrid').datagrid('acceptChanges');
			var parts = $("#partsGrid").datagrid('getRows');
			for(var i = parts.length - 1; i >= 0; i--){
				if($.trim(parts[i].fitMachineType) == ""){
					//parts.splice(i, 1);
					$.messager.alert('','请完善记录!');
					beginEdit(i);
					return;
				}else if($.trim(parts[i].partsCode) == ""){
					$.messager.alert('','配件编码必须输入!');
					beginEdit(i);
					return;
				}else if($.trim(parts[i].oldPartsCode) == ""){
					$.messager.alert('','旧配件编码必须输入!');
					beginEdit(i);
					return;
				}else if($.trim(parts[i].amount) == ""){
					$.messager.alert('','配件数量必须输入!');
					beginEdit(i);
					return;
				}
			}
			var serviceTicketVO = {};
			serviceTicketVO.serviceCustomer =  $.serializeObject($('#formCustomer'));
			serviceTicketVO.serviceProduct = $.serializeObject($('#formProduct'));
			serviceTicketVO.serviceTicket = $.serializeObject($('#formTicket')); 
			serviceTicketVO.serviceTroubleInfos = troubles;
			serviceTicketVO.servicePartsInfos = parts;
			
			$.ajax({
	            type:"POST", 
	            url:"${ctx}/repairReceipt/saveRepairReceipt", 
	            dataType:"json",      
	            contentType:"application/json",               
	            data:JSON.stringify(serviceTicketVO), 
	            success:function(data){
	            	if(data.flag == "success"){
	            		$.messager.alert('', '保存成功!',null,function(){
	            			window.location.href = "${ctx}/repairReceipt/repairReceiptView/" + data.serviceId;  
	            		});
	            	}else{
	            		$.messager.alert('',data.message);
	            	}
	            } 
	        });
		}
	}
	
	function beginEdit(index){
		$('#partsGrid').datagrid('beginEdit',index);
		$("tr[id=datagrid-row-r2-2-" + index + "] td[field=fitMachineType] input").val($("#machineType").combobox('getValue')).attr("disabled",true);
		$("tr[id=datagrid-row-r2-2-" + index + "] td[field=partsCode] input").attr("disabled",true)
		$("tr[id=datagrid-row-r2-2-" + index + "] td[field=partsName] input").attr("disabled",true)
		$("tr[id=datagrid-row-r2-2-" + index + "] td[field=price] input").attr("disabled",true)
		$("tr[id=datagrid-row-r2-2-" + index + "] td[field=oldPartsCode] input").attr("disabled",true)
		$("tr[id=datagrid-row-r2-2-" + index + "] td[field=oldPartsPrice] input").attr("disabled",true)
	}
	
	function goBack() {
		window.location.href = "${ctx}/repairReceipt/addview";
	}
	
	function fillForm(rec){
		if(refresh()){
			for(var i = indexParts-1; i>=0 ; i--){
				$('#partsGrid').datagrid('deleteRow', i);
			}
			indexParts = 0;
			for(var i = indexTrouble-1; i>=0 ; i--){
				$('#troubleGrid').datagrid('deleteRow', i);
			}
			indexTrouble = 0;
			$("#gomeCode").combobox('setValue',rec.gomeCode); 
			$("#brand").combobox('setValue',rec.brand); 
		}else{
			$("#machineType").combobox('clear');
		}
	}
	
	function fillCombo(rec){
	    $("#saleMarket").combobox('reload','${ctx}/hxCode/getStoreCombobox?center=' + rec.value);
	    $("#saleMarket").combobox('clear');
	}
	
	function selectCategory(rec){
		category = rec.value;
		$("#machineType").combobox('reload',"${ctx}/hxCode/getModelCombobox?type="+type+"&category=" + category);
		
		$("#machineType").combobox('clear');
		$("#mode").combobox('clear');
		$("#gomeCode").combobox('clear');
		$("#brand").combobox('clear');
	}
	
	function selectMode(rec){
		mode = rec.value;
		if(mode=="01"){
			type="-Z";
		}else if(mode=="02"){
			type="-N";
		}else{
			type="-W";
		}
		$("#machineType").combobox('reload',"${ctx}/hxCode/getModelCombobox?type="+type+"&category=" + category);
		$("#machineType").combobox('clear');
		$("#gomeCode").combobox('clear');
		$("#brand").combobox('clear');
	}
	
	function refresh(){
		if(category=="R03"){
			if(mode=="01"){
				$(".kongtiao").show();
				$(".reshuiqi").hide();
				$("#machineCode").val("");
				$('#machineCode').validatebox({required: false});  
				$('#internalMachineCode1').validatebox({required: true});  
				$('#externalMachineCode').validatebox({required: true}); 
				return true;
			}else if(mode=="02"){
				$(".kongtiao").show();
				$(".reshuiqi").hide();
				$("#machineCode").val("");
				$('#machineCode').validatebox({required: false});  
				$('#internalMachineCode1').validatebox({required: true});  
				$('#externalMachineCode').validatebox({required: false}); 
				return true;
			}else if(mode=="03"){
				$(".kongtiao").show();
				$(".reshuiqi").hide();
				$("#machineCode").val("");
				$('#machineCode').validatebox({required: false});  
				$('#internalMachineCode1').validatebox({required: false});  
				$('#externalMachineCode').validatebox({required: true}); 
				return true;
			}else{
				$.messager.alert('',"请选择退换模式！");
				return false;
			}
		}else if(category!=""){
			if(mode=="01"){
				$(".kongtiao").hide();
				$(".reshuiqi").show();
				$("#internalMachineCode1").val("");
				$("#internalMachineCode2").val("");
				$("#externalMachineCode").val("");
				$('#internalMachineCode1').validatebox({required: false});  
				$('#externalMachineCode').validatebox({required: false});  
				$('#machineCode').validatebox({required: true}); 
				return true;
			}else if(mode!=""){
				$.messager.alert('',"非空调品类请选择整机模式！");
				return false;
			}else{
				$.messager.alert('',"请选择整机模式！");
				return false;
			}
		}else{
			$.messager.alert('',"请选择品类！");
			return false;
		}
	}

</script>
<input type="hidden" id="continueToAddFlag" title="继续添加标志位" value="0">
<input type="hidden" id="faultCode">
<form id="formCustomer" method="post">
	<input type="hidden" name="customerId" value="${customer.customerId}">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">客户信息</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td width="20%">客户姓名<font color="red">*</font></td>
			<td width="30%"><input name="customerName" type="text"   class="easyui-validatebox span2" value="${customer.customerName}" data-options="required:true"></td>
			<td width="20%">客户类别<font color="red">*</font></td>
			<td width="30%">
				<input class="easyui-combobox" name="customerType" data-options="url:'${ctx}/hxCode/getCombobox/khlb?value=${customer.customerType}',panelHeight:'auto',editable:false,required:true"/>
			</td>
		</tr>
		<tr>
			<td>性别</td>
			<td>
				<input name="customerSex" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/xb?value=${customer.customerSex}',panelHeight:'auto', editable:false"/>
			</td>
			<td>会员卡号</td>
			<td><input name="memberNum"  type="text"  class="easyui-validatebox span2" value="${customer.memberNum}"></td>
		</tr>
		<tr>
			<td>省份<font color="red">*</font></td>
			<td><input name="province" type="text" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',panelHeight:'200', editable:true,required:true, hasDownArrow:false,mode:'remote'" value="${customer.province}"></td>
			<td>区号</td>
			<td><input name="areaCode" type="text"  class="easyui-validatebox span2" value="${customer.areaCode}"></td>
		</tr>
		<tr>
			<td>手机</td>
			<td><input name="phone" type="text"   class="easyui-validatebox span2" value="${customer.phone }"></td>
			<td>固定电话</td>
			<td><input name="telephone" type="text"   class="easyui-validatebox span2" value="${customer.telephone }"></td>
		</tr>
		<tr>
			<td>E-MAIL</td>
			<td><input name="email" type="text" data-options="validType:'email'"  class="easyui-validatebox span2" value="${customer.email}"></td>
			<td>邮编</td>
			<td><input name="postcode" type="text"   class="easyui-validatebox span2" value="${customer.postcode}"></td>
		</tr>
		<tr>
			<td>联系地址<font color="red">*</font></td>
			<td colspan="3"><textarea name="address" rows="2"  cols="80" class="easyui-validatebox span2" data-options="required:true">${customer.address}</textarea></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan="3"><textarea name="note"  rows="2" cols="80" class="easyui-validatebox span2" >${customer.note}</textarea></td>
		</tr>
	</table> 
</form>
<form id="formProduct" method="post">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">产品信息</div>
	<input name="installNum" type="hidden" value=""/>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td  width="20%">品类<font color="red">*</font></td>
			<td  width="30%">
				<input class="easyui-combobox" name="category" data-options="url:'${ctx}/hxCode/getCategoryCombobox',onSelect:function(rec){selectCategory(rec)},panelHeight:'100', editable:true,required:true"/>
			</td>
			<td width="20%">模式</td>
			<td width="30%"><input id="mode" name="mode" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/thms',onSelect:function(rec){selectMode(rec)},panelHeight:'auto'"/></td>
		</tr>
		
		<tr>
			<td width="20%">机型<font color="red">*</font></td>
			<td width="30%"><input id="machineType" name="machineType" class="easyui-combobox" data-options="required:true,valueField:'value',textField:'text',onSelect: function(rec){fillForm(rec)},panelHeight:'auto'"/></td>
			<td width="20%">国美代码</td>
			<td width="30%"><input id="gomeCode" class="easyui-combobox" name="gomeCode" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/gmdm',panelHeight:'auto', editable:false" /></td>
		</tr>
		<tr>
			<td>品牌</td>
			<td><input id="brand" name="brand" class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/pp',panelHeight:'auto', editable:false"/></td>
			<td class="reshuiqi">条码<font color="red">*</font></td>
			<td class="reshuiqi"><input id="machineCode" name="machineCode" type="text" value="" class="easyui-validatebox span2"></td>
			<td class="kongtiao">内机条1</td>
			<td class="kongtiao"><input id="internalMachineCode1" name="internalMachineCode1" type="text" data-options="required:true" value="" class="easyui-validatebox span2"></td>
		</tr>
		<tr class="kongtiao">
			<td>内机条码2</td>
			<td><input id="internalMachineCode2" name="internalMachineCode2" type="text" value=""  class="easyui-validatebox span2"></td>
			<td>外机条码</td>
			<td><input id="externalMachineCode" name="externalMachineCode" type="text" value="" data-options="required:true" class="easyui-validatebox span2"></td>
		</tr>
		<tr>
			<td>提货单号<font color="red">*</font></td>
			<td><input name="deliveryOrderNum" type="text" data-options="required:true" value="" class="easyui-validatebox span2"></td>
			<td>安装卡号</td>
			<td><input name="installCardNum" type="text" value="" class="easyui-numberbox" class="easyui-validatebox span2"></td>
		</tr>
		<tr>
			<td>单据序号</td>
			<td><input name="ticketNum" type="text" value="" class="easyui-numberbox" class="easyui-validatebox span2"></td>
			<td>发票号码<font color="red">*</font></td>
			<td><input name="invoiceNum" type="text" data-options="required:true" value="" class="easyui-validatebox span2"></td>
		</tr>
		<tr>
			<td>购买者姓名<font color="red">*</font></td>
			<td><input name="buyer" type="text" data-options="required:true" value="" class="easyui-validatebox span2"></td>
			<td>购机日期<font color="red">*</font></td>
			<td>
				<input id="buyDate" name="buyDate" type="text" data-options="required:true" readonly="readonly" class="easyui-validatebox span2">
				<img onclick="WdatePicker({el:'buyDate'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
			</td>
		</tr>
		<tr>
			<td>安装日期<font color="red">*</font></td>
			<td>
				<input id="installDate" name="installDate" type="text" data-options="required:true" readonly="readonly"  class="easyui-validatebox span2">
				<img onclick="WdatePicker({el:'installDate'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
			</td>
			<td>安装单位<font color="red">*</font></td>
			<td><input name="installUnit" type="text" class="easyui-combobox" disabled="disabled" value="${user.companyId}" data-options="url:'${ctx}/hxCode/getWebsiteCombobox',required:true"></td>
		</tr>
		<tr>
			<td>销售分部<font color="red">*</font></td>
			<td><input id="saleCenter" name="saleCenter" type="text" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getECCombobox',required:true,onSelect:function(rec){fillCombo(rec)}"></td>
			<td>销售门店<font color="red">*</font></td>
			<td><input id="saleMarket" name="saleMarket" type="text" class="easyui-combobox" data-options="required:true"></td>
		</tr>
		<tr>
			<td>销售价格</td>
			<td><input name="salePrice" type="text" value=""  class="easyui-validatebox span2"></td>
			<td>安装省份<font color="red">*</font> </td>
			<td><input name="installProvince" type="text"  class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',panelHeight:'200', editable:true,required:true, hasDownArrow:false,mode:'remote'" ></td>
		</tr>
		<tr>
			<td>安装详细地址<font color="red">*</font></td>
			<td colspan="3"><textarea name="installDetailAddress" class="easyui-validatebox span2" data-options="required:true" rows="2" cols="80"></textarea></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan="3"><textarea name="productNote" rows="2" cols="80"></textarea></td>
		</tr>
	</table> 
</form>
<form id="formTicket" method="post">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">维修单信息</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			
			<td width="20%">报修日期<font color="red">*</font></td>
			<td width="30%"><input id="informRepairDate" name="informRepairDate" type="text" readonly="readonly"  data-options="required:true" class="easyui-validatebox span2">
				<img onclick="WdatePicker({el:'informRepairDate'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
		</tr>
		<tr>
			<td>维修方式<font color="red">*</font></td>
			<td>
				<input name="repairMode" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/wxfs',panelHeight:'auto',editable:false,required:true"/>
			</td>
			<td>远程申请单号</td>
			<td><input name="distanceApplyNum" type="text" class="easyui-validatebox span2"></td>
		</tr>
		<tr>
			<td>充氟申请单号</td>
			<td><input name="chargeFluorineNum" type="text" class="easyui-validatebox span2"></td>
			<td>服务证号</td>
			<td><input name="serviceCardNum" type="text" class="easyui-validatebox span2"></td>
		</tr>
		<tr>
			<td>维修工<font color="red">*</font></td>
			<td><input name="serviceMan" type="text" class="easyui-validatebox span2" data-options="required:true"></td>
			<td>客户满意度<font color="red">*</font></td>
			<td>
				<input name="customerSatisfaction" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/myd',panelHeight:'auto',editable:false,required:true"/>
			</td>
		</tr>
		<tr>
			<td>维修日期<font color="red">*</font></td>
			<td><input id="serviceDate" name="serviceDate" type="text" readonly="readonly"  data-options="required:true" class="easyui-validatebox span2">
				<img onclick="WdatePicker({el:'serviceDate'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			<td>保修政策<font color="red">*</font></td>
			<td>
				<input name="warrantyPolicy" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/bxzc',panelHeight:'auto', editable:false,required:true"/>
			</td>
		</tr>
		<tr>
			<td>是否更换配件<font color="red">*</font></td>
			<td>
				<input name="isReplaceParts" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/sf',panelHeight:'auto',editable:false,required:true"/>
			</td>
			<td>手工单号</td>
			<td><input name="manulNum" type="text" class="easyui-validatebox span2"></td>
		</tr>
		<%-- <tr>
			<td>服务单位<font color="red">*</font></td>
			<td width="25%"><input name="serviceUnit" type="text" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getServiceUnit?wd=${user.companyId}',required:true"></td>
		</tr> --%>
		<tr>
			<td>备注</td>
			<td colspan="3"><textarea name="commentS" rows="2" cols="80"></textarea></td>
		</tr>
	</table> 
</form>
<div style="text-align:right;padding:5px">
	   	<a href="#" class="easyui-linkbutton"  onclick="save();">保存</a>
	   	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
	<div class="easyui-layout" data-options="fit:false,border:false">
		<table id="troubleGrid"></table>
	</div>
	<br/>
	<div class="easyui-layout" data-options="fit:false,border:false">
		<table id="partsGrid"></table>
	</div>
<div id="toolbarForTrouble" style="display: none;">
	<input type="text" id="numberForTrouble" style="width: 20px;" value="1">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addForTrouble();">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteForTrouble();">删除</a>
</div>
<div id="toolbarForParts" style="display: none;">
	<input type="text" id="numberForParts" style="width: 20px;" value="1">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addForParts();">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteForParts();">删除</a>
</div>
<br/>
<br/>
<br/>
