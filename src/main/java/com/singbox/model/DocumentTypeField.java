package com.singbox.model;

public class DocumentTypeField extends EntityId {

	private String code;
	private String name;
	private DataType dataType;
	private String defaultValue;

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

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = (defaultValue == null || defaultValue.trim().length() == 0) ? null : defaultValue.trim();
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

	public DocumentTypeField clone() {

		DocumentTypeField other = new DocumentTypeField();

		other.setId(this.getId());
		other.setCode(this.getCode());
		other.setName(this.getName());
		if (this.getDataType() != null) {
			other.setDataType(this.getDataType().clone());
		}
		other.setDefaultValue(this.getDefaultValue());

		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

} // END CLASS -----------------------------------------------------------------
