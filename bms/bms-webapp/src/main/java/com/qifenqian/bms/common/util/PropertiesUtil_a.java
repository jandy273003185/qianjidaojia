package com.qifenqian.bms.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 配置文件单例
 */

public class PropertiesUtil_a {

   
	private static Properties properties = null;
	private static Properties toPayProperties = null;
	public static Properties  getProperties(){
		
		if(properties == null){
			synchronized(PropertiesUtil_a.class){
				if(properties == null){
				InputStream	inputStream = PropertiesUtil_a.class.getResourceAsStream("/uploadFileConfig.properties");
				try {
					properties = new Properties();
					properties.load(inputStream);
										
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(inputStream!= null){
						try {
							inputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
					
				}
			}
		}
		
		return properties;
	}
	
	public static Properties  getProperties(String fileName){
		
		if(toPayProperties == null){
			synchronized(PropertiesUtil_a.class){
				if(toPayProperties == null){
				InputStream	inputStream = PropertiesUtil_a.class.getResourceAsStream(fileName);
				try {
					toPayProperties = new Properties();
					toPayProperties.load(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(inputStream!= null){
						try {
							inputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
					
				}
			}
		}
		
		return toPayProperties;
	}
}
