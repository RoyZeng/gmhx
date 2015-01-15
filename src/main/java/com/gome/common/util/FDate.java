package com.gome.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class FDate implements Cloneable{
	private static Logger logger = Logger.getLogger(FDate.class);
    private static final String pattern = "yyyy-MM-dd";
    private static final String pattern1 = "yyyyMMdd";
    private static SimpleDateFormat df;
    private static SimpleDateFormat df1;
    
   static{
        df = new SimpleDateFormat(pattern);
        df1 = new SimpleDateFormat(pattern1);
    }

    public  Object clone()
            throws CloneNotSupportedException
    {
        FDate cloned = (FDate)super.clone();
        return cloned;
    }

    public static Date getDate(String dateString)
    {
        Date tDate = null;
        try
        {
            if (dateString.indexOf("-") != -1)
            {
                tDate = df.parse(dateString);
            }
            else
            {
                tDate = df1.parse(dateString);
            }
        }
        catch (Exception e)
        {
        	return null;
        }
        return tDate;
    }

    public static String getString(Date mDate)
    {
        String tString = null;
        if (mDate != null)
        {
            tString = df.format(mDate);
        }
        return tString;
    }
}
