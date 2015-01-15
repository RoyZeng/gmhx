<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		
		$('#class1').combobox({
			url: "${ctx}/eccGoods/getGoodClass?classification=1",
			valueField:'value', 
			textField:'text',
			onChange:function(record){  
				$('#class2').combobox({
					url: "${ctx}/eccGoods/getGoodClass?classification=2&upperCode="+$('#class1').combobox('getValue'),
					valueField:'value', 
					textField:'text',
					onChange:function(record){
			        	$('#class3').combobox({
							valueField:'value', 
							textField:'text',
							url: "${ctx}/eccGoods/getGoodClass?classification=3&upperCode="+$('#class2').combobox('getValue'),
							onChange:function(record){
					        	$('#classifyCode').combobox({
					        		url: "${ctx}/eccGoods/getGoodClass?classification=4&upperCode="+$('#class3').combobox('getValue'),
									valueField:'value', 
									textField:'text',
								});
					        	$("#classifyCode").combobox("clear");
				         	} 
						});
			        	$("#class3").combobox("clear");
			        	$("#classifyCode").combobox("clear");
		         	} 
				});
	        	$("#class2").combobox("clear");
	        	$("#class3").combobox("clear");
	        	$("#classifyCode").combobox("clear");
	     	} 
		}); 
		
	});
</script>
<form id="form" method="post">
	<input type="hidden" name="id" value="${hxProductDetail.id}">
	<table class="table table-hover table-condensed" style="padding: 10px 20px 10px 20px;">
	    <tr>
		    <td width="25%" align="center">一级分类</td>
			<td width="25%"><input id="class1" type="text"  class="easyui-combobox"/></td>
			<td width="25%" align="center">二级分类</td>
			<td width="25%"><input id="class2" type="text"  class="easyui-combobox"></td>
		</tr>
		<tr>
		    <td width="25%" align="center">三级分类</td>
			<td width="25%"><input id="class3" type="text"  class="easyui-combobox"></td>
			<td width="25%" align="center">产品分类</td>
			<td width="25%"><input class="easyui-combobox" name="classifyCode" id="classifyCode" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/cpfl?value=${hxProductDetail.classifyCode}'"/></td>
		<tr>
			<td width="25%" align="center">产品编码</td>
			<td width="25%"><input name="productCode" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value="${hxProductDetail.productCode}"></td>
			<td width="25%" align="center">商品编码</td>
			<td width="25%"><input name="matnr" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value="${hxProductDetail.matnr}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">产品名称</td>
			<td width="25%"><input name="productName" type="text" placeholder="请输入产品名称" class="easyui-validatebox span2" data-options="required:true" value="${hxProductDetail.productName}"></td>
			<td width="25%" align="center">英文名称</td>
			<td width="25%"><input name="englishName" type="text" placeholder="请输入英文名称" class="easyui-validatebox span2" value="${hxProductDetail.englishName}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">机型</td>
			<td width="25%"><input name="model" type="text" placeholder="请输入机型" class="easyui-validatebox span2" data-options="required:true" value="${hxProductDetail.model}"></td>
			<td width="25%" align="center">机型类别</td>
			<td width="25%"><input class="easyui-combobox" name="modelClassify" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/jxlb?value=${hxProductDetail.modelClassify}'"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">产品类型</td>
			<td width="25%"><input class="easyui-combobox" name="productModel" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/cplx?value=${hxProductDetail.productModel}'"/></td>
		    <td width="25%" align="center">品牌</td>
		    <td width="5%"><input class="easyui-combobox" name="brand"  class="span2" data-options="url:'${ctx}/hxCode/getCombobox/pp?value=${hxProductDetail.brand}'"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">国美代码</td>
			<td width="25%"><input class="easyui-combobox" name="gomeCode" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/gmdm?value=${hxProductDetail.gomeCode}'"/></td>
			<td width="25%" align="center">是否新机</td>
			<td width="25%"><input class="easyui-combobox" name="isNew" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${hxProductDetail.isNew}'"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">是否惠民</td>
			<td width="25%"><input class="easyui-combobox" name="isPreferential" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${hxProductDetail.isPreferential}'"/></td>
			<td width="25%" align="center">安装费</td>
			<td width="25%"><input name="installationFee" type="text" placeholder="请输入安装费" class="easyui-validatebox span2" value="${hxProductDetail.installationFee}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">产品规格</td>
			<td width="25%"><input name="spec" type="text" placeholder="请输入产品规格" class="easyui-validatebox span2" value="${hxProductDetail.spec}"></td>
			<td width="25%" align="center">价格</td>
			<td width="25%"><input name="price" type="text" placeholder="请输入价格" class="easyui-validatebox span2" value="${hxProductDetail.price}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">制冷量</td>
			<td width="25%"><input name="refrigeration" type="text" placeholder="请输入制冷量" class="easyui-validatebox span2" value="${hxProductDetail.refrigeration}"></td>
			<td width="25%" align="center">备注</td>
			<td width="25%"><textarea name="comment" placeholder="请输入备注">${hxProductDetail.comment}</textarea></td>
		</tr>
	</table>
</form>