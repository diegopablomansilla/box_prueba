package com.singbox.persist.rdbms.sorm.pg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.singbox.persist.rdbms.dsw.ConnectionWrapper;
import com.singbox.persist.rdbms.dsw.DataSourceWrapper;
import com.singbox.persist.rdbms.sorm.Identifiable;

public class DataBasePG {

	private UpdateDAO updateDAO;
	private InsertDAO insertDAO;
	private QueryDAO queryDAO;
	private DeleteDAO deleteDAO;

	// -----------------------------------------------------

	private DataSourceWrapper dataSource;
	private String schema;

	protected ConnectionWrapper connection;

	public DataBasePG(DataSourceWrapper dataSource, String schema) {
		super();
		this.dataSource = dataSource;
		this.schema = schema;
		updateDAO = new UpdateDAO(this.schema);
		insertDAO = new InsertDAO(this.schema);
		queryDAO = new QueryDAO(this.schema);
		deleteDAO = new DeleteDAO(this.schema);
	}

	// -------------------------------------------------------------------------------

	public void begint() throws SQLException, Exception {
		if (connection != null) {
			throw new IllegalStateException(
					"Debe hacer close() la conexión primero, debido a que ya ha realizado un begint().");
		}
		connection = dataSource.getConnection();
		connection.begin();
	}

	public void commit() throws SQLException, Exception {
		connection.commit();
	}

	public void rollBack() throws SQLException, Exception {
		connection.rollBack();
	}

	public void close() throws SQLException, Exception {
		connection.close();
		connection = null;
	}

	// -------------------------------------------------------------------------------

	public boolean insertObject(Object obj) throws Exception {
		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"INSERT: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}
		return insertDAO.insertObject(connection, (Identifiable) obj);
	}

