<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>TECHBLOG - SERVER ERROR PAGE</title>
<%@ include file="library.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true"%>
</head>

<body>
	<%@ include file="header.jsp"%>
	<main>
		<div class="container-fluid m-0 p-0 clipPath">
			<div
				class="row justify-content-center align-items-center g-2 gap-3 bg-dark p-2 text-uppercase py-lg-4">
				<div class="col-lg-8 col-10" style="padding-bottom: 12rem">
					<div class="card text-bg-dark border border-3">
						<div class="card-body">
							<h4 class="card-title fs-2 text-center fw-bold text-danger">
								<i class="fa-solid fa-triangle-exclamation fa-fade fs-2"></i>
								Server Error!!!
							</h4>
							<div class="card-text p-3 text-center fs-1" data-bs-theme="dark">
								<p><%=exception%></p>
								<a type="reset"
									class="btn btn-outline-light border-2 btn-lg ms-2 text-uppercase"
									href="index.jsp"> Go To Home Page </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
