<%@page import="com.gourab.utils.CategoryHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>TECHBLOG - ADD-POST PAGE</title>
<%@ include file="library.jsp"%>
<script type="text/javascript" src="./javascripts/addPost.js"
	defer="defer"></script>
</head>

<body>
	<%@ include file="header.jsp"%>
	<main>
		<div class="container-fluid m-0 p-0 clipPath">
			<div
				class="row justify-content-center align-items-center g-2 gap-3 bg-dark p-2 text-uppercase py-lg-4">
				<div class="col-lg-8 col-10" style="padding-bottom: 11rem">
					<div class="card text-bg-dark border border-3">
						<div class="card-body">
							<h4 class="card-title fs-2 text-center fw-bold">
								<i class="fa-solid fa-user-plus"></i> Add Post
							</h4>
							<form class="card-text p-3" id="blogForm">
								<div class="mb-3">
									<select class="form-select form-select-lg text-bg-dark"
										name="category_id" id="category_id">
										<option selected="selected" disabled="disabled">---
											Select Category ---</option>
										<c:forEach var="category"
											items="${CategoryHandler.getCategories() }">
											<option value='<c:out value="${category.id }"></c:out>'><c:out
													value="${category.name }"></c:out></option>
										</c:forEach>
									</select>
								</div>
								<div class="form-floating mb-3">
									<input type="text" class="form-control text-bg-dark"
										name="post_title" id="post_title" placeholder="John Doe" /> <label
										for="post_title" class="text-light">Post Title</label>
								</div>
								<div class="form-floating mb-3">
									<textarea class="form-control text-bg-dark"
										placeholder="Leave a comment here" id="post_description"
										name="post_description" style="height: 7rem; resize: none"></textarea>
									<label for="post_description" class="text-light">Enter
										Post Description...</label>
								</div>
								<div class="form-floating mb-3">
									<textarea class="form-control text-bg-dark"
										placeholder="Leave a comment here" id="post_code"
										name="post_code" style="height: 7rem; resize: none"></textarea>
									<label for="post_code" class="text-light">Enter Post
										Code (IF ANY)...</label>
								</div>
								<div class="mb-3">
									<label for="post_picture" class="form-label text-bg-dark">Post
										Picture</label> <input type="file" class="form-control text-bg-dark"
										name="post_picture" id="post_picture"
										placeholder="Select Post Picture" />

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
