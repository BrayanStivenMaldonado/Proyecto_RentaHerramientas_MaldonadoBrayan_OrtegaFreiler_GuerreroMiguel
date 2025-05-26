# 🔧 Sistema de Alquiler de Herramientas

Proyecto backend desarrollado en **Java 21** con **Spring Boot**, enfocado en la gestión de reservas, entregas y pagos para el alquiler de herramientas. El sistema incluye autenticación segura mediante JWT, roles diferenciados (admin y usuario), documentación Swagger y una base de datos relacional en PostgreSQL.

---

## 🚀 Tecnologías Utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)
![WebSockets](https://img.shields.io/badge/WebSockets-ffa500?style=for-the-badge)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Figma](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)
![GitFlow](https://img.shields.io/badge/GitFlow-6C2E91?style=for-the-badge&logo=git&logoColor=white)

---

## 📥 Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/BrayanStivenMaldonado/Proyecto_RentaHerramientas_MaldonadoBrayan_OrtegaFreiler_GuerreroMiguel.git
cd Proyecto_RentaHerramientas_MaldonadoBrayan_OrtegaFreiler_GuerreroMiguel
```

### 2. Instalar dependencias
```bash
mvn clean install
```
### 3. Configuración de la base de datos
- La base de datos se crea automáticamente al iniciar la aplicación gracias a la configuración de JPA/Hibernate.

- Los datos iniciales están en el archivo src/main/resources/data/inserts.sql.

- Para cargar estos datos, abre pgAdmin (u otra herramienta PostgreSQL), conecta a la base de datos alquiler_herramientas y ejecuta el script inserts.sql.

### 4. Configuración del archivo `application.properties`

Configura el archivo `src/main/resources/application.properties` con los datos de conexión a tu base de datos y configuración JPA:

```properties
spring.application.name=alquiler_app

# Autenticación
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/alquiler_app
spring.sql.init.encoding=UTF-8
spring.datasource.username=postgres
spring.datasource.password=yourPassword
spring.datasource.driver-class-name=org.postgresql.Driver

# Setup JPA
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.sql.init.mode=update
```

### 5. Ejecutar la aplicación

Puedes iniciar la aplicación backend de dos formas:

- **Desde Visual Studio Code** usando la extensión **Spring Boot Dashboard** o alguna similar que permita correr aplicaciones Spring Boot fácilmente.

- **Desde la terminal** con Maven, ejecuta:

```bash
mvn spring-boot:run
```

### 6. Usar el frontend
- La interfaz de usuario está desarrollada con archivos HTML, CSS y JavaScript que se encuentran en el proyecto.

- Para probar la aplicación, abre el archivo login.html usando la extensión Live Server en Visual Studio Code o cualquier servidor local.

- Esto permitirá que el frontend interactúe correctamente con el backend y puedas ver la funcionalidad completa de la plataforma.

### Requisitos previos
- Tener instalado Java 17 (JDK 17).

- Tener PostgreSQL instalado y en ejecución.

- Tener Visual Studio Code con la extensión Live Server para abrir el frontend.

- Contar con el archivo src/main/resources/data/inserts.sql para cargar los datos iniciales en la base de datos a través de pgAdmin o herramienta similar.

# Diagrama relacional de la base de datos

![Untitled](https://github.com/user-attachments/assets/868620ef-690f-4027-ab91-4f967c73ba91)
