function anadirACesta(idProducto) {
    fetch('/usuario/logueado')
        .then(response => {
            if (response.status === 200) {
                return response.json(); // usuario logueado
            } else {
                throw new Error('No logueado');
            }
        })
        .then(usuario => {
            return fetch('/carrito/agregar', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ idProducto })
            });
        })
        .then(res => {
            if (res.ok) {
                document.getElementById(`tick-${idProducto}`).style.display = 'inline';
                setTimeout(() => {
                    document.getElementById(`tick-${idProducto}`).style.display = 'none';
                }, 2000);
            } else {
                alert('No se pudo añadir el producto');
            }
        })
        .catch(() => {
            alert('Debes iniciar sesión para añadir productos a la cesta.');
            window.location.href = '/html/usuario.html'; // redirige al login
        });
}
