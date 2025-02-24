FROM maven:3.9.9
WORKDIR /app/Backend
COPY JDR-server/pom.xml ./
RUN mvn install -DskipTests
COPY JDR-server/src ./src
EXPOSE 8080
