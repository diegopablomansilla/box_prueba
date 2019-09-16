package com.singbox.service;

import java.util.ArrayList;
import java.util.List;

public class GenericFTSFilter extends GenericFilter {

	private List<String> fullTextSearchWords = new ArrayList<String>();

	// ---------------------------------------------------------------------------------------------------------------------------

	public List<String> getFullTextSearchWords() {
		return fullTextSearchWords;
	}

	public void setFullTextSearchWords(List<String> fullTextSearchWords) {
		this.fullTextSearchWords = fullTextSearchWords;

		if (this.fullTextSearchWords != null) {

			List<String> fullTextSearchWords2 = new ArrayList<String>();

			for (String word : this.fullTextSearchWords) {
				word = (word == null || word.trim().length() == 0) ? null : word.trim();
				if (word != null) {
					fullTextSearchWords2.add(word);
				}
			}

			this.fullTextSearchWords = fullTextSearchWords2;

		}

	}

	public boolean addFullTextSearchWord(String e) {
		e = (e == null || e.trim().length() == 0) ? null : e.trim();
		if (e == null) {
			return false;
		}
		return fullTextSearchWords.add(e);
	}

	public void setFullTextSearchWords(String fullTextSearchWords) {
		fullTextSearchWords = (fullTextSearchWords == null || fullTextSearchWords.trim().length() == 0) ? null
				: fullTextSearchWords.trim();

		if (fullTextSearchWords != null) {
			String[] words = fullTextSearchWords.split(" ");

			for (String word : words) {
				word = (word == null || word.trim().length() == 0) ? null : word.trim();
				if (word != null) {
					this.fullTextSearchWords.add(word);
				}
			}

		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public GenericFTSFilter clone() {

		GenericFTSFilter other = new GenericFTSFilter();

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

		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

} // END CLASS -----------------------------------------------------------------