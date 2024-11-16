/**
 * JS File For Profile Page
 */
const editBtn = document.querySelector("#editBtn");
const deleteBtn = document.querySelector("#deleteBtn");
const editBackBtn = document.querySelector("#editBackBtn");
const changePwdBtn = document.querySelector("#changePwdBtn");
const pwdBackBtn = document.querySelector("#pwdBackBtn");
const editLoader = document.querySelector("#editLoader");
const editButtons = document.querySelector("#editButtons");
const pwdLoader = document.querySelector("#pwdLoader");
const pwdButtons = document.querySelector("#pwdButtons");
const detailsArea = document.querySelector("#detailsArea");
const editArea = document.querySelector("#editArea");
const passwordArea = document.querySelector("#passwordArea");
const editForm = document.querySelector("#editArea form");
const pwdForm = document.querySelector("#passwordArea form");
const editFormFields = {
	user_name: document.querySelector('input[name = "user_name"]'),
	user_gender: document.querySelector('input[name = "user_gender"]'),
	user_profile: document.querySelector('input[name = "user_profile"]')
};
const pwdFormFields = {
	user_password_old: document.querySelector('input[name = "user_password_old"]'),
	user_password: document.querySelector('input[name = "user_password"]'),
	user_confirm_password: document.querySelector('input[name = "user_confirm_password"]'),
};
//toggle the edit area and details
const toggleArea = () => {
	detailsArea.classList.toggle("hide");
	editArea.classList.toggle("hide");
}
const togglePassword = () => {
	detailsArea.classList.toggle("hide");
	passwordArea.classList.toggle("hide");
}
// Show / Hide Areas
editBtn.addEventListener("click", () => toggleArea());
editBackBtn.addEventListener("click", () => toggleArea());
changePwdBtn.addEventListener("click", () => togglePassword());
pwdBackBtn.addEventListener("click", () => togglePassword());
//Toggle Edit Validation
const addEditValidation = (error_section) => editFormFields[error_section].classList.add("is-invalid");
const removeEditValidation = () => {
	editFormFields["user_name"].classList.remove("is-invalid");
	editFormFields["user_gender"].classList.remove("is-invalid");
	editFormFields["user_profile"].classList.remove("is-invalid");
}
//Toggle Pwd Validation
const addPwdValidation = (error_section) => pwdFormFields[error_section].classList.add("is-invalid");
const removePwdValidation = () => {
	pwdFormFields["user_password_old"].classList.remove("is-invalid");
	pwdFormFields["user_password"].classList.remove("is-invalid");
	pwdFormFields["user_confirm_password"].classList.remove("is-invalid");
}
// Edit Functionality
const toggleEditLoader = () => {
	editLoader.classList.toggle("hide");
	editButtons.classList.toggle("hide");
}
editForm.addEventListener("submit", (event) => {
	event.preventDefault();
	toggleEditLoader();
	const xhr = new XMLHttpRequest();
	xhr.addEventListener("load", () => {
		toggleEditLoader();
		removeEditValidation();
		const { success, error_message, error_section } = JSON.parse(xhr.responseText);
		console.log(success);
		if (success) {
			Swal.fire({
				title: "Updated",
				text: "Profile Updated Sucessfully!!!",
				icon: "success"
			}).then(() => window.location = success);
		}
		else {
			Swal.fire({
				icon: "error",
				title: "Oops...",
				text: error_message,
			}).then(() => addEditValidation(error_section));
		}
	});
	xhr.addEventListener("error", () => {
		toggleEditLoader();
		Swal.fire({
			icon: "error",
			title: "Network Error",
			text: "Network Error Has Been Encountered"
		});
	});
	xhr.open("POST", "editInfo", true);
	xhr.send(new FormData(editForm));
});
//Change Password Functionality
const togglePwdLoader = () => {
	pwdLoader.classList.toggle("hide");
	pwdButtons.classList.toggle("hide");
}
pwdForm.addEventListener("submit", (event) => {
	event.preventDefault();
	togglePwdLoader();
	const xhr = new XMLHttpRequest();
	xhr.addEventListener("load", () => {
		togglePwdLoader();
		removePwdValidation();
		const { success, error_message, error_section, error_dual_marking } = JSON.parse(xhr.responseText);
		console.log(success);
		if (success) {
			Swal.fire({
				title: "Updated",
				text: "Password Updated Sucessfully!!!",
				icon: "success"
			}).then(() => window.location = success);
		}
		else {
			Swal.fire({
				icon: "error",
				title: "Oops...",
				text: error_message,
			}).then(() => {
				if (error_dual_marking) {
					addPwdValidation("user_password");
					addPwdValidation("user_confirm_password");
				} else {
					addPwdValidation(error_section);
				}
			});
		}
	});
	xhr.addEventListener("error", () => {
		togglePwdLoader();
		Swal.fire({
			icon: "error",
			title: "Network Error",
			text: "Network Error Has Been Encountered"
		});
	});
	xhr.open("POST", "changepwd", true);
	xhr.send(new FormData(pwdForm));
});

//Delete Functionality
deleteBtn.addEventListener("click", () => {
	Swal.fire({
		title: "Are you sure?",
		text: "You won't be able to revert this!",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "Yes, delete it!"
	}).then((result) => {
		if (result.isConfirmed) {
			const xhr = new XMLHttpRequest();
			xhr.addEventListener("load", () => {
				const { success, error_message } = JSON.parse(xhr.responseText);
				if (success) {
					Swal.fire({
						title: "Deleted!",
						text: "Your account has been deleted...",
						icon: "success"
					}).then(() => window.location = success);
				} else {
					Swal.fire({
						title: "Error",
						text: error_message,
						icon: "error"
					});
				}
			});
			xhr.open("POST", "deleteaccount", true);
			xhr.send();
		}
	});
})
