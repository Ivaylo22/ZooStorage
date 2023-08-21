FROM openjdk:21-ea-17-oracle

WORKDIR /app

COPY rest/target/tinqin.storage.jar /app/tinqin.storage.jar

EXPOSE 8081

CMD ["java", "-jar", "tinqin.storage.jar"]