package com.gome.gmhx.dao.materialmanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxCurrentStock;
import com.gome.gmhx.entity.HxMaterialDetail;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.vo.HxMaterialDetailVO;
import com.gome.gmhx.entity.vo.HxMaterialManageVO;

@Repository("hxMaterialDao")
public interface HxMaterialDao {

	List<String> getFittingBySuit(String suitModel,String faultCode);

	Map<String, Object> getFittingInfo(Map<String, Object> map);

	void addHxMaterialManage(HxMaterialManage hxMaterialManage);

	void addHxMaterialDetail(List<HxMaterialDetail> list);

	String getListNumber(String companyId);
	
	/**
	 * 查询当天该机构人下发货量
	 * @param companyId
	 * @return
	 */
	String getSendNumber(String companyId);

	/** 查询申请单基础数据*/
	HxMaterialManage getHxMaterialManage(String listNumber);
	
	/**根据listNumber查询申请单信息，其中创建人，修改人是关联过hx_user_unload的显示值*/
	HxMaterialManage getHxMaterialManageShow(String listNumber);

	/*** 根据分部机构查询上一级(总部)机构*/
	String getFatherFittingOrgId(String fittingOrgId);
	
	/*** 根据分部机构查询下一级网点机构*/
	List<Map<String, String>> getWebFittingOrgByFatherId(String fittingOrgId);
	
	String getWebFittingOrgId(String orgId);

	List<Map<String, Object>> getHxMaterialPageList(Page page);
	
	/**
	 * 定物料岗 查询邮包收发货历史
	 * @param page
	 * @return
	 */
	List<Map<String, Object>> getHxParcelPageList(Page page);//TODO 从已经完成的，和未完成的工作流中取，而不是所有的工作流了
	
	/**
	 * 查询配件申请历史
	 * @param page
	 * @return
	 */
	List<Map<String, Object>> getHxMaterialHistoryPageList(Page page);
	
	List<Map<String, Object>> getHxMaterialDetail(String listNumber);
	
	/**查询申请单中配件的收发货单位库存，公适用于新料(申请)*/
	List<Map<String, Object>> getHxMaterialStock(String listNumber);
	
	List<Map<String, Object>> getHxMaterialDetail2(Map<String, Object> map);

	void delHxMaterialDetail(String listNumber);

	void delHxMaterialManage(String listNumber);

	void updateStatus(Map<String, String> map);

	void insertRkCount(Map<String, Object> newMap);
	
	void updateRkCount(Map<String, Object> newMap);

	void updateCkCount(Map<String, Object> map);

	HxCurrentStock isExist(Map<String, Object> map);

	List<Map<String, Object>> getHxMaterialDetailForUpdate(Map<String, Object> map);

	void updateHxMaterialManage(HxMaterialManage manage);
	
	@Deprecated
	void updateHxMaterialDetail(HxMaterialDetail materialDetail);

	Float getFittingPrice(HxMaterialDetail hxMaterialDetail);

	/**
	 * 新料根据调拨根据分部申请号 处于S6(出库)状态
	 * @param listNumber
	 * @return
	 */
	List<Map<String, Object>> getHxMaterialDetail3(String listNumber);
	
	/** 根据listNumber查询带有发货，收货公司名称的申请单记录*/
	HxMaterialManage getHxMaterialShow(String listNumber);

	
	/** 获取申请单出库批次号总数	 */
	public Long getBatchNumber();
	
	public void updateIncrementBatchNumber();

	List<Map<String, Object>> exportMP08Excel(String listNumber);

	List<Map<String, Object>> exportBatchMP08Excel(Map<String, Object> map);

	HxMaterialManageVO getMaterialManageByNumber(String listNumber);

	List<HxMaterialDetailVO> getMaterialDetailByNumber(String listNumber);

	HxMaterialManageVO getMaterialByNumber(String listNumber);

	Long getListStockPrice(Map<String, Object> map);
	
}
