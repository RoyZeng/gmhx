package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxFeeMoveMachine;

@Repository("hxMoveChangeCheckDao")
public interface HxMoveChangeCheckoutDao {
	

	public List<Map<String, Object>> getHxMoveChangePageList(Page page);

	/**
	 * 新建移换机结算标准信息
	 */
	public void addMoveChangeCheckout(HxFeeMoveMachine hxFeeMoveMachine);
	
	/**
	 * 通过id删除移换机
	 * @param id id
	 */
	public void deleteMoveCheckout(String id);

	/**
	 * 通过feeID检索Checkout
	 * @param feeID
	 */
	public HxFeeMoveMachine getCheckoutById(String feeID);

	/**
	 * 为了Update视图：通过feeID检索Checkout
	 * @param feeID
	 */
	public HxFeeMoveMachine getUpdateCheckoutById(String feeID);
	
	public void updateCheckout(HxFeeMoveMachine checkout);

	public List<Map<String, Object>> getCheckoutExport(HxFeeMoveMachine checkout);
	
	void insertFeeMoveMachineBatch(List<Object> list);


}
