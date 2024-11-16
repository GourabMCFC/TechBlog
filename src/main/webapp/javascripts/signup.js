/**
 * Script File For The Sign-Up Page
 */

const form = document.querySelector("form");
const loader = document.querySelector("#loader");
const buttons = document.querySelector("#buttons");
const formElements = {
	user_name: document.querySelector('input[name = "user_name"]'),
	user_email: document.querySelector('input[name = "user_email"]'),
	user_password: document.querySelector('input[name = "user_password"]'),
	user_confirm_password: document.querySelector('input[name = "user_confirm_password"]'),
	user_gender: document.querySelector('input[name = "user_gender"]'),
	user_agreement: document.querySelector('input[name = "user_agreement"]'),
};


const toggleLoader = () => {
	loader.classList.toggle("hide");
	buttons.classList.toggle("hide");
}

const removeFormInvalid = () => {
	formElements["user_name"].classList.remove("is-invalid");
	formElements["user_email"].classList.remove("is-invalid");
	formElements["user_password"].classList.remove("is-invalid");
	formElements["user_confirm_password"].classList.remove("is-invalid");
	formElements["user_gender"].classList.remove("is-invalid");
	formElements["user_agreement"].classList.remove("is-invalid");
}

const addFormInvalid = (form_part) => formElements[form_part].classList.add("is-invalid");

form.addEventListener("submit", (event) => {
	event.preventDefault();
	toggleLoader();
	removeFormInvalid();
	const xhr = new XMLHttpRequest();
	xhr.addEventListener("load", () => {
		toggleLoader();
		const { success, error_message, error_section, error_server, error_dual_marking } = JSON.parse(xhr.responseText);
		if (success) {
			Swal.fire({
				title: "Successfully Registered!",
				text: "Successfully Registered! We're Redirecting To The Login Page",
				icon: "success"
			}).then(() => window.location = "login.jsp");
		} else {
			Swal.fire({
				icon: "error",
				title: error_server ? "Server Error" : "Invalid Credentials...",
				text: error_message,
			}).then(() => {
				if (error_dual_marking) {
					addFormInvalid("user_password");
					addFormInvalid("user_confirm_password");
				} else
					addFormInvalid(error_section);
			});
		}
	});
	xhr.addEventListener("error", () => {
		Swal.fire({
			icon: "error",
			title: "Network Error",
			text: "Network Error Has Been Encountered"
		});
	});

	xhr.open("POST", "signup", true);
	xhr.send(new FormData(form));
})
