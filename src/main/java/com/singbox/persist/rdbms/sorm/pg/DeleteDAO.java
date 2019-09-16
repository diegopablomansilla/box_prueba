package com.singbox.persist.rdbms.sorm.pg;

import java.util.List;

import com.singbox.persist.rdbms.dsw.ConnectionWrapper;
import com.singbox.persist.rdbms.sorm.Identifiable;
import com.singbox.persist.rdbms.sorm.pg.stm.builder.StmBuilderDelete;
import com.singbox.persist.rdbms.sorm.stm.Statement;
import com.singbox.persist.rdbms.sorm.stm.StatementParam;

public class DeleteDAO {

	private StmBuilderDelete builderStm;

	public DeleteDAO(String schema) {
		this.builderStm = new StmBuilderDelete(schema);
	}

	@SuppressWarnings("rawtypes")
	public boolean deleteAllObjects(ConnectionWrapper connection, Class mappingClass) throws Exception {
		Statement statement = builderStm.build(mappingClass);
		connection.delete(statement.getSql());

		return true;
	}

	public boolean deleteObject(ConnectionWrapper connection, Identifiable obj) throws Exception {
		return delete(connection, builderStm.build(obj));
	}

	@SuppressWarnings("rawtypes")
	public boolean deleteObjectById(ConnectionWrapper connection, String id, Class mappingClass)
			throws Exception {
		return delete(connection, builderStm.build(id, mappingClass));
	}

	public boolean[] deleteObjects(ConnectionWrapper connection, List<Identifiable> objs) throws Exception {

		if (objs == null) {
			throw new IllegalArgumentException("DELETE: Se esperaba una lista de objetos no nulo.");
		}

		if (objs.size() == 0) {
			throw new IllegalArgumentException("DELETE: Se esperaba una lista objetos no vacia.");
		}

		boolean[] r = new boolean[objs.size()];

		for (int i = 0; i < objs.size(); i++) {

			if (objs.get(i) == null) {
				throw new IllegalArgumentException("DELETE: Se esperaba una lista id's, con id's no nulos.");
			}

			r[i] = deleteObject(connection, objs.get(i));
		}

		return r;

	}

	@SuppressWarnings("rawtypes")
	public boolean[] deleteObjectsById(ConnectionWrapper connection, List<String> ids, Class mappingClass)
			throws Exception {

		if (ids == null) {
			throw new IllegalArgumentException("DELETE: Se esperaba una lista de id's no nulo.");
		}

		if (ids.size() == 0) {
			throw new IllegalArgumentException("DELETE: Se esperaba una lista id's no vacia.");
		}

		boolean[] r = new boolean[ids.size()];

		for (int i = 0; i < ids.size(); i++) {

			if (ids.get(i) == null) {
				throw new IllegalArgumentException("DELETE: Se esperaba una lista id's, con id's no nulos.");
			}

			r[i] = deleteObjectById(connection, ids.get(i), mappingClass);
		}

		return r;

	}

	private boolean delete(ConnectionWrapper connection, StatementParam statement) throws Exception {

		int rows = connection.delete(statement.getSql(), statement.getArgs());

		if (rows == 0) {
			throw new IllegalStateException(
					"DELETE: No se esperaba que la sentencia no eliminara en la base de datos.");
		} else if (rows > 1) {
			throw new IllegalStateException(
					"DELETE: No se esperaba que la sentencia eliminara a mas de un registro en la base de datos. Registros: "
							+ rows);
		}

		return true;

	}

}
