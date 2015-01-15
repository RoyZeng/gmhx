<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		var isFittingStock = $("#isFittingStock").val();
		if(isFittingStock=='1'){
			$("#partManage").show();
			$("#normalManage").hide();
		}else{
			$("#partManage").hide();
			$("#normalManage").show();
		}
		$('#isFittingStock').combobox({
			 onSelect: function (record) {
			 	var isFittingStock = record.value;
			    if(isFittingStock=='0' || isFittingStock == null ){
			    	$("#partManage").hide();
			    	$("#normalManage").show();
			    }else if(isFittingStock=='1'){
			    	$("#partManage").show();
			    	$("#normalManage").hide();
			    }
			}
		});
	});
	
	function submitForm(){
		if(validateForm()){
			$.post("${ctx}/hxOrganization/updateOrganization",$('#form').serialize(),
					function(msg){
						if($.parseJSON(msg).flag == "success"){
							$.messager.alert('提示:','修改成功!');
						}else if($.parseJSON(msg).flag == "duplicatekey"){
							$.messager.alert('提示:','修改失败,机构代码重复!');
						}
			});
		}
		
	}
	
	function goBack(){
		var pageMarkup = $("#pageMarkup").val();
		if("tree"==pageMarkup){
			window.location.href="${ctx}/hxOrganization/organizationTree";
		}else if("list"==pageMarkup){
			window.location.href="${ctx}/hxOrganization/organizationView";
		}else if("limit"==pageMarkup){
			window.location.href="${ctx}/hxLimit/limitHistoryView";
		}
	}
	
	function validateForm(){
		var orgName = $("#orgName").val();
		if(orgName==''||orgName==null){
			alert("机构名称不能为空");
			return false;
		}else{
			if(orgName.length>100){
				alert("机构名称的长度不能大于100");
				return false;
			}
		}
		var orgCode = $("#orgCode").val();
		if(orgCode==''||orgCode==null){
			alert("机构代码不能为空");
			return false;
		}else{
			if(orgCode.length>32){
				alert("机构代码的长度不能大于32");
				return false;
			}
		}
		var orgParentId = $('#orgParentId').combobox('getValues');
		if(orgParentId==''||orgParentId==null){
			alert("上级机构不能为空");
			return false;
		}
		
		var orgType = $('#orgType').combobox('getValues');
		if(orgType==''||orgType==null){
			alert("机构类型不能为空");
			return false;
		}
		
		var orgReference = $("#orgReference").val();
		if(orgReference.length>50){
			alert("联系人的长度不能大于50");
			return false;
		}
		
		var orgAddress = $("#orgAddress").val();
		if(orgAddress.length>255){
			alert("地址的长度不能大于255");
			return false;
		}
		var orgPostCode = $("#orgPostCode").val();
		if(orgPostCode.length>20){
			alert("邮编的长度不能大于20");
			return false;
		}
		var orgTelephone = $("#orgTelephone").val();
		if(orgTelephone==''||orgTelephone==null){
		}else{
			if(orgTelephone.length>50){
				alert("电话的长度不能大于50");
				return false;
			}else{
				var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
				if(!reg.test(orgTelephone)){
					alert("电话的格式不正确");
					return false;
				}
			}
		}
		var orgManager = $("#orgManager").val();
		if(orgManager==''||orgManager==null){
			alert("业务负责人不能为空");
			return false;
		}else{
			if(orgManager.length>50){
				alert("业务负责人的长度不能大于50");
				return false;
			}
		}
		var orgManagerPhone = $("#orgManagerPhone").val();
		if(orgManagerPhone==''||orgManagerPhone==null){
			alert("业务负责人电话不能为空");
			return false;
		}else{
			if(orgManagerPhone.length>50){
				alert("业务负责人电话的长度不能大于50");
				return false;
			}else{
				var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
				if(!reg.test(orgManagerPhone)){
					alert("业务负责人电话的格式不正确");
					return false;
				}
			}
		}
		var orgFax = $("#orgFax").val();
		if(orgFax==''||orgFax==null){
		}else{
			if(orgFax.length>50){
				alert("传真的长度不能大于50");
				return false;
			}else{
				var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
				if(!reg.test(orgFax)){
					alert("传真的格式不正确");
					return false;
				}
			}
		}
		var orgNote = $("#orgNote").val();
		if(orgNote.length>2000){
			alert("备注的长度不能大于2000");
			return false;
		}
		return true;
	}
	
	function partManage(){
		var orgCode = $("#orgCode").val();
		window.open('${ctx}/hxOrganization/fittingStockManageView?orgCode='+orgCode,"",'toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750,height=620');
	}
