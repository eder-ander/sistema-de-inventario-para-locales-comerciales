# 📝 Guía Rápida de Commits

Una referencia rápida para escribir mensajes de commit estructurados basándose en Conventional Commits.

| Tipo | 📌 Qué significa | 💡 Cuándo usarlo | 🏷️ Ejemplo Real |
| :--- | :--- | :--- | :--- |
| **`feat`** | **Nueva capacidad** | El sistema puede hacer algo nuevo o deja de hacer algo. | `feat(product): add product search` |
| **`fix`** | **Arreglo de error** | Algo estaba mal y ahora funciona bien. | `fix(login): validate empty password` |
| **`refactor`** | **Mejora interna** | Cambias el código pero el usuario no nota diferencia (optimización). | `refactor(service): simplify validation logic` |
| **`chore`** | **Mantenimiento** | Cambios que no afectan la lógica (config, dependencias, build). | `chore(pom): update dependencies` |
| **`docs`** | **Documentación** | Solo cambias README, comentarios internos o guías. | `docs: update API usage` |
| **`style`** | **Formato** | Cambios visuales de código: espacios, indentación, comas. | `style: format code` |
| **`test`** | **Pruebas** | Agregas, actualizas o corriges tests. | `test(user): add login tests` |

> [!TIP]
> **Estructura recomendada:** `tipo(ámbito_opcional): descripción corta en minúsculas`
> Ejemplo: `feat(ventas): crear interfaz del servicio de ventas`
