# Usar una imagen base de Java
FROM openjdk:11-jre-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR desde el directorio target
COPY target/Ytalent-Backend-0.0.1-SNAPSHOT.jar /app/Ytalent-Backend.jar

# Exponer el puerto donde la aplicación escuchará
EXPOSE 8080

# Ejecutar la aplicación Java
ENTRYPOINT ["java", "-jar", "Ytalent-Backend.jar"]
