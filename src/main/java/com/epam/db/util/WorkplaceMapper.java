package com.epam.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.entities.Address;
import com.epam.entities.City;
import com.epam.entities.Company;
import com.epam.entities.Country;
import com.epam.entities.Office;
import com.epam.entities.Position;
import com.epam.entities.Workplace;

public class WorkplaceMapper implements RowMapper<Workplace> {

	@Override
	public Workplace mapRow(ResultSet rs, int rowNum) throws SQLException {
		Workplace workplace = new Workplace();
		workplace.setId(rs.getInt("w_ID"));

		Position position = new Position();
		position.setId(rs.getInt("p_ID"));
		position.setName(rs.getString("p_NAME"));
		workplace.setPosition(position);

		Office office = new Office();
		office.setId(rs.getInt("o_ID"));
		office.setEmployeeCount(rs.getInt("o_headcount"));
		workplace.setOffice(office);

		Company company = new Company();
		company.setId(rs.getInt("c_ID"));
		company.setName(rs.getString("c_NAME"));
		office.setCompany(company);

		Address address = new Address();
		address.setId(rs.getInt("a_ID"));
		address.setContent(rs.getString("a_CONTENT"));
		office.setAddress(address);

		City city = new City();
		city.setId(rs.getInt("city_ID"));
		city.setName(rs.getString("city_NAME"));
		address.setCity(city);

		Country country=new Country();
		country.setId(rs.getInt("country_ID"));
		country.setName(rs.getString("country_NAME"));
		city.setCountry(country);
		
		return workplace;
	}

}
