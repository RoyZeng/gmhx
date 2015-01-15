package com.gome.gmhx.service.basicdata.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.gmhx.dao.basicdata.HxProductClassifyDao;
import com.gome.gmhx.entity.HxProductClassify;
import com.gome.gmhx.service.basicdata.HxProductClassifyService;

@Service("hxProductClassifyService")
public class HxProductClassifyServiceImpl implements HxProductClassifyService {
	
	@Resource
	private HxProductClassifyDao hxProductClassifyDao;
	
	@Override
	public List<HxProductClassify> getHxProductClassifyTree() {
		return hxProductClassifyDao.getHxProductClassifyTree();
	}

	@Override
	public void createHxProductClassify(HxProductClassify hxProductClassify) {
		hxProductClassifyDao.createHxProductClassify(hxProductClassify);
	}

	@Override
	public void updateHxProductClassify(Map<String, Object> map) {
		hxProductClassifyDao.updateHxProductClassify(map);		
	}

	@Override
	public void delHxProductClassify(String classifyCode) {
		hxProductClassifyDao.delHxProductClassify(classifyCode);
	}

	@Override
	public List<Map> getHxProductClassifyTreeData(String roleId) {
		// TODO Auto-generated method stub
		return hxProductClassifyDao.getHxProductClassifyTreeData(roleId);
	}

}
