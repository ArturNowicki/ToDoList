/**
 * 
 */
function confirmDelete(id, name) {
	var str1 = "Delete ";
	console.log("aa");
	var message = str1.concat(name, "?");
	var answer = confirm(message);
	if (answer) {
		var str2 = "/delete-"
		var url = str2.concat(id, "-user");
		window.location.href = url;
	}
}