<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});

	function submitForm() {
		if(validate()) {
			            $.post("${ctx}/hxPostage/addHxPostage",
						$('#form').serialize(),
						function(returnObj) {
							if (returnObj.flag = "success") {
								$.messager.alert('提示:', '保存成功!');
								var continueToAddFlag = $("#continueToAddFlag").val();
			 					if(continueToAddFlag=="1"){//如果是继续添加则会执行页面跳转
			 						window.location.href="${ctx}/hxPostage/addView";
								}else{
									window.location.href="${ctx}/hxPostage/hxPostageView";
								}
							} else {
								$.messager.alert('提示:', '保存失败!');
							}
						});
		}
}
	
	function validate(){
		var flag = true;
		if($("input[name=posSendUnit]").val() == ""){alert("请输入发件单位！");return false;};
		if($("input[name=posPayUnit]").val() == ""){alert("请输入邮寄付款单位！");return false;};
		if($("input[name=posNum]").val() == ""){alert("请输入邮寄单号！");return false;};		
		if($("input[name=posWay]").val() == ""){alert("请选择邮寄方式！");return false;};
		if($("input[name=posDate]").val() == ""){alert("请输入邮寄日期！");return false;};
		if($("input[name=posContent]").val() == ""){alert("请输入邮寄内容！");return false;};
		if($("input[name=posUnit]").val() == ""){alert("请输入邮寄单位！");return false;};
		if($("input[name=posMoney]").val() == ""){alert("请输入金额！");return false;};
		if($("input[name=posHandlers]").val() == ""){alert("请输入经手人！");return false;};
		if(($("input[name=posReceiptUnit]").val() == "")&&($("input[name=posRecipient]").val() == "")){
			alert('收件单位、收件人至少输入一个！');return false;};
		$.ajax({
			type : "post",  
            url : "${ctx}/hxPostage/validatePosNum/" + $("input[name=posNum]").val(),  
            async : false,
            cache: false,
            success : function(msg){
            	if(msg=="success"){
            		alert("此邮寄单号已经被录入,请重新检查单号!");
					flag = false;
            	}
            }
		});
		if(flag == false){
			return false;
		}
		return flag;
	}
	function goBack() {
		window.location.href = "${ctx}/hxPostage/hxPostageView";
	}

	function continueToAdd() {
		//继续添加标志位置1
		$("#continueToAddFlag").val("1");
		submitForm();
	}
	
	
</script>
<div class="easyui-panel" title="新建邮寄费用信息" style="width: auto">
	<div style="padding: 10px 0 10px 60px">
		<input type="hidden" id="continueToAddFlag" title="继续添加标志位" value="0">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
				    <td width="25%" align="center">发件单位</td>
					<td width="25%"><input name="posSendUnit" type="text" placeholder="请输入发件单位" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/jg',  required:true" ></td>
					<td width="25%" align="center">收件单位</td>
					<td width="25%"><input name="posReceiptUnit" class="easyui-validatebox " type="text" placeholder="请输入收件单位"  value=""></td>
				</tr>
				<tr>
					<td width="25%" align="center">收件人</td>
					<td width="25%"><input name="posRecipient" type="text" placeholder="请输入收件人" class="easyui-validatebox" value=""></td>
				</tr>
				<tr>
					<td width="25%" align="center">收件人地址</td>
					<td colspan="3"><textarea name="posAddress" placeholder="请输入收件人地址" rows="3" cols="50%"></textarea></td>
				</tr>
				<tr>
					<td width="25%" align="center">邮寄付款单位</td>
					<td width="25%"><input name="posPayUnit" type="text" placeholder="请输入邮寄付款单位" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/jg',  required:true"></td>
                   	<td width="25%" align="center">邮寄单号</td>
					<td width="25%"><input name="posNum" type="text" placeholder="请输入邮寄单号" class="easyui-validatebox" data-options="required:true" value=""></td>				
				</tr>
				<tr>
					<td width="25%" align="center">邮寄方式</td>
					<td width="25%"><input class="easyui-combobox" name="posWay" placeholder="请输入邮寄方式" data-options="url:'${ctx}/hxCode/getCombobox/yjfs', editable:false, required:true"  /></td>
					<td width="25%" align="center">邮寄日期</td>
					<td width="25%"><input name="posDate" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" data-options="required:true"  /></td>
				</tr>
				<tr>
					<td width="25%" align="center">邮寄内容</td>
					<td colspan="3"><textarea name="posContent" placeholder="请输入邮寄内容" data-options="required:true"  rows="3" cols="50%"></textarea></td>
				</tr>
				<tr>
					<td width="25%" align="center">邮寄单位</td>
					<td width="25%"><input name="posUnit" type="text" placeholder="请输入邮寄单位"class="easyui-validatebox " data-options="required:true"value=""></td>
					<td width="25%" align="center">重量</td>
					<td width="25%"><input name="posWeight" type="text" placeholder="请输入重量"class="easyui-validatebox " data-options="required:false"value=""></td>
				</tr>
				<tr>
					<td width="25%" align="center">金额</td>
					<td width="25%"><input name="posMoney" type="text" placeholder="请输入金额"class="easyui-validatebox " data-options="required:true"value=""></td>
					<td width="25%" align="center">经手人</td>
					<td width="25%"><input name="posHandlers" type="text" placeholder="请输入经手人"class="easyui-validatebox " data-options="required:true"value=""></td>
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" colspan="3"><textarea name="posNote" placeholder="请输入备注" rows="3" cols="50%"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align: center; padding: 5px">
		<a href="#" class="easyui-linkbutton" onclick="submitForm();">保存</a> 
		<a href="#" class="easyui-linkbutton" onclick="continueToAdd();">继续添加</a>
		<a href="#" class="easyui-linkbutton" onclick="goBack();">返回</a>
	</div>
</div>