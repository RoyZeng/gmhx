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
			title : "流程岗位映射",
			url : "${ctx}/hxPositionMapping/getPositionMappingPageList",
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 155,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			checkOnSelect : false,
			selectOnCheck : false,
			queryParams : {
				currentPage : 1,
				pageCount : 10
			},
			columns : [ [ {
                checkbox:true
            },{
				field : 'jbpmRoleCode',
				title : '审批权限编码',
				width : 10,
				align:'center',
				sortable : true
			} , {
				field : 'jbpmRoleName',
				title : '审批权限名称',
				width : 10,
				align:'center',
				sortable : true
			} ,{
				field : 'action',
				title : '操作',
				width : 10,
				align:'center',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="connection(\'' + row.jbpmRoleCode + '\',\'' + row.jbpmRoleName  + '\');">关联岗位角色</a>';
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');
			}
		});
		$('.datagrid-pager').pagination({
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
			currentPage : 1,
			pageCount : 10
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
	
	function searchs() {
		$('#dataGrid').datagrid('load');
	};

	function refreshDataGrid() {
		$('#dataGrid').datagrid("reload");
		parent.$.modalDialog.handler.dialog('close');	
	}	
	
	function addData(){
		window.location.href="${ctx}/hxPositionMapping/addView";
	}
	
	function editData() {
		var selectRows = $("#dataGrid").datagrid('getChecked');
		if (selectRows.length == 0) {
			$.messager.alert('提示', '请选择您要修改的行!', 'info');
		} else if (selectRows.length > 1) {
			$.messager.alert('提示', '一次只能编辑一条数据!', 'info');
		} else {
			var jbpmRoleCode = selectRows[0].jbpmRoleCode;
			var jbpmRoleName= selectRows[0].jbpmRoleName;
			var url="${ctx}/hxPositionMapping/updateView?jbpmRoleCode="+ jbpmRoleCode+"&jbpmRoleName="+jbpmRoleName;
			url = encodeURI(encodeURI(url));
			window.location.href=url;
		}
	};
	
	function connection(jbpmRoleCode, jbpmRoleName){
		var url="${ctx}/hxPositionMapping/connectionView?jbpmRoleCode="+ jbpmRoleCode+"&jbpmRoleName="+jbpmRoleName;
		url = encodeURI(encodeURI(url));
		window.location.href=url;
	}
	
	function deleteData(){
		var selectRows = $("#dataGrid").datagrid('getChecked');
		if (selectRows.length == 0) {
			$.messager.alert('提示', '请选择您要删除的行!', 'info');
		} else {
			$.messager.confirm('确认', '您确认删除所选的 ' + selectRows.length + ' 数据吗？', function(r) {
				if (r) {
					var codes = [];
					for (var i = 0; i < selectRows.length; i++) {
						codes.push(selectRows[i].jbpmRoleCode);
					}
					$.post(ctx + "/hxPositionMapping/delete", {
						codes : codes.join(",")
					}, function(data) {
						$.messager.alert('消息提示', data, 'info');
						searchs();
					});
				}
			});
		}
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 155px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%">角色名称:</td>
						<td width="5%"><input name="jbpmRoleCode" placeholder="输入查询条件" class="span2" /></td>
						<td width="5%"></td>
						<td width="10%">角色编码:</td>
						<td width="5%"><input name="jbpmRoleName" placeholder="输入查询条件" class="span2"></td>
						<td width="5%"></td>
						<td width="10%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="40%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="toolbar" style="display: none;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
			onclick="addData();">添加</a> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-edit'" onclick="editData();">修改</a> <a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-cut',plain:true" onclick="deleteData();">删除</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
