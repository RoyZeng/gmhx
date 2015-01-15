package gmhx.webservice.templete.test;


import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxrs.client.WebClient;

import com.gome.common.util.JsonUtil;

/**
 * @author wuzhe
 * @date 2014-7-10
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class PersonServiceTest {
    
    private static final String SERVICE_URL = "http://localhost:8080/gmhx/service";  
    private static final String NAME = " Tom ";  
    private static final String TYPE_JSON = "application/json";

    public static void main(String[] args) {
        testAddCategory(TYPE_JSON);
    }
    
    private static void testAddCategory(final String format) {  
        
        try {
            WebClient client = WebClient.create(SERVICE_URL);  
            client.path("/personService/sayHello/").accept(  
                    format).type(format); 
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("echostr", "19786720");
            map.put("timestamp", "1392270906044");
            map.put("appID", "28e3e1baf2cb4b999368f6927c1983a2");
            map.put("secretStr", "bcfde4f85eb744ec9f6482b7e877c5fe");
            map.put("signature", "6b10caf48714bbeb01538ad22b3b8b917d601825");
            map.put("name", NAME);
            String post = client.post(JsonUtil.javaObjectToJsonString(map),String.class);
            System.out.println(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
  
  
    }  
}
