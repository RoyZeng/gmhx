package com.gome.gmhx.dao.wsdl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.EccBrand;
import com.gome.gmhx.entity.EccGoods;
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.EccRegional;
import com.gome.gmhx.entity.EccRegionalSection;
import com.gome.gmhx.entity.EccSalesOrg;
import com.gome.gmhx.entity.EccSectionMapping;
import com.gome.gmhx.entity.EccStore;

@Repository("eccDao")
public interface EccDao {
	void clearEccBrand();
	void insertEccBrand(List<EccBrand> list);
	
	void replaceEccGoods(List<EccGoods> list);
	
	void clearEccGoodsCategory();
	void insertEccGoodsCategory(List<EccGoodsCategory> list);
	
	void clearEccRegional();
	void insertEccRegional(List<EccRegional> list);
	
	void clearEccRegionalSection();
	void insertEccRegionalSection(List<EccRegionalSection> list);
	
	void clearEccSalesOrg();
	void insertEccSalesOrg(List<EccSalesOrg> list);
	
	void clearEccSectionMapping();
	void insertEccSectionMapping(List<EccSectionMapping> list);
	
	void clearEccStore();
	void insertEccStore(List<EccStore> list);
	List<Map> getEccGoodsCategory(Map param);
	
	public Map getEccGoodsByGoodsCode(String goodsCode);
	List<Map<String, Object>> getGoodClassPageList(Page page);
	List getFaultByCategory(String category);
	void deleteEccRegionalByBzirks(String ids);
	void deleteEccRegionalSectionByVkburs(String ids);
	void deleteEccSalesOrgByPrimary(List<EccSalesOrg> list);
	void deleteEccSectionMappingByVkgrps(String ids);
	void deleteEccStoreByWerkss(String ids);
	
	EccGoods getEccGoodsByMATKL(String MATKL);
}
