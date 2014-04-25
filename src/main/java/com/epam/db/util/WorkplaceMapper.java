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

		Position position = new Position();
		position.setId(rs.getInt("position.id"));
		position.setName(rs.getString("position.name"));
		workplace.setPosition(position);

		Office office = new Office();
		office.setId(rs.getInt("office.id"));
		office.setEmployeeCount(rs.getInt("office.headcount"));
		workplace.setOffice(office);

		Company company = new Company();
		company.setId(rs.getInt("company.id"));
		company.setName(rs.getString("company.name"));
		office.setCompany(company);

		Address address = new Address();
		address.setId(rs.getInt("address.id"));
		address.setContent(rs.getString("address.content"));
		office.setAddress(address);

		City city = new City();
		city.setId(rs.getInt("city.id"));
		city.setName(rs.getString("city.name"));
		address.setCity(city);

		Country country=new Country();
		country.setId(rs.getInt("country.id"));
		country.setName(rs.getString("country.name"));
		city.setCountry(country);
		
		return workplace;
	}

}
