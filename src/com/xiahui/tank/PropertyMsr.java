package com.xiahui.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/5/4
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class PropertyMsr {
	private PropertyMsr(){}

	static Properties properties = new Properties();

	static {
		try {
			properties.load(PropertyMsr.class.getClassLoader().getResourceAsStream("Config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object getValue(String key){
		if (null == key) return null;
		return properties.get(key);
	}
	
	public static void main(String [] arr){
		Object initTankCount = PropertyMsr.getValue("initTankCount");
		System.out.println(initTankCount);
	}

}
