package com.epam.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.db.ICountryDAO;
import com.epam.entities.Country;

public class AAA {
	public static void main(String[] args) throws SQLException {

		//runJDBC();
		//runHibernate();
		hibMkoyng();

	}
	public static void hibMkoyng(){
		System.out.println("Maven + Hibernate + Oracle");
		Session session = HibernateUtil.getSessionFactory().openSession();
 
		session.beginTransaction();
		Country country = new Country("qwerqwerqwe");
 
		session.save(country);
		session.getTransaction().commit();
	}

	public static void runHibernate() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml");
		Country country = new Country("asdf");
		ICountryDAO countryDAO = (ICountryDAO) context.getBean("countryDAO");
		//countryDAO.save(country);
		System.out.println(countryDAO.findAll());
	}

	public static void runJDBC() throws SQLException {
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		Connection connection = null;
		Statement statement = null;
		try {
			try {

				Class.forName("oracle.jdbc.driver.OracleDriver");

			} catch (ClassNotFoundException e) {

				System.out.println("Where is your Oracle JDBC Driver?");
				e.printStackTrace();
				return;

			}

			System.out.println("Oracle JDBC Driver Registered!");


			try {

				connection = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe", "dbadmin",
						"dbadmin");

			} catch (SQLException e) {

				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return;

			}

			if (connection != null) {
				System.out
						.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
			}
			String insertTableSQL = "INSERT INTO COUNTRIES" + "(NAME) "
					+ "VALUES" + "('Marat')";
			statement = connection.createStatement();
			statement.executeUpdate(insertTableSQL);
		} finally {

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}

		}
		// String selectTableSQL =
		// "select * from all_sequences where sequence_name = 'COUNTRIES_SEQ'";
		// Statement statement = connection.createStatement();
		// ResultSet rs = statement.executeQuery(selectTableSQL);
		// while (rs.next()) {
		// ResultSetMetaData rsmd = rs.getMetaData();
		// int columnCount = rsmd.getColumnCount();
		//
		// // The column count starts from 1
		// for (int i = 1; i < columnCount + 1; i++ ) {
		// String name = rsmd.getColumnName(i);
		// System.out.println(name);
		// // Do stuff with name
		// }
		// }
	}
}
