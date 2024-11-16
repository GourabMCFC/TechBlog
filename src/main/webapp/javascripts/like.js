/**
 * 
 */
const likeBtn = document.querySelector("#likeBtn");
const likeCounter = document.querySelector("#likeBtn span");

const addLike = () => {
	const xhr = new XMLHttpRequest();
	xhr.addEventListener("load", () => {
		const { success, error_message } = JSON.parse(xhr.responseText);
		if (success) {
			likeBtn.classList.add("active");
			likeCounter.firstChild.nodeValue = (parseInt(likeCounter.firstChild.nodeValue) + 1);
		}
		else {
			Swal.fire({
				icon: "error",
				title: "Server Error",
				text: error_message,
			});
		}
	});
	xhr.addEventListener("error", () => {
		Swal.fire({
			icon: "error",
			title: "Network Error",
			text: "Network Error Has Been Encountered"
		});
	})
	xhr.open("POST", "likepost", true);
	xhr.send(new FormData(likeBtn));
}

const dislike = () => {
	const xhr = new XMLHttpRequest();
	xhr.addEventListener("load", () => {
		const { success, error_message } = JSON.parse(xhr.responseText);
		if (success) {
			likeBtn.classList.remove("active");
			likeCounter.firstChild.nodeValue = (parseInt(likeCounter.firstChild.nodeValue) - 1);
		}
		else {
			Swal.fire({
				icon: "error",
				title: "Server Error",
				text: error_message,
			});
		}
	});
	xhr.addEventListener("error", () => {
		Swal.fire({
			icon: "error",
			title: "Network Error",
			text: "Network Error Has Been Encountered"
		});
	})
	xhr.open("POST", "dislikepost", true);
	xhr.send(new FormData(likeBtn));
}

likeBtn.addEventListener("click", (event) => {
	event.preventDefault();
	if(!likeBtn.classList.contains("active")) addLike();
	else dislike();
});