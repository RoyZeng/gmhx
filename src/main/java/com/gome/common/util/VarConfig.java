package com.gome.common.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;


public class VarConfig {
	private String oaAccount ;
	private String orgRealtion;
	
	private static Logger logger = Logger.getLogger(VarConfig.class);
	
	private static VarConfig instance = null;
	
	private VarConfig(){
	}
	
	public static VarConfig getInstance(){
		if (instance == null){
			String path = "";
			instance = new VarConfig();
			try {
//				path = WebUtils.getRealPath(ServletActionContext.getServletContext(), "/WEB-INF/classes/varconfig.xml");
				
//				ServletContext.getResourceAsStream("mypath/filename");				
//				ServletContext.getRealPath("mypath/filename");
				path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(SysConstant.Config.CONFIG_PATH);
				if(SysUtils.isEmptyString(path)){
					logger.info("varconfig.xml文件路径配置错误！");
				}
				File file = new File(path.toString());
				SAXReader reader = new SAXReader();   
				Document doc = reader.read(file);
				Element root = doc.getRootElement();
				Element current = null;
				if (root.elementText("current").equals("800")){
					current = root.element("e800");
				}else if(root.elementText("current").equals("500")){
					current = root.element("e500");
				}else if(root.elementText("current").equals("300")){
					current = root.element("e300");
				}else{
					System.out.println("当前变更配置出错！");
					return null;
				}
				// oa身份平台接口地址
				instance.setOaAccount(current.elementText("oaaccountlocator"));
				String orgurl = "http://webserviceUrlPort/XISOAPAdapter/MessageServlet?senderParty=&senderService=BS_GMGS&receiverParty=&receiverService=&interface=SI_FEEDBACK_Out&interfaceNamespace=http%3A%2F%2Fgome.com%2FGMGS%2FCOMMON%2FOutbound";
				//组织关系数据接口反馈地址
				instance.setOrgRealtion(orgurl.replace("webserviceUrlPort", current.elementText("orgrealtionlocator")));
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("读取varconfig.xml文件出错",e);
				instance = null;
			}
		}
		return instance;
	}

	
	public String getOaAccount() {
		return oaAccount;
	}

	public void setOaAccount(String oaAccount) {
		this.oaAccount = oaAccount;
	}
	
	public String getOrgRealtion() {
		return orgRealtion;
	}

	public void setOrgRealtion(String orgRealtion) {
		this.orgRealtion = orgRealtion;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
		new String[]{"applicationContext_bean.xml",
				"applicationContext_other.xml",
				"applicationContext.xml"});
		System.out.println(VarConfig.getInstance().getOaAccount());
	}
	
}
