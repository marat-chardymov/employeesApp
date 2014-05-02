package com.epam.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.epam.db.IEmployeeDAO;
import com.epam.db.util.EmployeeMapper;
import com.epam.db.util.WorkplaceMapper;
import com.epam.entities.Employee;
import com.epam.entities.Workplace;

public class EmployeeJdbcDAO implements IEmployeeDAO {

	private static final String EMPLOYEES_SQL = "SELECT employee.id as e_id,"
			+ "first_name as e_first_name,last_name as e_last_name,"
			+ "address.id as address_id,address.content as address_content,"
			+ "city.id as city_id,city.name as city_name,"
			+ "country.id as country_id,"
			+ "country.name as country_name"
			+ " FROM employee LEFT JOIN address"
			+ " ON employee.address_id = address.id"
			+ " JOIN city ON address.city_id = city.id"
			+ " JOIN country ON city.country_id = country.id WHERE rownum<=? ORDER BY e_id";
	private static final String WORKPLACES_SQL = "select "
			+ "workplace.EMPLOYEE_ID as w_EMPLOYEE_ID, "
			+ "workplace.ID as w_ID, "
			+ "workplace.POSITION_ID as w_POSITION_ID, "
			+ "workplace.OFFICE_ID as w_OFFICE_ID, " + "position.ID as p_ID, "
			+ "position.NAME as p_NAME, " + "office.ID as o_ID, "
			+ "office.COMPANY_ID as o_COMPANY_ID, "
			+ "office.ADDRESS_ID as o_ADDRESS_ID, "

			+ "(select COUNT(*) from workplace where "
			+ "workplace.OFFICE_ID = office.ID) as o_headcount, "

			+ "company.ID as c_ID, " + "company.NAME as c_NAME, "
			+ "address.ID as a_ID, " + "address.CONTENT as a_CONTENT, "
			+ "address.CITY_ID as a_CITY_ID, " + "city.ID as city_ID, "
			+ "city.NAME as city_NAME, "
			+ "city.COUNTRY_ID as city_COUNTRY_ID, "
			+ "country.ID as country_ID, " + "country.NAME as country_NAME "
			+ "from WORKPLACE "
			+ "inner join POSITION on workplace.POSITION_ID=position.ID "
			+ "inner join OFFICE on workplace.OFFICE_ID=office.ID "
			+ "left outer join COMPANY on office.COMPANY_ID=company.ID "
			+ "left outer join ADDRESS on office.ADDRESS_ID=address.ID "
			+ "left outer join CITY on address.CITY_ID=city.ID "
			+ "left outer join COUNTRY on city.COUNTRY_ID=country.ID "
			+ "where workplace.EMPLOYEE_ID in ";

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Employee> getList(int begin, int numberOfElements)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		LinkedHashMap<Integer, Employee> employeeMap = new LinkedHashMap<Integer, Employee>();
		try {
			conn = dataSource.getConnection();
			ps1 = conn.prepareStatement(EMPLOYEES_SQL);
			ps1.setInt(begin + 1, numberOfElements);
			rs1 = ps1.executeQuery();

			// second query retrives workplaces for employees
			String query2 = addIdsToQuery(numberOfElements);
			ps2 = conn.prepareStatement(query2);

			while (rs1.next()) {
				Employee employee = new EmployeeMapper().mapRow(rs1,
						rs1.getRow());
				employeeMap.put(employee.getId(), employee);
				// set employees ids as parameters for 2nd query
				int rowNumber = rs1.getRow();
				ps2.setInt(rowNumber, employee.getId());
			}
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				Workplace workplace = new WorkplaceMapper().mapRow(rs2,
						rs2.getRow());
				int employeeId = rs2.getInt("W_EMPLOYEE_ID");
				Employee employee = employeeMap.get(employeeId);
				Set<Workplace> workplaces = employee.getWorkplaces();
				workplaces.add(workplace);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs1.close();
			ps1.close();
			rs2.close();
			ps2.close();
			conn.close();
		}
		// return values of employeeMap as List
		return new ArrayList<Employee>(employeeMap.values());
	}

	private String addIdsToQuery(int emplNumber) {
		StringBuilder strQuery = new StringBuilder(WORKPLACES_SQL);
		strQuery.append("(");
		for (int i = 1; i <= emplNumber - 1; i++) {
			strQuery.append("?,");
		}
		strQuery.append("?) ORDER BY w_EMPLOYEE_ID");
		return strQuery.toString();
	}
}
