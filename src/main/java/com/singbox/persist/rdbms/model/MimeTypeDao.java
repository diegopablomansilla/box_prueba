package com.singbox.persist.rdbms.model;

import java.util.ArrayList;
import java.util.List;

import com.singbox.model.MimeType;
import com.singbox.persist.rdbms.dsw.ConnectionWrapper;
import com.singbox.service.model.MimeTypesFilter;

public class MimeTypeDao {

	public List<MimeType> find(ConnectionWrapper connection, MimeTypesFilter f) throws Exception {

		List<MimeType> r = new ArrayList<MimeType>();

		MimeTypeStmFind stm = new MimeTypeStmFind(f, false);

		Object[][] table = connection.findToTable(stm.getSql(), stm.getArgs());

		if (table != null && table.length > 0) {

			for (Object[] row : table) {

				MimeType objRow = new MimeType();

				int c = -1;

				objRow.setId((String) row[++c]);

				objRow.setCode((String) row[++c]);
				objRow.setName((String) row[++c]);
				objRow.setFileExtension((String) row[++c]);

				r.add(objRow);

			}
		}

		return r;
	}

	public Long count(ConnectionWrapper connection, MimeTypesFilter f) throws Exception {

		MimeTypeStmFind stm = new MimeTypeStmFind(f, true);

		Object[][] table = connection.findToTable(stm.getSql(), stm.getArgs());

		if (table.length == 1) {

			Object[] row = table[0];

			return (Long) row[0];

		} else {

			throw new IllegalStateException(
					"No se esperaba que la consulta a la base de datos devuelva " + table.length + " filas.");

		}

	}

	public Long countAll(ConnectionWrapper connection) throws Exception {

		String sql = "SELECT COUNT(*) FROM box.MimeType";

		Object[][] table = connection.findToTable(sql);

		if (table.length == 1) {

			Object[] row = table[0];

			return (Long) row[0];

		} else {
			throw new IllegalStateException(
					"QUERY: No se esperaba que la consulta a la base de datos devuelva " + table.length + " filas.");
		}

	}

	public boolean exist(ConnectionWrapper connection, MimeType obj) throws Exception {

		if (obj == null) {
			throw new IllegalArgumentException("QUERY: Se esperaba un bojeto para filtrar la consulta. Se esperaba "
					+ MimeType.class.getCanonicalName());
		}

		return existById(connection, obj.getId());
	}

	private boolean existById(ConnectionWrapper connection, String id) throws Exception {

		if (id == null) {
			throw new IllegalArgumentException("QUERY: Se esperaba un id no nulo.");
		}

		id = id.trim();

		if (id.isEmpty()) {
			throw new IllegalArgumentException("QUERY: Se esperaba un id no vacio.");
		}

		String sql = "SELECT COUNT(*) > 0 FROM box.MimeType WHERE id = ?";

		Object[][] table = connection.findToTable(sql, new Object[] { id });

		if (table.length == 1) {

			Object[] row = table[0];

			return row[0].equals(true);

		} else {
			throw new IllegalStateException(
					"QUERY: No se esperaba que la consulta a la base de datos devuelva " + table.length + " filas.");
		}
	}

} // END CLASS -----------------------------------------------------------------