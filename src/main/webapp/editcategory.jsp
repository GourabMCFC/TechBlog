<%@page import="com.gourab.utils.CategoryHandler"%>
<%@page import="com.gourab.entity.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Category category = CategoryHandler.getCategory(Integer.parseInt(request.getParameter("id")));
if (category == null)
	response.sendError(HttpServletResponse.SC_NOT_FOUND);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>TECHBLOG - EDIT-CATEGORY PAGE</title>
<%@ include file="library.jsp"%>
<script type="text/javascript" src="./javascripts/editCategory.js"
	defer="defer"></script>
</head>

<body>
	<%@ include file="header.jsp"%>
	<main>
		<div class="container-fluid m-0 p-0 clipPath">
			<div
				class="row justify-content-center align-items-center g-2 gap-3 bg-dark p-2 text-uppercase py-lg-4">
				<div class="col-lg-8 col-10" style="padding-bottom: 8rem">
					<div class="card text-bg-dark border border-3">
						<div class="card-body">
							<h4 class="card-title fs-2 text-center fw-bold">
								<i class="fa-solid fa-user-plus"></i> Edit Category
							</h4>
							<form class="card-text p-3">

								<input type="hidden" class="form-control text-bg-dark"
									name="cat_id" id="cat_id" value="<%=category.getId()%>" />

								<div class="form-floating mb-3">
									<input type="text" class="form-control text-bg-dark"
										name="cat_name" id="cat_name" placeholder="John Doe"
										value="<%=category.getName()%>" /> <label for="cat_name"
										class="text-light">Category Name</label>
								</div>
								<div class="form-floating mb-3">
									<textarea class="form-control text-bg-dark"
										placeholder="Leave a comment here" id="cat_about"
										name="cat_about" style="height: 7rem; resize: none"><%=category.getDescription()%></textarea>
									<label for="cat_about" class="text-light">Category
										Description...</label>
								</div>
								<div class="mb-3">
									<label for="cat_profile" class="form-label text-bg-dark">Profile
										Picture</label> <input type="file" class="form-control text-bg-dark"
										name="cat_profile" id="cat_profile"
										placeholder="Select Profile Picture" />

								</div>
								<div id="loader" class="hide">
									<button
										class="btn btn-outline-light border-2 btn-lg text-uppercase"
										type="button" disabled>
										<span class="spinner-border spinner-border-sm"
											aria-hidden="true"></span> <span role="status">Loading...</span>
									</button>
								</div>
								<div id="buttons">
									<button type="submit"
										class="btn btn-outline-light border-2 btn-lg text-uppercase">
										Submit</button>
									<button type="reset"
										class="btn btn-outline-light border-2 btn-lg ms-2 text-uppercase">
										Reset</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
