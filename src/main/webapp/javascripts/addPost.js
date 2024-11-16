/**
 * JS File For Add post page
 */

const blogForm = document.querySelector("#blogForm");
const loader = document.querySelector("#loader");
const buttons = document.querySelector("#buttons");
const formElements = {
	category_id: document.querySelector('select[name = "category_id"]'),
	post_title: document.querySelector('input[name = "post_title"]'),
	post_description: document.querySelector('textarea[name = "post_description"]'),
	post_code: document.querySelector('textarea[name = "post_code"]'),
	post_picture: document.querySelector('input[name = "post_picture"]'),
};


const toggleLoader = () => {
	loader.classList.toggle("hide");
	buttons.classList.toggle("hide");
}

const removeFormInvalid = () => {
	formElements["category_id"].classList.remove("is-invalid");
	formElements["post_title"].classList.remove("is-invalid");
	formElements["post_description"].classList.remove("is-invalid");
	formElements["post_code"].classList.remove("is-invalid");
	formElements["post_picture"].classList.remove("is-invalid");
}

const addFormInvalid = (form_part) => formElements[form_part].classList.add("is-invalid");


blogForm.addEventListener("submit", (event) => {
	event.preventDefault();
	toggleLoader();
	removeFormInvalid();
	const xhr = new XMLHttpRequest();
	xhr.addEventListener("load", () => {
		toggleLoader();
		const { success, error_message, error_section, error_server } = JSON.parse(xhr.responseText);
		if (success) {
			Swal.fire({
				title: "Successfully Created!",
				text: "Successfully Registered! We're Redirecting To The Blogs Page",
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

	xhr.open("POST", "addpost", true);
	xhr.send(new FormData(blogForm));
})
