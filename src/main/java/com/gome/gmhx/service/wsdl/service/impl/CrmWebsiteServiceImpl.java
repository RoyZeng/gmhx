package com.gome.gmhx.service.wsdl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.gome.common.util.MD5EncryptUtils;
import com.gome.common.util.SysConstant;
import com.gome.common.util.SysUtils;
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.dao.wsdl.CrmWebsiteDao;
import com.gome.gmhx.entity.CrmWebsite;
import com.gome.gmhx.entity.CustomerserviceSaleMapping;
import com.gome.gmhx.entity.HxResetPassword;
import com.gome.gmhx.service.wsdl.service.CrmWebsiteService;

@Service("crmWebsiteService")
public class CrmWebsiteServiceImpl implements CrmWebsiteService {
	@Resource
	private CrmWebsiteDao crmWebsiteDao;

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public String insertResetPassword(String xmlData) {
		Document document = null;
		String fappaccount = "";
		String frandpwd = "";
		try {
			document = DocumentHelper.parseText(xmlData);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		Iterator iter = root.elementIterator("XML_DATA"); // 获取根节点下的子节点XML_DATA
		// 遍历head节点
		while (iter.hasNext()) {
			Element recordEle = (Element) iter.next();
			fappaccount = recordEle.elementTextTrim("fappaccount"); // 拿到XML_DATA节点下的子节点fappaccount值
			frandpwd = recordEle.elementTextTrim("frandpwd"); // 拿到XML_DATA节点下的子节点frandpwd值
		}

		HxResetPassword hxResetPassword = new HxResetPassword();
		String id = UUIDGenerator.getUUID();
		hxResetPassword.setId(id);
		hxResetPassword.setFappaccount(fappaccount);
		hxResetPassword.setFrandpwd(frandpwd);
		hxResetPassword.setFpassword(MD5EncryptUtils.MD5Encode(frandpwd));
		hxResetPassword.setFresetdate(new Date());
		
		StringBuffer sb = new StringBuffer();
		sb.append("<PASSWORD>");
		sb.append("<HEADER>");
		sb.append("<INTERFACE_ID>"+SysConstant.INTERFACE_ID+"</INTERFACE_ID>");
		sb.append("<MESSAGE_ID>"+SysUtils.getRandomCode()+"</MESSAGE_ID>");
		sb.append("<SENDER>"+SysConstant.SENDER+"</SENDER>");
		sb.append("<RECEIVER>"+SysConstant.RECEIVER+"</RECEIVER>");
		sb.append("</HEADER>");
		sb.append("<XML_DATA>");
		try{
			crmWebsiteDao.insertResetPassword(hxResetPassword);
			
			sb.append("<RESULT>"+SysConstant.SUCCESS+"</RESULT>");
			sb.append("<MESSAGE>"+SysConstant.SUCCESSMESSAGE+"</MESSAGE>");
		}catch(Exception e){
			sb.append("<RESULT>"+SysConstant.FAIL+"</RESULT>");
			sb.append("<MESSAGE>"+e.getMessage()+"</MESSAGE>");
		}
		
		sb.append("</XML_DATA>");
		sb.append("</PASSWORD>");
		String backXmlDatat = "";
		if(sb!=null){
			backXmlDatat = sb.toString();
		}
		return backXmlDatat;
	}

	@Override
	public void handleCrmwebsite(List<CrmWebsite> list) {
		List<Map<String, Object>> result = crmWebsiteDao.queryAllWebsiteList();
		List<String> iddataList = new ArrayList<String>();
		for(Map<String, Object> mapData : result){
			String azwd01 = (String) mapData.get("azwd01");
			String gsxx01 = (String) mapData.get("gsxx01");
			String id = "";
			if(StringUtils.isNotBlank(azwd01)&&StringUtils.isNotBlank(gsxx01)){
				id = azwd01 + "_" + gsxx01;
				iddataList.add(id);
			}
		}
		for(CrmWebsite crmWebsite : list){
			String partner = crmWebsite.getPartner();// 网点编码
			String sales_org = crmWebsite.getSales_org(); // 公司代码
			String id = partner + "_" + sales_org;
			if(iddataList.contains(id)){
				crmWebsiteDao.updateCrmwebsite(crmWebsite);
			}else{
				crmWebsiteDao.addCrmwebsite(crmWebsite);
			}
		}
		
	}

	@Override
	public void handleCustomerserviceSaleMapping(List<CustomerserviceSaleMapping> list) {
		List<String> saleComCodes = new ArrayList<String>();
		for(CustomerserviceSaleMapping csm:list){
			saleComCodes.add(csm.getZsales_org_2());
		}
		this.crmWebsiteDao.delSaleMappingBatch(saleComCodes);
		this.crmWebsiteDao.insertSaleMappingBatch(list);
	}

}
