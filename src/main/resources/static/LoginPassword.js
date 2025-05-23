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

// const boton = document.getElementById("botonI").addEventListener("click", loginUser);

// async function loginUser(username, password) {
//     const response = await fetch('http://localhost:8080/auth/login', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify({
//             username: username,
//             password: password
//         })
//     });

//     if (!response.ok) {
//         throw new Error('Login fallido');
//     }

//     const data = await response.json();
//     localStorage.setItem('token', data.token); // Guarda el token JWT en localStorage
//     console.log('Token:', data.token);
// }