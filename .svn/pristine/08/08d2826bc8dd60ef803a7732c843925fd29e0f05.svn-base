<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		$('#dataGridForSuit').datagrid({
			title : "适用机型配置",
			url : "${ctx}/hxFitting/getHxFittingModel/${hxFitting.fittingCode}",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [ {
				width : 10,
				checkbox : true
			},{
				field : 'suitModel',
				title : '适用机型',
				align:'center',
				editor:{
					type:'combobox',
					options:{
						url:'${ctx}/hxCode/getCombobox/jx',
						required:true
					}
				},
				width : 300
			}, {
				field : 'comment',
				title : '备注',
				align:'center',
				editor:'text',
				width : 300
			} ] ],
			toolbar : '#toolbarForSuit',
			onLoadSuccess : function(data){
				indexForSuit = data.total;
				for(var i = 0; i < indexForSuit; i++){
					$('#dataGridForSuit').datagrid('beginEdit', i);
				}
			}
		});
		
		
		$('#dataGridForFaultCode').datagrid({
			title : "故障配置",
			url : "${ctx}/hxFitting/getHxFittingFaultCode/${hxFitting.fittingCode}",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [ {
				width : 10,
				checkbox : true
			},{
				field : 'faultCode',
				title : '故障',
				align:'center',
				editor:{
					type:'combobox',
					options:{
						url:"${ctx}/hxCode/getFaultCodeCombobox",
						required:true
					}
				},
				width : 300
			}, {
				field : 'comment',
				title : '备注',
				align:'center',
				editor:'text',
				width : 300
			} ] ],
			toolbar : '#toolbarForFaultCode',
			onLoadSuccess : function(data){
				indexForFaultCode = data.total;
				for(var i = 0; i < indexForSuit; i++){
					$('#dataGridForFaultCode').datagrid('beginEdit', i);
				}
			}
		});
		
		$('#dataGridForProvider').datagrid({
			title : "供应商配置",
			url : "${ctx}/hxFitting/getHxFittingProvider/${hxFitting.fittingCode}",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [ {
				width : 10,
				checkbox : true
			},{
				field : 'provider',
				title : '供应商',
				align:'center',
				editor:'text',
				width : 300
			}, {
				field : 'comment',
				title : '备注',
				align:'center',
				editor:'text',
				width : 300
			} ] ],
			toolbar : '#toolbarForProvider',
			onLoadSuccess : function(data){
				indexForProvider = data.total;
				for(var i = 0; i < indexForSuit; i++){
					$('#dataGridForProvider').datagrid('beginEdit', i);
				}
			}
		});
		
		$("div.easyui-layout").css("height", "auto");
		
		$('#class1').combobox({
			url: "${ctx}/eccGoods/getGoodClass?classification=1",
			valueField:'value', 
			textField:'text',
			onChange:function(record){  
				$('#class2').combobox({
					url: "${ctx}/eccGoods/getGoodClass?classification=2&upperCode="+$('#class1').combobox('getValue'),
					valueField:'value', 
					textField:'text',
					onChange:function(record){
			        	$('#class3').combobox({
							valueField:'value', 
							textField:'text',
							url: "${ctx}/eccGoods/getGoodClass?classification=3&upperCode="+$('#class2').combobox('getValue'),
							onChange:function(record){
					        	$('#fittingType').combobox({
					        		url: "${ctx}/eccGoods/getGoodClass?classification=4&upperCode="+$('#class3').combobox('getValue'),
									valueField:'value', 
									textField:'text',
								});
					        	$("#fittingType").combobox("clear");
				         	} 
						});
			        	$("#class3").combobox("clear");
			        	$("#fittingType").combobox("clear");
		         	} 
				});
	        	$("#class2").combobox("clear");
	        	$("#class3").combobox("clear");
	        	$("#fittingType").combobox("clear");
	     	} 
		});
	});
	
	var indexForSuit = 0;
	var indexForProvider=0;;
	var indexForFaultCode = 0;
	
	function addForSuit(){
		var count = $("#numberForSuit").val();
		for(var i = 0; i < count; i++){
			$('#dataGridForSuit').datagrid('appendRow',{});
			$('#dataGridForSuit').datagrid('beginEdit', indexForSuit);
			indexForSuit ++;
		}
	}
	
	function deleteForSuit(){
		var checkedData = $('#dataGridForSuit').datagrid('getRowNum');
		indexForSuit = indexForSuit - checkedData.length;
		$.each(checkedData, function(){
			$('#dataGridForSuit').datagrid('deleteRow', checkedData.pop() - 1);
		});
	}
	
	function addForProvider(){
		var count = $("#numberForProvider").val();
		for(var i = 0; i < count; i++){
			$('#dataGridForProvider').datagrid('appendRow',{});
			$('#dataGridForProvider').datagrid('beginEdit', indexForProvider);
			indexForProvider ++;
		}
	}
	
	function deleteForProvider(){
		var checkedData = $('#dataGridForProvider').datagrid('getRowNum');
		indexForProvider = indexForProvider - checkedData.length;
		$.each(checkedData, function(){
			$('#dataGridForProvider').datagrid('deleteRow', checkedData.pop() - 1);
		});
	}
	
	function addForFaultCode(){
		var count = $("#numberForFaultCode").val();
		for(var i = 0; i < count; i++){
			$('#dataGridForFaultCode').datagrid('appendRow',{});
			$('#dataGridForFaultCode').datagrid('beginEdit', indexForFaultCode);
			indexForFaultCode ++;
		}
	}
	
	function deleteForFaultCode(){
		var checkedData = $('#dataGridForFaultCode').datagrid('getRowNum');
		indexForFaultCode = indexForFaultCode - checkedData.length;
		$.each(checkedData, function(){
			$('#dataGridForFaultCode').datagrid('deleteRow', checkedData.pop() - 1);
		});
	}
	
	function back(){
		history.go(-1);
		//window.location.href = "${ctx}/hxFitting/hxFittingView";
	}
	
	
	function save(){
		$('#dataGridForSuit').datagrid('acceptChanges');
		var suits = $("#dataGridForSuit").datagrid('getRows');
		for(var i = suits.length - 1; i >= 0; i--){
			if($.trim(suits[i].suitModel) == ""){
				suits.splice(i, 1);
			}
		}
		
		$('#dataGridForProvider').datagrid('acceptChanges');
		var providers = $("#dataGridForProvider").datagrid('getRows');
		for(var i = providers.length - 1; i >= 0; i--){
			if($.trim(providers[i].provider) == ""){
				providers.splice(i, 1);
			}
		}
		$('#dataGridForFaultCode').datagrid('acceptChanges');
		var faultCodes = $("#dataGridForFaultCode").datagrid('getRows');
		for(var i = faultCodes.length - 1; i >= 0; i--){
			if($.trim(faultCodes[i].faultCode) == ""){
				faultCodes.splice(i, 1);
			}
		}
		
		var hxFitting = {};
		hxFitting.fitting = $.serializeObject($('#form'));
		hxFitting.suits = suits;
		hxFitting.providers = providers;
		hxFitting.faultCodes = faultCodes;
		
		$.ajax({ 
            type:"POST", 
            url:"${ctx}/hxFitting/updateHxFitting", 
            dataType:"json",      
            contentType:"application/json",               
            data:JSON.stringify(hxFitting), 
            success:function(data){
            	if(data.flag == "success"){
	            	$.messager.alert('','修改成功!',null,function(){
	            		window.location.href = "${ctx}/hxFitting/hxFittingView";
	            	});
            	}else{
            		$.messager.alert('','修改失败!');
            	}
            } 
        }); 
	}
	
	$.serializeObject = function(form) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	};
