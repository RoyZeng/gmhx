<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp"%>
<%@ include file="inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<script type="text/javascript">
		$(function() {
			$.post("${ctx}/validateAuth",{},
			function(msg){
				if(msg == "true"){
					window.location.href = "${ctx}/index";
				}else{
					var positionType;
					var innerHtml='';
					var obj = $.parseJSON(msg);
					$.each(obj, function(){
						if($(this)[0].positionType == 1){
							positionType = "总部";
						}
						if($(this)[0].positionType == 2){
							positionType = "分部";
						}
						if($(this)[0].positionType == 3){
							positionType = "网点";
						}
						innerHtml += "<tr><td align='center'><a href='#' positionId='" + $(this)[0].positionId + "' orgId='" + $(this)[0].orgId + "' positionType='" + $(this)[0].positionType + "' onclick='login(this);'>" + positionType + "</a></td><td align='center'>" + $(this)[0].orgName + "</td><td align='center'>" + $(this)[0].positionName + "</td></tr>";
					});
					$("#posTable").append(innerHtml);
					$("#posTable").css("margin-top", obj.length==2?"30px":"23px");
					$("#posDiv").show();
					$("#posDiv").dialog({
					    modal:true,
					    closable:false,
					    draggable:false,
					    width:600,    
					    height:190,
					    buttons:[{
							text:'返回',
							iconCls:'icon-back',
							handler:function(){
								window.location.href = "${ctx}/login";
							}
						}]
					});
				}
			});
		});
		
		function login(obj){
			window.location.href = "${ctx}/fittingPosition?orgId=" + $(obj).attr("orgId") + "&positionId=" + $(obj).attr("positionId") + "&positionType=" + $(obj).attr("positionType");
		}
	</script>
	
	<div id="posDiv" title="请选择您要登录的物料身份" style="width:400px;height:200px;display:none;">
		<table id="posTable" style="width:100%;border-collapse:collapse;" border="1">
			<tr>
				<td align='center'>物料操作类型</td>
				<td align='center'>所属机构</td>
				<td align='center'>所属岗位</td>
			</tr>
		</table>
	</div>  
</html>