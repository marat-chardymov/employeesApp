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
	<div class="container">
		<div class="row" id="pageBar">
			<div class="col-md-2"></div>
			<div class="col-md-4">
				<ul class="pagination" id="pages">
					<li><a href="#">&laquo;</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
			</div>
			<div class="col-md-6">
				<label for="itemsPerPage" id="itemsPerPageLabel">items per
					page</label> <input type="text" id="itemsPerPage" class="numberInput"
					name="itemsPerPage"> <label for="pageNumber" id="pageNumberLabel">page #</label>
				<input type="text" id="pageNumber" class="numberInput"
					name="pageNumber"> <input type="submit"
					class="btn btn-default" id="goBtn" value="Go">
			</div>
		</div>

		<table class="table table-bordered">
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
				headcount = ${workplace.office.employeeCount},
				${workplace.position.name}
				<br>
						</c:forEach></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>