package com.gome.common.bean;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.aop.AfterReturningAdvice;

import com.gome.common.Constrants;
import com.gome.common.annotation.MaintenanceCode;
import com.gome.gmhx.dao.basicdata.HxCodeDao;
import com.gome.gmhx.entity.HxCode;

public class MaintenanceCodeAdvice implements AfterReturningAdvice{
	@Resource
	private HxCodeDao hxCodeDao;

	@Override
	public void afterReturning(Object returnObj, Method method, Object[] obj, Object target) throws Throwable {
		MaintenanceCode annotation = method.getAnnotation(MaintenanceCode.class);
		if(annotation == null) return;
		String codeKey = annotation.codeKey();
		if("".endsWith(codeKey)){
			codeKey = String.valueOf(obj[0]);
		}
		if(Constrants.CODEMAP.get(codeKey) == null || Constrants.CODEMAP.get(codeKey).isEmpty()){
			throw new Exception("维护代码map的值不存在:" + codeKey);
		}else{
			Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
				 public int compare(String o1, String o2) {  
		                return o1.compareTo(o2);  
		         }     
				
			});
			Constrants.CODEMAP.remove(codeKey);
			List<HxCode> list = hxCodeDao.getCodeByKey(codeKey);
			for(HxCode hxCode : list){
				map.put(hxCode.getCodeKey(), hxCode.getCodeValue());
			}
			Constrants.CODEMAP.put(codeKey, map);
		}
	}

}
