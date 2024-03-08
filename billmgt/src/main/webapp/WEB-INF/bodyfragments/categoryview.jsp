<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<section class="vh-100 gradient-custom ">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 15px; margin-bottom:30px; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); background-color: black !important; color: white;">
          <div class="card-body p-4 p-md-5">
           <%@include file="businessMessage.jsp" %>

            <c:choose>
  				<c:when test="${form.id>0}">
   				    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Edit Category</h3>
 			</c:when>

  			<c:otherwise>
                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Add Category</h3>
  			</c:otherwise>
			</c:choose>
             
            <sf:form method="post" action="${pageContext.request.contextPath}/addCategory" modelAttribute="form" enctype="multipart/form-data">
            <sf:input type="hidden" id="id" class="form-control form-control-lg" path="id" name="id" value="${form.id}"/>
              
              <div class="row">
                
                <div class="col-md-6 mb-4">
                  <div class="form-outline">   
                  <s:bind path="name">
                  <label class="form-label" for="name">Category Name</label>
                  <sf:input type="text" id="name" class="form-control form-control-lg" path="name" name="name"/>
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </s:bind>      
                  
                  </div>
				 
                </div>
     
                
              </div>


            <div class="row">
                <div class="col-md-6 mb-4">
                  <div class="form-outline"> 
                  <label class="form-label" for="image">Category Image</label>                  
                  <input type="file"  class="form-control form-control-lg" name="image" required="required"/>     
                  <font color="red" style="font-size: 15px"></font>                  
                  </div>			 
                </div>

              </div>

                <c:choose>
  				<c:when test="${form.id>0}">
   				  <div class="mt-4 pt-2">
                <input class="btn btn-primary btn-lg" style="border-color: white; color: black; background-color: white; font-weight: bold;" type="submit" value="Update" />
              </div>   
 			</c:when>

  			<c:otherwise>
             <div class="mt-4 pt-2">
                <input class="btn btn-primary btn-lg" style="border-color: white; color: black; background-color: white; font-weight: bold;" type="submit" value="Add" />
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