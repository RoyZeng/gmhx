<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/brand/updateBrand',
			onSubmit : function(param) {
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
					$.messager.alert('提示:','修改成功!');
					window.location.href="${ctx}/brand/brandView";
				}
			}
		});
		
		$('#id').combobox({
			url: "${ctx}/brand/getAllBrand",
			valueField:'id', 
			textField:'brand'
		}); 
		$('#id').combobox("setValue",'${brand.id}'); 
	});
	
	function submitForm(){
		var f = $("#form");
		f.submit();
	}
	
	function goBack(){
		window.location.href="${ctx}/brand/brandView";
	}
</script>
<div class="easyui-panel" title="新建品牌厂家信息" style="width:auto"> 
 		<div style="padding:10px 0 10px 60px"> 
 		<form id="form" method="post"> 
 			<input type="hidden" id="brand" name='brand' title="继续添加标志位" value="${brand.brand}"> 
 			<table class="table table-hover table-condensed" width="100%;"> 
			    <tr> 
 					<td>品牌</td> 
 					<td><input id="id"  name="id" type="text" placeholder="请选择品牌" editable="false" class="easyui-combobox" class="span2" ></td> 
 			    </tr> 
 		     	<tr>		 
 					<td>国美代码</td>
 					<td><input id="gm_code"  name="gm_code" type="text" placeholder="请请选择品牌国美代码" editable="false" class="easyui-combobox" class="span2"  data-options="url:'${ctx}/hxCode/getCombobox/gmdm?value=${brand.gm_code}'"  ></td> 
 				</tr> 
 				<tr>
 					<td>是否使用</td>
			 		<td><input id="isUse"  name="isUse" type="text"  editable="false" class="easyui-combobox" class="span2"  data-options="url:'${ctx}/hxCode/getCombobox/yxzt?value=${brand.isUse}'"  ></td> 
				</tr>
 			</table> 
 		</form> 
 	</div> 
	</div> 
	<div style="text-align:right;padding:5px"> 
	    	<a href="#" class="easyui-linkbutton"  onclick="submitForm();">保存</a> 
 	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div> 
 </div> 
