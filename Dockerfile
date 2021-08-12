FROM openjdk:11.0.7-slim 
COPY target/cuentacorriente-0.0.1-SNAPSHOT.jar /opt/cuentacorriente-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","/opt/cuentacorriente-0.0.1-SNAPSHOT.jar"]