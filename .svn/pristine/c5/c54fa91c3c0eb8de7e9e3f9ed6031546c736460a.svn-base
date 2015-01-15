<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	
	var category;
	var serviceUnit; 
	
	$(function() {
		parent.$.messager.progress('close');
		$(".kongtiao").show();
		$(".reshuiqi").hide();
		var category = "${map.category}";
		if(category.indexOf("R03")>-1){
			$(".kongtiao").show();
			$(".reshuiqi").hide();
			$("#machineCode").val("");
			$('#machineCode').validatebox({required: false});  
			$('#internalMachineCode1').validatebox({required: true});  
			$('#externalMachineCode').validatebox({required: true}); 
			
		}else{
			$(".kongtiao").hide();
			$(".reshuiqi").show();
			$("#internalMachineCode1").val("");
			$("#internalMachineCode2").val("");
			$("#externalMachineCode").val("");
			$('#internalMachineCode1').validatebox({required: false});  
			$('#externalMachineCode').validatebox({required: false});  
			$('#machineCode').validatebox({required: true});   
		}
	});
	
	function save(){
		if($("#form").form('validate')){
			$.post("${ctx}/installReceipt/updateInstallReceipt", 
					$("#form").serialize(),
					function(msg){
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('','修改成功!',null,function(){
								window.location.href="${ctx}/installReceipt/installReceiptView/${map.service_id}";
							});
						}else{
							$.messager.alert('',$.parseJSON(msg).message);
						}
					}
				); 
		}
	}
	
	function goBack(){
		window.location.href="${ctx}/installReceipt/installReceiptView";	
	}
	
 	function fillCombo(rec){
	    $("#saleMarket").combobox('reload','${ctx}/hxCode/getStoreCombobox?center=' + rec.value);
	    $("#saleMarket").combo({hasDownArrow:false});
	    $("#saleMarket").combobox('clear');
	} 
	function fillForm(rec){
		$("#gomeCode").combobox('setValue',rec.gomeCode); 
		$("#brand").combobox('setValue',rec.brand); 
		
		if(rec.brand.indexOf("空调")>-1){
			$(".kongtiao").show();
			$(".reshuiqi").hide();
			$("#machineCode").val("");
			$('#machineCode').validatebox({required: false});  
			$('#internalMachineCode1').validatebox({required: true});  
			$('#externalMachineCode').validatebox({required: true}); 
			
		}else if(rec.brand.indexOf("热水器")>-1){
			$(".kongtiao").hide();
			$(".reshuiqi").show();
			$("#internalMachineCode1").val("");
			$("#internalMachineCode2").val("");
			$("#externalMachineCode").val("");
			$('#internalMachineCode1').validatebox({required: false});  
			$('#externalMachineCode').validatebox({required: false});  
			$('#machineCode').validatebox({required: true});  
		}
	}
	
/* 	function fillForm(rec){
		$("#gomeCode").combobox('setValue',rec.gomeCode); 
		$("#brand").combobox('setValue',rec.brand); 
		
		if(rec.brand.indexOf("空调")>-1){
			$(".kongtiao").show();
			$(".reshuiqi").hide();
			$("#machineCode").val("");
			$('#machineCode').validatebox({required: false});  
			$('#internalMachineCode1').validatebox({required: true});  
			$('#externalMachineCode').validatebox({required: true}); 
			
		}else if(rec.brand.indexOf("热水器")>-1){
			$(".kongtiao").hide();
			$(".reshuiqi").show();
			$("#internalMachineCode1").val("");
			$("#internalMachineCode2").val("");
			$("#externalMachineCode").val("");
			$('#internalMachineCode1').validatebox({required: false});  
			$('#externalMachineCode').validatebox({required: false});  
			$('#machineCode').validatebox({required: true});  
		}
	} */
	
	function fillCombo(rec){
	    $("#saleMarket").combobox('reload','${ctx}/hxCode/getStoreCombobox?center=' + rec.value);
	    $("#saleMarket").combo({hasDownArrow:false});
	    $("#saleMarket").combobox('clear');
	} 
	
	function selectCategory(rec){
		category = rec.value;
		if(category=="R03"){
			$(".kongtiao").show();
			$(".reshuiqi").hide();
			$("#machineCode").val("");
			$('#machineCode').validatebox({required: false});  
			$('#internalMachineCode1').validatebox({required: true});  
			$('#externalMachineCode').validatebox({required: true}); 
		}else{
			$(".kongtiao").hide();
			$(".reshuiqi").show();
			$("#internalMachineCode1").val("");
			$("#internalMachineCode2").val("");
			$("#externalMachineCode").val("");
			$('#machineCode').validatebox({required: true});  
			$('#internalMachineCode1').validatebox({required: false});  
			$('#externalMachineCode').validatebox({required: false});
		}
		$("#machineType").combobox('clear');
		$("#gomeCode").combobox('clear');
		$("#brand").combobox('clear');
		$("#machineType").combobox('reload',"${ctx}/hxCode/getModelCombobox?type=-Z&category=" + category);
	}

