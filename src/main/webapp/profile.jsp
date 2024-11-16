<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"
	import="com.gourab.entity.Gender"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>TECHBLOG - PROFILE PAGE</title>
<%@ include file="library.jsp"%>
<script type="text/javascript" src="./javascripts/profile.js"
	defer="defer"></script>
</head>

<body>
	<%@ include file="header.jsp"%>
	<main>

		<div class="container-fluid m-0 p-0 clipPath">
			<div
				class="row justify-content-center align-items-center g-2 gap-3 bg-dark p-2 py-lg-4">
				<div class="col-lg-8 col-10" style="padding-bottom: 15rem">
					<!-- table start -->
					<div id="detailsArea">
						<div
							class="table-responsive border border-3 p-3 rounded text-bg-dark d-flex justify-content-center align-items-center flex-column">
							<div class="text-uppercase text-center">
								<img
									src='profile_pictures<%= File.separator %><c:out value="${sessionScope.user.user_picture}"></c:out>'
									alt="profile pic" class="profile-img" />
								<h1>
									<c:out value="${sessionScope.user.user_name}"></c:out>
								</h1>
							</div>
							<table
								class="mb-4 table-responsive w-50 table-dark align-middle fs-4 table-row-border mt-3 text-center">
								<tbody>
									<tr>
										<th scope="row" class="text-uppercase">id</th>
										<td><c:out value="${sessionScope.user.id }"></c:out></td>
									</tr>
									<tr>
										<th scope="row" class="text-uppercase">name</th>
										<td><c:out value="${sessionScope.user.user_name }"></c:out></td>
									</tr>
									<tr>
										<th scope="row" class="text-uppercase">Email</th>
										<td><c:out value="${sessionScope.user.user_email }"></c:out></td>
									</tr>
									<tr>
										<th scope="row" class="text-uppercase">Gender</th>
										<td><c:out value="${sessionScope.user.user_gender }"></c:out></td>
									</tr>
									<tr>
										<th scope="row" class="text-uppercase">About</th>
										<td><c:out value="${sessionScope.user.user_about }"></c:out></td>
									</tr>
									<tr>
										<th scope="row" class="text-uppercase">Created</th>
										<td><c:out
												value="${sessionScope.user.createdAt.toString() }"></c:out></td>
									</tr>
								</tbody>
							</table>
							<div
								class="d-flex gap-3 justify-content-center align-items-stretch flex-column flex-lg-row mt-2 my-width-50">
								<button id="deleteBtn"
									class="btn btn-danger border-2 btn-lg text-uppercase fw-bold"
									role="button">Delete</button>
								<button id="editBtn"
									class="btn btn-outline-light border-2 btn-lg text-uppercase"
									role="button">Edit</button>
								<button id="changePwdBtn"
									class="btn btn-outline-light border-2 btn-lg text-uppercase"
									role="button">Change Password</button>
							</div>
						</div>
					</div>
					<!-- table end -->

					<!-- edit form start -->
					<div class="card text-bg-dark border border-3 hide" id="editArea">
						<div class="card-body">
							<h4 class="card-title fs-2 text-center fw-bold">
								<i class="fa-solid fa-user-plus"></i> Edit
							</h4>
							<form class="card-text p-3 text-uppercase">
								<div class="form-floating mb-3">
									<input type="text" class="form-control text-bg-dark"
										name="user_name" id="user_name" placeholder="John Doe"
										value='<c:out value="${sessionScope.user.user_name }"></c:out>' />
									<label for="user_name" class="text-light">Name</label>
								</div>
								<div class="form-floating mb-3">
									<textarea class="form-control text-bg-dark"
										placeholder="Leave a comment here" id="user_about"
										name="user_about" style="height: 7rem; resize: none"><c:out
											value="${sessionScope.user.user_about }"></c:out></textarea>
									<label for="user_about" class="text-light">Enter About
										Yourself...</label>
								</div>
								<div class="form mb-3">
									<span class="me-5">Select Gender: </span>
									<div class="form-check form-check-inline">
										<input class="form-check-input text-bg-dark" type="radio"
											name="user_gender" id="male" value="male"
											<c:if test="${sessionScope.user.user_gender == Gender.MALE }">checked</c:if> /><label
											class="form-check-label" for="male">Male</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input text-bg-dark" type="radio"
											name="user_gender" id="female" value="female"
											<c:if test="${sessionScope.user.user_gender == Gender.FEMALE }">checked</c:if> />
										<label class="form-check-label" for="female">Female</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input text-bg-dark" type="radio"
											name="user_gender" id="others" value="others"
											<c:if test="${sessionScope.user.user_gender == Gender.OTHERS }">checked</c:if> />
										<label class="form-check-label" for="others">Others</label>
									</div>
								</div>
								<div class="mb-3">
									<label for="user_profile" class="form-label text-bg-dark">Profile
										Picture</label> <input type="file" class="form-control text-bg-dark"
										name="user_profile" id="user_profile"
										placeholder="Select Profile Picture" />

								</div>
								<div id="editLoader" class="hide">
									<button
										class="btn btn-outline-light border-2 btn-lg text-uppercase"
										type="button" disabled>
										<span class="spinner-border spinner-border-sm"
											aria-hidden="true"></span> <span role="status">Loading...</span>
									</button>
								</div>
								<div id="editButtons">
									<button type="submit"
										class="btn btn-outline-light border-2 btn-lg text-uppercase">
										Edit</button>
									<button type="button" id="editBackBtn"
										class="btn btn-outline-light border-2 btn-lg ms-2 text-uppercase">
										Back</button>
								</div>
							</form>
						</div>
					</div>
					<!-- edit form end -->
					<!-- change password form -->
					<div class="card text-bg-dark border border-3 hide"
						id="passwordArea">
						<div class="card-body">
							<h4 class="card-title fs-2 text-center fw-bold">
								<i class="fa-solid fa-user-plus"></i> Edit
							</h4>
							<form class="card-text p-3 text-uppercase">
								<div class="form-floating mb-3">
									<input type="password" class="form-control text-bg-dark"
										name="user_password_old" id="user_password_old"
										placeholder="Enter Old Password" /> <label
										for="user_password_old" class="text-light">Old
										Password</label>
								</div>
								<div class="form-floating mb-3">
									<input type="password" class="form-control text-bg-dark"
										name="user_password" id="user_password"
										placeholder="Enter Password" /> <label for="user_password"
										class="text-light">New Password</label>
								</div>
								<div class="form-floating mb-3">
									<input type="password" class="form-control text-bg-dark"
										name="user_confirm_password" id="user_confirm_password"
										placeholder="Enter Confirm Password" /> <label
										for="user_confirm_password" class="text-light">Confirm
										New Password</label>
								</div>
								<div id="pwdLoader" class="hide">
									<button
										class="btn btn-outline-light border-2 btn-lg text-uppercase"
										type="button" disabled>
										<span class="spinner-border spinner-border-sm"
											aria-hidden="true"></span> <span role="status">Loading...</span>
									</button>
								</div>
								<div id="pwdButtons">
									<button type="submit"
										class="btn btn-outline-light border-2 btn-lg text-uppercase">
										Edit</button>
									<button type="button" id="pwdBackBtn"
										class="btn btn-outline-light border-2 btn-lg ms-2 text-uppercase">
										Back</button>
								</div>
							</form>
						</div>
					</div>
					<!-- change password end -->
				</div>
			</div>
		</div>
	</main>
</body>
</html>
