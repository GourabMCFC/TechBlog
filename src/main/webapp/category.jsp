<%@page import="java.io.File"%>
<%@page import="com.gourab.utils.CategoryHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>TECHBLOG - CATEGORY PAGE</title>
<%@ include file="library.jsp"%>
<script type="text/javascript" src="./javascripts/deleteOption.js"
	defer="defer"></script>
</head>

<body>
	<%@ include file="header.jsp"%>
	<main>
		<div class="container-fluid m-0 p-0 clipPath">
			<div
				class="row justify-content-center align-items-center g-2 gap-3 bg-dark p-2 text-uppercase py-lg-4">
				<div class="col text-white">
					<h1 class="fw-bold text-center text-decoration-underline fs-1 pb-4">Categories</h1>
				</div>
			</div>
		</div>
		<div class="container-fluid my-5 w-90">
			<div
				class="row justify-content-center align-items-center gap-lg-0 gap-2 g-lg-3 g-2 p-3 border border-3 border-dark rounded-3">
				<div class="col-12 d-flex justify-content-end pe-5">
					<a href="addcategory.jsp" type="button"
						class="btn btn-lg px-4 btn-dark text-uppercase border-2"><i
						class="fa-solid fa-plus fa-xl"></i> Add Category </a>
				</div>
				<c:set var="categories" value="${CategoryHandler.getCategories() }"></c:set>
				<c:choose>
					<c:when test="${categories != null && !categories.isEmpty() }">
						<c:forEach var="category" items="${categories}">
							<div class="col-lg-4">
								<div class="card border-3 border-dark p-2">
									<img class="category-img card-img-top img-fluid img-thumbnail"
										src="category_pictures<%= File.separator %>${category.picture}"
										alt="${category.name}" />
									<div class="card-body text-uppercase">
										<h4 class="card-title fw-bold fs-3 overflow-hidden"
											style="height: 4rem;">${category.name}</h4>
										<p class="card-text overflow-hidden" style="height: 10rem;">${category.description}</p>
										<div class="d-flex gap-2">
											<a href="editcategory.jsp?id=${category.id}"
												class="btn btn-dark"><i class="fa-solid fa-pen me-1"></i>Edit</a>
											<form class="col-auto deleteData">
												<input type="hidden" class="form-control" name="cat_id"
													id="cat_id" value="${category.id}" /> <input type="hidden"
													class="form-control" name="cat_pic" id="cat_pic"
													value="${category.picture}" />
												<button class="text-uppercase btn btn-danger" type="submit">
													<i class="fa-regular fa-trash-can me-1"></i>delete
												</button>
											</form>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<h4 class="fw-bold fs-3 text-uppercase text-center">no
							categories found...</h4>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</main>
</body>
</html>
