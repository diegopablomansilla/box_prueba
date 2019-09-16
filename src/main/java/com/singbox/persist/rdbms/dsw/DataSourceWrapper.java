package com.singbox.persist.rdbms.dsw;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.time.ZonedDateTime;

import javax.sql.DataSource;

public class DataSourceWrapper {

	// ---------------------------------------------------------------------------------------------------------------------------

	private final String SEP = "\n\n=================================================================================\n\n";

	private final String OPERATION_TYPE_START_POOL_CONECTION = "START_POOL_CONECTION";
	private final String OPERATION_TYPE_START_CONNECTION = "START_CONNECTION";
	private final String OPERATION_TYPE_METADATA_CONECTION = "METADATA_CONECTION";
	private final String OPERATION_TYPE_CLOSE_CONNECTION = "CLOSE_CONNECTION";

	private final String TITLE_START_POOL_CONECTION = "Data Source - Pool Connection";
	private final String TITLE_START_CONNECTION = "Apertura de Conexión";
	private final String TITLE_METADATA_CONECTION = "Data Source - Meta Data";
	private final String TITLE_CLOSE_CONNECTION = "Cierre de Conexión";

	private final String SUBJECT_START_POOL_CONECTION = "No se pudo obtener las conexiones a la base de datos. Error al tratar de iniciar el pool de conexiones.";
	private final String SUBJECT_START_CONNECTION = "Error al intentar abrir una conexión.";
	private final String SUBJECT_METADATA_CONECTION = "No se pudo obtener información del origen de datos.";
	private final String SUBJECT_CLOSE_CONNECTION = "Error al intentar cerrar una conexión. No se pudo cerrar la conexion para obtener información del origen de datos.";

	private DataSource dataSource;
	private DataSourceMetaData metaData;
	private DataSourceProperties properties;

	// ---------------------------------------------------------------------------------------------------------------------------

	public DataSourceWrapper(DataSource dataSource, DataSourceProperties properties) throws Exception {

		this.dataSource = dataSource;
		this.properties = properties;
		this.metaData = this.getMetaData(dataSource);

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public synchronized boolean isVerbose() {
		return this.properties.isVerbose();
	}

	public synchronized boolean isPostgreSql() {
		return metaData.isPostgreSql();
	}

	public synchronized ConnectionWrapper getConnection() throws SQLExceptionWrapper {

		try {

			Connection connection = dataSource.getConnection();

			printSQLWarning(connection.getWarnings());

			return new ConnectionWrapper(connection, metaData, properties);

		} catch (SQLException e) {
			throw this.buildSQLExceptionWrapper(e, OPERATION_TYPE_START_CONNECTION, TITLE_START_CONNECTION,
					SUBJECT_START_CONNECTION);
		}

	}

	private synchronized void printSQLWarning(SQLWarning sqlWarning) {

		String msg = "\n\nSQL WARNING " + ZonedDateTime.now() + "\n\n";

		String msg2 = "";

		while (sqlWarning != null) {

			msg2 += "Warning : " + sqlWarning.getErrorCode() + " Message : " + sqlWarning.getMessage() + " SQL state "
					+ sqlWarning.getSQLState() + "\n";

			sqlWarning = sqlWarning.getNextWarning();
		}

		if (msg2 != null && msg2.isEmpty() == false) {
			msg += msg2;
		}

		msg += "\n\nEND SQL WARNING " + ZonedDateTime.now() + "\n\n";

		if (isVerbose() && msg2 != null && msg2.isEmpty() == false) {

			System.out.println(msg);
		}
	}

	private synchronized DataSourceMetaData getMetaData(DataSource dataSource) throws Exception {

		if (isVerbose()) {

			System.out.println(SEP + "\n\n[..] Conectandose a\n\n" + properties);
		}

		Connection connection = null;

		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw this.buildSQLExceptionWrapper(e, OPERATION_TYPE_START_POOL_CONECTION, TITLE_START_POOL_CONECTION,
					SUBJECT_START_POOL_CONECTION);
		}

		DataSourceMetaData dataSourceMetaData = null;

		try {
			dataSourceMetaData = new DataSourceMetaData();

			dataSourceMetaData.url = connection.getMetaData().getURL();
			dataSourceMetaData.userName = connection.getMetaData().getUserName();
			dataSourceMetaData.databaseProductName = connection.getMetaData().getDatabaseProductName();
			dataSourceMetaData.databaseProductVersion = connection.getMetaData().getDatabaseProductVersion();
			dataSourceMetaData.driverName = connection.getMetaData().getDriverName();
			dataSourceMetaData.driverVersion = connection.getMetaData().getDriverVersion();
			dataSourceMetaData.jDBCMajorVersion = connection.getMetaData().getJDBCMajorVersion();
			dataSourceMetaData.jDBCMinorVersion = connection.getMetaData().getJDBCMinorVersion();
		} catch (SQLException e) {
			throw this.buildSQLExceptionWrapper(e, OPERATION_TYPE_METADATA_CONECTION, TITLE_METADATA_CONECTION,
					SUBJECT_METADATA_CONECTION);
		} finally {

			try {
				if (connection != null && connection.isClosed() == false) {
					connection.close();
				}
			} catch (SQLException e) {
				throw this.buildSQLExceptionWrapper(e, OPERATION_TYPE_CLOSE_CONNECTION, TITLE_CLOSE_CONNECTION,
						SUBJECT_CLOSE_CONNECTION);
			}
		}

		if (isVerbose()) {

			System.out.println("\n\nConectado a\n\n" + dataSourceMetaData);

			System.out.println("\n\n[OK] Conectado a\n\n" + dataSourceMetaData.getUrl() + SEP);
		}

		return dataSourceMetaData;

	}

	private synchronized SQLExceptionWrapper buildSQLExceptionWrapper(SQLException sQLException, String operationType,
			String title, String subject) {

		SQLExceptionWrapper sQLExceptionWrapper = new SQLExceptionWrapper(sQLException);

		sQLExceptionWrapper.setTitle(title);
		sQLExceptionWrapper.setSubject(subject);

		sQLExceptionWrapper.setOperationType(operationType);

		if (this.properties != null) {
			sQLExceptionWrapper.setDriverClassName(properties.getDriverClassName());
			sQLExceptionWrapper.setInitialSize(properties.getInitialSize());
			sQLExceptionWrapper.setMaxActive(properties.getMaxActive());
			sQLExceptionWrapper.setMaxIdle(properties.getMaxIdle());
			sQLExceptionWrapper.setValidationQuery(properties.getValidationQuery());
		}

		if (metaData != null) {
			sQLExceptionWrapper.setDatabaseProductName(metaData.getDatabaseProductName());
			sQLExceptionWrapper.setDatabaseProductVersion(metaData.getDatabaseProductVersion());
			sQLExceptionWrapper.setDriverName(metaData.getDriverName());
			sQLExceptionWrapper.setDriverVersion(metaData.getDriverVersion());
			sQLExceptionWrapper.setjDBCMajorVersion(metaData.getjDBCMajorVersion());
			sQLExceptionWrapper.setjDBCMinorVersion(metaData.getjDBCMinorVersion());
			sQLExceptionWrapper.setUrl(metaData.getUrl());
			sQLExceptionWrapper.setUserName(metaData.getUserName());

		} else if (this.properties != null) {
			sQLExceptionWrapper.setUrl(properties.getUrl());
			sQLExceptionWrapper.setUserName(properties.getUserName());
		}

		return sQLExceptionWrapper;

	}

} // END CLASS -----------------------------------------------------------------
