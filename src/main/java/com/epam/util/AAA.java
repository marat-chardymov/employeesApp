package com.epam.util;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.db.ICountryDAO;
import com.epam.db.IEmployeeDAO;
import com.epam.entities.Country;
import com.epam.entities.Employee;

public class AAA {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"mvc-dispatcher-servlet.xml");
		// insertCountry(context,0);
		//insertEmployee(context);
		System.out.println(get100(context));
		//System.out.println(getEmployee(context, 10003));
	}

	public static void insertCountry(ApplicationContext context, int i) {
		Country country = new Country("AAAAAAAAAAAAAAAAAAAAAAAa" + i);
		ICountryDAO countryDAO = (ICountryDAO) context.getBean("countryDAO");
		countryDAO.save(country);
	}
	
	public static void insertEmployee(ApplicationContext context) {
		Employee employee = new Employee("AAAAAAAA","BBBBBBBBB");
		IEmployeeDAO employeeDAO = (IEmployeeDAO) context.getBean("employeeDAO");
		employeeDAO.save(employee);
	}
	public static List<Employee> get100(ApplicationContext context){
		IEmployeeDAO employeeDAO = (IEmployeeDAO) context.getBean("employeeDAO");
		return employeeDAO.getFirst100List();
	}
	public static Employee getEmployee(ApplicationContext context,int id){
		IEmployeeDAO employeeDAO = (IEmployeeDAO) context.getBean("employeeDAO");
		return employeeDAO.read(id);
	}

}
