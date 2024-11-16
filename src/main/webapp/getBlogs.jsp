<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="com.gourab.utils.BlogHandler"%>
<%@page import="java.io.File"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ include file="library.jsp"%>
<c:set var="blogs"
	value="${BlogHandler.getPostsByCategory(param.categoryId)}"></c:set>
<c:choose>
	<c:when test="${blogs != null && !blogs.isEmpty() }">
		<c:forEach var="blog" items="${blogs}">
			<div class="col-lg-4">
				<div class="card border-3 border-dark p-2">
					<img class="category-img card-img-top img-fluid img-thumbnail"
						src='blog_pictures<%= File.separator %><c:out value="${blog.picture}"></c:out>'
						alt="Title" />
					<div class="card-body text-uppercase">
						<h4 class="card-title fw-bold fs-3 overflow-hidden"
							style="height: 4rem;">
							<c:out value="${blog.title}"></c:out>
						</h4>
						<p class="card-text overflow-hidden" style="height: 10rem;">
							<c:out value="${blog.description}"></c:out>
						</p>
						<div class="d-flex gap-2">
							<a href='blog.jsp?blogId=<c:out value="${blog.id}"></c:out>'
								class="btn btn-dark">Read More...</a>
							<c:if
								test="${sessionScope.user != null && sessionScope.user.id == blog.userId}">
								<a href="editblog.jsp?postId=${blog.id}" class="btn btn-dark"><i
									class="fa-solid fa-pen fa-xl"></i></a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="col">
			<h4 class="fw-bold fs-3 text-center text-uppercase">nothing to
				see here...</h4>
		</div>
	</c:otherwise>
</c:choose>