<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/paginator.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Employees list</title>
<link rel="stylesheet" href="css/vendor/bootstrap.min.css" />
<link rel="stylesheet" href="css/vendor/tooltipster.css" />
<link rel="stylesheet" href="css/emplList.css" />

<script type="text/javascript" src="js/vendor/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/vendor/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/vendor/jquery.tooltipster.min.js"></script>
<script type="text/javascript" src="js/list.js"></script>


</head>
<body>
	<input type="hidden" value="${totalPages}" id="totalPages"> 
	<div class="container">
		<div class="row paginationRow">
			<div class="col-md-10 col-md-offset-2">
				<c:url var="searchUri" value="/?page=##" />
				<paginator:display maxLinks="7" currPage="${currPage}"
					totalPages="${totalPages}" uri="${searchUri}" />
			</div>
		</div>
		<c:choose>
			<c:when test="${empty emplList}">
					<div class="alert alert-warning">no employees found. Maybe page you enetered is out of range? Try smaller value</div>
				</c:when>
			<c:otherwise>
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
							<td><c:forEach var="workplace"
									items="${employee.workplaces}">
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
			</c:otherwise>
		</c:choose>
		<div class="row paginationRow">
			<div class="col-md-10 col-md-offset-2">
				<c:url var="searchUri" value="/?page=##" />
				<paginator:display maxLinks="7" currPage="${currPage}"
					totalPages="${totalPages}" uri="${searchUri}" />
			</div>
		</div>
	</div>
</body>
</html>