package com.gome.gmhx.service.wsdl.service;

import java.util.List;
import java.util.Map;

import com.gome.gmhx.entity.EmpAccount;
import com.gome.gmhx.entity.EmpAccountJobLog;
import com.gome.gmhx.entity.EmpPosition;
import com.gome.gmhx.entity.EmpSysVar;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.schedule.GetAccountJob;

public interface EmpService {
	
	public EmpAccount getAccountById(String accountNo);
	
	public void addAccount(EmpAccount account);

	public void updateAccount(EmpAccount account);
	
	void addAccountJobLog(EmpAccountJobLog accountJobLog);

	void deleteLogByEmpno(String empno);
	
	public void deleteEmpPositionById(String empNo);

	public int addEmpPosition(EmpPosition emp);
	
	int selectCountByPostCodeAndState(String positioncode);
	
	public EmpSysVar getSysVarById(String batch_code);

	public int updateSysVar(EmpSysVar svar);

	public void addSysVar(EmpSysVar svar);

	public void saveAccountAndEmp(Map<EmpAccount, List<EmpPosition>> accMap,
			List<EmpAccount> accounts, GetAccountJob getAccountJob);

	public SysUser getAccountByApAccount(String indexLoginId);

	public EmpAccount getResetPassword(String userAccount);
}
