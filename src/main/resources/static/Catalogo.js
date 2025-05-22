const token = localStorage.getItem("token");

fetch('http://localhost:8080/api/tools', {
    headers: {
        'Authorization': 'Bearer ' + token
    }
});