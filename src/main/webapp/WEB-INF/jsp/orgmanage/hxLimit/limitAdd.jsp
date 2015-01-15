<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
	
	function submitForm(){
		if(validateForm()){
			$.post("${ctx}/hxLimit/addLimit",$('#form').serialize(),
					function(msg){
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('提示:','保存成功!');
							var continueToAddFlag = $("#continueToAddFlag").val();
							if(continueToAddFlag=="1"){//如果是继续添加则会执行页面跳转
								window.location.href="${ctx}/hxLimit/addView";
							}else{
								window.location.href="${ctx}/hxLimit/limitView";
							}
						}else{
							$.messager.alert('提示:','该网点额度已经存在');
						}
			});
		}
	}
	
	function goBack(){
		window.location.href="${ctx}/hxLimit/limitView";
	}
	
	function continueToAdd(){
		//继续添加标志位置1
		$("#continueToAddFlag").val("1");
		submitForm();
	}
	
	function validateForm(){
		var limitOrgId = $('#limitOrgId').combobox('getValues');
		if(limitOrgId==''||limitOrgId==null){
			alert("机构名称不能为空");
			return false;
		}
		var limitCashChange = $("#limitCashChange").val();
		var reg = /^\d+$/;

		if(limitCashChange==''||limitCashChange==null){
			alert("现金额度不能为空");
			return false;
		}else{
			if(limitCashChange.length>10){
				alert("现金额度的长度不能大于10");
				return false;
			}else{
				if(!limitCashChange.match(reg)){
					alert("现金额度的格式不正确");
					return false;
				 }
			}
		}
		var limitCreditChange = $("#limitCreditChange").val();
		if(limitCreditChange==''||limitCreditChange==null){
			alert("信用额度不能为空");
			return false;
		}else{
			if(limitCreditChange.length>10){
				alert("信用额度的长度不能大于10");
				return false;
			}else{
				if(!limitCreditChange.match(reg)){
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
				新建机构额度
			</div>
			<input type="hidden" id="continueToAddFlag" title="继续添加标志位" value="0">
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">机构名称<font color="red">*</font></td>
					<td width="25%" colspan="3"><input id="limitOrgId" name="limitOrgId" type="text" placeholder="请输入机构名称" class="easyui-combobox" class="span2" value="" data-options="url:'${ctx}/hxCode/getOrgCombobox',hasDownArrow:false"></td>
				</tr>
				<tr>
					<td width="25%" align="center">现金额度<font color="red">*</font></td>
					<td width="25%"><input id="limitCashChange" name="limitCashChange" type="text" placeholder="请输入现金额度"></td>
					<td width="25%" align="center">信用额度<font color="red">*</font></td>
					<td width="25%"><input id="limitCreditChange" name="limitCreditChange" type="text" placeholder="请输入信用额度"></td>
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" colspan="3"><textarea id="limitDesc" name="limitDesc" rows="3" cols="52" placeholder="请输入备注" ></textarea></td>
				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'"  onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-add'" onclick="continueToAdd();">继续添加</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>