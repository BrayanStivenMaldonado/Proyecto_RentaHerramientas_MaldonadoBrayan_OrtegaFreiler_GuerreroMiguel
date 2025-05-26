
const productosCarrito = document.getElementById('productos-carrito');
const totalCarrito = document.getElementById('total-carrito');

function mostrarCarrito() {
  
  const carrito = JSON.parse(localStorage.getItem('carrito')) || [];

  
  productosCarrito.innerHTML = '';

  
  let total = 0;

  carrito.forEach(producto => {
    
    const productoDiv = document.createElement('div');
    productoDiv.classList.add('producto-carrito');

    
    const productoImagen = document.createElement('img');
    productoImagen.src = producto.imagen || 'imagen-placeholder.png'; 
    productoImagen.alt = producto.nombre;
    
    
    const infoProducto = document.createElement('div');
    infoProducto.classList.add('info');
    const nombreProducto = document.createElement('h3');
    nombreProducto.textContent = producto.nombre;
    const precioProducto = document.createElement('p');
    precioProducto.textContent = `$${producto.precio} por día`;
    const cantidadProducto = document.createElement('p');
    cantidadProducto.textContent = `Cantidad: ${producto.cantidad}`;

    // Agregar los elementos de información al contenedor
    infoProducto.appendChild(nombreProducto);
    infoProducto.appendChild(precioProducto);
    infoProducto.appendChild(cantidadProducto);

    // Crear la sección de cantidad (si es necesario editarla o eliminar)
    const cantidadDiv = document.createElement('div');
    cantidadDiv.classList.add('cantidad');
    const btnDisminuir = document.createElement('button');
    btnDisminuir.textContent = '-';
    const btnAumentar = document.createElement('button');
    btnAumentar.textContent = '+';
    cantidadDiv.appendChild(btnDisminuir);
    cantidadDiv.appendChild(btnAumentar);

    // Funciones para aumentar y disminuir la cantidad de productos
    btnAumentar.addEventListener('click', () => actualizarCantidad(producto, 1));
    btnDisminuir.addEventListener('click', () => actualizarCantidad(producto, -1));

    // Agregar todo al contenedor del producto
    productoDiv.appendChild(productoImagen);
    productoDiv.appendChild(infoProducto);
    productoDiv.appendChild(cantidadDiv);

  
    productosCarrito.appendChild(productoDiv);

    
    total += producto.precio * producto.cantidad;
  });

  totalCarrito.textContent = `$${total.toFixed(2)}`;
}


function actualizarCantidad(producto, cantidad) {

  const carrito = JSON.parse(localStorage.getItem('carrito')) || [];

  const productoIndex = carrito.findIndex(item => item.nombre === producto.nombre);

  if (productoIndex !== -1) {
   
    carrito[productoIndex].cantidad += cantidad;

    
    if (carrito[productoIndex].cantidad <= 0) {
      carrito.splice(productoIndex, 1);
    }

   
    localStorage.setItem('carrito', JSON.stringify(carrito));

    
    mostrarCarrito();
  }
}


document.addEventListener('DOMContentLoaded', mostrarCarrito);
