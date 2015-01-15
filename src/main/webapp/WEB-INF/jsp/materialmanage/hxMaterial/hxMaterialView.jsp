<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		$('#dataGrid').datagrid({
			title : "配件明细",
			url : "${ctx}/hxMaterial/getHxMaterialDetails/${m.listNumber}",
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
				field : 'spec',
				title : '规格',
				align : 'center',
				styler: cellStyler,
				width : 300
			}, {
				field : 'price',
				title : '单价',
				align : 'center',
				styler: cellStyler,
				width : 300
			}, {
				field : 'applyCount',
				title : '申请数',
				align:'center',
				width : 300,
				editor:'numberbox'
			}, {
				field : 'auditCount',
				title : '审批',
				align:'center',
				width : 300
			}/*, {
				field : 'receive_count',
				title : '接收',
				align:'center',
				width : 300
			}, {
				field : 'surplus_count',
				title : '剩余',
				align:'center',
				width : 300
			}*/, {
				field : 'comment',
				title : '备注',
				align:'center',
				width : 300
			} ] ]
		});
		
		$("div.datagrid-wrap").css("height","auto");
		
		function cellStyler(value,row,index){
			if(row.spec=="总计（元）："){
				return 'background-color:#ffee00;color:red;';
			}
		}
	});
	
	function send(){
		$.post("${ctx}/hxMaterial/hxMaterialSend",{
			listNumber : $("#listNumber").val(),
			type: $("#authId").val()
		},function(msg){
			data = $.parseJSON(msg);
			if (data.flag ==  "success") {
				parent.$.messager.alert('成功', '报批成功', 'info',function(){
					window.location.href = "${ctx}/hxMaterial/hxMaterialView";
				})
			} else {
				parent.$.messager.alert('报批失败!',decodeURI(data.info), 'error');
			}
		});
	}
	
	function sure(){
		$('#dataGrid').datagrid('acceptChanges');
		var rows = $("#dataGrid").datagrid('getRows');
		for(var i = rows.length - 1; i >= 0; i--){
			if($.trim(rows[i].fitting_code) == ""){
				rows.splice(i, 1);
			}
		}
		
		var materials = new Array();
		for(var i = 0; i < rows.length; i++){
			var obj = {};
			obj.fittingCode = rows[i].fitting_code;
			obj.applyCount = rows[i].apply_count;
			materials.push(obj);
		}
		
		var hxMaterial = {};
		var manage = {};
		hxMaterial.authId = $("#authId").val();
		manage.sendCompany = $("#sendCompany").combo("getValue");
		manage.receiveCompany = $("#receiveCompany").combo("getValue");
		manage.listNumber = $("#listNumber").val();
		hxMaterial.manage = manage;
		hxMaterial.detail = materials;
		
		$.ajax({ 
            type:"POST", 
            url:"${ctx}/hxMaterial/hxMaterialSure", 
            dataType:"json",      
            contentType:"application/json",               
            data:JSON.stringify(hxMaterial), 
            success:function(msg){
            	if(msg.flag == "success"){
            		window.location.href = "${ctx}/hxMaterial/hxMaterialView";
            	}else{
            		parent.$.messager.alert('','确认失败!');
            	}
            } 
        }); 
	}
	
	function update(){
		window.location.href = "${ctx}/hxMaterial/updateHxMaterial?listNumber=" + $("#listNumber").val() + "&type=" + $("#authId").val();
	}
	
	function back(){
		window.location.href = "${ctx}/hxMaterial/hxMaterialView";//返回到配件申请列表页面
// 		window.history.go(-1);
	}
       $.extend($.fn.datagrid.methods, {
           editCell: function(jq,param){
               return jq.each(function(){
                   var opts = $(this).datagrid('options');
                   var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
                   for(var i=0; i<fields.length; i++){
                       var col = $(this).datagrid('getColumnOption', fields[i]);
                       col.editor1 = col.editor;
                       if (fields[i] != param.field){
                           col.editor = null;
                       }
                   }
                   $(this).datagrid('beginEdit', param.index);
                   for(var i=0; i<fields.length; i++){
                       var col = $(this).datagrid('getColumnOption', fields[i]);
                       col.editor = col.editor1;
                   }
               });
           }
       });
       
       var editIndex = undefined;
       function endEditing(){
           if (editIndex == undefined){return true}
           if ($('#dg').datagrid('validateRow', editIndex)){
               $('#dg').datagrid('endEdit', editIndex);
               editIndex = undefined;
               return true;
           } else {
               return false;
           }
       }
       function onClickCell(index, field){
           if (endEditing()){
               $('#dg').datagrid('selectRow', index)
                       .datagrid('editCell', {index:index,field:field});
               editIndex = index;
           }
       }
       
       function printMaterialDetail(){
			var iWidth=850; //弹出窗口的宽度;
			var iHeight=850; //弹出窗口的高度;
			var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
			window.open('${ctx }/HxJasperReport/print?type=3&id=${m.listNumber}',"","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft+"toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no"); 
	}
</script>
<form id="form" method="post">
	<input id="authId" type="hidden" value="${type}">
	<input id="listNumber" type="hidden" value="${m.listNumber}">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
		详细查看${title}
	</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td width="25%" align="center">单据编号</td>
			<td width="25%">${m.listNumber}</td>
			<td width="25%" align="center">单据类型</td>
			<td width="25%">${title}</td>
		</tr>
			<c:if test="${type=='fb-kc-zy'}">
		<tr>
			<td width="25%" align="center">良品,残品转移</td>
			<c:if test="${m.moveDirection =='0' }">
				<td width="25%">良品转残品</td>
			</c:if>
			<c:if test="${m.moveDirection =='1' }">
				<td width="25%">残品转良品</td>
			</c:if>
		</tr>
			</c:if>
			<c:if test="${type != 'zb-n-cg' }">
			<tr>
				<td width="25%" align="center">发货单位</td>
				<td width="25%"><input id="sendCompany" class="easyui-combobox" name="sendCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox?value=${m.sendCompany}'" disabled="disabled"/></td>
				<td width="25%" align="center">收货单位</td>
				<td width="25%"><input id="receiveCompany" class="easyui-combobox" name="receiveCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox?value=${m.receiveCompany}'" disabled="disabled"/></td>
			</tr>
		</c:if>
		<tr>
			<td width="25%" align="center">状态</td>
			<td width="25%"><input class="easyui-combobox" name="status" data-options="url:'${ctx}/hxCode/getCombobox/djzt?value=${m.status}'" disabled="disabled"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">申请人</td>
			<td width="25%">${m.proposer}</td>
			<td width="25%" align="center">申请日期</td>
			<td width="25%"><fmt:formatDate value="${m.applyDate}" pattern="yyyy-MM-dd"/></td>
		</tr>
			<c:if test="${type=='wd-yjjh-sq'}">
		<tr>
			<td width="25%" align="center">客户姓名</td>
			<td width="25%">${m.customName}</td>
			<td width="25%" align="center">客户电话</td>
			<td width="25%">${m.customTel}</td>
		</tr>
		<tr>
			<td width="25%" align="center">地址</td>
			<td width="25%" colspan="3">${m.address}</td>
		</tr>
			</c:if>
		<tr>
			<td width="25%" align="center">备注</td>
			<td width="25%" colspan="3">${m.comment}</td>
		</tr>
		<tr>
			<td width="25%" align="center">创建人</td>
			<td width="25%">${m.createPerson}</td>
			<td width="25%" align="center">创建时间</td>
			<td width="25%"><fmt:formatDate value="${m.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
		</tr>
		<tr>
			<td width="25%" align="center">修改人</td>
			<td width="25%">${m.updatePerson}</td>
			<td width="25%" align="center">修改时间</td>
			<td width="25%"><fmt:formatDate value="${m.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
	</table>
</form>
<div align="right" class="easyui-panel" style="padding:5px;">
	<c:if test="${type != 'zb-n-cg' }">
		<c:if test="${isEdit=='true'}">
			<c:if test="${isSend=='true'}">
				<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g2',plain:true,iconCls:'icon-redo'" onclick="send();">报批</a>
			</c:if>
			<c:if test="${isSend=='false'}">
				<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g2',plain:true,iconCls:'icon-ok'" onclick="sure();">确认</a>
			</c:if>
			<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g2',plain:true,iconCls:'icon-edit'" onclick="update();">修改</a>
		</c:if>
	</c:if>
	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g2',plain:true,iconCls:'icon-back'" onclick="back();">返回</a>
	<a id="print" href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'"  onclick="printMaterialDetail();">打印</a>	
</div>
<div class="easyui-layout">
	<table id="dataGrid"></table>
</div>