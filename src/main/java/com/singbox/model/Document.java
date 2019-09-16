package com.singbox.model;

import java.util.ArrayList;
import java.util.List;

public class Document extends Content {

	private DocumentType type;
	private List<DocumentField> fields = new ArrayList<DocumentField>();

	// ---------------------------------------------------------------------------------------------------------------------------

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}

	public List<DocumentField> getFields() {
		return fields;
	}

	public void setFields(List<DocumentField> fields) {
		this.fields = fields;
	}

	public boolean addField(DocumentField e) {
		return fields.add(e);
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

	public Document clone() {

		Document other = new Document();

		other.setId(this.getId());
		other.setCode(this.getCode());
		other.setName(this.getName());
		if (this.getType() != null) {
			other.setType(this.getType().clone());
		}
		if (this.getFields() != null) {

			for (DocumentField field : this.getFields()) {
				if (field != null) {
					other.addField(field.clone());
				} else {
					other.addField(null);
				}
			}

		}

		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

} // END CLASS -----------------------------------------------------------------
