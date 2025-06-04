document.addEventListener('DOMContentLoaded', function () {
  const form = document.getElementById('formRegistro');

  form.addEventListener('submit', function (e) {
    e.preventDefault();

    const datos = {
      nombre: document.getElementById('nombre').value,
      apellido: document.getElementById('apellido').value,
      email: document.getElementById('email').value,
      telefono: document.getElementById('telefono').value,
      password: document.getElementById('password').value
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
 