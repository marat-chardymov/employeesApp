package com.epam.util;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.db.ICityDAO;
import com.epam.db.ICountryDAO;
import com.epam.entities.City;
import com.epam.entities.Country;

public class RecordsGenerator {
	public static void main(String[] args) throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml");
		
//		for (int i = 1; i <= 10000; i++) {
//			insertCountry(context,i);
//		}
		//insertCountry(context,0);

	}

	public static void insertCountry(ApplicationContext context, int i) {
		Country country = new Country("CountryName" + i);
		ICountryDAO countryDAO = (ICountryDAO) context.getBean("countryDAO");
		countryDAO.save(country);
	}
	public static void insertCity(ApplicationContext context, int i) {
		City city = new City("City" + i);
		ICityDAO cityDAO = (ICityDAO) context.getBean("countryDAO");
		cityDAO.save(city);
	}

}
