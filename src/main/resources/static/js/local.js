document.addEventListener("DOMContentLoaded", function () {
  // --- MAPA ---
  const lat = 36.7213;
  const lng = -4.4217;

  const mapa = L.map('mapa').setView([lat, lng], 13);
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
  }).addTo(mapa);
  L.marker([lat, lng]).addTo(mapa)
    .bindPopup("IVI REINA")
    .openPopup();

  // --- DETALLES TIENDA DESPLEGABLE ---
  const botonDetalles = document.getElementById("boton-detalles");
  const detalles = document.querySelector("#detalles-tienda.detalles-tienda");

  botonDetalles.addEventListener("click", function () {
    if (detalles.classList.contains("oculto")) {
      detalles.classList.remove("oculto");
      botonDetalles.textContent = "Ocultar detalles";
    } else {
      detalles.classList.add("oculto");
      botonDetalles.textContent = "Detalles tienda";
    }
  });

  // --- Mostrar/Ocultar mapa al pulsar icono ---
  const iconoMapa = document.getElementById("icono-mapa");
  const panelInfo = document.querySelector(".panel-info");
  const mapaContenedor = document.getElementById("mapaContenedor");

  iconoMapa.addEventListener("click", function () {
    panelInfo.style.display = "none";
    mapaContenedor.style.display = "block";
    setTimeout(() => {
      mapa.invalidateSize();
    }, 300);
  });

  // --- BOTÓN CERRAR para ocultar mapa y mostrar panel ---
  const cerrarBtn = document.querySelector(".cerrar");
  if (cerrarBtn) {
    cerrarBtn.addEventListener("click", function () {
      mapaContenedor.style.display = "none";
      panelInfo.style.display = "block";
    });
  }
});