<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		$('#dataGrid').datagrid({
			url : "${ctx}/hxMaterial/getHxMaterialDetailForUpdate/${m.listNumber}?type=" + $("#authId").val(),
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			columns : [ [ {
				width : 10,
				checkbox : true
			}, {
				field : 'suitModel',
				title : '适用机型',
				align:'center',
				editor:{
					type:'combobox',
					options:{
						url:'${ctx}/hxCode/getCombobox/jx',
						onSelect:function(){
							var currentRowNo = parseInt($(this).parents('tr[id^=datagrid-row-r1-2-]').attr("id").replace("datagrid-row-r1-2-",""));
				            var fittingCode = $('#dataGrid').datagrid('getEditor', {index:currentRowNo,field:'fittingCode'});
				            var url = '${ctx}/hxMaterial/getFittingBySuit?suitModel=' + window.encodeURIComponent($(this).combo("getValue"));
				            $(fittingCode.target).combobox('reload', window.encodeURI(url));
						},
						onChange:function(newValue,oldValue){  
							if(newValue == undefined) return;
							if(oldValue != undefined && oldValue != ""){
								var currentRowNo = parseInt($(this).parents('tr[id^=datagrid-row-r1-2-]').attr("id").replace("datagrid-row-r1-2-",""));
								var fittingCode = $('#dataGrid').datagrid('getEditor', {index:currentRowNo,field:'fittingCode'});
								$(fittingCode.target).combo('clear', '${ctx}/hxMaterial/getFittingBySuit?suitModel=XXX');
								eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=fittingName]');").children("div").text("");
				            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=spec]');").children("div").text("");
				            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=price]');").children("div").text("");
				            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=stock]');").children("div").text("");
							}
						}
					}
				},
				width : 200
			},{
				field : 'fittingCode',
				title : '配件编码',
				align :'center',
				editor:{
					type:'combobox',
					options:{
						onSelect:function(value){
				            var currentRowNo = parseInt($(this).parents('tr[id^=datagrid-row-r1-2-]').attr("id").replace("datagrid-row-r1-2-",""));
				            $.post("${ctx}/hxMaterial/getFittingInfo/" + value.text + "?authId=" + $("#authId").val(),{
				            },function(msg){
				            	var obj = $.parseJSON(msg);
				            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=fittingName]');").children("div").text(obj.fittingName);
				            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=spec]');").children("div").text(obj.spec);
				            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=price]');").children("div").text(obj.price);
				            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=stock]');").children("div").text(obj.stock);
				            });
						}
					}
				},
				width : 160
			},{
				field : 'fittingName',
				title : '配件名称',
				align :'center',
				width : 300
			},{
				field : 'spec',
				title : '规格',
				align : 'center',
				width : 200
			},{
				field : 'price',
				title : '价格',
				align : 'center',
				width : 100/*,
				formatter: function(value, row, index) {
					return getListStockPrice(row.fittingCode);
				}*/
			},{
				field : 'stock',
				title : '当前库存',
				align : 'center',
				width : 100
			},{
				field : 'applyCount',
				title : '申请数量',
				align : 'center',
				editor: 'text',
				width : 100,
				editor:'numberbox'
			}, {
				field : 'comment',
				title : '备注',
				align : 'center',
				editor: 'text',
				width : 300
			} ] ],
			toolbar : '#toolbarFitting',
			onLoadSuccess : function(data){
				
				indexMaterial = data.total;
				for(var i = 0; i < indexMaterial; i++){
					$('#dataGrid').datagrid('beginEdit', i);
					var partsCode = $('#dataGrid').datagrid('getEditor', {index:i,field:'fittingCode'});
					var s = $("#dataGrid").datagrid('getRows')[i]['suitModel'];
		            var url = '${ctx}/hxMaterial/getFittingBySuit?suitModel=' + window.encodeURIComponent(s);
					$(partsCode.target).combobox('reload', window.encodeURI(url));
				}
			}
		});
		
		$("div.easyui-layout").css("height", "auto");
	});
	
	function getListStockPrice(fittingCode){
		if(fittingCode==undefined){
			return;//添加，删除datagrid行时
		}
		var result="";
		$.ajax({
             url: "${ctx}/hxMaterial/getListStockPrice?listNumber=" + "${m.listNumber}" + "&fittingCode=" + fittingCode+"&authId="+$("#authId").val(),
             data: {},
             async:false,
             success: function(data){
            	 result=data;
              }
         });
		 return result;
	}
	
	function add(){
		var count = $("#numberFitting").val();
		for(var i = 0; i < count; i++){
			$('#dataGrid').datagrid('appendRow',{});
			$('#dataGrid').datagrid('beginEdit', indexMaterial);
			indexMaterial ++;
		}
		$("td[field=suitModel] span.combo-arrow").css("display","none");
		$("td[field=suitModel] input.combo-text").css("width", $('td[field=suitModel] span.combo').css('width'));
	}
	
	function del(){
		var checkedData = $('#dataGrid').datagrid('getRowNum');
		indexMaterial = indexMaterial - checkedData.length;
		$.each(checkedData, function(){
			$('#dataGrid').datagrid('deleteRow', checkedData.pop() - 1);
		});
	}
	
	function back(){
		window.location.href = "${ctx}/hxMaterial/hxMaterialView";
	}
	
	function save(){
		var authId = $("#authId").val();
		if(authId == "fb-n-db"){
			if ($("#sendCompany").combobox('getValue') == ""){
				alert('请选择发货单位');
				return;
			}
		}
		$('#dataGrid').datagrid('acceptChanges');
		var rows = $("#dataGrid").datagrid('getRows');
		for(var i = rows.length - 1; i >= 0; i--){
			if($.trim(rows[i].suitModel) == ""){
				rows.splice(i, 1);
			}
		}
		
		var materials = new Array();
		for(var i = 0; i < rows.length; i++){
			var obj = {};
			if(rows[i].applyCount =="" || rows[i].applyCount == 0){
				$.messager.alert('警告','申请数量有误, 请点击第'+(i+1)+' 行进行修改！','warning');
				$('#dataGrid').datagrid('rejectChanges');
				for(var j = 0; j < rows.length; j++){
					$('#dataGrid').datagrid('beginEdit', j);
					var partsCode = $('#dataGrid').datagrid('getEditor', {index:j,field:'fittingCode'});
					var s = $("#dataGrid").datagrid('getRows')[j]['suitModel'];
					var f = $("#dataGrid").datagrid('getRows')[j]['fittingCode'];
		            var url = '${ctx}/hxMaterial/getFittingBySuit?suitModel=' + window.encodeURIComponent(s);
					$(partsCode.target).combobox('reload', window.encodeURI(url));
					var currentRowNo = j;//第几行
		            var move="";
		            if($("#authId").val() == "fb-kc-zy"){
		    			if ($("#moveDirection").combobox('getValue') == ""){
		    				$.messager.alert('警告','请选择良品，残品转移方向');
		    				return;
		    			}else{
		    				move = $("#moveDirection").combobox('getValue');
		    			}
		    		}
		            setFittingInfo(currentRowNo,  f, move);
				}
				return;
			}
			obj.fittingCode = rows[i].fittingCode;
			obj.suitModel = rows[i].suitModel;
			obj.listNumber = $("#listNumber").val();
			obj.applyCount = rows[i].applyCount;
			obj.comment = rows[i].comment;
			obj.price = rows[i].price;
			materials.push(obj);
		}
		
		var hxMaterial = {};
		hxMaterial.authId = $("#authId").val();
		hxMaterial.manage = $.serializeObject($('#form'));
		hxMaterial.detail = materials;
		
		$.ajax({ 
            type:"POST", 
            url:"${ctx}/hxMaterial/saveHxMaterial", 
            dataType:"json",      
            contentType:"application/json",               
            data:JSON.stringify(hxMaterial), 
            success:function(msg){
            	if(msg.flag == "success"){
	            	parent.$.messager.alert('','修改成功!',null,function(){
            			window.location.href = "${ctx}/hxMaterial/viewHxMaterial?listNumber=" + msg.listNumber + "&type=" + $("#authId").val();
	            	});
            	}else{
            		parent.$.messager.alert('','修改失败!');
            	}
            } 
        }); 
	}
	
	function setFittingInfo(currentRowNo, text, move){
		$.ajax({
			url:"${ctx}/hxMaterial/getFittingInfo/" + text + "?authId=" + $("#authId").val()+"&move="+move,
			data:{},
			async:false,
			success:function(msg){
	        	var obj = $.parseJSON(msg);
	//             var ed = $('#dataGrid').datagrid('getEditor', {  
	//                     index : currentRowNo,  
	//                     field : 'price'  
	//                 });  
	//             $(ed.target).numberbox('setValue', obj.price);  
	//             $('#dataGrid').datagrid('getEditor', {  
	//                 index : currentRowNo,  
	//                 field : 'price'  
	//             }).disable();
	//             $('#dataGrid').datagrid('getEditor', {  
	//                 index : currentRowNo,  
	//                 field : 'price'  
	//             }).disabled();
				if(obj.flag == 'success'){
	            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=fittingName]');").children("div").text(obj.fittingName);
	            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=spec]');").children("div").text(obj.spec);
	            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=price]');").children("div").text(obj.price);
	            	eval("$('tr[id=datagrid-row-r1-2-" + currentRowNo + "] td[field=stock]');").children("div").text(obj.stock);
				}else if(obj.flag == 'failure'){
					$.messager.alert('警告','此配件信息有误！请联系管理员');    
				}
			}
        });
	}
	
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
</script>
<form id="form" method="post">
	<input id="authId" type="hidden" value="${type}">
	<input id="listNumber" type="hidden" value="${m.listNumber}">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
		修改${title}
	</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td width="25%" align="center">单据类型<font color="red">*</font></td>
			<td width="25%">${title}</td>
			<c:if test="${type=='fb-n-db'}">
			<td width="25%" align="center">发货单位<font color="red">*</font></td>
			<td width="25%"><input class="easyui-combobox" name="sendCompany" id="sendCompany" data-options="url:'${ctx}/hxCode/getFbOrgCombobox?value=${m.sendCompany}'"/></td>
		</tr>
		<tr>
