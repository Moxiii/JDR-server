FROM maven:3.9.9
WORKDIR /app/Backend
COPY JDR-server/pom.xml ./
RUN useradd -ms /bin/sh -u 1001 app
RUN  mvn install -DskipTests
COPY JDR-server/entry.sh ./entry.sh
RUN chmod +x ./entry.sh
EXPOSE 8080
CMD ["./entry.sh" , "backend"]
