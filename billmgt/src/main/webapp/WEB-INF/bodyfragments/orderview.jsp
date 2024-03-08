<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<section class="vh-100 gradient-custom ">
	<div class="container py-5 h-100">
		<div class="row justify-content-center align-items-center h-100">
			<div class="col-12 col-lg-9 col-xl-7">
				<div class="card shadow-2-strong card-registration"
					style="border-radius: 15px; margin-bottom: 30px; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); background-color: black !important; color: white;">
					<div class="card-body p-4 p-md-5">
						<%@include file="businessMessage.jsp"%>

						<c:choose>
							<c:when test="${form.id>0}">
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Edit Order</h3>
							</c:when>

							<c:otherwise>
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Add Order</h3>
							</c:otherwise>
						</c:choose>

						<sf:form method="post"
							action="${pageContext.request.contextPath}/addOrder"
							modelAttribute="form" enctype="multipart/form-data">
							<sf:input type="hidden" id="id"
								class="form-control form-control-lg" path="id" name="id"
								value="${form.id}" />
								
						<sf:input type="hidden" id="userId"
								class="form-control form-control-lg" path="userId" name="userId"
								value="${user.id}" />

							<div class="row">
								<div class="col-md-6 mb-4">
									<div class="form-outline">
										<s:bind path="firstName">
											<label class="form-label" for="firstName">First Name</label>
											<sf:input type="text" id="firstName"
												class="form-control form-control-lg" path="firstName"
												name="firstName" value="${user.firstName}" readonly="true"/>
											<font color="red" style="font-size: 15px"><sf:errors
													path="${status.expression}" /></font>
										</s:bind>


									</div>

								</div>
								<div class="col-md-6 mb-4">

									<div class="form-outline">
										<s:bind path="lastName">
											<label class="form-label" for="lastName">Last Name</label>
											<sf:input type="text" id="lastName"
												class="form-control form-control-lg" path="lastName" value="${user.lastName}" readonly="true" />
											<font color="red" style="font-size: 15px"><sf:errors
													path="${status.expression}" /></font>
										</s:bind>
									</div>

								</div>

							</div>

							<div class="row">
								<div class="col-md-6 mb-4 pb-2">
									<s:bind path="email">
										<div class="form-outline">
											<label class="form-label" for="emailAddress">Email</label>
											<sf:input type="email" id="emailAddress"
												class="form-control form-control-lg" path="email"
												value="${user.email}" readonly="true" />
											<font color="red" style="font-size: 15px"><sf:errors
													path="${status.expression}" /></font>
										</div>
									</s:bind>

								</div>
								<div class="col-md-6 mb-4 pb-2">
									<s:bind path="phoneNumber">
										<div class="form-outline">
											<label class="form-label" for="phoneNumber">Phone
												Number</label>
											<sf:input type="tel" id="phoneNumber"
												class="form-control form-control-lg" path="phoneNumber" value="${user.phoneNumber}" readonly="true"  />
											<font color="red" style="font-size: 15px"><sf:errors
													path="${status.expression}" /></font>
										</div>
									</s:bind>
								</div>

							</div>
							
							
							<div class="row">
								<div class="col-md-6 mb-4">
									<div class="form-outline">
										<sf:select class="select form-control-lg" path="productId">
											<option selected="true" disabled="disabled">Select
												Product</option>
											<c:forEach var="product" items="${productList}">
												<sf:option value="${product.id}" label="${product.name}" />
											</c:forEach>
										</sf:select>

									</div>

								</div>
							</div>

							<div class="row">
								<div class="col-md-6 mb-4">
									<div class="form-outline">
										<s:bind path="numberOfUnit">
											<label class="form-label" for="name">numberOfUnit</label>
											<sf:input type="text" id="numberOfUnit"
												class="form-control form-control-lg" path="numberOfUnit"
												name="numberOfUnit" />
											<font color="red" style="font-size: 15px"><sf:errors
													path="${status.expression}" /></font>
										</s:bind>

									</div>

								</div>
							</div>


		
							<c:choose>
								<c:when test="${form.id>0}">
									<div class="mt-4 pt-2">
										<input class="btn btn-primary btn-lg"
											style="border-color: white; color: black; background-color: white; font-weight: bold;"
											type="submit" value="Update" />
									</div>
								</c:when>

								<c:otherwise>
									<div class="mt-4 pt-2">
										<input class="btn btn-primary btn-lg"
											style="border-color: white; color: black; background-color: white; font-weight: bold;"
											type="submit" value="Order & Pay" />
									</div>
								</c:otherwise>
							</c:choose>

						</sf:form>
					</div>

				</div>
			</div>
		</div>
	</div>
	</div>
</section>