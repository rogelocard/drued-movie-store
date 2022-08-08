// Call the dataTables JQuery plugin
$(document).ready(function () {

	loadMovies()
	var tableMovies = $('#tableMovies').DataTable({

		"language": {
			"sProcessing": "Procesando...",
			"sLengthMenu": "Mostrar _MENU_ registros",
			"sZeroRecords": "No se encontraron resultados",
			"sEmptyTable": "Ningún dato disponible en esta tabla",
			"sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
			"sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
			"sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
			"sInfoPostFix": "",
			"sSearch": "Buscar:",
			"sUrl": "",
			"sInfoThousands": ",",
			"sLoadingRecords": "Cargando...",
			"oPaginate": {
				"sFirst": "Primero",
				"sLast": "Último",
				"sNext": "Siguiente",
				"sPrevious": "Anterior"
			},
			"oAria": {
				"sSortAscending": ": Activar para ordenar la columna de manera ascendente",
				"sSortDescending": ": Activar para ordenar la columna de manera descendente"
			}
		}
	});
});

// FUNCTION to load and list in a table all the movie records from the database.
async function loadMovies() {

	const request = await fetch('/api/peliculas', {
		method: 'GET',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
	});
	const movies = await request.json();
	//console.log(movies); //<- Here I check if the data is being returned with a console.log.

	let htmlList = '';
	for (let movie of movies) {
		let buttonDelete = '<button onClick="deleteMovie( ' + movie.id + ' )" class="btnBorrar btn btn-danger" type="button">Borrar</button>'
		let buttonEdit = '<button class="btnEditar btn btn-success" data-toggle="toggle" data-target="#addNewRecordModal">Editar</button>'

		let movieHtml = '<tr></tr><td> ' + movie.id + ' </td><td> ' +
			movie.name + ' </td><td> ' + movie.genre + '</td><td> ' +
			movie.director + ' </td><td> ' + movie.inStock + ' </td><td> ' +
			movie.price + ' </td><td> ' + movie.description +
			' </td><td> ' + buttonDelete + buttonEdit + '';

		htmlList += movieHtml;
	}


	// This sentence popullates the body of the HTML Movie table
	document.querySelector('#tableMovies tbody').outerHTML = htmlList;
}


async function deleteMovie(id) {
	if (!confirm('¿Desea eliminar esta pelicula?')) {
		return;
	}
	//alert(id);
	const request = await fetch('/api/peliculas/eliminar/' + id, {
		method: 'DELETE',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}
	});
	location.reload();
}

//Funcion asignada al boton editar
$('#tableMovies tbody').on('click', '.btnEditar', function () {
	var row = $(this).closest('tr');
	//var nitUsuario = tablaUsuarios.row(row).data().nitUsuario;
	//var nombreCUsuario = tablaUsuarios.row(row).data().nombreCUsuario;
	//var emailUsuario = tablaUsuarios.row(row).data().emailUsuario;
	//var nickUsuario = tablaUsuarios.row(row).data().nickUsuario;
	$('#txtNameMovie').val(function () {
		return tableMovies.row(row).data().name;

	});
	$('#txtGenre').val(function () {
		return tableMovies.row(row).data().genre;

	});
	$('#txtDirector').val(function () {
		return tableMovies.row(row).data().director;

	});
	$('#txtInStock').val(function () {
		return tableMovies.row(row).data().inStock;

	});
	$('#txtPrice').val(function () {
		return tableMovies.row(row).data().price;
	});
	$('#txtDescription').val(function () {
		return tableMovies.row(row).data().description;
	});
});

//Funcion asignada al boton eliminar
$('#tableMovies tbody').on('click', '.btnBorrar', function () {
	var row = $(this).closest('tr');
	var id = tableMovies.row(row).data().id;
	deleteMovie(id);
});
//});
// Method that checks that the browser supports the HTML5 File API
function browserSupportFileUpload() {
	var isCompatible = false;
	if (window.File && window.FileReader &&
		window.FileList && window.Blob) {
		isCompatible = true;
	}
	return isCompatible;
}

function getHeaders() {
	return {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
		'Authorization': localStorage.token
	};
}
//Function to update a movie object. 
async function updateMovie() {
	let data = {};
	data.name = document.getElementById('txtNameMovie').value;
	data.genre = document.getElementById('txtGenre').value;
	data.director = document.getElementById('txtDirector').value;
	data.inStock = document.getElementById('txtInStock').value;
	data.price = document.getElementById('txtPrice').value;
	data.description = document.getElementById('txtDescription').value;

	const request = await fetch('api/peliculas/pelicula/' + id, {
		method: 'PUT',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'Authorization': localStorage.token
		},
		body: JSON.stringify(datos)
	});
	const response = await request.text();
	if (response.includes('NO_ADMIN')) {
		alert('SOLO EL ADMINISTRADOR PUEDE ACTUALIZAR');
	} else if (response.includes('ACTUALIZADO')) {
		alert('La cuenta fue actualizada con exito');
		location.reload();
	} else {
		alert("Revise los campos");
	}
}