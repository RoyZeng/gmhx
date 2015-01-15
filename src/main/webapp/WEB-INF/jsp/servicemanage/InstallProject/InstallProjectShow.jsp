<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function(){
		$('#projectGrid').datagrid({
			url : "${ctx}/installProject/getProjects/${map.service_id}",
			title : "工程机安装信息",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [ {field : 'serviceId',title : '服务单',align:'center',width : 30
			}, {field : 'machineType',title : '＊机型',align:'center',editor:'text',width : 30
			}, {field : 'internalMachineCode1',title : '＊内机条码1',align:'center',editor:'text',width : 30
			}, {field : 'internalMachineCode2',title : '内机条码2',align:'center',editor:'text',width : 30
			}, {field : 'externalMachineCode',title : '＊外机条码',align:'center',editor:'text',width : 30
			}, {field : 'installCardNum',title : '安装卡号',align:'center',editor:'text',width : 30
			}, {field : 'installDate',title : '＊安装日期',align:'center',editor:'text',width : 30
			}, {field : 'installer',title : '＊安装工',align:'center',editor:'text',width : 30
			}, {field : 'isDoubleInstall',title : '＊是否二次安装',align:'center',editor:'text',width : 30,
				formatter : function(value, row, index) {
					if(value){
						return '是';
			  		}else{
			  			return '否';
			  		}
			  	}
			}, {field : 'warrantyPolicy',title : '＊保修政策',align:'center',editor:'text',width : 30,
				formatter : function(value, row, index) {
					if(value == "01"){
						return "3";
					}else if(value == "02"){
						return "2";
					}else if(value == "03"){
						return "1";
					}else if(value == "04"){
						return "4";
					}else if(value == "05"){
						return "5";
					}else if(value == "06"){
						return "6";
					}
			  	}
			}, {field : 'delayFee',title : '延管费',align:'center',editor:'text',width : 30
			}, {field : 'otherFee',title : '其它费',align:'center',editor:'text',width : 30
			}, {field : 'commentP',title : '备注',align:'center',editor:'text',width : 30
			} ] ],
			toolbar : '#toolbarForProject'
		});
		$("div.easyui-layout").css("height", "auto");
	});
	
	function goBack(){
		window.location.href="${ctx}/installProject/installProjectView";	
	}
</script>
<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">客户信息</div>
<input id="service_id" type="hidden" value="${map.service_id }"/>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="25%" align="center">客户姓名</td><td width="25%" ><label>${map.customer_name}</label></td>
		<td width="25%" align="center">客户类别</td><td width="25%" >
			<input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/khlb?value=${map.customer_type}', editable:false"/>
		</td>
	</tr>
	<tr>
		<td width="25%" align="center">性别</td><td width="25%" >
			<input class="easyui-combobox" disabled="disabled" value="${map.customer_sex}" data-options="url:'${ctx}/hxCode/getCombobox/xb', editable:false"/>
		</td>
		<td width="25%" align="center">会员卡号</td><td width="25%" ><label>${map.member_num }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">省份</td>
		<td width="25%" >
			<input type="text" disabled="disabled" value="${map.province}" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',panelHeight:'200', editable:true,required:true">
		</td>
		<td width="25%" align="center">区号</td><td width="25%" ><label>${map.area_code }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">手机</td><td width="25%" ><label>${map.phone }</label></td>
		<td width="25%" align="center">固定电话</td><td width="25%" ><label>${map.telephone }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">E_MAIL</td><td width="25%" ><label>${map.e_mail }</label></td>
		<td width="25%" align="center">邮编</td><td width="25%" ><label>${map.postcode }</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">联系地址</td><td width="25%" ><label>${map.address}</label></td>
		<td width="25%" align="center">备注</td><td width="25%" ><label>${map.note }</label></td>
	</tr>
</table>
<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">产品信息</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="25%" align="center">购机日期<font color="red">*</font></td><td width="25%" ><label><fmt:formatDate value='${map.buy_date}' pattern='yyyy-MM-dd' /></label></td>
		<td width="25%" align="center">安装单位<font color="red">*</font></td>
		<td width="25%" >
			<input type="text" disabled="disabled" value="${map.install_unit}" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getWebsiteCombobox',required:true">
		</td>
	</tr>
	<tr>
		<td width="25%" align="center">销售门店<font color="red">*</font></td>
		<td width="25%" >
			<input disabled="disabled" type="text" value='${map.sale_market}' class="easyui-combobox" data-options="url:'${ctx}/hxCode/getStoreCombobox',required:true">
		</td>
		<td width="25%" align="center">安装省份<font color="red">*</font></td>
		<td width="25%" >
			<input disabled="disabled" type="text" value='${map.province}' class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',panelHeight:'200', editable:true,required:true">
		</td>
	</tr>
	<tr>
		<td width="25%" align="center">安装详细地址<font color="red">*</font></td><td width="25%" ><label>${map.install_detail_address}</label></td>
	</tr>
	<tr>
		<td width="25%" align="center">工程名称<font color="red">*</font></td><td width="25%" ><label>${map.project_name }</label></td>
		<td width="25%" align="center">合同编码<font color="red">*</font></td><td width="25%" ><label>${map.bargain_code }</label></td>
	</tr>
</table>
<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">客户评价</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<%-- <tr>
		<td width="25%" align="center">产品外观满意度<font color="red">*</font></td>
		<td width="25%" ><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.product_exterior_satisfaction}', editable:false"/></td>
		
		<td width="25%" align="center">产品外观重要性<font color="red">*</font></td>
		<td width="25%" ><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.product_exterior_significance}', editable:false"/></td>
	</tr>
	<tr>
		<td width="25%" align="center">产品质量满意度<font color="red">*</font></td><td width="25%" ><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.quality_satisfaction}', editable:false"/></td>
		<td width="25%" align="center">产品质量重要性<font color="red">*</font></td><td width="25%" ><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.quality_significance}', editable:false"/></td>
	</tr>
	<tr>
		<td width="25%" align="center">价格满意度<font color="red">*</font></td><td width="25%" ><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.price_satisfaction}', editable:false"/></td>
		<td width="25%" align="center">价格重要性<font color="red">*</font></td><td width="25%" ><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.price_significance}', editable:false"/></td>
	</tr>
	<tr>
		<td width="25%" align="center">服务满意度<font color="red">*</font></td><td width="25%" ><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.service_satisfaction}', editable:false"/></td>
		<td width="25%" align="center">服务重要性<font color="red">*</font></td><td width="25%" ><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.service_significance}', editable:false"/></td>
	</tr>
	<tr>
		<td width="25%" align="center">生理感受满意度<font color="red">*</font></td><td width="25%" ><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/myd?value=${map.physiology_satisfaction}', editable:false"/></td>
		<td width="25%" align="center">生理感受重要性<font color="red">*</font></td><td width="25%" ><input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/zyx?value=${map.physiology_significance}', editable:false"/></td>
	</tr> --%>
	<tr>
		<td width="25%" align="center">备注</td><td width="25%" ><label>${map.comment_s }</label></td>
	</tr>
</table>
<br/>
<div class="easyui-layout" data-options="fit : true,border : false">
	<table id="projectGrid"></table>
</div>
<div style="text-align:right;padding:5px">
   	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
</div>