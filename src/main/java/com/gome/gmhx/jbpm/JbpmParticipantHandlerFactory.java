package com.gome.gmhx.jbpm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class JbpmParticipantHandlerFactory {
	
	private static Map<String,JbpmParticipantHandler>  handlers =  new HashMap<String,JbpmParticipantHandler>();
	
	static{
		Properties prop = new Properties();
		try {
			prop.load(JbpmParticipantHandlerFactory.class.getResourceAsStream("handlerMapping.properties"));
			for(Map.Entry<Object,Object> entry:prop.entrySet()){
				String processDefinationKey = (String)entry.getKey();
				String className = (String)entry.getValue();
				handlers.put(processDefinationKey, (JbpmParticipantHandler)Class.forName(className).newInstance());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static JbpmParticipantHandler getInstance(String processDefinationKey){
		return handlers.get(processDefinationKey);
	}

}
