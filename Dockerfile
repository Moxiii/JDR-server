FROM maven:3.9.9
WORKDIR /app/Backend
COPY ../pom.xml ./
RUN mvn install -DskipTests
COPY ../src ./src
EXPOSE 8080
