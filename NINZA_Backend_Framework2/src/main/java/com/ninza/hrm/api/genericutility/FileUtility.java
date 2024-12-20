package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.util.Properties;
/**
 * Utility class for reading data from properties files.
 *@author JAGADISH
 */
public class FileUtility implements IPathConstants {
/**
 * Reads a value from properties file based on the given key.
 * @param key The key whose value need to be fetched
 * @return It will return the value corresponding to the key.
 */
	public String getDataFromPropetyFile(String key) {
		Properties p=null;
	try {
		FileInputStream fis=new FileInputStream("./config-env-data/configEnvdata.properties");
		p=new Properties();
		p.load(fis);
	} catch (Exception e) {
		e.printStackTrace();
	}
	String data = p.getProperty(key);
	return data;

	
	}
}
