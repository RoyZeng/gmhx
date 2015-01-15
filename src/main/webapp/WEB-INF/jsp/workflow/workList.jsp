<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	var dataGrid;
    $(function() {
    	// 系统环境初始化
			$('#processInstanceId').combobox({
	        url: '<%=request.getContextPath() %>/json/processInstanceId.json',
	        valueField:'id',
	        textField:'text',
	        loadFilter : function(rows) {
	        	var row = {
	        		id : '',
	        		text : ''
	        	};
				rows.unshift(row);
				return rows;
			}
	        });
			$('#status').combobox({
		        url: '<%=request.getContextPath() %>/json/status.json',
		        valueField:'id',
		        textField:'text',
		        loadFilter : function(rows) {
		        	var row = {
		        		id : '',
		        		text : ''
		        	};
					rows.unshift(row);
					return rows;
				}
		        });
		
		$('#processInstanceId').combobox('setValue', '');	
		$('#status').combobox('setValue', '');

    	
        dataGrid = $('#dataGrid').datagrid(
                {
                    title : '我的工作列表',
                    url : "${ctx}/hxWorkFlow/getWorkPageList",
                    nowrap : false,
        			striped : true,
        			height : document.body.clientHeight - 70,
        			autoRowHeight : false,
        			remoteSort : false,
        			idField : 'listNumber',
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
                    columns : [ [
                            {
                                field : 'listNumber',
                                title : '单据编码',
                                width : 10
                            },{
                                field : 'processInstanceId',
                                title : '流程模型',
                                width : 10,
                                formatter : function(value, row, index) {
	                              return processInstanceIdToVal(row.processInstanceId);
								}
                            },{
                                field : 'status',
                                title : '状态',
                                width : 10,
                                formatter:function(value, row, index){ 
                                	return statusVal(row.status);
            	                }
                            },{
                                field : 'proposer',
                                title : '申请人',
                                width : 10,
                                formatter:function(value, row, index){ 
                                 	return changeToName(row.proposer,1);
                	            }
                                
                            },{
                                field : 'applyDate',
                                title : '申请日期',
                                width : 10
                            },{
								field : 'action',
								title : '操作',
								width : 5,
								formatter : function(value, row, index) {
									 if (row.status == 'S5') {
										return '<a href="#" onclick="writeMaterialOut(\''
										+ row.listNumber
										+ '\');">【填写出库数量】</a>&nbsp;<a href="#" onclick="statusOperator_view(\'' + row.listNumber + '\',\'' + row.processInstanceId  + '\');">【查看】</a>';
									 } else if (row.status == 'S7' || row.status == 'S8' || row.processInstanceId.startWith('SP01')) {
											return '<a href="#" onclick="statusOperator_view(\'' + row.listNumber + '\',\'' + row.processInstanceId  + '\');">【查看】</a>';
									}else {
									   return '<a href="#" onclick="statusOperator(\'' + row.listNumber + '\',\'' + row.processInstanceId  + '\');">【'+
									   statusVal(row.status)
									   +'】</a>&nbsp;<a href="#" onclick="statusOperator_view(\'' + row.listNumber + '\',\'' + row.processInstanceId  + '\');">【查看】</a>';
									}  
									 
								}
                            }] ],
							loadMsg : "数据加载中...",
							onBeforeLoad : function(param) {
								$.serializeObject($('#searchForm'), param);
								param.currentPage = param.page;
								param.pageCount = param.rows;
							}
						});
    });
        
	function searchs() {
		$('#dataGrid').datagrid('load');
	};
	function goS7(listNumber){
		window.location.href = "${ctx}/ParcelDelivery/ParcelDeliveryView";
    }
	function goS8(listNumber){
		window.location.href = "${ctx}/hxParcelReceipt/hxParcelReceiptView";
	}

	$.serializeObject = function(form, o) {
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	};
	
    
	function processInstanceIdToVal(processInstanceId) {
			var result = "";
			$.ajax({
				url : ctx + "/hxWorkFlow/processInstanceIdToVal",
				data : {
					processInstanceId : processInstanceId
				},
				async : false,
				success : function(data) {
					result = data;
				}
			});
			return result;
		}

	function statusVal(status) {
		if (status == 'S0') {
			return '流程结束';
		} else if (status == 'S1') {
			return '暂存';
		} else if (status == 'S2') {
			return '提交';
		} else if (status == 'S3') {
			return '分部审核';
		} else if (status == 'S4') {
			return '总部审核';
		} else if (status == 'S5') {
			return '填写出库数量';
		} else if (status == 'S6') {
			return '出库';
		} else if (status == 'S7') {
			return '邮包发货';
		} else if (status == 'S8') {
			return '邮包收货';
		} else if (status == 'S9') {
			return '网店确认';
		}else if(status=='S10'){
			return "退回修改";
		}else if(status=='S11'){
			return "发货";
		}else if(status=='S12'){
			return "收货";
		}else if(status=='S13'){
			return "检测";
		}else if(status=='S14'){
			return "入库";
		}else if(status=='S15'){
			return "移库";
		}
	}
	
	function changeToName(applicant,type) {
		var result="";
		$.ajax({
             url: ctx + "/hxWorkFlow/changeToName",
             data: {value:applicant, type:type},
             async:false,
             success: function(data){
            	 result=data;
              }
         });
		 return result;
	}

	function statusOperator(listNumber, processInstanceId) {
		window.location.href = "${ctx}/hxWorkFlow/statusOperator?flag=1&listNumber="
				+ listNumber + "&processInstanceId=" + processInstanceId;
	}
	
	function statusOperator_view(listNumber, processInstanceId) {
		window.location.href = "${ctx}/hxWorkFlow/statusOperator?flag=2&listNumber="
				+ listNumber + "&processInstanceId=" + processInstanceId+"&type=-1";
	}
	
	String.prototype.startWith=function(str){     
		  var reg=new RegExp("^"+str);     
		  return reg.test(this);        
		};
	
	function writeMaterialOut(listNumber) {
		
		var url="${ctx}/hxMaterial/getHxMaterialDetailView/"+listNumber;
		var returnValue = window.showModalDialog(url,window,"dialogHeight=600px;dialogWidth=800px;center=yes;status=no;scroll=no;resizable=yes");
		if (returnValue == 1)//判断是否刷新 
        {
            window.location.reload();
        };
		
		/* parent.$.modalDialog({
			title : '填写出库数量',
			width : 800,
			height : 600,
			href : '${ctx}/hxMaterial/getHxMaterialDetailView/'+listNumber, 
			buttons : [ {
				text : '确定',
				handler : function() {
				    parent.$.modalDialog.openner_dataGrid = dataGrid;
                    alert("何时来!!");
				}
			} ]
		}); */
	}
