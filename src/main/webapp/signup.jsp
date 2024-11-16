<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>TECHBLOG - SIGN-UP PAGE</title>
<%@ include file="library.jsp"%>
<script type="text/javascript" src="./javascripts/signup.js"
	defer="defer"></script>
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
							<h4 class="card-title fs-2 text-center fw-bold">
								<i class="fa-solid fa-user-plus"></i> SignUp
							</h4>
							<form class="card-text p-3">
								<div class="form-floating mb-3">
									<input type="text" class="form-control text-bg-dark"
										name="user_name" id="user_name" placeholder="John Doe" /> <label
										for="user_name" class="text-light">Name</label>
								</div>
								<div class="form-floating mb-3">
									<input type="email" class="form-control text-bg-dark"
										name="user_email" id="user_email"
										placeholder="johndoe@domain.com" /> <label for="user_email"
										class="text-light">Email</label>
								</div>
								<div class="form-floating mb-3">
									<input type="password" class="form-control text-bg-dark"
										name="user_password" id="user_password"
										placeholder="Enter Password" /> <label class="text-light"
										for="user_password">Password</label>
								</div>
								<div class="form-floating mb-3">
									<input type="password" class="form-control text-bg-dark"
										name="user_confirm_password" id="user_confirm_password"
										placeholder="Enter Confirm Password" /> <label
										class="text-light" for="user_password">Confirm
										Password</label>
								</div>
								<div class="form mb-3">
									<span class="me-5">Select Gender: </span>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio"
											name="user_gender" id="male" value="male" /> <label
											class="form-check-label" for="male">Male</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio"
											name="user_gender" id="female" value="female" /> <label
											class="form-check-label" for="female">Female</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio"
											name="user_gender" id="others" value="others" /> <label
											class="form-check-label" for="others">Others</label>
									</div>
								</div>
								<div class="form-floating mb-3">
									<textarea class="form-control text-bg-dark"
										placeholder="Leave a comment here" id="user_about"
										name="user_about" style="height: 7rem; resize: none"></textarea>
									<label for="user_about" class="text-light">Enter About
										Yourself...</label>
								</div>
								<div class="form-check form-switch mb-3">
									<input name="user_agreement" class="form-check-input"
										type="checkbox" role="switch" id="user_agreement" /> <label
										class="form-check-label" for="user_agreement">Agree
										Terms &amp; Condition</label>
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
