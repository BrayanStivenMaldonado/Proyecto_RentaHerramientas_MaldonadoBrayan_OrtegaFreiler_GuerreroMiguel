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

