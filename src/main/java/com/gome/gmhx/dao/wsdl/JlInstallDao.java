package com.gome.gmhx.dao.wsdl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.JlInstall;

@Repository("installDao")
public interface JlInstallDao {
	
	public List<Map<String, Object>> getInstallPageList(Page page);
	
	public List<Map<String, Object>> getInstallList(JlInstall param);
	
	public void insertInstall(JlInstall install);
	
	public JlInstall selectInstallByPrimaryKey(BigDecimal installId);
	
	public void updateInstallByPrimaryKey(JlInstall install);
	
	public void deleteInstallByPrimaryKey(BigDecimal installId);

}
