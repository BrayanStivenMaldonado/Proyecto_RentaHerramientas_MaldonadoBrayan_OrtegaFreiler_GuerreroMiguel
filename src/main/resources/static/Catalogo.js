document.addEventListener('DOMContentLoaded', () => {
  const productosContainer = document.querySelector('.productos');
  const token = localStorage.getItem('token');

  fetch('http://localhost:8080/api/tools', {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('No se pudo obtener las herramientas');
      }
      return response.json();
    })
    .then(data => {
      productosContainer.innerHTML = '';
      data.forEach(tool => {
        const producto = document.createElement('div');
        producto.classList.add('producto');

        producto.innerHTML = `
          <div>
            <a href="Herramientas.html" class="Link_Herramientas">
              <img src="../media/${tool.image}" alt="${tool.toolName}">
            </a>
          </div>
          <div>${tool.toolName}</div>
          <div>$${tool.rentalPrice} por día</div>
        `;

        productosContainer.appendChild(producto);
      });
    })
    .catch(error => {
      console.error('Error al cargar herramientas:', error);
      productosContainer.innerHTML = '<p>Error al cargar herramientas.</p>';
    });
});

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