</script>
</head>
<body class="easyui-layout" style="padding: 10px">
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div
			data-options="region:'north',title:'查询条件',border:false,collapsible:false"
			style="height: 150px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed"
					style="width: 100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="5%">单据编码:</td>
						<td width="5%"><input type="text" name="listNumber" /></td>
						<td width="5%"></td>
						<td width="5%">流程模型:</td>
						<td width="5%"><input name="processInstanceId" id="processInstanceId" class="easyui-combobox"/></td>
						<td width="5%"></td>
						<td width="5%">状态:</td>
						<td width="5%">
						<input name="status" id="status" class="easyui-combobox"/></td>
						<td width="5%"></td>
						<td width="55%"></td>
					</tr>
					<tr>
						<td width="5%">申请人:</td>
						<td width="5%"><input name="proposer" type="text" /></td>
						<td width="5%"></td>
						<td width="5%">申请日期:</td>
						<td width="25%"><input id="ksrq" placeholder="" class="Wdate"
							name="ksrq" type="text"
							onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jsrq\')}'})" />至 <input
							id="jsrq" placeholder="" class="Wdate" name="jsrq" type="text"
							onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ksrq\')}'})" /></td>
						<td width="5%"></td>
						<td width="5%"><a href="javascript:void(0);"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-search',plain:true"
							onclick="searchs();">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>

</body>
</html>
