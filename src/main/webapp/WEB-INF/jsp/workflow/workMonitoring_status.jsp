<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
function exportMP08Excel() {
	window.location.href = "${ctx}/hxWorkFlow/exportMP08Excel?listNumber=${order.listNumber }";
}
</script>
</head>
<body>
    <input type="hidden"  id="listNumber" value="${order.listNumber }">
	<jsp:include page="${processInstanceToView }"></jsp:include>
	<%@ include file="workProcessTour.jsp"%>
	<div style="text-align: center; padding: 5px">
		<a href="#" class="easyui-linkbutton"
			data-options="toggle:true,group:'g1',iconCls:'icon-back'"
			onclick="javascript:window.location.href = '${ctx}/${goback }'">返回</a>
	</div>

</body>
</html>
