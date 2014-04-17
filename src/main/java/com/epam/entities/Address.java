package com.epam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class Address {
	private int id;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
}
