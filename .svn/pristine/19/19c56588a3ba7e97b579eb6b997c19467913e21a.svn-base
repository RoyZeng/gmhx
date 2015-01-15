package com.gome.gmhx.service.materialmanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxOrganization;
import com.gome.gmhx.entity.HxParcelDelivery;
import com.gome.gmhx.entity.vo.HxMaterialDetailVO;
import com.gome.gmhx.entity.vo.HxParcelDelivery_MaterialDetailVO;
import com.gome.gmhx.entity.vo.HxParcelDelivery_MaterialDetailVO2;

public interface HxParcelDeliveryService {
	List<Map<String, Object>> getParcelDeliveryPageList(Page page);

	void addHxParcelDelivery(Map<String, Object> map, List<String> listNumbers);

	HxParcelDelivery getHxParcelDeliveryById(String parcelCode);
	
	HxParcelDelivery_MaterialDetailVO2 getHxParcelDeliveryById2(String parcelCode);

	List<Map<String, Object>> getParcelDeliveryExport(HxParcelDelivery parcel);

	HxOrganization getOrgnazationBySuit(String receiveUnit);

	List<Map<String, Object>> getApplayCodeList(Page page);

	void updateParcelDelivery(HxParcelDelivery parcel, List<String> listNumbers); // 更新

	HxParcelDelivery getsendParcelDeliveryShow(String parcelCode);

	HxParcelDelivery getsendById(String parcelCode); // 保存后，跳进界面，显示数据

	String getParcelCode(String number);

	String getListNumber(String companyId);

	List<Map<String, Object>> getParcelDeliveryDetail(String parcelCode);

	void delParcelDelivery(String parcelCode);

	/**
	 * 	 网点、分部新料申请,总部新料调拨 邮包发货
	 * 
	 * @param parcel
	 */
	void updateSendDelivery(String parcelCode);
	
	public HxParcelDelivery selectByPrimaryKey(String parcelCode);
	
	List<HxParcelDelivery_MaterialDetailVO> selectPDMDByParcelCode(String parcelCode);
	
	List<HxMaterialDetailVO> getMaterialDetail(String parcelCode);

}
