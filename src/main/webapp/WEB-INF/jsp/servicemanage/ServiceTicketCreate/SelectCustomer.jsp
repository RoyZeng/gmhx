<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		
	});
	
	function query(){
		if (($("#queryName").val() == null || $("#queryName").val() == "")
				&& ($("#queryPhone").val() == null || $("#queryPhone").val() == "")
				&& ($("#queryAddress").val() == null || $("#queryAddress").val() == "")
				&& ($("#queryCard").val() == null || $("#queryCard").val() == "")
				&& ($("#queryCode").val() == null || $("#queryCode").val() == "")) {
			$.messager.alert('', '请输入用户查询条件！',null,null);
			return false;
		}else{
			parent.$.modalDialog({
				title : '选取用户',
				width : 600,
				height : 400,
				closable : false,
				href : '${ctx}/serviceTicketCreate/selectCustomer?'+encodeURI($('#query').serialize()),
				buttons : [{
					text : '确定',
					handler : function() {
						var id = parent.$.modalDialog.handler.find('#checkedId').val();
						parent.$.modalDialog.handler.dialog('close');
						window.location.href="${ctx}/serviceTicketCreate/getCustomer?azd01="+ id;
					}
				},{
					text : '取消',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
					}
				}]
			});
		}
	}
	
	function save(){
		$("#form").submit();
	}
	
	function newCustomer(){
		alert("new");
	}
	
	function goBack() {
		window.location.href = "${ctx}/serviceTicketCreate/serviceTicketCreateView";
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 140px; overflow: hidden;">
		<form id="query" method="post">
			<table class="table table-hover table-condensed" width="100%;" style="padding:20px 0 10px 60px">
				<tr>
					<td width="20%">姓名</td>
					<td width="30%"><input id="queryName" name="queryName"
						type="text" placeholder="请输入客户名称"
						class="easyui-validatebox span2" value=""></td>
					<td width="20%">联系电话</td>
					<td width="30%"><input id="queryphone" name="queryphone"
						type="text" placeholder="请输入联系电话"
						class="easyui-validatebox span2" value=""></td>
				</tr>
				<tr>
					<td>机器条码</td>
					<td><input id="queryCode" name="queryCode" type="text"
						placeholder="请输入机器条码" class="easyui-validatebox span2" value=""></td>
					<td>安装卡号</td>
					<td><input id="queryCard" name="queryCard" type="text"
						placeholder="请输入安装卡号" class="easyui-validatebox span2" value=""></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input id="queryAddress" name="queryAddress" type="text"
						placeholder="请输入客户地址" class="easyui-validatebox span2" value=""></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',title:'\r\n',border:false,collapsible:false" style="text-align:center;padding:5px">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="query();">查询</a>
		<a href="#" class="easyui-linkbutton" onclick="newCustomer();">新建</a>
		<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>

