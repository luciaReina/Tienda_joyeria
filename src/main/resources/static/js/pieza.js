document.addEventListener('DOMContentLoaded', function () {
  // --- Código menú y scroll ---
  let lastScrollTop = 0;
  const menuContainer = document.querySelector('.menu-container');
  const iconoHamburguesa = document.querySelector('.icon-tres');
  const menuMovil = document.querySelector('.menu-movil');
  const body = document.body;

  if (menuContainer) {
    menuContainer.style.transition = 'transform 0.3s ease';
    menuContainer.style.transform = 'translateY(0)';
  }

  window.addEventListener('scroll', function () {
    let currentScroll = window.pageYOffset || document.documentElement.scrollTop;

    if (menuContainer) {
      if (currentScroll > lastScrollTop) {
        menuContainer.style.transform = 'translateY(-100%)';
      } else {
        if (currentScroll <= 0) {
          menuContainer.style.transform = 'translateY(0)';
        }
      }
    }

    lastScrollTop = currentScroll <= 0 ? 0 : currentScroll;
  });

  if (iconoHamburguesa && menuMovil) {
    iconoHamburguesa.addEventListener('click', function () {
      menuMovil.classList.toggle('show');
      body.classList.toggle('no-scroll');
    });
  }

  const colorOptions = document.querySelectorAll('.color');

  colorOptions.forEach(color => {
    color.addEventListener('click', () => {
      colorOptions.forEach(option => option.classList.remove('selected'));
      color.classList.add('selected');
    });
  });

  // --- Código carrusel ---
  // Para tu nuevo HTML: carrusel dentro de #productoMovil
  const carrusel = document.querySelector("#productoMovil .carrousel");
  if (carrusel) {
    const imgs = carrusel.querySelectorAll("img");
    const flechaIzquierda = carrusel.querySelector(".flecha.izquierda");
    const flechaDerecha = carrusel.querySelector(".flecha.derecha");

    let indiceActual = 0;

    function mostrarImagen(indice) {
      imgs.forEach((img, i) => {
        img.style.display = i === indice ? "block" : "none";
      });
    }

    mostrarImagen(indiceActual);

    if (flechaDerecha) {
      flechaDerecha.addEventListener("click", function (e) {
        e.stopPropagation();
        indiceActual = (indiceActual + 1) % imgs.length;
        mostrarImagen(indiceActual);
      });
    }

    if (flechaIzquierda) {
      flechaIzquierda.addEventListener("click", function (e) {
        e.stopPropagation();
        indiceActual = (indiceActual - 1 + imgs.length) % imgs.length;
        mostrarImagen(indiceActual);
      });
    }
  }
  
});
