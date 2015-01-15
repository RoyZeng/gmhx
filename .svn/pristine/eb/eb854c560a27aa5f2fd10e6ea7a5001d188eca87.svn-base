<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#limitOrgId').combobox('disable');   //不可用
	});

	function submitForm(){
		if(validateForm()){
			$.post("${ctx}/hxLimit/updateLimit",$('#form').serialize(),
					function(msg){
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('提示:','修改成功!');
							window.location.href="${ctx}/hxLimit/limitView";
						}
			});
		}
	}

	function goBack() {
		window.location.href = "${ctx}/hxLimit/limitView";
	}
	
	function validateForm(){
		var limitOrgId = $('#limitOrgId').combobox('getValues');
		if(limitOrgId==''||limitOrgId==null){
			alert("机构名称不能为空");
			return false;
		}
		var radios = document.getElementsByName("limitAddTagButton");
		var limitAddTag = "";
		for (var i = 0; i < radios.length; i++) {
			if (radios[i].checked == true) {
				limitAddTag = radios[i].value;
				$("#limitAddTag").val(limitAddTag);
				break;
			}
		}
		if (limitAddTag == null || limitAddTag == '') {
			alert("请选择增减标记");
			return false;
		}
		var limitCash = $("#limitCashChange").val();
		var reg = /^\d+$/;
		if(limitCash==''||limitCash==null){
			alert("现金额度不能为空");
			return false;
		}else{
			if(limitCash.length>10){
				alert("现金额度的长度不能大于10");
				return false;
			}else{
				if(!limitCash.match(reg)){
					alert("现金额度的格式不正确");
					return false;
				 }
			}
		}
		var limitCredit = $("#limitCreditChange").val();
		if(limitCredit==''||limitCredit==null){
			alert("信用额度不能为空");
			return false;
		}else{
			if(limitCredit.length>10){
				alert("信用额度的长度不能大于10");
				return false;
			}else{
				if(!limitCredit.match(reg)){
					alert("信用额度的格式不正确");
					return false;
				 }
			}
		}
		var limitDesc = $("#limitDesc").val();
		if(limitDesc.length>2000){
			alert("备注的长度不能大于2000");
			return false;
		}
		return true;
	}
</script>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				修改机构额度
			</div>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">机构名称<font color="red">*</font></td>
					<td width="25%"><input id="limitId" name="limitId" type="hidden" value="${limit.limitId}"><input id="limitOrgId" name="limitOrgId" type="text" placeholder="请输入机构名称" editable="false" class="easyui-combobox" class="span2" value="${limit.limitOrgId}" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"></td>
					<td width="25%" align="center">增减标记<font color="red">*</font></td>
					<td width="25%"><input id="limitAddTag" name="limitAddTag" type="hidden" /><input name="limitAddTagButton" type="radio" value="1" checked >增</input><input name="limitAddTagButton" type="radio" value="0">减</input></td>
				</tr>
				<tr>
					<td width="25%" align="center">现金额度<font color="red">*</font></td>
					<td width="25%"><input id="limitCashChange" name="limitCashChange" type="text" placeholder="请输入现金额度" value="0"></td>
					<td width="25%" align="center">信用额度<font color="red">*</font></td>
					<td width="25%"><input id="limitCreditChange" name="limitCreditChange" type="text" placeholder="请输入信用额度" value="0"></td>
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" colspan="3"><textarea id="limitDesc" name="limitDesc" rows="3" cols="52" placeholder="请输入备注" ></textarea></td>
				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'"  onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>