package com.epam.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.epam.db.IEmployeeDAO;
import com.epam.db.util.EmployeeMapper;
import com.epam.entities.Employee;

public class EmployeeJdbcDAO implements IEmployeeDAO {

	private static final String LIST_SQL = "SELECT employee.id as e_id,"
			+ "first_name as e_first_name,last_name as e_last_name,"
			+ "address.id as address_id,address.content as address_content,"
			+ "city.id as city_id,city.name as city_name,"
			+ "country.id as country_id," + "country.name as country_name"
			+ " FROM employee LEFT JOIN address"
			+ " ON employee.address_id = address.id"
			+ " JOIN city ON address.city_id = city.id"
			+ " JOIN country ON city.country_id = country.id WHERE rownum<?";

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Employee> getList(int begin, int numberOfElements)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(LIST_SQL);
			ps.setInt(1, 100);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee employee = new EmployeeMapper().mapRow(rs, 0);
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			conn.close();
		}
		return employeeList;
	}
}
