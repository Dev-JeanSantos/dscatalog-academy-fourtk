# Use uma imagem base com Java
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copie os arquivos necessários para o build
COPY build/libs/*.jar app.jar

# Exponha a porta que a aplicação vai usar
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["sh", "-c", "java -Dspring.datasource.url=${DATABASE_URL} -Dserver.port=${PORT} -jar app.jar"]



