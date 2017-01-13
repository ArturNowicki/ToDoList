function check_pass(pass, confirm_pass) {
	if (pass.value == confirm_pass.value) {
		confirm_pass.setCustomValidity("");
	} else {
		confirm_pass.setCustomValidity("Passwords Don't Match");
	}
}