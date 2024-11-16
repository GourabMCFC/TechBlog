/**
 * Script File For The Add Category Page
 */

const form = document.querySelector("main form");
const loader = document.querySelector("#loader");
const buttons = document.querySelector("#buttons");
const formElements = {
	cat_name: document.querySelector('input[name = "cat_name"]'),
	cat_profile: document.querySelector('input[name = "cat_profile"]'),
};

const toggleLoader = () => {
	loader.classList.toggle("hide");
	buttons.classList.toggle("hide");
}

const removeFormInvalid = () => {
	formElements["cat_name"].classList.remove("is-invalid");
	formElements["cat_profile"].classList.remove("is-invalid");
}

const addFormInvalid = (form_part) => formElements[form_part].classList.add("is-invalid");


form.addEventListener("submit", (event) => {
	event.preventDefault();
	toggleLoader();
	removeFormInvalid();
	const xhr = new XMLHttpRequest();
	xhr.addEventListener("load", () => {
		toggleLoader();
		console.log(xhr.responseText);
		const { success, error_message, error_section, error_server } = JSON.parse(xhr.responseText);
		if (success) {
			Swal.fire({
				title: "Successfully Registered!",
				text: "Successfully Registered! We're Redirecting To The Category Page",
				icon: "success"
			}).then(() => window.location = success);
		} else {
			Swal.fire({
				icon: "error",
				title: error_server ? "Server Error" : "Invalid Input...",
				text: error_message,
			}).then(() => addFormInvalid(error_section));
		}
	});
	xhr.addEventListener("error", () => {
		Swal.fire({
			icon: "error",
			title: "Network Error",
			text: "Network Error Has Been Encountered"
		});
	});

	xhr.open("POST", "editcategory", true);
	xhr.send(new FormData(form));
})
