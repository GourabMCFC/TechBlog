<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<header>
	<!-- place NAVBAR here -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container p-3">
			<a class="navbar-brand text-uppercase fs-2 fw-bold"
				href="./index.jsp"> <i class="fa-solid fa-lightbulb"></i>
				TechBlog
			</a>
			<button class="navbar-toggler d-lg-none" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapsibleNavId"
				aria-controls="collapsibleNavId" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavId">
				<ul
					class="navbar-nav me-auto mt-2 mt-lg-0 text-uppercase fw-semibold">
					<li class="nav-item"><a class="nav-link active"
						href="./index.jsp" aria-current="page"> <i
							class="fa-solid fa-house"></i> Home <span class="visually-hidden">(current)</span></a>
					</li>
				</ul>
				<!-- for login/signout or signout/profile -->

				<ul class="navbar-nav text-uppercase fw-semibold">
					<c:choose>
						<c:when test="${sessionScope.user != null}">
							<li class="nav-item"><a class="nav-link" href="profile.jsp">
									<i class="fa-solid fa-user me-1"></i> <c:out
										value="${sessionScope.user.user_name }"></c:out>
							</a></li>

							<li class="nav-item">
								<form action="logout" method="post">
									<button class="nav-link text-uppercase" type="submit">
										<i class="fa-solid fa-arrow-right-from-bracket me-1"></i>logout
									</button>
								</form>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="signup.jsp">
									<i class="fa-solid fa-user-plus"></i> Sign-Up
							</a></li>
							<li class="nav-item"><a class="nav-link" href="login.jsp">
									<i class="fa-solid fa-user me-1"></i> Login
							</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
</header>