// Ejemplo de productos
const productos = [
  { nombre: "anillo plata" },
  { nombre: "anillo oro" },
  { nombre: "brazalete plata"},
  { nombre: "brazalete oro"},
  { nombre: "collar plata"},
  { nombre: "collar oro"},
  { nombre: "pendientes plata"},
  { nombre: "pendientes oro"},
  { nombre: "pendientes"},
  { nombre: "Collares"},
  { nombre: "anillos"},
  { nombre: "brazaletes"},
  
];

function searchProducts() {
      const input = document.getElementById("searchInput").value.toLowerCase();
      const resultados = productos.filter(p => p.nombre.toLowerCase().includes(input));
      const resultsDiv = document.getElementById("searchResults");

      resultsDiv.innerHTML = ''; // Limpiar resultados previos

      if (resultados.length === 0) {
        resultsDiv.innerHTML = "<p style='padding:10px;'>No se encontraron productos.</p>";
      } else {
        resultados.forEach(p => {
          const div = document.createElement("div");
          div.classList.add("product");
          div.innerHTML = `<strong>${p.nombre}</strong>`;
          div.addEventListener('click', () => {
            window.location.href = 'productos.html'; // Redirigir a la página
          });
          resultsDiv.appendChild(div);
        });
      }
    }