<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script language="javascript" type="text/javascript"
	src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		
		$('#importForm').form({
			url : '${ctx}/hxOrganization/importExcel',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(msg) {
				parent.$.messager.progress('close');
				$.messager.alert('提示:','失败'+$.parseJSON(msg).failure+'行');
				$.messager.alert('提示:','已存在'+$.parseJSON(msg).exists+'行');
				$.messager.alert('提示:','成功'+$.parseJSON(msg).success+'行');
			}
		});
	});
	
	function submitForm(){
		var f = $("#importForm");
		f.submit();
	}

	function goBack() {
		window.location.href = "${ctx}/hxOrganization/organizationView";
	}
	
	function downloadTemplate(){
		window.location.href = "${ctx}/hxOrganization/downloadTemplate";
	}

</script>
	<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
		上传导入文件
	</div>
	<form id="importForm" method="post" enctype="multipart/form-data" action="${ctx}/hxUser/importExcel">
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">导入类型</td>
					<td width="25%">机构信息</td>
					<td width="25%" align="center">Excel模版</td>
					<td width="25%"><a href="javascript:void(0);" onclick="downloadTemplate();">模版下载</a></td>
				</tr>
				<tr>
					<td width="25%" align="center">文件名</td>
					<td width="25%"><input id="fileUpload" name="file" type="file" class="" /></td>
				</tr>
			</table>
	</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
