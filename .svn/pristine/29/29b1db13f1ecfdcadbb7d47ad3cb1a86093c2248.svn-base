package com.gome.gmhx.service.wsdl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.wsdl.JlAccountDao;
import com.gome.gmhx.entity.JlAccount;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.wsdl.service.JlAccountService;

@Service("jlAccountService")
public class JlAccountServiceImpl implements JlAccountService {
	
	@Resource
	private JlAccountDao jlAccountDao; 
	
	@Override
	public void insertAccount(JlAccount entity) {
		if(null==jlAccountDao.selectAccountByPrimaryKey(entity.getCzy01())){
			jlAccountDao.insertAccount(entity);
		}else{
			jlAccountDao.updateAccountByPrimaryKey(entity);
		}
	}

	@Override
	public void deleteAccountById(String accountId) {
		jlAccountDao.deleteAccountByPrimaryKey(accountId);
	}

	@Override
	public void updateAccount(JlAccount entity) {
		jlAccountDao.updateAccountByPrimaryKey(entity);
	}

	@Override
	public List<Map<String, Object>> queryAccount(Page page) {
		return jlAccountDao.getAccountsPageList(page);
	}
	
	@Override
	public SysUser selectAccountByPrimaryKey(String czy01) {
		return jlAccountDao.selectAccountByPrimaryKey(czy01);
	}

}
