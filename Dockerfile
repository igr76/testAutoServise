FROM openjdk:11.0.11-jre-slim
ADD /target/test-0.0.1-SNAPSHOT.jar test-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","test-0.0.1-SNAPSHOT.jar"]
