package com.gome.gmhx.webservice.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.gome.common.util.AccessTokenUtil;
import com.gome.common.util.BussinessGlossary;
import com.gome.common.util.JsonUtil;
/**
 * WebService服务自动认证
 * @author Zhang.Mingji
 * @date 2014-2-13下午3:19:47
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class WSMethodAuthenticationInteceptor implements MethodInterceptor, InitializingBean {

    public static Logger logger = LoggerFactory.getLogger(WSMethodAuthenticationInteceptor.class);
    
    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
    	 return invocation.proceed();
//        try {
//            Method method = invocation.getMethod();
//            String targetName = method.getDeclaringClass().getName();
//            String methodName = method.getName();
//            
//            logger.info("----------------------------- log start --------------------------------------");
//            logger.info("-----拦截类 " + targetName + " 方法 " + methodName + "-------");
//            
//            Object[] arguments = invocation.getArguments();
//            String paramJson = (String) arguments[0];
//            logger.info("------输入参数Json：" + paramJson + "------");
//            logger.info("----------------------------- log end --------------------------------------");
//            Map map = JsonUtil.jsonStringToMap(paramJson);
//            if(AccessTokenUtil.checkIsValidateUser(map)){
//                return invocation.proceed();
//            }else{
//                return Response.ok(BussinessGlossary.WS_SERVICE_NOT_FOUND).build();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.ok(BussinessGlossary.WS_SERVICE_NOT_FOUND).build();
//        }
        
    }

}
