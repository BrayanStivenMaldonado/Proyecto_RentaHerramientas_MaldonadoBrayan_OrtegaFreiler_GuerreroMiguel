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