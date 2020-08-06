FROM openjdk:11.0.8-jdk
ARG JAR_FILE
ADD /target/${JAR_FILE} cheeper.jar
ENTRYPOINT ["java","-jar","/cheeper.jar"]