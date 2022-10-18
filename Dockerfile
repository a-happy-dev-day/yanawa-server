FROM openjdk:11-jre

COPY build/libs/yanawaserver-0.0.1-SNAPSHOT.jar app.jar

ENV	USE_PROFILE dep

ENTRYPOINT ["java","-Dspring.profiles.active=${USE_PROFILE}","-jar","app.jar"]
