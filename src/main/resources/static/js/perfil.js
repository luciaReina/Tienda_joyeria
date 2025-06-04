document.addEventListener("DOMContentLoaded", function () {
  fetch("/usuario/perfil")
    .then(response => {
      if (!response.ok) {
        throw new Error("Usuario no autenticado");
      }
      return response.json();
    })
    .then(usuario => {
      document.getElementById("nombre").textContent = usuario.nombre + " " + usuario.apellido;
      document.getElementById("email").textContent = usuario.email;
      document.getElementById("telefono").textContent = usuario.telefono || "No definido";
      document.getElementById("direccion").textContent = usuario.direccion || "No definida";
    })
    .catch(err => {
      window.location.href = "/html/login.html";
    });
});

function mostrarSeccion(id) {
  // Lógica para mostrar u ocultar secciones si haces múltiples
  document.getElementById(id).style.display = "block";
}