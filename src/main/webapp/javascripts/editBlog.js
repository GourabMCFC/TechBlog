/**
 * 
 */
const deleteBtn = document.querySelector("#deleteBtn");
const blogForm = document.querySelector("#blogForm");
const loader = document.querySelector("#loader");
const buttons = document.querySelector("#buttons");
const toggleLoader = () => {
	loader.classList.toggle("hide");
	buttons.classList.toggle("hide");
}
const formElements = {
	category_id: document.querySelector('select[name = "category_id"]'),
	post_title: document.querySelector('input[name = "post_title"]'),
	post_description: document.querySelector('textarea[name = "post_description"]'),
	post_code: document.querySelector('textarea[name = "post_code"]'),
	post_picture: document.querySelector('input[name = "post_picture"]'),
};
const removeFormInvalid = () => {
	formElements["category_id"].classList.remove("is-invalid");
	formElements["post_title"].classList.remove("is-invalid");
	formElements["post_description"].classList.remove("is-invalid");
	formElements["post_code"].classList.remove("is-invalid");
	formElements["post_picture"].classList.remove("is-invalid");
}
const addFormInvalid = (form_part) => formElements[form_part].classList.add("is-invalid");


deleteBtn.addEventListener("click", (event) => {
	event.preventDefault();
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
						text: "Post has been deleted...",
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
			xhr.open("POST", "deletepost", true);
			xhr.send(new FormData(blogForm));
		}
	});
});

blogForm.addEventListener("submit", (event) => {
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
				title: "Successfully Modified!",
				text: "Successfully Modified! We're Redirecting To The Blogs Page",
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

	xhr.open("POST", "editpost", true);
	xhr.send(new FormData(blogForm));
})
