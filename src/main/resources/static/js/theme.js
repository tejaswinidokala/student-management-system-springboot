(function () {
	const root = document.documentElement;
	const storedTheme = localStorage.getItem("student-management-theme");
	const preferredTheme = window.matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light";
	root.dataset.theme = storedTheme || preferredTheme;

	function updateToggle(toggle) {
		const isDark = root.dataset.theme === "dark";
		toggle.textContent = isDark ? "Light mode" : "Dark mode";
		toggle.setAttribute("aria-label", isDark ? "Switch to light mode" : "Switch to dark mode");
	}

	document.querySelectorAll(".theme-toggle").forEach(function (toggle) {
		updateToggle(toggle);
		toggle.addEventListener("click", function () {
			root.dataset.theme = root.dataset.theme === "dark" ? "light" : "dark";
			localStorage.setItem("student-management-theme", root.dataset.theme);
			document.querySelectorAll(".theme-toggle").forEach(updateToggle);
		});
	});
})();