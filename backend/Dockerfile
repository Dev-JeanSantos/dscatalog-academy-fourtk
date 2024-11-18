# Use uma imagem base com Java
FROM eclipse-temurin:17-jdk-alpine

# Instale o Gradle (se necessário)
RUN apk add --no-cache gradle

# Diretório de trabalho dentro do container
WORKDIR /app

# Copie os arquivos de configuração do Gradle e o código-fonte para o contêiner
COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

# Execute o build do projeto para gerar o JAR
RUN gradle build -x test

# Copie o JAR gerado para o contêiner
COPY build/libs/*.jar app.jar

# Exponha a porta que a aplicação vai usar
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["sh", "-c", "java -Dspring.datasource.url=${DATABASE_URL} -Dspring.datasource.username=${DATABASE_USERNAME}  -Dspring.datasource.password=${DATABASE_PASSWORD} -Dserver.port=${PORT} -jar app.jar"]
