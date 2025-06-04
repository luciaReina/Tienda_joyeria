document.addEventListener('DOMContentLoaded', function () {
  const form = document.getElementById('formRegistro');

  form.addEventListener('submit', function (e) {
    e.preventDefault();

	const datos = {
	  username: document.getElementById('username').value,
	  apellido: document.getElementById('apellido').value,
	  email: document.getElementById('email').value,
	  telefono: document.getElementById('telefono').value,
	  password: document.getElementById('password').value,
	  pais: document.getElementById('pais').value,
	  ciudad: document.getElementById('ciudad').value,
	  calle: document.getElementById('calle').value,
	  codigoPostal: document.getElementById('codigoPostal').value
	};
	
	console.log(datos);

    fetch('http://localhost:8080/auth/registro', {
      method: 'post',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(datos)
    })
    .then(res => {
      if (res.ok) {
        window.location.href = "/html/login.html";
      } else {
        alert("Error al registrar usuario");
      }
    })
    .catch(err => {
      console.error(err);
      alert("Error de conexión");
    });
  });
});
 