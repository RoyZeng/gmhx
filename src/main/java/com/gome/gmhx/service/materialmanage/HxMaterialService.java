package com.gome.gmhx.service.materialmanage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxMaterialDetail;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.vo.HxMaterialDetailVO;
import com.gome.gmhx.entity.vo.HxMaterialManageVO;

public interface HxMaterialService {

	List<String> getFittingBySuit(String suitModel,String faultCode);

	Map<String, Object> getFittingInfo(Map<String, Object> map);

	/**
	 * 总部新料采购
	 * @param map
	 */
	void addHxMaterial(Map<String, Object> map);

	String getListNumber(String companyId);

	HxMaterialManage getHxMaterialManage(String listNumber);
	
	HxMaterialManage getHxMaterialManageShow(String listNumber);

	/** 获取组织机构上级机构(系统岗) */
	String getFatherFittingOrgId(String fittingOrgId);

	List<Map<String, Object>> getHxMaterialPageList(Page page);
	
	List<Map<String, Object>> getHxMaterialHistoryPageList(Page page);
	
	List<Map<String, Object>> getHxParcelPageList(Page page);

	/**
	 * 查询配件信息的基本信息及库存（新料、旧料）及发货公司
	 * @param listNumber
	 * @return
	 */
	List<Map<String, Object>> getHxMaterialDetail(String listNumber);
	
	/**
	 * 查询配件信息的基本信息
	 * @param listNumber
	 * @return
	 */
	public List<Map<String, Object>> getHxMaterialDetails(String listNumber);
	
	public List<Map<String, Object>> getHxMaterialStock(String listNumber);

	void delHxMaterial(String listNumber);

	void updateStatus(Map<String, String> map);

	void updateCurrentStock(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> getHxMaterialDetailForUpdate(Map<String, Object> map);

	
	void updateHxMaterial(Map<String, Object> map);

	void hxMaterialSend(String listNumber, String type);

	void updateHxMaterialManage(String listNumber,String transactionpass, String type);

	/**
	 * 网点、分部新料申请/总部新料调拨 确认出库数量操作
	 * @param list
	 */
	void updateHxMaterialDetails(ArrayList<HxMaterialDetail> list);

	/**
	 * 网点、分部新料申请/总部新料调拨 出库操作
	 * @param listNumber
	 */
	void updateReduceStock(String listNumber);

	/**
	 * 网点申请根据fittingOrgId查询诎要售后公司，销售公司对应的配件库Code
	 * @param fittingOrgId
	 * @return
	 */
	String getWebFittingOrgId(String fittingOrgId);

	/*** 根据分部机构查询下一级网点机构*/
	List<Map<String, String>> getWebFittingOrgByFatherId(String fittingOrgId);
	
	/**
	 * 网点新料退回/旧料返回/来料不良 发货操作
	 * @param listNumber
	 */
	void updateDelivery(String listNumber);

	/**
	 * 网点新料退回/旧料检测/来料不良 检测操作
	 * @param listNumber
	 */
	void updateCheck(String listNumber);

	/**
	 * 网点新料退回/旧料返回/来料不良  收货操作
	 * @param listNumber
	 */
	void updateReceive(String listNumber);

	/**
	 * 网点新料退回/旧料返回/来料不良 入库操作
	 * @param listNumber
	 */
	void updateIn(String listNumber);

	/**
	 * 网点来料报失/保外销售, 分部旧料报废   出库操作
	 * @param listNumber
	 */
	void updateMaterialLostReduceStock(String listNumber);

	/**
	 * 分部库存转移  转移操作
	 * @param listNumber
	 */
	void updateMaterialMove(String listNumber);

	List<Map<String, Object>> getHxMaterialDetail3(String listNumber);
	
	HxMaterialManage getHxMaterialShow(String listNumber);
	
	/*
	 * 根据入库批次号
	 */
	public String getInBatch() ;
	
	/*
	 * 根据出库批次号
	 */
	public String getOutBatch();

	HxMaterialManageVO getMaterialManageByNumber(String listNumber);

	List<HxMaterialDetailVO> getMaterialDetailByNumber(String listNumber);

	HxMaterialManageVO getMaterialByNumber(String listNumber);

	/**
	 * 从申请单详细表里查询fittingCode的价格
	 */
	Long getListStockPrice(Map<String, Object> map);

}
