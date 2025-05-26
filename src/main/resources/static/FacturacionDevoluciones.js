// Mostrar/Ocultar Notificaciones
function Mostrar_Notificaciones() {
    const mensajes = document.getElementById("Mensajes");
    mensajes.style.display = (mensajes.style.display === "none" || mensajes.style.display === "") ? "flex" : "none";
}

// Menú hamburguesa
function toggleMenu() {
    const menu = document.getElementById("navMenu");
    const mensajes = document.getElementById("Mensajes");
    menu.classList.toggle("active");
    if (!menu.classList.contains("active")) {
        mensajes.style.display = "none";
    }
}

// Previsualizar comprobante de pago
document.getElementById('Comprobante_Pago').addEventListener('change', function (event) {
    const file = event.target.files[0];
    const preview = document.getElementById('previewImagen');
    if (file && file.type.startsWith('image/')) {
        const reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
        };
        reader.readAsDataURL(file);
    } else {
        preview.style.display = 'none';
        preview.src = '';
    }
});

// Previsualizar imagen del estado del producto
document.getElementById('Estado_Producto').addEventListener('change', function (event) {
    const file = event.target.files[0];
    const preview = document.getElementById('previewEstado');
    if (file && file.type.startsWith('image/')) {
        const reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
        };
        reader.readAsDataURL(file);
    } else {
        preview.style.display = 'none';
        preview.src = '';
    }
});

// Enviar la información al backend
function Enviar_Informacion() {
    const motivo = document.getElementById("Text_Tools_Description").value;
    const comprobante = document.getElementById("Comprobante_Pago").files[0];
    const estadoProducto = document.getElementById("Estado_Producto").files[0];
    const token = localStorage.getItem("token");

    if (!motivo || !comprobante || !estadoProducto) {
        alert("Por favor, completa todos los campos y selecciona las imágenes.");
        return;
    }

    if (!token) {
        alert("Token no encontrado. Por favor inicia sesión.");
        return;
    }

    const formData = new FormData();
    formData.append("motivo", motivo);
    formData.append("comprobante", comprobante);
    formData.append("estadoProducto", estadoProducto);

    fetch("http://localhost:8080/api/returns", {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${token}`
        },
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("No se pudo enviar la devolución");
        }
        return response.json();
    })
    .then(data => {
        alert("Devolución enviada correctamente");
        console.log("Respuesta del servidor:", data);
        document.getElementById("Text_Tools_Description").value = "";
        document.getElementById("Comprobante_Pago").value = "";
        document.getElementById("Estado_Producto").value = "";
        document.getElementById("previewImagen").style.display = "none";
        document.getElementById("previewEstado").style.display = "none";
        cargarDevoluciones();
    })
    .catch(error => {
        alert("Error al enviar la devolución: " + error.message);
    });
}

// Cargar devoluciones desde el backend
function cargarDevoluciones() {
    const contenedor = document.getElementById("listaDevoluciones");
    contenedor.innerHTML = "<p>Cargando devoluciones...</p>";

    const token = localStorage.getItem("token");
    if (!token) {
        contenedor.innerHTML = "<p>No se encontró el token. Inicia sesión para ver devoluciones.</p>";
        return;
    }

    fetch("http://localhost:8080/api/returns", {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("No se pudo obtener la lista de devoluciones");
        }
        return res.json();
    })
    .then(devoluciones => {
        console.log("Devoluciones recibidas:", devoluciones);

        if (!devoluciones.length) {
            contenedor.innerHTML = "<p>No hay devoluciones registradas</p>";
            return;
        }

        contenedor.innerHTML = "";
        devoluciones.forEach(dev => {
            const tarjeta = document.createElement("div");
            tarjeta.className = "tarjeta";
            tarjeta.innerHTML = `
                <h4>Devolución</h4>
                <p><strong>ID:</strong> ${dev.id}</p>
                <p><strong>Fecha:</strong> ${dev.fecha || 'No disponible'}</p>
                <p><strong>Condición:</strong> ${dev.condition || 'No indicada'}</p>
                <p><strong>Comprobante de pago:</strong> ${dev.motivo || 'Sin especificar'}</p>
            `;
            contenedor.appendChild(tarjeta);
        });
    })
    .catch(error => {
        contenedor.innerHTML = `<p>Error al cargar devoluciones: ${error.message}</p>`;
    });
}

// Filtrar al hacer clic en el botón (recarga la lista)
document.addEventListener("DOMContentLoaded", () => {
    const botonFiltrar = document.querySelector(".filtros button");
    if (botonFiltrar) {
        botonFiltrar.addEventListener("click", cargarDevoluciones);
    }

    cargarDevoluciones(); // Carga inicial
});