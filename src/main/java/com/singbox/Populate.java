package com.singbox;

import java.util.List;

//import com.massoftware.service.AppCX;
import com.singbox.model.Content;
import com.singbox.model.MimeType;
import com.singbox.model.Store;
import com.singbox.persist.rdbms.dsw.SQLExceptionWrapper;
import com.singbox.service.model.ContentService;
import com.singbox.service.model.MimeTypeService;
import com.singbox.service.model.StoreService;

public class Populate {

	private int maxRows = 300;
	private String key;

	public void populate(String key) throws Exception {
		this.key = key;

		insertMimeType();
		insertStore();
		insertContent();
	}

	public void insertMimeType() throws Exception {

		MimeTypeService service = new MimeTypeService(key);

		for (int i = 0; i < maxRows; i++) {

			try {

				MimeType obj = new MimeType();

				obj.setCode(UtilPopulate.getStringRandom(null, 3, true));

				obj.setName(UtilPopulate.getStringRandom(null, 50, true));

				obj.setFileExtension(UtilPopulate.getStringRandom(null, 5, true));

				service.insert(obj);

			} catch (SQLExceptionWrapper e) {

				if (("23505".equals(e.getSQLState()) || "23502".equals(e.getSQLState())
						|| "23514".equals(e.getSQLState())) == false) {

					throw e;

				}

			}

		}

	}

	public void insertStore() throws Exception {

		StoreService service = new StoreService(key);

		for (int i = 0; i < maxRows; i++) {

			try {

				Store obj = new Store();

				obj.setCode(UtilPopulate.getStringRandom(null, 3, true));

				obj.setName(UtilPopulate.getStringRandom(null, 50, true));

				obj.setUrl(UtilPopulate.getStringRandom(null, 5, true));

				service.insert(obj);

			} catch (SQLExceptionWrapper e) {

				if (("23505".equals(e.getSQLState()) || "23502".equals(e.getSQLState())
						|| "23514".equals(e.getSQLState())) == false) {

					throw e;

				}

			}

		}

	}

	public void insertContent() throws Exception {

		ContentService service = new ContentService(key);
		MimeTypeService servicemimeType = new MimeTypeService(key);
		Long mimeTypeCount = servicemimeType.count();
		StoreService servicestore = new StoreService(key);
		Long storeCount = servicestore.count();

		for (int i = 0; i < maxRows; i++) {

			try {

				Content obj = new Content();

				obj.setCode(UtilPopulate.getStringRandom(null, 3, true));

				obj.setName(UtilPopulate.getStringRandom(null, 50, true));

				obj.setKiloSize(UtilPopulate.getDoubleRandom(0.0, 99999.9999, false));

				int mimeTypeIndex = UtilPopulate.getIntegerRandom(0, mimeTypeCount.intValue() - 1);
				List<MimeType> mimeTypeListado = servicemimeType.find();
				MimeType objFkMimeType = new MimeType();
				objFkMimeType.setId(mimeTypeListado.get(mimeTypeIndex).getId());
				obj.setMimeType(objFkMimeType);

				int storeIndex = UtilPopulate.getIntegerRandom(0, storeCount.intValue() - 1);
				List<Store> storeListado = servicestore.find();
				Store objFkStore = new Store();
				objFkStore.setId(storeListado.get(storeIndex).getId());
				obj.setStore(objFkStore);

				obj.setCreated(UtilPopulate.getDateTimeRandom(2000, 2019, true));

				obj.setModified(UtilPopulate.getDateTimeRandom(2000, 2019, true));

				service.insert(obj);

			} catch (SQLExceptionWrapper e) {

				if (("23505".equals(e.getSQLState()) || "23502".equals(e.getSQLState())
						|| "23514".equals(e.getSQLState())) == false) {

					throw e;

				}

			}

		}

	}

}
