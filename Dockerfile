FROM openjdk:11.0.8-jdk
ARG JAR_FILE
ADD /target/${JAR_FILE} cheeper-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/cheeper-0.0.1-SNAPSHOT.jar"]