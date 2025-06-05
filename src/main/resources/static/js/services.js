document.getElementById('formulario').addEventListener('submit', function(e) {
  e.preventDefault();

  const estaLogueado = document.body.getAttribute('data-logueado');

  if (!estaLogueado) {
    alert('Tienes que iniciar sesión para acceder al formulario');
    return; // NO enviar el formulario
  }

  console.log("estas logeado? : " +estaLogueado);
  
  const data = {
    asunto: document.getElementById('asunto').value,
    mensaje: document.getElementById('mensaje').value
  };
  
  console.log('Asunto enviado:', data.asunto);

  fetch('http://localhost:8080/api/solicitudes', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  })
  .then(response => {
    if (response.ok) {
      alert('Solicitud enviada con éxito');
      document.getElementById('formulario').reset();
    } else if (response.status === 401 || response.status === 403) {
      alert('Debes iniciar sesión para enviar la solicitud');
    } else {
      alert('Error al enviar la solicitud');
    }
  })
  .catch(error => {
    alert('Error al enviar la solicitud');
    console.error(error);
  });
});
