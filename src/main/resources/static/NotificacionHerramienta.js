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

const imageUrlInput = document.getElementById("imageUrlInput");
const preview = document.getElementById("preview");

imageUrlInput.addEventListener("input", () => {
    preview.src = imageUrlInput.value || "../media/Imagen Default.png";
});

document.getElementById('addToolForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const toolName = document.getElementById('toolName').value;
    const toolType = document.getElementById('toolType').value;
    const toolQuantity = document.getElementById('toolQuantity').value;

    console.log(`Herramienta añadida: ${toolName}, Tipo: ${toolType}, Cantidad: ${toolQuantity}`);

    document.getElementById('addToolForm').reset();
});
