package com.singbox.model;

public class DocumentField extends EntityId {

	private String code;
	private String name;
	private DataType dataType;
	private String value;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = (value == null || value.trim().length() == 0) ? null : value.trim();
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

	public DocumentField clone() {

		DocumentField other = new DocumentField();

		other.setId(this.getId());
		other.setCode(this.getCode());
		other.setName(this.getName());
		if (this.getDataType() != null) {
			other.setDataType(this.getDataType().clone());
		}
		other.setValue(this.getValue());

		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

} // END CLASS -----------------------------------------------------------------
