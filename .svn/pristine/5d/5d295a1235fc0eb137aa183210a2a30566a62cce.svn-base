<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">空调退换机申请详细资料</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="25%" align="center">申请单号</td><td width="25%" ><label>${sr.applyId}</label></td>
		<td width="25%" align="center">申请单位</td><td width="25%" ><label>${sr.applyUnit}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">销售分部</td><td width="25%" ><label>${sr.sellSubsection}</label></td>
		<td width="25%" align="center">销售门店</td><td width="25%" ><label>${sr.sellStore}</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">是否售前机</td><td width="25%"><input class="easyui-combobox" name="isPresale" value="${sr.isPresale}" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/sf',panelHeight:'auto',required:true,editable:false"/></td>
		<td width="25%" align="center">是否上墙</td><td width="25%"><input class="easyui-combobox" name="isOnwall" value="${sr.isOnwall}" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/sf',panelHeight:'auto',required:true,editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">用户姓名</td><td width="25%" ><label>${sr.customerName}</label></td>
		<td width="25%" align="center">用户电话</td><td width="25%" ><label>${sr.customerPhone}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">用户地址</td><td width="25%" ><label>${sr.customerAddress}</label></td>
		<td width="25%" align="center">购机日期</td><td width="25%" ><label><fmt:formatDate value='${sr.buyDate}' pattern='yyyy-MM-dd' /></label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">故障日期</td><td width="25%" ><label><fmt:formatDate value='${sr.faultDate}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">退换机</td><td width="25%"><input class="easyui-combobox" name="retreatReplacement" value="${sr.retreatReplacement}" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/thj',panelHeight:'auto',required:true,editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">退换模式</td><td width="25%"><input class="easyui-combobox" name="rrMode" value="${sr.rrMode}" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/thms',panelHeight:'auto',required:true,editable:false"/></td>
		<td width="25%" align="center">故障代码</td><td width="25%" ><label>${sr.faultCode}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">故障原因</td><td width="25%" ><label>${sr.faultReason}</label></td>
		<td width="25%" align="center">故障原因详细描述</td><td width="25%" ><label>${sr.faultReasonDetail}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">安装单位</td><td width="25%" ><label>${sr.installUnit}</label></td>
		<td width="25%" align="center">安装日期</td><td width="25%" ><label><fmt:formatDate value='${sr.installDate}' pattern='yyyy-MM-dd' /></label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">安装人员</td><td width="25%" ><label>${sr.intaller}</label></td>
		<td width="25%" align="center">原提货单号</td><td width="25%" ><label>${sr.deliveryOrderNum}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">原机型</td><td width="25%" ><label>${sr.oldMachineType}</label></td>
		<td width="25%" align="center">国美代码(原)</td><td width="25%"><input class="easyui-combobox" name="oldGomeCode" value="${sr.oldGomeCode}" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/gmdm',panelHeight:'auto',required:true,editable:false"/></td>
	</tr>
	<tr>	
		<td width="25%" align="center">品牌(原)</td><td width="25%"><input class="easyui-combobox" name="oldBrand" value="${sr.oldBrand}" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/pp',panelHeight:'auto',editable:false"/></td>
		<td width="25%" align="center">原内机条码1</td><td width="25%" ><label>${sr.oldInnerCode1}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">原内机条码2</td><td width="25%" ><label>${sr.oldInnerCode2}</label></td>
		<td width="25%" align="center">原外机条码</td><td width="25%" ><label>${sr.oldOuterCode}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">现机型</td><td width="25%" ><label>${sr.machineType}</label></td>
		<td width="25%" align="center">国美代码(新)</td><td width="25%"><input class="easyui-combobox" name="gomeCode" value="${sr.gomeCode}" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/gmdm',panelHeight:'auto',editable:false"/></td>
	</tr> 
	<tr>
		<td width="25%" align="center">品牌(新)</td><td width="25%"><input class="easyui-combobox" name="brand" value="${sr.brand}" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/pp',panelHeight:'auto',editable:false"/></td>
		<td width="25%" align="center">现内机条码1</td><td width="25%" ><label>${sr.innerCode1}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">现内机条码2</td><td width="25%" ><label>${sr.innerCode2}</label></td>
		<td width="25%" align="center">现外机条码</td><td width="25%" ><label>${sr.outerCode}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">退换日期</td><td width="25%" ><label><fmt:formatDate value='${sr.rrDate}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">安装卡是否退回</td><td width="25%" ><label>${sr.isIcBack}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">录单时间</td><td width="25%" ><label>${sr.recordTime}</label></td>
		<td width="25%" align="center">状态</td><td width="25%" ><label>${sr.status}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">是否已退库</td><td width="25%" ><label>${sr.isStored}</label></td>
		<td width="25%" align="center">鉴定人</td><td width="25%" ><label>${sr.appraiser}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">机审通过</td><td width="25%" ><label>${sr.checkResult}</label></td>
		<td width="25%" align="center">机审错误原因</td><td width="25%" ><label>${sr.machineCheckFaultReason}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">分部是否同意</td><td width="25%" ><label>${sr.centerCheckResult}</label></td>
		<td width="25%" align="center">分部意见</td><td width="25%" ><label>${sr.centerCheckComment}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">分部审批人</td><td width="25%" ><label>${sr.centerCheckMan}</label></td>
		<td width="25%" align="center">分部审批日期</td><td width="25%" ><label>${sr.centerCheckTime}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">总部是否同意</td><td width="25%" ><label>${sr.headquartersCheckResult}</label></td>
		<td width="25%" align="center">总部意见</td><td width="25%" ><label>${sr.headquartersCheckComment}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">总部审批人</td><td width="25%" ><label>${sr.headquartersCheckMan}</label></td>
		<td width="25%" align="center">总部审批日期</td><td width="25%" ><label>${sr.headquartersCheckTime}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">备注</td><td width="25%" ><label>${sr.comment}</label></td>
		<td width="25%" align="center">创建人</td><td width="25%" ><label>${sr.createMan}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">创建机构</td><td width="25%" ><label>${sr.createOrganization}</label></td>
		<td width="25%" align="center">创建时间</td><td width="25%" ><label>${sr.createTime}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">修改人</td><td width="25%" ><label>${sr.alterMan}</label></td>
		<td width="25%" align="center">修改机构</td><td width="25%" ><label>${sr.alterOrganization}</label></td>
	</tr>
	<tr>	
		<td width="25%" align="center">修改时间</td><td width="25%" ><label>${sr.alterTime}</label></td>
		<td width="25%" align="center">附件</td><td width="25%" ><label>${sr.accessory}</label></td>
	</tr>
</table>