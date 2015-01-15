<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
 <script type="text/javascript"> 
 	$(function() {
 		parent.$.messager.progress('close');
 		$('#form').form({
 			url : '${ctx}/brand/addBrand',
			onSubmit : function(param) { 				//combobox取值
 			   var brand = $("#brand").combobox('getValues');
 				param.brand = brand;
 				//combobox取值
 				var gm_code = $('#gm_code').combobox('getValues');
 				param.gm_code = gm_code;
				param.note = $("#note").val();
 				parent.$.messager.progress({
 					title : '提示',
					text : '数据处理中，请稍后....'
 				});
 				var isValid = $(this).form('validate');
 				if (!isValid) {
 					parent.$.messager.progress('close');
 				}
				return isValid;
 			},
 			success : function(msg) {
 				parent.$.messager.progress('close');
 				if($.parseJSON(msg).flag == "success"){
					$.messager.alert('提示:','保存成功!');
					var continueToAddFlag = $("#continueToAddFlag").val();
 					if(continueToAddFlag=="1"){//如果是继续添加则会执行页面跳转
 						window.location.href="${ctx}/brand/addView";
					}
			} 	
 		}
 		});
 	});
	
	 	function submitForm(){
 		var f = $("#form");
 		f.submit();
 	}
	
 	function goBack(){
 		window.location.href="${ctx}/brand/brandView";
 	}
 	function continueToAdd(){
		//继续添加标志位置1
 		$("#continueToAddFlag").val("1");
		var f = $("#form");
 		f.submit();
 	}
 </script> 
 <div class="easyui-panel" title="新建品牌厂家信息" style="width:auto"> 
 		<div style="padding:10px 0 10px 60px"> 
 		<input type="hidden" id="continueToAddFlag" title="继续添加标志位" value="0"> 
 		<form id="form" method="post"> 
 			<table class="table table-hover table-condensed" width="100%;"> 
			    <tr> 
 					<td>品牌</td> 
 					<td><input id="brand" type="text" placeholder="请选择品牌" editable="false" class="easyui-combobox" class="span2"  data-options="url:'${ctx}/hxCode/getCombobox/pp?value=${brand.brand}'"  /></td> 
 			    </tr> 
 		     	<tr>		 
 					<td>国美代码</td>
 					<td><input id="gm_code" type="text" placeholder="请请选择品牌国美代码" editable="false" class="easyui-combobox" class="span2"  data-options="url:'${ctx}/hxCode/getCombobox/gmdm?value=${brand.gm_code}'"  /></td> 
 				</tr> 
 
 				<tr> 
					<td>备注</td> 
 					<td><textarea id="note" cols="80" rows="1" placeholder="请输入备注信息" class="easyui-validatebox span2" data-options="required:false" ></textarea></td> 
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
 