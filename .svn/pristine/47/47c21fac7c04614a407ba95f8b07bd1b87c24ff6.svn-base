package com.gome.gmhx.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.gome.gmhx.service.wsdl.service.AppSyncServiceStub;
import com.gome.gmhx.service.wsdl.service.AppSyncServiceStub.ArrayOfString;
import com.gome.gmhx.service.wsdl.service.AppSyncServiceStub.GetBatchNumbersByAppCodeAndBatchNumber;
import com.gome.gmhx.service.wsdl.service.AppSyncServiceStub.GetUsersByAppCodeAndBatchNumber;
import com.gome.gmhx.entity.EmpAccount;
import com.gome.gmhx.entity.EmpPosition;
import com.gome.gmhx.entity.EmpSysVar;
import com.gome.gmhx.service.wsdl.service.EmpService;
import com.gome.common.util.SysUtils;
import com.gome.common.util.SysConstant;
import com.gome.common.util.VarConfig;
@Component
public class GetAccountJob {
	private String batchNo ;
	private static String SYS_CODE = "HXSH";
	private static String BATCH_CODE = "abatchno";
	@Resource
	private EmpService empService;
	
	private AppSyncServiceStub stub = null; 
	
	public void getAccount() throws Exception{
		try {
			batchNo = getCurrentBatchNo();
			VarConfig varConfig = VarConfig.getInstance();
			String url = varConfig.getOaAccount();
			stub = new AppSyncServiceStub(url);
			GetBatchNumbersByAppCodeAndBatchNumber gbn = new GetBatchNumbersByAppCodeAndBatchNumber();
			gbn.setAppCode(SYS_CODE);
			gbn.setBatchNumber(batchNo);
			ArrayOfString arrStr = stub.getBatchNumbersByAppCodeAndBatchNumber(gbn).getGetBatchNumbersByAppCodeAndBatchNumberResult();
			if(arrStr==null){
				System.out.println("没有最新批次！");
				return;
			}
			String[] batchNos = arrStr.getString();
			
			GetUsersByAppCodeAndBatchNumber gubn = new GetUsersByAppCodeAndBatchNumber();
			gubn.setAppCode(SYS_CODE);

			if(batchNos!=null&&batchNos.length==1){
				gubn.setBatchNumber(batchNos[0]);
				this.batchNo = batchNos[0];
				String resultStr = stub.getUsersByAppCodeAndBatchNumber(gubn).getGetUsersByAppCodeAndBatchNumberResult();
				parserXml(resultStr);
				saveBatchNo();
			}else if(batchNos!=null&&batchNos.length>1){
				for(int i=0;i<batchNos.length;i++){
					this.batchNo = batchNos[i];
					gubn.setBatchNumber(batchNos[i]);
					String resultStr = stub.getUsersByAppCodeAndBatchNumber(gubn).getGetUsersByAppCodeAndBatchNumberResult();
					parserXml(resultStr);
				}
				this.batchNo = batchNos[batchNos.length-1];
				saveBatchNo();
			}
		}  catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	private void saveBatchNo() {
		EmpSysVar svar = empService.getSysVarById(BATCH_CODE);
		if(svar==null){
			svar = new EmpSysVar();
			svar.setVarcode(BATCH_CODE);
			svar.setVarvalue(this.batchNo);
			svar.setVardesc("获取账号的批次号！");
			empService.addSysVar(svar);
		}else{
			svar.setVarvalue(this.batchNo);
			empService.updateSysVar(svar);
		}
	}

	public String getChildElementText(Element e , String ChildName){
		if(e==null){
			return null;
		}
		Element tempE = e.element(ChildName);
		if(tempE==null){
			return null;
		}
		if(SysUtils.isEmptyString(tempE.getTextTrim())){
			return null;
		}else{
			return tempE.getTextTrim();
		}
	}
	
	private void parserXml(String xml) throws DocumentException {
		System.out.println(xml);
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		List<Element> rowList = root.element(SysConstant.ACCOUNT.DATASET).elements(SysConstant.ACCOUNT.ROW);
		Iterator<Element> it = rowList.iterator();
		Element row = null;
		List<EmpAccount> accounts = new ArrayList<EmpAccount>();
		EmpAccount account = null;
		List<EmpPosition> empPositions = null;
		EmpPosition empPosition = null;
		String currentDate = SysUtils.getCurrentDate();
		String currentTime = SysUtils.getCurrentTime();
		Map<EmpAccount,List<EmpPosition>> accMap = new HashMap<EmpAccount, List<EmpPosition>>();
		while (it.hasNext()) {
			empPositions = new ArrayList<EmpPosition>();
			
			row = it.next();
			account = empService.getAccountById(getChildElementText(row,SysConstant.ACCOUNT.EMPLOYEE_NO));
			if(account==null){
				account = new EmpAccount();
				account.setCreatedate(currentDate);
				account.setCreatetime(currentTime);
				account.setModifydate(currentDate);
				account.setModifytime(currentTime);
			}else{
				account.setModifydate(currentDate);
				account.setModifytime(currentTime);
			}
			account.setEmpno(getChildElementText(row,SysConstant.ACCOUNT.EMPLOYEE_NO));
			account.setEmpname(getChildElementText(row, SysConstant.ACCOUNT.EMPLOYEE_NAME));
			String temp  = getChildElementText(row, SysConstant.ACCOUNT.AD_ACCOUNT);
			account.setAdAccount(SysUtils.isEmptyString(temp)?"":temp.toUpperCase());
			account.setAdAccountpwd(getChildElementText(row, SysConstant.ACCOUNT.AD_ACCOUNT_PWD));
			temp  = getChildElementText(row, SysConstant.ACCOUNT.AP_ACCOUNT);
			account.setApAccount(SysUtils.isEmptyString(temp)?"":temp.toUpperCase());
			account.setApAccountpwd(getChildElementText(row, SysConstant.ACCOUNT.AP_ACCOUNT_PWD));
			account.setCompcode(getChildElementText(row, SysConstant.ACCOUNT.COMPANY_CODE));
			account.setCompname(getChildElementText(row, SysConstant.ACCOUNT.COMPANY_NAME));
			account.setPhone(getChildElementText(row, SysConstant.ACCOUNT.MOBILE));
			account.setEmail(getChildElementText(row, SysConstant.ACCOUNT.EMAIL));
			account.setState(getChildElementText(row, SysConstant.ACCOUNT.STATE));


			List<Element> ps = row.element(SysConstant.ACCOUNT.POSITIONINFO).
									element(SysConstant.ACCOUNT.NEW).elements(SysConstant.ACCOUNT.ITEM);
			
			if(ps!=null&&ps.size()>0){
				for(Element p : ps){
					empPosition = new EmpPosition();
					empPosition.setEmpno(account.getEmpno());
					empPosition.setHeadcode(getChildElementText(p,SysConstant.ACCOUNT.HEAD_CODE));
					empPosition.setHeadname(getChildElementText(p, SysConstant.ACCOUNT.HEAD_NAME));
					empPosition.setRegioncode(getChildElementText(p, SysConstant.ACCOUNT.REGION_CODE));
					empPosition.setRegionname(getChildElementText(p, SysConstant.ACCOUNT.REGION_NAME));
					empPosition.setOnebranchcode(getChildElementText(p, SysConstant.ACCOUNT.ONE_SUBSECTION_CODE));
					empPosition.setOnebranchname(getChildElementText(p, SysConstant.ACCOUNT.ONE_SUBSECTION_NAME));
					empPosition.setTwobranchcode(getChildElementText(p, SysConstant.ACCOUNT.TWO_SUBSECTION_CODE));
					empPosition.setTwobranchname(getChildElementText(p, SysConstant.ACCOUNT.TWO_SUBSECTION_NAME));
					empPosition.setStorecode(getChildElementText(p, SysConstant.ACCOUNT.STORE_CODE));
					empPosition.setStorename(getChildElementText(p, SysConstant.ACCOUNT.STORE_NAME));
					empPosition.setDeptcode(getChildElementText(p, SysConstant.ACCOUNT.DEPT_CODE));
					empPosition.setDeptname(getChildElementText(p, SysConstant.ACCOUNT.DEPT_NAME));
					empPosition.setPositioncode(getChildElementText(p, SysConstant.ACCOUNT.POSITION_CODE));
					empPosition.setPositionname(getChildElementText(p, SysConstant.ACCOUNT.POSITION_DESC));
					
					empPosition.setStartdate(getChildElementText(p, SysConstant.ACCOUNT.STARTDATE));
					empPosition.setEnddate(getChildElementText(p, SysConstant.ACCOUNT.ENDDATE));
					empPosition.setOrglevel(getChildElementText(p, SysConstant.ACCOUNT.LEVEL));
					empPosition.setOrgleveldetail(getChildElementText(p, SysConstant.ACCOUNT.LEVEL_DETAIL));
					empPosition.setDutyflag(getChildElementText(p, SysConstant.ACCOUNT.DUTY_FLAG));
					empPosition.setPositionname(getChildElementText(p, SysConstant.ACCOUNT.POSITION_DESC));
					empPosition.setPositionname(getChildElementText(p, SysConstant.ACCOUNT.POSITION_DESC));
					empPosition.setCreatedate(currentDate);
					empPosition.setCreatetime(currentTime);
					empPosition.setModifydate(currentDate);
					empPosition.setModifytime(currentTime);
					
					empPositions.add(empPosition);
				}
			}
			accounts.add(account);
			
			accMap.put(account, empPositions);
		}
		
		this.empService.saveAccountAndEmp(accMap,accounts,this);
//		this.accountService.saveAccountAndEmp(accounts, empPositions,this.empPositionService);
	}
	
	private String getCurrentBatchNo() {
		EmpSysVar sysVar = empService.getSysVarById(BATCH_CODE);
		if(sysVar==null){
			return null;
		}else{
			return sysVar.getVarvalue();
		}
	}
	
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

}
