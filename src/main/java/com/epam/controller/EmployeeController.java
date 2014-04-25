package com.epam.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.db.IEmployeeDAO;
import com.epam.entities.Employee;

@Controller
@RequestMapping("/")
public final class EmployeeController {
	
	@Autowired
	@Qualifier("employeeJdbcDAO")
    private IEmployeeDAO employeeDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String printEmplList(ModelMap model) throws SQLException {
		List<Employee> emplList = employeeDAO.getList(0,100);
		model.addAttribute("emplList", emplList);
		return "employeeList";
	}

}