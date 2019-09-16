package com.singbox.service.model;

import java.util.ArrayList;
import java.util.List;

import com.singbox.model.MimeType;
import com.singbox.persist.rdbms.DataBase;
import com.singbox.persist.rdbms.DataBaseFactory;

public class MimeTypeService {

	private String key;

	// ---------------------------------------------------------------------------------------------------------------------------

	public MimeTypeService(String key) {
		super();
		this.key = key;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public MimeType insert(MimeType obj) throws Exception {

		if (obj == null) {
			throw new IllegalArgumentException(
					"Se esperaba un objeto " + MimeType.class.getCanonicalName() + " no nulo.");
		}

		DataBase db = DataBaseFactory.get(key);

		try {

			db.begint();

			boolean b = db.insertObject(obj);
			if (b == false) {
				throw new IllegalStateException(
						"No se pudo guardar el " + MimeType.class.getCanonicalName() + " " + obj);
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

	public MimeType update(MimeType obj) throws Exception {

		if (obj == null) {
			throw new IllegalArgumentException(
					"Se esperaba un objeto " + MimeType.class.getCanonicalName() + " no nulo.");
		}
		if (obj.getId() == null || obj.getId().trim().length() == 0) {
			throw new IllegalArgumentException(
					"Se esperaba un objeto " + MimeType.class.getCanonicalName() + " con id no nulo/vacio.");
		}

		DataBase db = DataBaseFactory.get(key);

		try {

			db.begint();

			boolean b = db.updateObject(obj);
			if (b == false) {
				throw new IllegalStateException(
						"No se pudo guardar el " + MimeType.class.getCanonicalName() + " " + obj);
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
					"Se esperaba un id (" + MimeType.class.getCanonicalName() + ".id) no nulo/vacio.");
		}

		id = id.trim();

		DataBase db = DataBaseFactory.get(key);

		try {

			db.begint();

			boolean b = db.deleteObjectById(id, MimeType.class);
			if (b == false) {
				throw new IllegalStateException("No se pudo borrar el " + MimeType.class.getCanonicalName() + " " + id);
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

	public MimeType findById(String id) throws Exception {

		if (id == null || id.trim().length() == 0) {
			throw new IllegalArgumentException(
					"Se esperaba un id (" + MimeType.class.getCanonicalName() + ".id) no nulo/vacio.");
		}

		id = id.trim();

		DataBase db = DataBaseFactory.get(key);

		MimeType obj = null;

		try {

			db.begint();

			obj = (MimeType) db.fillObjectById(id, MimeType.class, 4);
			if (obj == null) {
				throw new IllegalStateException(
						"No se pudo encontrar el " + MimeType.class.getCanonicalName() + " con id " + obj);
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

			count = db.countAllObjects(MimeType.class);

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
	public List<MimeType> find() throws Exception {

		DataBase db = DataBaseFactory.get(key);

		List<MimeType> listado = null;

		try {

			db.begint();

			listado = db.fillAllObjects(MimeType.class);

			db.commit();

		} catch (Exception e) {
			db.rollBack();
			throw e;
		} finally {
			db.close();
		}

		if (listado == null) {
			listado = new ArrayList<MimeType>();
		}

		return listado;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

//	public List<MimeTypes> find(MimeTypesFiltro f) throws Exception {
//
//		if (f == null) {
//			throw new IllegalArgumentException(
//					"Se esperaba un objeto " + MimeTypesFiltro.class.getCanonicalName() + " no nulo.");
//		}
//
//		DataBase db = DataBaseFactory.get(key);
//
//		List<MimeType> listado = null;
//
//		try {
//
//			db.begint();
//
//			listado = db.findMimeTypes(f);
//
//			db.commit();
//
//		} catch (Exception e) {
//			db.rollBack();
//			throw e;
//		} finally {
//			db.close();
//		}
//
//		if (listado == null) {
//			listado = new ArrayList<MimeType>();
//		}
//
//		return listado;
//	}

	// ---------------------------------------------------------------------------------------------------------------------------

//	public Integer count(MimeTypesFiltro f) throws Exception {
//
//		if (f == null) {
//			throw new IllegalArgumentException(
//					"Se esperaba un objeto " + MimeTypesFiltro.class.getCanonicalName() + " no nulo.");
//		}
//
//		DataBase db = BackendContextPG.get().getDataBase();
//
//		Integer count = 0;
//
//		try {
//
//			db.begint();
//
//			count = db.countMimeTypes(f);
//
//			db.commit();
//
//		} catch (Exception e) {
//			db.rollBack();
//			throw e;
//		} finally {
//			db.close();
//		}
//
//		return count;
//
//	}

} // END CLASS -----------------------------------------------------------------
