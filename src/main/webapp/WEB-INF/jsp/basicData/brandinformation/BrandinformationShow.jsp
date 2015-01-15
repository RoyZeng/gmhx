<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">

function update(){
	var id = $("#brandId").val();
	window.location.href="${ctx}/brand/updateView/"+id;
}

function goBack(){
	window.location.href="${ctx}/brand/brandView";
}
</script>
<div class="easyui-panel" title="修改品牌厂家信息" style="width:auto">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<input id="gm_code" type="hidden" value="${hx_brand_information.gm_code}"/>
			<input id="brand" type="hidden" value="${hx_brand_information.brand}"/>
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td>品牌</td>
					<td><input id="brand" type="text"  readonly="false" placeholder="请选择品牌" editable="false" class="easyui-combobox" class="span2"  data-options="url:'${ctx}/hxCode/getCombobox/pp?value=${brand.brand}'"  ></td>  
					<td>国美代码</td>
					<td><input id="gm_code" type="text"  readonly="false" placeholder="请请选择品牌国美代码" editable="false" class="easyui-combobox" class="span2"  data-options="url:'${ctx}/hxCode/getCombobox/gmdm?value=${brand.gm_code}'"  ></td> 
				</tr>
				<tr>
					<td>备注</td>
 					<td colspan="3"><label>${brand.note}</label></td>	
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>创建人</td>
					<td><label>${brand.creater}</label></td>
					<td>创建时间</td>
					<td><label><fmt:formatDate value="${brand.rep_date}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
				<tr>
					<td>修改人</td>
					<td><label>${brand.modifier}</label></td>
					<td>修改时间</td>
					<td><label><fmt:formatDate value="${brand.mod_date}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
			</table>
		</form>
	</div>
<div style="text-align:right;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="update();">修改</a>
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>
<input id="brandId" type="hidden" value="${brand.id}"/>