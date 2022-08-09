$(document).ready(function () {
	//On ready
});

function getHeaders() {
	return {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
		'Authorization': localStorage.token
	}
}

async function login() {
	let data = {};
	data.username = document.getElementById('txtUsername').value;
	data.password = document.getElementById('txtPasswordUsuario').value;

	const request = await fetch('/api/login', {
		method: 'POST',
		headers: getHeaders(),
		body: JSON.stringify(data)
	});
	const response = await request.text();
	if(response == 'OK'){
		window.location.href = 'movie.html'
	} else {
		alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
	}
}