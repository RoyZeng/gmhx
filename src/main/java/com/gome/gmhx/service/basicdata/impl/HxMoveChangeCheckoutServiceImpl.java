package com.gome.gmhx.service.basicdata.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxMoveChangeCheckoutDao;
import com.gome.gmhx.dao.basicdata.HxPostageDao;
import com.gome.gmhx.entity.HxFeeMoveMachine;
import com.gome.gmhx.service.basicdata.HxMoveChangeCheckoutService;

@Service("hxMoveChangeCheckoutService")
public class HxMoveChangeCheckoutServiceImpl implements	HxMoveChangeCheckoutService {

	@Resource
	private HxMoveChangeCheckoutDao hxMoveChangeCehckouteDao;
	
	@Override
	public List<Map<String, Object>> getHxMoveChangePageList(Page page) {
		return hxMoveChangeCehckouteDao.getHxMoveChangePageList(page);
	}

	@Override
	public void addMoveChangeCheckout(HxFeeMoveMachine feeMoveMachine) {
		hxMoveChangeCehckouteDao.addMoveChangeCheckout(feeMoveMachine);
	}

	@Override
	public HxFeeMoveMachine getCheckoutById(String feeID) {
		return hxMoveChangeCehckouteDao.getCheckoutById(feeID);
	}
	
	@Override 
	public HxFeeMoveMachine getUpdateCheckoutById(String feeID){
		return hxMoveChangeCehckouteDao.getUpdateCheckoutById(feeID);
	}

	@Override
	public void updateCheckout(HxFeeMoveMachine checkout) {
		hxMoveChangeCehckouteDao.updateCheckout(checkout);
	}

	/**
	 * @param idString 用,分隔的要删除的id
	 */
	@Override
	public void deleteMoveCheckout(String idString) {
		String []ids = idString.split(",");
		for (String id : ids) {
			hxMoveChangeCehckouteDao.deleteMoveCheckout(id);
		}
	}

	@Override
	public List<Map<String, Object>> getCheckoutExport(HxFeeMoveMachine checkout) {
		return hxMoveChangeCehckouteDao.getCheckoutExport(checkout);
	}

	
	
}
