<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid(
				{
					title : "添加申请单信息",
					nowrap : false,
					striped : true,
					height : document.documentElement.clientHeight *0.5,
					url : "${ctx}/ParcelDelivery/getParcelDeliveryDetail/${parcelCode}",
					collapsible : true,
					autoRowHeight : false,
					remoteSort : false,
					rownumbers : true,
					fitColumns : true,
					checkOnSelect : false,
					singleSelect : true,
					selectOnCheck : false,
					queryParams : {
						currentPage : 1,
						pageCount : 10
					},
					columns : [ [{field:'ck',title : '发送',checkbox : true},
								 {field : 'list_number',title : '申请单号',align:'center',width : 100},
								 {field : 'type',title : '单据类型',align:'center',width : 80},
								 {field : 'send_number',title : '发货单号',align:'center',editor: 'text',width : 150},
								 {field : 'fitting_code',title : '配件编码',align:'center',width : 50},
								 {field : 'fitting_code',title : '配件名称',align:'center',width : 50},
								 {field : 'spec',title : '规格',align:'center',width : 100},
								 {field : 'audit_count',title : '数量',align:'center',width : 50},
								 {field : 'update_time',title : '出单日期',align:'center',width : 100},
								 {field : 'comment',title : '备注',align:'center',width : 50},
							{
								field : 'action',
								title : '操作',
								width : 20,
								align : 'center',
								formatter : function(value, row, index) {
									return '<a href="#" onclick="show(\''
											+ row.parcel_code + '\',\''
											+ row.bills_code + '\');">查看</a> ';

								}
							} ] ],
					toolbar : '#toolbar',
					onLoadSuccess : function() {
						$('#searchForm table').show();
						$(this).datagrid('doCellTip', {
							'max-width' : '300px',
							'delay' : 500
						});
						parent.$.messager.progress('close');
					},
				});
		$('#receiveUnit').combobox({  
	        onSelect:function(record){
	        	$.post('${ctx}/ParcelDelivery/getOrgnazationBySuit?receiveUnit=' + record.value,{},
		        		function(returnObj){ 
		            		var returnObj = $.parseJSON(returnObj);
			        		$(" input[name='receiveName']").val(returnObj.orgReference);
			        		$(" input[name='receivePhone']").val(returnObj.orgTelephone);
			        		$(" input[name='receivePost']").val(returnObj.orgPostCode);
			        		$(" input[name='receiveAddress']").val(returnObj.orgAddress);
		        		}
		        	);
	        }
		
	    }); 
	});

	function searchs() {
		var options = {};
		receiveUnit = $("#receiveUnit").combobox('getValue');
		if(receiveUnit == ""){
			$.messager.alert("操作提示", "请输入收货单位！","info");
			return;
		}
		options.url = "${ctx}/ParcelDelivery/getApplayCodeList?receiveUnit="+receiveUnit;
		$('#dataGrid').datagrid(options);
	};

	$.serializeObject = function(form) {
		var o = {
			currentPage : 1,
			pageCount : 10
		};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	};
	function submitForm() {
		var sendDate = $("#sendDate").val();
		if (sendDate == null || sendDate == '') {
		} else {
			sendDate = sendDate.replace(/-/g, '/'); // 转换日期格式
		}
		$("#sendDate").val(sendDate);

		var selectRows = $("#dataGrid").datagrid('getChecked');
		if (selectRows.length == 0) {
			$.messager.alert('提示', '请至少选择一个发货单!', 'info');
			return;
		}
		var listNumbers = [];
		for (var i = 0; i < selectRows.length; i++) {
			var item = selectRows[i];
			listNumbers.push(item.list_number);
		}
		listNumbers = listNumbers.join(",");

		if ($.trim($("#form input[name='billsCode']").val()) == "") {
			alert("货运单号为空，不能保存");
			return false;
		}
		if ($.trim($("#form input[name='sendDate']").val()) == "") {
			alert("发货时间为空，不能保存");
			return false;
		}
		if ($.trim($("#form input[name='receiveUnit']").val()) == "") {
			alert("收货单位为空！");
			return false;
		}
		$("#listNumbers").val(listNumbers);
		$.post(
			"${ctx}/ParcelDelivery/updateParcelDelivery",
			$('#form').serialize(),
			function(msg) {
				if ($.parseJSON(msg).flag == "success") {
					$.messager.alert('提示:', '修改成功!');
					window.location.href="${ctx}/ParcelDelivery/showView/" + $("#parcelCode").val();
				} else {
					$.messager.alert('提示:', '修改失败!');
				}
			});
	};

	function goBack() {
		window.location.href = "${ctx}/ParcelDelivery/ParcelDeliveryView";
	};
