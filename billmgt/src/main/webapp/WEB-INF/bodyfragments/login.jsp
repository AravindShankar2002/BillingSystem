
<body>
<section class="vh-100 " class="bg-image bgimage" >
  <div class="container py-5 h-100">
    <div class="row d-flex align-items-center justify-content-center h-100">
      <div class="col-md-8 col-lg-7 col-xl-6">
        <img src="https://media.istockphoto.com/id/1281150061/vector/register-account-submit-access-login-password-username-internet-online-website-concept.jpg?s=612x612&w=0&k=20&c=9HWSuA9IaU4o-CK6fALBS5eaO1ubnsM08EOYwgbwGBo="
          class="img-fluid" alt="Phone image">
      </div>
      <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
      <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Login</h3>
      <%@include file="businessMessage.jsp" %>
        <form method="post" action="${pageContext.request.contextPath}/auth" modelAttribute="form">
          <!-- Email input -->
          <div class="form-outline mb-4">
          	<label class="form-label" for="form1Example13">Email address</label>
            <input type="email" id="form1Example13" class="form-control form-control-lg" name="email" />  
          </div>

          <!-- Password input -->
          <div class="form-outline mb-4">
          	<label class="form-label" for="form1Example23">Password</label>
            <input type="password" id="form1Example23" class="form-control form-control-lg" name="password" />
          </div>
          
          <div class="d-flex justify-content-around align-items-center mb-4">
          <!-- Submit button -->
          <button type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>
		  
        </form>
      </div>
       <span class="newUser">New User? </span><a href="${pageContext.request.contextPath}/registration" class="text-reset">
              <span class="createAccnt">Create account</span>
            </a>
    </div>
  </div>
</section>
</body>