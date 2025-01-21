# Foro Hub

Foro Hub es una aplicación basada en Spring Boot que permite gestionar tópicos, respuestas y cursos, implementando un sistema de autenticación de usuario con JWT (JSON Web Tokens). La aplicación permite crear, listar y buscar tópicos y cursos, así como agregar respuestas a los tópicos.

## Tecnologías Utilizadas

* Java 17
* Spring Boot 2.7.x
* Spring Security
* JWT (JSON Web Tokens)
* JPA (Java Persistence API)
* H2 Database (para pruebas)
* Maven
* Insomnia/Postman para pruebas

## Características Principales

* Registro y autenticación de usuarios con JWT.
* Gestión de tópicos y cursos.
* Creación de respuestas para los tópicos.
* Búsqueda y filtrado de tópicos por curso y fecha de creación.

## Instalación

### Requisitos

* Java 17 o superior.
* Maven 3.6 o superior.
* IDE como IntelliJ IDEA, Eclipse o Spring Tool Suite (STS).
* Base de datos H2 o MySQL (según configuración).

### Pasos para ejecutar el proyecto
1. Clonar el repositorio:

````bash
git clone https://github.com/tuusuario/forohub.git
````
2. Configurar la base de datos: Si usas una base de datos diferente a H2, configura los parámetros en src/main/resources/application.properties.

Para H2 (base de datos en memoria):
````bash
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
````
Compilar y ejecutar el proyecto: Ejecuta el siguiente comando en el directorio raíz del proyecto:
```bash
mvn spring-boot:run
```
Configura la URL de conexión como jdbc:h2:mem:testdb y usuario/contraseña como sa/password.

## Endpoints
### 1. Autenticación de Usuario
*    POST /forohub/auth/login
     Endpoint para iniciar sesión y obtener un token JWT.

Request Body:

```bash
json
{
"correoElectronico": "usuario@ejemplo.com",
"contrasena": "password"
}
```
Response:

```bash
json
{
"token": "jwt_token_aqui"
}
```

### 2. Tópicos
*    GET /forohub/topicos

Listar todos los tópicos filtrados por curso y año (opcional).

**Parámetros de consulta:**

* curso (opcional): Nombre del curso a filtrar.
* anio (opcional): Año de creación de los tópicos (formato yyyy).
* page (opcional): Página de resultados (por defecto 0).
* size (opcional): Número de resultados por página (por defecto 10).

**Response:**

```bash
json
[
  {
    "id": 1,
    "titulo": "Título del tópico",
    "mensaje": "Mensaje del tópico",
    "autorId": 1,
    "cursoId": 2,
    "fechaCreacion": "2025-01-20T02:30:00",
    "estado": "ACTIVO"
  }
]
```
* POST /forohub/topicos

Crear un nuevo tópico.

**Request Body:**
````bash
json
{
"titulo": "Nuevo Tópico",
"mensaje": "Este es el mensaje del tópico.",
"cursoId": 1
}
````
**Response**:
```bash
json
{
"id": 2,
"titulo": "Nuevo Tópico",
"mensaje": "Este es el mensaje del tópico.",
"autorId": 1,
"cursoId": 1,
"fechaCreacion": "2025-01-20T02:30:00",
"estado": "ACTIVO"
}
```
### 3. Usuarios
* GET /forohub/usuarios/{id}

Obtener información de un usuario por su ID.

**Response:**

```bash
json
{
"id": 1,
"nombre": "Usuario Nombre",
"correoElectronico": "usuario@ejemplo.com",
"perfilesIds": [1, 2]
}
```

## JWT y Seguridad

### Generación de Token JWT
Para generar un token JWT, simplemente realiza una solicitud POST al endpoint /forohub/auth/login con las credenciales correctas. El servidor devolverá un token JWT que debe ser incluido en el encabezado de las solicitudes que requieren autenticación.

### Autenticación en Endpoints Protegidos
Para acceder a los endpoints que requieren autenticación, agrega el token JWT al encabezado Authorization de la siguiente manera:

```bash
Authorization: Bearer <jwt_token_aqui>
```

