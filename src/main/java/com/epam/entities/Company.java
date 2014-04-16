package com.epam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Company {
	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
