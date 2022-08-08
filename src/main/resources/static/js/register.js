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
	const response = await request.json();
	if (response.includes('AGREGADO')) {
		Swal.fire({
			icon: 'success',
			tittle: 'La cuenta fue creada con exito',
			showConfirmButton: false,
		});
		location.reload();
		
	} else {
		//alert('La cuenta fue creada con exito')
		Swal.fire({
			icon: 'success',
			tittle: 'La cuenta fue creada con exito',
			showConfirmButton: false,
		});
	}
	// } else {
	// 	if (response.includes('usuarios.UK_dqepd9dx5ubsh89wj99uimqw')) {
	// 		Swal.fire({
	// 			icon: 'error',
	// 			tittle: 'Oops...',
	// 			text: 'El nombre de usuario ya esta en uso'
	// 		});
	// 	}
	// 	if (response.includes('usuarios.UK_t9qm9g9sbjvhupwktuwdr499w')) {
	// 		Swal.fire({
	// 			icon: 'error',
	// 			tittle: 'Oops...',
	// 			text: 'La cedula ya se encuentra registrada'
	// 		});
	// 	}
	// 	if (response.includes('usuarios.UK_bdp41y8e8un0nsxowhgsl783s')) {
	// 		Swal.fire({
	// 			icon: 'error',
	// 			tittle: 'Oops...',
	// 			text: 'El correo ya se encuentra registrado',
	// 		});
	// 	}
	// }
}