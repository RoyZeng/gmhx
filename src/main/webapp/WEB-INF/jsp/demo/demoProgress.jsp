<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress({
			title : '系统提示',
			text : '正在努力开发中......'
		});
		setInterval(function(){callBack()},3000);//间隔3秒执行跳转
	});
	function callBack(){
		parent.$.messager.progress('close');
	}
</script>
