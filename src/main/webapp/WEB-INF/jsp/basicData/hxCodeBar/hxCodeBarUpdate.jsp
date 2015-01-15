<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		
		$('#compareType').combobox({
		    url:'${ctx}/hxCode/getCombobox/dzlx?value=${hxCodeBar.compareType}',
		    editable:false,
		    onChange: function(param){
		    	if(param == "01"){
		    		$("input[name=innerCode2]").val("");
		    		$('#innerModel1, #innerModel2, #outerModel').combo('setText', null);
		    		$("input[name=innerCode1],input[name=outerCode]").removeAttr("disabled").unbind("blur");
		    		$("input[name=innerCode2],input[name=innerModel1],input[name=innerModel2],input[name=outerModel]").attr("disabled","disabled");
		    	}
		    	if(param == "02"){
		    		$('#innerModel1, #innerModel2, #outerModel').combo('setText', null).combo('disable', true);
		    		$("input[name=innerCode1],input[name=outerCode],input[name=innerCode2]").removeAttr("disabled");
		    		$("input[name=innerCode1]").on("blur", function(){
		    			if($(this).val() != ""){
			    			$.post("${ctx}/hxCodeBar/validateInnerCode1",{
			    				code : $(this).val()
			    			},
							function(msg){
								if(msg == "failure"){
									parent.$.messager.alert('提示:','内机代码1不存在!');
									$("input[name=innerCode1]").val("");
								}
							});
		    			}
		    		});
		    		$("input[name=innerCode2]").on("blur", function(){
		    			if($(this).val() != ""){
			    			$.post("${ctx}/hxCodeBar/validateInnerCode2",{
			    				code : $(this).val()
			    			},
							function(msg){
								if(msg == "failure"){
									parent.$.messager.alert('提示:','内机代码2不存在!');
									$("input[name=innerCode2]").val("");
								}
							});
		    			}
		    		});
		    		$("input[name=outerCode]").on("blur", function(){
		    			if($(this).val() != ""){
			    			$.post("${ctx}/hxCodeBar/validateOuterCode",{
			    				code : $(this).val()
			    			},
							function(msg){
								if(msg == "failure"){
									parent.$.messager.alert('提示:','外机外码不存在!');
									$("input[name=outerCode").val("");
								}
							});
		    			}
		    		});
		    	}
		    	if(param == "03"){
		    		$("input[name=innerCode1],input[name=innerCode2],input[name=outerCode]").val("").attr("disabled","disabled");
		    		$('#innerModel1, #innerModel2, #outerModel').combo('enable', true);
		    	}
		    	if(param == undefined){
		    		$("input[name=innerCode1],input[name=innerCode2],input[name=outerCode]").val("");
		    		$('#innerModel1, #innerModel2, #outerModel').combo('setText', null);
		    	}
			}
		}); 
		
		$('#innerModel1').combobox({    
		    url:'${ctx}/hxCode/getCombobox/jx?value=${hxCodeBar.innerModel1}'
		});
		
		$('#innerModel2').combobox({    
		    url:'${ctx}/hxCode/getCombobox/jx?value=${hxCodeBar.innerModel2}'
		}); 
		
		$('#outerModel').combobox({    
		    url:'${ctx}/hxCode/getCombobox/jx?value=${hxCodeBar.outerModel}'
		}); 
		
		$('#wholeModel').combobox({    
		    url:'${ctx}/hxCode/getCombobox/jx?value=${hxCodeBar.wholeModel}'
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
			<td width="25%"><input name="innerCode1" value="${hxCodeBar.innerCode1}" type="text" placeholder="请输入内机代码1"></td>
		</tr>
		<tr>
			<td width="25%" align="center">内机代码2</td>
			<td width="25%"><input name="innerCode2" value="${hxCodeBar.innerCode2}" type="text" placeholder="请输入内机代码2"></td>
			<td width="25%" align="center">外机代码</td>
			<td width="25%"><input name="outerCode" value="${hxCodeBar.outerCode}" type="text" placeholder="请输入外机代码"></td>
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
			<td width="25%"><input name="codeBegin" value="${hxCodeBar.codeBegin}" type="text" placeholder="请输入代码起始位"></td>
			<td width="25%" align="center">品牌</td>
		    <td width="25%"><input class="easyui-combobox" name="brand"  class="span2" data-options="url:'${ctx}/hxCode/getCombobox/pp?value=${hxCodeBar.brand }'"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">备注</td>
			<td width="25%" colspan="3"><textarea name="comment" placeholder="请输入备注" rows="3" cols="52">${hxCodeBar.comment}</textarea></td>
		</tr>
	</table>
</form>