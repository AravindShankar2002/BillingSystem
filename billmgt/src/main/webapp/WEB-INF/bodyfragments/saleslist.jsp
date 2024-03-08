<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body>



	<div class="container content">
		
		<h2 style="margin-top: 5px;">Search</h2>

		<input class="form-control" id="myInput" type="text"
			placeholder="Search.."> <br>
       <h2 style="padding-top: 5px">Sales List</h2>
	
		<%@include file="businessMessage.jsp"%>
		<table class="table bg-light text-dark table-striped"
			style="margin-top: 20px;">
			<thead>
				<tr>
					<th scope="col">Product Name</th>
					<th scope="col">firstName</th>
					<th scope="col">lastName</th>
					<th scope="col">email</th>
					<th scope="col">phoneNumber</th>
					<th scope="col">numberOfUnit</th>
					<th scope="col">Total Price</th>
					<th scope="col">Order Date</th>

				</tr>
			</thead>
			<tbody id="myTable">
				<c:forEach items="${list}" var="li" varStatus="u">
					<tr>
						<td>${li.productName}</td>
						<td>${li.firstName}</td>
						<td>${li.lastName}</td>
						<td>${li.email}</td>
						<td>${li.phoneNumber}</td>
						<td>${li.numberOfUnit}</td>
						<td>${li.totalPrice}</td>
						<td>${li.orderDate}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<script>
		$(document)
				.ready(
						function() {
							$("#myInput")
									.on(
											"keyup",
											function() {
												var value = $(this).val()
														.toLowerCase();
												$("#myTable tr")
														.filter(
																function() {
																	$(this)
																			.toggle(
																					$(
																							this)
																							.text()
																							.toLowerCase()
																							.indexOf(
																									value) > -1)
																});
											});
						});
	</script>

</body>
</html>