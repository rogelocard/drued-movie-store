$(document).ready(function() {
	//On ready
});

function getHeaders() {
	return {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
		'Authorization': localStorage.token
	}
}

async function addMovie() {
	let data = {};
	// data.nitProveedor = document.getElementById('txtNitProveedor').value;
	data.description = document.getElementById('txtDescription').value;
	data.director = document.getElementById('txtDirector').value;
	data.genre = document.getElementById('txtGenre').value;
	data.inStock = document.getElementById('txtInStock').value;
	data.name = document.getElementById('txtNameMovie').value;
	data.price = document.getElementById('txtPrice').value;
	
	const request = await fetch('/api/peliculas/guardarPelicula', {
		method: 'POST',
		headers: getHeaders(),
		body: JSON.stringify(data)
	});
	const response = await request.json();
	
	// const response = await request.text();
	// if(response == 'OK'){
	// 	window.location.href = 'movie.html'
	// } else {
	// 	alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
	// };
	// if (response.includes('AGREGADO')) {
	// 	alert('La pelicula fue agregada con exito');
	// 	window.location.href = 'movie.html'
	// } else {
	// 	Swal.fire({
	// 		icon: 'error',
	// 		tittle: 'Oops...',
	// 		text: 'Por favor, revise los datos ingresados',
	// 	});
	// }
}
