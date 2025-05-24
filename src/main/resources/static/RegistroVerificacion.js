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

function togglePasswordVisibility2() {
    const passwordField = document.getElementById("password2");
    const toggleButton = document.getElementById("togglePassword");
    
    if (passwordField.type === "password") {
        passwordField.type = "text";
        toggleButton.textContent = "Hide Password";
    } else {
        passwordField.type = "password";
        toggleButton.textContent = "Show Password";
    }
}

document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('form');

  form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const nombre = form.querySelector('input[placeholder="Nombre"]').value.trim();
    const apellido = form.querySelector('input[placeholder="Apellido"]').value.trim();
    const email = form.querySelector('input[type="email"]').value.trim();
    const password = form.querySelector('#password').value.trim();
    const password2 = form.querySelector('#password2').value.trim();

    if (password !== password2) {
      alert('Las contraseñas no coinciden');
      return;
    }

    const data = {
      firstname: nombre,
      lastname: apellido,
      username: email,
      password: password,
      role: "USER"
    };

    try {
      const response = await fetch('http://localhost:8080/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

      const result = await response.text();

      if (response.ok) {
        alert('Registro exitoso. Ahora puedes iniciar sesión.');
        window.location.href = 'Login.html';
      } else {
        alert('Error al registrarse: ' + result);
      }
    } catch (error) {
      console.error('Error en el registro:', error);
      alert('Ocurrió un error al intentar registrarse.');
    }
  });
});
