FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ADD loan-application-service/target/loan-application-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]