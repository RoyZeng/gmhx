<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function(){
		$("#formCustomer").hide();
		$("#formService").hide();
		$('#serviceGrid').datagrid({
			title : "服务单",
			striped : true,
			nowrap : false,
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			idField : '',
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			checkOnSelect : false,
			selectOnCheck : false,
			columns : [ [ {field : 'service_id',title : '服务单号',align:'center',width : 10, align:'center', 
			}, {field : 'service_status',title : '服务单状态',align:'center',width : 10, align:'center', 
			}, {field : 'service_type',title : '服务单类型',align:'center',width : 10, align:'center', 
			}, {field : 'urgency_degree',title : '服务紧急程度',align:'center',width : 10, align:'center', 
			}, {field : 'service_pattern',title : '服务方式',align:'center',width : 10, align:'center', 
			}, {field : 'service_date',title : '修复日期',align:'center',width : 10, align:'center', 
			}, {field : 'action',title : '操作 ',align:'center',width : 10, align:'center', 
			} ] ],
			toolbar : '#toolbarForService'
		});
		$("div.easyui-layout").css("height", "auto");
	});
	
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
	
	function validate(){
		if($("#formCustomer input[name=customerName]").val() == ""){alert("请输入客户姓名！");return false;};
		if($("input[name=customerType]").val() == ""){alert("请输入客户类别！");return false;};
		if($("input[name=province]").val() == ""){alert("请输入客户省份！");return false;};
		if($("textarea[name=address]").val() == ""||$("textarea[name=address]").val() == null){alert("请输入客户联系地址！");return false;};
		if($("input[name=category]").val() == ""){alert("请输入品类！");return false;};
		if($("input[name=serviceType]").val() == ""){alert("请选择服务类型！");return false;};
		if($("input[name=urgencyDegree]").val() == ""){alert("请选择紧急程度！");return false;};
		if($("input[name=appointmentQuantum]").val() == ""){alert("请输入预约时间段！");return false;}; 
		
		var phone = $("input[name=phone]").val();
		if(phone==''||phone==null){
		}else{
			if(phone.length>50){
				alert("电话的长度不能大于50");
				return false;
			}else{
				var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
				if(!reg.test(phone)){
					alert("手机的格式不正确");
					return false;
				}
			}
		}
		
		var telephone = $("input[name=telephone]").val();
		if(telephone==''||telephone==null){
		}else{
			if(telephone.length>50){
				alert("电话的长度不能大于50");
				return false;
			}else{
				var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
				if(!reg.test(telephone)){
					alert("固定电话的格式不正确");
					return false;
				}
			}
		}
		return true; 
	}
	
	function save(){
		if(validate()){
			var serviceTicketVO = {};
			serviceTicketVO.serviceCustomer = $.serializeObject($('#formCustomer'));
			serviceTicketVO.serviceTicket = $.serializeObject($('#formService')); 
			$.ajax({
	            type:"POST", 
	            url:"${ctx}/serviceTicketCreate/saveServiceTicket",
	            dataType:"json",
	            contentType:"application/json",
	            data:JSON.stringify(serviceTicketVO),
	            success:function(data){
	            	if(data.flag == "success"){
	            		$.messager.alert('', '保存成功!',null,function(){
	            			window.location.href="${ctx}/serviceTicketCreate/serviceTicketView/"+data.serviceId;
	            		});
	            	}else{
	            		$.messager.alert('','新增失败!');
	            	}
	            } 
	        });
		}
	}
	
	function query(){
		if (($("#queryName").val() == null || $("#queryName").val() == "")
				&& ($("#queryPhone").val() == null || $("#queryPhone").val() == "")
				&& ($("#queryAddress").val() == null || $("#queryAddress").val() == "")
				&& ($("#queryCard").val() == null || $("#queryCard").val() == "")
				&& ($("#queryCode").val() == null || $("#queryCode").val() == "")) {
			alert("请输入用户查询条件！");
			return false;
		}else{
			parent.$.modalDialog({
				title : '选取用户',
				width : 600,
				height : 400,
				closable : false,
				href : '${ctx}/serviceTicketCreate/selectCustomer?'+encodeURI($('#formSearch').serialize()),
				buttons : [{
					text : '确定',
					handler : function() {
						var customerId = parent.$.modalDialog.handler.find('#checkedId').val();
						parent.$.modalDialog.handler.dialog('close');
						show(customerId);
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
	
	function show(customerId){
		$("#formCustomer").show();
		$("#formService").show();
		$.getJSON("${ctx}/serviceTicketCreate/getCustomer?customerId="+ customerId, function(json){
			$("#customerId").val(json.customerId);
			$("#formCustomer input[name=customerName]").val(json.customerName);
			$("#formCustomer input[name=phone]").val(json.phone);
			$("#formCustomer input[name=telephone]").val(json.telephone);
			$("#formCustomer textarea[name=address]").val(json.address);
			$("#customerType").combobox('setValue',json.customerType); 
			$("#customerSex").combobox('setValue',json.customerSex);
			$("#formCustomer input[name=memberNum]").val(json.memberNum);
			$("#province").combobox('setValue',json.province);
			$("#formCustomer input[name=areaCode]").val(json.areaCode);
			$("#formCustomer input[name=email]").val(json.EMail);
			$("#formCustomer input[name=postcode]").val(json.postcode);
			$("#formCustomer textarea[name=note]").val(json.note);
		});
	}
	
	function continueSave(){
		$("#continueToAddFlag").val("1");	
		save();
	}
	
	function goBack() {
		window.location.href="${ctx}/serviceTicketCreate/serviceTicketCreateView";
	}
	
    $(document).keydown(function(event){
    	if(event.keyCode==13){
    		query();
    	}
    }); 
	
</script>
<form id="formSearch" method="post">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">查询条件</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td width="20%">姓名</td>
			<td width="30%"><input id="queryName" name="customerName" type="text" placeholder="请输入客户名称" class="easyui-validatebox span2" value=""></td>
			<td width="20%">联系电话</td>
			<td width="20%"><input id="queryphone" name="phone" type="text" placeholder="请输入联系电话" class="easyui-validatebox span2" value=""></td>
			<td width="10%" rowspan="3" align="center">
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="query();">查询</a>
				<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
			</td>
		</tr>
		<tr>
			<td>机器条码</td>
			<td><input id="queryCode" name="machineCode" type="text" placeholder="请输入机器条码" class="easyui-validatebox span2" value=""></td>
			<td>安装卡号</td>
			<td><input id="queryCard" name="installNum" type="text" placeholder="请输入安装卡号" class="easyui-validatebox span2" value=""></td>
			<td></td>
		</tr>
		<tr>
			<td>地址</td>
			<td><input id="queryAddress" name="address" type="text" placeholder="请输入客户地址" class="easyui-validatebox span2" value=""></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</form>
<br/>
<form id="formCustomer" method="post">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">客户信息</div>
	<input id="customerId" name="customerId" type="hidden">
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td width="20%">客户姓名<font color="red">*</font></td>
			<td width="30%"><input name="customerName" type="text"   class="easyui-validatebox span2" value="${CustomerProduct.khmc}" data-options="required:true"></td>
			<td width="20%">客户类别<font color="red">*</font></td>
			<td width="30%">
				<input class="easyui-combobox" id="customerType" name="customerType" data-options="url:'${ctx}/hxCode/getCombobox/khlb',panelHeight:'auto', editable:false,required:true"/>
			</td>
		</tr>
		<tr>
			<td>性别</td>
			<td>
				<input id="customerSex" name="customerSex" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/xb',panelHeight:'auto', editable:false"/>
			</td>
			<td>会员卡号</td>
			<td><input name="memberNum"  type="text"  class="easyui-validatebox span2" value=""></td>
		</tr>
		<tr>
			<td>省份<font color="red">*</font></td>
			<td><input id="province" name="province" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',panelHeight:'200', editable:true,required:true, hasDownArrow:false,mode:'remote'"/></td>
			<td>区号</td>
			<td><input name="areaCode" type="text"  class="easyui-validatebox span2" value=""></td>
		</tr>
		<tr>
			<td>手机</td>
			<td><input name="phone" type="text"   class="easyui-validatebox span2" value="${CustomerProduct.lxdh }"></td>
			<td>固定电话</td>
			<td><input name="telephone" type="text"   class="easyui-validatebox span2" value="${CustomerProduct.qtdh}"></td>
		</tr>
		<tr>
			<td>E-MAIL</td>
			<td><input name="email" type="text" data-options="validType:'email'"  class="easyui-validatebox span2" value=""></td>
			<td>邮编</td>
			<td><input name="postcode" type="text"   class="easyui-validatebox span2" value=""></td>
		</tr>
		<tr>
			<td>联系地址<font color="red">*</font></td>
			<td colspan="3"><textarea name="address" rows="2"  cols="80" class="easyui-validatebox span2" data-options="required:true">${CustomerProduct.lxdz}</textarea></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan="3"><textarea name="note"  rows="2" cols="80" class="easyui-validatebox span2" ></textarea></td>
		</tr>
	</table> 
</form>
<br/>
<form id="formService" method="post">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">服务单</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td  width="20%">品类<font color="red">*</font></td>
			<td  width="30%">
				<input class="easyui-combobox" name="category" data-options="url:'${ctx}/hxCode/getCombobox/wxpl',panelHeight:'auto', editable:false,required:true"/>
			</td>
			<td  width="20%">服务类型<font color="red">*</font></td>
			<td  width="30%">
				<input class="easyui-combobox" name="serviceType" data-options="url:'${ctx}/hxCode/getCombobox/djlx',panelHeight:'auto', editable:false,required:true"/>
			</td>
		</tr>
		<tr>
			<td>紧急度<font color="red">*</font></td>
			<td>
				<input class="easyui-combobox" name="urgencyDegree" data-options="url:'${ctx}/hxCode/getCombobox/jjd',panelHeight:'auto', editable:false,required:true"/>
			</td>
			<td>预约时间段<font color="red">*</font></td>
			<td>
				<input class="easyui-combobox" name="appointmentQuantum" data-options="url:'${ctx}/hxCode/getCombobox/sjd',panelHeight:'auto', editable:false,required:true"/>
			</td>
		</tr>
		<tr>
			<td>联系人</td>
			<td><input id="linkMan" name="linkMan" type="text" class="easyui-validatebox span2"></td>
			<td>预约日期</td>
			<td>
				<input id="appointmentDate" name="appointmentDate" type="text" readonly="true">
				<img onclick="WdatePicker({el:'appointmentDate'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
			</td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td><input id="linkPhone" name="linkPhone" type="text" class="easyui-validatebox span2"></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>客户描述</td>
			<td colspan="3"><textarea id="customerDescribe" name="customerDescribe" rows="2" cols="60"></textarea></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan="3"><textarea id="commentS" name="commentS" rows="2" cols="60"></textarea></td>
		</tr>
	</table> 
<div style="text-align:right;padding:5px">
   	<a href="#" class="easyui-linkbutton"  onclick="save();">保存</a>
</div>
</form>
<div class="easyui-layout" data-options="fit : true,border : false">
	<table id="serviceGrid"></table>
</div>

