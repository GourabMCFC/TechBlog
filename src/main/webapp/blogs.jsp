<%@page import="com.gourab.utils.LikeHandler"%>
<%@page import="com.gourab.utils.CategoryHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>TECHBLOG - BLOGS PAGE</title>
<%@ include file="library.jsp"%>
<script type="text/javascript" src="./javascripts/blogs.js"
	defer="defer"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<main>
		<div class="container-fluid m-0 p-0 clipPath">
			<div
				class="row justify-content-center align-items-center g-2 gap-3 bg-dark p-2 text-uppercase py-lg-4">
				<div class="col text-white">
					<h1 class="fw-bold text-center text-decoration-underline fs-1 pb-4">Blogs</h1>
				</div>
			</div>
		</div>
		<div class="container-fluid my-5 w-90">
			<div
				class="row justify-content-center align-items-center gap-lg-0 gap-2 g-lg-3 g-2 p-3 border border-3 border-dark rounded-3">
				<c:if test="${sessionScope.user != null}">
					<div class="col-12 d-flex justify-content-end pe-5">
						<a href="addblog.jsp" type="button"
							class="btn btn-lg px-4 btn-dark text-uppercase border-2"> <i
							class="fa-solid fa-plus fa-xl"></i> Add Blog
						</a>
					</div>
				</c:if>
				<c:set var="categories" value="${CategoryHandler.getCategories()}"></c:set>
				<c:choose>
					<c:when test="${categories != null && !categories.isEmpty()}">
						<div class="col-lg-3 align-self-start">
							<c:forEach var="category" items="${categories }">
								<div class="list-group">
									<a
										href='getBlogs.jsp?categoryId=<c:out value="${category.id}"></c:out>'
										class="fw-bold fs-5 list-group-item list-group-item-action list-group-item-dark p-3"
										id=<c:out
											value="${category.id}"></c:out>><c:out
											value="${fn:toUpperCase(category.name)}"></c:out></a>
								</div>
							</c:forEach>
						</div>
						<div class="col-lg-9">
							<div class="text-center fa-beat-fade" id="loader">
								<h1>
									<i class="fa-solid fa-hourglass-start fa-spin fa-2xl"></i>
								</h1>
								<h1 class="fs-2 text-uppercase mt-4">loading...</h1>
							</div>
							<div class="row hide g-2" id="blogs"></div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-lg-auto">
							<h4 class="fw-bold fs-3 text-center text-uppercase">nothing
								to see here...</h4>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</main>
</body>
</html>
