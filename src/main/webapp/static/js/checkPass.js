function checkPass(pass, confirmPass) {
	if (pass.value == confirmPass.value) {
		confirmPass.setCustomValidity("");
	} else {
		confirmPass.setCustomValidity("Passwords Don't Match");
	}
}