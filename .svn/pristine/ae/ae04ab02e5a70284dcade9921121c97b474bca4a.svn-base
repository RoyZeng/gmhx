<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		$('#dataGrid').datagrid({
			title : "配件明细",
			url : "${ctx}/hxInoutStock/getInoutStockDetail/${m.listNumber}",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			singleSelect : true,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			showFooter: true,
			columns : [ [ {
				field : 'fittingCode',
				title : '配件编码',
				align:'center',
				width : 300
			}, {
				field : 'fittingName',
				title : '配件名称',
				align : 'center',
				width : 300
			}, {
				field : 'count',
				title : '数量',
				align:'center',
				width : 300,
				editor:'numberbox'
			}, {
				field : 'in_price',
				title : '入库价格',
				align : 'center',
				width : 300
			}, {
				field : 'out_price',
				title : '出库价格',
				align : 'center',
				width : 300
			}] ],
			onBeforeLoad : function(param) {
				param.fittingPositionType =  $("#fittingPositionType").val();
				param.inout =  $("#inout").val();
				param.isNew =  $("#isNew").val();
			},
			onLoadSuccess : function() {
				$('#searchForm table').show();
				$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
				parent.$.messager.progress('close');
			}
		});
		
		$("div.datagrid-wrap").css("height","auto");
	});
	
	function back(){
		window.history.go(-1);
	}
	
	function printInOutStock(){
		var iWidth=850; //弹出窗口的宽度;
		var iHeight=850; //弹出窗口的高度;
		var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
		window.open('${ctx }/HxJasperReport/print?type=4&id=${m.listNumber}&inout=${inout}&fittingPositionType=${fittingPositionType}',"","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft+"toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no"); 
}
	
</script>
<form id="form" method="post">
	<input id="authId" type="hidden" value="${type}">
	<input id="listNumber" type="hidden" value="${m.listNumber}">
	<input id="fittingPositionType" type="hidden" value="${fittingPositionType}">
	<input id="inout" type="hidden" value="${inout}">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
		详细查看${title}历史
	</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
		<tr>
			<td width="15%" align="center">单据编号</td>
			<td width="35%">${m.listNumber}</td>
			<td width="15%" align="center">批次号</td>
			<c:if test="${inout==2 }">
				<td width="35%">${m.inBatch}</td>
			</c:if><c:if test="${inout==1 }">
				<td width="35%">${m.outBatch}</td>
			</c:if>
		</tr>
		<tr>
			<td width="15%" align="center">关联仓库</td>
			<td width="35%">${m.sendCompany}</td>
			<td width="15%" align="center">单位名称</td>
			<td width="35%">${m.receiveCompany}</td>
		</tr>
		<tr>
			<td width="15%" align="center">科目</td>
			<td width="35%">${type}</td>
		</tr>
		<tr>
			<td width="15%" align="center">操作员</td>
			<td width="35%">${m.createPerson}</td>
			<td width="15%" align="center">操作日期</td>
			<td width="35%"><fmt:formatDate value="${m.createTime}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<td width="15%" align="center">备注</td>
			<td width="35%" colspan="3">${m.comment}</td>
		</tr>
	</table>
</form>
<div align="right" class="easyui-panel" style="padding:5px;">
	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g2',plain:true,iconCls:'icon-back'" onclick="back();">返回</a>
	<a id="print" href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'"  onclick="printInOutStock();">打印</a>	
</div>
<div class="easyui-layout">
	<table id="dataGrid"></table>
</div>