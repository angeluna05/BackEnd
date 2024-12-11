# Usar una imagen base de Java
FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado por Maven
COPY target/Ytalent-Backend-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto (Render asignará uno dinámicamente)
EXPOSE 8080

# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "app.jar"]
