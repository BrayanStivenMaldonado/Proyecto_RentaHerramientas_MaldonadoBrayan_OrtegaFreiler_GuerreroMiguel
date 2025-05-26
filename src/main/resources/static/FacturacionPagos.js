function Mostrar_Notificaciones() {
    const Mensajes = document.getElementById("Mensajes");
    if (Mensajes.style.display == "none") {
        Mensajes.style.display = "flex";
    } else {
        Mensajes.style.display = "none";
    }
}

function toggleMenu() {
    const menu = document.getElementById("navMenu");
    const mesanje = document.getElementById("Mensajes");

    menu.classList.toggle("active");

    if (!menu.classList.contains("active")) {
        mesanje.style.display = "none";
    }
}

function Mostrar_Notificaciones() {
    const Mensajes = document.getElementById("Mensajes");
    if (Mensajes.style.display == "none") {
        Mensajes.style.display = "flex";
    } else {
        Mensajes.style.display = "none";
    }
}

function toggleMenu() {
    const menu = document.getElementById("navMenu");
    const mesanje = document.getElementById("Mensajes");

    menu.classList.toggle("active");

    if (!menu.classList.contains("active")) {
        mesanje.style.display = "none";
    }
}

function DescargarFactura() {
    const factura = document.querySelector(".contenido");
    const btnDescargar = document.querySelector(".donwload");
    const filtros = document.querySelector(".filtros");

    btnDescargar.style.display = "none";
    filtros.style.display = "none";

    const opciones = {
        margin: 0.5,
        filename: 'Factura.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2, scrollY: 0 },
        jsPDF: { unit: 'in', format: 'a4', orientation: 'portrait' }
    };

    html2pdf().set(opciones).from(factura).save().then(() => {
        btnDescargar.style.display = "block";
        filtros.style.display = "flex";
    });
}

async function filtrarTabla() {
    const token = localStorage.getItem("token");
    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/payments", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (response.status === 403) {
            alert("Token inválido o expirado");
            return;
        }

        const nuevosPagos = await response.json();
        console.log("Pagos recibidos:", nuevosPagos);

        const tbody = document.querySelector(".tabla-factura tbody");

        // Limpiar filas anteriores
        tbody.innerHTML = "";

        let total = 0;

        nuevosPagos.forEach(pago => {
            const herramientas = pago.toolNames || [];

            herramientas.forEach(toolName => {
                const producto = `Producto ID ${pago.id} - ${toolName}`;
                const detalle = `Fecha: ${pago.date} / Método: ${pago.paymentMethod}`;

                // Cantidad real pagada (asumimos 1 si no existe)
                const cantidad = pago.quantity !== undefined && pago.quantity !== null ? pago.quantity : 1;

                // Precio unitario (total dividido por cantidad)
                const precio = (pago.total && cantidad) ? (pago.total / cantidad) : 0;

                // Total del ítem (por herramienta)
                const totalItem = pago.total / herramientas.length;

                total += totalItem;

                // Crear fila para la tabla
                const fila = document.createElement("tr");
                fila.innerHTML = `
                    <td>${producto}</td>
                    <td>${detalle}</td>
                    <td>${cantidad.toFixed(2)}</td>
                    <td>$ ${precio.toFixed(2)}</td>
                    <td>$ ${totalItem.toFixed(2)}</td>
                `;
                tbody.appendChild(fila);
            });
        });

        const totalConIVA = total * 1.21;
        document.getElementById("subtotal").textContent = `Subtotal $ ${total.toFixed(2)}`;
        document.getElementById("totalFinal").innerHTML = `<strong>$ ${totalConIVA.toFixed(2)}</strong>`;

    } catch (error) {
        console.error("Error al obtener los pagos:", error);
        alert("Hubo un error al cargar los pagos.");
    }
}

function limpiarTabla() {
    const tbody = document.querySelector(".tabla-factura tbody");
    tbody.innerHTML = "";
    document.getElementById("subtotal").textContent = "Subtotal $ 0.00";
    document.getElementById("totalFinal").innerHTML = "<strong>$ 0.00</strong>";
}

function cargarTabla(pagos) {
    const tbody = document.querySelector(".tabla-factura tbody");
    tbody.innerHTML = "";

    let total = 0;

    pagos.forEach(pago => {
        const producto = `Factura ID #${pago.id}`;
        const detalle = `Fecha: ${pago.date} / Método: ${pago.paymentMethod}`;

        const cantidad = 1; // Solo un pago
        const precio = pago.subtotal || 0;
        const totalItem = pago.total || 0;

        total += totalItem;

        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td>${producto}</td>
            <td>${detalle}</td>
            <td>${cantidad}</td>
            <td>$ ${precio.toFixed(2)}</td>
            <td>$ ${totalItem.toFixed(2)}</td>
        `;
        tbody.appendChild(fila);
    });

    const totalConIVA = total;
    document.getElementById("subtotal").textContent = `Subtotal $ ${total.toFixed(2)}`;
    document.getElementById("totalFinal").innerHTML = `<strong>$ ${totalConIVA.toFixed(2)}</strong>`;
}

function mostrarFacturas() {
    document.getElementById("filtroID").value = "";
    filtrarTabla();
}

function filtrarPorNombre() {
    const idBuscado = document.getElementById("filtroID").value.trim();
    if (!idBuscado) {
        alert("Por favor, introduce un ID de factura.");
        return;
    }

    const token = localStorage.getItem("token");
    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    fetch("http://localhost:8080/api/payments", {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    .then(res => res.json())
    .then(pagos => {
        const pagoFiltrado = pagos.filter(p => p.id.toString() === idBuscado);
        if (pagoFiltrado.length === 0) {
            alert("No se encontró ninguna factura con ese ID.");
            limpiarTabla();
        } else {
            cargarTabla(pagoFiltrado);
        }
    })
    .catch(err => {
        console.error("Error al filtrar:", err);
        alert("Error al buscar la factura.");
    });
}

function verificarEnter(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        filtrarPorNombre();
    }
}