</script>
<form id="form" method="post">
	<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
		更新配件资料
	</div>
	<input type="hidden" name="fittingCode" value="${hxFitting.fittingCode}">
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	    <tr>
		    <td width="25%" align="center">一级分类</td>
			<td width="25%"><input id="class1" type="text"  class="easyui-combobox"/></td>
			<td width="25%" align="center">二级分类</td>
			<td width="25%"><input id="class2" type="text"  class="easyui-combobox"></td>
		</tr>
		<tr>
			<td width="25%" align="center">三级分类</td>
			<td width="25%"><input id="class3" type="text"  class="easyui-combobox"></td>
			<td width="25%" align="center">配件分类<font color="red">*</font></td>
			<td width="25%"><input class="easyui-combobox" name="fittingType" id="fittingType" data-options="url:'${ctx}/hxCode/getCombobox/cpfl?value=${hxFitting.fittingType}', editable:false" /></td>
		</tr>
		<tr>
			<td width="25%" align="center">配件编码<font color="red">*</font></td>
			<td width="25%">${hxFitting.fittingCode}</td>
			<td width="25%" align="center">配件名称<font color="red">*</font></td>
			<td width="25%"><input name="fittingName" type="text" placeholder="请输入配件名称" value="${hxFitting.fittingName}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">部品号</td>
			<td width="25%"><input name="partsCode" type="text" placeholder="请输入部品号" value="${hxFitting.partsCode}"></td>
			<td width="25%" align="center">规格</td>
			<td width="25%"><input name="spec" type="text" placeholder="请输入规格" value="${hxFitting.spec}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">生产类型</td>
			<td width="25%"><input class="easyui-combobox" name="produceType" data-options="url:'${ctx}/hxCode/getCombobox/sclx?value=${hxFitting.produceType}', editable:false"/></td>
			<td width="25%" align="center">配件级别</td>
			<td width="25%"><input class="easyui-combobox" name="fittingLevel" data-options="url:'${ctx}/hxCode/getCombobox/pjjb?value=${hxFitting.fittingLevel}', editable:false"/></td>
		</tr>
		<tr>
		    <td width="25%" align="center">品牌<font color="red">*</font></td>
		    <td width="25%"><input class="easyui-combobox" name="brand"   data-options="url:'${ctx}/hxCode/getCombobox/pp?value=${hxFitting.brand}', editable:false"/></td>
			<td width="25%" align="center">国美代码<font color="red">*</font></td>
			<td width="25%"><input class="easyui-combobox" name="gomeCode" data-options="url:'${ctx}/hxCode/getCombobox/gmdm?value=${hxFitting.gomeCode}', editable:false"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">成本价格<font color="red">*</font></td>
			<td width="25%"><input name="cost" type="text" placeholder="请输入成本价格" value="${hxFitting.cost}"></td>
			<td width="25%" align="center">分部价格<font color="red">*</font></td>
			<td width="25%"><input name="branchPrice" type="text" placeholder="请输入分部价格" value="${hxFitting.branchPrice}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">网点价格<font color="red">*</font></td>
			<td width="25%"><input name="networkPrice" type="text" placeholder="请输入网点价格" value="${hxFitting.networkPrice}"></td>
			<td width="25%" align="center">用户价格<font color="red">*</font></td>
			<td width="25%"><input name="userPrice" type="text" placeholder="请输入用户价格" value="${hxFitting.userPrice}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">是否回收</td>
			<td width="25%"><input class="easyui-combobox" name="isRetrieve" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${hxFitting.isRetrieve}', editable:false"/></td>
			<td width="25%" align="center">是否停用</td>
			<td width="25%"><input class="easyui-combobox" name="isStop" data-options="url:'${ctx}/hxCode/getCombobox/sf?value=${hxFitting.isStop}', editable:false"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">保修期限</td>
			<td width="25%"><input name="term" type="text" placeholder="请输入保修期限" value="${hxFitting.term}"></td>
			<td width="25%" align="center">备注</td>
			<td width="25%"><textarea name="comment" placeholder="请输入备注">${hxFitting.comment}</textarea></td>
		</tr>
	</table>
</form>
<div>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="dataGridForSuit"></table>
	</div>
		
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="dataGridForFaultCode"></table>
	</div>
	
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="dataGridForProvider"></table>
	</div>
</div>
<div id="toolbarForSuit" style="display: none;">
	<input type="text" id="numberForSuit" style="width: 20px;" value="1">
	<a href="javascript:void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true" onclick="addForSuit();">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true" onclick="deleteForSuit();">删除</a>
</div>

<div id="toolbarForFaultCode" style="display: none;">
	<input type="text" id="numberForFaultCode" style="width: 20px;" value="1">
	<a href="javascript:void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true" onclick="addForFaultCode();">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true" onclick="deleteForFaultCode();">删除</a>
</div>

<div id="toolbarForProvider" style="display: none;">
	<input type="text" id="numberForProvider" style="width: 20px;" value="1">
	<a href="javascript:void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true" onclick="addForProvider();">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true" onclick="deleteForProvider();">删除</a>
</div>
<div align="right" style="padding:5px;">
	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="save();">保存</a>
	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="back();">返回</a>
</div>
