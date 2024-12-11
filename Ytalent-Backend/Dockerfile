# Usa una imagen base que tenga Java
FROM openjdk:11-jre-slim
# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app
# Copia el archivo JAR generado en el directorio 'target' en tu máquina local al contenedor
COPY Ytalent-Backend/target/Ytalent-Backend-0.0.1-SNAPSHOT.jar /app/Ytalent-Backend.jar
# Expone el puerto 8080
EXPOSE 8080
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "Ytalent-Backend.jar"]
