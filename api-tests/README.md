# Pruebas de API REST para Inventario SaaS

Esta carpeta contiene archivos `.http` estándar para probar todos los controladores y endpoints de tu backend en orden secuencial.

## 📁 Archivos creados:
1. `01-empresa.http`: Pruebas para Crear (`POST /empresa`), Consultar (`GET /empresa/{id}`) y Actualizar (`PUT /empresa/{id}`).
2. `02-empleado.http`: Pruebas para Crear (`POST /empleados/{id_empresa}`), Consultar (`GET /empleados/{id_empresa}/{dni}`) y Editar (`PUT /empleados/{id_empresa}/{dni}`).
3. `03-producto.http`: Pruebas para Crear (`POST /productos/{id_empresa}`), Listar (`GET /productos/{id_empresa}`), Buscar (`GET /productos/{id_empresa}/{id_producto}`) y Actualizar (`PUT /productos/{id_empresa}/{id_producto}`).
4. `04-venta.http`: Pruebas para Registrar Ventas (`POST /ventas/{id_empresa}/{dniEmpleado}`) con casos de éxito y manejo de errores (stock insuficiente, producto inexistente, cantidad <= 0, empleado ajeno).

## 🔄 Orden Sugerido de Ejecución:
1. Ejecuta primero `01-empresa.http` (Crea la Empresa ID `1`).
2. Ejecuta `02-empleado.http` (Crea el Empleado DNI `71234567` en Empresa `1`).
3. Ejecuta `03-producto.http` (Crea los Productos ID `1` y `2` en Empresa `1`).
4. Ejecuta `04-venta.http` (Registra la Venta conectando Empresa `1` y Empleado `71234567`).

## 🚀 Cómo ejecutarlos:

### Opción 1: En IntelliJ IDEA (Recomendado)
- Abre cualquier archivo `.http` en IntelliJ.
- Verás un icono de Play verde (▶️) al lado de cada petición.
- Inicia tu aplicación Spring Boot (`InicioAplicacion.java`) y haz clic en el botón de Play (▶️) para enviar la petición directamente desde el IDE.

### Opción 2: En VSCode
- Instala la extensión gratuita **REST Client**.
- Abre cualquier archivo `.http` y haz clic en `Send Request` arriba de cada endpoint.

### Opción 3: En Postman
- Puedes importar estos archivos `.http` directamente en Postman usando `File -> Import`.