	@SuppressWarnings("rawtypes")
	public boolean insertObject(Object obj, Class mappingClass) throws Exception {
		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"INSERT: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}
		return insertDAO.insertObject(connection, (Identifiable) obj, mappingClass);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean[] insertObjects(List objs) throws Exception {
		return insertDAO.insertObjects(connection, objs);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean[] insertObjects(List objs, Class mappingClass) throws Exception {
		return insertDAO.insertObjects(connection, objs, mappingClass);
	}

	// -------------------------------------------------

	public boolean updateObject(Object obj) throws Exception {
		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"UPDATE: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}
		return updateDAO.updateObject(connection, (Identifiable) obj);
	}

	@SuppressWarnings("rawtypes")
	public boolean updateObject(Object obj, Class mappingClass) throws Exception {
		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"UPDATE: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}
		return updateDAO.updateObject(connection, (Identifiable) obj, mappingClass);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean[] updateObjects(List objs) throws Exception {
		return updateDAO.updateObjects(connection, objs);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean[] updateObjects(List objs, Class mappingClass) throws Exception {
		return updateDAO.updateObjects(connection, objs, mappingClass);
	}

	// -------------------------------------------------

	public boolean deleteObject(Object obj) throws Exception {
		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"DELETE: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}
		return deleteDAO.deleteObject(connection, (Identifiable) obj);
	}

	@SuppressWarnings("rawtypes")
	public boolean deleteObjectById(String id, Class mappingClass) throws Exception {
		return deleteDAO.deleteObjectById(connection, id, mappingClass);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean[] deleteObjects(List objs) throws Exception {
		return deleteDAO.deleteObjects(connection, objs);
	}

	@SuppressWarnings({ "rawtypes" })
	public boolean[] deleteObjectsById(List<String> ids, Class mappingClass) throws Exception {
		return deleteDAO.deleteObjectsById(connection, ids, mappingClass);
	}

	@SuppressWarnings("rawtypes")
	public boolean deleteAllObjects(Class mappingClass) throws Exception {
		return deleteDAO.deleteAllObjects(connection, mappingClass);
	}

	// -------------------------------------------------

	public boolean objectExist(Object obj) throws Exception {

		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"QUERY: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}

		// if (obj instanceof Identifiable == false) {
		// throw new IllegalArgumentException(
		// "QUERY: Se esperaba una lista objetos, con objetos tipo " +
		// Identifiable.class.getSimpleName());
		// }

		return queryDAO.objectExist(connection, (Identifiable) obj);
	}

	@SuppressWarnings("rawtypes")
	public boolean objectExistById(String id, Class mappingClass) throws Exception {
		return queryDAO.objectExistById(connection, id, mappingClass);
	}

	// -------------------------------------------------

	@SuppressWarnings("rawtypes")
	public long countAllObjects(Class mappingClass) throws Exception {
		return queryDAO.countAllObjects(connection, mappingClass);
	}

	// -------------------------------------------------

	public Object fillObject(Object obj) throws Exception {

		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"QUERY: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}

		return queryDAO.fillObject(connection, (Identifiable) obj);
	}

	public Object fillObject(Object obj, int leftLevel) throws Exception {

		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"QUERY: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}

		return queryDAO.fillObject(connection, (Identifiable) obj, leftLevel);
	}

	@SuppressWarnings({ "rawtypes" })
	public Object fillObject(Object obj, Class mappingClass) throws Exception {

		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"QUERY: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}

		return queryDAO.fillObject(connection, (Identifiable) obj, mappingClass);
	}

	@SuppressWarnings({ "rawtypes" })
	public Object fillObject(Object obj, Class mappingClass, int leftLevel) throws Exception {

		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"QUERY: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}

		return queryDAO.fillObject(connection, (Identifiable) obj, mappingClass, leftLevel);
	}

	@SuppressWarnings("rawtypes")
	public Object fillObject(Object obj, Class instanceClass, Class mappingClass, int leftLevel) throws Exception {

		if (obj instanceof Identifiable == false) {
			throw new IllegalArgumentException(
					"QUERY: Se esperaba un objeto tipo " + Identifiable.class.getSimpleName());
		}

		return queryDAO.fillObject(connection, (Identifiable) obj, instanceClass, mappingClass, leftLevel);
	}

	@SuppressWarnings({ "rawtypes" })
	public Object fillObjectById(String id, Class mappingClass) throws Exception {
		return queryDAO.fillObject(connection, id, mappingClass);
	}

	@SuppressWarnings({ "rawtypes" })
	public Object fillObjectById(String id, Class mappingClass, int leftLevel) throws Exception {
		return queryDAO.fillObject(connection, id, mappingClass, leftLevel);
	}

	@SuppressWarnings({ "rawtypes" })
	public Object fillObjectById(String id, Class instanceClass, Class mappingClass, int leftLevel) throws Exception {
		return queryDAO.fillObject(connection, id, instanceClass, mappingClass, leftLevel);

	}

	@SuppressWarnings("rawtypes")
	public List fillAllObjects(Class mappingClass) throws Exception {
		return queryDAO.fillAllObjects(connection, mappingClass);
	}

	@SuppressWarnings("rawtypes")
	public List fillAllObjects(Class mappingClass, int leftLevel) throws Exception {
		return queryDAO.fillAllObjects(connection, mappingClass, leftLevel);
	}

	@SuppressWarnings("rawtypes")
	public List fillAllObjects(Class instanceClass, Class mappingClass, int leftLevel) throws Exception {
		return queryDAO.fillAllObjects(connection, instanceClass, mappingClass, leftLevel);
	}

//	@SuppressWarnings("rawtypes")
//	public List fillAllObjectsByIdFk(String idFk, Class mappingClass) throws Exception {
//		return queryDAO.fillAllObjects(connectionWrapper, mappingClass);
//	}
//
//	@SuppressWarnings("rawtypes")
//	public List fillAllObjectsByIdFk(String idFk, Class mappingClass, int leftLevel) throws Exception {
//		return queryDAO.fillAllObjects(connectionWrapper, mappingClass, leftLevel);
//	}
//
//	@SuppressWarnings("rawtypes")
//	public List fillAllObjectsByIdFk(String idFk, Class instanceClass, Class mappingClass, int leftLevel) throws Exception {
//		return queryDAO.fillAllObjects(connectionWrapper, instanceClass, mappingClass, leftLevel);
//	}

	// -------------------------------------------------

	@SuppressWarnings("rawtypes")
	public List<String> utilExtractsIds(List objs) {

		if (objs == null) {
			throw new IllegalArgumentException("Se esperaba una lista de objetos no nulo.");
		}

		if (objs.size() == 0) {
			throw new IllegalArgumentException("Se esperaba una lista objetos no vacia.");
		}

		List<String> ids = new ArrayList<String>();

		for (Object obj : objs) {

			if (obj == null) {
				throw new IllegalArgumentException("Se esperaba una lista objetos con objetos no nulos.");
			}

			if (obj != null && obj instanceof Identifiable && ((Identifiable) obj).getId() != null
					&& ((Identifiable) obj).getId().trim().isEmpty() == false) {
				ids.add(((Identifiable) obj).getId());
			}
		}

		return ids;
	}

} // END CLASS -----------------------------------------------------------------
