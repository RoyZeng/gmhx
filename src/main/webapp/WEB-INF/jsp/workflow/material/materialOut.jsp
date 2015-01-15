<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>填写出库数量</title>
<script type="text/javascript">
	
	var dataGrid;
    $(function() {
    	//parent.$.messager.progress('close');
    	dataGrid = $('#accessories').datagrid({
			title : "配件明细",
			url:"${ctx}/hxMaterial/getHxMaterialDetail/${listNumber}",
			height:'auto',
			striped : true,
			collapsible : true,
			autoRowHeight : false,
			singleSelect : true,
			remoteSort : false,
			rownumbers : true,
			fitColumns : true,
			showFooter : true,
			columns : [ [ {
				field : 'fitting_code',
				title : '配件编码',
				align : 'center',
				width : 400
			}, {
				field : 'fitting_name',
				title : '配件名称',
				align : 'center',
				width : 300
			}, {
				field : 'spec',
				title : '规格',
				align : 'center',
				styler : cellStyler,
				width : 300
			}, {
				field : 'price',
				title : '单价',
				align : 'center',
				styler : cellStyler,
				width : 300
			}, {
				field : 'apply_count',
				title : '申请数',
				align : 'center',
				width : 300
			}, {
				field : 'audit_count',
				title : '审批',
				align : 'center',
				width : 300,
				editor : 'numberbox'
			}, {
				field : 'stock',
				title : '库存',
				align : 'center',
				width : 300
			}/*, {
				field : 'comment',
				title : '备注',
				align : 'center',
				width : 300,
				editor : 'text'
			}*/, {
				field : 'action',
				title : '修改',
				width : 350,
				align : 'center',
				formatter : function(value, row, index) {
					if (row.editing && row.spec != "总计（元）：") {
						var s = '<a href="#" onclick="saverow(this)">保存</a> ';
						var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
						return s + c;
					} else if (row.spec != "总计（元）：") {
						var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
						return e;
					}
				}
			} ] ],
			onBeforeEdit : function(index, row) {
				row.editing = true;
				updateActions(index);
			},
			onAfterEdit : function(index, row) {
				row.editing = false;
				updateActions(index);
				updateTotalCount();
			},
			onCancelEdit : function(index, row) {
				row.editing = false;
				updateActions(index);
			}
		});
	});
	function updateActions(index) {
		$('#accessories').datagrid('updateRow', {
			index : index,
			row : {}
		});
	};
	function getRowIndex(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	};
	function editrow(target) {
		$('#accessories').datagrid('beginEdit', getRowIndex(target));
	};
	function saverow(target) {
		$('#accessories').datagrid('endEdit', getRowIndex(target));
	};
	function cancelrow(target) {
		$('#accessories').datagrid('cancelEdit', getRowIndex(target));
	};
	function cellStyler(value, row, index) {
		if (row.spec == "总计（元）：") {
			return 'background-color:#ffee00;color:red;';
		}
	};
	function searchs() {
		$('#dataGrid').datagrid('load');
	};

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

	function updateTotalCount() {
		var datas = $('#accessories').datagrid('getData').rows;
		var total = 0;
		$.each(datas, function(index, value) {
			if(value.audit_count!=null && value.audit_count!='' && value.audit_count<=value.apply_count){
				rowval = value.audit_count * value.price;
			}else if(value.audit_count>value.apply_count){
				rowval = value.apply_count * value.price;
			}
			total += rowval;
		});
		// 更新页脚行的值并刷新
		var rows = $('#accessories').datagrid('getFooterRows');
		rows[0]['price'] = total;
		$('#accessories').datagrid('reloadFooter');

	};
	function updateHxMaterialDetail() {
		var datas = $('#accessories').datagrid('getData').rows;
		var counts = [];
		var codes = [];
// 		var comments = [];
		$.each(datas, function(index, value) {
			if(value.audit_count ==null || value.audit_count==''){
				value.audit_count=value.apply_count;
			}
			counts.push(value.audit_count);
			codes.push(value.fitting_code);
// 			comments.push(value.comment);
		});
		$.post(ctx + "/hxMaterial/updateHxMaterialDetail", {
			listNumber : '${listNumber}',
			counts : counts.join(","),
			codes : codes.join(","),
// 			comments: comments.join(",")
		}, function(data) {
			if (data.flag = "success") {
				$.messager.alert('提示:','填写出库数量成功!');
				winClose(1);
			} else {
				$.messager.alert('提示:','填写出库数量失败!');
				winClose(1);
			}
		});
	}

	function goBack() {
		winClose(1);
	}
 
	//关闭窗口返回是否刷新的参数. 
    function winClose(isRefrash) {
        window.returnValue = isRefrash;
        window.close();
    }
</script>
</head>
<body>
<div>

<form id="form" method="post">
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
		<c:if test="${m.type=='fb-kc-zy'}">
		<tr>
			<td width="25%" align="center">良品,残品转移方向</td>
			<c:if test="${m.moveDirection =='0' }">
				<td width="25%">良品转残品</td>
			</c:if>
			<c:if test="${m.moveDirection =='1' }">
				<td width="25%">残品转良品</td>
			</c:if>
		</tr>
			</c:if>
		<tr>
			<td width="25%" align="center">发货单位</td>
			<td width="25%"><input id="sendCompany" class="easyui-combobox" name="sendCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox?value=${m.sendCompany}'" disabled="disabled"/></td>
			<td width="25%" align="center">收货单位</td>
			<td width="25%"><input id="receiveCompany" class="easyui-combobox" name="receiveCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox?value=${m.receiveCompany}'" disabled="disabled"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">状态</td>
			<td width="25%"><input class="easyui-combobox" name="sendCompany" data-options="url:'${ctx}/hxCode/getStatusCombobox?status=${m.status}'" disabled="disabled"/></td>
			<td width="25%" align="center"></td>
			<td width="25%"></td>
		</tr>
		<tr>
			<td width="25%" align="center">申请人</td>
			<td width="25%">${m.applicant}</td>
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
</div>
	<div id="cc" class="easyui-layout"
			style="width: 790px;">
			<input type="hidden" id="listNumber">
			<table id="accessories"></table>
		</div>
<div id="dlg-buttons" align="center">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="updateHxMaterialDetail()">确认</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="goBack()">取消</a>
	</div>
</body>
</html>