package com.singbox.model;

import java.util.ArrayList;
import java.util.List;

public class DocumentType extends EntityId {

	private String code;
	private String name;
	private List<DocumentTypeField> fields = new ArrayList<DocumentTypeField>();

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

	public List<DocumentTypeField> getFields() {
		return fields;
	}

	public void setFields(List<DocumentTypeField> fields) {
		this.fields = fields;
	}

	public boolean addField(DocumentTypeField e) {
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

	public DocumentType clone() {

		DocumentType other = new DocumentType();

		other.setId(this.getId());
		other.setCode(this.getCode());
		other.setName(this.getName());
		if (this.getFields() != null) {

			for (DocumentTypeField field : this.getFields()) {
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
