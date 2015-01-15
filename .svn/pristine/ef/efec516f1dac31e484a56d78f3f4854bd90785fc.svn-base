<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">

	$(function(){
		$('#progressGrid').datagrid({
			url : "${ctx}/progress/getProgress/${map.service_id}",
			title : "服务进度",
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [
			   {field : 'serviceId',title : '服务单号',align:'center',width : 30 ,hidden:true
			}, {field : 'progressDescription',title : '进程描述',editor:'text',align:'center',width : 30
			}, {field : 'recorder',title : '记录人',align:'center',width : 30
			}, {field : 'recordTime',title : '记录时间',align:'center',width : 30
			} ] ],
			toolbar : '#toolbarForProgress',
			onLoadSuccess : function(data){
				indexProgress = data.total;
			}
		});
	});
	
	var indexProgress = 0;
	
	function addForProgress(){
		var count = $("#numberForProgress").val();
		for(var i = 0; i < count; i++){
			$('#progressGrid').datagrid('appendRow',{ serviceId:'${map.service_id}'});
			$('#progressGrid').datagrid('beginEdit', indexProgress);
			indexProgress ++;
		}
	}
	
	function deleteForProgress(){
		$('#progressGrid').datagrid("reload");	
		/* var checkedData = $('#progressGrid').datagrid('getRowNum');
		indexProgress = indexProgress - checkedData.length;
		$.each(checkedData, function(){
			$('#progressGrid').datagrid('deleteRow', checkedData.pop() - 1);
		}); */
	}
	
	function add(){
		$.messager.confirm("", "您确定要保存吗？", function (data) {
    		if(data){
    			$('#progressGrid').datagrid('acceptChanges');
    			var progress = $("#progressGrid").datagrid('getRows');
    			for(var i = progress.length - 1; i >= 0; i--){
    				if($.trim(progress[i].progressDescription) == "" || progress[i].recordTime!=null){
    					progress.splice(i, 1);
    				}
    			} 
    			var serviceTicketVO = {};
    			serviceTicketVO.serviceProgressInfos = progress;
    			$.ajax({
    	            type:"POST", 
    	            url:"${ctx}/serviceTicketCheck/addProgress", 
    	            dataType:"json",      
    	            contentType:"application/json",               
    	            data:JSON.stringify(serviceTicketVO), 
    	            success:function(data){
    	            	if(data.flag == "success"){
    	            		$.messager.alert('', '保存成功!',null,function(){
    	            			$('#progressGrid').datagrid("reload");
    	            		});
    	            	}else{
    	            		$.messager.alert('','新增失败!');
    	            		$('#progressGrid').datagrid("reload");
    	            	}
    	            } 
    	        });
    			
    		}
    	});
	}
	
	function update(){
		window.location.href="${ctx}/serviceTicketQuery/updateServiceTicketView/${map.service_id}";
	}
	
	function send(){
		window.location.href="${ctx}/serviceTicketQuery/serviceTicketSendView/${map.service_id}";
	}
	
	function back(){
		window.location.href="${ctx}/serviceTicketQuery/serviceTicketQueryView";
	}
	
</script>
<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">客户信息</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="20%">客户姓名<font color="red">*</font></td>
		<td width="30%"><label>${map.customer_name }</label></td>
		<td width="20%">客户类别<font color="red">*</font></td>
		<td width="30%">
			<input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/khlb?value=${map.customer_type}',panelHeight:'auto', editable:false"/>
		</td>
	</tr>
	<tr>
		<td>性别</td>
		<td>
			<input class="easyui-combobox" disabled="disabled" data-options="url:'${ctx}/hxCode/getCombobox/xb?value=${map.customer_sex}',panelHeight:'auto', editable:false"/>
		</td>
		<td>会员卡号</td>
		<td><label>${map.member_num }</label></td>
	</tr>
	<tr>
		<td>省份<font color="red">*</font></td>
		<td>
			<input value="${map.province}" disabled="disabled" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getRegionCombobox',panelHeight:'200', editable:true,required:true"/>
		</td>
		<td>区号</td>
		<td><label>${map.area_code }</label></td>
	</tr>
	<tr>
		<td>手机</td>
		<td><label>${map.phone }</label></td>
		<td>固定电话</td>
		<td><label>${map.telephone }</label></td>
	</tr>
	<tr>
		<td>E-MAIL</td>
		<td><label>${map.e_mail }</label></td>
		<td>邮编</td>
		<td><label>${map.postcode }</label></td>
	</tr>
	<tr>
		<td>联系地址<font color="red">*</font></td>
		<td colspan="3"><label>${map.address }</label></td>
	</tr>
	<tr>
		<td>备注</td>
		<td colspan="3"><label>${map.note }</label></td>
	</tr>
</table>
<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">服务单信息</div>
<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
	<tr>
		<td width="20%">服务单号<font color="red">*</font></td>
		<td width="30%">
			<label>${map.service_id }</label>
		</td>
		<td width="20%">服务类型<font color="red">*</font></td>
		<td width="30%">
			<input class="easyui-combobox" disabled="disabled" value="${map.service_type }" data-options="url:'${ctx}/hxCode/getCombobox/djlx',panelHeight:'auto', editable:false"/>
		</td>
	</tr>
	<tr>
		<td>紧急度<font color="red">*</font></td>
		<td>
			<input class="easyui-combobox" disabled="disabled" value="${map.urgency_degree }" data-options="url:'${ctx}/hxCode/getCombobox/jjd',panelHeight:'auto', editable:false"/>
		</td>
		<td>预约时间段<font color="red">*</font></td>
		<td>
			<input class="easyui-combobox" disabled="disabled" value="${map.appointment_quantum }" data-options="url:'${ctx}/hxCode/getCombobox/sjd',panelHeight:'auto', editable:false"/>
		</td>
	</tr>
	<tr>
		<td>联系人</td>
		<td><label>${map.link_man }</label></td>
		<td>预约日期</td>
		<td><label><fmt:formatDate value='${map.appointment_date }' pattern='yyyy-MM-dd'/></label></td>
	</tr>
	<tr>
		<td>联系电话</td>
		<td colspan="3" ><label>${map.link_phone }</label></td>
	</tr>
	<tr>
		<td>客户描述</td>
		<td colspan="3"><label>${map.customer_describe }</label></td>
	</tr>
	<tr>
		<td>备注</td>
		<td colspan="3"><label>${map.comment_s }</label></td>
	</tr>
</table>
<div style="text-align:right;padding:5px">
    <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="update();">修改</a>
    <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="send();">发送</a>
    <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="back();">返回</a>
</div>
<div class="easyui-layout" data-options="fit : true,border : false">
	<table id="progressGrid"></table>
</div>
<div id="toolbarForProgress">
	<input type="text" id="numberForProgress" hidden="true" style="width: 20px;" value="1">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addForProgress();">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteForProgress();">取消</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="add();">保存</a>
</div>