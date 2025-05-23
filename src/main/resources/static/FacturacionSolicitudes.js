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

document.addEventListener("DOMContentLoaded", () => {
  const estados = ["Pendiente", "Aceptado", "Rechazado"];
  const colores = ["pendiente", "aceptado", "rechazado"];

  const cambiarEstadoBtn = document.querySelector(".filtros button:last-child");
  const tarjetas = document.querySelectorAll(".tarjeta");

  cambiarEstadoBtn.addEventListener("click", () => {
    tarjetas.forEach((tarjeta) => {
      const estadoDiv = tarjeta.querySelector(".estado");

      let estadoActual = estados.find(estado =>
        estadoDiv.textContent.includes(estado)
      );

      let indexActual = estados.indexOf(estadoActual);
      let siguienteIndex = (indexActual + 1) % estados.length;

      estadoDiv.textContent = `ESTADO: ${estados[siguienteIndex]}`;

      colores.forEach(color => estadoDiv.classList.remove(color));
      estadoDiv.classList.add(colores[siguienteIndex]);
    });
  });
});
