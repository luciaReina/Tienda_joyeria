document.addEventListener('DOMContentLoaded', function () {
    let lastScrollTop = 0;
    const menuContainer = document.querySelector('.menu-container');
    const iconoHamburguesa = document.querySelector('.icon-tres');
    const menuMovil = document.querySelector('.menu-movil');
    const body = document.body;
  
    // Mostrar el menú al cargar la página
    menuContainer.style.transition = 'transform 0.3s ease';
    menuContainer.style.transform = 'translateY(0)';
  
    // Ocultar/mostrar el menú al hacer scroll
    window.addEventListener('scroll', function () {
      let currentScroll = window.pageYOffset || document.documentElement.scrollTop;
  
      if (currentScroll > lastScrollTop) {
        // Scroll hacia abajo → ocultar
        menuContainer.style.transform = 'translateY(-100%)';
      } else {
        // Scroll hacia arriba y estamos en la parte superior → mostrar
        if (currentScroll <= 0) {
          menuContainer.style.transform = 'translateY(0)';
        }
      }
  
      lastScrollTop = currentScroll <= 0 ? 0 : currentScroll;
    });
  
    // Función hamburguesa para móviles
    if (iconoHamburguesa && menuMovil) {
      iconoHamburguesa.addEventListener('click', function () {
        menuMovil.classList.toggle('show');
        body.classList.toggle('no-scroll');
      });
    }
  });
  