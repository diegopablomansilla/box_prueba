package com.singbox.persist.rdbms.model;

import com.singbox.persist.rdbms.sorm.stm.StatementParam;
import com.singbox.service.GenericFTSFilter;
import com.singbox.service.model.MimeTypesFilter;

public class MimeTypeStmFind extends StatementParam {

	public MimeTypeStmFind(MimeTypesFilter f, boolean count) {
		super();

		if (f == null) {
			throw new IllegalArgumentException("QUERY: Se esperaba un bojeto para filtrar la consulta. Se esperaba "
					+ GenericFTSFilter.class.getCanonicalName());
		}

//		if (f.getUnlimited() == false) {
//
//		}

		String atts = " COUNT(*) ";
		String orderBy = "";
		String page = "";
		String join = "";

		if (count == false) {

			atts = "MimeType.id , MimeType.code, MimeType.name, MimeType.fileExtension ";

			orderBy = " ORDER BY " + f.getOrderBy() + " " + (f.getOrderByDesc() ? "DESC" : "");

			if (f.getUnlimited() == false) {
				page = " LIMIT " + f.getLimit() + " OFFSET " + f.getOffset();
			}

		}

		join += "";

		String sql = "SELECT  " + atts + " FROM box.MimeType " + join + buildWhere(f) + orderBy + page;

		this.setSql(sql);

	}

	private String buildWhere(MimeTypesFilter f) {

		String where = "";

		// -----------------

		if (f.getFullTextSearchWords() != null) {

			where = buildWhereFTS(f, where, "code");
			where = buildWhereFTS(f, where, "name");
			where = buildWhereFTS(f, where, "fileExtension");

		}
		
		if (f.getCode() != null) {
			where += (where.trim().length() > 0 ) ? " AND " : "";
			where += " MimeType.code = ?";
			this.addArg(buildArgTrim(f.getCode(), String.class));
		}

		// -----------------

		if (where.trim().isEmpty() == false) {
			where = " WHERE " + where;
		}

		return where;
	}

	private String buildWhereFTS(MimeTypesFilter f, String where, String attName) {		

		for (String word : f.getFullTextSearchWords()) {
			where += (where.trim().length() > 0) ? " AND " : "";
			where += " TRANSLATE(LOWER(TRIM(MimeType." + attName + "))" + translate + ") LIKE ?";
			this.addArg(buildArgTrimLower(word.trim(), String.class));
		}

		return where;
	}

} // END CLASS -----------------------------------------------------------------