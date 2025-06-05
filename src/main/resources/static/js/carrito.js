document.addEventListener('DOMContentLoaded', function () {
  const botones = document.querySelectorAll('.btn-add-carrito');

  botones.forEach(boton => {
    boton.addEventListener('click', function () {
      const producto = this.closest('.producto');
      const id = producto.dataset.id;
      const nombre = producto.dataset.nombre;
      const precio = producto.dataset.precio;

      // Verificar sesión
      fetch('/api/sesion-usuario')
        .then(res => {
          if (res.status === 200) {
            // Usuario logeado: enviamos producto al backend
            fetch('/api/carrito/agregar', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify({ id, nombre, precio })
            }).then(resp => {
              if (resp.ok) {
                const msg = producto.querySelector('.mensaje-ok');
                msg.style.display = 'inline';
                setTimeout(() => msg.style.display = 'none', 2000);
              }
            });
          } else {
            alert("Debes iniciar sesión para añadir productos al carrito.");
          }
        });
    });
  });
});
