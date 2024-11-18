# Etapa de build usando uma imagem do Ubuntu
FROM ubuntu:latest AS build

# Atualize o sistema, instale o OpenJDK 17, wget e unzip
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk wget unzip

# Instalar o Gradle
RUN wget https://services.gradle.org/distributions/gradle-8.4-bin.zip -P /tmp && \
    unzip /tmp/gradle-8.4-bin.zip -d /opt && \
    ln -s /opt/gradle-8.4/bin/gradle /usr/bin/gradle

# Verifique a instalação do Gradle
RUN gradle -v

# Copiar os arquivos do projeto para o contêiner (garanta que os arquivos Gradle sejam copiados)
COPY . /app

# Defina o diretório de trabalho
WORKDIR /app

# Execute o build com Gradle (omitindo testes)
RUN gradle clean build -x test --stacktrace

# Etapa de execução usando a imagem slim do OpenJDK
FROM openjdk:17-jdk-slim

# Exponha a porta da aplicação
EXPOSE 8080

# Copiar o JAR gerado pela etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Defina o comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
