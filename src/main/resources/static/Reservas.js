async function obtenerReservas() {
    try{
      const respuesta = await fetch('http://localhost:8080/api/tools');
        const datos = await respuesta.json();
        mostrarReserva(datos);
    }catch(error){
        console.error("error al obtener reservas",error);
    }
    

}

obtenerReservas();