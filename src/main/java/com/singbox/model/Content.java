package com.singbox.model;

import java.time.LocalDateTime;

public class Content extends EntityId {

	protected String id;
	private String code;
	private String name;
	private Double kiloSize;
	private MimeType mimeType;
	private Store store;
	private LocalDateTime created;
	private LocalDateTime modified;

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

	public Double getKiloSize() {
		return kiloSize;
	}

	public void setKiloSize(Double kiloSize) {
		this.kiloSize = (kiloSize == null || kiloSize < 0) ? 0 : kiloSize;
	}

	public MimeType getMimeType() {
		return mimeType;
	}

	public void setMimeType(MimeType mimeType) {
		this.mimeType = mimeType;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
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

	public Content clone() {

		Content other = new Content();

		other.setId(this.getId());
		other.setCode(this.getCode());
		other.setName(this.getName());
		other.setKiloSize(this.getKiloSize());
		if (this.getMimeType() != null) {
			other.setMimeType(this.getMimeType().clone());
		}
		if (this.getStore() != null) {
			other.setStore(this.getStore().clone());
		}
		other.setCreated(this.getCreated());
		other.setModified(this.getModified());

		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

} // END CLASS -----------------------------------------------------------------
