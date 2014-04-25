package com.epam.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.entities.Address;
import com.epam.entities.City;
import com.epam.entities.Country;
import com.epam.entities.Employee;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("e_id"));
		employee.setFirstName(rs.getString("e_first_name"));
		employee.setLastName(rs.getString("e_last_name"));

		Address address = new Address();
		address.setId(rs.getInt("address_id"));
		address.setContent(rs.getString("address_content"));
		employee.setAddress(address);

		City city = new City();
		city.setId(rs.getInt("city_id"));
		city.setName(rs.getString("city_name"));
		address.setCity(city);

		Country country = new Country();
		country.setId(rs.getInt("country_id"));
		country.setName(rs.getString("country_name"));
		city.setCountry(country);

		return employee;		
	}

}
