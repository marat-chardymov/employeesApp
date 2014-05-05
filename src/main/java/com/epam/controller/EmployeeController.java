package com.epam.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.db.IEmployeeDAO;
import com.epam.entities.Employee;

@Controller
@RequestMapping("/")
public final class EmployeeController {

	@Autowired
	@Qualifier("employeeJpaDAO")
	private IEmployeeDAO employeeDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String printEmplList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "itemsPerPage", defaultValue = "100") int itemsPerPage,
			ModelMap model) throws SQLException {
		System.out.println(page);
		model.addAttribute("currPage", page);
		System.out.println(itemsPerPage);
		List<Employee> emplList = employeeDAO.getList((page-1)*itemsPerPage, 100);
		model.addAttribute("emplList", emplList);
		return "employeeList";
	}

}