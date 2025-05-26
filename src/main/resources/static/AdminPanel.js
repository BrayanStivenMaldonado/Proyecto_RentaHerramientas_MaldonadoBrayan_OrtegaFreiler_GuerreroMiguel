document.addEventListener("DOMContentLoaded", () => {
  const tipoSelect = document.getElementById("tipo");
  const tbody = document.querySelector("tbody");
  const adminNameSpan = document.getElementById("admin-name");
  const API_URL = "http://localhost:8080/api/persons";
  const REGISTER_URL = "http://localhost:8080/auth/register";

  const TOKEN = localStorage.getItem("token");
  const userData = localStorage.getItem("user");

  if (!TOKEN) {
    alert("No se encontró el token de autenticación. Por favor inicia sesión.");
    return;
  }

  if (userData) {
    const user = JSON.parse(userData);
    adminNameSpan.textContent = `Admin: ${user.firstName} ${user.lastName}`;
  } else {
    console.warn("No se encontró usuario en localStorage");
  }

  tipoSelect.addEventListener("change", () => {
    const filtro = tipoSelect.value;
    let rol;

    switch (filtro) {
      case "cliente":
        rol = "USER";
        break;
      case "proveedor":
        rol = "PROVIDER";
        break;
      case "admin":
        rol = "ADMIN";
        break;
      default:
        rol = null;
    }

    if (rol) {
      cargarDatos(rol);
    }
  });

  async function cargarDatos(rol) {
    try {
      const response = await fetch(API_URL, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${TOKEN}`,
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        throw new Error(`Error en la solicitud: ${response.status}`);
      }

      const data = await response.json();

      const filtrados = data.filter((persona) => persona.role === rol);

      tbody.innerHTML = "";
      filtrados.forEach((persona) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
          <td>${persona.id}</td>
          <td>${persona.firstName} ${persona.lastName}</td>
          <td>${persona.username}</td>
          <td>${persona.role}</td>
          <td></td>
        `;
        tbody.appendChild(tr);
      });

      if (filtrados.length === 0) {
        tbody.innerHTML =
          "<tr><td colspan='5'>No se encontraron usuarios con ese rol</td></tr>";
      }
    } catch (error) {
      console.error("Error al cargar los datos:", error);
      tbody.innerHTML =
        "<tr><td colspan='5'>Error al cargar los datos</td></tr>";
    }
  }
  const form = document.getElementById("registerForm");
  if (form) {
    form.addEventListener("submit", async (event) => {
      event.preventDefault();

      const nombre = form.nombre.value.trim();
      const apellido = form.apellido.value.trim();
      const correo = form.correo.value.trim();
      const contrasena = form.contrasena.value.trim();
      const rol = form.rol.value;

      if (!nombre || !apellido || !correo || !contrasena || !rol) {
        alert("Por favor, complete todos los campos.");
        return;
      }

      const data = {
        firstname: nombre,
        lastname: apellido,
        username: correo,
        password: contrasena,
        role: rol,
      };

      try {
        const response = await fetch(REGISTER_URL, {
          method: "POST",
          headers: {
            Authorization: `Bearer ${TOKEN}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(data),
        });

        const result = await response.text();

        if (response.ok) {
          alert("Registro exitoso.");
          const filtro = tipoSelect.value;
          let rolFiltro;
          switch (filtro) {
            case "cliente":
              rolFiltro = "USER";
              break;
            case "proveedor":
              rolFiltro = "PROVIDER";
              break;
            case "admin":
              rolFiltro = "ADMIN";
              break;
            default:
              rolFiltro = null;
          }
          if (rolFiltro) {
            cargarDatos(rolFiltro);
          }

          form.reset();
        } else {
          alert("Error al registrarse: " + result);
        }
      } catch (error) {
        console.error("Error en el registro:", error);
        alert("Ocurrió un error al intentar registrarse.");
      }
    });
  }
  tipoSelect.dispatchEvent(new Event("change"));

  const updateForm = document.getElementById("updateForm");
if (updateForm) {
  updateForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("update-id").value.trim();
    const firstName = document.getElementById("update-nombre").value.trim();
    const lastName = document.getElementById("update-apellido").value.trim();
    const username = document.getElementById("update-correo").value.trim();
    const password = document.getElementById("update-contrasena").value.trim();
    const role = document.getElementById("update-rol").value;

    if (!id || !firstName || !lastName || !username || !password || !role) {
      alert("Por favor, complete todos los campos.");
      return;
    }

    const updateData = {
      firstName: firstName,
      lastName: lastName,
      username: username,
      password: password,
      role: role,
    };

    console.log(updateData)

    try {
      const response = await fetch(`http://localhost:8080/api/persons/${id}`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${TOKEN}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(updateData),
      });

      if (response.ok) {
        const result = await response.json();
        alert("Persona actualizada con éxito.");
        console.log("Respuesta de actualización:", result);
        updateForm.reset();

        const filtro = tipoSelect.value;
        if (filtro) {
          tipoSelect.dispatchEvent(new Event("change"));
        }
      } else {
        const errorText = await response.text();
        alert("Error al actualizar: " + errorText);
      }
    } catch (error) {
      console.error("Error al actualizar persona:", error);
      alert("Ocurrió un error al intentar actualizar la persona.");
    }
  });
}
});
