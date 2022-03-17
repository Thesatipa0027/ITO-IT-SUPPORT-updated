package com.itsupport.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
	@Id
	private int category_id;

	private String category_desc;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = { CascadeType.ALL })
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	private List<Sub_Category> sub_category_list;

	public Category() {
		super();
	}

	public Category(int category_id, String category_desc) {
		super();
		this.category_id = category_id;
		this.category_desc = category_desc;
	}

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_desc() {
		return category_desc;
	}

	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}

}
