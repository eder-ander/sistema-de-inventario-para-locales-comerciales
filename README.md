# 🏪 Sistema de Inventario para Locales Comerciales — Backend

API REST desarrollada con **Spring Boot 3**, **Spring Security** y **JWT** para gestionar empresas, empleados, inventario y ventas en un modelo **SaaS multi-empresa**.

---

## 🛠️ Stack Tecnológico

| Tecnología | Versión | Uso |
| :--- | :--- | :--- |
| Java | 21 | Lenguaje principal |
| Spring Boot | 3.4.3 | Framework backend |
| Spring Security | 6 | Autenticación y autorización |
| JWT (jjwt) | 0.12.7 | Tokens de acceso |
| Spring Data JPA | - | Acceso a datos |
| H2 Database | - | Base de datos en desarrollo |
| Lombok | 1.18.34 | Reducción de boilerplate |
| BCrypt | - | Encriptación de contraseñas |

---

## 🚀 Cómo Ejecutar el Proyecto

### 1. Prerrequisitos
- Java 21 instalado
- Maven instalado

### 2. Variables de Entorno Requeridas

Antes de arrancar, configura la variable de entorno del secreto JWT:

```bash
# Linux / Mac
export JWT_SECRET=mi_clave_super_secreta_de_al_menos_32_caracteres

# Windows (PowerShell)
$env:JWT_SECRET="mi_clave_super_secreta_de_al_menos_32_caracteres"
```

### 3. Arrancar la Aplicación

```bash
./mvnw spring-boot:run
```

El servidor arrancará en `http://localhost:8080`.

### 4. Usuario Inicial (SuperAdmin)

Al arrancar por primera vez con la base de datos vacía, se crea automáticamente un usuario SuperAdmin inicial:

| Campo | Valor |
| :--- | :--- |
| **username** | `admin` |
| **password** | `admin123` |
| **rol** | `SUPERADMIN` |

> ⚠️ Cambia esta contraseña en `InicioAplicacion.java` antes de desplegar a producción.

### 5. Consola H2 (Desarrollo)

Accede a la base de datos en memoria en: `http://localhost:8080/h2-console`

| Campo | Valor |
| :--- | :--- |
| JDBC URL | `jdbc:h2:mem:productosdb` |
| User | `sa` |
| Password | `password` |

---

## 🔐 Autenticación

La API usa **JWT Bearer Token**. Para autenticarte:

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

**Respuesta:**
```json
{
  "username": "admin",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "rol": "ROLE_SUPERADMIN"
}
```

Incluye el token en todas las peticiones protegidas:
```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 👥 Roles y Permisos

El sistema tiene **3 roles** con distintos niveles de acceso:

| Rol | Descripción | Scope |
| :--- | :--- | :--- |
| `SUPERADMIN` | Administrador de la plataforma SaaS | Global (todas las empresas) |
| `ADMIN` | Dueño / Gerente del local comercial | Solo su empresa |
| `EMPLEADO` | Cajero / Vendedor | Solo operaciones de venta de su local |

---

## 📋 Referencia de Endpoints

### 🔓 Públicos (Sin token)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Iniciar sesión y obtener JWT |

---

### 🏢 Empresas — `/api/empresas`

| Método | Endpoint | Roles | Descripción |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/empresas` | `SUPERADMIN` | Crear nueva empresa cliente |
| `GET` | `/api/empresas/mi-empresa` | Cualquier autenticado | Ver datos de tu propia empresa (extraída del JWT) |
| `PUT` | `/api/empresas/{id_empresa}` | `SUPERADMIN` | Actualizar datos de una empresa |

---

### 👔 Empleados — `/api/empleados`

> ⚠️ Un `ADMIN` solo puede gestionar empleados de **su propia empresa** (validado automáticamente con el JWT).

