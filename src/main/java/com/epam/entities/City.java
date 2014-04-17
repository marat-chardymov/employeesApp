package com.epam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class City {

	private int id;
	private String name;

	public City(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
