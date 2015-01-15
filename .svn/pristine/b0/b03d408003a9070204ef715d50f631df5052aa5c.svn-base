<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		if('${type}'=='zb-n-cg'){
			$("#toolbarFitting").css('display','none'); 
		}
		$('#dataGrid').datagrid({
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
				            var move="";
				            if($("#authId").val() == "fb-kc-zy"){
				    			if ($("#moveDirection").combobox('getValue') == ""){
				    				$.messager.alert('警告','请选择良品，残品转移方向');
				    				return;
				    			}else{
				    				move = $("#moveDirection").combobox('getValue');
				    			}
				    		}
				            setFittingInfo(currentRowNo, value.text, move);
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
				width : 150
			},{
				field : 'price',
				title : '价格',
				align : 'center',
				width : 100
			},{
				field : 'stock',
				title : '当前库存',
				align : 'center',
				width : 100
			},{
				field : 'applyCount',
				title : '申请数量',
				align : 'center',
				editor: 'numberbox',
				width : 100,
			}, {
				field : 'comment',
				title : '备注',
				align : 'center',
				editor: 'text',
				width : 300
			} ] ],
			toolbar : '#toolbarFitting'
		});
		/*
		dataGrid2 = $('#dataGrid2').datagrid(
	             {
	                 title : '我的工作列表',
	                 url : '${ctx}/hxMaterial/getHxMaterialDetail3/'+number;
	                 nowrap : false,
	     			striped : true,
	     			height : document.body.clientHeight - 70,
	     			autoRowHeight : false,
	     			remoteSort : false,
	     			rownumbers : true,
	     			singleSelect : true,
	     			fitColumns : true,
	     			pagination : true,
	     			checkOnSelect : false,
	     			selectOnCheck : false,
	                 queryParams : {
	     				currentPage : 1,
	     				pageCount : 10
	     			},
	     			columns : [[{field : 'suitModel',	title  : '适应机型',width : 25,	align:'center'},
	    				{field : 'fittingCode',title : '配件编码',width : 25,	align:'center'},
	    				{field : 'fittingName',title : '配件名称',width : 25,	align:'center'},
	    				{field : 'spec',		title  : '规格',width : 25,	align:'center'},"
	    				{field : 'price', 		title  : '价格',width:25,align:'center'}," +
	    				{field : 'applyCount',title  : '申请数量',width : 25,	align:'center'},"
	    				{field : 'comment', title : '注释',width : 20, align:'center'}
	    		]],
				loadMsg : "数据加载中...",
				onLoadSuccess : function(data) {
					alert(data);
				}
				});*/
		$("div.easyui-layout").css("height", "auto");
	});
	
	var indexFitting = 0;
	
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
	function add(){
		var count = $("#numberFitting").val();
		for(var i = 0; i < count; i++){
			$('#dataGrid').datagrid('appendRow',{});
			$('#dataGrid').datagrid('beginEdit', indexFitting);
			indexFitting ++;
		}
		$("td[field=suitModel] span.combo-arrow").css("display","none");
		$("td[field=suitModel] input.combo-text").css("width", $('td[field=suitModel] span.combo').css('width'));
	}
	
	function del(){
		var checkedData = $('#dataGrid').datagrid('getRowNum');
		indexFitting = indexFitting - checkedData.length;
		$.each(checkedData, function(){
			$('#dataGrid').datagrid('deleteRow', checkedData.pop() - 1);
		});
	}
	
	function back(){
		window.location.href = "${ctx}/hxMaterial/hxMaterialView";
	}
	
	function save(){
		var authId = $("#authId").val();
		var move ="";
		if(authId == "fb-kc-zy"){
			if ($("#moveDirection").combobox('getValue') == ""){
				$.messager.alert('警告', '请选择良品，残品转移方向！','warning');
				return;
			}else{
				move = $("#moveDirection").combobox('getValue');
			}
		}
		if(authId == "fb-n-db"){
			var sendCompany = $("#sendCompany").combobox('getValue');
			var c = "${sessionScope.user.companyId}";
			if (sendCompany == ""){
				$.messager.alert('警告', '请选择发货单位！','warning');
				return;
			}else if(sendCompany==c){
				$.messager.alert('警告', '发货单位不得与当前用户的所在机构相同！', 'warning');
				return;
			}
		}
		if(authId == "zb-n-cg"){
			if ($("#relatedListNumber").val() == ""){
				$.messager.alert('警告', '请输入分部申请单号');
				return;
			}
		}
		$('#dataGrid').datagrid('acceptChanges');
		var materials = $("#dataGrid").datagrid('getRows');
		for(var i = materials.length - 1; i >= 0; i--){
			delete materials[i]['spec'];
			delete materials[i]['fittingName']
			if(materials[i].applyCount =="" || materials[i].applyCount == 0){
				$.messager.alert('警告','申请数量有误, 请点击第'+(i+1)+' 行进行修改！','warning');
				$('#dataGrid').datagrid('rejectChanges');
				//
				indexMaterial = materials;
				for(var j = 0; j < indexMaterial.length; j++){
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
			if($.trim(materials[i].suitModel) == ""){
				materials.splice(i, 1);
			}
		}
		var hxMaterial = {};
		hxMaterial.authId = $("#authId").val();
		hxMaterial.manage = $.serializeObject($('#form'));
		hxMaterial.detail = materials;
		if(move != ""){
			hxMaterial.moveDirection = move;
		}
		$.ajax({ 
            type:"POST", 
            url:"${ctx}/hxMaterial/addHxMaterial", 
            dataType:"json",      
            contentType:"application/json",               
            data:JSON.stringify(hxMaterial), 
            success:function(msg){
            	if(msg.flag == "success"){
	            	parent.$.messager.alert('','新增成功!',null,function(){
            			window.location.href = "${ctx}/hxMaterial/viewHxMaterial?listNumber=" + msg.listNumber + "&type=" + $("#authId").val();
	            	});
            	}else{
            		parent.$.messager.alert('','新增失败!');
            	}
            } 
        }); 
	}
	 function searchs(){
		var number = $("#relatedListNumber").val();
		if(number == ""){
			alert("请输入分部申请单号,");
			return;
		}
		var s = "";
		s = "[[" + "{field : 'suitModel',	title  : '适应机型',width : 25,	align:'center'}," +
				"{field : 'fittingCode',title : '配件编码',width : 25,	align:'center'}," + 
				"{field : 'fittingName',title : '配件名称',width : 25,	align:'center'}," +
				"{field : 'spec',		title  : '规格',width : 25,	align:'center'}," +
				"{field : 'price', 		title  : '价格',width:25,align:'center'}," +
				"{field : 'applyCount',title  : '申请数量',width : 25,	align:'center'}," +
				"{field : 'comment', title : '注释',width : 20, align:'center'}";
		s = s + "]]";
		var options = {};
		options.url = '${ctx}/hxMaterial/getHxMaterialDetail3/'+number;
		options.columns = eval(s);
		$('#dataGrid').datagrid({toolbar: ''});

		$("#dataGrid").datagrid({title:"分部申请单配件信息"}); 
		$('#dataGrid').datagrid({
			url:'${ctx}/hxMaterial/getHxMaterialDetail3/'+number,
			columns : [[{field : 'suitModel',	title  : '适应机型',width : 25,	align:'center'},
	    				{field : 'fittingCode',title : '配件编码',width : 25,	align:'center'},
	    				{field : 'fittingName',title : '配件名称',width : 25,	align:'center'},
	    				{field : 'spec',		title  : '规格',width : 25,	align:'center'},
	    				{field : 'price', 		title  : '价格',width:25,align:'center'},
	    				{field : 'applyCount',title  : '申请数量',width : 25,	align:'center'},
	    				{field : 'comment', title : '注释',width : 20, align:'center'}
	    		]]
		});
	 };
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
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
		新建${title}
	</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td width="15%" align="center">单据类型<font color="red">*</font></td>
			<td width="35%">${title}</td>
			<c:if test="${type=='fb-n-db'}">
			<td width="15%" align="center">发货单位<font color="red">*</font></td>
			<td width="35%"><input class="easyui-combobox" name="sendCompany"  id="sendCompany" data-options="url:'${ctx}/hxCode/getFbOrgCombobox'"/></td>
		</tr>
		<tr>
			</c:if>
			<c:if test="${type=='fb-kc-zy'}">
			<td width="15%" align="center">良品,残品转移<font color="red">*</font></td>
			<td width="35%"><input class="easyui-combobox" name="moveDirection" id="moveDirection" data-options="
				valueField: 'label',
				textField: 'value',
				data: [{
					label: '0',
					value: '良品转残品'
				},{
					label: '1',
					value: '残品转良品'
				}]
				
			"/></td>
		</tr>
		<tr>
			</c:if>
			<c:if test="${type=='zb-n-cg'}">
			<td width="5%" align="center">分部申请单号<font color="red">*</font></td>
			<td width="30%"><input name="relatedListNumber" id="relatedListNumber" type="text"/>
					<a  href="#"  id="query" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchs();">搜索</a>
			</td>
		</tr>
		<tr>
			</c:if>
			<td width="15%" align="center">手工单号</td>
			<td width="35%"><input name="listNumber" readonly="readonly" type="text"></td>
		</tr>
		<tr>
			<td width="15%" align="center">申请人</td>
			<td width="35%"><input name="proposer" type="text" placeholder="请输入申请人" value=""></td>
			<td width="15%" align="center">申请日期</td>
			<td width="35%"><input id="applyDate" name="applyDate" type="text" placeholder="请选择申请日期" data-options="required:false" value="${date}" readonly="true">
							<img onclick="WdatePicker({el:'applyDate'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
		</tr>
			<c:if test="${type=='wd-yjjh-sq'}">
		<tr>
			<td width="25%" align="center">客户姓名<font color="red">*</font></td>
			<td width="25%"><input name="customerName" type="text" placeholder="请输入客户姓名"></td>
			<td width="25%" align="center">客户电话<font color="red">*</font></td>
			<td width="25%"><input name="customerTel" type="text" placeholder="请输入客户电话"></td>
		</tr>
		<tr>
			<td width="25%" align="center">地址<font color="red">*</font></td>
			<td width="25%" colspan="3"><input name="address" type="text" placeholder="请输入地址"></td>
		</tr>
			</c:if>
		<tr>
			<td width="25%" align="center">备注</td>
			<td width="25%" colspan="3"><textarea name="comment" placeholder="请输入备注" rows="3" cols="52"></textarea></td>
		</tr>
	</table>
</form>
<div>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table id="dataGrid"></table>
	</div>
</div>
<div id="toolbarFitting" style="display: none;">
	<input id="numberFitting" style="width: 20px;" type="hidden" value="1">
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