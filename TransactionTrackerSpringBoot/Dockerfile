FROM maven:3.8-openjdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mkdir -p /usr/assets/images
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests -U

FROM openjdk:11-jre-slim
COPY --from=build /usr/src/app/target/TransactionTracker-0.0.1-SNAPSHOT.jar /usr/app/TransactionTracker-0.0.1-SNAPSHOT.jar
COPY assets/images /usr/src/app/assets/images
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/TransactionTracker-0.0.1-SNAPSHOT.jar"]