| Método | Endpoint | Roles | Descripción |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/empleados/{id_empresa}` | `SUPERADMIN` / `ADMIN` (solo su empresa) | Crear nuevo empleado |
| `GET` | `/api/empleados/{dni}` | `SUPERADMIN` / `ADMIN` | Ver datos de un empleado (empresa extraída del JWT) |
| `PUT` | `/api/empleados/{dni}` | `SUPERADMIN` / `ADMIN` | Editar datos de un empleado |

**Contraseña inicial:** Al crear un empleado, su contraseña inicial es su propio DNI (ya hasheada con BCrypt).

---

### 📦 Productos — `/api/productos`

> ⚠️ La empresa se extrae **automáticamente del JWT**. No se necesita enviar `id_empresa` en la URL.

| Método | Endpoint | Roles | Descripción |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/productos` | `ADMIN` / `EMPLEADO` | Crear nuevo producto |
| `GET` | `/api/productos/{id_producto}` | `ADMIN` / `EMPLEADO` | Buscar producto por ID |
| `GET` | `/api/productos` | `ADMIN` / `EMPLEADO` | Listar todos los productos de mi empresa |
| `PUT` | `/api/productos/{id_producto}` | `ADMIN` / `EMPLEADO` | Actualizar precio, nombre o stock |

**Formato de precios:** Los precios usan `BigDecimal` con 2 decimales. Enviar con punto como separador decimal (ej: `12.50`).

---

### 🛒 Ventas — `/api/ventas`

> ⚠️ La empresa y el DNI del empleado se extraen **automáticamente del JWT**. No se necesitan PathVariables.

| Método | Endpoint | Roles | Descripción |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/ventas` | `ADMIN` / `EMPLEADO` | Registrar una nueva venta (descuenta stock automáticamente) |

**Body de Venta:**
```json
{
  "productos": [
    { "productoId": 1, "cantidad": 2 },
    { "productoId": 3, "cantidad": 1 }
  ]
}
```

---

## 📁 Estructura del Proyecto

```
src/main/java/intisoft2025/practica/
├── config/
│   └── SecurityConfig.java         # Configuración de Spring Security, CORS y JWT
├── controller/
│   ├── AuthController.java         # POST /api/auth/login
│   ├── EmpresaController.java      # CRUD Empresas
│   ├── EmpleadoController.java     # CRUD Empleados
│   ├── ProductoController.java     # CRUD Productos (inventario)
│   └── VentaController.java        # Registro de Ventas
├── dto/                            # Objetos de transferencia de datos
├── exception/                      # Manejo global de errores (GlobalExceptionHandler)
├── jwt/
│   ├── JwtService.java             # Generación y validación de tokens JWT
│   └── JwtAuthenticationFilter.java # Filtro de autenticación por request
├── model/                          # Entidades JPA (Empresa, Empleado, Producto, Venta, DetalleVenta)
├── repository/                     # Interfaces JPA
├── security/
│   └── CustomUserDetails.java      # Implementación personalizada de UserDetails
├── service/                        # Interfaces e implementaciones de lógica de negocio
└── InicioAplicacion.java           # Main + CommandLineRunner para datos iniciales

api-tests/                          # Colección de pruebas HTTP (IntelliJ / VSCode REST Client)
├── 00-auth.http                    # Login con los 3 roles
├── 01-empresa.http                 # Pruebas de Empresa
├── 02-empleado.http                # Pruebas de Empleado
├── 03-producto.http                # Pruebas de Producto
└── 04-venta.http                   # Pruebas de Venta
```

---

## 📐 Formato de Respuesta Estándar

Todas las respuestas siguen la misma estructura:

```json
{
  "success": true,
  "mensaje": "Operación exitosa",
  "data": { }
}
```

En caso de error:
```json
{
  "success": false,
  "mensaje": "Stock insuficiente para: Galletas Oreo",
  "data": null
}
```

---

## 🔒 Seguridad Multi-Empresa (Multi-Tenant)

Cada operación valida que los recursos (productos, empleados, ventas) pertenezcan a la empresa del usuario autenticado. Un `ADMIN` de la Empresa A **nunca puede** ver ni modificar datos de la Empresa B, aunque intente manipular los parámetros de la URL.
