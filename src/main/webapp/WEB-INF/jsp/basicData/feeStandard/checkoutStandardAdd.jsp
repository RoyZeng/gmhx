<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
 <script type="text/javascript"> 
 	
 	function validate(param){
 		if($("#operationType").combobox('getValues')=="") {alert("请选择移/换机选项！");return false;}
 		if($('#classifyCode').combobox('getValues')=="") {alert("请选择产品分类");return false;}
 		if($("#wholefee").val()=="") {alert("请输入整机费用！");return false;}
 		if($("#innerfee").val()=="") {alert("请输入内机费用！");return false;}
 		if($("#outerfee").val()=="") {alert("请输入外机费用 ！");return false;}
 		return true;
 	};
	
	 function submitForm(){
 		if(validate()){
            $.post("${ctx}/standardFee/addCheckout",
			$('#form').serialize(),
			function(returnObj) {
				if (returnObj.flag = "success") {
					$.messager.alert('提示:', '保存成功!');
					var continueToAddFlag = $("#continueToAddFlag").val();
 					if(continueToAddFlag=="1"){//如果是继续添加则会执行页面跳转
 						window.location.href="${ctx}/standardFee/addView";
					}else{
						window.location.href="${ctx}/standardFee/moveChangeCheckoutView";
					}
				} else {
					$.messager.alert('提示:', '保存失败!');
				}
			});
 		}
 	};
	
 	function goBack(){
 		window.location.href="${ctx}/standardFee/moveChangeCheckoutView";
 	};
 	function continueToAdd(){
		//继续添加标志位置1
 		$("#continueToAddFlag").val("1");
 		submitForm();
 	};
 </script> 
 <div class="easyui-panel" title="新建移换机结算标准信息" style="width:auto"> 
 		<div style="padding:10px 0 10px 60px"> 
 		<input type="hidden" id="continueToAddFlag" title="继续添加标志位" value="0"> 
 		<form id="form" method="post"> 
 			<table class="table table-hover table-condensed" width="100%;"> 
			    <tr> 
 					<td width="15%">移/换机:</td>
					<td width="25%"><input name="operationType" id="operationType" type="text" placeholder="请选择移换机选项" editable="false" class="easyui-combobox" data-options="url:'${ctx}/standardFee/getCombobox/mcm?value=${checkout.operationType}',required:true"></td>
 			    	<td width="15%">产品分类:</td>
					<td width="25%"><input name="classifyCode" id="classifyCode" type="text" placeholder="请输入产品分类" editable="false" class="easyui-combobox" data-options="url:'${ctx}/standardFee/getCombobox/cpfl?value=${checkout.classifyCode}',required:true"></td>
 			    	<td width="20"></td>
 			    </tr> 
 		     	<tr>		 
 					<td width="10%">整机费用:</td>
			    	<td width="15%"><input name="wholefee" id="wholefee" type="text" placeholder="请输入整机费用" class="easyui-numberbox" precision="2" data-options="required:true"></td>
<!-- 					<td width="15%"></td> -->
                  	<td width="10%">内机费用:</td>
					<td width="15%"><input name="innerfee" id="innerfee" type="text" placeholder="请输入内机费用" class="easyui-numberbox" precision="2" data-options="required:true"></td> 
<!-- 					<td width="15"></td> -->
                    <td width="10%">外机费用:</td>
					<td width="15%"><input name="outerfee" id="outerfee" type="text" placeholder="请输入外机费用" class="easyui-numberbox" precision="2" data-options="required:true"></td>
          			<td width="25"></td>
          		</tr> 
 			</table> 
 		</form> 
 	</div> 
	</div> 
	<div style="text-align:right;padding:5px"> 
	    	<a href="#" class="easyui-linkbutton"  onclick="submitForm();">保存</a> 
 	    	<a href="#" class="easyui-linkbutton"  onclick="continueToAdd();">继续添加</a>
 	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div> 
 