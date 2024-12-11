# Usar una imagen base de Java
FROM amazoncorretto:22-alpine-jdk

# Copiar el archivo JAR desde el directorio target
COPY target/Ytalent-Backend-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto donde la aplicación escuchará
EXPOSE 8080

# Ejecutar la aplicación Java
ENTRYPOINT ["java", "-jar", "/app.jar"]
