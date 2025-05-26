document.addEventListener("DOMContentLoaded", () => {
  // Cargar detalles del producto
  const producto = JSON.parse(localStorage.getItem("productoSeleccionado"));

  if (!producto) {
    document.querySelector(".detalle-producto").innerHTML = "<p>No se encontró información del producto.</p>";
    return;
  }

  document.getElementById("imagen-producto").src = producto.image;
  document.getElementById("nombre-producto").textContent = producto.toolName;
  document.getElementById("descripcion-producto").textContent = producto.description || "Sin descripción";
  document.getElementById("precio-producto").textContent = `$${producto.rentalPrice} por día`;
  document.getElementById("breadcrumb-nombre").textContent = producto.toolName;

  // Actualizar carrito al cargar la página
  actualizarCarrito();

  // Asignar evento al botón
  const btnCarrito = document.getElementById('btn-carrito');
  btnCarrito.addEventListener('click', () => agregarAlCarrito(producto));
});

function agregarAlCarrito(productoSeleccionado) {
  // Crear un objeto del producto con los detalles
  const producto = {
    nombre: productoSeleccionado.toolName,
    descripcion: productoSeleccionado.description,
    precio: productoSeleccionado.rentalPrice,
    cantidad: 1 // Inicializamos la cantidad en 1
  };

  // Obtener el carrito desde localStorage
  let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

  // Verificar si el producto ya está en el carrito
  const productoExistente = carrito.find(item => item.nombre === producto.nombre);

  if (productoExistente) {
    // Si ya existe, aumentar la cantidad
    productoExistente.cantidad += 1;
  } else {
    // Si no existe, agregarlo al carrito
    carrito.push(producto);
  }

  // Guardar el carrito actualizado en localStorage
  localStorage.setItem('carrito', JSON.stringify(carrito));

  alert(`${producto.nombre} ha sido agregado al carrito`);
  actualizarCarrito();
}

function actualizarCarrito() {
  const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
  const cantidadProductos = carrito.reduce((total, item) => total + item.cantidad, 0);

  const carritoIcono = document.querySelector('.nav-links a[href="carrito.html"]');
  if (carritoIcono) {
    carritoIcono.innerHTML = `Carrito (${cantidadProductos})`;
  }
}
