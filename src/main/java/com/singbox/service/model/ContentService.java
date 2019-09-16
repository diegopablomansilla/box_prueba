package com.singbox.service.model;

import java.util.ArrayList;
import java.util.List;

import com.singbox.model.Content;
import com.singbox.persist.rdbms.DataBase;
import com.singbox.persist.rdbms.DataBaseFactory;

public class ContentService {

	private String key;

	// ---------------------------------------------------------------------------------------------------------------------------

	public ContentService(String key) {
		super();
		this.key = key;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public Content insert(Content obj) throws Exception {

		if (obj == null) {
			throw new IllegalArgumentException(
					"Se esperaba un objeto " + Content.class.getCanonicalName() + " no nulo.");
		}

		DataBase db = DataBaseFactory.get(key);

		try {

			db.begint();

			boolean b = db.insertObject(obj);
			if (b == false) {
				throw new IllegalStateException(
						"No se pudo guardar el " + Content.class.getCanonicalName() + " " + obj);
			}

			db.commit();

		} catch (Exception e) {
			db.rollBack();
			throw e;
		} finally {
			db.close();
		}

		return obj;

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public Content update(Content obj) throws Exception {

		if (obj == null) {
			throw new IllegalArgumentException(
					"Se esperaba un objeto " + Content.class.getCanonicalName() + " no nulo.");
		}
		if (obj.getId() == null || obj.getId().trim().length() == 0) {
			throw new IllegalArgumentException(
					"Se esperaba un objeto " + Content.class.getCanonicalName() + " con id no nulo/vacio.");
		}

		DataBase db = DataBaseFactory.get(key);

		try {

			db.begint();

			boolean b = db.updateObject(obj);
			if (b == false) {
				throw new IllegalStateException(
						"No se pudo guardar el " + Content.class.getCanonicalName() + " " + obj);
			}

			db.commit();

		} catch (Exception e) {
			db.rollBack();
			throw e;
		} finally {
			db.close();
		}

		return obj;

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public boolean deleteById(String id) throws Exception {

		if (id == null || id.trim().length() == 0) {
			throw new IllegalArgumentException(
					"Se esperaba un id (" + Content.class.getCanonicalName() + ".id) no nulo/vacio.");
		}

		id = id.trim();

		DataBase db = DataBaseFactory.get(key);

		try {

			db.begint();

			boolean b = db.deleteObjectById(id, Content.class);
			if (b == false) {
				throw new IllegalStateException("No se pudo borrar el " + Content.class.getCanonicalName() + " " + id);
			}

			db.commit();

		} catch (Exception e) {
			db.rollBack();
			throw e;
		} finally {
			db.close();
		}

		return true;

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public Content findById(String id) throws Exception {

		if (id == null || id.trim().length() == 0) {
			throw new IllegalArgumentException(
					"Se esperaba un id (" + Content.class.getCanonicalName() + ".id) no nulo/vacio.");
		}

		id = id.trim();

		DataBase db = DataBaseFactory.get(key);

		Content obj = null;

		try {

			db.begint();

			obj = (Content) db.fillObjectById(id, Content.class, 4);
			if (obj == null) {
				throw new IllegalStateException(
						"No se pudo encontrar el " + Content.class.getCanonicalName() + " con id " + obj);
			}

			db.commit();

		} catch (Exception e) {
			db.rollBack();
			throw e;
		} finally {
			db.close();
		}

		return obj;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public Long count() throws Exception {

		DataBase db = DataBaseFactory.get(key);

		Long count = 0L;

		try {

			db.begint();

			count = db.countAllObjects(Content.class);

			db.commit();

		} catch (Exception e) {
			db.rollBack();
			throw e;
		} finally {
			db.close();
		}

		return count;

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Content> find() throws Exception {

		DataBase db = DataBaseFactory.get(key);

		List<Content> listado = null;

		try {

			db.begint();

			listado = db.fillAllObjects(Content.class, 1);

			db.commit();

		} catch (Exception e) {
			db.rollBack();
			throw e;
		} finally {
			db.close();
		}

		if (listado == null) {
			listado = new ArrayList<Content>();
		}

		return listado;
	}

} // END CLASS -----------------------------------------------------------------
