package com.gome.gmhx.dao.wsdl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.JlAccount;
import com.gome.gmhx.entity.SysUser;

@Repository("jlAccountDao")
public interface JlAccountDao {
	public void insertAccount(JlAccount jlAccount);

	public void deleteAccountByPrimaryKey(String accountId);
	
	public SysUser selectAccountByPrimaryKey(String accountId);
	
	public void updateAccountByPrimaryKey(JlAccount jlAccount);
	
	public List<Map<String, Object>> getAccountsPageList(Page page);
}
