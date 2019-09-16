package com.singbox.model;

import com.singbox.persist.rdbms.sorm.Identifiable;

public class EntityId implements Cloneable, Comparable<EntityId>, Identifiable {

	protected String id;

	// ---------------------------------------------------------------------------------------------------------------------------

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = (id == null || id.trim().length() == 0) ? null : id.trim();
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {

		if (this.getId() != null) {
			return this.getId();
		}

		return "";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		EntityId other = (EntityId) obj;

		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;

		return true;
	}

	public int compareTo(EntityId entityId) {

		return this.getId().compareTo(entityId.getId());
	}

	public EntityId clone() {

		EntityId other = new EntityId();

		other.setId(this.getId());

		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

} // END CLASS -----------------------------------------------------------------
