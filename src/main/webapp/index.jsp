<%@page import="java.io.File"%>
<%@page import="com.gourab.utils.CategoryHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>TECHBLOG - HOME PAGE</title>
<%@ include file="library.jsp"%>
</head>

<body>
	<%@ include file="header.jsp"%>
	<main>
		<div class="container-fluid m-0 p-0 clipPath">
			<div
				class="row justify-content-center align-items-center g-2 gap-3 bg-dark p-2 text-uppercase py-lg-4">
				<!-- flash alert start -->
				<c:if test="${sessionScope.error != null}">
					<div class="alert alert-danger text-center fs-4" role="alert">
						<i class="fa-solid fa-triangle-exclamation fa-shake"></i> <span
							class="fw-bold"> <c:out
								value="${sessionScope.error.type} : "></c:out></span><span><c:out
								value="${sessionScope.error.message}"></c:out></span>
					</div>
					<c:remove var="error" scope="session" />
				</c:if>
				<!-- flash alert end -->
				<div class="col-10 text-white">
					<h1 class="fw-bold text-decoration-underline fs-1">Welcome to
						techblog</h1>
				</div>
				<div class="col-10 text-white fs-4">
					<p>Welcome to Tech Blog! Explore the latest in technology
						with expert reviews, in-depth analysis. Stay
						ahead of the curve with insights and tips on the innovations
						shaping our world. Join us and unlock the future of tech!</p>

				</div>
				<div class="col-10">
					<div class="row justify-content-start align-items-center g-3"
						style="padding-bottom: 8rem">
						<c:choose>
							<c:when test="${sessionScope.user != null }">
								<div class="col-auto">
									<a href="addblog.jsp" type="button"
										class="btn btn-lg px-4 btn-outline-light text-uppercase border-2">
										<i class="fa-solid fa-plus fa-fade fs-xl"></i> Post
									</a>
								</div>
								<c:if test="${sessionScope.user.isAdmin }">
									<div class="col-auto">
										<a href="category.jsp" type="button"
											class="btn btn-lg px-4 btn-outline-light text-uppercase border-2">
											<i class="fa-solid fa-list"></i> Categories
										</a>
									</div>
								</c:if>
							</c:when>
							<c:otherwise>
								<div class="col-auto">
									<a href="signup.jsp" type="button"
										class="btn btn-lg px-4 btn-outline-light text-uppercase border-2">
										<i class="fa-solid fa-user-plus"></i> Sign-up
									</a>
								</div>
								<div class="col-auto">
									<a href="login.jsp" type="button"
										class="btn btn-lg px-4 btn-outline-light text-uppercase border-2">
										<i class="fa-solid fa-user fa-bounce me-1"></i> Login
									</a>
								</div>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid my-5 w-90">
			<div
				class="row justify-content-center align-items-center gap-lg-0 gap-2 g-lg-3 g-2 p-3 border border-3 border-dark rounded-3">
				<c:set var="categories" value="${CategoryHandler.getCategories() }"></c:set>
				<c:choose>
					<c:when test="${categories != null && !categories.isEmpty() }">
						<c:forEach var="category" items="${categories }">
							<div class="col-lg-4">
								<div class="card border-3 border-dark p-2">
									<img class="category-img card-img-top img-fluid img-thumbnail"
										src='category_pictures<%= File.separator %><c:out value="${category.picture }"></c:out>'
										alt="Title" />
									<div class="card-body text-uppercase">
										<h4 class="card-title fw-bold fs-3 overflow-hidden"
											style="height: 4rem;">
											<c:out value="${category.name }"></c:out>
										</h4>
										<p class="card-text overflow-hidden" style="height: 10rem;">
											<c:out value="${category.description }"></c:out>
										</p>
									</div>
									<a
										href='blogs.jsp?categoryId=<c:out value="${category.id }"></c:out>'
										class="btn btn-dark">EXPLORE MORE...</a>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="col-lg-auto">
							<h4 class="fw-bold fs-3 text-uppercase">no categories
								found...</h4>
						</div>
					</c:otherwise>
				</c:choose>


			</div>
		</div>
	</main>
</body>
</html>
