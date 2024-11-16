/**
 * 
 */
const deleteForms = document.querySelectorAll(".deleteData");

deleteForms.forEach(form => {
	form.addEventListener("submit", (event) => {
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
							text: "Category has been deleted...",
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
				xhr.open("POST", "deletecategory", true);
				xhr.send(new FormData(form));
			}
		});
	})
})