</script>
<div class="easyui-panel" style="width: auto">
	<div class="easyui-panel" title="查询发货单" style="width: auto">
		<div style="padding: 10px 0 10px 60px">
			<form id="form" method="post">
				<input id="parcelCode" name="parcelCode" type="hidden" value="${parcel.parcelCode}" />
				<table class="table table-hover table-condensed" width="100%;">
					<tr>
						<td>收货单位</td>
						<td><input id="receiveUnit" name="receiveUnit"
							class="easyui-combobox"
							data-options="url:'${ctx}/hxCode/getOrgCombobox?value=${parcel.receiveUnit}',hasDownArrow:false" />
							<input type="hidden" id="listNumbers" name="listNumbers" /> <a
							href="#" id="query" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" onclick="searchs();">搜索</a>
						</td>
						<td>货运单号</td>
						<td><input id="billsCode" name="billsCode"
							class="easyui-validatebox span2" type="text"
							value="${parcel.billsCode}"></td>
						<td>发运时间</td>
						<td><input id="sendDate" type="text" name="sendDate"
							class="easyui-validatebox span2"
							value="<fmt:formatDate value='${parcel.sendDate}' pattern='yyyy-MM-dd'/>">
							<img onclick="WdatePicker({el:'sendDate'})"
							src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="16"
							height="22" align="absmiddle"></td>
					</tr>
					<tr>
						<td>发货方式<font color="red">*</font></td>
						<td width="30%"><select id="deliveryWay" name="deliveryWay"
							class="easyui-validatebox span2" type="text"
							value="${parcel.deliveryWay}" panelHeight="auto">
								<option checked="checked">邮政包裹</option>
								<option value="邮政包裹">邮政包裹</option>
								<option value="宅急送">宅急送</option>
								<option value="航空包裹">航空包裹</option>
								<option value="EMS">EMS</option>
								<option value="大通快递">大通快递</option>
								<option value="联邦快递">联邦快递</option>
								<option value="大田快递">大田快递</option>
								<option value="韵达快递">韵达快递</option>
								<option value="东方快递">东方快递</option>
								<option value="申通快递">申通快递</option>
								<option value="奔达快递">奔达快递</option>
								<option value="吉照快递">吉照快递</option>
								<option value="外运快递">外运快递</option>
								<option value="东方快递">东方快递</option>
								<option value="一通快递">一通快递</option>
								<option value="泛达航空">泛达航空</option>
								<option value="中五商航">中五商航</option>
								<option value="索飞特">索飞特</option>
								<option value="圆通速递">圆通速递</option>
								<option value="友和道通">友和道通</option>
								<option value="长发快运">长发快运</option>
								<option value="佳吉货运">佳吉货运</option>
								<option value="长吉货运">长吉货运</option>
								<option value="自取">自取</option>
								<option value="其他">其他</option>
						</select></td>
						<td>承运单位</td>
						<td><input id="carrierUnit" name="carrierUnit"
							class="easyui-validatebox span2" type="text"
							value="${parcel.carrierUnit}"></td>
						<td>运输费用</td>
						<td><input id="trantransportationExpenses"
							name="trantransportationExpenses"
							class="easyui-validatebox span2" type="text"
							value="${parcel.trantransportationExpenses}"></td>
					</tr>
					<tr>
						<td>物料件数</td>
						<td><input id="packageNumber" name="packageNumber"
							class="easyui-validatebox span2" type="text"
							value="${parcel.packageNumber}"></td>
						<td>重量</td>
						<td><input id="packageWeight" name="packageWeight"
							class="easyui-validatebox span2" type="text"
							value="${parcel.packageWeight}"></td>
						<td>体积</td>
						<td><input id="packageVolume" name="packageVolume"
							class="easyui-validatebox span2" type="text"
							value="${parcel.packageVolume}"></td>
					</tr>
					<tr>
						<td>体积重量</td>
						<td><input id="volumeWeight" name="volumeWeight"
							class="easyui-validatebox span2" type="text"
							value="${parcel.volumeWeight}"></td>
						<td>收货联系人</td>
						<td><input id="receiveName" name="receiveName"
							class="easyui-validatebox span2" type="text" disabled="disabled"
							value="${parcel.receiveName}"></td>
						<td>联系电话</td>
						<td><input id="receivePhone" name="receivePhone"
							class="easyui-validatebox span2" type="text" disabled="disabled"
							value="${parcel.receivePhone}"></td>
					</tr>
					<tr>
						<td>发货邮编</td>
						<td><input id="receivePost" name="receivePost" type="text"
							class="easyui-validatebox span2" disabled="disabled"
							value="${parcel.receivePost}"></td>
						<td>收货地址</td>
						<td><input id="receiveAddress" name="receiveAddress"
							type="text" class="easyui-validatebox span2" disabled="disabled"
							value="${parcel.receiveAddress}"></td>
						<td>备注</td>
						<td><input id="note" name="note" type="text"
							class="easyui-validatebox span2" value="${parcel.note}"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<div style="text-align: center; padding: 5px">
	<a href="#" class="easyui-linkbutton" onclick="submitForm();">保存</a> <a
		href="#" class="easyui-linkbutton" onclick="goBack();">返回</a>
</div>
<div data-options="region:'center',border:false">
	<table id="dataGrid"></table>
</div>
