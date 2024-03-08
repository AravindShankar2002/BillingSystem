<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<script src="https://kit.fontawesome.com/a076d05399.js"
	crossorigin="anonymous"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body>

	<div class="container content">

		<h2 style="padding-top: 30px">Bill/Invoice List</h2>
		<%@include file="businessMessage.jsp"%>
		<table class="table bg-light text-dark" style="margin-top: 20px;">
			<thead>
				<tr>
					<th scope="col">Product Name</th>
					<th scope="col">Category</th>
					<th scope="col">quantity</th>
					<th scope="col">price</th>
					<th scope="col">User</th>
					<th scope="col">Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="li" varStatus="u">
					<tr>
						<td>${li.productName}</td>
						<td>${li.catId}</td>
						<td>${li.quantity}</td>
						<td>${li.price}</td>
						<td>${li.userEmail}</td>
						<td><c:choose>
								<c:when test="${li.status == 'Pendding'}">
									<a class="btn btn-primary btn-lg"
										href="${pageContext.request.contextPath}/payment?totalPrice=${li.price}&billId=${li.id}">Pay</a>
								</c:when>
								<c:otherwise>
									<a class="btn btn-secondary btn-lg" href="#">Paid</a>
								</c:otherwise>

							</c:choose></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>