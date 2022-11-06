FROM openjdk:11-jre

COPY build/libs/yanawaserver-0.0.1-SNAPSHOT.jar app.jar

ENV	USE_PROFILE dep

EXPOSE 8089

ENV JAVA_TOOL_OPTIONS "-Dspring.profiles.active=${USE_PROFILE}\
 -Dcom.sun.management.jmxremote.ssl=false \
 -Dcom.sun.management.jmxremote.authenticate=false \
 -Dcom.sun.management.jmxremote.port=8089 \
 -Dcom.sun.management.jmxremote.rmi.port=8089 \
 -Djava.rmi.server.hostname=13.125.243.184"

ENTRYPOINT ["java","-jar","app.jar"]
