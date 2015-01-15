package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxFeeMoveMachine;

public interface HxMoveChangeCheckoutService {

	/**
	 * 浏览移换机结算标准资料
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> getHxMoveChangePageList(Page page);
	
	/**
	 * 添加机器结算标准
	 * @param feeMoveMachine
	 */
	public void addMoveChangeCheckout(HxFeeMoveMachine feeMoveMachine);

	/**
	 * 通过feeID检索Checkout
	 * @return HxFeeMoveMachine
	 */
	public HxFeeMoveMachine getCheckoutById(String feeID);

	/**
	 * 为了Update视图：通过feeID检索Checkout
	 */
	HxFeeMoveMachine getUpdateCheckoutById(String feeID);
	
	public void updateCheckout(HxFeeMoveMachine checkout) ;
	
	public void deleteMoveCheckout(String idString);

	public List<Map<String, Object>> getCheckoutExport(HxFeeMoveMachine checkout);



}
