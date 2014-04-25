package com.epam.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.epam.db.IEmployeeDAO;
import com.epam.entities.Employee;

public class EmployeeJdbcDAO implements IEmployeeDAO {

	private static final String LIST_SQL = "SELECT * FROM Employee";

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Employee> getList(int begin, int numberOfElements) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<News> newsList = new ArrayList<News>();
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return null;
	}
}
