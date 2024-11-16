/**
 * Script File For Blogs Page
 */
const links = document.querySelectorAll(".list-group-item");
const loader = document.querySelector("#loader");
const blogs = document.querySelector("#blogs");

const removeAttribues = () => links.forEach(link => link.classList.remove("text-bg-dark"));
const addAttribute = element => element.classList.add("text-bg-dark");
const toggleLoaders = () => {
	loader.classList.toggle("hide");
	blogs.classList.toggle("hide");
}
const getPosts = (categoryId) => {
	const xhr = new XMLHttpRequest();
	xhr.addEventListener("load", () => {
		toggleLoaders();
		blogs.innerHTML = xhr.responseText;
	});
	xhr.open("GET", `getBlogs.jsp?categoryId=${categoryId}`, true);
	xhr.send();
}

window.addEventListener("load", () => {
	removeAttribues();
	const id = new URLSearchParams(window.location.search).get("categoryId");
	getPosts(id);
	document.getElementById(id).classList.add("text-bg-dark");	
});

links.forEach(link => link.addEventListener("click", (event) => {
	event.preventDefault();
	removeAttribues();
	addAttribute(link);
	toggleLoaders();
	getPosts(link.id);
}));