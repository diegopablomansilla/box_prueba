package com.singbox.service.model;

import com.singbox.service.GenericFTSFilter;

public class MimeTypesFilter extends GenericFTSFilter {
	
	private String code;

	// ---------------------------------------------------------------------------------------------------------------------------

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = (code == null || code.trim().length() == 0) ? null : code.trim();
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public MimeTypesFilter clone() {

		MimeTypesFilter other = new MimeTypesFilter();

		other.setOffset(this.getOffset());
		other.setLimit(this.getLimit());
		other.setOrderBy(this.getOrderBy());
		other.setOrderByDesc(this.getOrderByDesc());
		other.setUnlimited(this.getUnlimited());

		if (this.getFullTextSearchWords() != null) {

			for (String word : this.getFullTextSearchWords()) {
				other.addFullTextSearchWord(word);
			}

		}
		
		other.setCode(this.getCode());
		
		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

} // END CLASS -----------------------------------------------------------------