
FROM eclipse-temurin:21-jdk-jammy

# Define el directorio de trabajo dentro del contenedor donde se ejecutará la app
WORKDIR /app

# Copia el archivo JAR generado desde la carpeta target de tu máquina al contenedor
# Se usa un comodín para que funcione aunque cambie la versión en pom.xml
COPY target/*.jar app.jar

# Informa a Docker que el contenedor escuchará en el puerto 8080 en tiempo de ejecución
EXPOSE 8080

# Metadatos del autor
LABEL authors="ander"

# Comando que se ejecuta al iniciar el contenedor para arrancar la aplicación Java
ENTRYPOINT ["java", "-jar", "app.jar"]
