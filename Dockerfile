# Usa una imagen base con Java (por ejemplo, OpenJDK 17)
FROM openjdk:17-jdk-slim

# Directorio de la aplicación en el contenedor
WORKDIR /app

# Copia el archivo JAR generado (asegúrate de construir el proyecto previamente)
COPY target/ecolink-0.0.1-SNAPSHOT.jar app.jar

# Copia el archivo .env al directorio de trabajo
COPY .env .

# Expone el puerto configurado (8080)
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
