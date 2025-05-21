async function obtenerHerramienta() {
    try{
      const respuesta = await fetch('http://localhost:8080/api/tools');
        const datos = await respuesta.json();
        mostrarHerramientas(datos);
    }catch(error){
        console.error("error al obtener herramientas",error);
    }
    

}

obtenerHerramienta();