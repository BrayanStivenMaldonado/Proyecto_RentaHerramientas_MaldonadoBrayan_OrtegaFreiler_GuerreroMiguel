document.addEventListener("DOMContentLoaded", () => {
  const productosContainer = document.querySelector(".productos");
  const token = localStorage.getItem("token");
  const Dem = document.getElementById("Dem");
  const Tra = document.getElementById("Tra");
  const Obr = document.getElementById("Obr");
  const Her = document.getElementById("Her");
  const Gen = document.getElementById("Gen");
  let herramientas = [];

  fetch("http://localhost:8080/api/tools", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("No se pudo obtener las herramientas");
      }
      return response.json();
    })
    .then((data) => {
      herramientas = data;
      renderTools(herramientas);
      productosContainer.innerHTML = "";
      data.forEach((tool) => {
        const producto = document.createElement("div");
        producto.classList.add("producto");

        producto.innerHTML = `
          <div>
            <a href="Detalleprodu.html" class="Link_Herramientas">
              <img src="${tool.image}" alt="${tool.toolName}">
            </a>
          </div>
          <div>${tool.toolName}</div>
          <div>$${tool.rentalPrice} por día</div>
        `;

        productosContainer.appendChild(producto);
      });
    })
    .catch((error) => {
      console.error("Error al cargar herramientas:", error);
      productosContainer.innerHTML = "<p>Error al cargar herramientas.</p>";
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

  function renderTools(tools) {
    productosContainer.innerHTML = "";
    tools.forEach((tool) => {
      const producto = document.createElement("div");
      producto.classList.add("producto");
      producto.innerHTML = `
      <div>
        <a href="Herramientas.html" class="Link_Herramientas">
          <img src="${tool.image}" alt="${tool.toolName}">
        </a>
      </div>
      <div>${tool.toolName}</div>
      <div>$${tool.rentalPrice} por día</div>
    `;
      productosContainer.appendChild(producto);
    });
  }

  Dem.addEventListener("click", () => {
    Dem.style.color = "white";
    Tra.style.color = "black";
    Obr.style.color = "black";
    Her.style.color = "black";
    Gen.style.color = "black";
    const filtrado = herramientas.filter(
      (tool) => tool.category === "demolicion"
    );
    renderTools(filtrado);
  });

  Tra.addEventListener("click", () => {
    Tra.style.color = "white";
    Dem.style.color = "black";
    Obr.style.color = "black";
    Her.style.color = "black";
    Gen.style.color = "black";
    const filtrado = herramientas.filter(
      (tool) => tool.category === "trabajo en altura"
    );
    renderTools(filtrado);
  });

  Obr.addEventListener("click", () => {
    Obr.style.color = "white";
    Tra.style.color = "black";
    Dem.style.color = "black";
    Her.style.color = "black";
    Gen.style.color = "black";
    const filtrado = herramientas.filter(
      (tool) => tool.category === "obra gruesa"
    );
    renderTools(filtrado);
  });

  Her.addEventListener("click", () => {
    Tra.style.color = "black";
    Obr.style.color = "black";
    Dem.style.color = "black";
    Gen.style.color = "black";
    Her.style.color = "white";
    const filtrado = herramientas.filter(
      (tool) => tool.category === "herramientas electricas"
    );
    renderTools(filtrado);
  });

  Gen.addEventListener("click", () => {
    Tra.style.color = "black";
    Obr.style.color = "black";
    Her.style.color = "black";
    Gen.style.color = "white";
    Dem.style.color = "black";
    const filtrado = herramientas.filter(
      (tool) => tool.category === "generacion electrica"
    );
    renderTools(filtrado);
  });

  const buscador = document.getElementById("buscador");

  buscador?.addEventListener("input", (e) => {
    Tra.style.color = "black";
    Obr.style.color = "black";
    Her.style.color = "black";
    Gen.style.color = "black";
    Dem.style.color = "black";
    const texto = e.target.value.toLowerCase();
    const filtradas = herramientas.filter((tool) =>
      tool.toolName.toLowerCase().includes(texto)
    );
    renderTools(filtradas);
  });
});
