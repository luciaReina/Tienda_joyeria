document.querySelectorAll(".proyecto").forEach((proyecto) => {
  const carrousel = proyecto.querySelector(".carrousel");
  const images = carrousel.querySelectorAll("img");
  const imagenPrincipal = proyecto.querySelector(".imagen-principal");
  let currentIndex = 0;

  // Mostrar solo la primera imagen del carrusel inicialmente oculta
  images.forEach((img, index) => {
    img.style.display = "none";
  });
  images[0].style.display = "block";

  function mostrarImagen(index) {
    images.forEach((img, i) => {
      img.style.display = "none";
    });
    images[index].style.display = "block";

    // Oculta la imagen principal al activar carrusel
    if (imagenPrincipal) {
      imagenPrincipal.style.opacity = "0";
    }
  }

  const flechaIzquierda = proyecto.querySelector(".flecha.izquierda");
  const flechaDerecha = proyecto.querySelector(".flecha.derecha");

  flechaIzquierda.addEventListener("click", () => {
    currentIndex = currentIndex === 0 ? images.length - 1 : currentIndex - 1;
    mostrarImagen(currentIndex);
  });

  flechaDerecha.addEventListener("click", () => {
    currentIndex = currentIndex === images.length - 1 ? 0 : currentIndex + 1;
    mostrarImagen(currentIndex);
  });

  // Restaurar imagen principal cuando se sale del hover
  proyecto.addEventListener("mouseleave", () => {
    currentIndex = 0;
    images.forEach((img, i) => {
      img.style.display = "none";
    });
    images[0].style.display = "block";
    if (imagenPrincipal) {
      imagenPrincipal.style.opacity = "1";
    }
  });

  document.querySelectorAll(".flecha").forEach((flecha) => {
    flecha.addEventListener("click", function (event) {
      event.preventDefault(); // <-- Bloquea el <a href>
      event.stopPropagation(); // <-- Evita que suba al contenedor
    });
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const productosContainer = document.getElementById("productos-container");
  const loadingMessage = document.getElementById("loadingMessage");
  const errorMessage = document.getElementById("errorMessage");

  // Función para generar el HTML de un solo producto
  function createProductHtml(product) {
    const precioFormateado = product.precio
      ? product.precio.toFixed(2) + "$"
      : "Precio no disponible";

    const imagenPrincipal = product.imgUrl ? `/img/${product.imgUrl}` : "/img/cat1.png";

    const enlaceProducto = `/admin/productos/productos/${product.idProducto}`;

    return `
      <a href="${enlaceProducto}" class="proyecto-enlace">
        <div class="proyecto" id="producto-${product.idProducto}"> 
          <div class="producto-imagen">
            <img class="imagen-principal" src="${imagenPrincipal}" alt="${product.nombre || "Producto"}">
          </div>
          <h4>${product.nombre || "Nombre no disponible"}</h4>
          <p>${precioFormateado}</p>
        </div>
      </a>
    `;
  }


  // Función para cargar los productos desde el backend
  function loadProducts() {
    loadingMessage.style.display = "block"; // Mostrar mensaje de carga
    errorMessage.style.display = "none"; // Ocultar mensaje de error anterior
    productosContainer.innerHTML = ""; // Limpiar contenido anterior

    fetch("http://localhost:8080/admin/productos/productos") // Endpoint REST que devuelve la lista de productos
      .then((response) => {
        if (!response.ok) {
          throw new Error(
            "Error de red o del servidor: " +
              response.status +
              " " +
              response.statusText
          );
        }
        return response.json(); 
      })
      .then((products) => {
        console.log("Productos recibidos:", products); // Para depuración en la consola
        loadingMessage.style.display = "none"; // Ocultar mensaje de carga

        if (products.length === 0) {
          productosContainer.innerHTML =
            '<p class="no-products">No hay productos disponibles en este momento.</p>';
        } else {
          // Generar el HTML para cada producto y añadirlo al contenedor
          products.forEach((product) => {
            productosContainer.innerHTML += createProductHtml(product);
          });

          // Si tienes una función para inicializar carousels, llámala aquí.
          // initializeCarousels();
        }
      })
      .catch((error) => {
        console.error("Error al cargar los productos:", error);
        loadingMessage.style.display = "none"; // Ocultar mensaje de carga
        errorMessage.style.display = "block"; // Mostrar mensaje de error
        errorMessage.textContent =
          "No se pudieron cargar los productos. Por favor, inténtelo más tarde. Detalle: " +
          error.message;
        productosContainer.innerHTML = ""; // Asegurarse de que no haya productos incompletos
      });
  }

  // Llama a la función de carga al cargar la página
  loadProducts();
});
