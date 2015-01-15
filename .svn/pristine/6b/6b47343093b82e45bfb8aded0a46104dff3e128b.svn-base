<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<c:choose>
		<c:when
			test="${processInstance_Status==null || processInstance_Status==''}">
       out.println("<script>window.alert('此状态的流程不在此操作,请您去其他页面操作!');window.history.go(-1);</script>");
       </c:when>
		<c:otherwise>
			<jsp:forward page="${processInstance_Status }"></jsp:forward>
		</c:otherwise>
	</c:choose>
</body>
</html>
