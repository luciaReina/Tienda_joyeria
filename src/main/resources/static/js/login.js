// Mostrar mensaje de error si la URL contiene ?error=true
  window.onload = function() {
    if (window.location.search.indexOf('error=true') !== -1) {
      document.getElementById('error-message').style.display = 'block';
    }
  };