package com.gome.gmhx.webservice.template;

import javax.ws.rs.core.Response;
/**
 * 
 * @author wuzhe
 * @date 2014-7-10
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface PersonWSService {
    
    public Response sayHello(String jsonParam);
}
