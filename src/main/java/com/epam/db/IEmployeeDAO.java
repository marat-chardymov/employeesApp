package com.epam.db;

import java.sql.SQLException;
import java.util.List;

import com.epam.entities.Employee;

public interface IEmployeeDAO {
	public List<Employee> getList(int begin,int numberOfElements) throws SQLException;
}
