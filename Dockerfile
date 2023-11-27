FROM openjdk:17
COPY "./target/Parcial-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8095
ENTRYPOINT [ "java", "-jar", "app.jar" ]