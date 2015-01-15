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
			            $.post("${ctx}/hxBarCodeRules/addHxBarCodeRules",
						$('#form').serialize(),
						function(returnObj) {
							if (returnObj.flag = "success") {
								$.messager.alert('提示:', '保存成功!');
								var continueToAddFlag = $("#continueToAddFlag").val();
			 					if(continueToAddFlag=="1"){//如果是继续添加则会执行页面跳转
			 						window.location.href="${ctx}/hxBarCodeRules/addView";
								}else{
									window.location.href="${ctx}/hxBarCodeRules/hxBarCodeRulesView";
								}
							} else {
								$.messager.alert('提示:', '保存失败!');
							}
						});
		}
}
	
	function validate(){
		var flag = true;
		if($("input[name=gomeCode]").val() == ""){alert("请输入国美代码！");return false;};
		if($("input[name=category]").val() == ""){alert("请选择品类！");return false;};
	
		return flag;
	}
	function goBack() {
		window.location.href = "${ctx}/hxBarCodeRules/hxBarCodeRulesView";
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
					<td width="25%" align="center">国美代码</td>
					<td width="25%"><input class="easyui-combobox" name="gomeCode" placeholder="请输入国美代码" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/gmdm', required:true"/></td>
					<td width="25%" align="center">品类</td>
			     	<td width="25%"><input class="easyui-combobox" name="category" placeholder="请输入品类" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/wxpl', required:true"/></td>
				</tr>	
				<tr>
					<td width="25%" align="center">条码位数</td>
				    <td width="25%" ><input name="barCodeNumber" type="text" placeholder="请输入条码位数" class="easyui-validatebox span2"  ></td>	
				</tr>
				<tr>
				   <td width="25%" align="center">内机位</td>
				   <td width="25%" ><input name="insideMachine" type="text" placeholder="请输入条码位数" class="easyui-validatebox span2" ></td>
				   <td width="25%" align="center">内机位内容</td>
				   <td width="25%" ><input name="insideMachineContent" type="text" placeholder="请输入内机位内容" class="easyui-validatebox span2"  ></td>		
				</tr>
				<tr>
				   <td width="25%" align="center">内机位1</td>
				   <td width="25%" ><input name="insideMachineOne" type="text" placeholder="请输入内机位1" class="easyui-validatebox span2"  ></td>
				   <td width="25%" align="center">内机位内容1</td>
				   <td width="25%" ><input name="insideMachineContentOne" type="text" placeholder="请输入内机位内容1" class="easyui-validatebox span2"  ></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">内机位2</td>
				   <td width="25%" ><input name="insideMachineTwo" type="text" placeholder="请输入内机位2" class="easyui-validatebox span2"  ></td>
				   <td width="25%" align="center">内机位内容2</td>
				   <td width="25%" ><input name="insideMachineContentTwo" type="text" placeholder="请输入内机位内容2" class="easyui-validatebox span2"  ></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">外机位</td>
				   <td width="25%" ><input name="outsideMachine" type="text" placeholder="请输入外机位" class="easyui-validatebox span2"  ></td>
				   <td width="25%" align="center">外机位内容</td>
				   <td width="25%" ><input name="outsideMachineContent" type="text" placeholder="请输入外机位内容" class="easyui-validatebox span2"  ></td>		
				</tr>
				<tr>
				   <td width="25%" align="center">外机位1</td>
				   <td width="25%" ><input name="outsideMachineOne" type="text" placeholder="请输入外机位1" class="easyui-validatebox span2"  ></td>
				   <td width="25%" align="center">外机位内容1</td>
				   <td width="25%" ><input name="outsideMachineContentOne" type="text" placeholder="请输入外机位内容1" class="easyui-validatebox span2"  ></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">外机位2</td>
				   <td width="25%" ><input name="outsideMachineTwo" type="text" placeholder="请输入外机位2" class="easyui-validatebox span2"  ></td>
				   <td width="25%" align="center">外机位内容2</td>
				   <td width="25%" ><input name="outsideMachineContentTwo" type="text" placeholder="请输入外机位内容2" class="easyui-validatebox span2"  ></td>			
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" ><input name="note" type="text" placeholder="请输入备注" class="easyui-validatebox span2"  ></td>
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