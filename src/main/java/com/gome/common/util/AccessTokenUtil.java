package com.gome.common.util;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 用户验证用户的请求是否合法
 * @author Zhang.Mingji
 * @date 2014-2-9下午3:35:46
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class AccessTokenUtil {
    /**
     * 用户验证用户
     * @param timestamp 时间戳
     * @param echostr 随机数
     * @param appID 应用ID
     * @param secretStr 应用密钥
     * @param signature 签名
     * @return
     */
    public static boolean checkIsValidateUser(String timestamp, String echostr, String appID, String secretStr, String signature){
        
        String[] arrTmp = {appID, secretStr, timestamp, echostr};
        Arrays.sort(arrTmp);
        String tmpStr = StringUtils.join(arrTmp, "");
        tmpStr = DigestUtils.shaHex(tmpStr);
        tmpStr = tmpStr.toLowerCase();
        if(tmpStr.equals(signature)){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 用户验证用户
     * @param map
     * @return
     */
    public static boolean checkIsValidateUser(Map map){
        String timestamp = (String) map.get(BussinessGlossary.TOKEN_PARAM_TIMESTAMP);
        String echostr = (String) map.get(BussinessGlossary.TOKEN_PARAM_ECHOSTR);
        String appID = (String) map.get(BussinessGlossary.TOKEN_PARAM_APPID);
        String secretStr = (String) map.get(BussinessGlossary.TOKEN_PARAM_SECRETSTR);
        String signature = (String) map.get(BussinessGlossary.TOKEN_PARAM_SIGNATURE);
        return checkIsValidateUser(timestamp, echostr, appID, secretStr, signature);
    }
    
}
