async function obtenerReservas() {
    try{
      const respuesta = await fetch('http://localhost:8080/api/tools');
        const datos = await respuesta.json();
        mostrarReserva(datos);
    }catch(error){
        console.error("error al obtener reservas",error);
    }
    

}

obtenerReservas();

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