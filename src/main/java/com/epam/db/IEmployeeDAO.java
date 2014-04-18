package com.epam.db;

import java.util.List;

import com.epam.entities.Country;
import com.epam.entities.Employee;

public interface IEmployeeDAO {
	public List<Employee> getFirst100List();
	public void save(Employee employee);
	public Employee read(int id);
}
