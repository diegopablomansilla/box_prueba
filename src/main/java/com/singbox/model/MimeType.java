package com.singbox.model;

public class MimeType extends EntityId {

	protected String id;
	private String code;
	private String name;
	private String fileExtension;

	// ---------------------------------------------------------------------------------------------------------------------------
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = (id == null || id.trim().length() == 0) ? null : id.trim();
	}

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

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = (fileExtension == null || fileExtension.trim().length() == 0) ? null
				: fileExtension.trim();
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

	public MimeType clone() {

		MimeType other = new MimeType();

		other.setId(this.getId());
		other.setCode(this.getCode());
		other.setName(this.getName());
		other.setFileExtension(this.getFileExtension());

		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

} // END CLASS -----------------------------------------------------------------
