/**
 * JS File For Login Page
 */
const form = document.querySelector("form");
const loader = document.querySelector("#loader");
const buttons = document.querySelector("#buttons");
const formElements = {
	user_email: document.querySelector('input[name = "user_email"]'),
	user_password: document.querySelector('input[name = "user_password"]')
};

const toggleLoader = () => {
	loader.classList.toggle("hide");
	buttons.classList.toggle("hide");
}
const removeFormInvalid = () => {
	formElements["user_email"].classList.remove("is-invalid");
	formElements["user_password"].classList.remove("is-invalid");
}

const addFormInvalid = (error_section) => formElements[error_section].classList.add("is-invalid");


form.addEventListener("submit", (event) => {
	event.preventDefault();
	toggleLoader();
	removeFormInvalid();
	const xhr = new XMLHttpRequest();
	xhr.addEventListener("load", () => {
		toggleLoader();
		const { success, error_message, error_section, error_dual_marking } = JSON.parse(xhr.responseText);
		if (success) {
			window.location = success;
		} else {
			Swal.fire({
				icon: "error",
				title: "Invalid Credentials...",
				text: error_message,
			}).then(() => {
				if (error_dual_marking) {
					addFormInvalid("user_email");
					addFormInvalid("user_password");
				} else {
					addFormInvalid(error_section);
				}
			});
		}
	});
	xhr.addEventListener("error", () => {
		toggleLoader();
		Swal.fire({
			icon: "error",
			title: "Network Error",
			text: "Network Error Has Been Encountered"
		});
	});
	xhr.open("POST", "login", true);
	xhr.send(new FormData(form));
})