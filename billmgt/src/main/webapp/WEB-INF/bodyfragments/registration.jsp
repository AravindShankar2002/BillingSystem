<%@page import="java.text.DateFormat"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page import="java.util.Date"%>


<%DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>

<body>
<section class="vh-100 gradient-custom ">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
    <div class="col-md-5 col-lg-5 col-xl-4 image-class">
        <img src="https://i.pinimg.com/736x/4b/39/a9/4b39a94252cddcc7d20326c140278c4e.jpg"
          class="img-fluid" alt="Phone image">
      </div>
      <div class="col-md-9 col-lg-7 col-xl-7 offset-xl-1">
        <div class="card shadow-2-strong card-registration">
          <div class="card-body p-4 p-md-5">
           <%@include file="businessMessage.jsp" %>
           <c:choose>
  				<c:when test="${form.id>0}">
   				    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Profile</h3>
 			</c:when>

  			<c:otherwise>
                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Registration Form</h3>
  			</c:otherwise>
			</c:choose> 
           
            
             
            <sf:form method="post" action="${pageContext.request.contextPath}/AddUser" modelAttribute="form">
            <sf:input type="hidden" id="id" class="form-control form-control-lg" path="id" name="id" value="${form.id}"/>
              <div class="row">
                <div class="col-md-6 mb-4">
                  <div class="form-outline">   
                  <s:bind path="firstName">
                  <label class="form-label" for="firstName">First Name</label>
                  <sf:input type="text" id="firstName" class="form-control form-control-lg" path="firstName" name="firstName"/>
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </s:bind>      
                  
                  
                  </div>
				 
                </div>
                <div class="col-md-6 mb-4">
                
                  <div class="form-outline">
                  <s:bind path="lastName">
                  <label class="form-label" for="lastName">Last Name</label>
                    <sf:input type="text" id="lastName" class="form-control form-control-lg" path="lastName"/>
                    <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </s:bind>
                  </div>
               
                </div>
                
              </div>

              <div class="row">
                <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="dob">
                  <div class="form-outline datepicker w-100">
                   <label for="birthdayDate" class="form-label">Date of birth</label>
                    <sf:input type="date" class="form-control form-control-lg" id="dob" path="dob" placeholder="dd/MM/yyyy" />
                   <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </div>
                  </s:bind>

                </div>
                <div class="col-md-6 mb-4">
                
                  <label for="Gender" class="form-label">Gender </label><br/>
                  <s:bind path="gender">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"  id="gender"
                      value="Female" checked name="gender"/>
                    <label class="form-check-label" for="femaleGender">Female</label>
                  </div>
                  
                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"  id="gender"
                      value="Male" name="gender"/>
                    <label class="form-check-label" for="maleGender">Male</label>
                  </div>

                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="gender"
                      value="Other" name="gender" />
                    <label class="form-check-label" for="otherGender">Other</label>
                  </div>
				</s:bind>
                </div>
              </div>


              <div class="row">
              
           <c:choose>
           
           <c:when test="${form.id>0}">
             <div class="col-md-6 mb-4 pb-2">
                  <s:bind path="email">
                  <div class="form-outline">
                  <label class="form-label" for="emailAddress">Email</label>
                    <sf:input type="email" id="emailAddress" class="form-control form-control-lg"  path="email" readonly="true"/>
                    <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </div>
                  </s:bind>

                </div>
           </c:when>
           <c:otherwise>
             <div class="col-md-6 mb-4 pb-2">
                  <s:bind path="email">
                  <div class="form-outline">
                  <label class="form-label" for="emailAddress">Email</label>
                    <sf:input type="email" id="emailAddress" class="form-control form-control-lg"  path="email"/>
                    <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </div>
                  </s:bind>

                </div>
           
           </c:otherwise>
           </c:choose>
              
              
                  <div class="col-md-6 mb-4 pb-2">
                  <s:bind path="password">
                  <div class="form-outline">
                   <label class="form-label" for="emailAddress" >Password</label>
                    <sf:input type="password" id="emailAddress" class="form-control form-control-lg" path="password"/>
                    <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </div>
					</s:bind>
                </div>
                <div class="col-md-6 mb-4 pb-2">
                 <s:bind path="phoneNumber">
                  <div class="form-outline">
                  <label class="form-label" for="phoneNumber">Phone Number</label>
                    <sf:input type="tel" id="phoneNumber" class="form-control form-control-lg" path="phoneNumber" />
                      <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </div>
					</s:bind>
                </div> 
              </div>
 

                <c:choose>
  				<c:when test="${form.id>0}">
   				  <div >
                <input class="btn btn-primary btn-lg" type="submit" value="Update" />
              </div>   
 			</c:when>

  			<c:otherwise>
             <div >
                <input class="btn btn-primary btn-lg" type="submit" value="Submit" />
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
</body>