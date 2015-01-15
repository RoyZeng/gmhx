<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.read{FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#fefefe,endColorStr=#d9d9d9);color:#555555;border:1px solid #bebebe;}
</style>

<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			title : "",
			url : "${ctx}/settleaccountsParam/listAllDataByPage", 
			nowrap : false,
			striped : true,
			height : document.body.clientHeight - 130,
			collapsible : true,
			autoRowHeight : false,
			remoteSort : false,
 			idField : 'freeId',
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			checkOnSelect : false,
			singleSelect : true,
			selectOnCheck : false,
			queryParams : {
				currentPage : 1,
				pageCount : 10
			},
			toolbar:"#toolbar",
			pageList:[10,20],
			columns : [ [ 
				  {
						field : 'paramNo',
						title : '编号',
						width : 50,
						align:'center'
			      },
				  {
						field : "paramName",
						title : '参数',
						width : 50,
						align:'center'
			      },
				  {
						field : "paramValue1",
						title : '数值一',
						width : 70,
						align:'center'
			      },
				  {
						field : "paramValue2",
						title : '数值二',
						width : 70,
						align:'center'
			      },
				  {
						field : "paramDesc",
						title : '参数描述',
						width : 110,
						align:'center'
			      },
			      {
						field : "operation",
						title : '操作',
						width : 80,
						align:'center',
					  	formatter:function(value , record , index){
					  		var returnStr ="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-search',plain:true\" onclick=\"showRecord('"+record.paramNo+"')\">查看</a>&nbsp;&nbsp|&nbsp;&nbsp;"+
					  			"<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-search',plain:true\" onclick=\"modifyRecord('"+record.paramNo+"')\">修改</a>&nbsp;&nbsp|&nbsp;&nbsp;"+
					  			"<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-search',plain:true\" onclick=\"delRecord('"+record.paramNo+"')\">删除</a>";
					  		return returnStr;
					  	}
				  }
			] ],
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');
			}
			});
		$('.datagrid-pager').pagination({
			pageSize: 10,//每页显示的记录条数，默认为10           
			onSelectPage : function(pageNumber, pageSize) {
				$(this).pagination('loading');
				var queryParams = $.serializeObject($('#searchForm'));
				queryParams.currentPage = pageNumber;
				queryParams.pageCount = pageSize;
				$('#dataGrid').datagrid("options").queryParams = queryParams;
				$('#dataGrid').datagrid("reload");
				$(this).pagination('loaded');
			}
		});
		$("#query").on("click", function() {
			dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
		});
			
	});
	
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
		
		function  showRecord(number){
			var returnData = postData("${ctx}/settleaccountsParam/queryByParamNo","paramNo="+number);
			var jsonData = eval("("+returnData+")");
			$("#paramNo").val(jsonData.paramNo);
			$("#paramName").val(jsonData.paramName);
			$("#paramValue1").val(jsonData.paramValue1);
			$("#paramValue2").val(jsonData.paramValue2);
			$("#paramDesc").val(jsonData.paramDesc);
			 $("#dialog input").attr("readOnly","readOnly").addClass("read");
			 buildForm("查看核算参数",function (){
				 $("#dialog input").val("").removeClass("read").attr("readOnly",false);
				 $('#dialog').dialog("close");
			 });
		}
		
		function modifyRecord(number){
			var returnData = postData("${ctx}/settleaccountsParam/queryByParamNo","paramNo="+number);
			var jsonData = eval("("+returnData+")");
			$("#paramNo").val(jsonData.paramNo).addClass("read").attr("readOnly","readOnly");
			$("#paramName").val(jsonData.paramName);
			$("#paramValue1").val(jsonData.paramValue1);
			$("#paramValue2").val(jsonData.paramValue2);
			$("#paramDesc").val(jsonData.paramDesc);
			 buildForm("修改核算参数",function (){
				 var returnData = postData("${ctx}/settleaccountsParam/savaOrUpdateRecode", $("#paramRecord").serialize());
				 $("#dialog input").val("").removeClass("read").attr("readOnly",false);
				 $('#dialog').dialog("close");
				 $("#dataGrid").datagrid("reload");
				 $.messager.alert('提示信息',"修改成功！");
			 });
		}
		
		function delRecord(number){
			 var returnData = postData("${ctx}/settleaccountsParam/delRecode","paramNo="+number);
			 $("#dataGrid").datagrid("reload");
			 $.messager.alert('提示信息',"删除成功！");
		}
		
		function addRecord(){
			$("#paramNo").addClass("read").attr("readOnly","readOnly");
		     buildForm("增加核算参数",function (){
				 var returnData = postData("${ctx}/settleaccountsParam/savaOrUpdateRecode", $("#paramRecord").serialize());
				 $("#dialog input").val("").removeClass("read").attr("readOnly","");
				 $('#dialog').dialog("close");
				 $("#dataGrid").datagrid("reload");
				 $.messager.alert('提示信息',"已添加！");
			 });
		}
		
		function buildForm(title,confirmHandler){
			$('#dialog').dialog({
				title:title ,
				iconCls:'icon-ok' ,
				modal:true,
				buttons:[
					{
						text:'确定' ,
						iconCls:'icon-ok' ,
						handler :confirmHandler
					},
					{
						text:'取消',
						iconCls:'icon-cancel',
						handler : function(){
							 $("#dialog input").val("").removeClass("read").attr("readOnly",false);
							$('#dialog').dialog("close");
						}
					}
				]
			});
			$('#dialog').dialog("open");
		}
		
		function postData(requesturl,requestdata,msg){
			var returnData;
			$.ajax({
				url: requesturl,
				type:"POST",
				async:false,
				data:requestdata,
				success:function(data){
					returnData = data;
					if(msg){
						$.messager.alert('提示信息',msg);
					}
					
				}
			});
			return returnData;
		}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false,collapsible:false" style="height: 130px; overflow: hidden;">
		<div>
			<form  id="searchForm">
				<table class="table table-hover table-condensed"
					style="width: 100%; padding: 7px 100px 0px 80px">
					<tr>
						<td width="5%"  align="right">参数：</td>
						<td width="10%" align="left"><input name="paramName" ></td>
					    <td width="5%"  align="right">数值一：</td>
						<td width="10%" align="left"><input name="paramValue1"></td>
						<td width="5%"  align="right">数值二：</td>
						<td width="10%" align="left"><input name="paramValue2"></td>
					</tr>
					<tr>
					  <td width="5%"  align="right">编号：</td>
						<td width="10%" align="left"><input name="paramNo"></td>
						<td width="5%"  align="right">描述：</td>
						<td width="10%" align="left"><input name="paramDesc"></td>
					</tr>
					<tr align="right">
						<td colspan="5" > </td>
						<td><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
			
		<div id="dialog" class="easyui-dialog" style="width:500px;height:300px;padding-top: 30px" closed=true>
			<fieldset >
			<legend>核算参数信息</legend>
				<form action="" id="paramRecord">
					<table class="table table-hover table-condensed" style="width: 100%; padding: 7px 100px 0px 80px">
							<tr><td width="5%"  align="right">编号：</td><td width="10%" align="left"><input name="paramNo"   id="paramNo"></tr>
							<tr><td width="5%"  align="right">参数：</td><td width="10%" align="left"><input name="paramName"  id="paramName"></td></tr>
						    <tr><td width="5%"  align="right">数值一：</td><td width="10%" align="left"><input name="paramValue1"  id="paramValue1"></td></tr>
							<tr><td width="5%"  align="right">数值二：</td><td width="10%" align="left"><input name="paramValue2" id="paramValue2"></td></tr>
							<tr><td width="5%"  align="right">描述：</td><td width="10%" align="left"><input name="paramDesc" id="paramDesc"></td></tr>
					</table>
				</form>
			</fieldset>
		</div>
		</div>
		<div id="toolbar" >
			<a href="#" id="add" class="easyui-linkbutton" iconCls="icon-add"  onclick="addRecord()">新增</a>
		</div>
		
</body>
</html>
