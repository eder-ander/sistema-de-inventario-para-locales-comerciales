# Pruebas de API REST para Inventario SaaS

Esta carpeta contiene archivos `.http` estándar para probar todos los controladores y endpoints de tu backend.

## 📁 Archivos creados:
1. `01-empresa.http`: Pruebas para Crear, Consultar y Actualizar Empresas.
2. `02-empleado.http`: Pruebas para Crear, Consultar y Editar Empleados.
3. `03-producto.http`: Pruebas para Crear, Listar, Buscar y Actualizar Productos.
4. `04-venta.http`: Pruebas para Registrar Ventas (Casos Éxito, Stock Insuficiente y Errores).

## 🚀 Cómo ejecutarlos:

### Opción 1: En IntelliJ IDEA (Recomendado)
- Abre cualquier archivo `.http` en IntelliJ.
- Verás un icono de Play verde (▶️) al lado de cada petición.
- Inicia tu aplicación de Spring Boot (`PracticaApplication.java`) y haz clic en el botón de Play (▶️) para enviar la petición directamente desde el IDE.

### Opción 2: En VSCode
- Instala la extensión gratuita **REST Client**.
- Abre cualquier archivo `.http` y haz clic en `Send Request` arriba de cada endpoint.

### Opción 3: En Postman
- Puedes importar estos archivos `.http` directamente en Postman usando `File -> Import`.
