package com.gome.gmhx.webservice.template.impl;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.gome.common.util.JsonUtil;
import com.gome.gmhx.service.PersonService;
import com.gome.gmhx.webservice.template.PersonWSService;


/**
 * 
 * @author wuzhe
 * @date 2014-7-10
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Path("/")
@Produces({"application/json","application/xml"})
public class PersonWSServiceImpl implements PersonWSService {
	private PersonService personService;
    
    /**
     * 获取personservice
     * @return personservice
     */
    public PersonService getPersonService() {
        return personService;
    }

    /**
     * 设置wlservice
     * @param wlservice wlservice
     */
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

	@Override
    @POST
    @Path("/sayHello")
    @Produces({"application/json","application/xml"})
    public Response sayHello(String jsonParam) {
        String result =null;
        Map map = null;
		try {
			map = JsonUtil.jsonStringToMap(jsonParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
        String name = (String)map.get("name");
        result = personService.sayHello(name);
        return Response.ok(result).build();
    }
}
