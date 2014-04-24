package com.epam.entities;

public class Workplace {
	
	private int id;	
	private Position position;
	private Office office;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}		
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}	
	
}
