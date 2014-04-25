package com.epam.db;

import java.util.List;

import com.epam.entities.Employee;

public interface IEmployeeDAO {
	public List<Employee> getList(int begin,int numberOfElements);
}
