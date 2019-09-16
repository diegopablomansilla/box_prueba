package com.singbox.persist.rdbms;

import java.util.List;

import com.singbox.persist.rdbms.dsw.DataSourceWrapper;
import com.singbox.persist.rdbms.model.MimeTypeDao;
import com.singbox.persist.rdbms.sorm.pg.DataBasePG;
import com.singbox.service.model.MimeTypesFilter;

public class DataBase extends DataBasePG {

	// ---------------------------------------------------------------------------------------------------------------------------

	private MimeTypeDao mimeTypeDao;

	public DataBase(DataSourceWrapper dataSourceWrapper, String schema) {
		super(dataSourceWrapper, schema);

		mimeTypeDao = new MimeTypeDao();
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	@SuppressWarnings("rawtypes")
	public List findObjects(MimeTypesFilter f) throws Exception {
		return mimeTypeDao.find(connection, f);
	}

	public Long countObjects(MimeTypesFilter f) throws Exception {
		return mimeTypeDao.count(connection, f);
	}

	// ---------------------------------------------------------------------------------------------------------------------------

}
