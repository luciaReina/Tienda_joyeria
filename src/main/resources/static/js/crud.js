function paginarTabla(tablaId, filasPorPagina = 10) {
    const tabla = document.getElementById(tablaId);
    if (!tabla) return;

    const tbody = tabla.querySelector("tbody");
    const filas = Array.from(tbody.querySelectorAll("tr"));
    const totalPaginas = Math.ceil(filas.length / filasPorPagina);

    // Si hay paginación anterior, eliminarla
    const paginacionExistente = tabla.parentNode.querySelector(".paginacion");
    if (paginacionExistente) {
        paginacionExistente.remove();
    }

    if (totalPaginas <= 1) return;

    const paginacion = document.createElement("div");
    paginacion.className = "paginacion mt-3";

    function mostrarPagina(pagina) {
        filas.forEach((fila, i) => {
            fila.style.display = (i >= (pagina - 1) * filasPorPagina && i < pagina * filasPorPagina) ? "" : "none";
        });

        paginacion.querySelectorAll("button").forEach((btn, i) => {
            btn.classList.toggle("btn-dark", i + 1 === pagina);
            btn.classList.toggle("btn-outline-dark", i + 1 !== pagina);
        });
    }

    for (let i = 1; i <= totalPaginas; i++) {
        const btn = document.createElement("button");
        btn.textContent = i;
        btn.className = "btn btn-outline-dark btn-sm me-1";
        btn.addEventListener("click", () => mostrarPagina(i));
        paginacion.appendChild(btn);
    }

    tabla.parentNode.appendChild(paginacion);
    mostrarPagina(1);
}

document.addEventListener("DOMContentLoaded", () => {
    const sections = document.querySelectorAll('.card'); // todas las tablas (secciones)
    const links = document.querySelectorAll('nav.sidebar .nav-link'); // enlaces menú lateral

    // Ocultar todas las secciones inicialmente
    sections.forEach(section => section.style.display = 'none');

    function mostrarSeccion(id) {
        sections.forEach(section => {
            section.style.display = (section.id === id) ? 'block' : 'none';
        });
        // Paginamos SOLO la tabla visible
        const tablaVisible = document.querySelector(`#${id} table.paginable`);
        if (tablaVisible && tablaVisible.id) {
            paginarTabla(tablaVisible.id, 10);
        }
    }

    // Si la URL tiene hash, mostramos esa sección
    if (window.location.hash) {
        mostrarSeccion(window.location.hash.substring(1));
    }

    // Añadimos evento click a cada link del menú lateral
    links.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            const targetId = link.getAttribute('href').substring(1);

            mostrarSeccion(targetId);

            // Cambiamos el hash sin recargar la página
            history.replaceState(null, null, '#' + targetId);
        });
    });
});
