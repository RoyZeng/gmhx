<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#compareType').combobox({    
		    url:'${ctx}/hxCode/getCombobox/dzlx?value=${hxCodeBar.compareType}',
		    disabled:true
		});
		
		$('#innerModel1').combobox({    
		    url:'${ctx}/hxCode/getCombobox/jx?value=${hxCodeBar.innerModel1}',
		    disabled:true
		});
		
		$('#innerModel2').combobox({    
		    url:'${ctx}/hxCode/getCombobox/jx?value=${hxCodeBar.innerModel2}',
		    disabled:true
		}); 
		
		$('#outerModel').combobox({    
		    url:'${ctx}/hxCode/getCombobox/jx?value=${hxCodeBar.outerModel}',
		    disabled:true
		}); 
		
		$('#wholeModel').combobox({    
		    url:'${ctx}/hxCode/getCombobox/jx?value=${hxCodeBar.wholeModel}',
		    disabled:true
		}); 
	});
</script>
<form id="form" method="post">
	<input type="hidden" name="id" value="${hxCodeBar.id}">
	<table class="table table-hover table-condensed" style="padding: 10px 20px 10px 20px;">
		<tr>
			<td width="25%" align="center">对照类型<font color="red">*</font></td>
			<td width="25%"><input id="compareType" name="compareType"/></td>
			<td width="25%" align="center">内机代码1</td>
			<td width="25%"><input name="innerCode1" type="text" disabled="disabled" placeholder="请输入内机代码1" value="${hxCodeBar.innerCode1}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">内机代码2</td>
			<td width="25%"><input name="innerCode2" type="text" disabled="disabled" placeholder="请输入内机代码2" value="${hxCodeBar.innerCode2}"></td>
			<td width="25%" align="center">外机代码</td>
			<td width="25%"><input name="outerCode" type="text" disabled="disabled" placeholder="请输入外机代码" value="${hxCodeBar.outerCode}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">内机机型1</td>
			<td width="25%"><input id="innerModel1" name="innerModel1"/></td>
			<td width="25%" align="center">内机机型2</td>
			<td width="25%"><input id="innerModel2" name="innerModel2"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">外机机型</td>
			<td width="25%"><input id="outerModel" name="outerModel"/></td>
			<td width="25%" align="center">整机机型<font color="red">*</font></td>
			<td width="25%"><input id="wholeModel" name="wholeModel"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">代码起始位<font color="red">*</font></td>
			<td width="25%"><input name="codeBegin" disabled="disabled" type="text" placeholder="请输入代码起始位" value="${hxCodeBar.codeBegin}"></td>
			<td width="25%" align="center">品牌</td>
		    <td width="25%"><input class="easyui-combobox" disabled="disabled" name="brand"  class="span2" data-options="url:'${ctx}/hxCode/getCombobox/pp?value=${hxCodeBar.brand }'"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">更新时间<font color="red">*</font></td>
			<td width="25%"><input name="updateTime" disabled="disabled" type="text" placeholder="请输入代码起始位" value="<fmt:formatDate value="${hxCodeBar.updateTime}" pattern='yyyy-MM-dd HH:mm:ss'/>"></td>
			<td width="25%" align="center">备注</td>
			<td width="25%"><textarea name="comment" disabled="disabled" placeholder="请输入备注">${hxCodeBar.comment}</textarea></td>
		</tr>
	</table>
</form>