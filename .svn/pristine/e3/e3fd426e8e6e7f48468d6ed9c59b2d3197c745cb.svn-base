<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
	
	function submitForm(){
		if(validate()){
		$.post(
		"${ctx}/hxBarCodeRules/updateHxBarCodeRules/",
		$("#form").serialize(),
		function(returnObj){
			if(returnObj.flag="success"){
				$.messager.alert('提示:','修改成功!');
				window.location.href="${ctx}/hxBarCodeRules/hxBarCodeRulesView";
			}else{
				$.messager.alert('提示:','修改失败!');
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
	
	function goBack(){
			window.location.href="${ctx}/hxBarCodeRules/hxBarCodeRulesView";
	}
</script>
<div class="easyui-panel" title="修改配件库位" style="width:auto">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%;">
            <td><input name="rulesId" type="hidden" value="${hxBarCodeRules.rulesId}"></td>
				<tr>
					<td width="25%" align="center">国美代码</td>
					<td width="25%"><input class="easyui-combobox" name="gomeCode" placeholder="请输入国美代码" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/gmdm?value=${hxBarCodeRules.gomeCode}'"/></td>
					<td width="25%" align="center">品类</td>
			     	<td width="25%"><input class="easyui-combobox" name="category" placeholder="请输入品类" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/wxpl?value=${hxBarCodeRules.category}'"/></td>
				</tr>	
				<tr>
					<td width="25%" align="center">条码位数</td>
				    <td width="25%" ><input name="barCodeNumber" type="text" placeholder="请输入条码位数" class="easyui-validatebox span2"  value="${hxBarCodeRules.barCodeNumber}"></td>	
				</tr>
				<tr>
				   <td width="25%" align="center">内机位</td>
				   <td width="25%" ><input name="insideMachine" type="text" placeholder="请输入条码位数" class="easyui-validatebox span2"  value="${hxBarCodeRules.insideMachine}"></td>
				   <td width="25%" align="center">内机位内容</td>
				   <td width="25%" ><input name="insideMachineContent" type="text" placeholder="请输入内机位内容" class="easyui-validatebox span2"  value="${hxBarCodeRules.insideMachineContent}"></td>		
				</tr>
				<tr>
				   <td width="25%" align="center">内机位1</td>
				   <td width="25%" ><input name="insideMachineOne" type="text" placeholder="请输入内机位1" class="easyui-validatebox span2"  value="${hxBarCodeRules.insideMachineOne}"></td>
				   <td width="25%" align="center">内机位内容1</td>
				   <td width="25%" ><input name="insideMachineContentOne" type="text" placeholder="请输入内机位内容1" class="easyui-validatebox span2"  value="${hxBarCodeRules.insideMachineContentOne}"></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">内机位2</td>
				   <td width="25%" ><input name="insideMachineTwo" type="text" placeholder="请输入内机位2" class="easyui-validatebox span2"  value="${hxBarCodeRules.insideMachineTwo}"></td>
				   <td width="25%" align="center">内机位内容2</td>
				   <td width="25%" ><input name="insideMachineContentTwo" type="text" placeholder="请输入内机位内容2" class="easyui-validatebox span2"  value="${hxBarCodeRules.insideMachineContentTwo}"></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">外机位</td>
				   <td width="25%" ><input name="outsideMachine" type="text" placeholder="请输入外机位" class="easyui-validatebox span2"  value="${hxBarCodeRules.outsideMachine}"></td>
				   <td width="25%" align="center">外机位内容</td>
				   <td width="25%" ><input name="outsideMachineContent" type="text" placeholder="请输入外机位内容" class="easyui-validatebox span2"  value="${hxBarCodeRules.outsideMachineContent}"></td>		
				</tr>
				<tr>
				   <td width="25%" align="center">外机位1</td>
				   <td width="25%" ><input name="outsideMachineOne" type="text" placeholder="请输入外机位1" class="easyui-validatebox span2"  value="${hxBarCodeRules.outsideMachineOne}"></td>
				   <td width="25%" align="center">外机位内容1</td>
				   <td width="25%" ><input name="outsideMachineContentOne" type="text" placeholder="请输入外机位内容1" class="easyui-validatebox span2"  value="${hxBarCodeRules.outsideMachineContentOne}"></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">外机位2</td>
				   <td width="25%" ><input name="outsideMachineTwo" type="text" placeholder="请输入外机位2" class="easyui-validatebox span2"  value="${hxBarCodeRules.outsideMachineTwo}"></td>
				   <td width="25%" align="center">外机位内容2</td>
				   <td width="25%" ><input name="outsideMachineContentTwo" type="text" placeholder="请输入外机位内容2" class="easyui-validatebox span2"  value="${hxBarCodeRules.outsideMachineContentTwo}"></td>			
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" ><input name="note" type="text" placeholder="请输入备注" class="easyui-validatebox span2"  value="${hxBarCodeRules.note}"></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>