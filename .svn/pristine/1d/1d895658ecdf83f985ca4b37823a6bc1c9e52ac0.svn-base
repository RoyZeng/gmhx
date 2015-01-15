<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
		var dataGrid;
		$(function() {
			dataGrid = $('#dataGrid').datagrid({
				title : "机型条码对照",
				url : "${ctx}/hxCodeBar/getHxCodeBarPageList",
				striped : true,
				collapsible : true,
				autoRowHeight : false,
				remoteSort : false,
				rownumbers : true,
				fitColumns : true,
				pagination : true,
				singleSelect : true,
				height : document.body.clientHeight*0.7,
				queryParams : {
					pageNo : 1,
					pageCount : 20
				},
				columns : [ [ {
					field : 'id',
					hidden : true
				}, {
					field : 'compare_type',
					title : '对照类型',
					align:'center',
					width : 50
				}, {
					field : 'inner_code1',
					title : '内机代码1',
					align:'center',
					width : 50
				}, {
					field : 'inner_code2',
					title : '内机代码2',
					align:'center',
					width : 50
				}, {
					field : 'outer_code',
					title : '外机代码',
					align:'center',
					width : 50
				}, {
					field : 'inner_model1',
					title : '内机机型1',
					align:'center',
					width : 50
				}, {
					field : 'inner_model2',
					title : '内机机型2',
					align:'center',
					width : 50
				}, {
					field : 'outer_model',
					title : '外机机型',
					align:'center',
					width : 50
				}, {
					field : 'whole_model',
					title : '整机机型',
					align:'center',
					width : 50
				}, {
					field : 'code_begin',
					title : '代码起始位',
					align:'center',
					width : 50
				},  {
					field : 'brand',
					title : '品牌',
					align:'center',
					width : 50
				}, {
					field : 'update_time',
					title : '更新日期',
					align:'center',
					width : 50
				}, {
					field : 'action',
					title : '操作',
					align:'center',
					width : 50,
					formatter : function(value, row, index) {
						return '<a href="#" onclick="view(\'' + row.id + '\');">查看</a> <a href="#" onclick="update(\'' + row.id + '\');">修改</a>';
					}
				} ] ],
				toolbar : '#toolbar',
				onLoadSuccess : function() {
					$('#searchForm table').show();
					$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
					parent.$.messager.progress('close');
				}
			});
			$('.datagrid-pager').pagination({
				pageSize: 20,//每页显示的记录条数，默认为10 
				onSelectPage : function(pageNumber, pageSize) {
					$(this).pagination('loading');
					var queryParams = $.serializeObject($('#searchForm'));
					queryParams.currentPage = pageNumber;
					queryParams.pageCount = pageSize;
					$('#dataGrid').datagrid("options").queryParams = queryParams;
					$('#dataGrid').datagrid("reload");
					$(this).pagination('loaded');
				}
			});
			$("#query").on("click", function() {
				dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
			});
		});
		$.serializeObject = function(form) {
			var o = {
				pageNo : 1,
				pageCount : 20
			};
			$.each(form.serializeArray(), function(index) {
				if (o[this['name']]) {
					o[this['name']] = o[this['name']] + "," + this['value'];
				} else {
					o[this['name']] = this['value'];
				}
			});
			return o;
		};
		
		function update(id){
			parent.$.modalDialog({
				title : '修改机型',
				width : 600,
				height : 320,
				closable : false,
				href : '${ctx}/hxCodeBar/updateView/' + id,
				buttons : [ {
					text : '确定',
					handler : function() {
						if(validate()){
							$.post("${ctx}/hxCodeBar/updateHxCodeBar",parent.$.modalDialog.handler.find('#form').serialize(),
							function(msg){
								parent.$.messager.progress('close');
								if(msg == "success"){
									$.messager.alert('','修改成功!');
									dataGrid.datagrid("reload");
									parent.$.modalDialog.handler.dialog('close');
								}else{
									$.messager.alert('','修改失败!');
								}
							});
						}
					}
				}, {
					text : '取消',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
					}
				}]
			});
		}
		
		function view(id){
			parent.$.modalDialog({
				title : '查看机型条码对照',
				width : 600,
				height : 320,
				closable : false,
				href : '${ctx}/hxCodeBar/viewHxCodeBar/' + id,
				buttons : [ {
					text : '修改',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
						update(id);
					}
				}, {
					text : '取消',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
					}
				}]
			});
		}
	
		function refreshDataGrid() {
			$('#dataGrid').datagrid("reload");
			parent.$.modalDialog.handler.dialog('close');	
		}
		
		function validate(){
			var flag = true;
			if($$('[name=compareType]').val() == ''){
				parent.$.messager.alert('','对照类型不能为空!');
				return false;
			}
			if($$('[name=compareType]').val() == '01'){
				if(($.trim($$('[name=innerCode1]').val())==''&&$.trim($$('[name=outerCode]').val())=='')
					||($.trim($$('[name=innerCode1]').val())!=''&&$.trim($$('[name=outerCode]').val())!='')){
					parent.$.messager.alert('','内机代码1和外机代码必须存在一个!');
					return false;
				}
			}
			if($$('[name=compareType]').val() == '02'){
				if($.trim($$('[name=innerCode1]').val())==''&&$.trim($$('[name=outerCode]').val())==''){
					parent.$.messager.alert('','内机代码1和外机代码必须存在!');
					return false;
				}
			}
			if($$('[name=compareType]').val() == '03'){
				if($.trim($$('#innerModel1').combo('getText'))==''||$.trim($$('#outerModel').combo('getText'))==''){
					parent.$.messager.alert('','内机机型1和外机机型不能为空!');
					return false;
				}else{
				    $.ajax({  
			              type : "post",  
			              url : "${ctx}/hxCodeBar/validateModel/" + $$('#innerModel1').combo('getText'),  
			              async : false,
			              cache: false,
			              success : function(msg){  
			            	  if(msg == "failure"){
									parent.$.messager.alert('','内机机型1不存在!');
									flag = false;
							  }
			              }  
			        });
				    if(!flag){
				    	return false;
				    }
				    $.ajax({  
			              type : "post",  
			              url : "${ctx}/hxCodeBar/validateModel/" + $$('#outerModel').combo('getText'),  
			              async : false,
			              cache: false,
			              success : function(msg){  
			            	  if(msg == "failure"){
									parent.$.messager.alert('','外机机型不存在!');
									flag = false;
							  }
			              }  
			        });
					if(!flag){
				    	return false;
				    }
				}
				if($$('#innerModel2').combo('getText')!=''){
					$.ajax({  
			              type : "post",  
			              url : "${ctx}/hxCodeBar/validateModel/" + $$('#innerModel2').combo('getText'),  
			              async : false,
			              cache: false,
			              success : function(msg){  
			            	  if(msg == "failure"){
									parent.$.messager.alert('','内机机型2不存在!');
									flag = false;
							  }
			              }  
			        });
					if(!flag){
				    	return false;
				    }
				}
			}
			if($$('#wholeModel').combo('getText')==''){
				parent.$.messager.alert('','整机机型不能为空!');
				return false;
			}else{
				$.ajax({  
		              type : "post",  
		              url : "${ctx}/hxCodeBar/validateModel/" + $$('#wholeModel').combo('getText'),  
		              async : false,
		              cache: false,
		              success : function(msg){  
		            	  if(msg == "failure"){
								parent.$.messager.alert('','整机机型不存在!');
								flag = false;
						  }
		              }  
		        });
				if(!flag){
			    	return false;
			    }
			}
			if($$('[name=codeBegin]').val() == ''){
				parent.$.messager.alert('','代码起始位不能为空!');
				return false;
			}
			return true;
		}
		
		function add(){
			parent.$.modalDialog({
				title : '新增机型条码对照',
				width : 600,
				height : 320,
				closable : false,
				href : '${ctx}/hxCodeBar/addView/',
				buttons : [ {
					text : '保存',
					handler : function() {
						if(validate()){
							$.post("${ctx}/hxCodeBar/addHxCodeBar",
							$$('#form').serialize(),
							function(msg){
								parent.$.messager.progress('close');
								if($.parseJSON(msg).flag == "success"){
									parent.$.messager.alert('','新增成功!');
									dataGrid.datagrid("reload");
									parent.$.modalDialog.handler.dialog('close');
								}else{
									parent.$.messager.alert('','新增失败!');
								}
							});
						}
					}
				}, {
					text : '继续添加',
					handler : function() {
						if(validate()){
							$.post("${ctx}/hxCodeBar/addHxCodeBar",
							$$('#form').serialize(),
							function(msg){
								parent.$.messager.progress('close');
								if($.parseJSON(msg).flag == "success"){
									dataGrid.datagrid("reload");
									parent.$.modalDialog.handler.dialog('close');
									add();
								}else{
									parent.$.messager.alert('','新增失败!');
								}
							});
						}
					}
				}, {
					text : '取消',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
					}
				}]
			});
		}
		
		function importExcel() {
			parent.$.modalDialog({
				title : '机型条码对照数据导入',
				width : 400,
				height :250,
				closable : false,
				href : '${ctx}/common/import/importData?templateName=codebar&actionName=importHxCodeBar',
				buttons : [ 
				{
					text : '关闭',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
					}
				}]
			});
		}
		
	</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 200px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="5%">对照类型:</td>
						<td width="5%"><input class="easyui-combobox" name="compareType" data-options="url:'${ctx}/hxCode/getCombobox/dzlx', editable:false"/></td>
						<td width="5%"></td>
						<td width="5%">内机代码1:</td>
						<td width="5%"><input name="innerCode1" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="5%">内机代码2:</td>
						<td width="5%"><input name="innerCode2" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="55%"></td>
					</tr>
					<tr>
						<td width="5%">外机代码:</td>
						<td width="5%"><input name="outerCode" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="5%">内机机型1:</td>
						<td width="5%"><input name="innerModel1" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="5%">内机机型2:</td>
						<td width="5%"><input name="innerModel2" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="55%"></td>
					</tr>
					<tr>
						<td width="5%">外机机型:</td>
						<td width="5%"><input name="outerModel" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="5%">整机机型:</td>
						<td width="5%"><input name="wholeModel" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="5%">代码起始位:</td>
						<td width="5%"><input name="codeBegin" placeholder="输入查询条件"/></td>
						<td width="5%"></td>
						<td width="55%"></td>
					</tr>
					<tr>
						<td width="5%">更新日期:</td>
						<td width="25%">
							<input id="ksrq" placeholder="选择起始日期" class="Wdate" name="ksrq" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})"/>至
							<input id="jsrq" placeholder="选择结束日期" class="Wdate" name="jsrq" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')}'})"/> 
						</td>
						<td width="5%"></td>
						<td width="5%">品牌:</td>
						<td width="5%"><input class="easyui-combobox" name="brand" data-options="url:'${ctx}/hxCode/getCombobox/pp', editable:false"/></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="35%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true" onclick="importExcel();">对照导入</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
