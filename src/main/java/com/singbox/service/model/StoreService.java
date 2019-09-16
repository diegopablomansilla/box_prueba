package com.singbox.service.model;

import java.util.ArrayList;
import java.util.List;

import com.singbox.model.Store;
import com.singbox.persist.rdbms.DataBase;
import com.singbox.persist.rdbms.DataBaseFactory;

public class StoreService {

	private String key;

	// ---------------------------------------------------------------------------------------------------------------------------

	public StoreService(String key) {
		super();
		this.key = key;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public Store insert(Store obj) throws Exception {

		if (obj == null) {
			throw new IllegalArgumentException("Se esperaba un objeto " + Store.class.getCanonicalName() + " no nulo.");
		}

		DataBase db = DataBaseFactory.get(key);

		try {

			db.begint();

			boolean b = db.insertObject(obj);
			if (b == false) {
				throw new IllegalStateException("No se pudo guardar el " + Store.class.getCanonicalName() + " " + obj);
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

	public Store update(Store obj) throws Exception {

		if (obj == null) {
			throw new IllegalArgumentException("Se esperaba un objeto " + Store.class.getCanonicalName() + " no nulo.");
		}
		if (obj.getId() == null || obj.getId().trim().length() == 0) {
			throw new IllegalArgumentException(
					"Se esperaba un objeto " + Store.class.getCanonicalName() + " con id no nulo/vacio.");
		}

		DataBase db = DataBaseFactory.get(key);

		try {

			db.begint();

			boolean b = db.updateObject(obj);
			if (b == false) {
				throw new IllegalStateException("No se pudo guardar el " + Store.class.getCanonicalName() + " " + obj);
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
					"Se esperaba un id (" + Store.class.getCanonicalName() + ".id) no nulo/vacio.");
		}

		id = id.trim();

		DataBase db = DataBaseFactory.get(key);

		try {

			db.begint();

			boolean b = db.deleteObjectById(id, Store.class);
			if (b == false) {
				throw new IllegalStateException("No se pudo borrar el " + Store.class.getCanonicalName() + " " + id);
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

	public Store findById(String id) throws Exception {

		if (id == null || id.trim().length() == 0) {
			throw new IllegalArgumentException(
					"Se esperaba un id (" + Store.class.getCanonicalName() + ".id) no nulo/vacio.");
		}

		id = id.trim();

		DataBase db = DataBaseFactory.get(key);

		Store obj = null;

		try {

			db.begint();

			obj = (Store) db.fillObjectById(id, Store.class, 4);
			if (obj == null) {
				throw new IllegalStateException(
						"No se pudo encontrar el " + Store.class.getCanonicalName() + " con id " + obj);
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

			count = db.countAllObjects(Store.class);

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
	public List<Store> find() throws Exception {

		DataBase db = DataBaseFactory.get(key);

		List<Store> listado = null;

		try {

			db.begint();

			listado = db.fillAllObjects(Store.class);

			db.commit();

		} catch (Exception e) {
			db.rollBack();
			throw e;
		} finally {
			db.close();
		}

		if (listado == null) {
			listado = new ArrayList<Store>();
		}

		return listado;
	}

} // END CLASS -----------------------------------------------------------------