</script>
<form id="form" method="post">
<input name="serviceCustomer.customerId" type="hidden" value="${map.customer_id }"/>
<input name="serviceProduct.productId" type="hidden" value="${map.product_id }"/>
<input name="serviceProduct.customerId" type="hidden" value="${map.customer_id }"/>
<input name="serviceTicket.serviceId" type="hidden" value="${map.service_id }"/>
<input name="serviceTicket.productId" type="hidden" value="${map.product_id }"/>
<input name="serviceTicket.customerId" type="hidden" value="${map.customer_id }"/>
<form id="form" method="post">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">客户信息</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td width="20%">客户姓名<font color="red">*</font></td>
			<td width="30%"><input name="serviceCustomer.customerName" type="text"   class="easyui-validatebox span2" value="${map.customer_name}" data-options="required:true"></td>
			<td width="20%">客户类别<font color="red">*</font></td>
			<td width="30%">
				<input class="easyui-combobox" name="serviceCustomer.customerType" data-options="url:'${ctx}/hxCode/getCombobox/khlb?value=${map.customer_type}',panelHeight:'auto', editable:false"/>
			</td>
		</tr>
		<tr>
			<td>性别</td>
			<td>
				<input name="serviceCustomer.customerSex" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/xb?value=${map.customer_sex}',panelHeight:'auto', editable:false"/>
			</td>
			<td>会员卡号</td>
			<td><input name="serviceCustomer.memberNum"  type="text"  class="easyui-validatebox span2" value="${map.member_num }"></td>
		</tr>
		<tr>
			<td>省份<font color="red">*</font></td>
			<td><input name="serviceCustomer.province" type="text" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',hasDownArrow:false,panelHeight:'200', editable:true,required:true, mode:'remote'" value="${map.province}"></td>
			<td>区号</td>
			<td><input name="serviceCustomer.areaCode" type="text"  class="easyui-validatebox span2" value="${map.area_code }"></td>
		</tr>
		<tr>
			<td>手机</td>
			<td><input name="serviceCustomer.phone" type="text"   class="easyui-validatebox span2" value="${map.phone }"></td>
			<td>固定电话</td>
			<td><input name="serviceCustomer.telephone" type="text"   class="easyui-validatebox span2" value="${map.telephone }"></td>
		</tr>
		<tr>
			<td>E-MAIL</td>
			<td><input name="serviceCustomer.email" type="text" data-options="validType:'email'"  class="easyui-validatebox span2" value="${map.e_mail }"></td>
			<td>邮编</td>
			<td><input name="serviceCustomer.postcode" type="text"   class="easyui-validatebox span2" value="${map.postcode }"></td>
		</tr>
		<tr>
			<td>联系地址<font color="red">*</font></td>
			<td colspan="3"><textarea name="serviceCustomer.address" rows="2"  cols="80" class="easyui-validatebox span2" data-options="required:true">${map.address }</textarea></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan="3"><textarea name="serviceCustomer.note"  rows="2" cols="80" class="easyui-validatebox span2" >${map.note }</textarea></td>
		</tr>
	</table> 
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">产品信息</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td  width="20%">品类<font color="red">*</font></td>
			<td  width="30%">
				<input class="easyui-combobox" value="${map.category }" name="serviceProduct.category" data-options="url:'${ctx}/hxCode/getCategoryCombobox',onSelect:function(rec){selectCategory(rec)},panelHeight:'100', editable:false,required:true"/>
			</td>
		</tr>
		
		<tr>
			<td width="20%">机型<font color="red">*</font></td>
			<td width="30%"><input id="machineType" name="serviceProduct.machineType" class="easyui-combobox" value="${map.machine_type}" data-options="required:true,valueField:'value',textField:'text',url:'${ctx}/hxCode/getModelCombobox?type=-Z',onSelect: function(rec){fillForm(rec)},panelHeight:'auto'"/></td>
			<td width="20%">国美代码</td>
			<td width="30%"><input id="gomeCode" class="easyui-combobox" name="serviceProduct.gomeCode" disabled="disabled" value="${map.gome_code}" data-options="url:'${ctx}/hxCode/getCombobox/gmdm',panelHeight:'auto', editable:false" /></td>
		</tr>
		<tr>
			<td>品牌</td>
			<td><input id="brand" name="serviceProduct.brand" class="easyui-combobox" value="${map.brand}" disabled="disabled" data-options="valueField:'value',textField:'value',url:'${ctx}/hxCode/getCombobox/pp',panelHeight:'auto', editable:false"/></td>
			<td class="reshuiqi">条码<font color="red">*</font></td>
			<td class="reshuiqi"><input id="machineCode" name="serviceProduct.machineCode" value="${map.machine_code }" type="text" data-options="required:true" value="" class="easyui-validatebox span2"></td>
			<td class="kongtiao">内机条1<font color="red">*</font></td>
			<td class="kongtiao"><input id="internalMachineCode1" value="${map.internal_machine_code1 }" name="serviceProduct.internalMachineCode1" type="text" data-options="required:true" value="" class="easyui-validatebox span2"></td>
		</tr>
		<tr class="kongtiao">
			<td>内机条码2</td>
			<td><input id="internalMachineCode2" name="serviceProduct.internalMachineCode2" value="${map.internal_machine_code2 }" type="text" value=""  class="easyui-validatebox span2"></td>
			<td>外机条码<font color="red">*</font></td>
			<td><input id="externalMachineCode" name="serviceProduct.externalMachineCode" value="${map.external_machine_code }" type="text" value="" data-options="required:true" class="easyui-validatebox span2"></td>
		</tr>
		<tr>
			<td>提货单号<font color="red">*</font></td>
			<td><input name="serviceProduct.deliveryOrderNum" type="text" data-options="required:true" value="${map.delivery_order_num }" class="easyui-validatebox span2"></td>
			<td>安装卡号</td>
			<td><input name="serviceProduct.installCardNum" type="text" value="${map.install_card_num }" class="easyui-numberbox" class="easyui-validatebox span2"></td>
		</tr>
		<tr>
			<td>单据序号</td>
			<td><input name="serviceProduct.ticketNum" type="text" value="${map.ticket_num }" class="easyui-numberbox" class="easyui-validatebox span2"></td>
			<td>发票号码<font color="red">*</font></td>
			<td><input name="serviceProduct.invoiceNum" type="text" data-options="required:true" value="${map.invoice_num }" class="easyui-validatebox span2"></td>
		</tr>
		<tr>
			<td>购买者姓名<font color="red">*</font></td>
			<td><input name="serviceProduct.buyer" type="text" data-options="required:true" value="${map.buyer }" class="easyui-validatebox span2"></td>
			<td>购机日期<font color="red">*</font></td>
			<td>
				<input id="buyDate" name="serviceProduct.buyDate" type="text" readonly="readonly"  data-options="required:true" value="<fmt:formatDate value='${map.buy_date }' pattern='yyyy-MM-dd'/>" class="easyui-validatebox span2">
				<img onclick="WdatePicker({el:'buyDate'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
			</td>
		</tr>
		<tr>
			<td>安装日期<font color="red">*</font></td>
			<td>
				<input id="installDate" name="serviceProduct.installDate" type="text" readonly="readonly"  data-options="required:true" value="<fmt:formatDate value='${map.install_date }' pattern='yyyy-MM-dd'/>" class="easyui-validatebox span2">
				<img onclick="WdatePicker({el:'installDate'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
			</td>
			<td>安装单位<font color="red">*</font></td>
			<td><input name="serviceProduct.installUnit" type="text" disabled="disabled" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getWebsiteCombobox',hasDownArrow:false,required:true" value="${map.install_unit }"></td>
		</tr>
		<tr>
			<td>销售分部<font color="red">*</font></td>
			<td><input name="serviceProduct.saleCenter" type="text" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getECCombobox',hasDownArrow:false,required:true,onSelect:function(rec){fillCombo(rec)}" value="${map.sale_center }"></td>
			<td>销售门店<font color="red">*</font></td>
			<td><input id="saleMarket" name="serviceProduct.saleMarket" type="text" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getStoreCombobox',hasDownArrow:false,required:true" value="${map.sale_market }"></td>
		</tr>
		<tr>
			<td>销售价格</td>
			<td><input name="serviceProduct.salePrice" type="text" value="${map.sale_price }"  class="easyui-validatebox span2"></td>
			<td>安装省份<font color="red">*</font> </td>
			<td><input name="serviceProduct.installProvince" type="text" data-options="url:'${ctx}/hxCode/getRegionCombobox',hasDownArrow:false,panelHeight:'200', editable:true,required:true,mode:'remote'" value="${map.install_province }"></td>
		</tr>
		<tr>
			<td>安装详细地址<font color="red">*</font></td>
			<td colspan="3"><textarea name="serviceProduct.installDetailAddress" data-options="required:true" rows="2" cols="80">${map.install_detail_address }</textarea></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan="3"><textarea name="serviceProduct.productNote" rows="2" cols="80">${map.product_note }</textarea></td>
		</tr>
	</table> 
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">安装单信息</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td>服务单位<font color="red">*</font></td>
			<td width="30%"><input name="serviceUnit" value="${map.service_unit }" type="text" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getServiceUnit?wd=${user.companyId}',required:true"></td>
			<td width="20%">远程申请单号</td>
			<td width="30%"><input name="serviceTicket.distanceApplyNum" type="text" value="${map.distance_apply_num }"  class="easyui-validatebox span2"></td>
		</tr>
		<tr>
			<td width="20%">服务证号</td>
			<td width="30%"><input name="serviceTicket.serviceCardNum" type="text" value="${map.service_card_num }"  class="easyui-validatebox span2"></td>
			<td>安装工<font color="red">*</font></td>
			<td><input name="serviceTicket.serviceMan" type="text"  data-options="required:true" value="${map.service_man }" class="easyui-validatebox span2"></td>
		</tr>
		<%-- <tr>
			<td>产品外观满意度<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.productExteriorSatisfaction" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.product_exterior_satisfaction}',panelHeight:'auto', editable:false,required:true"/>
			</td>
			<td>产品外观重要性<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.productExteriorSignificance" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.product_exterior_significance}',panelHeight:'auto', editable:false,required:true"/>
			</td>
		</tr>
		<tr>
			<td>产品质量满意度<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.qualitySatisfaction" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.quality_satisfaction}',panelHeight:'auto',editable:false,required:true ">
			</td>
			<td>产品质量重要性<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.qualitySignificance" class="easyui-combobox"  data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.quality_significance}',panelHeight:'auto',editable:false,required:true ">
			</td>
		</tr>
		<tr>
			<td>价格满意度<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.priceSatisfaction" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.price_satisfaction}',panelHeight:'auto', editable:false,required:true"/> 
			</td>
			<td>价格重要性<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.priceSignificance" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.price_significance}',panelHeight:'auto', editable:false,required:true"/>
			</td>
		</tr>
		<tr>
			<td>服务满意度<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.serviceSatisfaction" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.service_satisfaction}',panelHeight:'auto', editable:false,required:true"/>
			</td>
			<td>服务重要性<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.serviceSignificance" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.service_significance}',panelHeight:'auto', editable:false,required:true"/>	
			</td>
		</tr>
		<tr>
			<td>生理感受满意度<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.physiologySatisfaction" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.physiology_satisfaction}',panelHeight:'auto', editable:false,required:true"/>
			</td>
			<td>生理感受重要性<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.physiologySignificance" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.physiology_significance}',panelHeight:'auto', editable:false,required:true"/>	
			</td>
		</tr> --%>
		<tr>
			<td>是否二次安装<font color="red">*</font></td>
			<td>
				<input name="serviceTicket.isDoubleInstall" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${map.is_double_install}',panelHeight:'auto', editable:false,required:true"/>
			</td>
			<td>保修政策<font color="red">*</font></td>
			<td colspan="3">
				<input name="serviceTicket.warrantyPolicy" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/bxzc?value=${map.warranty_policy}',panelHeight:'auto', editable:false,required:true"/>
			</td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan="3"><textarea name="serviceTicket.commentS" rows="2" cols="80">${map.comment_s }</textarea></td>
		</tr>
	</table> 
</form>
<div style="text-align:right;padding:5px">
    	<a href="#" class="easyui-linkbutton"  onclick="save();">保存</a>
    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
</div>