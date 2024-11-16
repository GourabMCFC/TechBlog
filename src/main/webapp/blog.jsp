<%@page import="com.gourab.utils.LikeHandler"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.io.File"%>
<%@page import="com.gourab.utils.BlogHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<c:set var="blog" value="${BlogHandler.getPostById(param.blogId)}"></c:set>
<c:if test="${blog == null}">
	<%
	response.sendError(HttpServletResponse.SC_NOT_FOUND);
	%>
</c:if>
<%!public static String formatTime(Timestamp timestamp) {
		String t = timestamp.toString();
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		LocalDateTime dateTime = LocalDateTime.parse(t, inputFormatter);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a");
		return dateTime.format(outputFormatter);
	}%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>TECHBLOG - ${fn:toUpperCase(blog.title)}</title>
<%@ include file="library.jsp"%>
<script type="text/javascript" src="./javascripts/blog.js" defer="defer"></script>
<c:if test="${sessionScope.user != null}">
	<script type="text/javascript" src="./javascripts/like.js"
		defer="defer"></script>
	<c:set var="isLiked"
		value="${LikeHandler.checkIfPostLikedByUser(blog.id, sessionScope.user.id)}"></c:set>
</c:if>
</head>
<body>
	<%@ include file="header.jsp"%>
	<main>
		<div class="container-fluid m-0 p-0 clipPath">
			<div
				class="row justify-content-center align-items-center g-2 gap-3 bg-dark p-2 text-uppercase py-lg-4">
				<div class="col text-white">
					<h1 class="fw-bold text-center text-decoration-underline fs-1 pb-4">${fn:toUpperCase(blog.title)}</h1>
				</div>
			</div>
		</div>
		<div class="container-fluid my-5 w-90">
			<div
				class="row justify-content-center align-items-center gap-lg-0 gap-2 g-lg-3 g-2 p-lg-3 border border-3 border-dark rounded-3">
				<div class="col-10 text-center">
					<img class="blog-img img-fluid img-thumbnail"
						src='blog_pictures<%= File.separator %><c:out value="${blog.picture}"></c:out>'
						alt="${blog.title}" />
				</div>

				<div class="col-10 mb-3">
					<h1 style="display: inline;"
						class="ms-2 fw-bolder border-bottom border-dark border-3">${fn:toUpperCase(blog.title)}</h1>
				</div>
				<div class="col-10">
					<p class="ms-2 fs-3 fw-medium">${(blog.description)}</p>
				</div>
				<c:if test="${blog.code != null && !blog.code.isEmpty()}">
					<div class="col-10">
						<p class="ms-2 fs-3 text-uppercase text-decoration-underline">code</p>
						<pre class="ms-2 p-4 text-bg-dark rounded fs-5 fw-light">${blog.code}</pre>
					</div>
				</c:if>
				<hr />
				<div class="row justify-content-center">
					<div class="col-4 fs-5 fst-italic">
						Posted By <span class="text-decoration-underline">${blog.username}</span>
					</div>
					<c:set var="createdAt" value="${blog.createdAt}"></c:set>
					<c:set var="updatedAt" value="${blog.updatedAt}"></c:set>
					<div class="col-4 text-end fs-5 fst-italic">
						Created At:
						<%=formatTime((Timestamp) pageContext.getAttribute("createdAt"))%></div>
				</div>
				<div class="row justify-content-center">
					<div class="col-4 fs-5 fst-italic">
						Category: <a href="blogs.jsp?categoryId=${blog.categoryId}"
							class="link-dark link-underline-dark">${blog.categoryname}</a>
					</div>

					<div class="col-4 text-end fs-5 fst-italic">
						Updated At:
						<%=formatTime((Timestamp) pageContext.getAttribute("updatedAt"))%></div>
				</div>
				<div class="row justify-content-center mt-3">
					<div class="col-8 fst-italic">
						<form
							class='btn btn-outline-dark fs-4 px-3 py-1 <c:if test="${isLiked}">active</c:if>'
							id="likeBtn">
							<input type="hidden" name="postId" value="${blog.id}" /> <input
								type="hidden" name="userId" value="${sessionScope.user.id}" />
							<i class="fa-solid fa-heart fa-xl"></i><span>${blog.likes}</span>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
