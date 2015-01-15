<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<input type="hidden" name="id" value="${hxProductDetail.id}">
			<table class="table table-hover table-condensed" style="padding: 10px 20px 10px 20px;">
				<tr>
					<td width="25%" align="center">产品分类</td>
					<td width="25%"><input class="easyui-combobox" disabled="disabled" name="classifyCode" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/cpfl?value=${hxProductDetail.classifyCode}'"/></td>
					<td width="25%" align="center">产品编码</td>
					<td width="25%"><input name="productCode" disabled="disabled" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value="${hxProductDetail.productCode}"></td>
				</tr>
				<tr>
				    <td width="25%" align="center">商品编码</td>
					<td width="25%"><input name="matnr" disabled="disabled" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value="${hxProductDetail.matnr}"></td>
					<td width="25%" align="center">产品名称</td>
					<td width="25%"><input name="productName" disabled="disabled" type="text" placeholder="请输入产品名称" class="easyui-validatebox span2" data-options="required:true" value="${hxProductDetail.productName}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">英文名称</td>
					<td width="25%"><input name="englishName" disabled="disabled" type="text" placeholder="请输入英文名称" class="easyui-validatebox span2" value="${hxProductDetail.englishName}"></td>
					<td width="25%" align="center">机型</td>
					<td width="25%"><input name="model" type="text" disabled="disabled" placeholder="请输入机型" class="easyui-validatebox span2" data-options="required:true" value="${hxProductDetail.model}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">机型类别</td>
					<td width="25%"><input class="easyui-combobox" disabled="disabled" name="modelClassify" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/jxlb?value=${hxProductDetail.modelClassify}'"/></td>
					<td width="25%" align="center">产品类型</td>
					<td width="25%"><input class="easyui-combobox" disabled="disabled" name="productModel" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/cplx?value=${hxProductDetail.productModel}'"/></td>
				</tr>
				<tr>
					<td width="25%" align="center">品牌</td>
		            <td width="25%"><input class="easyui-combobox" disabled="disabled" name="brand"  class="span2" data-options="url:'${ctx}/hxCode/getCombobox/pp?value=${hxProductDetail.brand}'"/></td>
					<td width="25%" align="center">国美代码</td>
					<td width="25%"><input class="easyui-combobox" disabled="disabled" name="gomeCode" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/gmdm?value=${hxProductDetail.gomeCode}'"/></td>
				</tr>
				<tr>
					<td width="25%" align="center">是否新机</td>
					<td width="25%"><input class="easyui-combobox" disabled="disabled" name="isNew" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${hxProductDetail.isNew}'"/></td>
					<td width="25%" align="center">是否惠民</td>
					<td width="25%"><input class="easyui-combobox" disabled="disabled" name="isPreferential" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${hxProductDetail.isPreferential}'"/></td>
				</tr>
				<tr>
					<td width="25%" align="center">安装费</td>
					<td width="25%"><input name="installationFee" disabled="disabled" type="text" placeholder="请输入安装费" class="easyui-validatebox span2" value="${hxProductDetail.installationFee}"></td>
					<td width="25%" align="center">产品规格</td>
					<td width="25%"><input name="spec" type="text" disabled="disabled" placeholder="请输入产品规格" class="easyui-validatebox span2" value="${hxProductDetail.spec}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">价格</td>
					<td width="25%"><input name="price" type="text" disabled="disabled" placeholder="请输入价格" class="easyui-validatebox span2" value="${hxProductDetail.price}"></td>
					<td width="25%" align="center">制冷量</td>
					<td width="25%"><input name="refrigeration" disabled="disabled" type="text" placeholder="请输入制冷量" class="easyui-validatebox span2" value="${hxProductDetail.refrigeration}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%"><textarea name="comment" disabled="disabled" placeholder="请输入备注">${hxProductDetail.comment}</textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>