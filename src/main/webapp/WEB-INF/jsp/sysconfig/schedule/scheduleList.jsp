<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	var win;
	function openWin() {
		win = $.messager.progress({
			title : 'Please waiting',
			msg : 'Loading data...'
		});
	};

	function closeWin() {
		$.messager.progress('close');
	};
	
	
	function unLoadOrganization(){
		 $.messager.progress({
            title: '请稍候',
            msg: '任务调度中...'
        });
		 $.post("${ctx}/schedule/unloadOrganization",
					function(msg){
						 setTimeout(function() {
                               $.messager.progress('close');
                           }, 100);
						if(msg == "success"){
							$.messager.alert('提示:','任务调度成功!');
						}else{
							$.messager.alert('提示:','任务出现异常!');
						}
					});
	};
	
	function unLoadUser(){
		 $.messager.progress({
             title: '请稍候',
             msg: '任务调度中...'
         });
		 $.post("${ctx}/schedule/unloadUser",
					function(msg){
						 setTimeout(function() {
                                $.messager.progress('close');
                            }, 100);
						if(msg == "success"){
							$.messager.alert('提示:','任务调度成功!');
						}else{
							$.messager.alert('提示:','任务出现异常!');
						}
					});
	};
	
	function unLoadEmpUser(){
		 $.messager.progress({
            title: '请稍候',
            msg: '任务调度中...'
        });
		 $.post("${ctx}/schedule/unloadEmpUser",
					function(msg){
						 setTimeout(function() {
                               $.messager.progress('close');
                           }, 100);
						if(msg == "success"){
							$.messager.alert('提示:','任务调度成功!');
						}else{
							$.messager.alert('提示:','任务出现异常!');
						}
					});
	};
	
	function syncOrganizationUser(){
		 $.messager.progress({
           title: '请稍候',
           msg: '任务调度中...'
       });
		 $.post("${ctx}/schedule/syncOrganizationUser",
					function(msg){
						 setTimeout(function() {
                              $.messager.progress('close');
                          }, 100);
						if(msg == "success"){
							$.messager.alert('提示:','任务调度成功!');
						}else{
							$.messager.alert('提示:','任务出现异常!');
						}
					});
	};
	function getAccountJob(){
		 $.messager.progress({
           title: '请稍候',
           msg: '任务调度中...'
       });
		 $.post("${ctx}/schedule/getAccountJob",
					function(msg){
						 setTimeout(function() {
                              $.messager.progress('close');
                          }, 100);
						if(msg == "success"){
							$.messager.alert('提示:','任务调度成功!');
						}else{
							$.messager.alert('提示:','任务出现异常!');
						}
					});
	};
	function createSettlmentTicketJob(){
		 $.messager.progress({
           title: '请稍候',
           msg: '任务调度中...'
       });
		 $.post("${ctx}/schedule/createSettlmentTicketJob",
					function(msg){
						 setTimeout(function() {
                              $.messager.progress('close');
                          }, 100);
						if(msg == "success"){
							$.messager.alert('提示:','任务调度成功!');
						}else{
							$.messager.alert('提示:','任务出现异常!');
						}
					});
	};
	
	function codeSynchCache(){
		 $.messager.progress({
	           title: '请稍候',
	           msg: '任务调度中...'
	       });
			 $.post("${ctx}/schedule/codeSynchCache",
						function(msg){
							 setTimeout(function() {
	                              $.messager.progress('close');
	                          }, 100);
							if(msg == "success"){
								$.messager.alert('提示:','任务调度成功!');
							}else{
								$.messager.alert('提示:','任务出现异常!');
							}
				});
	}
	
</script>
</head>
<body>
<div align="center">
	<table class="easyui-datagrid" style="width:800px;height:250px"  data-options="fitColumns:true, singleSelect:true">   
	    <thead>   
	        <tr  align="center">   
	            <th data-options="field:'code',width:250" align="center">任务名称</th>   
	            <th data-options="field:'price',width:450,align:'center'">立即执行</th>   
	        </tr>   
	    </thead>
	    <tbody align="center">    
	      	 <tr>   
	            <td align="center">获取身份管理平台的用户岗位信息</td><td><input type="button" value="启动" onclick="getAccountJob();" width="50px"></td> 
	        </tr> 
	        <tr>   
	            <td align="center">转储身份管理平台用户</td><td><input type="button" value="启动" onclick="unLoadUser();" width="50px"></td>   
	        </tr>
	         <tr>   
	            <td align="center">转储主数据的组织机构</td><td><input type="button" value="启动" onclick="unLoadOrganization();" width="50px"></td>   
	        </tr>
	         <tr>   
	            <td align="center">转储身份管理平台用户登录名岗位和来源信息</td><td><input type="button" value="启动" onclick="unLoadEmpUser();" width="50px"></td>   
	        </tr>
	        <tr>   
	            <td align="center">同步系统建的组织机构和用户到转储表</td><td><input type="button" value="启动" onclick="syncOrganizationUser();" width="50px"></td>  
	        </tr>
	         <tr>   
	            <td align="center">安装单、保外销售、维修单结算</td><td><input type="button" value="启动" onclick="createSettlmentTicketJob();" width="50px"></td>  
	        </tr>
	        <tr>   
	            <td align="center">缓存同步</td><td><input type="button" value="启动" onclick="codeSynchCache();" width="50px"></td>  
	        </tr>      
	    </tbody>   
	</table>  
</div>
</body>
</html>
