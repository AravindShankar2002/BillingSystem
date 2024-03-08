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

		<h2 style="padding-top: 30px">Order List</h2>
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

					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="li" varStatus="u">
					<tr>
						<td>${li.productName}</td>
						<td>${li.firstName}</td>
						<td>${li.lastName}</td>
						<td>${li.email}</td>
						<td>${li.phoneNumber}</td>
						<td>${li.numberOfUnit}</td>
						<td>${li.totalPrice}</td>
						<td><c:if test="${sessionScope.user.userRole == 'Admin'}">
								<a
									href="${pageContext.request.contextPath}/orderDelete?id=${li.id}"><i
									class="fas fa-trash"></i></a>
							</c:if> <c:choose>
								<c:when test="${li.status == 'Booked'}">
									<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/cancel?id=${li.id}">Cancel</a>
								</c:when>
								<c:otherwise>
									<a class="btn btn-secondary btn-lg" href="#">Canceled</a>
								</c:otherwise>

							</c:choose></td>


						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>