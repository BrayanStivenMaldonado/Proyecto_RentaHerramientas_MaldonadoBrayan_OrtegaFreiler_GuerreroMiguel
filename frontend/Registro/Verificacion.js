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
