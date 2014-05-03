<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Employees list</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/emplList.css" />
</head>
<body>
	<table class="table">
		<tr>
			<th>First name</th>
			<th>Last name</th>
			<th>Address</th>
			<th>Offices</th>
		</tr>
		<c:forEach var="employee" items="${emplList}">
			<tr>
				<td>${employee.firstName}</td>
				<td>${employee.lastName}</td>
				<td>${employee.address.content},${employee.address.city.name},
					${employee.address.city.country.name}</td>
				<td><c:forEach var="workplace" items="${employee.workplaces}">
				${workplace.office.company.name},
				${workplace.office.address.content},
				${workplace.office.address.city.name},
				${workplace.office.address.city.country.name},
				office id = ${workplace.office.id},
				headcount = ${workplace.office.employeeCount},
				${workplace.position.name}
				<br>
					</c:forEach></td> 
			</tr>
		</c:forEach>
	</table>
</body>
</html>