</script>
		<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				修改组织机构资料
			</div>
			<input type="hidden" id="pageMarkup" title="页面标记位" value="${organization.pageMarkup}">
			<input type="hidden" id="fromType" name="fromType" title="来源标记位" value="${organization.fromType}">
			<input type="hidden" id="oldOrgCode" name="oldOrgCode" value="${organization.orgCode}">
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">机构名称<font color="red">*</font></td>
					<td width="25%"><input id="orgName" name="orgName" type="text" placeholder="请输入机构名称" value="${organization.orgName}" <c:if test="${organization.fromType!='hx001'}">readonly</c:if> ></td>
					<td width="25%" align="center">机构级别</td>
					<td width="25%"><input id="orgLevel" name="orgLevel" type="text" placeholder="请输入机构级别" value="${organization.orgLevel}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">机构代码<font color="red">*</font></td>
					<td width="25%"><input id="orgCode" name="orgCode" type="text" placeholder="请输入机构代码" value="${organization.orgCode}" ></td>
					<td width="25%" align="center">上级机构<font color="red">*</font></td>
					<td width="25%"><input id="orgParentId" name="orgParentId" type="text" placeholder="请选择上级机构" editable="true" class="easyui-combobox" class="span2" value="${organization.orgParentId}" data-options="url:'${ctx}/hxCode/getOrgCombobox'"  ></td>
				</tr>
				<tr>
					<td width="25%" align="center">机构类型<font color="red">*</font></td>
					<td width="25%"><input id="orgType" name="orgType" type="text" placeholder="请输入机构类型" editable="false" class="easyui-combobox" class="span2" value="${organization.orgType}" data-options="url:'${ctx}/hxCode/getCombobox/jglx'"  data-options="required:true"></td>
					<td width="25%" align="center">地区</td>
					<td width="25%"><input id="orgCity" name="orgCity" type="text" placeholder="请输入地区" value="${organization.orgCity}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">联系人</td>
					<td width="25%"><input id="orgReference" name="orgReference" type="text" placeholder="请输入联系人"  value="${organization.orgReference}"></td>
					<td width="25%" align="center">地址</td>
					<td width="25%"><input id="orgAddress" name="orgAddress" type="text" placeholder="请输入地址"  value="${organization.orgAddress}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">邮编</td>
					<td width="25%"><input id="orgPostCode" name="orgPostCode" type="text" placeholder="请输入邮编"  value="${organization.orgPostCode}"></td>
					<td width="25%" align="center">电话</td>
					<td width="25%"><input id="orgTelephone" name="orgTelephone" type="text" placeholder="请输入电话"  value="${organization.orgTelephone}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">业务负责人<font color="red">*</font></td>
					<td width="25%"><input id="orgManager" name="orgManager" type="text" placeholder="请输入业务负责人"  value="${organization.orgManager}"></td>
					<td width="25%" align="center">业务负责人电话<font color="red">*</font></td>
					<td width="25%"><input id="orgManagerPhone" name="orgManagerPhone" type="text" placeholder="请输入业务负责人电话" value="${organization.orgManagerPhone}"></td>
				</tr>
				<tr>
					<td width="25%" align="center">传真</td>
					<td width="25%"><input id="orgFax" name="orgFax" type="text" placeholder="请输入传真" value="${organization.orgFax}"></td>
					<td width="25%" align="center">退换机总部审批</td>
					<td width="25%"><input id="orgApproval" name="orgApproval" type="text" placeholder="请输入退换机总部审批" editable="false" class="easyui-combobox" class="span2" value="${organization.orgApproval}" data-options="url:'${ctx}/hxCode/getCombobox/sf'"></td>
				</tr>
				<tr>
					<td width="25%" align="center">是否为配件库</td>
					<td width="25%"><input id="isFittingStock" name="isFittingStock"  type="text" placeholder="请选择是否为配件库" editable="false" class="easyui-combobox" class="span2" value="${organization.isFittingStock}" data-options="url:'${ctx}/hxCode/getCombobox/sf'" ></td>
					<td width="25%" align="center">物料上级机构</td>
					<td width="25%"><input id="fittingPid" name="fittingPid"  type="text" placeholder="请选择物料上级机构" editable="true" class="easyui-combobox" class="span2" value="${organization.fittingPid}" data-options="url:'${ctx}/hxCode/getOrgCombobox'" ></td>
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" colspan="3"><textarea id="orgNote" name="orgNote"  placeholder="请输入备注" rows="3" cols="52"  >${organization.orgNote}</textarea></td>
				</tr>
			</table>
		</form>
	<div style="text-align:center;padding:5px">
			<div id="partManage">
				<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="partManage();">配件库分管配置</a>
				<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    		<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
			</div>
			<div id="normalManage">
				<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="submitForm();">保存</a>
	    		<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
			</div>
	    	
	</div>
</div>