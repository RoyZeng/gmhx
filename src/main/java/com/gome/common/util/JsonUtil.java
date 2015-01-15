package com.gome.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.GsonBuilder;

/**
 * 
 * @Filename: JsonUtil.java
 * @Version: 1.0
 * @Author: WangYandong
 * 
 */
@SuppressWarnings("all")
public final class JsonUtil {

    private static Logger log = Logger.getLogger(JsonUtil.class);

    /**
     * 将Java对象序列化成JSON字符串。
     * 
     * @param obj
     * @return
     */
    public static final String toGJson(Object obj) {
        if (obj == null)
            return null;
        try {
            GsonBuilder gb = new GsonBuilder();
            gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
            return gb.create().toJson(obj);
        } catch (Exception e) {
            log.warn("Can not serialize object to json", e);
            return null;
        }
    }

    /**
     * 应用于Jquery Easy UI datagrid(pagination:true)
     * 
     * @param Response
     * @param listCount
     * @param obj
     * @throws Exception
     */
    public static void writeJsonToDataGrid(HttpServletResponse Response, int listCount, Object obj) throws Exception {
        Response.setCharacterEncoding("UTF-8");
        new JSONBuilder(Response.getWriter()).object().key("total").value(listCount).key("rows")
            .value(JsonHelper.getJsonString4Object(obj, "yyyy-MM-dd HH:mm:ss")).endObject();
    }

    public static String writeListToDataGrid(int listCount, List<Map<String, Object>> list) throws Exception {
    	JSONObject object = new JSONObject();
    	if(list==null)
    		list=new ArrayList<Map<String, Object>>();
    	object.put("total", listCount);
    	object.put("rows", JsonHelper.getJsonString4Object(toGJson(list), "yyyy-MM-dd HH:mm:ss"));
        return object.toString();
    }

    /**
     * @param o
     * @return
     */
    public static String toJson(Object o) {
        if (o instanceof Map) {
            return toJson((Map) o);
        } else if (o instanceof List) {
            return toJson((List) o);
        } else {
            return null;
        }
    }

    /**
     * @param map
     * @return
     */
    public static String toJson(Map map) {
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuffer sb = new StringBuffer("{");
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object object = map.get(key);
            if (object instanceof String) {
                sb.append("\"").append(key).append("\"").append(":\"").append(object).append("\",");
            } else if (object instanceof Date) {
                sb.append("\"").append(key).append("\"").append(":\"")
                    .append(DateUtils.formatDateTime((Date) object)).append("\",");
            } else if (object instanceof Map) {
                sb.append("\"").append(key).append("\"").append(":").append(toJson((Map) object))
                    .append(",");
            } else if (object instanceof List) {
                sb.append("\"").append(key).append("\":").append(toJson((List) object)).append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1).append("}");
        return sb.toString().replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * @param list
     * @return
     */
    public static String toJson(List list) {
        if (list.isEmpty()) {
            return "[]";
        }
        StringBuffer sb = new StringBuffer("[");
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Object object = (Object) iterator.next();
            if (object instanceof Map) {
                Map dataMap = (Map) object;
                sb.append(toJson(dataMap)).append(",");
            } else if (object instanceof String) {
                sb.append("\"").append(object).append("\"").append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1).append("]");
        return sb.toString();
    }

    /**
     * Json字串转HashMap
     * 
     * @param obj
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static HashMap JsonStr2HashMap(Object obj) throws Exception {

        return JsonHelper.toHashMap(obj);

    }

    /**
     * HashMap转Json字串
     * 
     * @param m
     * @return
     * @throws Exception
     */
    public static String HashMap2JsonStr(Map<String, Object> m) throws Exception {

        return JsonHelper.formatSquare(JsonHelper.getJsonString4Object(m).toString());
    }

    /**
     * combobox
     * 
     * @param Response
     * @param
     * @param obj
     * @throws Exception
     */
    public static void writeJsonToCombo(HttpServletResponse Response, Object obj) throws Exception {

        Response.setCharacterEncoding("UTF-8");

        String json = JsonHelper.getJsonString4Object(obj, "yyyy-MM-dd HH:mm").toString();

        Response.getWriter().append(json);

    }

    @SuppressWarnings("rawtypes")
    public static List getList4Json(String jsonString, Class pojoClass) {
        return JsonHelper.getList4Json(jsonString, pojoClass);
    }
    
    /**
     * 获取到Map值；
     * 
     * @param jsonString
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static Map jsonStringToMap(String jsonString) throws Exception {
        Map hashMap = new HashMap();
        if (null == jsonString) {
            return null;
        }
        try {
            hashMap = (Map) getJsonStringToObject(jsonString, HashMap.class);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
        return hashMap;
    }
    
    /**
     * JsonString 转换成对应Object
     * 
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static Object getJsonStringToObject(String jsonString, Class cls) throws Exception {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        return mapper.readValue(jsonString, cls);
    }
    
    /**
     * 得到Json串
     * 
     * @param object
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static String javaObjectToJsonString(Object object) throws Exception {
        String jsonString = "";
        if (null != object) {
            try {
                JsonFactory factory = new JsonFactory();
                ObjectMapper mapper = new ObjectMapper(factory);
                jsonString = mapper.writeValueAsString(object);
            } catch (Exception e) {
                e.printStackTrace();
                jsonString = "";
            }
        }
        return jsonString;
    }
}
