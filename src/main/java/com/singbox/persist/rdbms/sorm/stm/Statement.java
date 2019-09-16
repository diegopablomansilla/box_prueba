package com.singbox.persist.rdbms.sorm.stm;

public class Statement {

	protected String translate = ",'/\"'';,_-.âãäåāăąàáÁÂÃÄÅĀĂĄÀèééêëēĕėęěĒĔĖĘĚÉÈËÊìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőòÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮçÇñÑ', '         aaaaaaaaaAAAAAAAAAeeeeeeeeeeEEEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnN'";
	private String sql;

	// ---------------------------------------------------------------------------------------------------------------------------

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = (sql == null || sql.trim().length() == 0) ? null : sql.trim();
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {
		String s = "";

		s += "\n" + sql;

		return s;
	}

} // END CLASS -----------------------------------------------------------------
