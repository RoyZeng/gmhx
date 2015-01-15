<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/resource.inc"%>
<%
	// 其他生成的动态页面共享ctx项目名称
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
$.ajaxSetup({//设置ajax同步请求,防止多次请求顺序错乱
  	async: false
  });

function reduceStock() {
	var u;
	if('${codekey}' == 'MP07'){
		u = "/hxMaterial/updateMaterialLostReduceStock";
	}else if('${codekey}' == 'MP08'){
		u = "/hxMaterial/updateMaterialLostReduceStock";
	}else if('${codekey}' == 'MP09'){
		u = "/hxMaterial/updateMaterialLostReduceStock";
	}else{
		u = "/hxMaterial/reduceStock";
	}
	$.post(ctx + u, {
		listNumber :  '${order.listNumber}',
	}, function(data) {
		data = $.parseJSON(data);
		if (data.flag ==  "success") {
			parent.$.messager.alert('确认出库成功', '成功', 'info',function(){
			 window.location.href="${ctx}/hxWorkFlow/list";
			})
		} else {
			parent.$.messager.alert('确认出库失败',decodeURI(data.info), 'error',  function(){
			 window.location.href="${ctx}/hxWorkFlow/list";
			})
		}
	});
}
function materialMove(){
	var u = "/hxMaterial/updateMaterialMove";//库存转换
	$.post(ctx + u, {
		listNumber :  '${order.listNumber}',
	}, function(data) {
		data = $.parseJSON(data);
		if (data.flag ==  "success") {
			parent.$.messager.alert('确认移库成功', '成功', 'info',function(){
			 window.location.href="${ctx}/hxWorkFlow/list";
			})
		} else {
			parent.$.messager.alert('确认移库失败',decodeURI(data.info), 'error',  function(){
			 window.location.href="${ctx}/hxWorkFlow/list";
			})
		}
	});
}

function deliveryS11() {
	$.post(ctx + "/hxMaterial/wdnthDelivery", {
		listNumber :  '${order.listNumber}',
	}, function(data) {
		data = $.parseJSON(data);
		if(data.flag == "success"){
			parent.$.messager.alert('确认发货成功','成功!','info',function(){
				 window.location.href="${ctx}/hxWorkFlow/list";
			})
		} else {
			parent.$.messager.alert('确认发货失败',decodeURI(data.info), 'error',  function(){
			 	window.location.href="${ctx}/hxWorkFlow/list";
			});
		}
	});
}
function deliveryS12() {
	$.post(ctx + "/hxMaterial/wdnthReceive", {
		listNumber :  '${order.listNumber}',
	}, function(data) {
		data = $.parseJSON(data);
		if(data.flag == "success"){
			parent.$.messager.alert('确认收货成功','成功!','info',function(){
				 window.location.href="${ctx}/hxWorkFlow/list";
			})
		} else {
			parent.$.messager.alert('确认收货失败', data.info, 'error', function(){
			 	window.location.href="${ctx}/hxWorkFlow/list";
			});
		}
	});
}
function deliveryS13() {
	$.post(ctx + "/hxMaterial/wdnthCheck", {
		listNumber :  '${order.listNumber}',
	}, function(data) {
		data = $.parseJSON(data);
		if(data.flag == "success"){
			parent.$.messager.alert('确认检测成功','成功!','info',function(){
				 window.location.href="${ctx}/hxWorkFlow/list";
			})
		} else {
			parent.$.messager.alert('确认检测失败', data.info, 'error', function(){
			 	window.location.href="${ctx}/hxWorkFlow/list";
			});
		}
	});
}
function deliveryS14() {
	$.post(ctx + "/hxMaterial/wdnthIn", {
		listNumber :  '${order.listNumber}',
	}, function(data) {
		data = $.parseJSON(data);
		if(data.flag == "success"){
			parent.$.messager.alert('确认入库成功','成功!','info',function(){
				 window.location.href="${ctx}/hxWorkFlow/list";
			})
		} else {
			parent.$.messager.alert('确认入库失败', data.info,'error', function(){
			 	window.location.href="${ctx}/hxWorkFlow/list";
			});
		}
	});
}
function goBack() {
	window.location.href = "${ctx}/hxWorkFlow/list";
}
</script>
</head>
<body>
	<jsp:include page="${processInstanceToView }"></jsp:include>
	<%@ include file="workProcessTour.jsp"%>
	<div style="text-align: center; padding: 5px">
		<a href="#" class="easyui-linkbutton"
			data-options="toggle:true,group:'g1',iconCls:'icon-save'"
			onclick="${function}">${button}</a> <a href="#"
			class="easyui-linkbutton"
			data-options="toggle:true,group:'g1',iconCls:'icon-back'"
			onclick="goBack();">返回</a>
	</div>
</body>
</html>
