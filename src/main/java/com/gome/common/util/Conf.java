package com.gome.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conf {
	static Properties pro = new Properties();
	static {
		InputStream in = Conf.class.getClassLoader().getResourceAsStream(
				"jsp_forward.properties");
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String k) {
		return pro.getProperty(k);
	}
}
