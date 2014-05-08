package com.epam.entities;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@NamedQueries({
		@NamedQuery(name = "FIND_EMPLOYEES", query = "SELECT e FROM Employee e"
				+ " LEFT JOIN FETCH e.address a"
				+ " LEFT JOIN FETCH a.city city LEFT JOIN FETCH city.country country"
				+ " ORDER BY e.id"),
		@NamedQuery(name = "COUNT_RECORDS", query = "SELECT COUNT(*) FROM Employee e") })
public class Employee {
	@Id
	@SequenceGenerator(name = "EMPLOYEES_SEQ", sequenceName = "EMPLOYEES_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEES_SEQ")
	private int id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@OneToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPLOYEE_ID")
	@BatchSize(size = 100)
	private Set<Workplace> workplaces = new LinkedHashSet<Workplace>();

	public Employee() {
		super();
	}

	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	public Set<Workplace> getWorkplaces() {
		return workplaces;
	}

	public void setWorkplaces(Set<Workplace> workplaces) {
		this.workplaces = workplaces;
	}
}
