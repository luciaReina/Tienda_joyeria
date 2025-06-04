document.addEventListener('DOMContentLoaded', function () {
  const menuContainer = document.querySelector('.menu-container');
  const iconoHamburguesa = document.querySelector('.icon-tres');
  const menuMovil = document.querySelector('.menu-movil');
  const body = document.body;

  // Mostrar el menú fijo (sin animación)
  menuContainer.style.transition = 'none';
  menuContainer.style.transform = 'translateY(0)';

  // Eliminamos el listener de scroll que oculta/muestra la cabecera

  // Función hamburguesa para móviles
  if (iconoHamburguesa && menuMovil) {
    iconoHamburguesa.addEventListener('click', function () {
      menuMovil.classList.toggle('show');
      body.classList.toggle('no-scroll');
    });
  }
});