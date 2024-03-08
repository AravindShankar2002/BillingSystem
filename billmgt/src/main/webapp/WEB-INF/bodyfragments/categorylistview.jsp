<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
</head>
<body >

<div class="container content" >

<h2 style="padding-top: 30px">Category List</h2>
<%@include file="businessMessage.jsp" %>
<table class="table bg-light text-dark" style="margin-top: 20px;">
  <thead>
    <tr>
      <th scope="col">Category Name</th>
      <th scope="col">Image</th>   
      <c:if test="${sessionScope.user.userRole == 'Admin'}">  
      <th scope="col">Action</th>
      </c:if>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list}" var="li" varStatus="u">
    <tr>     
      <td><a href="${pageContext.request.contextPath}/bycategory?id=${li.id}">${li.name} </a></td>
      <td><img alt="" src="${pageContext.request.contextPath}/getCategoryImage/${li.id}" width="150px" height="100px" ></td> 
      <c:if test="${sessionScope.user.userRole == 'Admin'}">
      <td>      
      <a href="${pageContext.request.contextPath}/categoryEdit?id=${li.id}"><i class="fas fa-pen"></i></a>
      <a href="${pageContext.request.contextPath}/categoryDelete?id=${li.id}"><i class="fas fa-trash"></i></a>      
      </td>
      </c:if>
    </tr>
   </c:forEach>
  </tbody>
</table>

</div>

</body>
</html>