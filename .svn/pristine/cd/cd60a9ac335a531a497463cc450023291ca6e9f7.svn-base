<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
		var editIndex = undefined;
		var codeId = "${hxCode.codeId}";
		
		function append(){
			if (editIndex == undefined){
				$('#dg').datagrid('appendRow',{});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			}
		}
		
		function save(index){
			editIndex = undefined;
			$('#dg').datagrid('acceptChanges');
			var row = $('#dg').datagrid('getRows')[index];
			if($.trim(row.codeKey)==""||$.trim(row.codeValue)==""){
				$.messager.show({
					title:'我的消息',
					msg:'不能为空',
					timeout:2000,
					showType:'slide'
				});
				$('#dg').datagrid('deleteRow',index);
				return;
			}
			var reg=/^\d+$/;
			if(!reg.test($.trim(row.codeKey))){
				$.messager.show({
					title:'我的消息',
					msg:'key只能为纯数字',
					timeout:2000,
					showType:'slide'
				});
				$('#dg').datagrid('deleteRow',index);
				return;
			}
			$.post("${ctx}/hxCode/insertSetting",{
				codeId : codeId,
				codeKey : $.trim(row.codeKey),
				codeValue : $.trim(row.codeValue)
			},
			function(msg){
				if(msg == "success"){
					$.messager.show({
						title:'我的消息',
						msg:'保存成功',
						timeout:2000,
						showType:'slide'
					});
					$('#dg').datagrid('load');
				}
				if(msg == "failure"){
					$.messager.show({
						title:'我的消息',
						msg:'保存失败',
						timeout:2000,
						showType:'slide'
					});
					$('#dg').datagrid('deleteRow',index);
				}
			});
		}
		
		function back(){
			window.history.back();
		}
	</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid" title="修改代码表:${hxCode.codeName}" style="fit : true,border : false"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				fitColumns : true,
				toolbar: '#tb',
				url: '${ctx}/hxCode/getHxCodeSettingByCodeId/${hxCode.codeId}',
				method: 'get'
			">
		<thead>
			<tr>
				<th data-options="field:'codeId',width:10,align:'center',hidden:true"></th>
				<th data-options="field:'codeKey',width:10,align:'center',editor:'text'">代码键</th>
				<th data-options="field:'codeValue',width:10,align:'center',editor:'text'">代码值</th>
				<th data-options="field:'action',width:5,align:'center',
					formatter : function(value, row, index) {
						if(!row.codeId){
							return '<a href=&quot;#&quot; onclick=&quot;save(\'' + index + '\');&quot;>保存</a>';
						}
					}">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true" onclick="back()">返回</a>
	</div>
	
</body>
</html>
