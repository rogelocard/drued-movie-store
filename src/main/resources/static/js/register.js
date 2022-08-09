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

async function registerUser() {
	let data = {};
	data.firstName = document.getElementById('txtName').value;
	data.lastName = document.getElementById('txtLastName').value;
	data.username = document.getElementById('txtUsername').value;
	data.password = document.getElementById('txtPasswordUsuario').value;

	let passwordConfirm = document.getElementById('txtPasswordUsuarioRepeat').value;

	if (passwordConfirm != data.password) {
		// alert("las contra...")
		Swal.fire({
			icon: 'error',
			tittle: 'Oops...',
			text: 'Las contraseñas no coinciden'
		});
		return;
	}

	const request = await fetch('/api/usuarios/guardar', {
		method: 'POST',
		headers: getHeaders(),
		body: JSON.stringify(data)
	});
	const response = await request.text();
	if(response == 'OK'){
		window.location.href = 'login.html'
	} else {
		alert("El usuario fue creado exitosamente");
		window.location.href = 'login.html'
	}

}