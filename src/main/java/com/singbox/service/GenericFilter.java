package com.singbox.service;

public class GenericFilter implements Cloneable {

	// ---------------------------------------------------------------------------------------------------------------------------

	protected String id;

	private Boolean unlimited = false;

	private Integer limit;

	private Integer offset;

	private Integer orderBy = 1;

	private Boolean orderByDesc = false;

	// ---------------------------------------------------------------------------------------------------------------------------

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = (id == null || id.trim().length() == 0) ? null : id.trim();
	}

	// GET Unlimited
	public Boolean getUnlimited() {
		return this.unlimited;
	}

	// SET Unlimited
	public void setUnlimited(Boolean unlimited) {
		unlimited = (unlimited == null) ? false : unlimited;
		this.unlimited = unlimited;
	}

	// GET Limit
	public Integer getLimit() {
		limit = (limit == null || limit < 1) ? 50 : limit;
		return this.limit;
	}

	// SET Limit
	public void setLimit(Integer limit) {
		limit = (limit == null || limit < 1) ? 50 : limit;
		this.limit = limit;
	}

	// GET Offset
	public Integer getOffset() {
		offset = (offset == null || offset < 0) ? 0 : offset;
		return this.offset;
	}

	// SET Offset
	public void setOffset(Integer offset) {
		offset = (offset == null || offset < 0) ? 0 : offset;
		this.offset = offset;
	}

	// GET Order by att
	public Integer getOrderBy() {
		return this.orderBy;
	}

	// SET Order by att
	public void setOrderBy(Integer orderBy) {
		this.orderBy = (orderBy == null) ? 1 : orderBy;
	}

	// GET Order by desc
	public Boolean getOrderByDesc() {
		return this.orderByDesc;
	}

	// SET Order by desc
	public void setOrderByDesc(Boolean orderByDesc) {
		orderByDesc = (orderByDesc == null) ? false : orderByDesc;
		this.orderByDesc = orderByDesc;
	}

	@Override
	public GenericFilter clone() {
		GenericFilter other = new GenericFilter();

		other.setOffset(this.getOffset());
		other.setLimit(this.getLimit());
		other.setOrderBy(this.getOrderBy());
		other.setOrderByDesc(this.getOrderByDesc());
		other.setUnlimited(this.getUnlimited());

		return other;
	}

} // END CLASS -----------------------------------------------------------------
