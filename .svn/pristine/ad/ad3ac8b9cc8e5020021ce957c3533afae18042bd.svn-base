<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	
$(function() {
	parent.$.messager.progress('close');
});

function goBack(){
	window.location.href="${ctx}/hxBillNumImportQuery/hxBillNumView";	
}

</script>
<div class="easyui-panel" title="详细查看提单号" style="width:auto">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td width="25%" align="center">公司名称</td>
					<td width="25%" ><label>${sale.gsmc}</label></td>
					<td width="25%" align="center">提货单号</td>
			     	<td width="25%" ><label>${sale.thdh}</label></td>
				<tr>
					<td width="25%" align="center">顾客姓名</td>
				    <td width="25%" ><label>${sale.khmc}</label></td>
				    <td width="25%" align="center">商品名称</td>
				    <td width="25%" ><label>${sale.spmc}</label></td>
				</tr>
				<tr>
				    <td width="25%" align="center">销售金额</td>
				    <td width="25%" ><label>${sale.xsje}</label></td>		
					<td width="25%" align="center">发票号</td>
					<td width="25%" ><label>${sale.fph}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">销售日期</td>
					<td width="25%" ><label><fmt:formatDate value="${sale.jzrq}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
					<td width="25%" align="center">购自门店</td>
					<td width="25%" ><label>${sale.bmmc}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">营业员</td>
					<td width="25%" ><label>${sale.yyymc}</label></td>
					<td width="25%" align="center">赠品信息</td>
					<td width="25%" ><label>${sale.zpbj}</label></td>
				</tr>	
				<tr>
					<td width="25%" align="center">品类</td>
					<td width="25%" ><label>${sale.spflmc}</label></td>
					<td width="25%" align="center">品牌</td>
					<td width="25%" ><label>${sale.ppbmc}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">商品编码</td>
					<td width="25%" ><label>${sale.spbm}</label></td>
					<td width="25%" align="center">商品名称</td>
					<td width="25%" ><label>${sale.spmc}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">pos标识</td>
					<td width="25%" ><label>${sale.ds}</label></td>
					<td width="25%" align="center">迁移时间</td>
					<td width="25%" ><label>${sale.ts}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">销售类型</td>
					<td width="25%" ><label><c:if test="${sale.xslx==0 }"
					>正常零售</c:if><c:if test="${sale.xslx==1 }"
					>其他建档</c:if></label></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>
