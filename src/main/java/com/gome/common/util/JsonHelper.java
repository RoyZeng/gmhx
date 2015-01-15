package com.gome.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * @ClassName JsonHelper
 * @Description:  JSON帮助类
 * @author: QIJJ  
 * @since: 2014-3-16 下午4:23:17
 */
public class JsonHelper {

    public static String formatSquare(String s) {
        String regEx = "\\[|\\]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);

        return m.replaceAll("");
    }

    /**
     * 将java对象转换成JSON字符串
     * 
     * @param javaObj
     * @param dataFormat
     * @return
     */
    public static JSONArray getJsonString4Object(Object obj) {
        JSONArray jsonArray = JSONArray.fromObject(obj);
        return jsonArray;
    }

    /**
     * 将java对象转换成JSON字符串,并设定日期格式
     * 
     * @param javaObj
     * @param dataFormat
     * @return
     */
    public static JSONArray getJsonString4Object(Object obj, String dataFormat) {
        JsonConfig jsonConfig = configJson(dataFormat);
        JSONArray jsonArray = JSONArray.fromObject(obj, jsonConfig);
        return jsonArray;
    }

    /***
     * 将对象转换为HashMap
     * 
     * @param object
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static HashMap toHashMap(Object object) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        JSONObject jsonObject = toJSONObject(object);
        Iterator it = jsonObject.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }

        return data;
    }

    /***
     * 将对象转换为JSON对象
     * 
     * @param object
     * @return
     */
    public static JSONObject toJSONObject(Object object) {
        return JSONObject.fromObject(object);
    }

    /**
     * JSON 时间解析器具
     * 
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String datePattern) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] { "" });
        jsonConfig.setIgnoreDefaultExcludes(false);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(datePattern));

        return jsonConfig;
    }

    /**
     * 除去不想生成的字段（特别适合去掉级联的对象）+时间转换
     * 
     * @param excludes
     *            除去不想生成的字段
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String[] excludes, String datePattern) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        jsonConfig.setIgnoreDefaultExcludes(false);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(datePattern));

        return jsonConfig;
    }

    /**
     * @Description：  
     * @author: QIJJ 
     * @since: 2014-3-16 下午4:24:49
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List getList4Json(String jsonString, Class pojoClass) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;

        List list = new ArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject, pojoClass);
            list.add(pojoValue);
        }
        
        return list;
    }

}
