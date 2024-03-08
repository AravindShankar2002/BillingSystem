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
<title>Insert title here</title>
</head>
<body>

<section class="h-100" style="background-color: #eee;">
  <div class="container h-100 py-5">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-10">

        <div class="d-flex justify-content-between align-items-center mb-4">
          <h3 class="fw-normal mb-0 text-black">Shopping Cart</h3>
        </div>

   <c:forEach items="${list}" var="li" varStatus="u">
        <div class="card rounded-3 mb-4">
          <div class="card-body p-4">
            <div class="row d-flex justify-content-between align-items-center">
              <div class="col-md-2 col-lg-2 col-xl-2">
                <img
                  src="${pageContext.request.contextPath}/getCartProductImage/${li.id}"
                  class="img-fluid rounded-3" alt="Cotton T-shirt">
              </div>
              <div class="col-md-3 col-lg-3 col-xl-3">
                <p class="lead fw-normal mb-2">${li.productName}</p>
            
              </div>
              <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
             

                    <sf:form method="post"
							action="${pageContext.request.contextPath}/updateCart" modelAttribute="form">
							<sf:input type="hidden" id="id"
								class="form-control form-control-lg" path="id" name="id"
								value="${li.id}" />
								
							<sf:input type="hidden" id="productId"
								class="form-control form-control-lg" path="productId" name="productId"
								value="${li.productId}" />

							<div class="row">

								<div>
									<div >
										<s:bind path="quantity">
											<label class="form-label" for="quantity">Quantity</label>
											<sf:input type="text" id="quantity" 
												class="form-control form-control-sm" path="quantity" name="quantity" value="${li.quantity}" />
											<font color="red" style="font-size: 15px"><sf:errors
													path="${status.expression}" /></font>
										</s:bind>


									</div>
								</div>
							     <div style="margin-top: 10px;">
							     <input class="btn btn-primary btn-sm" type="submit" value="Update" />
							     </div>
                            </div>
                           
							
				
					</sf:form>
              </div>
              <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                <h5 class="mb-0">${li.price}</h5>
              </div>
              <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                <a href="${pageContext.request.contextPath}/cartDelete?id=${li.id}" class="text-danger"><i class="fas fa-trash fa-lg"></i></a>
              </div>
            </div>
          </div>
        </div>
</c:forEach>
    

        <div class="card">
          <div class="card-body">
            
            <a href="${pageContext.request.contextPath}/orderByCart?totalPrice=${totalPrice}" class="btn btn-primary btn-m active">Pay Total ${totalPrice}</a> 
          </div>
        </div>

      </div>
    </div>
  </div>
</section>

</body>
</html>