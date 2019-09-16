package com.singbox.persist.rdbms;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

import com.singbox.persist.rdbms.dsw.DataSourceProperties;
import com.singbox.persist.rdbms.dsw.DataSourceWrapper;

public class DataBaseFactory {

	// ---------------------------------------------------------------------------------------------------------------------------

	private final String SEP = "\n\n=================================================================================\n\n";

	private Map<String, DataSourceWrapper> dataSourceMap = new HashMap<String, DataSourceWrapper>();

	private static DataBaseFactory instanceDBF;

	// ---------------------------------------------------------------------------------------------------------------------------

	private DataBaseFactory() {

	}

	public static DataBase get(String key) throws Exception {
		if (instanceDBF == null) {
			instanceDBF = new DataBaseFactory();
		}

		return instanceDBF.getDataBase(key);
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	private DataBase getDataBase(String key) throws Exception {

		if (dataSourceMap.containsKey(key) == false) {

			dataSourceMap.put(key, buildDataSource(key));

		}

		return new DataBase(dataSourceMap.get(key), "box");
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	private DataSourceWrapper buildDataSource(String key) throws Exception {

//		System.err.println("System.getProperty(\"user.dir\") " + System.getProperty("user.dir"));

		Properties properties = loadProperties(key + "_jdbc.properties");

		// -------------------------------------------------------------------

		String path = "jdbc.";

		DataSourceProperties dataSourceProperties = new DataSourceProperties();

		dataSourceProperties.setDriverClassName(properties.getProperty(path + "driverClassName"));
		dataSourceProperties.setUrl(properties.getProperty(path + "url"));
		dataSourceProperties.setUserName(properties.getProperty(path + "userName"));
		dataSourceProperties.setUserPassword(properties.getProperty(path + "userPassword"));
		dataSourceProperties.setInitialSize(new Integer(properties.getProperty(path + "initialSize")));
		dataSourceProperties.setMaxActive(new Integer(properties.getProperty(path + "maxActive")));
		dataSourceProperties.setMaxIdle((new Integer(properties.getProperty(path + "maxIdle"))));
		dataSourceProperties.setValidationQuery(properties.getProperty(path + "validationQuery"));
		dataSourceProperties.setVerbose((new Boolean(properties.getProperty(path + "verbose"))));

		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(dataSourceProperties.getDriverClassName());
		ds.setUrl(dataSourceProperties.getUrl());
		ds.setUsername(dataSourceProperties.getUserName());
		ds.setPassword(dataSourceProperties.getUserPassword());
		ds.setInitialSize(dataSourceProperties.getInitialSize());
		ds.setMaxActive(dataSourceProperties.getMaxActive());
		ds.setMaxIdle(dataSourceProperties.getMaxIdle());
		ds.setValidationQuery(dataSourceProperties.getValidationQuery());

		return new DataSourceWrapper(ds, dataSourceProperties);

	}

	private Properties loadProperties(String path) {

		try {

			Properties properties = new Properties();
			InputStream input = null;

			try {

				System.out.println(SEP + "[..] Leyendo archivo de propiedades\n\n" + path + SEP);

				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				input = classLoader.getResourceAsStream(path);
				// input = new FileInputStream(path);

				properties.load(input);

				String json = "\n{\n";

				for (Enumeration<Object> e = properties.keys(); e.hasMoreElements();) {
					Object obj = e.nextElement();
					json += "\n\t\"" + obj + "\":" + buildValue(properties.getProperty(obj.toString())) + ",";
				}

				json = json.substring(0, json.length() - 1);

				json += "\n}";

				System.out.println(
						"[OK] Lectura de archivo de propiedades\n\n" + path + "\n\nContenido:\n\n" + json + SEP);

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			return properties;

		} catch (Exception e) {
			printFatal(e);
		}
		return null;

	}

	private String buildValue(Object value) {

		if (value == null) {
			return null;
		}

		if (value instanceof Number) {
			return value.toString();
		}

		if (value instanceof Boolean) {
			return value.toString();
		}

		return "\"" + value + "\"";

	}

	private void printFatal(Exception e) {
		String msg = SEP + "ERROR FATAL!! - Se procede a interrumpir la ejecución del sistema. " + ZonedDateTime.now();
		System.err.println(msg);
		e.printStackTrace();
		System.err.println(SEP);
		System.exit(-1);
	}

} // END CLASS -----------------------------------------------------------------
