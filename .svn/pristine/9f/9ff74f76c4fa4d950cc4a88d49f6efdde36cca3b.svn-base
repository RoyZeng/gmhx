/** 
 * @Project:     sy_oa  
 * @Title:       UUIDGenerator.java 
 * @Package:     com.sy.common.util 
 * @Description: UUID工具类 
 * @author:      WangYandong   
 * @date:        2013-8-10 上午7:31:29 
 * @version:     v1.0
 */
package com.gome.common.util;

import java.util.UUID;

public class UUIDGenerator {

    private UUIDGenerator() {

    }

    /**
     * NUM_EIGHT
     */
    public static final int NUM_EIGHT    = 8;

    /**
     * NUM_TENTHREE
     */
    public static final int NUM_TENTHREE = 13;

    /**
     * NUM_NINE
     */
    public static final int NUM_NINE     = 9;

    /**
     * NUM_TENFOUR
     */
    public static final int NUM_TENFOUR  = 14;

    /**
     * NUM_TENEIGHT
     */
    public static final int NUM_TENEIGHT = 18;

    /**
     * NUM_TENNINE
     */
    public static final int NUM_TENNINE  = 19;

    /**
     * NUM_TWOTHREE
     */
    public static final int NUM_TWOTHREE = 23;

    /**
     * NUM_TWOFOUR
     */
    public static final int NUM_TWOFOUR  = 24;

    /**
     * @Title:       getUUID 
     * @Description: 获取UUID
     * @return    
     * @return:      String
     * @throws:      
     * @author:      WangYandong
     * @date:        2013-8-10 上午8:07:31
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        /**
         * 去掉"-"符号
         */
        String temp = str.substring(0, NUM_EIGHT) + str.substring(NUM_NINE, NUM_TENTHREE)
                      + str.substring(NUM_TENFOUR, NUM_TENEIGHT)
                      + str.substring(NUM_TENNINE, NUM_TWOTHREE) + str.substring(NUM_TWOFOUR);
        return temp;
    }

    /**
     * @Title:       getUUID 
     * @Description: 获得指定数量的UUID
     * @param number
     * @return    
     * @return:      String[]
     * @throws:      
     * @author:      WangYandong
     * @date:        2013-8-10 上午8:07:46
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

    /**
     * @Description：  Test
     * @author: QIJJ 
     * @since: 2014-2-24 下午9:36:16
     */
    public static void main(String[] args) {
        System.out.println(getUUID());
    }

}