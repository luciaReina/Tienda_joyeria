document.querySelectorAll('.proyecto').forEach(proyecto => {
    const carrousel = proyecto.querySelector('.carrousel');
    const images = carrousel.querySelectorAll('img');
    const imagenPrincipal = proyecto.querySelector('.imagen-principal');
    let currentIndex = 0;
  
    // Mostrar solo la primera imagen del carrusel inicialmente oculta
    images.forEach((img, index) => {
      img.style.display = 'none';
    });
    images[0].style.display = 'block';
  
    function mostrarImagen(index) {
      images.forEach((img, i) => {
        img.style.display = 'none';
      });
      images[index].style.display = 'block';
  
      // Oculta la imagen principal al activar carrusel
      if (imagenPrincipal) {
        imagenPrincipal.style.opacity = '0';
      }
    }
  
    const flechaIzquierda = proyecto.querySelector('.flecha.izquierda');
    const flechaDerecha = proyecto.querySelector('.flecha.derecha');
  
    flechaIzquierda.addEventListener('click', () => {
      currentIndex = (currentIndex === 0) ? images.length - 1 : currentIndex - 1;
      mostrarImagen(currentIndex);
    });
  
    flechaDerecha.addEventListener('click', () => {
      currentIndex = (currentIndex === images.length - 1) ? 0 : currentIndex + 1;
      mostrarImagen(currentIndex);
    });
  
    // Restaurar imagen principal cuando se sale del hover
    proyecto.addEventListener('mouseleave', () => {
      currentIndex = 0;
      images.forEach((img, i) => {
        img.style.display = 'none';
      });
      images[0].style.display = 'block';
      if (imagenPrincipal) {
        imagenPrincipal.style.opacity = '1';
      }
    });

    document.querySelectorAll('.flecha').forEach(flecha => {
      flecha.addEventListener('click', function(event) {
        event.preventDefault();    // <-- Bloquea el <a href>
        event.stopPropagation();   // <-- Evita que suba al contenedor
      });
    });


  });
  