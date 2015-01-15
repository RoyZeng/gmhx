package com.gome.gmhx.service.basicdata.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.SettleaccountsParamDao;
import com.gome.gmhx.entity.SettleaccountsParam;
import com.gome.gmhx.service.basicdata.SettleaccountsParamService;
@Service
public class SettleaccountsParamServiceImpl implements
		SettleaccountsParamService {
	
	@Resource
	private SettleaccountsParamDao settleaccountsParamDao;

	@Override
	public List listAllDataByPage(Page page) {
		return this.settleaccountsParamDao.getSettleaccountsParamPageList(page);
	}

	@Override
	public SettleaccountsParam queryByParamNo(String paramNo) {
		return this.settleaccountsParamDao.selectByPrimaryKey(paramNo);
	}

	@Override
	public void delSettleaccountsParam(String paramNo) {
			this.settleaccountsParamDao.deleteByPrimaryKey(paramNo);

	}

	@Override
	public void saveOrUpdate(SettleaccountsParam param) {
			if(param.getParamNo()==null || "".equals(param.getParamNo())){
				    param.setParamNo(UUID.randomUUID().toString().replace("-",""));
					this.settleaccountsParamDao.insert(param);
			}else{
					this.settleaccountsParamDao.updateByPrimaryKeySelective(param);
			}

	}

}
