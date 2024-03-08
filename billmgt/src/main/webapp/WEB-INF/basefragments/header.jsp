<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/home" style="color: white;">Supermarket Billing</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav" style="justify-content: space-between;">
			<div class="float-left">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/home" style="color: white;">Home</a>
					</li>

					<c:if test="${sessionScope.user == null}">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/registration" style="color: white;">SignUp</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/loginView" style="color: white;">Login</a></li>
					</c:if>

					<c:if test="${sessionScope.user.userRole == 'Admin'}">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" style="color: white;"> Users </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item"
									href="${pageContext.request.contextPath}/registration">Add
									User</a> <a class="dropdown-item"
									href="${pageContext.request.contextPath}/userList">User
									List</a>
							</div></li>
                    	<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" style="color: white;"> Category </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item"
									href="${pageContext.request.contextPath}/category">Add
									Category</a> <a class="dropdown-item"
									href="${pageContext.request.contextPath}/categoryList">Category
									List</a>
							</div></li>


                     	<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" style="color: white;"> Product </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item"
									href="${pageContext.request.contextPath}/product">Add
									 Product </a> <a class="dropdown-item"
									href="${pageContext.request.contextPath}/productList"> Product 
									List</a>
							</div></li>
							
					       	<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" style="color: white;"> Order </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item"
									href="${pageContext.request.contextPath}/order">Add
									  Order  </a> <a class="dropdown-item"
									href="${pageContext.request.contextPath}/orderList">  Order  
									List</a>
							</div></li>
							
							<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/saleslist" style="color: white;">Sales</a></li>
							<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/bill" style="color: white;">Generate Bills</a></li>
							
								<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/billlist" style="color: white;">Bill List</a></li>
							
							


					</c:if>

					<c:if test="${sessionScope.user.userRole == 'User'}">
					
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" style="color: white;"> Category </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							 <a class="dropdown-item"
									href="${pageContext.request.contextPath}/categoryList">Category
									List</a>
							</div></li>
							
							    	<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" style="color: white;"> Product </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							 <a class="dropdown-item"
									href="${pageContext.request.contextPath}/productList"> Product 
									List</a>
							</div></li>
					
					     	<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" style="color: white;"> Order </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item"
									href="${pageContext.request.contextPath}/order">Add
									  Order  </a> <a class="dropdown-item"
									href="${pageContext.request.contextPath}/orderList">  Order  
									List</a>
							</div></li>
							
							<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/billlist" style="color: white;">Bill List</a></li>
                           
                           <li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/cartList" style="color: white;">Cart List</a></li>

					</c:if>



				</ul>
			</div>
			<div>
				<ul class="navbar-nav" style="margin-right: 83px;">
					<c:if test="${sessionScope.user != null}">
			               <li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/cartList" style="color: white;">My Cart</a></li>
						    <li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" style="color: white;"> <i class="fa fa-user" style="color:white; font-size: 20px;"></i> </a>
							
							<div class="dropdown-menu profile-dropdown" aria-labelledby="navbarDropdown">
								<a class="dropdown-item"
									href="${pageContext.request.contextPath}/userEdit?id=${sessionScope.user.id}">
									View Profile</a> <a class="dropdown-item"
									href="${pageContext.request.contextPath}/logout">Logout</a>

							</div></li>	
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</nav>