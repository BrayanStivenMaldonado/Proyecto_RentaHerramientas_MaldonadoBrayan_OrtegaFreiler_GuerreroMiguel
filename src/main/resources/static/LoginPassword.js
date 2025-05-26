function togglePasswordVisibility() {
  const passwordField = document.getElementById("password");
  const toggleButton = document.getElementById("togglePassword");

  if (passwordField.type === "password") {
    passwordField.type = "text";
    toggleButton.textContent = "Hide Password";
  } else {
    passwordField.type = "password";
    toggleButton.textContent = "Show Password";
  }
}

document
  .getElementById("loginForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        alert("Usuario o contraseña incorrectos.");
        return;
      }

      const data = await response.json();
      localStorage.setItem("token", data.token); // Guarda el token JWT
      localStorage.setItem(
        "user",
        JSON.stringify({
          firstName: data.firstname,
          lastName: data.lastname,
        })
      );
      console.log("Usuario guardado:", localStorage.getItem("user"));

      if (data.role === "ADMIN") {
        window.location.href = "AdminPanel.html";
      } else if (data.role === "USER") {
        window.location.href = "Catalogo.html";
      } else if (data.role === "PROVIDER") {
        window.location.href = "Inventario.html";
      }
    } catch (error) {
      console.error("Error al iniciar sesión:", error);
      alert("Ocurrió un error al iniciar sesión.");
    }
  });
