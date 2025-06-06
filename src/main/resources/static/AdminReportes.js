const chartData = [
  { mes: "Ene", valor: 20 },
  { mes: "Feb", valor: 50 },
  { mes: "Mar", valor: 90 },
  { mes: "Abr", valor: 70 },
];

const chartContainer = document.getElementById("barChart");
chartData.forEach((data) => {
  const bar = document.createElement("div");
  bar.className = "bar";
  bar.style.height = `${data.valor * 2}px`;
  bar.innerHTML = `<div>${data.mes}</div>`;
  chartContainer.appendChild(bar);
});

const paymentData = [
  { fecha: "2025-01-15", usuario: "Juan", monto: 70, metodo: "Tarjeta" },
  { fecha: "2025-02-12", usuario: "Ana", monto: 50, metodo: "Transferencia" },
  { fecha: "2025-03-10", usuario: "Luis", monto: 90, metodo: "Efectivo" },
];

const tbody = document.getElementById("paymentBody");
paymentData.forEach((pago) => {
  const tr = document.createElement("tr");
  tr.innerHTML = `
     <td>${pago.fecha}</td>
     <td>${pago.usuario}</td>
     <td>
       <div class="progress-bar">
         <div class="progress-fill" style="width: ${pago.monto}%;"></div>
       </div>
     </td>
     <td>${pago.metodo}</td>
   `;
  tbody.appendChild(tr);
});

document.addEventListener("DOMContentLoaded", () => {
  const token = localStorage.getItem("token");

  if (!token) {
    alert("No hay token de autorización. Por favor inicia sesión.");
    return;
  }

  fetch("http://localhost:8080/api/payments", {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al obtener pagos: " + response.status);
      }
      return response.json();
    })
    .then((payments) => {
      const tbody = document.getElementById("paymentBody");
      tbody.innerHTML = "";

      payments.forEach((payment) => {
        const tr = document.createElement("tr");

        tr.innerHTML = `
          <td>${payment.date}</td>
          <td>${payment.username}</td>
          <td>${payment.total.toFixed(2)}</td>
          <td>${payment.paymentMethod}</td>
        `;

        tbody.appendChild(tr);
      });
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("No se pudieron cargar los pagos.");
    });
});
