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
<body >

<div class="container content" >
<h2 style="margin-top: 5px;">Search</h2>

		<input class="form-control" id="myInput" type="text"
			placeholder="Search.."> <br>
<h2 style="padding-top: 30px">Product List </h2>
<%@include file="businessMessage.jsp" %>
<table class="table bg-light text-dark" style="margin-top: 20px;">
  <thead>
    <tr>
      <th scope="col">Product Name</th>
      <th scope="col">quantity</th>
      <th scope="col">price</th>
      <th scope="col">Image</th>   
       <c:if test="${sessionScope.user.userRole == 'Admin'}">  
      <th scope="col">Action</th>
      </c:if>
    </tr>
  </thead>
  <tbody id="myTable">
  <c:forEach items="${list}" var="li" varStatus="u">
    <tr>     
      <td>${li.name}</td>
      <td>${li.quantity}</td>
      <td>${li.price}</td>
      <td><img alt="" src="${pageContext.request.contextPath}/getProductImage/${li.id}" width="150px" height="100px" ></td> 
       
      <td>    
       <c:if test="${sessionScope.user.userRole == 'Admin'}"> 
      <a href="${pageContext.request.contextPath}/productEdit?id=${li.id}" style="padding: 5px;"><i class="fas fa-pen"></i></a>
      <a href="${pageContext.request.contextPath}/productDelete?id=${li.id}" style="padding: 5px;"><i class="fas fa-trash"></i></a>  
   
       </c:if>    
       <a href="${pageContext.request.contextPath}/addToCart?id=${li.id}" class="btn btn-primary btn-m active">Add To Cart</a>   
      </td>
     
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