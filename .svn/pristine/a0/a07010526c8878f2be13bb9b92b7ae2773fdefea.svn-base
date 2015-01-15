<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	
$(function() {
	parent.$.messager.progress('close');
});

function update(){
	window.location.href="${ctx}/Network/NetworkView/";
}

function goBack(){
		window.location.href="${ctx}/Network/NetworkView";
	
}

</script>
<div class="easyui-panel" title="详细网点资料信息" style="width:auto">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
			     	<td>网点名称</td>
					<td><label>${network.wdmc}</label></td>
					<td>上级机构</td>
					<td><label>${network.gsxx01}</label></td>
				</tr>
				<tr>
					<td>网点ID</td>
					<td><label>${network.id}</label></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="update();">修改</a>
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>