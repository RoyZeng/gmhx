/** 
 * @Project:     sy_oa  
 * @Title:       LogUtils.java 
 * @Package:     com.sy.common.util 
 * @Description: 日志工具类 
 * @author:      WangYandong   
 * @date:        2013-8-10 上午9:04:27 
 * @version:     v1.0
*/
package com.gome.common.util;

import org.apache.log4j.Logger;

public class LogUtils {

    public static final Integer DEBUG    = 1;
    public static final Integer INFO     = 2;
    public static final Integer WARN     = 3;
    public static final Integer ERROR    = 4;
    public static final Integer FATAL    = 5;

    private static Logger       log      = null;

    private static LogUtils     logUtils = new LogUtils();

    private LogUtils() {

    }

    /**
     * @Title:       getInstance 
     * @Description: 单例获取LogUtils对象
     * @return    
     * @return:      LogUtils
     * @throws:      
     * @author:      WangYandong
     * @date:        2013-8-10 上午9:27:42
     */
    public static LogUtils getInstance() {
        return logUtils;
    }

    /**
     * @Title:       log 
     * @Description: 获取日志信息，默认级别为INFO
     * @param clazz
     * @param message    
     * @return:      void
     * @throws:      
     * @author:      WangYandong
     * @date:        2013-8-10 上午9:14:36
     */
    public void log(Class<?> clazz, String message) {
        log = Logger.getLogger(clazz);
        log.info(message);
    }

    /**
     * @Title:       log 
     * @Description: 获取指定级别日志信息，如果level值为空，默认置为INFO级别日志
     * @param level
     * @param clazz
     * @param message    
     * @return:      void
     * @throws:      
     * @author:      WangYandong
     * @date:        2013-8-10 上午9:14:42
     */
    public void log(Integer level, Class<?> clazz, String message) {
        log = Logger.getLogger(clazz);
        if (null == level)
            level = 2;
        switch (level) {
            case 1:
                log.debug(message);
                break;
            case 2:
                log.info(message);
                break;
            case 3:
                log.warn(message);
                break;
            case 4:
                log.error(message);
                break;
            case 5:
                log.fatal(message);
                break;
            default:
                log.info(message);
        }
    }

    /**
     * @Title:       log 
     * @Description: 获取日志信息，默认级别为ERROR
     * @param clazz
     * @param message
     * @param e    
     * @return:      void
     * @throws:      
     * @author:      WangYandong
     * @date:        2013-8-10 上午9:18:01
     */
    public void log(Class<?> clazz, String message, Throwable e) {
        log = Logger.getLogger(clazz);
        log.error(message);
    }

    /**
     * @Title:       log 
     * @Description: 获取指定级别日志信息，只接收ERROR和FATAL级别的日志
     * @param level
     * @param clazz
     * @param message
     * @param e    
     * @return:      void
     * @throws:      
     * @author:      WangYandong
     * @date:        2013-8-10 上午9:22:24
     */
    public void log(Integer level, Class<?> clazz, String message, Throwable e) {
        if (null == level)
            level = 4;
        log = Logger.getLogger(clazz);
        switch (level) {
            case 4:
                log.error(message);
                break;
            case 5:
                log.fatal(message);
                break;
            default:
                log.error(message);
        }
    }

}
