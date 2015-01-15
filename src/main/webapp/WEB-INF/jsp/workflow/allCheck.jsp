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
	function submitForm(orderType) {
		var checkAgree = $("input[name='approveComment']:checked").val();
		var comment = $("#comment").val();
		//
		var additionalParams = {};
		if(orderType =="MP" && checkAgree!='0'){
			$('#dataGrid').datagrid('acceptChanges');
			var rows = $("#dataGrid").datagrid('getRows');
			for(var i = rows.length - 1; i >= 0; i--){
				delete rows[i]['spec'],
				delete rows[i]['fittingName'],
				delete rows[i]['receiveStock'],
				delete rows[i]['sendStock']
				if(rows[i].auditCount =="" || rows[i].auditCount == 0){
					for(var j = 0; j < rows.length; j++){
						$('#dataGrid').datagrid('beginEdit', j);
						var partsCode = $('#dataGrid').datagrid('getEditor', {index:j,field:'fittingCode'});
						var s = $("#dataGrid").datagrid('getRows')[j]['suitModel'];
			            var url = '${ctx}/hxMaterial/getFittingBySuit?suitModel=' + window.encodeURIComponent(s);
						$(partsCode.target).combobox('reload', window.encodeURI(url));
					}
					$.messager.alert('警告','申请数量有误, 请点击第'+(i+1)+' 行进行修改！','warning');
					return;
				}else if(rows[i].applyCount < rows[i].auditCount){
					for(var j = 0; j < rows.length; j++){
						$('#dataGrid').datagrid('beginEdit', j);
						var partsCode = $('#dataGrid').datagrid('getEditor', {index:j,field:'fittingCode'});
						var s = $("#dataGrid").datagrid('getRows')[j]['suitModel'];
			            var url = '${ctx}/hxMaterial/getFittingBySuit?suitModel=' + window.encodeURIComponent(s);
						$(partsCode.target).combobox('reload', window.encodeURI(url));
					}
					$.messager.alert('警告','第'+(i+1)+' 行审核数量不得大于申请数量！','warning');
					return;
				}
			}
			additionalParams.details = rows;
		}
		additionalParams.workEntityId = '${order.listNumber}',
		additionalParams.approveComment = comment,
		additionalParams.orderType = orderType,
		additionalParams.checkAgree = checkAgree,
		$.ajax({ 
            type:"POST", 
            url: "${ctx}/hxWorkFlow/updateJbpmWorkEntity",
            contentType:"application/json",        
            data:JSON.stringify(additionalParams), 
            success:function(data){
				$.messager.alert("操作提示", data, "info", function() {
					window.location.href = "${ctx}/hxWorkFlow/list";
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
	<c:choose>
		<c:when test="${codekey=='MP03' && order.status=='S3' && flag=='1' }">
		
				<jsp:include page="material/materialCheckView.jsp"></jsp:include>
				
		</c:when>
		<c:when test="${codekey=='MP02' && order.status=='S4' && flag=='1' }">
		
				<jsp:include page="material/materialCheckView.jsp"></jsp:include>
				
		</c:when>
		<c:when test="${codekey=='MP01' && order.status=='S4' && flag=='1' }">
		
				<jsp:include page="material/materialCheckView.jsp"></jsp:include>
				
		</c:when>
		<c:otherwise>
				<jsp:include page="${processInstanceToView }"></jsp:include>
		</c:otherwise>
	</c:choose>

	<%@ include file="workProcessTour.jsp"%>
	<jsp:include page="${processInstanceToCheck }"></jsp:include>
	<jsp:include page="${processInstanceToSave }"></jsp:include>
</body>
</html>
