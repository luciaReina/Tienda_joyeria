function anadirACesta(idProducto) {
    fetch("http://localhost:8080/usuario/perfil", { credentials: "include" }) 
        .then(response => {
            if (response.status === 200) {
                return response.json(); // usuario logueado
            } else {
                throw new Error('No logueado');
            }
        })
        .then(usuario => {
			console.log(idProducto);
            return fetch('http://localhost:8080/carrito/agregar', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ idProducto }),
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
            window.location.href = '/html/login.html'; // redirige al login
        });
}
