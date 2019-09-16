package com.singbox.model;

import java.util.ArrayList;
import java.util.List;

public class Folder extends EntityId {
	
	// ---------------------------------------------------------------------------------------------------------------------------

	private String code;
	private String name;
	private List<Document> documents = new ArrayList<Document>();

	// ---------------------------------------------------------------------------------------------------------------------------

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = (code == null || code.trim().length() == 0) ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = (name == null || name.trim().length() == 0) ? null : name.trim();
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public boolean addDocument(Document e) {
		return documents.add(e);
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {
		if (this.getCode() != null && this.getName() != null) {
			return this.getCode() + " - " + this.getName();
		} else if (this.getCode() != null && this.getName() == null) {
			return this.getCode();
		} else if (this.getCode() == null && this.getName() != null) {
			return this.getName();
		} else {
			return super.toString();
		}
	}

	public Folder clone() {

		Folder other = new Folder();

		other.setId(this.getId());
		other.setCode(this.getCode());
		other.setName(this.getName());
		if (this.getDocuments() != null) {

			for (Document doc : this.getDocuments()) {
				if (doc != null) {
					other.addDocument(doc.clone());
				} else {
					other.addDocument(null);
				}
			}

		}

		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

} // END CLASS -----------------------------------------------------------------
