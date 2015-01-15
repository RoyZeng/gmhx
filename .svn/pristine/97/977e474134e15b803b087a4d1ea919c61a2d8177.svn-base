<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">

	$(function(){
		$("#button1").hide();
		$("#button2").hide();
		if("${map.service_status}"==01){
			$("#button1").show();
		}else{
			$("#button2").show();
		}
	})
	
	function update(){
		window.location.href="${ctx}/serviceTicketCreate/updateServiceTicketView/${map.service_id}";
	}
	
	function send(){
		window.location.href="${ctx}/serviceTicketCreate/serviceTicketSendView/${map.service_id}";
	}
	
	function goBack(){
		window.location.href="${ctx}/serviceTicketCreate/serviceTicketCreateView";
	}
	
</script>
<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">客户信息</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="20%">客户姓名<font color="red">*</font></td>
		<td width="30%"><label>${map.customer_name }</label></td>
		<td width="20%">客户类别<font color="red">*</font></td>
		<td width="30%">
			<input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/khlb?value=${map.customer_type}',panelHeight:'auto', editable:false"/>
		</td>
	</tr>
	<tr>
		<td>性别</td>
		<td>
			<input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/xb?value=${map.customer_sex}',panelHeight:'auto', editable:false"/>
		</td>
		<td>会员卡号</td>
		<td><label>${map.member_num }</label></td>
	</tr>
	<tr>
		<td>省份<font color="red">*</font></td>
		<td><label>${map.province }</label></td>
		<td>区号</td>
		<td><label>${map.area_code }</label></td>
	</tr>
	<tr>
		<td>手机</td>
		<td><label>${map.phone }</label></td>
		<td>固定电话</td>
		<td><label>${map.telephone }</label></td>
	</tr>
	<tr>
		<td>E-MAIL</td>
		<td><label>${map.e_mail }</label></td>
		<td>邮编</td>
		<td><label>${map.postcode }</label></td>
	</tr>
	<tr>
		<td>联系地址<font color="red">*</font></td>
		<td colspan="3"><label>${map.address }</label></td>
	</tr>
	<tr>
		<td>备注</td>
		<td colspan="3"><label>${map.note }</label></td>
	</tr>
</table>
<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">服务单信息</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="20%">服务单号<font color="red">*</font></td>
		<td width="30%">
			<label>${map.service_id }</label>
		</td>
		<td width="20%">服务类型<font color="red">*</font></td>
		<td width="30%">
			<input class="easyui-combobox" disabled="disabled" value="${map.service_type }" data-options="url:'${ctx}/hxCode/getCombobox/djlx',panelHeight:'auto', editable:false"/>
		</td>
	</tr>
	<tr>
		<td>紧急度<font color="red">*</font></td>
		<td>
			<input class="easyui-combobox" disabled="disabled" value="${map.urgency_degree }" data-options="url:'${ctx}/hxCode/getCombobox/jjd',panelHeight:'auto', editable:false"/>
		</td>
		<td>预约时间段<font color="red">*</font></td>
		<td>
			<input class="easyui-combobox" disabled="disabled" value="${map.appointment_quantum }" data-options="url:'${ctx}/hxCode/getCombobox/sjd',panelHeight:'auto', editable:false"/>
		</td>
	</tr>
	<tr>
		<td>联系人</td>
		<td><label>${map.link_man }</label></td>
		<td>预约日期</td>
		<td><label><fmt:formatDate value='${map.appointment_date }' pattern='yyyy-MM-dd'/></label></td>
	</tr>
	<tr>
		<td>联系电话</td>
		<td colspan="3" ><label>${map.link_phone }</label></td>
	</tr>
	<tr>
		<td>客户描述</td>
		<td colspan="3"><label>${map.customer_describe }</label></td>
	</tr>
	<tr>
		<td>备注</td>
		<td colspan="3"><label>${map.comment_s }</label></td>
	</tr>
</table>
<div id=button1 type="hidden" style="text-align:right;padding:5px">
    <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="update();">修改</a>
    <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="send();">发送</a>
    <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="goBack();">返回</a>
</div>
<div id=button2 type="hidden" style="text-align:right;padding:5px">
    <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="goBack();">返回</a>
</div>


