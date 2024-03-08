<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
          <div class="card-body p-4 p-md-5">
           <%@include file="businessMessage.jsp" %>
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Payment</h3>
            
             
          <sf:form method="post" action="${pageContext.request.contextPath}/addPayment" modelAttribute="form">    
            
            <sf:input type="hidden" id="id" class="form-control form-control-lg" path="id" name="id" value="${form.id}"/>
            
            <sf:input type="hidden" id="billId" class="form-control form-control-lg" path="billId" name="billId" value="${billId}"/>
            
              <div class="row">
                <div class="col-md-6 mb-4">
                  <div class="form-outline">   
                  <s:bind path="cardNumber">
                   <label class="form-label" for="cardNumber">Card Number</label>
                  
                  <sf:input type="text" id="cardNumber" class="form-control form-control-lg" path="cardNumber" name="cardNumber"/>
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </s:bind>      
                 
                  
                  </div>
				 
                </div>
                <div class="col-md-6 mb-4">
                
                  <div class="form-outline">
                  <s:bind path="nameOnCard">
                   <label class="form-label" for="nameOnCard">Name On Card</label>
                 
                    <sf:input type="text" id="nameOnCard" class="form-control form-control-lg" path="nameOnCard"/>
                    <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </s:bind>
                  </div>
               
                </div>
                
              </div>

              <div class="row">
                <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="expireDate">
                  <div class="form-outline datepicker w-100">
                  <label for="expireDate" class="form-label">Expiry Date</label>
                    <sf:input type="date" class="form-control form-control-lg" id="expireDate" path="expireDate" placeholder="dd/MM/yyyy"/>
                    <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </div>
                  </s:bind>

                </div>
              </div>


                <div class="col-md-6 mb-4 pb-2" style="padding-left: 0px;">
                 <s:bind path="totalPrice">
                  <div class="form-outline">
                  <label class="form-label" for="totalPrice">Total Price in dollars</label>
                    <sf:input type="tel" id="totalPrice" class="form-control form-control-lg" path="totalPrice" value="${totalPrice}" readonly="true"/>
                    <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </div>
					</s:bind>
                </div> 
 
                <c:choose>
  				<c:when test="${form.id>0}">
   				  <div class="mt-4 pt-2">
                <input class="btn btn-primary btn-lg" type="submit" value="Update" />
              </div>   
 			</c:when>

  			<c:otherwise>
             <div class="mt-4 pt-2">
                <input class="btn btn-primary btn-lg" type="submit" value="Pay" />
              </div>
  			</c:otherwise>
			</c:choose> 
  
        </sf:form>
          
          </div>
        </div>
      </div>
    </div>
</div>
</section>