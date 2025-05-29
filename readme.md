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

## 📄 Documentación de la API (Swagger)

La documentación interactiva de los endpoints está disponible gracias a Swagger UI. Desde allí puedes probar cada ruta, enviar peticiones y ver respuestas en tiempo real.

🔗 **Accede a Swagger UI aquí**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

> Asegúrate de que la aplicación esté en ejecución para acceder a la interfaz Swagger.

[![Swagger UI](https://img.shields.io/badge/API-Swagger_UI-green?logo=swagger)](http://localhost:8080/swagger-ui/index.html)

## 📄 Documentación de Endpoints de la API

### 🔐 Autenticación

> ⚠️ Todos los endpoints que requieran autenticación deben incluir el siguiente header:

```
Authorization: Bearer <JWT>
```

---

### 💳 Pagos - `/api/payments`

#### `GET /api/payments`

* **Descripción:** Obtiene todos los pagos registrados.
* **Headers:** Authorization
* **Response:**

```json
[
  {
    "id": 1,
    "paymentMethod": "Tarjeta",
    "date": "2025-05-01",
    "subtotal": 100.0,
    "total": 110.0,
    "reservation": {
      "id": 1,
      "rentalDate": "2025-05-01",
      "returnDate": "2025-05-03"
    }
  }
]
```

#### `POST /api/payments`

* **Descripción:** Crea un nuevo pago.
* **Headers:** Authorization
* **Request Body:**

```json
{
  "paymentMethod": "Tarjeta",
  "date": "2025-05-01",
  "subtotal": 100.0,
  "total": 110.0,
  "reservation": { "id": 1 }
}
```

* **Response:** Pago creado con los datos enviados.

#### `GET /api/payments/{id}`

* **Descripción:** Obtiene un pago por su ID.
* **Headers:** Authorization
* **Response:**

```json
{
  "id": 1,
  "paymentMethod": "Tarjeta",
  "date": "2025-05-01",
  "subtotal": 100.0,
  "total": 110.0,
  "reservation": {
    "id": 1,
    "rentalDate": "2025-05-01",
    "returnDate": "2025-05-03"
  }
}
```

#### `PUT /api/payments/{id}`

* **Descripción:** Actualiza un pago existente.
* **Headers:** Authorization
* **Request Body:** (igual que en `POST`)
* **Response:** Pago actualizado.

#### `DELETE /api/payments/{id}`

* **Descripción:** Elimina un pago por ID.
* **Headers:** Authorization
* **Response:** `204 No Content`

---

### 👤 Personas - `/api/persons`

#### `GET /api/persons`

* **Descripción:** Retorna todas las personas registradas.
* **Headers:** Authorization
* **Response:**

```json
[
  {
    "id": 1,
    "name": "Juan Pérez",
    "email": "juan@mail.com",
    "roles": [
      {
        "id": 1,
        "name": "ROLE_USER"
      }
    ]
  }
]
```

#### `GET /api/persons/{id}`

* **Descripción:** Obtiene una persona por ID.
* **Headers:** Authorization
* **Response:**

```json
{
  "id": 1,
  "name": "Juan Pérez",
  "email": "juan@mail.com",
  "roles": [
    {
      "id": 1,
      "name": "ROLE_USER"
    }
  ]
}
```

#### `POST /api/persons`

* **Descripción:** Crea una nueva persona.
* **Headers:** Authorization
* **Request:**

```json
{
  "name": "Juan Pérez",
  "email": "juan@mail.com",
  "password": "1234",
  "roles": [
    {
      "id": 1
    }
  ]
}
```

#### `PUT /api/persons/{id}`

* **Descripción:** Actualiza la información de una persona.
* **Headers:** Authorization
* **Request:**

```json
{
  "name": "Juan Pérez",
  "email": "nuevo@mail.com"
}
```

#### `DELETE /api/persons/{id}`

* **Descripción:** Elimina una persona por ID.
* **Headers:** Authorization

---

### 📅 Reservas - `/api/reservation`

#### `GET /api/reservation`

* **Descripción:** Lista todas las reservas realizadas.
* **Headers:** Authorization

#### `GET /api/reservation/{id}`

* **Descripción:** Retorna los detalles de una reserva por su ID.
* **Headers:** Authorization

#### `POST /api/reservation`

* **Descripción:** Crea una nueva reserva.
* **Headers:** Authorization
* **Request:**

```json
{
  "rentalDate": "2024-05-25",
  "returnDate": "2024-05-30",
  "user": {
    "id": 1
  }
}
```

#### `PUT /api/reservation/{id}`

* **Descripción:** Actualiza una reserva existente.
* **Headers:** Authorization
* **Request:** Igual que el POST.

#### `DELETE /api/reservation/{id}`

* **Descripción:** Elimina una reserva por ID.
* **Headers:** Authorization

---

### 🔁 Devoluciones - `/api/returns`

#### `GET /api/returns`

* **Descripción:** Retorna todas las devoluciones registradas.
* **Headers:** Authorization

#### `POST /api/returns`

* **Descripción:** Procesa una devolución con archivos adjuntos.

* **Headers:** Authorization

* **Request (multipart/form-data):**

  * `reservationId`: ID de la reserva
  * `condition`: Estado de la herramienta devuelta
  * `purchaseDate`: Fecha del alquiler
  * `proofFile`: Comprobante de pago (archivo)
  * `productImage`: Imagen del producto (archivo)

* **Respuesta:**

```text
Devolución registrada correctamente
```

---

### 🛠️ Herramientas - `/api/tools`

#### `GET /api/tools`

* **Descripción:** Lista todas las herramientas disponibles.
* **Headers:** Authorization

#### `GET /api/tools/{id}`

* **Descripción:** Obtiene una herramienta específica por ID.
* **Headers:** Authorization

#### `POST /api/tools`

* **Descripción:** Crea una nueva herramienta.
* **Headers:** Authorization
* **Request:**

```json
{
  "toolName": "Martillo",
  "image": "url_imagen.jpg",
  "usage": "Construcción",
  "rentalPrice": 20.0,
  "replacementPrice": 50.0,
  "quantity": 10
}
```

#### `PUT /api/tools/{id}`

* **Descripción:** Actualiza los datos de una herramienta existente.
* **Headers:** Authorization
* **Request:** Igual que el POST.

#### `DELETE /api/tools/{id}`

* **Descripción:** Elimina una herramienta por ID.
* **Headers:** Authorization

# 🔐 Autenticación y Roles

- El sistema implementa seguridad basada en **JWT (JSON Web Tokens)** para autenticar y autorizar a los usuarios.

- Tras iniciar sesión con credenciales válidas, el backend genera un token JWT que debe ser incluido en todas las solicitudes a endpoints protegidos.

#### 🔑 Cómo utilizar el token

Incluye el token en el encabezado de la solicitud HTTP:

```
Authorization: Bearer <JWT>
```

#### 👥 Roles del sistema

El sistema define tres roles con permisos diferenciados:

| Rol        | Descripción                                                                   |
| ---------- | ----------------------------------------------------------------------------- |
| `USER`     | Usuario final. Puede consultar herramientas, crear reservas y realizar pagos. |
| `PROVIDER` | Proveedor. Puede registrar y administrar herramientas.                        |
| `ADMIN`    | Administrador. Acceso completo a todos los recursos del sistema.              |
---
