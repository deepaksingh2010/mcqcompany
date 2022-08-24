FROM openjdk:8-jdk-alpine
COPY target/mcq-company-*-SNAPSHOT.jar mcq-company.jar 
COPY src/main/resources/secure-connect.zip secure-connect.zip
ENTRYPOINT ["java","-jar","-Dspring.profile.active=dev","-Ddatastax.astra.secure-connect-bundle=secure-connect.zip","mcq-company.jar"]