<!-- 			<td width="25%" align="center">收货单位<font color="red">*</font></td> -->
<%-- 			<td width="25%"><input class="easyui-combobox" name="receiveCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox?value=${m.receiveCompany}'"/></td> --%>
			</c:if>
			<td width="25%" align="center">手工单号</td>
			<td width="25%"><input name="listNumber" readonly="readonly" value="${m.listNumber}" type="text"></td>
		</tr>
		<tr>
			<td width="25%" align="center">申请人</td>
			<td width="25%"><input name="proposer" type="text" placeholder="请输入申请人" value="${m.proposer}"></td>
			<td width="25%" align="center">申请日期</td>
			<td width="25%"><input id="applyDate" name="applyDate" type="text" placeholder="请选择申请日期" data-options="required:false" value='<fmt:formatDate value="${m.applyDate}" pattern="yyyy-MM-dd"/>' readonly="true">
							<img onclick="WdatePicker({el:'applyDate'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
		</tr>
			<c:if test="${type=='wd-yjjh-sq'}">
		<tr>
			<td width="25%" align="center">客户姓名<font color="red">*</font></td>
			<td width="25%"><input name="customerName" type="text" placeholder="请输入客户姓名" value="${m.customerName}"></td>
			<td width="25%" align="center">客户电话<font color="red">*</font></td>
			<td width="25%"><input name="customerTel" type="text" placeholder="请输入客户电话" value="${m.customerTel}"></td>
		</tr>
		<tr>
			<td width="25%" align="center">地址<font color="red">*</font></td>
			<td width="25%" colspan="3"><input name="address" type="text" placeholder="请输入地址" value="${m.address}"></td>
		</tr>
			</c:if>
		<tr>
			<td width="25%" align="center">备注</td>
			<td width="25%" colspan="3"><textarea name="comment" placeholder="请输入备注" rows="3" cols="52">${m.comment}</textarea></td>
		</tr>
	</table>
</form>
<div>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="dataGrid"></table>
	</div>
</div>
<div id="toolbarFitting" style="display: none;">
	<input type="text" id="numberFitting" style="width: 20px;" value="1" type="hidden">
	<a href="javascript:void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true" onclick="del();">删除</a>
</div>
<div align="right" style="padding:5px;">
	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-save'" onclick="save();">保存</a>
	<a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="back();">返回</a>
</div>
<script type="text/javascript">
        $.extend($.fn.datagrid.methods, {
            editCell: function(jq,param){
                return jq.each(function(){
                    var opts = $(this).datagrid('options');
                    var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
                    for(var i=0; i<fields.length; i++){
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor1 = col.editor;
//                         if (fields[i] != param.field){
//                             col.editor = null;
//                         }
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
            if ($('#dataGrid').datagrid('validateRow', editIndex)){
                $('#dataGrid').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickCell(index, field){
            if (endEditing()){
                $('#dataGrid').datagrid('selectRow', index)
                        .datagrid('editCell', {index:index,field:field});
                editIndex = index;
            }
        }
    </script>